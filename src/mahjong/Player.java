package project;

public class Player extends Actor {

	private Tile mSelectedTile = null;

	public void selectTile(Tile tile) {
		if (mSelectedTile != null) {
			mSelectedTile.deselect();
		}
		tile.select();
		mSelectedTile = tile;
	}

	public void deselectTile() {
		if (mSelectedTile != null) {
			mSelectedTile.deselect();
		}
		mSelectedTile = null;
	}

	public boolean isTileSelected() {
		return mSelectedTile != null;
	}
	
	public Tile getSelectedTile() {
		return mSelectedTile;
	}

	public Cell getSelectedTileCell() {
		for (Cell cell : mPrivateCells) {
			if (cell.getTile() == mSelectedTile) {
				return cell;
			}
		}
		return null;
	}
	
	public Cell getNextOccupiedPrivateCell() {
		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				return cell;
			}
		}
		return null;
	}

	public void setPrivateTilesIsInteractable(boolean isInteractable) {
		if (!isInteractable) {
			deselectTile();
		}

		for (Cell cell : mPrivateCells) {
			if (!cell.isEmpty()) {
				cell.getTile().setIsInteractable(isInteractable);
			}
		}
	}

	public int getPrivateCellIndex(Cell cell) {
		for (int i = 0; i < mPrivateCells.size(); i++) {
			if (mPrivateCells.get(i) == cell) {
				return i;
			}
		}
		return -1;
	}
	
	public void moveTileLeft() {
		int originIndex = getPrivateCellIndex(getSelectedTileCell());
		if (originIndex == 0) {
			return;
		}
		Tile tile = mPrivateCells.get(originIndex).removeTile();
		mPrivateCells.get(originIndex).addTile(mPrivateCells.get(originIndex - 1).removeTile());
		mPrivateCells.get(originIndex - 1).addTile(tile);
	}
	
	public void moveTileRight() {
		int originIndex = getPrivateCellIndex(getSelectedTileCell());
		if (originIndex == mPrivateCells.size() - 1) {
			return;
		}
		Tile tile = mPrivateCells.get(originIndex).removeTile();
		mPrivateCells.get(originIndex).addTile(mPrivateCells.get(originIndex + 1).removeTile());
		mPrivateCells.get(originIndex + 1).addTile(tile);
	}

	@Override
	public Cell getDiscardTileCell() {
		return getSelectedTileCell();
	}
}
