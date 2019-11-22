//File 4
/**
 * Team Project CSE360
 * Names: Gabriel Anderson 
 * Class ID: 70642
 * Description: AddingMachine.java is the class
 * that acts like a basic calculator with the add,
 * subtract, get total, clear total and convert total to string.
 * GitHub URL (via HTTPS): 
 */

/**
 * Creates Formatter class that acts like a basic calculator with the add,
 * subtract, get total, clear total and convert total to string.
 * 
 * @author Gabriel Anderson <gander16 @ asu.edu>
 * @version 1.1
 * @since 1.0
 */
package formatTextFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Formatter {
	protected int total;
	protected String currentLine, spillOver, inputFileName, outputFileName, indexLength;

	public Formatter() {
		indexLength = "     "; // 5 spaces long
	}

	public void readFile() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("/Users/pankaj/Downloads/myfile.txt"));
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
