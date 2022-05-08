package project;

import java.util.LinkedList;

public class Bot extends Actor {

	@Override
	public Cell getDiscardTileCell() {
		Tile discardTile = getDiscardTile();
		for (Cell cell : mPrivateCells) {
			if (cell.getTile() == discardTile) {
				return cell;
			}
		}
		return null;
	}
	
	private Tile getDiscardTile() {
		LinkedList<Tile> discardableTiles = getPrivateTiles();
		Tile discardedTile = discardableTiles.getFirst();
		double minHeuristic = calculateHeuristic(null, discardableTiles.getFirst());
		for (int i = 1; i < discardableTiles.size(); i++) {
			Tile tile = discardableTiles.get(i);
			double heuristic = calculateHeuristic(null, tile);
			if (heuristic < minHeuristic) {
				discardedTile = tile;
				minHeuristic = heuristic;
			}
		}
		return discardedTile;
	}
}
