package project2;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Netflix GUI"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 500);
		
		MainPanel panel = new MainPanel(); 
		
		frame.getContentPane().add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {	
			public void windowClosing(WindowEvent we) {
				panel.doClose();
			}
		});
	}
}

		
