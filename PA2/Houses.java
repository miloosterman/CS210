/**
 * Milo Osterman
 * CS210
 * House coordinate plotting program with GUI that finds houses cheaper than an input value
 * plots them, and saves them to a txt file
 */

import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Double;
import java.io.PrintWriter;

public class Houses {
    //Global arraylist to store coordinates of houses
    public static ArrayList<Double[]> points = new ArrayList<Double[]>();

    public static void main(String[] args) {
        createAndShowGUI();
    }
    /**
     * Create main JFrame components, set locations, add actionlistener to JButton,
     * create graphics panel.
     */
    private static void createAndShowGUI() {
        JFrame mainFrame = new JFrame("Home Price Distribution");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 525);

        JPanel mainPanel = new JPanel(null);
        JPanel widgetPanel = new JPanel();
        JLabel fileLabel = new JLabel("File:");
        JTextField fileTextField = new JTextField("houses.csv");
        JLabel priceLabel = new JLabel("Price:");
        JTextField priceTextField = new JTextField(10);
        JButton plotButton = new JButton("Plot");

        mainPanel.setSize(500,450);
        widgetPanel.setLocation(0, 450);
        widgetPanel.setSize(500, 50);
        widgetPanel.setBackground(Color.gray);
        widgetPanel.add(fileLabel);
        widgetPanel.add(fileTextField);
        widgetPanel.add(priceLabel);
        widgetPanel.add(priceTextField);
        JPanel graphicsPanel = new Houses.GPanel();

        plotButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName;
                String priceStr;

                fileName = fileTextField.getText();
                priceStr = priceTextField.getText();
                if (fileName.isEmpty() || priceStr.isEmpty()) {
                    System.out.println("File name and price must both be input.");
                } else {
                    try {
                        points.clear();
                        processInput(fileName, priceStr);
                        graphicsPanel.repaint();
                    } catch (NumberFormatException ex) {
                        System.out.println("The number format was incorrect.");
                    }
                }
            }
        });
        widgetPanel.add(plotButton);
        mainPanel.add(widgetPanel);
        graphicsPanel.setLocation(0, 0);
        graphicsPanel.setSize(500, 450);
        graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        mainPanel.add(graphicsPanel);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);

    }
    //Simple function to find minimum value of arraylist of doubles
    private static Double findMin(ArrayList<Double[]> list, int index) {
        Double min = list.get(0)[index];
        for (Double[] xy : points) {
            if (xy[index] < min) {
                min = xy[index];
            }
        }

        return Math.abs(min);
    }
    //Simple function to find maximum value of arraylist of doubles
    private static Double findMax(ArrayList<Double[]> list, int index) {
        Double max = list.get(0)[index];
        for (Double[] xy : points) {
            if (xy[index] > max) {
                max = xy[index];
            }
        }

        return Math.abs(max);
    }
    /* Create scanner and read in filename from arguments, adding each point to points arraylist
     * that is less than the price input. Also output to cheaphouses.txt file.
     */
    private static void processInput(String fileName, String priceStr) {
        HashMap<String, String[]> houseList = new HashMap<String, String[]>();
        int housePrice = Integer.parseInt(priceStr);
        try {
            Scanner scanner = new Scanner(new File("houses.csv"));
            String header = scanner.nextLine();
            while (scanner.hasNextLine()) {
                String currLine = scanner.nextLine();
                String[] splitLine = currLine.split(",");
                houseList.put(splitLine[0], splitLine);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }

        try {
            PrintWriter outputFile = new PrintWriter("cheaphouses.txt");
            houseList.forEach((k, v) -> {
                int priceVal = Integer.parseInt(v[9]);
                Double xVal = Double.parseDouble(v[10]);
                Double yVal = Double.parseDouble(v[11]);
                if (housePrice > priceVal) {
                    Double[] coords = new Double[] { xVal, yVal };
                    points.add(coords);
                    for (String s : v){
                        outputFile.print(s);
                    }
                    outputFile.print("\n");
                }
            });
            outputFile.close();

        } catch (FileNotFoundException ex) {
            System.out.println("Could not find file.");
        }
    }

    private static class GPanel extends JPanel {
        /*paintComponent method handles the graphics panel, initializing background rectangle
         * and populating with points if the points arraylist contains values
         */
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int width = getSize().width;
            int height = getSize().height;
            g.setColor(Color.white);
            g.fillRect(0, 0, width, height);

            if (!points.isEmpty()){
                Double maxX = findMin(points, 0);
                Double minY = findMin(points, 1);
                Double minX = findMax(points, 0);
                Double maxY = findMax(points, 1);
                g.setColor(Color.black);
                for (Double[] xy : points) {
                    Double convertedX = (((Math.abs(xy[0]) - minX) / (maxX - minX)) * 425);
                    Double convertedY = (((Math.abs(xy[1]) - minY) / (maxY - minY)) * 490);
                    int newConvertedX = (int) Math.floor(convertedX);
                    int newConvertedY = (int) Math.floor(convertedY);
                    
                    g.fillOval(newConvertedY, newConvertedX, 5, 5);
            }
        }
    }
}
}

