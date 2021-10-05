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
	private int explorer_x, explorer_y, explorer_dir;
	private int gamePixelSize;  		 
	private boolean debug; 
	private int mazeLength_y, mazeLength_x; 
	private char[][] maze; 
	private int steps;

	public Main() {
		setBoard();  //write this method to read board from file
		frame = new JFrame("A-Mazing Program");
		frame.setSize(1000, 600);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void setBoard()   {

		explorer_x = 0; explorer_y = 0; explorer_dir = 0;
		gamePixelSize = 40;
		debug = true;
		steps = 0;

		try {
			String file = "maze0.txt";
			BufferedReader input = new BufferedReader(new FileReader(new File(file)));

			mazeLength_y = (int) Files.lines(Paths.get(file)).count();
			mazeLength_x = (new BufferedReader(new FileReader(new File(file))).readLine().length());

			maze = new char[mazeLength_y][mazeLength_x];

			for (int i = 0; i < mazeLength_y; i++){
				maze[i] = input.readLine().toCharArray();
			}

		} catch (IOException io) {
			System.err.println(io);
        }
	}



	public void paintComponent(Graphics g) {
		int debug_loc_x = 40; 
		int debug_loc_y = mazeLength_y * gamePixelSize + 2 * gamePixelSize;

		//Make Background Blank
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, frame.getWidth(), frame.getHeight());

		// DRAW MAZE
		g2.setColor(Color.GRAY);
		for(int y = 0; y < maze.length; y++){
			for(int x = 0; x < maze[0].length; x++){

				int img_x = (x * gamePixelSize) + gamePixelSize;
				int img_y = (y * gamePixelSize) + gamePixelSize; 
				int img_width = gamePixelSize;
				int img_height = gamePixelSize;

				if (maze[y][x] == 'E'){
					g2.setColor(Color.GRAY);
					g2.fillRect(img_x, img_y, img_width, img_height);

					try {
						BufferedImage image = ImageIO.read(new File("image.png"));

						switch (explorer_dir){
							case 0:  g2.drawImage(rotate(image, (90 * 0)), img_x, img_y, img_width, img_height, null); break;
							case 1:  g2.drawImage(rotate(image, (90 * 1)), img_x, img_y, img_width, img_height, null); break;
							case 2:  g2.drawImage(rotate(image, (90 * 2)), img_x, img_y, img_width, img_height, null); break;
							case 3:  g2.drawImage(rotate(image, (90 * 3)), img_x, img_y, img_width, img_height, null); break;
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else if (maze[y][x] == 'F'){
					//SET FINISH OR GOAL SQUARE
					g2.setColor(Color.GREEN);
					g2.fillRect(img_x, img_y, img_width, img_height);
					g2.setColor(Color.GRAY);

				} else if(maze[y][x] == ' '){ // OPEN SQUARE GRAY
					g2.fillRect(img_x, img_y, img_width, img_height);

			 	} else { // BLOCKED SQUARE GRAY OUTLINE
					g2.drawRect(img_x, img_y, img_width, img_height);
				}
			}
		}

		if (debug){ // PRINT EXTRA INFO TO HELP DEBUG
			g2.setColor(Color.YELLOW);
			// g2.drawString("Debug message here",40, numRows*size+2*size);
			// if (dir == 0) g2.drawString("Dir: UP ",40, numRows*size+2*size);
			// if (dir == 1) g2.drawString("Dir: RIGHT ",40, numRows*size+2*size);
			// if (dir == 2) g2.drawString("Dir: DOWN ",40, numRows*size+2*size);
			// if (dir == 3) g2.drawString("Dir: LEFT ",40, numRows*size+2*size);
			// g2.drawString("Steps: " + steps, 0, (mazeLength_x * gamePixelSize + 2 * gamePixelSize));
			g2.drawString("Steps: " + steps, debug_loc_x, debug_loc_y);
			g2.setColor(Color.GRAY);
		}

	}


	//0=up 1=right 2=down 3=left
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());

		//forward
		if(e.getKeyCode() == 38) {
			switch (explorer_dir) {
				case 0: keypress_North(); break;
				case 1: keypress_South(); break;
				case 2: keypress_West();  break;
				case 3: keypress_East();  break;
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


		if (maze[explorer_y - 1][explorer_x] != '#') {
			maze[explorer_y--][explorer_x] = ' ';
			// System.out.printf("in: %s\n", maze[explorer_y][explorer_x]);
			maze[explorer_y][explorer_x] = 'E';
			steps++;
		}
	}


	public void keypress_East(){
		if (explorer_x < 1) return;
		if (maze[explorer_y][explorer_x - 1] == '#') return;

			System.out.printf("in: %s\n", maze[explorer_y][explorer_x]);
		if (maze[explorer_y][explorer_x] == 'F') {
		}
		maze[explorer_y][explorer_x--] = ' ';
		maze[explorer_y][explorer_x] = 'E';
		steps++;
		
	}


	public void keypress_South(){
		if (explorer_x >= mazeLength_x - 1) return;

		if (maze[explorer_y][explorer_x + 1] != '#') {
			maze[explorer_y][explorer_x++] = ' ';
			// System.out.printf("in: %s\n", maze[explorer_y][explorer_x]);
			maze[explorer_y][explorer_x] = 'E';
			steps++;
		}
	}

	public void keypress_West(){
		if (explorer_y >= mazeLength_y - 1) return;

		if (maze[explorer_y + 1][explorer_x] != '#') {
			maze[explorer_y++][explorer_x] = ' ';
			// System.out.printf("in: %s\n", maze[explorer_y][explorer_x]);
			maze[explorer_y][explorer_x] = 'E';
			steps++;
		}
	}












	public String checkForWin(){
		return null;
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