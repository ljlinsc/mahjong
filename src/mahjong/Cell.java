package project;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Cell extends Pane {

	protected static final double WIDTH = 25;
	protected static final double HEIGHT = 35;
	private Tile mTile = null;

	public Cell() {
		setPrefSize(WIDTH, HEIGHT);
//		setStyle("-fx-background-color: #117864; -fx-background-radius: 2.5px;");
		getChildren().add(new Rectangle(WIDTH, HEIGHT, new Color(0, 0, 0, 0)));
	}

	public Tile getTile() {
		return mTile;
	}

	public void addTile(Tile tile) {
		if (tile != null) {
			mTile = tile;
			getChildren().add(tile);
		} else {
			removeTile();
		}
	}

	public Tile removeTile() {
		Tile tile = mTile;
		mTile = null;
		getChildren().remove(tile);
		return tile;
	}

	public boolean isEmpty() {
		return mTile == null;
	}

	public Bounds getBoundsInScene() {
		return localToScene(getBoundsInLocal());
	}
}
