import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
public class Main extends JPanel implements KeyListener
{
	private JFrame frame;
	private int col=0, row=0,dir = 0; //Location and direction of explorer
	private int size = 40;  		  //size of grid square in pixels
	private boolean debug = true;     //Set to true to print debug info
	private int numRows = 4, numCols=20; // set based on uploaded data
	private char[][] maze=new char[numRows][numCols];

	public Main()
	{
		setBoard();  //write this method to read board from file
		frame=new JFrame("A-Mazing Program");
		frame.setSize(1000,600);
		frame.add(this);
		frame.addKeyListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
	public void paintComponent(Graphics g)
	{

		//Make Background Blank
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(Color.BLACK);
		g2.fillRect(0,0,frame.getWidth(),frame.getHeight());

		// DRAW MAZE
		g2.setColor(Color.GRAY);
		for(int c=0;c<maze[0].length;c++)
		for(int r=0;r<maze.length;r++){
			if (maze[r][c] == 'E'){
				//EXPLORER
				g2.setColor(Color.RED);
				g2.fillRect(c*size+size,r*size+size,size,size);
				g2.setColor(Color.GRAY);
			}
			else if (maze[r][c] == 'F'){
				//SET FINISH OR GOAL SQUARE
			}
			else if(maze[r][c]==' ') // OPEN SQUARE GRAY
				g2.fillRect(c*size+size,r*size+size,size,size);
			else // BLOCKED SQUARE GRAY OUTLINE
				g2.drawRect(c*size+size,r*size+size,size,size);
		}
		if (debug){ // PRINT EXTRA INFO TO HELP DEBUG
			g2.setColor(Color.YELLOW);
			g2.drawString("Debug message here",40, numRows*size+2*size);
			g2.setColor(Color.GRAY);
		}
	}
	public void keyPressed(KeyEvent e)
	{
		System.out.println(e.getKeyCode());
		//forward
		if(e.getKeyCode()==38)
		{
			//0=up 1=right 2=down 3=left
			if(dir==0 && row > 1 && maze[row-1][col] != '#'){
				// Move Explorer up and set previous space to blank
			}

		}
		if(e.getKeyCode()==39) //rotate right
		{
			dir++;
			if(dir>3)
				dir=0;
		}

		repaint();
	}
	public void keyReleased(KeyEvent e) //Required for interface, leave empty
	{
	}
	public void keyTyped(KeyEvent e) // Required for interface, leave open
	{
	}

	public void setBoard()
	{
		// Read maze from file and set to maze 2d array
		maze[0][0] = 'E';
		maze[0][1] = ' ';
		maze[0][2] = ' ';


	}

	public static void main(String[] args)
	{
		new Main();
	}
}