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
        processCommands(inputRes);
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
     * 
     * @param inputRes
     * @return Garden object
     */
    private static Garden createGarden(ArrayList<String> inputRes) {
        String[] rowLine = inputRes.get(0).split(" ");
        String[] colLine = inputRes.get(1).split(" ");

        int rows = Integer.parseInt(rowLine[1]);
        inputRes.remove(0);
        int cols = Integer.parseInt(colLine[1]);
        inputRes.remove(0);

        return new Garden(rows, cols);

    }

    private static void processCommands(ArrayList<String> inputRes) {
        Garden mainGarden = createGarden(inputRes);
        for (String s : inputRes) {
            s = s.trim().toLowerCase();
            String[] tokens = s.split(" ");

            switch (tokens[0]) {
                case "plant":
                    int row = Integer.parseInt(tokens[1].substring(1, 2));
                    int col = Integer.parseInt(tokens[1].substring(3, 4));
                    String type = tokens[2];
                    mainGarden.plant(row, col, type);
                    break;
                case "print":
                    System.out.println(mainGarden.toString());
                case "grow":
                    if (tokens.length == 2){
                        mainGarden.grow(Integer.parseInt(tokens[1]));
                    }
                    break;
                case "harvest":
                    mainGarden.remove("vegetable");
                    break;
                default:
                    break;
            }
        }
    }
}