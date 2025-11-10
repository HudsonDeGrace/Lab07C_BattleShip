public class BSRunner {
    public static void main(String[] args) {
        BSFrame frame = new BSFrame();

        Ship battleship1 = new Ship(5);
        battleship1.placeShip();
        Ship battleship2 = new Ship(4);
        battleship2.placeShip();
        Ship battleship3 = new Ship(3);
        battleship3.placeShip();
        Ship battleship4 = new Ship(3);
        battleship4.placeShip();
        Ship battleship5 = new Ship(2);
        battleship5.placeShip();
    }
}
