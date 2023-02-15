/**
 * Milo Osterman
 * CS210
 * Battleship program written according to specifications of PA1 of CS210
 */
import java.util.Scanner;

public class Battleship {
    /**
     * Main method, create scanner object, create boards, and begin gameplay
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String playerName = getName(input);
        char[][] playerBoard = chooseBoardPos(input, playerName);
        char[][] computerBoard = genComputerBoard();
        fireShip(playerBoard, computerBoard, playerName, input);
    }

    /**
     * Scanner object as parameter. Create board 2d array,
     * populate board with ship locations. Return board array.
     */

    private static String getName(Scanner input) {
        System.out.println("What is your first name?");
        String name = input.nextLine();
        return name;
    }

    private static char[][] chooseBoardPos(Scanner input, String playerName) {
        char[][] board = {
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
        };
        int numShips = 2;
        int shipSize = 3;
        System.out.println("Place your ships " + playerName + "!");
        for (int i = 1; i <= numShips; i++) {
            System.out.println("Place ship number " + i);
            for (int j = 0; j < shipSize; j++) {
                System.out.println("Enter the row and column (ex. B2)");
                String userPlacement = input.nextLine();
                int xAxis = (int) userPlacement.charAt(0) - 65;
                int yAxis = Character.getNumericValue(userPlacement.charAt(1));
                board[xAxis][yAxis] = 'C';
            }
        }
        return board;
    }

    /**
     * Choose a random x and y coordinate, pick random direction and check
     * if the next 3 elements in that direction are unused. If they are,
     * place the ship there. Return computer board array.
     */
    private static char[][] genComputerBoard() {
        char[][] board = {
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
        };
        int placementCount = 0;
        int xAxis = 0;
        int yAxis = 0;
        int direction = 0;

        while (placementCount < 2) {
            xAxis = (int) Math.floor((Math.random() * 5));
            yAxis = (int) Math.floor((Math.random() * 5));
            direction = (int) Math.floor((Math.random() * 4));

            switch (direction) {
                case 0:
                    if (yAxis >= 2) {
                        if (board[xAxis][yAxis] == '█' &&
                                board[xAxis][yAxis - 1] == '█' &&
                                board[xAxis][yAxis - 2] == '█') {
                            board[xAxis][yAxis] = 'C';
                            board[xAxis][yAxis - 1] = 'C';
                            board[xAxis][yAxis - 2] = 'C';
                            placementCount++;
                        }
                    }
                    break;
                case 1:
                    if (yAxis <= 2) {
                        if (board[xAxis][yAxis] == '█' &&
                                board[xAxis][yAxis + 1] == '█' &&
                                board[xAxis][yAxis + 2] == '█') {
                            board[xAxis][yAxis] = 'C';
                            board[xAxis][yAxis + 1] = 'C';
                            board[xAxis][yAxis + 2] = 'C';
                            placementCount++;
                        }
                    }
                    break;
                case 2:
                    if (xAxis >= 2) {
                        if (board[xAxis][yAxis] == '█' &&
                                board[xAxis - 1][yAxis] == '█' &&
                                board[xAxis - 2][yAxis] == '█') {
                            board[xAxis][yAxis] = 'C';
                            board[xAxis - 1][yAxis] = 'C';
                            board[xAxis - 2][yAxis] = 'C';
                            placementCount++;
                        }
                    }
                    break;
                case 3:
                    if (xAxis <= 2) {
                        if (board[xAxis][yAxis] == '█' &&
                                board[xAxis + 1][yAxis] == '█' &&
                                board[xAxis + 2][yAxis] == '█') {
                            board[xAxis][yAxis] = 'C';
                            board[xAxis + 1][yAxis] = 'C';
                            board[xAxis + 2][yAxis] = 'C';
                            placementCount++;
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return board;
    }

    /**
     * Test to see if all ships are sunk each time a guess is made. Repeat
     * guessing until either human or computer ship is sunk and game is over.
     */
    private static void fireShip(char[][] playerBoard, char[][] computerBoard,
            String playerName, Scanner input) {
        char[][] compHiddenBoard = {
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
                { '█', '█', '█', '█', '█' },
        };
        int playerHits = 0;
        int computerHits = 0;
        int xAxis = 0;
        int yAxis = 0;
        String userGuess = "";

        while (playerHits < 6 && computerHits < 6) {
            System.out.println("Call your shot " + playerName + "!");
            System.out.println("Enter the coordinates for your shot (ex.B2)");
            userGuess = input.nextLine();
            xAxis = (int) userGuess.charAt(0) - 65;
            yAxis = Character.getNumericValue(userGuess.charAt(1));
            if (computerBoard[xAxis][yAxis] == 'C') {
                compHiddenBoard[xAxis][yAxis] = 'H';
                System.out.println("Hit!");
                playerHits++;
            } else if (compHiddenBoard[xAxis][yAxis] == 'H') {
                System.out.println("You already hit on those coordinates.");
            } else if (compHiddenBoard[xAxis][yAxis] == 'M') {
                System.out.println("You already missed on those coordinates.");
            } else {
                compHiddenBoard[xAxis][yAxis] = 'M';
                System.out.println("Miss.");
            }
            System.out.println("Computer Board:");
            printBoard(compHiddenBoard);
            computerHits = computerFire(playerBoard, computerHits);
        }

        if (playerHits >= 6) {
            System.out.println("Nicely done! You win " + playerName + "!");
        } else {
            System.out.println("The computer got you. Better luck next time " +
                    playerName + "!");
        }
    }

    /**
     * Checks the player board to see if the square has already been guessed,
     * and generates a new guess until a valid square is found. Changes the
     * element to H or M depending on a hit or miss.
     */
    private static int computerFire(char[][] board, int computerHits) {
        int xAxis = (int) Math.floor((Math.random() * 5));
        int yAxis = (int) Math.floor((Math.random() * 5));
        while (board[xAxis][yAxis] == 'H' || board[xAxis][yAxis] == 'M') {
            xAxis = (int) Math.floor((Math.random() * 5));
            yAxis = (int) Math.floor((Math.random() * 5));
        }
        if (board[xAxis][yAxis] == 'C') {
            board[xAxis][yAxis] = 'H';
            computerHits++;
        } else {
            board[xAxis][yAxis] = 'M';
        }
        System.out.println("Your Board:");
        printBoard(board);

        return computerHits;
    }

    private static void printBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}