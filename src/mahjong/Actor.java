package project;

import java.util.LinkedList;

import project.HonorTile.HonorsSuit;
import project.SuitedTile.SuitedSuit;
import project.Tile.TileType;

abstract public class Actor {

	protected LinkedList<Cell> mPublicCells = new LinkedList<Cell>();
	protected LinkedList<Cell> mPrivateCells = new LinkedList<Cell>();

	abstract public Cell getDiscardTileCell();

	public void addPublicCell(Cell cell) {
		mPublicCells.add(cell);
	}

	public void addPrivateCell(Cell cell) {
		mPrivateCells.add(cell);
	}

	public Cell getPrivateCell(int index) {
		return mPrivateCells.get(index);
	}

	public Cell getPrivateCell(Tile tile) {
		for (Cell cell : mPrivateCells) {
			if (cell.getTile() == tile) {
				return cell;
			}
		}
		return null;
	}

	public Cell getNextEmptyPublicCell() {
		for (Cell cell : mPublicCells) {
			if (cell.isEmpty()) {
				return cell;
			}
		}
		return null;
	}

	public Cell getNextEmptyPublicCell(int offset) {
		for (int i = 0; i < mPublicCells.size(); i++) {
			if (mPublicCells.get(i).isEmpty()) {
				return mPublicCells.get(i + offset);
			}
		}
		return null;
	}

	public Cell getNextEmptyPrivateCell() {
		for (Cell cell : mPrivateCells) {
			if (cell.isEmpty()) {
				return cell;
			}
		}
		return null;
	}

	public boolean containsCellInPublic(Cell cell) {
		for (Cell publicCell : mPublicCells) {
			if (publicCell == cell) {
				return true;
			}
		}
		return false;
	}

	public boolean containsCellInPrivate(Cell cell) {
		for (Cell privateCell : mPrivateCells) {
			if (privateCell == cell) {
				return true;
			}
		}
		return false;
	}
	
	public LinkedList<Tile> getPublicTiles() {
		LinkedList<Tile> publicTiles = new LinkedList<Tile>();
		for (Cell cell : mPublicCells) {
			if (!cell.isEmpty()) {
				publicTiles.add(cell.getTile());
			}
		}
		return publicTiles;
	}
	
