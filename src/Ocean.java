import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Ocean {

    Ship[][] ships = new Ship[20][20];
    List<Ship> shipsToPlace = new ArrayList<>();
    int shotsFired;
    private int hitCount = 0;
    int shipsSunk = 0;

    Ocean() {
        for (int r = 0; r < ships.length; r++) {
            for (int c = 0; c < ships.length; c++) {
                ships[r][c] = new EmptySea();
            }
        }
        this.shotsFired = 0;
        BattleShip battleShip = new BattleShip();
        shipsToPlace.add(battleShip);
        BattleCruiser battleCruiser = new BattleCruiser();
        shipsToPlace.add(battleCruiser);
        Cruiser cruiser_one = new Cruiser();
        shipsToPlace.add(cruiser_one);
        Cruiser cruiser_two = new Cruiser();
        shipsToPlace.add(cruiser_two);
        LightCruiser lightCruiser_one = new LightCruiser();
        shipsToPlace.add(lightCruiser_one);
        LightCruiser lightCruiser_two = new LightCruiser();
        shipsToPlace.add(lightCruiser_two);
        Destroyer destroyer_one = new Destroyer();
        shipsToPlace.add(destroyer_one);
        Destroyer destroyer_two = new Destroyer();
        shipsToPlace.add(destroyer_two);
        Destroyer destroyer_three = new Destroyer();
        shipsToPlace.add(destroyer_three);
        Submarine submarine_one = new Submarine();
        shipsToPlace.add(submarine_one);
        Submarine submarine_two = new Submarine();
        shipsToPlace.add(submarine_two);
        Submarine submarine_three = new Submarine();
        shipsToPlace.add(submarine_three);
        Submarine submarine_four = new Submarine();
        shipsToPlace.add(submarine_four);
    }

    void placeAllShipsRandomly() {
        Random r = new Random();
        for (Ship ship : shipsToPlace) {
            boolean shipPlaced = false;
            while (!shipPlaced) {
                Boolean isHorizontal = r.nextBoolean();
                int randomRow = r.nextInt(20);
                int randomColumn = r.nextInt(20);
                if (ship.okToPlaceShipAt(randomRow, randomColumn, isHorizontal, this)) {
                    ship.placeShipAt(randomRow, randomColumn, isHorizontal, this);
                    shipPlaced = true;
                }
            }
        }
    }

    boolean isOccupied(int row, int column) {
        return !ships[row][column].getShipType().equals("empty");
    }

    boolean shootAt(int row, int column) {
        shotsFired++;
        boolean shotResult = ships[row][column].shootAt(row, column);
        if (shotResult) {
            System.out.println("Hit");
            hitCount++;
            if (ships[row][column].isSunk()) {
                System.out.println("You just sank a " + ships[row][column].getShipType());
                shipsSunk++;
            }
        } else {
            System.out.println("Miss");
        }
        return shotResult;
    }

    int getShotsFired() {
        return this.shotsFired;
    }

    int getHitCount() {
        return this.hitCount;
    }

    private int getShipsSunk() {
        return this.shipsSunk;
    }

    boolean isGameOver() {
        return getShipsSunk() == 13;
    }

    Ship[][] getShipArray() {
        return this.ships;
    }

    void print() {
        System.out.print("    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19");
        System.out.println();
        for (int r = 0; r < ships.length; r++) {
            if (r < 10) {
                System.out.print(" " + r + " ");
            } else {
                System.out.print(r + " ");
            }
            for (int c = 0; c < ships.length; c++) {
                if (ships[r][c].isSunk() || "empty".equals(ships[r][c].getShipType())) {
                    System.out.print(" " + ships[r][c].toString() + " ");
                } else {
                    int hitPosition = ships[r][c].isHorizontal() ? c - ships[r][c].getBowColumn() : r - ships[r][c].getBowRow();
                    if (ships[r][c].getHit()[hitPosition]) {
                        System.out.print(" " + ships[r][c].toString() + " ");
                    } else {
                        System.out.print(" " + "." + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    public void testPrint() {
        System.out.print("    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19");
        System.out.println();
        for (int r = 0; r < ships.length; r++) {
            if (r < 10) {
                System.out.print(" " + r + " ");
            } else {
                System.out.print(r + " ");
            }
            for (int c = 0; c < ships.length; c++) {
                System.out.print(" " + ships[r][c].toString() + " ");
            }
            System.out.println();
        }
    }
}
