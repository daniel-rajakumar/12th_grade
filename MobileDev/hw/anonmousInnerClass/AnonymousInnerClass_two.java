import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AnonymousInnerClass_two extends JPanel {
	public AnonymousInnerClass_two(){
		Dimension size= Toolkit.getDefaultToolkit().getScreenSize();

		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setSize(200, 200);
		setFocusable(true);

		int resScreenWidth = (int)size.getWidth(), resScreenHeight = (int)size.getHeight();
		double physicalWidth = 14.04, physicalHeight = 9.18; // inch
		int numOfPixels = resScreenWidth * resScreenHeight; //pixel
		double screenSize = physicalWidth *  physicalHeight; // inch
		double pixelWidth = (screenSize/numOfPixels) * 100; // inch
		frame.addMouseMotionListener(new  MouseMotionListener(){
			double totalDistance = 0;
			
			public void mouseDragged(MouseEvent e){
				System.out.printf("(%s,%s)\n", e.getX(), e.getY());
			}

			int prevGetX = 0, prevGetY = 0;
			double currentDistance = 1;
			boolean firstLocation = true;
			public void mouseMoved(MouseEvent e) {
				if (firstLocation) 	firstLocation = false; 
				else 				currentDistance = Math.abs((e.getX() - prevGetX) + (e.getY() - prevGetY));

				System.out.printf("current Distrance -> %s, pixel width -> %s /n", currentDistance, pixelWidth);
				currentDistance *= pixelWidth; // adjust the pixel size
				currentDistance *= 0.83333333; // inch -> feet
				totalDistance += currentDistance;
				System.out.printf("(%s)\n", (totalDistance));
				prevGetX = e.getX(); prevGetY = e.getY();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main (String[] args){
		AnonymousInnerClass_two demo = new AnonymousInnerClass_two();
	 }

}