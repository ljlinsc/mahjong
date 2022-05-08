package project;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import project.HonorTile.HonorsSuit;
import project.SuitedTile.SuitedSuit;

public class Game extends Application {

	private static final int COLS = 26;
	private static final int ROWS = 20;

	private Stage mStage;
	private Pane mRoot = new Pane();
	private GridPane mCellPane = new GridPane();
	private HBox mButtonPane = new HBox(5);

	private Player mPlayer = new Player();
	private LinkedList<Actor> mActors = new LinkedList<Actor>();
	private Center mCenter = new Center();
	private Wall mWall = new Wall();

	private int mCurrentActor = 0;

	private Button mDiscardButton = new Button("Discard");
	private Button mHuButton = new Button("Hu");
	private Button mPongButton = new Button("Pong");
	private Button mDontPongButton = new Button("Don't pong");
	private Button mChowButton = new Button("Chow");
	private Button mDontChowButton = new Button("Don't chow");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(mRoot, 802, 850);
		mRoot.setStyle("-fx-background-color: #0E6251;");
		mStage = stage;
		mStage.setScene(scene);
		mStage.show();
		mStage.setOnCloseRequest(eventHandler -> {
			Platform.exit();
			System.exit(0);
		});

		scene.setOnKeyPressed(keyEvent -> {
			if (mPlayer.isTileSelected()) {
				if (keyEvent.getCode() == KeyCode.LEFT) {
					mPlayer.moveTileLeft();
					keyEvent.consume();
				} else if (keyEvent.getCode() == KeyCode.RIGHT) {
					mPlayer.moveTileRight();
					keyEvent.consume();
				} else if (keyEvent.getCode() == KeyCode.ENTER) {
					handleDiscardButton();
					keyEvent.consume();
				}
			}
		});

		mRoot.getChildren().addAll(mCellPane, mButtonPane);

