public class Destroyer extends Ship {

    public Destroyer() {
        setLength(4);
    }

    @Override
    String getShipType() {
        return "destroyer";
    }
}
