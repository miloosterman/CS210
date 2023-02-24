import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Input file consists of:
 * rows: int
 * cols: int
 * 
 * PLANT (x,y) plantType - plant a plantType to coords
 * PRINT - read entire garden to stdout
 * GROW num - each plant grows specified number of times
 * GROW [num] (row,col) - grow specific plant at position, "can't grow there" if invalid
 * GROW [num][plant] - grows plants of specified type num of times
 * HARVEST - remove all veggies from garden
 * HARVEST(row,col) - remove from specified coord, "can't harvest there" if invalid
 * HARVEST[type] - harvest all veg of type
 * PICK - pick all flowers
 * PICK(row,col) - ''
 * PICK[type] - ''
 * CUT - ''
 * CUT(row, col) - ''
 * CUT[type] - ''
 */

public class PA3Main {
    private static final int MAX_COLS = 16;

    public static void main(String[] args) {
        controlInterface();
    }

    private static void controlInterface() {
        ArrayList<String> inputRes = readInput();
        Garden mainGarden = createGarden(inputRes);
        mainGarden.plant(0, 0, "lily");
        mainGarden.grow(4);
        System.out.print(mainGarden.toString());
    }

    /**
     * Open and read input file, returning result
     * 
     * @return inputRes
     */
    private static ArrayList<String> readInput() {
        ArrayList<String> inputRes = new ArrayList<String>();

        try {
            Scanner sc = new Scanner(new File("input.txt"));

            while (sc.hasNextLine()) {
                inputRes.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File could not be found.");
        }

        return inputRes;
    }
    /**
     * Check input file for rows and columns, create Garden object from results
     * @param inputRes
     * @return Garden object
     */
    private static Garden createGarden(ArrayList<String> inputRes) {
        int rows = 0;
        int cols = 0;

        for (String cmd : inputRes) {
            cmd = cmd.toLowerCase();
            String[] words = cmd.split(" ");
            if (cmd.startsWith("rows")) {
                rows = Integer.parseInt(words[1]);
            } else if (cmd.startsWith("cols")) {
                cols = Integer.parseInt(words[1]);
                if (cols > MAX_COLS) {
                    cols = 16;
                }
            }
        }

        return new Garden(rows, cols);

    }
}