		// Create cells
		Cell[][] cells = new Cell[COLS][ROWS];
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				mCellPane.add(cells[i][j] = new Cell(), i, j);
			}
		}

		// Set up cell pane
		mCellPane.setVgap(5);
		mCellPane.setHgap(5);
		mCellPane.layout();
		mRoot.layout();

		// Create actors
		mActors.add(mPlayer);
		mActors.add(new Bot());
		mActors.add(new Bot());
		mActors.add(new Bot());

		// Allocate cells to actors
		for (int i = 6; i < 20; i++) {
			mActors.get(2).addPrivateCell(cells[i][0]);
			mActors.get(2).addPublicCell(cells[i][1]);
			mPlayer.addPublicCell(cells[i][18]);
			mPlayer.addPrivateCell(cells[i][19]);
		}
		for (int j = 3; j < 17; j++) {
			mActors.get(1).addPrivateCell(cells[0][j]);
			mActors.get(1).addPublicCell(cells[1][j]);
			mActors.get(3).addPublicCell(cells[24][j]);
			mActors.get(3).addPrivateCell(cells[25][j]);
		}

		// Allocate cells to the wall
		for (int i = 2; i < 24; i++) {
			mWall.addCell(cells[i][2]);
			mWall.addCell(cells[i][3]);
		}
		for (int j = 4; j < 16; j++) {
			mWall.addCell(cells[22][j]);
			mWall.addCell(cells[23][j]);
		}
		for (int i = 23; i > 1; i--) {
			mWall.addCell(cells[i][17]);
			mWall.addCell(cells[i][16]);
		}
		for (int j = 15; j > 2; j--) {
			mWall.addCell(cells[2][j]);
			mWall.addCell(cells[3][j]);
		}

		// Allocate cells to the center
		for (int i = 4; i < 22; i++) {
			for (int j = 4; j < 16; j++) {
				mCenter.addCell(cells[i][j]);
			}
		}

		// Create tiles
		LinkedList<Tile> tiles = new LinkedList<Tile>();
		for (int j = 0; j < 4; j++) {
			for (int i = 1; i <= 9; i++) {
				tiles.add(new SuitedTile(mPlayer, SuitedSuit.DOTS, i));
				tiles.add(new SuitedTile(mPlayer, SuitedSuit.BAMBOO, i));
				tiles.add(new SuitedTile(mPlayer, SuitedSuit.CHARACTERS, i));
			}
			tiles.add(new HonorTile(mPlayer, HonorsSuit.EAST));
			tiles.add(new HonorTile(mPlayer, HonorsSuit.SOUTH));
			tiles.add(new HonorTile(mPlayer, HonorsSuit.WEST));
			tiles.add(new HonorTile(mPlayer, HonorsSuit.NORTH));
			tiles.add(new HonorTile(mPlayer, HonorsSuit.RED));
			tiles.add(new HonorTile(mPlayer, HonorsSuit.GREEN));
			tiles.add(new HonorTile(mPlayer, HonorsSuit.WHITE));
		}

		// Shuffle tiles
		Collections.shuffle(tiles);

		// Add tiles to the wall
		for (Tile tile : tiles) {
			mWall.getNextEmptyCell().addTile(tile);
		}

		// Deal tiles
		int count = 0;
		SequentialTransition sequentialTransition = new SequentialTransition();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					sequentialTransition.getChildren().add(
							moveTileBetweenCells(mWall.getCell(count), mActors.get(j).getPrivateCell(4 * i + k), 250));
					count++;
				}
			}
		}
		sequentialTransition.getChildren()
				.add(moveTileBetweenCells(mWall.getCell(count), mActors.get(0).getPrivateCell(12), 250));
		sequentialTransition.getChildren()
				.add(moveTileBetweenCells(mWall.getCell(count + 2), mActors.get(0).getPrivateCell(13), 250));
		sequentialTransition.getChildren()
				.add(moveTileBetweenCells(mWall.getCell(count + 1), mActors.get(1).getPrivateCell(12), 250));
		sequentialTransition.getChildren()
				.add(moveTileBetweenCells(mWall.getCell(count + 3), mActors.get(2).getPrivateCell(12), 250));
		sequentialTransition.getChildren()
				.add(moveTileBetweenCells(mWall.getCell(count + 4), mActors.get(3).getPrivateCell(12), 250));
		sequentialTransition.setOnFinished(eventHandler -> {
			// Start the game
			mPlayer.showPrivateTiles();
			mPlayer.setPrivateTilesIsInteractable(true);
			showButton(mDiscardButton);
		});
		sequentialTransition.playFromStart();

		// Set up buttons
		mButtonPane.relocate(640, 780);
		
		mDiscardButton.setOnAction(eventHandler -> {
			handleDiscardButton();
		});

		mHuButton.setOnAction(actionEvent -> {
			mCurrentActor = 0;
			hideButton(mHuButton);
			currentActorHus();
		});

		mPongButton.setOnAction(actionEvent -> {
			mCurrentActor = 0;
			hideButton(mPongButton);
			hideButton(mDontPongButton);
			currentActorPongs();
		});

		mDontPongButton.setOnAction(actionEvent -> {
			mCurrentActor = (mCurrentActor + 1) % mActors.size();
			hideButton(mPongButton);
			hideButton(mDontPongButton);
			checkForChow();
		});

		mChowButton.setOnAction(actionEvent -> {
			mCurrentActor = 0;
			hideButton(mChowButton);
			hideButton(mDontChowButton);
			currentActorChows();
		});

		mDontChowButton.setOnAction(actionEvent -> {
			hideButton(mChowButton);
			hideButton(mDontChowButton);
			currentActorDraws();
		});
	}

	public void hideButton(Button button) {
		mButtonPane.getChildren().remove(button);
	}

	public void showButton(Button button) {
		mButtonPane.getChildren().add(button);
	}

	public void handleDiscardButton() {
		if (mPlayer.isTileSelected()) {
			Cell origin = mPlayer.getSelectedTileCell();
			mPlayer.setPrivateTilesIsInteractable(false);
			hideButton(mDiscardButton);
			SequentialTransition buttonSequentialTransition = new SequentialTransition();
			buttonSequentialTransition.getChildren()
					.add(moveTileBetweenCells(origin, mCenter.getNextEmptyCell(), 1000));
			buttonSequentialTransition.setOnFinished(eventHandler2 -> {
				checkForHus();
			});
			buttonSequentialTransition.playFromStart();
		}
	}

	public void endGame(boolean playerWon) {
		if (playerWon) {
			new MediaPlayer(new Media(new File("src/project/win.wav").toURI().toString())).play();
		} else {
			new MediaPlayer(new Media(new File("src/project/lose.wav").toURI().toString())).play();
		}

		for (Actor actor : mActors) {
			actor.showPrivateTiles();
		}
	}

	public void checkForHus() {
		for (int i = 0; i < mActors.size(); i++) {
			if (i != mCurrentActor) {
				if (mActors.get(i).canHu(mCenter.getLastTile())) {
					if (i == 0) {
						showButton(mHuButton);
						mHuButton.requestFocus();
						return;
					}
					mCurrentActor = i;
					currentActorHus();
					return;
				}
			}
		}

		checkForPongs();
	}

	public void checkForPongs() {
		for (int i = 0; i < mActors.size(); i++) {
			if (i != mCurrentActor) {
				if (mActors.get(i).canPong(mCenter.getLastTile())) {
					if (i == 0) {
						showButton(mPongButton);
						showButton(mDontPongButton);
						mPongButton.requestFocus();
						return;
					}
					mCurrentActor = i;
					currentActorPongs();
					return;
				}
			}
		}

		mCurrentActor = (mCurrentActor + 1) % mActors.size();
		checkForChow();
	}

	public void checkForChow() {
		if (mActors.get(mCurrentActor).canChow(mCenter.getLastTile())) {
			if (mCurrentActor == 0) {
				showButton(mChowButton);
				showButton(mDontChowButton);
				mChowButton.requestFocus();
				return;
			}
			currentActorChows();
		} else {
			currentActorDraws();
		}
	}

	public TranslateTransition moveTileBetweenCells(Cell origin, Cell destination, int duration) {
		if (origin.getTile() == null) {
			System.out.print(origin);
			System.out.print(" does not have a tile.\n");
			return null;
		}
		Tile tile = origin.getTile();
		tile.toFront();

		Bounds originBounds = origin.getBoundsInScene();
		Bounds destinationBounds = destination.getBoundsInScene();

		TranslateTransition translateTransition = new TranslateTransition();
		translateTransition.setNode(tile);
		translateTransition.setDuration(new Duration(duration));
		translateTransition.setCycleCount(1);
		translateTransition.setByX(destinationBounds.getMinX() - originBounds.getMinX());
		translateTransition.setByY(destinationBounds.getMinY() - originBounds.getMinY());
		translateTransition.setOnFinished(eventHandler -> {
			origin.removeTile();
			destination.addTile(tile);
			if (mPlayer.containsCellInPrivate(destination) || mCenter.containsCell(destination)) {
				tile.show();
			}
			for (Actor actor : mActors) {
				if (actor.containsCellInPublic(destination)) {
					tile.show();
					break;
				}
			}
			new MediaPlayerThread().start();
			tile.setTranslateX(0);
			tile.setTranslateY(0);
		});
		return translateTransition;
	}

	public TranslateTransition moveTileToCenter(Cell origin) {
		return moveTileBetweenCells(origin, mCenter.getNextEmptyCell(), 1000);
	}

	public TranslateTransition moveTileFromWall(Cell destination) {
		return moveTileBetweenCells(mWall.getNextOccupiedCell(), destination, 1000);
	}
	
	public void playerDiscards(Tile drawnTile) {
		mPlayer.setPrivateTilesIsInteractable(true);
		if (drawnTile != null) {
			mPlayer.selectTile(drawnTile);
		} else {
			mPlayer.selectTile(mPlayer.getNextOccupiedPrivateCell().getTile());
		}
		showButton(mDiscardButton);
	}

	public void currentBotDiscards() {
		SequentialTransition sequentialTransition = new SequentialTransition();
		sequentialTransition.getChildren().add(moveTileToCenter(mActors.get(mCurrentActor).getDiscardTileCell()));
		sequentialTransition.setOnFinished(eventHandler -> {
			checkForHus();
		});
		sequentialTransition.playFromStart();
	}

	public void currentActorDraws() {
		if (mWall.isEmpty()) {
			endGame(false);
		}

		Tile tile = mWall.getNextTile();

		if (mActors.get(mCurrentActor).canHu(tile)) {
			if (mCurrentActor == 0) {
				showButton(mHuButton);
				mHuButton.requestFocus();
				return;
			}
			SequentialTransition sequentialTransition = new SequentialTransition();
			sequentialTransition.getChildren()
					.add(moveTileToCenter(mActors.get(mCurrentActor).getNextEmptyPublicCell()));
			sequentialTransition.setOnFinished(eventHandler -> {
				endGame(false);
			});
			sequentialTransition.playFromStart();
		} else {
			SequentialTransition sequentialTransition = new SequentialTransition();
			Tile drawnTile = mWall.getNextTile();
			sequentialTransition.getChildren()
					.add(moveTileFromWall(mActors.get(mCurrentActor).getNextEmptyPrivateCell()));
			sequentialTransition.setOnFinished(eventHandler -> {
				if (mCurrentActor == 0) {
					playerDiscards(drawnTile);
				} else {
					currentBotDiscards();
				}
			});
			sequentialTransition.playFromStart();
		}
	}

	public void currentActorHus() {
		SequentialTransition buttonSequentialTransition = new SequentialTransition();
		buttonSequentialTransition.getChildren().add(
				moveTileBetweenCells(mCenter.getLastCell(), mActors.get(mCurrentActor).getNextEmptyPublicCell(), 1000));
		buttonSequentialTransition.setOnFinished(eventHandler -> {
			endGame(mCurrentActor == 0);
		});
		buttonSequentialTransition.playFromStart();
	}

	public void currentActorPongs() {
		LinkedList<Cell> pongCells = mActors.get(mCurrentActor).getPongCells(mCenter.getLastCell());
		SequentialTransition buttonSequentialTransition = new SequentialTransition();
		for (int j = 0; j < pongCells.size(); j++) {
			buttonSequentialTransition.getChildren().add(
					moveTileBetweenCells(pongCells.get(j), mActors.get(mCurrentActor).getNextEmptyPublicCell(j), 1000));
		}
		buttonSequentialTransition.setOnFinished(eventHandler -> {
			if (mCurrentActor == 0) {
				playerDiscards(null);
			} else {
				currentBotDiscards();
			}
		});
		buttonSequentialTransition.playFromStart();
	}

	public void currentActorChows() {
		LinkedList<Cell> chowCells = mActors.get(mCurrentActor).getChowCells(mCenter.getLastCell());
		SequentialTransition buttonSequentialTransition = new SequentialTransition();
		for (int i = 0; i < chowCells.size(); i++) {
			buttonSequentialTransition.getChildren().add(
					moveTileBetweenCells(chowCells.get(i), mActors.get(mCurrentActor).getNextEmptyPublicCell(i), 1000));
		}
		buttonSequentialTransition.setOnFinished(eventHandler -> {
			if (mCurrentActor == 0) {
				playerDiscards(null);
			} else {
				currentBotDiscards();
			}
		});
		buttonSequentialTransition.playFromStart();
	}

	public class MediaPlayerThread extends Thread {
		@Override
		public void run() {
			new MediaPlayer(new Media(new File("src/project/mahjong.m4a").toURI().toString())).play();
		}
	}
}
