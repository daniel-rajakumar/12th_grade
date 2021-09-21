import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AnonymousInnerClass_three extends JPanel {
	public AnonymousInnerClass_three(){

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(200, 200);
		setFocusable(true);

		addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent e) {  System.out.println(e.getKeyCode() + " hello ");}

			@Override
			public void keyReleased(KeyEvent e) {  System.out.println(e.getKeyCode() + " hello ");}

			public void keyTyped(KeyEvent e) { System.out.println(e.getKeyCode() + " hello ");}

		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main (String[] args){
		new AnonymousInnerClass_three();
	 }

}