package project;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SuitedTile extends Tile {

	private static final double DOT_RADIUS = 2.5;
	private static final double BAMBOO_WIDTH = 1.5;
	private static final double BAMBOO_HEIGHT = 10;
	private SuitedSuit mSuit;
	private int mNumber;

	public SuitedTile(Player player, SuitedSuit suit, int number) {
		super(player);
		mSuit = suit;
		mNumber = number;
	}
	
	public SuitedSuit getSuit() {
		return mSuit;
	}
	
	public int getNumber() {
		return mNumber;
	}
	
	public TileType getType() {
		return TileType.SUITED;
	}
	
	public String getTileId() {
		return mSuit.name() + mNumber;
	}

	@Override
	public void show() {
		setStyle("-fx-background-color: white; -fx-background-radius: 2.5px;");

		getChildren().clear();
		switch (mSuit) {
		case DOTS:
			Dot d1, d2, d3, d4, d5, d6, d7, d8, d9;
			switch (mNumber) {
			case 1:
				d1 = new Dot("red");
				d1.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				getChildren().add(d1);
				break;
			case 2:
				d1 = new Dot("green");
				d2 = new Dot("darkblue");
				d1.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d2.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				getChildren().addAll(d1, d2);
				break;
			case 3:
				d1 = new Dot("darkblue");
				d2 = new Dot("red");
				d3 = new Dot("green");
				d1.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d2.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d3.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3);
				break;
			case 4:
				d1 = new Dot("darkblue");
				d2 = new Dot("green");
				d3 = new Dot("green");
				d4 = new Dot("darkblue");
				d1.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d2.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d3.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d4.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3, d4);
				break;
			case 5:
				d1 = new Dot("darkblue");
				d2 = new Dot("green");
				d3 = new Dot("green");
				d4 = new Dot("darkblue");
				d5 = new Dot("red");
				d1.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d2.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d3.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d4.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d5.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3, d4, d5);
				break;
			case 6:
				d1 = new Dot("darkblue");
				d2 = new Dot("green");
				d3 = new Dot("red");
				d4 = new Dot("red");
				d5 = new Dot("red");
				d6 = new Dot("red");
				d1.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d2.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d3.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d4.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d5.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d6.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3, d4, d5, d6);
				break;
			case 7:
				d1 = new Dot("green");
				d2 = new Dot("green");
				d3 = new Dot("green");
				d4 = new Dot("red");
				d5 = new Dot("red");
				d6 = new Dot("red");
				d7 = new Dot("red");
				d1.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 3 / 16 - DOT_RADIUS);
				d2.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d3.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 5 / 16 - DOT_RADIUS);
				d4.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d5.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d6.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d7.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3, d4, d5, d6, d7);
				break;
			case 8:
				d1 = new Dot("darkblue");
				d2 = new Dot("darkblue");
				d3 = new Dot("darkblue");
				d4 = new Dot("darkblue");
				d5 = new Dot("darkblue");
				d6 = new Dot("darkblue");
				d7 = new Dot("darkblue");
				d8 = new Dot("darkblue");
				d1.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT / 5 - DOT_RADIUS);
				d2.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT / 5 - DOT_RADIUS);
				d3.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 2 / 5 - DOT_RADIUS);
				d4.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 2 / 5 - DOT_RADIUS);
				d5.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 3 / 5 - DOT_RADIUS);
				d6.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 3 / 5 - DOT_RADIUS);
				d7.relocate(WIDTH / 3 - DOT_RADIUS, HEIGHT * 4 / 5 - DOT_RADIUS);
				d8.relocate(WIDTH * 2 / 3 - DOT_RADIUS, HEIGHT * 4 / 5 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3, d4, d5, d6, d7, d8);
				break;
			default:
				d1 = new Dot("green");
				d2 = new Dot("green");
				d3 = new Dot("green");
				d4 = new Dot("red");
				d5 = new Dot("red");
				d6 = new Dot("red");
				d7 = new Dot("darkblue");
				d8 = new Dot("darkblue");
				d9 = new Dot("darkblue");
				d1.relocate(WIDTH / 4 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d2.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d3.relocate(WIDTH * 3 / 4 - DOT_RADIUS, HEIGHT / 4 - DOT_RADIUS);
				d4.relocate(WIDTH / 4 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d5.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d6.relocate(WIDTH * 3 / 4 - DOT_RADIUS, HEIGHT / 2 - DOT_RADIUS);
				d7.relocate(WIDTH / 4 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d8.relocate(WIDTH / 2 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				d9.relocate(WIDTH * 3 / 4 - DOT_RADIUS, HEIGHT * 3 / 4 - DOT_RADIUS);
				getChildren().addAll(d1, d2, d3, d4, d5, d6, d7, d8, d9);
				break;
			}
			break;
		case BAMBOO:
			Bamboo b1, b2, b3, b4, b5, b6, b7, b8, b9;
			switch (mNumber) {
			case 1:
				b1 = new Bamboo("darkblue");
				b1.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				getChildren().add(b1);
				break;
			case 2:
				b1 = new Bamboo("darkblue");
				b2 = new Bamboo("green");
				b1.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2);
				break;
			case 3:
				b1 = new Bamboo("darkblue");
				b2 = new Bamboo("green");
				b3 = new Bamboo("green");
				b1.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3);
				break;
			case 4:
				b1 = new Bamboo("darkblue");
				b2 = new Bamboo("green");
				b3 = new Bamboo("green");
				b4 = new Bamboo("darkblue");
				b1.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b4.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3, b4);
				break;
			case 5:
				b1 = new Bamboo("green");
				b2 = new Bamboo("darkblue");
				b3 = new Bamboo("darkblue");
				b4 = new Bamboo("green");
				b5 = new Bamboo("red");
				b1.relocate(WIDTH / 4 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH * 3 / 4 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH / 4 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b4.relocate(WIDTH * 3 / 4 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b5.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3, b4, b5);
				break;
			case 6:
				b1 = new Bamboo("darkblue");
				b2 = new Bamboo("darkblue");
				b3 = new Bamboo("darkblue");
				b4 = new Bamboo("green");
				b5 = new Bamboo("green");
				b6 = new Bamboo("green");
				b1.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b4.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b5.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b6.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3, b4, b5, b6);
				break;
			case 7:
				b1 = new Bamboo("red");
				b2 = new Bamboo("green");
				b3 = new Bamboo("darkblue");
				b4 = new Bamboo("green");
				b5 = new Bamboo("green");
				b6 = new Bamboo("darkblue");
				b7 = new Bamboo("green");
				b1.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				b4.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				b5.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b6.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b7.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3, b4, b5, b6, b7);
				break;
			case 8:
				b1 = new Bamboo("green");
				b2 = new Bamboo("green");
				b3 = new Bamboo("green");
				b4 = new Bamboo("green");
				b5 = new Bamboo("darkblue");
				b6 = new Bamboo("darkblue");
				b7 = new Bamboo("darkblue");
				b8 = new Bamboo("darkblue");
				b2.setRotate(30);
				b3.setRotate(-30);
				b6.setRotate(-30);
				b7.setRotate(30);
				b1.relocate(WIDTH / 4 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH * 2 / 5 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH * 3 / 5 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b4.relocate(WIDTH * 3 / 4 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b5.relocate(WIDTH / 4 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b6.relocate(WIDTH * 2 / 5 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b7.relocate(WIDTH * 3 / 5 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b8.relocate(WIDTH * 3 / 4 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8);
				break;
			default:
				b1 = new Bamboo("green");
				b2 = new Bamboo("red");
				b3 = new Bamboo("darkblue");
				b4 = new Bamboo("green");
				b5 = new Bamboo("red");
				b6 = new Bamboo("darkblue");
				b7 = new Bamboo("green");
				b8 = new Bamboo("red");
				b9 = new Bamboo("darkblue");
				b1.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b2.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b3.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT / 4 - BAMBOO_HEIGHT / 2);
				b4.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				b5.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				b6.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT / 2 - BAMBOO_HEIGHT / 2);
				b7.relocate(WIDTH / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b8.relocate(WIDTH / 2 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				b9.relocate(WIDTH * 2 / 3 - BAMBOO_WIDTH / 2, HEIGHT * 3 / 4 - BAMBOO_HEIGHT / 2);
				getChildren().addAll(b1, b2, b3, b4, b5, b6, b7, b8, b9);
				break;
			}
			break;
		case CHARACTERS:
			Text characterText;
			switch (mNumber) {
			case 1:
				characterText = new Text("一");
				break;
			case 2:
				characterText = new Text("二");
				break;
			case 3:
				characterText = new Text("三");
				break;
			case 4:
				characterText = new Text("四");
				break;
			case 5:
				characterText = new Text("伍");
				break;
			case 6:
				characterText = new Text("六");
				break;
			case 7:
				characterText = new Text("七");
				break;
			case 8:
				characterText = new Text("八");
				break;
			default:
				characterText = new Text("九");
				break;
			}
			Text wanText = new Text("萬");

			characterText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
			characterText.setFill(Color.DARKBLUE);
			wanText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
			wanText.setFill(Color.RED);

			wanText.relocate(WIDTH / 2 - wanText.getLayoutBounds().getWidth() / 2,
					HEIGHT * 3 / 4 - wanText.getLayoutBounds().getHeight() / 2);
			characterText.relocate(WIDTH / 2 - characterText.getLayoutBounds().getWidth() / 2,
					HEIGHT / 4 - characterText.getLayoutBounds().getHeight() / 2);
			getChildren().addAll(characterText, wanText);
			break;
		}

		getChildren().add(new Rectangle(WIDTH, HEIGHT, new Color(0, 0, 0, 0)));
	}

	public class Dot extends Circle {
		public Dot(String color) {
			super(DOT_RADIUS);
			setStyle("-fx-fill: " + color + ";");
		}
	}

	public class Bamboo extends Rectangle {
		public Bamboo(String color) {
			super(BAMBOO_WIDTH, BAMBOO_HEIGHT);
			setStyle("-fx-fill: white; -fx-stroke: " + color + ";");
		}
	}

	public enum SuitedSuit {
		DOTS, BAMBOO, CHARACTERS
	}
}
