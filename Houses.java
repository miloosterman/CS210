import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.lang.Float;

public class Houses {
    public static void main(String[] args) {
        createAndShowGUI();
        HashMap<String, ArrayList<String>> houseList = new HashMap<String, ArrayList<String>>();
        ArrayList<Integer> housePrices = new ArrayList<Integer>();
        ArrayList<Integer> coords = new ArrayList<Integer>();
        processInput(houseList);
        housePrices = convertPrices(houseList.get("Price"));
        coords = convertCoords(houseList.get("X Coords"), houseList.get("Y Coords"));
        System.out.println();

    }
/**
 * Create main frame, create main panel and widget panel, add text fields with labels and button and add to
 * widget panel. Add main panel and widget panel to main frame.
 */
    public static void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Home Price Distribution");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 525);

        JPanel mainPanel = new JPanel(null);
        
        JPanel widgetPanel = new JPanel();
        widgetPanel.setLocation(0,450);
        widgetPanel.setSize(500,50);
        widgetPanel.setBackground(Color.gray);
        JLabel fileLabel = new JLabel("File:");
        JTextField fileTextField = new JTextField(10);
        widgetPanel.add(fileLabel);
        widgetPanel.add(fileTextField);
        JTextField priceTextField = new JTextField(10);
        JLabel priceLabel = new JLabel("Price:");
        widgetPanel.add(priceLabel);
        widgetPanel.add(priceTextField);
        JButton plotButton = new JButton("Plot");
        widgetPanel.add(plotButton);

        JPanel graphicsPanel = new GPanel();
        graphicsPanel.setLocation(0, 0);
        graphicsPanel.setSize(500,450);
        graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        mainPanel.add(graphicsPanel);
        mainPanel.add(widgetPanel);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    private static void processInput(HashMap<String, ArrayList<String>> houseList) {
        ArrayList<String> xCoords = new ArrayList<String>();
        ArrayList<String> yCoords = new ArrayList<String>();
        ArrayList<String> price = new ArrayList<String>();
        houseList.put("X Coords", xCoords);
        houseList.put("Y Coords", yCoords);
        houseList.put("Price", price);
        try {
            Scanner scanner = new Scanner(new File("houses.csv"));
            String header = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String currLine = scanner.nextLine();
                String[] splitLine = currLine.split(",");
                houseList.get("X Coords").add(splitLine[splitLine.length - 1]);
                houseList.get("Y Coords").add(splitLine[splitLine.length - 2]);
                houseList.get("Price").add(splitLine[splitLine.length - 3]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
    }
    
    private static ArrayList<Integer> convertPrices(ArrayList<String> housePrices){
        ArrayList<Integer> newPrices = new ArrayList<Integer>();
        for (String i : housePrices) {
            newPrices.add(Integer.parseInt(i));
        }
        return newPrices;
    }
    
    private static ArrayList<Integer> convertCoords(ArrayList<String> xCoords, ArrayList<String> yCoords){
        float tempX;
        float tempY;
        int convertedX;
        int convertedY;

        for (int i = 0; i <= xCoords.size();i++){
            tempX = Integer.parseInt(xCoords.get(i));
            tempY = Integer.parseInt(yCoords.get(i));
            convertedX = tempX

        }
    }
    
    private static class GPanel extends JPanel{
            public void paintComponent(Graphics g){
                g.setColor(Color.white);
            }
        }
    }

