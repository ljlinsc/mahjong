package project;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract public class Tile extends Pane {

	protected static final double WIDTH = 25;
	protected static final double HEIGHT = 35;
	private ChangeListener<Boolean> mChangeListener;
	private boolean mIsInteractable = false;

	abstract public String getTileId();

	abstract public TileType getType();

	abstract public void show();

	public Tile(Player player) {
		setPrefSize(WIDTH, HEIGHT);

		mChangeListener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean oldPropertyValue,
					Boolean newPropertyValue) {
				if (newPropertyValue) {
					player.selectTile(Tile.this);
				}
			}
		};

		addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
			if (mIsInteractable) {
				requestFocus();
			}
		});

		getChildren().add(new Rectangle(WIDTH, HEIGHT, new Color(0, 0, 0, 0)));
		hide();
	}

	public void setIsInteractable(boolean isInteractable) {
		mIsInteractable = isInteractable;

		if (mIsInteractable) {
			setFocusTraversable(true);
			focusedProperty().addListener(mChangeListener);
		} else {
			setFocusTraversable(false);
			focusedProperty().removeListener(mChangeListener);
		}
	}

	public boolean getIsInteractable() {
		return mIsInteractable;
	}

	public void hide() {
		setStyle("-fx-background-color: #27AE60; -fx-background-radius: 2.5px;");
	}

	public void select() {
		setStyle(
				"-fx-background-color: white; -fx-background-radius: 2.5px; -fx-border-color: red; -fx-border-width: 1; -fx-border-style: solid;");
		requestFocus();
	}

	public void deselect() {
		setStyle("-fx-background-color: white; -fx-background-radius: 2.5px; -fx-border-width: 0;");
	}

	public enum TileType {
		SUITED, HONOR
	}
}
