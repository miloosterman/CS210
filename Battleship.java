import java.util.Scanner;

public class Battleship{
    public static void main(String[] args){
        String[][] gameBoard = {
            {"█", "█", "█", "█", "█"},
            {"█", "█", "█", "█", "█"},
            {"█", "█", "█", "█", "█"},
            {"█", "█", "█", "█", "█"},
            {"█", "█", "█", "█", "█"},
        };
        String [][] playerBoard = chooseBoardPos(gameBoard);
        printBoard(playerBoard);
    }

    private static String[][] chooseBoardPos(String[][] board){
        int numShips = 2;
        int shipSize = 3;

        Scanner input = new Scanner(System.in);
        for (int i=1;i<=numShips;i++){
            System.out.println("Place ship number " + i);
            for (int j=0;j<shipSize;j++){
                System.out.println("Enter the row and column (ex. B2)");
                String userPlacement = input.nextLine();
                int xAxis = (int)userPlacement.charAt(0) - 65;
                int yAxis = Character.getNumericValue(userPlacement.charAt(1));
                board[xAxis][yAxis] = "C";
            }
        }
        input.close();
        return board;
    }

    private static void printBoard(String[][] board){
        for (int i=0;i<board.length;i++){
            for (int j=0; j<board[i].length;j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

}