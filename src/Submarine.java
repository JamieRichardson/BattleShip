public class Submarine extends Ship {

    public Submarine() {
        setLength(3);
    }

    @Override
    String getShipType() {
        return "submarine";
    }
}
