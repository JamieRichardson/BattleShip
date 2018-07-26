public class BattleCruiser extends Ship {

    public BattleCruiser() {
        setLength(7);
    }

    @Override
    String getShipType() {
        return "battlecruiser";
    }
}
