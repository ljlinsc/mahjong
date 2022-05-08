package project;

import java.util.LinkedList;

public class Wall {

	private LinkedList<Cell> mCells = new LinkedList<Cell>();

	public void addCell(Cell cell) {
		mCells.add(cell);
	}

	public Cell getCell(int index) {
		return mCells.get(index);
	}

	public Cell getNextOccupiedCell() {
		for (Cell cell : mCells) {
			if (!cell.isEmpty()) {
				return cell;
			}
		}
		return null;
	}
	
	public Tile getNextTile() {
		return getNextOccupiedCell().getTile();
	}
	
	public Cell getNextEmptyCell() {
		for (Cell cell : mCells) {
			if (cell.isEmpty()) {
				return cell;
			}
		}
		return null;
	}
	
	public boolean isEmpty() {
		return getNextOccupiedCell() == null;
	}
}
