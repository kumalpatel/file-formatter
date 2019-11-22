// File 1
package formatTextFile;

//         Name: Gabriel Anderson
//  Description: The StartGUI class creates a controlpanel and
//               adds it as its Applet content and also sets its size.

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
