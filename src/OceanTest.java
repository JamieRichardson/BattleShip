import org.junit.Assert;

public class OceanTest {
    private Ocean testOcean;
    private BattleCruiser battleCruiser;

    @org.junit.Before
    public void setUp() throws Exception {
        testOcean = new Ocean();
        battleCruiser = new BattleCruiser();
    }

    @org.junit.Test
    public void placeAllShipsRandomly() throws Exception {
    }

    @org.junit.Test
    public void isOccupied() throws Exception {
        battleCruiser.placeShipAt(0, 0, true, testOcean);
        Assert.assertTrue("Spot was occupied but returned false", testOcean.isOccupied(0,0));
        Assert.assertFalse("Spot was not occupied but returned true", testOcean.isOccupied(3, 0));
    }

    @org.junit.Test
    public void shootAt() throws Exception {
        Destroyer destroyer = new Destroyer();
        destroyer.placeShipAt(5, 17, false, testOcean);
        testOcean.shootAt(5, 17);
        testOcean.shootAt(6, 17);
        testOcean.shootAt(7, 17);
        testOcean.shootAt(8, 17);
        Assert.assertTrue("Ship was shot, but didn't sink", destroyer.isSunk());
    }

    @org.junit.Test
    public void okayToPlaceShipAt() throws Exception {
        BattleCruiser testCruiser = new BattleCruiser();
        testCruiser.placeShipAt(3, 2, true, testOcean);
        Assert.assertFalse("Ship was allowed to hang over the board vertically", testCruiser.okToPlaceShipAt(14, 0, false, testOcean)); //place that hangs over the board - vertically
        Assert.assertFalse("Ship was allowed to hang over the board horizontally", testCruiser.okToPlaceShipAt(0, 14, true, testOcean)); //place that hangs over the board - horizontally
        Assert.assertFalse("Ship was allowed to be placed in an occupied place", testCruiser.okToPlaceShipAt(3, 2, true, testOcean)); //place already occupied
        Assert.assertFalse("Ship was allowed to be placed beside another ship horizontally", testCruiser.okToPlaceShipAt(3, 9, true, testOcean)); // place right beside another ship - horizontal
        Assert.assertFalse("Ship was allowed to be placed beside another ship Vertically", testCruiser.okToPlaceShipAt(4, 2, false, testOcean)); // place right beside another ship - vertical
        Assert.assertTrue("Ship could not be placed on the top row horizontally", testCruiser.okToPlaceShipAt(0, 2, true, testOcean)); // place on first row - horizontal
        Assert.assertTrue("Ship could not be placed on the bottom row horizontally", testCruiser.okToPlaceShipAt(19, 2, true, testOcean)); // place on last row - horizontal
        Assert.assertTrue("Ship could not be placed on last row vertically", testCruiser.okToPlaceShipAt(13, 0, false, testOcean)); // place up to last row - vertical
        Assert.assertTrue("Ship could not be placed on last row and last column vertically", testCruiser.okToPlaceShipAt(13, 19, false, testOcean)); // place up to last row in last column - vertical
        Assert.assertTrue("Ship could not be placed somewhere in the middle vertically", testCruiser.okToPlaceShipAt(6, 5, false, testOcean)); // place up to last row in last column - vertical
        testCruiser.placeShipAt(6, 5, false, testOcean);
        Assert.assertFalse("Ship could not be placed somewhere in the middle vertically", testCruiser.okToPlaceShipAt(6, 5, false, testOcean)); // place up to last row in last column - vertical
        Assert.assertTrue("Ship could not be placed somewhere in the middle horizontally", testCruiser.okToPlaceShipAt(10, 10, true, testOcean)); // place up to last row in last column - vertical
        testCruiser.placeShipAt(10, 10, true, testOcean);
        Assert.assertFalse("Ship could not be placed somewhere in the middle horizontally", testCruiser.okToPlaceShipAt(10, 10, true, testOcean)); // place up to last row in last column - vertical
    }

    @org.junit.Test
    public void getShotsFired() throws Exception {
        testOcean.shootAt(1, 1);
        Assert.assertEquals(1, testOcean.getShotsFired());
        testOcean.shootAt(2, 2);
        Assert.assertEquals(2, testOcean.getShotsFired());
    }

    @org.junit.Test
    public void getHitCount() throws Exception {
        battleCruiser.placeShipAt(0, 1, true, testOcean);
        testOcean.shootAt(0, 0);
        Assert.assertEquals(0, testOcean.getHitCount());
        testOcean.shootAt(0, 1);
        Assert.assertEquals(1, testOcean.getHitCount());
        testOcean.shootAt(0, 2);
        Assert.assertEquals(2, testOcean.getHitCount());
    }

    @org.junit.Test
    public void getShipsSunk() throws Exception {
        battleCruiser.placeShipAt(1, 1, true, testOcean);
        testOcean.shootAt(1, 1);
        testOcean.shootAt(1, 2);
        testOcean.shootAt(1, 3);
        testOcean.shootAt(1, 4);
        testOcean.shootAt(1, 5);
        testOcean.shootAt(1, 6);
        testOcean.shootAt(1, 7);
        Assert.assertEquals(1, testOcean.shipsSunk);
    }

    @org.junit.Test
    public void isGameOver() throws Exception {
        testOcean.shipsSunk = 12;
        Assert.assertFalse("isGameOver returned true but all ships were not sunk", testOcean.isGameOver());
        testOcean.shipsSunk = 13;
        Assert.assertTrue("isGameOver returned false but all ships were sunk", testOcean.isGameOver());
    }

    @org.junit.Test
    public void getShipArray() throws Exception {
    }

    @org.junit.Test
    public void print() throws Exception {
    }

}