package project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class HonorTile extends Tile {

	private HonorsSuit mSuit;

	public HonorTile(Player player, HonorsSuit suit) {
		super(player);
		mSuit = suit;
	}
	
	public HonorsSuit getSuit() {
		return mSuit;
	}
	
	public TileType getType() {
		return TileType.HONOR;
	}
	
	public String getTileId() {
		return mSuit.name();
	}

	@Override
	public void show() {
		setStyle("-fx-background-color: white; -fx-background-radius: 2.5px;");

		getChildren().clear();
		Text text = new Text();
		switch (mSuit) {
		case EAST:
			text.setText("東");
			text.setFill(Color.DARKBLUE);
			break;
		case SOUTH:
			text.setText("南");
			text.setFill(Color.DARKBLUE);
			break;
		case WEST:
			text.setText("西");
			text.setFill(Color.DARKBLUE);
			break;
		case NORTH:
			text.setText("北");
			text.setFill(Color.DARKBLUE);
			break;
		case RED:
			text.setText("中");
			text.setFill(Color.RED);
			break;
		case GREEN:
			text.setText("發");
			text.setFill(Color.GREEN);
			break;
		case WHITE:
			Rectangle window1 = new Rectangle(WIDTH - 10, HEIGHT - 10);
			Rectangle window2 = new Rectangle(WIDTH - 13, HEIGHT - 13);
			window1.setStyle("-fx-fill: white; -fx-stroke: darkblue;");
			window2.setStyle("-fx-fill: white; -fx-stroke: darkblue;");
			window1.relocate(5, 5);
			window2.relocate(6.5, 6.5);
			getChildren().addAll(window1, window2);
			break;
		}

		if (mSuit != HonorsSuit.WHITE) {
			text.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
			text.relocate(WIDTH / 2 - text.getLayoutBounds().getWidth() / 2,
					HEIGHT / 2 - text.getLayoutBounds().getHeight() / 2);
			getChildren().add(text);
		}

		getChildren().add(new Rectangle(WIDTH, HEIGHT, new Color(0, 0, 0, 0)));
	}

	public enum HonorsSuit {
		EAST, SOUTH, WEST, NORTH, RED, GREEN, WHITE
	}
}
