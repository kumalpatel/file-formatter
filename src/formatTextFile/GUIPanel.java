package formatTextFile;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIPanel extends JPanel {
	// components of the panel
	private JButton start, clear;
	private JLabel label1, label2;
	private JLabel message;	// not placed yet
	private JPanel buttons1;
	private JTextField field1, field2;

	// Constructor to create all components, add their listener to them,
	// and arrange them using a layout.
	public GUIPanel(int width, int height) {

		// create 3 buttons, start, clear, and color chooser for waves.
		start = new JButton("Start");
		clear = new JButton("Clear");

		// initialize JTexfields
		field1 = new JTextField(10);
		field2 = new JTextField(10);

		// add a listener to each button
		start.addActionListener(new ButtonListener());
		clear.addActionListener(new ButtonListener());

		buttons1 = new JPanel();
		buttons1.setLayout(new GridLayout(2, 1));

		buttons1.add(start);
		buttons1.add(clear);

		// create a label for the delay
		label1 = new JLabel("Input File Name: ");

		JPanel panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		panel3.add(label1, BorderLayout.NORTH);
		panel3.add(field1, BorderLayout.SOUTH);

		// create a label for the wave width
		label2 = new JLabel("Output File Name: ");

		JPanel panel4 = new JPanel();
		panel4.setLayout(new BorderLayout());
		panel4.add(label2, BorderLayout.NORTH);
		panel4.add(field2, BorderLayout.SOUTH);

		message = new JLabel();

		JPanel panel6 = new JPanel();
		panel6.setLayout(new GridLayout(3, 1));
		panel6.add(panel3);
		panel6.add(panel4);
		panel6.add(message);

		JPanel panel5 = new JPanel();
		panel5.setLayout(new BorderLayout());
		panel5.add(buttons1, BorderLayout.SOUTH);
		panel5.add(panel6, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(panel5, BorderLayout.WEST);

	}

	// ButtonListener defines actions to be performed when each button
	// is pushed.
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object action = event.getSource();

			if (action == start) {
				// wPanel.resume();			
				String inputFileName = field1.getText();
				String outputFileName = field2.getText();
				// setup Formatter class
				Formatter doc = new Formatter(inputFileName, outputFileName);
				// check for initial errors
				message.setText(doc.checkInitialErrors(inputFileName, outputFileName));
				
				// if there are no error messages, 
				// then it's all clear for formatting
				if(message.getText().contentEquals("<html></html>")) {
					if(doc.createOutput() == 1) {
						message.setText("<html>" + outputFileName + " created.</br></html>");
					
				}
				
				
			} else if (action == clear) {
				field1.setText("");
				field2.setText("");
				message.setText("");
			}
		}
	} // end of ButtonListener
}

