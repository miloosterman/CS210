import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;


public class PA3Main {

    public static void main(String[] args) {
        ArrayList<String> inputRes = readInput(args[0]);
        processCommands(inputRes);
    }

    /**
     * Open and read input file, returning result
     * 
     * @return inputRes
     */
    private static ArrayList<String> readInput(String args) {
        ArrayList<String> inputRes = new ArrayList<String>();

        try {
            File file = new File(args);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                inputRes.add(sc.nextLine());
            }
            sc.close();
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
        int row;
        int col;
        int amount;
        String type;
        for (String s : inputRes) {
            String sFormatted = s.trim().toLowerCase();
            String[] tokens = sFormatted.split(" ");
            if (!tokens[0].equals("plant") && !tokens[0].equals("")){
                System.out.println("> " + s.toUpperCase());
                if (!tokens[0].equals("print")){
                    System.out.print("\n");
                }
            }

            switch (tokens[0]) {
                case "plant":
                    row = Integer.parseInt(tokens[1].substring(1, 2));
                    col = Integer.parseInt(tokens[1].substring(3, 4));
                    type = tokens[2];
                    mainGarden.plant(row, col, type);
                    break;
                case "print":
                    System.out.println(mainGarden.toString());
                    break;
                case "grow":
                    if (tokens.length == 2){
                        amount = Integer.parseInt(tokens[1]);
                        mainGarden.grow(amount);
                    } else if (tokens.length == 3 && tokens[2].endsWith(")")){
                        amount = Integer.parseInt(tokens[1]);
                        row = Integer.parseInt(tokens[2].substring(1, 2));
                        col = Integer.parseInt(tokens[2].substring(3, 4));
                        
                        mainGarden.grow(amount, row, col);
                    } else if (tokens.length == 3){
                        amount = Integer.parseInt(tokens[1]);
                        type = tokens[2];
                        mainGarden.grow(amount, type);
                    }
                    break;
                case "harvest":
                    if (tokens.length == 1){
                        mainGarden.remove("vegetable");
                    } else if (tokens.length == 2 && tokens[1].endsWith(")")){
                        row = Integer.parseInt(tokens[1].substring(1, 2));
                        col = Integer.parseInt(tokens[1].substring(3, 4));
                        mainGarden.remove("vegetable", row, col);
                    } else if (tokens.length == 2){
                        type = tokens[1];
                        mainGarden.remove(type, type.charAt(0));
                    }
                    break;
                case "pick":
                    if (tokens.length == 1){
                        mainGarden.remove("flower");
                    } else if (tokens.length == 2 && tokens[1].endsWith(")")){
                        row = Integer.parseInt(tokens[1].substring(1, 2));
                        col = Integer.parseInt(tokens[1].substring(3, 4));
                        mainGarden.remove("flower", row, col);
                    } else if (tokens.length == 2){
                        type = tokens[1];
                        mainGarden.remove(type, type.charAt(0));
                    }
                    break;
                case "cut":
                    if (tokens.length == 1){
                        mainGarden.remove("tree");
                    } else if (tokens.length == 2 && tokens[1].endsWith(")")){
                        row = Integer.parseInt(tokens[1].substring(1, 2));
                        col = Integer.parseInt(tokens[1].substring(3, 4));
                        mainGarden.remove("tree", row, col);
                    } else if (tokens.length == 2){
                        type = tokens[1];
                        mainGarden.remove(type, type.charAt(0));
                    }
                    break;
                default:
                    break;
            }
        }
    }
}