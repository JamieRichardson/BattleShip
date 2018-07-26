public class EmptySea extends Ship {

    public EmptySea() {
        setLength(1);
    }

    @Override
    public boolean shootAt(int row, int column) {
        getHit()[0] = true;
        return false;
    }

    @Override
    public boolean isSunk() {
        return false;
    }

    @Override
    String getShipType() {
        return "empty";
    }

    @Override
    public String toString() {
        if (getHit()[0]) {
            return "-";
        } else {
            return ".";
        }
    }
}
