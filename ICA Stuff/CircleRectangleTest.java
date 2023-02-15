import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class CircleRectangleTest 
{
	static List<Circle> circles = new ArrayList<>();
	static List<Rectangle> rects = new ArrayList<>();
	
	public static void main(String[] args)
	{
		createAndShowGUI();       
	}   
	
	public static void createAndShowGUI()
	{
		JFrame mainFrame = new JFrame("Circles and Rectangles!");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(600,400);

		JPanel mainPanel = new JPanel(null);
		JPanel widgetsPanel  = new JPanel();
		widgetsPanel.setLocation(400, 0);
		widgetsPanel.setSize(200,400);
		
		JTextField textField = new JTextField("Circles");
		textField.setColumns(10);
		widgetsPanel.add(textField);		
		
		mainPanel.add(widgetsPanel);

		JPanel graphicsPanel  = new GPanel();
		graphicsPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + "  " + e.getY());
				if (textField.getText().equals("Circles"))
				{
					Circle c = new Circle(e.getX(), e.getY(), 20.0, Color.red);
					circles.add(c);
				}
				else if (textField.getText().equals("Rectangles"))
				{
					Rectangle r = new Rectangle( e.getX(), e.getY(), 20, 20, Color.green );
					rects.add(r);
				}
					
				graphicsPanel.repaint();
			}
		});

		graphicsPanel.setLocation(0, 0);
		graphicsPanel.setSize(400,400);
		graphicsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanel.add(graphicsPanel);

		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);  
	}

	private static class GPanel extends JPanel 
	{				
		public void paintComponent(Graphics g) 
		{    
			int width = getSize().width;
			int height = getSize().height;
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, width, height);
			for (Circle c: circles)
			{
				g.setColor(c.getColor());
				g.fillOval(c.getX(), c.getY(), (int)c.getRadius(), (int)c.getRadius());
			}
			for (Rectangle r: rects)
			{
				g.setColor(r.getColor());
				g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
			}
		}
	}	
}         


class Circle 
{
	private int x, y;
	private Color color;
	private double radius;
	private static int numberOfCircles = 0;

	Circle() 
	{
		x = y = 0;
		radius = 1;
		numberOfCircles++;
	}

	Circle(int x, int y, double newRadius, Color c) 
	{
		this.x = x;
		this.y = y;
		radius = newRadius;
		color = c;
		numberOfCircles++;
	}

	void setRadius(double newRadius) { radius = newRadius; }
	int getX() { return x; }
	int getY() { return y; }
	Color getColor() { return color; }
	double getRadius() { return radius; }
	double getArea() { return radius * radius * Math.PI; }	
	static int getNumberOfCircles() { return numberOfCircles; }
}


class Rectangle 
{
	private int x, y;
	private Color color;
	private int width;
	private int height;
	private static int numberOfRectangles = 0;

	Rectangle() 
	{
		x = y = 0;
		width = height = 1;
		numberOfRectangles++;
	}

	Rectangle( int x, int y,  int w, int h, Color c ) 
	{
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		color = c;
		numberOfRectangles++;
	}

	int getX() { return x; }
	int getY() { return y; }
	int getWidth() { return width; }
	int getHeight() { return height; }
	Color getColor() { return color; }
	double getArea() { return width * height; }	
	static int getNumberOfRectangles() { return numberOfRectangles; }
}