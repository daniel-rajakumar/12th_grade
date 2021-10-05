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
	private int explorer_y = 0, explorer_x = 0, explorer_dir = 0;
	private int gamePixelSize = 40;  		 
	private boolean debug = true; 
	private int mazeLength_x, mazeLength_y; 
	private char[][] maze; 
	private int steps = 0;

	public Main() {
		setBoard();  //write this method to read board from file
		frame = new JFrame("A-Mazing Program");
		frame.setSize(1000,600);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}



	public void paintComponent(Graphics g) {

		//Make Background Blank
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		// DRAW MAZE
		g2.setColor(Color.GRAY);
		for(int c = 0; c < maze[0].length; c++)
			for(int r = 0; r < maze.length; r++){

				int x = c * gamePixelSize + gamePixelSize;
				int y = r * gamePixelSize + gamePixelSize; 
				int width = gamePixelSize;
				int height = gamePixelSize;

				if (maze[r][c] == 'E'){
					g2.setColor(Color.GRAY);
					g2.fillRect(x,y,width,height);

					try {
						BufferedImage image = ImageIO.read(new File("image.png"));

						switch (explorer_dir){
							case 0:  g2.drawImage(rotate(image, (90 * 0)), x, y, width, height, null); break;
							case 1:  g2.drawImage(rotate(image, (90 * 1)), x, y, width, height, null); break;
							case 2:  g2.drawImage(rotate(image, (90 * 2)), x, y, width, height, null); break;
							case 3:  g2.drawImage(rotate(image, (90 * 3)), x, y, width, height, null); break;
						}
						
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else if (maze[r][c] == 'F'){
					//SET FINISH OR GOAL SQUARE
					g2.setColor(Color.GREEN);
					g2.fillRect(x, y, width, height);
					g2.setColor(Color.GRAY);

				} else if(maze[r][c]==' ') // OPEN SQUARE GRAY
					g2.fillRect(x,y,width,height);

				  else // BLOCKED SQUARE GRAY OUTLINE
					g2.drawRect(x,y,width,height);
			}

		if (debug){ // PRINT EXTRA INFO TO HELP DEBUG
			g2.setColor(Color.YELLOW);
			// g2.drawString("Debug message here",40, numRows*size+2*size);
			// if (dir == 0) g2.drawString("Dir: UP ",40, numRows*size+2*size);
			// if (dir == 1) g2.drawString("Dir: RIGHT ",40, numRows*size+2*size);
			// if (dir == 2) g2.drawString("Dir: DOWN ",40, numRows*size+2*size);
			// if (dir == 3) g2.drawString("Dir: LEFT ",40, numRows*size+2*size);
			// g2.drawString("Steps: " + steps, 0, (mazeLength_x * gamePixelSize + 2 * gamePixelSize));

			int loc_x = 0; 
			int loc_y = mazeLength_x * gamePixelSize + 2 * gamePixelSize;
			g2.drawString("Steps: " + steps, loc_x, loc_y);
			g2.setColor(Color.GRAY);
		}

	}


	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		//forward

		if(e.getKeyCode()==38) {
			//0=up 1=right 2=down 3=left
			if ((explorer_dir==0) && ( explorer_x > 0) && (maze[explorer_x-1][explorer_y] != '#')) {
				maze[explorer_x--][explorer_y] = ' ';
				maze[explorer_x][explorer_y] = 'E';
				steps++;
			}

			if ((explorer_dir == 3) && (explorer_y > 0) && (maze[explorer_x][explorer_y-1] != '#')) {
				maze[explorer_x][explorer_y--] = ' ';
				maze[explorer_x][explorer_y] = 'E';
				steps++;
			}

			if ((explorer_dir == 1) && (explorer_y < mazeLength_y - 1) && (maze[explorer_x][explorer_y+1] != '#')) {
				maze[explorer_x][explorer_y++] = ' ';
				maze[explorer_x][explorer_y] = 'E';
				steps++;
			}

			if ((explorer_dir==2) && (explorer_x < mazeLength_x - 1) && (maze[explorer_x+1][explorer_y] != '#')) {
				maze[explorer_x++][explorer_y] = ' ';
				maze[explorer_x][explorer_y] = 'E';
				steps++;
			}


		}


		if(e.getKeyCode()==39) 
			if(++explorer_dir>3)
				explorer_dir=0;

		if(e.getKeyCode()==37) 
			if(--explorer_dir<0)
				explorer_dir=3;


		repaint();
	}


	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }


	public void setBoard()   {

		// Read maze from file and set to maze 2d array
		try {
			String file = "maze0.txt";

			BufferedReader input = new BufferedReader(new FileReader(new File(file)));
			mazeLength_x = (int) Files.lines(Paths.get(file)).count();
			mazeLength_y = (new BufferedReader(new FileReader(new File(file))).readLine().length());

			maze = new char[mazeLength_x][mazeLength_y];

			for (int i = 0; i < mazeLength_x; i++)
				maze[i] = input.readLine().toCharArray();

		} catch (IOException io) {
			System.err.println(io);
        }
	}

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