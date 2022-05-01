import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

// STARTER FOR STUDENTS
public class Sierpinski extends JPanel implements KeyListener
{
    JFrame frame;
    ArrayList<Point> points;
    int currX,currY;
    Graphics g;
    int padding = 0;
    Polygon polygon;
    int pointSize = 1;
    int numPoints = 3;

    public Sierpinski() {
        frame=new JFrame("Sierpinski Triangle Simulator");
        frame.add(this);
        frame.setSize(1200,700);  // You can change size if you want
        // points=/* result of calling point generator method*;/

        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        points = new ArrayList<>();

        drawTriangle();
        addRandomPoint();
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.g = g;

        /* Set Background Color you Wish */
        g.setColor(Color.BLACK);
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        // Print all points here
//        g.setColor(Color.GREEN);

        System.out.println(points.size());
        g.drawRect(padding, padding, getWidth() - (padding * 2), getHeight() - (padding * 2));

        points.forEach(e -> {
            g.setColor(e.getColor());
            g.fillOval(e.getX(), e.getY(), pointSize, pointSize);
        });
    }

    Color[] colors = {Color.GREEN, Color.BLUE, Color.RED, Color.CYAN, Color.YELLOW};

    public void addRandomPoint(){
        int x , y;

        do {
            x = random(0, getWidth());
            y = random(0, getHeight());
        } while (!polygon.contains(x, y));


        points.add(new Point(x, y, colors[random(0, colors.length - 1)]));
    }

    public void drawTriangle(){
//        points.add(new Point(getWidth()/2 - padding, 0 + padding, Color.green));
        int x, y;
        polygon = new Polygon();

        x = drawOnX(0);
        y = drawOnY(50);
        polygon.addPoint(x, y);
        points.add(new Point(x, y, Color.green));



        x = drawOnX(100);
        y = drawOnY(0);
        polygon.addPoint(x, y);
        points.add(new Point(x, y, Color.green));



        x = drawOnX(100);
        y = drawOnY(100);
        polygon.addPoint(x, y);
        points.add(new Point(x, y, Color.green));



    }

    public void addPoint()
    {
        Color color = Color.GREEN;

        Point randomPoint = points.get((int) (Math.random() * (numPoints)));
        Point currentPoint = points.get((points.size() -1));

        points.add(
                new Point(currentPoint.getX() + ((randomPoint.getX() - currentPoint.getX()) / 2),
                        currentPoint.getY() + ((randomPoint.getY() - currentPoint.getY()) / 2),
                        color));
    }

    public void keyPressed(KeyEvent e)
    {
        // Get a key to add 5 points at a time & speed process
        System.out.println(e.getKeyCode());
        for (int i = 0; i < 1000; i++){
            addPoint();
            repaint();
        }
    }

    public void keyReleased(KeyEvent e)
    {
    }
    public void keyTyped(KeyEvent e)
    {
    }

    public static void main(String[] args)
    {
        Sierpinski app=new Sierpinski();
    }

    // Write a simple odered pair embedded class
    public class Point
    {
        private int x, y;
        private Color color;

        public Point(int x, int y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public Color getColor() {
            return color;
        }
    }


    int random(int min, int max){
        return new Random().nextInt(max - min + 1) + min;
    }


    int drawOnX(int x){
        return (int)(getWidth()/100.0 * x);
    }


    int drawOnY(int y){
        return (int)(getHeight()/100.0 * y);
    }
}
