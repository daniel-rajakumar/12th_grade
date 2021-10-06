import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Main extends JPanel implements KeyListener {
    private JFrame frame;
    private Graphics2D graphics;
    private char[][] maze; 
    private int explorer_x, explorer_y, explorer_dir;
    private int gamePixelSize;  		 
    private int mazeLength_y, mazeLength_x; 
    private int drawString_x, drawString_y;
    private int totalSteps, steps;
    private boolean debug; 
    private boolean didWin;

    public Main() {
        setBoard();  //write this method to read board from file
        frame = new JFrame("A-Mazing Program");
        frame.setSize(1150, 600);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void setBoard()   {
        explorer_dir = 0;
        gamePixelSize = 40;
        debug = true;
        steps = 0;
        didWin = false;
        totalSteps = 0;

        try {
            String file = "Levels/level_1.txt";
            BufferedReader input = new BufferedReader(new FileReader(new File(file)));

            mazeLength_y = (int) Files.lines(Paths.get(file)).count();
            mazeLength_x = (new BufferedReader(new FileReader(new File(file))).readLine().length());

            maze = new char[mazeLength_y][mazeLength_x];

            for (int i = 0; i < mazeLength_y; i++){
                maze[i] = input.readLine().toCharArray();
            }

        } catch (IOException io) { System.err.println(io); }

        placeExplorerInstantiatePosition();
    }

    public void placeExplorerInstantiatePosition(){
        for (int y = 0; y < mazeLength_y; y++){
            for (int x = 0; x < mazeLength_x; x++){
                if (maze[y][x] == 'E'){
                    explorer_x = x;
                    explorer_y = y;
                }
            }
        }
    }



    public void paintComponent(Graphics g) {

        //Make Background Blank
        super.paintComponent(g);
        graphics = (Graphics2D) g;
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, frame.getWidth(), frame.getHeight());



        // DRAW MAZE
        graphics.setColor(Color.GRAY);
        for(int y = 0; y < maze.length; y++){
            for(int x = 0; x < maze[0].length; x++){

                int img_x = (x * gamePixelSize) + gamePixelSize;
                int img_y = (y * gamePixelSize) + gamePixelSize; 
                int img_width = gamePixelSize;
                int img_height = gamePixelSize;

                if (maze[y][x] == 'E'){
                    graphics.setColor(Color.GRAY);
                    try {
                        BufferedImage image = ImageIO.read(new File("grass.png"));

                        graphics.drawImage(image, img_x, img_y, img_width, img_height, null);
                        
                    } catch (IOException e) { e.printStackTrace(); }

                    try {
                        BufferedImage image = ImageIO.read(new File("blob.png"));

                        switch (explorer_dir){
                            case 0:  graphics.drawImage(rotate(image, (90 * 0)), img_x, img_y, img_width, img_height, null); break;
                            case 1:  graphics.drawImage(rotate(image, (90 * 1)), img_x, img_y, img_width, img_height, null); break;
                            case 2:  graphics.drawImage(rotate(image, (90 * 2)), img_x, img_y, img_width, img_height, null); break;
                            case 3:  graphics.drawImage(rotate(image, (90 * 3)), img_x, img_y, img_width, img_height, null); break;
                        }
                        
                    } catch (IOException e) { e.printStackTrace(); }

                } else if (maze[y][x] == 'F'){ //SET FINISH OR GOAL SQUARE
                    try {
                        BufferedImage image_1 = ImageIO.read(new File("grass.png"));
                        BufferedImage image_2 = ImageIO.read(new File("pizza.png"));
                        graphics.drawImage(image_1, img_x, img_y, img_width, img_height, null);
                        graphics.drawImage(image_2, img_x, img_y, img_width, img_height, null);
                    } catch (IOException e) { e.printStackTrace(); }

                } else if(maze[y][x] == ' '){ // OPEN SQUARE GRAY
                    try {
                        BufferedImage image = ImageIO.read(new File("grass.png"));
                        graphics.drawImage(image, img_x, img_y, img_width, img_height, null);
                    } catch (IOException e) { e.printStackTrace(); }

                } else { // BLOCKED SQUARE GRAY OUTLINE
                    try {
                        BufferedImage image = ImageIO.read(new File("water.png"));
                        graphics.drawImage(image, img_x, img_y, img_width, img_height, null);
                    } catch (IOException e) { e.printStackTrace(); }
                }
            }
        }

        drawString_x = 40; 
        drawString_y = mazeLength_y * gamePixelSize + 2 * gamePixelSize;

        if (debug){ // PRINT EXTRA INFO TO HELP DEBUG
            graphics.setColor(Color.RED);
            graphics.drawString("Steps: " + steps, drawString_x, drawString_y);
            graphics.setColor(Color.GRAY);
        }

        if (didWin){
            graphics.setColor(Color.GREEN);
            graphics.drawString("TOTAL STEPS: " + totalSteps, drawString_x, drawString_y + 20);
            graphics.setColor(Color.YELLOW);
            graphics.drawString("CONGRATULATIONS YOU WIN!", drawString_x, drawString_y + 40);
            graphics.setColor(Color.GRAY);

        } else {
             totalSteps = steps + 1;
        }

    }


    //0=up 1=right 2=down 3=left
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        //forward
        if(e.getKeyCode() == 38) {
            switch (explorer_dir) {
                case 0: keypress_North(); break;
                case 1: keypress_East(); break;
                case 2: keypress_South();  break;
                case 3: keypress_West();  break;
            }
        }


        if(e.getKeyCode() == 39) {
            if(++explorer_dir > 3){
                explorer_dir = 0;
            }
        }

        if(e.getKeyCode() == 37) {
            if(--explorer_dir < 0){
                explorer_dir = 3;
            }
        }


        repaint();
    }


    public void keypress_North(){
        if (explorer_y < 1) return;
        if (maze[explorer_y - 1][explorer_x] == '#') return;
        if (maze[explorer_y - 1][explorer_x] == 'F') didWin = true;

        moveForward(0, -1);
    }


    public void keypress_West(){
        if (explorer_x < 1) return;
        if (maze[explorer_y][explorer_x - 1] == '#') return;
        if (maze[explorer_y][explorer_x - 1] == 'F') didWin = true;

        moveForward(-1, 0);
    }

    public void keypress_East(){
        if (explorer_x >= mazeLength_x - 1) return;
        if (maze[explorer_y][explorer_x + 1] == '#') return;
        if (maze[explorer_y][explorer_x + 1] == 'F')  didWin = true;

        moveForward(1, 0);
    }

    public void keypress_South(){
        if (explorer_y >= mazeLength_y - 1) return;
        if (maze[explorer_y + 1][explorer_x] == '#') return; 
        if (maze[explorer_y + 1][explorer_x] == 'F') didWin = true;

        moveForward(0, 1);
    }

    public void moveForward(int x, int y){
        maze[explorer_y][explorer_x] = ' ';
        maze[explorer_y += y][explorer_x += x] = 'E';
        steps++;
    }


    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) { }


    public static BufferedImage rotate(BufferedImage img, int deg) {
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        int imgType = img.getType();

        BufferedImage rotatedImage = new BufferedImage(imgWidth, imgHeight, imgType);

        Graphics2D graphics2D = rotatedImage.createGraphics();

        graphics2D.rotate(Math.toRadians(deg), imgWidth / 2, imgHeight / 2);
        graphics2D.drawImage(img, null, 0, 0);

        
        return rotatedImage;
    }

    public static void main(String[] args) {
        new Main();
    }
}