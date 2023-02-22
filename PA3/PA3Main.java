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

public class PA3Main{
    public static void main(String[] args) {
        controlInterface();
    }

    private static void controlInterface(){
        ArrayList<String> inputRes = readInput();
        Garden mainGarden = createGarden(inputRes);
        mainGarden.plant(0,0, "banana");
        mainGarden.plant(0,1,"lily");
        System.out.print(mainGarden.toString());
    }

    private static ArrayList<String> readInput(){
        ArrayList<String> inputRes = new ArrayList<String>();

        try{
            Scanner sc = new Scanner (new File("input.txt"));

            while (sc.hasNextLine()){
                inputRes.add(sc.nextLine());
            }
        } catch(FileNotFoundException e){
            System.out.println("File could not be found.");
        }

        return inputRes;
    }

private static Garden createGarden(ArrayList<String> inputRes){
    int rows = 0;
    int cols = 0;

    for (String cmd : inputRes){
        cmd = cmd.toLowerCase();
        String [] words = cmd.split(" ");
        if (cmd.startsWith("rows")){
            rows = Integer.parseInt(words[1]);
        } else if(cmd.startsWith("cols")){
            cols = Integer.parseInt(words[1]);
        }
    }

    return new Garden(rows, cols);

}
}