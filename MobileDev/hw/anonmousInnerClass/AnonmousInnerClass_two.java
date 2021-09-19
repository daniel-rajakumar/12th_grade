import java.awt.event.*;
import javax.swing.*;

public class AnonmousInnerClass_two extends JPanel {
	public AnonmousInnerClass_two(){
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(200, 200);
		setFocusable(true);

		frame.addMouseMotionListener(new  MouseMotionListener(){
			public void mouseDragged(MouseEvent e){
				System.out.println("hi");
			}

			public void mouseMoved(MouseEvent e) {
				System.out.printf("(%s,%s)\n", e.getX(), e.getY());
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main (String[] args){
		AnonmousInnerClass_two demo = new AnonmousInnerClass_two();
	 }

}