public class Cruiser extends Ship {

    public Cruiser() {
        setLength(6);
    }

    @Override
    String getShipType() {
        return "cruiser";
    }
}
