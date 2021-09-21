import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class AnonymousInnerClass_three extends JPanel {

	public AnonymousInnerClass_three(){
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setVisible(true);
		setFocusable(true);

		frame.addKeyListener(new KeyListener(){

			@Override
			public void keyTyped(KeyEvent e) {
				switch (e.getKeyChar()) {
					case 'a': case 'e': case 'i': case 'o': case 'u':
						System.out.printf("%s -> vowels\n", e.getKeyChar());
						break;

					default:
						System.out.printf("%s -> consonants\n", e.getKeyChar());
						break;
				}
				if (e.getKeyChar() == 'e'){

				}
				
			}

			@Override
			public void keyPressed(KeyEvent e) {

				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main (String [] args){
		AnonymousInnerClass_three demo = new AnonymousInnerClass_three();
	}

	public class KeyInterface implements KeyListener {
			public void keyPressed(KeyEvent e){ System.out.println(e.getKeyChar()); }
			public void keyReleased(KeyEvent e){}
			public void keyTyped(KeyEvent e){}
	}
}