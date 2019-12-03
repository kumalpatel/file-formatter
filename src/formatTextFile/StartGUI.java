package formatTextFile;

import javax.swing.JApplet;

public class StartGUI extends JApplet {
	private final int WIDTH = 800;
	private final int HEIGHT = 340;

	public void init() {
		ControlPanel panel = new ControlPanel(WIDTH, HEIGHT);
		getContentPane().add(panel);
		setSize(WIDTH, HEIGHT);
	}
}
