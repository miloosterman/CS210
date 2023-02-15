import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;

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
}
