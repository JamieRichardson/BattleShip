public class BattleShip extends Ship {

    public BattleShip() {
        setLength(8);
    }

    @Override
    String getShipType() {
        return "battleship";
    }
}