	public LinkedList<Tile> getPrivateTiles() {
		LinkedList<Tile> privateTiles = new LinkedList<Tile>();
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				privateTiles.add(cell.getTile());
			}
		}
		return privateTiles;
	}

	public void showPrivateTiles() {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				cell.getTile().show();
			}
		}
	}

	public boolean canHu(Tile tile) {
		boolean canDouble = false;
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				if (cell.getTile().getTileId().equals(tile.getTileId())) {
					canDouble = true;
					break;
				}
			}
		}
		return (canDouble || canPong(tile) || canChow(tile)) && (calculateHeuristic(tile, null) == 0.0);
	}

	public boolean canPong(Tile tile) {
		int count = 1;
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				if (cell.getTile().getTileId().equals(tile.getTileId())) {
					count++;
				}
			}
		}

		if (count == 3) {
			return true;
		} else {
			return false;
		}
	}

	public boolean canChow(Tile tile) {
		if (tile.getType() == TileType.SUITED) {
			SuitedTile suitedTile = (SuitedTile) tile;
			if (suitedTile.getNumber() > 2) {
				if (privateContains(suitedTile.getSuit(), suitedTile.getNumber() - 2)
						&& privateContains(suitedTile.getSuit(), suitedTile.getNumber() - 1)) {
					return true;
				}
			}
			if (suitedTile.getNumber() > 1 && suitedTile.getNumber() < 9) {
				if (privateContains(suitedTile.getSuit(), suitedTile.getNumber() - 1)
						&& privateContains(suitedTile.getSuit(), suitedTile.getNumber() + 1)) {
					return true;
				}
			}
			if (suitedTile.getNumber() < 8) {
				if (privateContains(suitedTile.getSuit(), suitedTile.getNumber() + 1)
						&& privateContains(suitedTile.getSuit(), suitedTile.getNumber() + 2)) {
					return true;
				}
			}
		}
		return false;
	}

	public LinkedList<Cell> getPongCells(Cell centerCell) {
		Tile tile = centerCell.getTile();
		Tile tile1;
		Tile tile2;
		if (tile.getType() == TileType.SUITED) {
			SuitedTile suitedTile = (SuitedTile) tile;
			tile1 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber());
			tile2 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber(), tile1);
		} else {
			HonorTile honorsTile = (HonorTile) tile;
			tile1 = findPrivateTile(honorsTile.getSuit());
			tile2 = findPrivateTile(honorsTile.getSuit(), tile1);
		}
		LinkedList<Cell> pongCells = new LinkedList<Cell>();
		pongCells.add(getPrivateCell(tile1));
		pongCells.add(centerCell);
		pongCells.add(getPrivateCell(tile2));
		return pongCells;
	}

	public LinkedList<Cell> getChowCells(Cell centerCell) {
		Tile tile = centerCell.getTile();
		Tile tile1 = null;
		Tile tile2 = null;
		SuitedTile suitedTile = (SuitedTile) tile;
		if (suitedTile.getNumber() > 2) {
			if (privateContains(suitedTile.getSuit(), suitedTile.getNumber() - 2)
					&& privateContains(suitedTile.getSuit(), suitedTile.getNumber() - 1)) {
				tile1 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber() - 2);
				tile2 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber() - 1);
			}
		}
		if (suitedTile.getNumber() > 1 && suitedTile.getNumber() < 9) {
			if (privateContains(suitedTile.getSuit(), suitedTile.getNumber() - 1)
					&& privateContains(suitedTile.getSuit(), suitedTile.getNumber() + 1)) {
				tile1 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber() - 1);
				tile2 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber() + 1);
			}
		}
		if (suitedTile.getNumber() < 8) {
			if (privateContains(suitedTile.getSuit(), suitedTile.getNumber() + 1)
					&& privateContains(suitedTile.getSuit(), suitedTile.getNumber() + 2)) {
				tile1 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber() + 1);
				tile2 = findPrivateTile(suitedTile.getSuit(), suitedTile.getNumber() + 2);
			}
		}

		LinkedList<Cell> chowCells = new LinkedList<Cell>();
		if (tile1 != null && tile2 != null) {
			chowCells.add(getPrivateCell(tile1));
			chowCells.add(centerCell);
			chowCells.add(getPrivateCell(tile2));
		}
		return chowCells;
	}

	public double calculateHeuristic(Tile addedTile, Tile removedTile) {
		LinkedList<Tile> publicHand = getPublicTiles();
		LinkedList<Tile> privateHand = getPrivateTiles();
		
		if (addedTile != null) {
			privateHand.add(addedTile);
		}
		if (removedTile != null) {
			privateHand.remove(removedTile);
		}
		
		return calculateHeuristic(publicHand, privateHand);
	}
	
	public double calculateHeuristic(LinkedList<Tile> publicHand, LinkedList<Tile> privateHand) {
		LinkedList<Tile> pongs = new LinkedList<Tile>();
		LinkedList<Tile> chows = new LinkedList<Tile>();
		LinkedList<Tile> eyes = new LinkedList<Tile>();
		LinkedList<Tile> unmatchedTiles = new LinkedList<Tile>();
		
		for (int i = 0; i < publicHand.size(); i += 3) {
			if (publicHand.get(i).getTileId() == publicHand.get(i + 1).getTileId()) {
				pongs.add(publicHand.get(i));
				pongs.add(publicHand.get(i + 1));
				pongs.add(publicHand.get(i + 2));
			} else {
				chows.add(publicHand.get(i));
				chows.add(publicHand.get(i + 1));
				chows.add(publicHand.get(i + 2));
			}
		}
		
		// Sort the hand
		privateHand.sort((a, b) -> {
			return a.getTileId().compareTo(b.getTileId());
		});

		// Determine the pongs
		if (privateHand.size() >= 3) {
			int count = 1;
			for (int i = privateHand.size() - 2; i >= 0; i--) {
				if (i + 1 < privateHand.size()) {
					if (privateHand.get(i).getTileId().equals(privateHand.get(i + 1).getTileId())) {
						count++;
					} else {
						count = 1;
					}
					if (count == 3) {
						count = 1;
						pongs.add(privateHand.remove(i));
						pongs.add(privateHand.remove(i));
						pongs.add(privateHand.remove(i));
					}
				}
			}
		}

		// Determine the honors eyes
		if (privateHand.size() >= 2) {
			for (int i = privateHand.size() - 2; i >= 0; i--) {
				if (i + 1 < privateHand.size()) {
					if (privateHand.get(i).getType() == TileType.HONOR && privateHand.get(i + 1).getType() == TileType.HONOR
							&& privateHand.get(i).getTileId().equals(privateHand.get(i + 1).getTileId())) {
						eyes.add(privateHand.remove(i));
						eyes.add(privateHand.remove(i));
					}
				}
			}
		}

		// Add remaining honors to the unmatched tiles
		for (int i = privateHand.size() - 1; i >= 0; i--) {
			if (privateHand.get(i).getType() == TileType.HONOR) {
				unmatchedTiles.add(privateHand.remove(i));
			}
		}
		
		// Temporarily remove duplicate tiles from the hand
		LinkedList<Tile> duplicates = new LinkedList<Tile>();
		if (privateHand.size() >= 2) {
			for (int i = privateHand.size() - 2; i >= 0; i--) {
				if (i + 1 < privateHand.size()) {
					if (privateHand.get(i).getTileId().equals(privateHand.get(i + 1).getTileId())) {
						duplicates.add(privateHand.remove(i + 1));
					}
				}
			}
		}

		// Determine the chows
		if (privateHand.size() >= 3) {
			int count = 1;
			for (int i = privateHand.size() - 2; i >= 0; i--) {
				if (i + 1 < privateHand.size()) {
					SuitedTile curr = (SuitedTile) privateHand.get(i);
					SuitedTile prev = (SuitedTile) privateHand.get(i + 1);
					if (curr.getSuit() == prev.getSuit() && curr.getNumber() == prev.getNumber() - 1) {
						count++;
					} else {
						count = 1;
					}
					if (count == 3) {
						count = 1;
						chows.add(privateHand.remove(i));
						chows.add(privateHand.remove(i));
						chows.add(privateHand.remove(i));
					}
				}
			}
		}

		// Re-add duplicate tiles to the hand
		privateHand.addAll(duplicates);
		privateHand.sort((a, b) -> {
			return a.getTileId().compareTo(b.getTileId());
		});

		// Determine the remaining eyes
		if (privateHand.size() >= 2) {
			for (int i = privateHand.size() - 2; i >= 0; i--) {
				if (i + 1 < privateHand.size()) {
					if (privateHand.get(i).getTileId().equals(privateHand.get(i + 1).getTileId())) {
						eyes.add(privateHand.remove(i));
						eyes.add(privateHand.remove(i));
					}
				}
			}
		}

		// Add remaining tiles in the hand to the unmatched list
		unmatchedTiles.addAll(privateHand);
		privateHand.clear();

		double heuristic = Math.abs(12 - pongs.size() - chows.size()) + Math.abs(2 - eyes.size())
				+ Math.abs(0 - unmatchedTiles.size());
		return heuristic;
	}

	private boolean privateContains(SuitedSuit suit, int number) {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				Tile tile = cell.getTile();
				if (tile.getType() == TileType.SUITED) {
					SuitedTile suitedTile = (SuitedTile) tile;
					if (suitedTile.getSuit() == suit && suitedTile.getNumber() == number) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private Tile findPrivateTile(SuitedSuit suit, int number) {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				Tile tile = cell.getTile();
				if (tile.getType() == TileType.SUITED) {
					SuitedTile suitedTile = (SuitedTile) tile;
					if (suitedTile.getSuit() == suit && suitedTile.getNumber() == number) {
						return suitedTile;
					}
				}
			}
		}
		return null;
	}

	private Tile findPrivateTile(SuitedSuit suit, int number, Tile excludedTile) {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				Tile tile = cell.getTile();
				if (tile.getType() == TileType.SUITED && tile != excludedTile) {
					SuitedTile suitedTile = (SuitedTile) tile;
					if (suitedTile.getSuit() == suit && suitedTile.getNumber() == number) {
						return suitedTile;
					}
				}
			}
		}
		return null;
	}

	private Tile findPrivateTile(HonorsSuit suit) {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				Tile tile = cell.getTile();
				if (tile.getType() == TileType.HONOR) {
					HonorTile honorsTile = (HonorTile) tile;
					if (honorsTile.getSuit() == suit) {
						return honorsTile;
					}
				}
			}
		}
		return null;
	}

	private Tile findPrivateTile(HonorsSuit suit, Tile excludedTile) {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				Tile tile = cell.getTile();
				if (tile.getType() == TileType.HONOR && tile != excludedTile) {
					HonorTile honorsTile = (HonorTile) tile;
					if (honorsTile.getSuit() == suit) {
						return honorsTile;
					}
				}
			}
		}
		return null;
	}
}
