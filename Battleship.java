import java.util.Scanner;

public class Battleship{
    /**
     * Main method, generates gameBoard 2d array of chars
     * 
     */
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        char [][] playerBoard = chooseBoardPos(input);
        char [][] computerBoard = genComputerBoard();
        fireShip(playerBoard, computerBoard, input);
    }

    private static char[][] chooseBoardPos(Scanner input){
        char [][] board = {
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
        };
        int numShips = 2;
        int shipSize = 3;
        for (int i=1;i<=numShips;i++){
            System.out.println("Place ship number " + i);
            for (int j=0;j<shipSize;j++){
                System.out.println("Enter the row and column (ex. B2)");
                String userPlacement = input.nextLine();
                int xAxis = (int)userPlacement.charAt(0) - 65;
                int yAxis = Character.getNumericValue(userPlacement.charAt(1));
                board[xAxis][yAxis] = 'C';
            }
        }
        return board;
    }

    private static char[][] genComputerBoard(){
        //Choose a random x and y coordinate, pick random direction and check
        //if the next 3 elements in that direction are unused. If they are,
        //place the ship there
        char [][] board = {
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
        };
        int placementCount = 0;
        int xAxis = 0;
        int yAxis = 0;
        int direction = 0;

        while(placementCount < 2){
            xAxis = (int)Math.floor((Math.random() * 5));
            yAxis = (int)Math.floor((Math.random() * 5));
            direction = (int)Math.floor((Math.random() * 4));

            switch (direction) {
                case 0:
                if (yAxis >= 2){
                    if (board[xAxis][yAxis] == '█' &&
                    board[xAxis][yAxis - 1] == '█' &&
                    board[xAxis][yAxis - 2] == '█'){
                        board[xAxis][yAxis] = 'C';
                        board[xAxis][yAxis - 1] = 'C';
                        board[xAxis][yAxis - 2] = 'C';
                        placementCount++;
                    }
                }
                break;
                case 1:
                if (yAxis <= 2){
                    if (board[xAxis][yAxis] == '█' &&
                    board[xAxis][yAxis + 1] == '█' &&
                    board[xAxis][yAxis + 2] == '█'){
                        board[xAxis][yAxis] = 'C';
                        board[xAxis][yAxis + 1] = 'C';
                        board[xAxis][yAxis + 2] = 'C';
                        placementCount++;
                    }
                }
                break;
                case 2:
                if (xAxis >= 2){
                    if (board[xAxis][yAxis] == '█' &&
                    board[xAxis - 1][yAxis] == '█' &&
                    board[xAxis - 2][yAxis] == '█'){
                        board[xAxis][yAxis] = 'C';
                        board[xAxis - 1][yAxis] = 'C';
                        board[xAxis - 2][yAxis] = 'C';
                        placementCount++;
                    }
                }
                break;
                case 3:
                if (xAxis <= 2){
                    if (board[xAxis][yAxis] == '█' &&
                    board[xAxis + 1][yAxis] == '█' &&
                    board[xAxis + 2][yAxis] == '█'){
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

    private static void fireShip(char[][] playerBoard, char[][] computerBoard,
    Scanner input){
        char [][] compHiddenBoard = {
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
            {'█', '█', '█', '█', '█'},
        };
        int playerHits = 0;
        int computerHits = 0;
        int xAxis = 0;
        int yAxis = 0;
        String userGuess = "";

        while (playerHits < 6 && computerHits < 6){
            System.out.println("Enter the coordinates for your shot (ex.B2)");
            userGuess = input.nextLine();
            xAxis = (int)userGuess.charAt(0) - 65;
            yAxis = Character.getNumericValue(userGuess.charAt(1));
            if (computerBoard[xAxis][yAxis] == 'C'){
                compHiddenBoard[xAxis][yAxis] = 'H';
                System.out.println("Hit!");
                playerHits++;
            } else if (compHiddenBoard[xAxis][yAxis] == 'H'){
                System.out.println("You already hit on those coordinates.");
            } else if (compHiddenBoard[xAxis][yAxis] == 'M'){
                System.out.println("You already missed on those coordinates.");
            } else {
                compHiddenBoard[xAxis][yAxis] = 'M';
                System.out.println("Miss.");
            }
            System.out.println("Computer Board:");
            printBoard(compHiddenBoard);
            computerHits = computerFire(playerBoard, computerHits);
        }

        if (playerHits >= 6){
            System.out.println("Nicely done! You win!");
        } else{
            System.out.println("The computer got you. Better luck next time!");
        }
    }

    private static int computerFire(char[][] board, int computerHits){
        int xAxis = (int)Math.floor((Math.random() * 5));
        int yAxis = (int)Math.floor((Math.random() * 5));
        while (board[xAxis][yAxis] == 'H' || board[xAxis][yAxis] == 'M'){
            xAxis = (int)Math.floor((Math.random() * 5));
            yAxis = (int)Math.floor((Math.random() * 5));
        }
        if (board[xAxis][yAxis] == 'C'){
            board[xAxis][yAxis] = 'H';
            computerHits++;
        } else {
            board[xAxis][yAxis] = 'M';
        }
        System.out.println("Your Board:");
        printBoard(board);

        return computerHits;
    }

    private static void printBoard(char[][] board){
        for (int i=0;i<board.length;i++){
            for (int j=0; j<board[i].length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}