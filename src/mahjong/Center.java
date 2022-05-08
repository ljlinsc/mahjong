package project;

import java.util.LinkedList;

public class Center {

	private LinkedList<Cell> mCells = new LinkedList<Cell>();
	
	public void addCell(Cell cell) {
		mCells.add(cell);
	}
	
	public Cell getNextEmptyCell() {
		for (Cell cell : mCells) {
			if (cell.isEmpty()) {
				return cell;
			}
		}
		return null;
	}
	
	public Cell getLastCell() {
		Cell lastCell = null;
		for (Cell cell : mCells) {
			if (cell.isEmpty()) {
				break;
			}
			lastCell = cell;
		}
		return lastCell;
	}
	
	public Tile getLastTile() {
		return getLastCell().getTile();
	}
	
	public boolean containsCell(Cell cell) {
		for (Cell centerCell : mCells) {
			if (centerCell == cell) {
				return true;
			}
		}
		return false;
	}
}
