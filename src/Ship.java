public abstract class Ship {

    private int bowRow;
    private int bowColumn;
    private int length;
    private boolean horizontal;
    private boolean[] hit = new boolean[8];

    int getBowRow() {
        return bowRow;
    }

    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }

    int getBowColumn() {
        return bowColumn;
    }

    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }

    public int getLength() {
        return length;
    }

    void setLength(int length) {
        this.length = length;
    }

    boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    boolean[] getHit() {
        return hit;
    }

    public void setHit(boolean[] hit) {
        this.hit = hit;
    } //test message

    abstract String getShipType();

    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        int rowStart;
        int rowEnd;
        int columnStart;
        int columnEnd;
        final Ship[][] ships = ocean.getShipArray();
        if (horizontal) {
            if (length + column >= 21) {
                return false;
            }
            columnStart = column == 0 ? 0 : column - 1; //If trying to place ship, starting with the first column, you don't need to check the column before
            columnEnd = column + length == 20 ? 20 : column + length + 1; //If trying to place ship, ending at the last column, there's no column after to check
            rowStart = row == 0 ? row : row - 1; //If trying to place in the top row there is no row above to check
            rowEnd = row >= 19 ? 20 : row + 2; //If the last spot in the ship would end up on the last row there is no row below to check
            for (int i = rowStart; i < rowEnd; i++) {
                for (int j = columnStart; j < columnEnd; j++) {
                    if (!"empty".equals(ships[i][j].getShipType())) {
                        return false;
                    }
                }
            }
        } else {
            if (row + length >= 21) {
                return false;
            }
            rowStart = row == 0 ? row : row - 1; //If trying to place in the top row there is no row above to check
            rowEnd = row + length == 20 ? 20 : row + length + 1; //If the last spot in the ship would end up on the last row there is no row below to check
            columnStart = column == 0 ? 0 : column - 1; //If trying to place ship, starting with the first column, you don't need to check the column before
            columnEnd = column >= 19 ? 20 : column + 2; //If trying to place ship, ending at the last column, there's no column after to check
            for (int i = rowStart; i < rowEnd; i++) {
                for (int j = columnStart; j < columnEnd; j++) {
                    if (!"empty".equals(ships[i][j].getShipType())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        this.horizontal = horizontal;
        this.bowRow = row;
        this.bowColumn = column;
        if (horizontal) {
            for (int i = 0; i < length; i++) {
                ocean.ships[row][column + i] = this;
            }

        } else {
            for (int i = 0; i < length; i++) {
                ocean.ships[row + i][column] = this;
            }
        }
    }

    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, marks that part of
     * the ship as ”hit” (in the hit array, 0 indicates the bow)
     *
     * @param row integer for the row in the ocean grid to shoot at
     * @param column integer for the column in the ocean grid to shoot at
     * @return return true if hit, otherwise return false.
     */
    public boolean shootAt(int row, int column) {
        int hitArrayCount = 0;
        if (isHorizontal()) {
            for (int i = getBowColumn(); i < getBowColumn() + length + 1; i++) {
                if (i == column && row == getBowRow() && !isSunk()) {
                    this.hit[hitArrayCount] = true;
                    return true;
                }
                hitArrayCount++;
            }
        } else {
            for (int i = getBowRow(); i < getBowRow() + length + 1; i++) { //added i < getBowRow()
                if (i == row && column == getBowColumn() && !isSunk()) {
                    this.hit[hitArrayCount] = true; //Use setHit() instead?
                    return true;
                }
                hitArrayCount++;
            }
        }
        return false;
    }

    public boolean isSunk() {
        int hitCount = 0;
        for (boolean hit : getHit()) {
            if (hit) {
                hitCount++;
            }
        }
        return hitCount == length;
    }

    @Override
    public String toString() {
        return this.isSunk() ? "x" : "S";
    }
}
