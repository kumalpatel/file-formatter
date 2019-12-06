/**
 * Team Project CSE360
 * Names: Gabriel Anderson 
 * Class ID: 70642
 * Description: Formatter.java is the class that 
 * transforms the inputed text file into a formatted output.
 * GitHub URL (via HTTPS): 
 */

/**
 * Creates Formatter class that reads an input string from a text file,
 * and converts it into a standardized format.
 * 
 * @author Gabriel Anderson <gander16@asu.edu>
 * @version 1.2
 * @since 1.0
 */
package formatTextFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

public class Formatter {
	protected int total, textAlign;
	protected boolean doubleSpaced, block, indent, twoColumn, title;
	protected String currentString, spillOver, inputFileName, outputFileName, indentLength, extra;
	protected BufferedReader reader;
	protected BufferedWriter outputWriter;

	public Formatter(String inputFile, String outputFile) {
		indentLength = "     "; // 5 spaces long
		currentString = "";
		spillOver = "";
		extra = "";
		textAlign = 0; // left aligned
		doubleSpaced = false;
		block = false;
		twoColumn = true;
		indent = false;
		title = false;

		inputFileName = inputFile;
		outputFileName = outputFile;
	}

	public void readFile() {
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
			String line = "";
			while (line != null) {

				// read next line
				line = reader.readLine();
				if (line.length() == 2) {
					checkFlags(line);
				} 
				else {
					if (title) {
						printTitle(line);
					}
					if (twoColumn) {
						printTwoColumn(line);
					} 
					else {
						printSingleLine(line);
					}
				}

				if (doubleSpaced) {
					System.out.print("\n");
				}
			}
			reader.close();
			printSingleLine(spillOver);
			PrintStream out = new PrintStream(new FileOutputStream(outputFileName));
			System.setOut(out);

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void checkFlags(String line) {
		String temp = line.toLowerCase();
		if (temp.equals("-r")) {
			textAlign = 2; // right flag
		} else if (temp.equals("-c")) {
			textAlign = 1; // center flag
		} else if (temp.equals("-l")) {
			textAlign = 0; // left flag
		} else if (temp.equals("-t")) {
			title = true;
		} else if (temp.equals("-n")) {
			indent = false;
			block = false;
		} else if (temp.equals("-d")) {
			doubleSpaced = true;
		} else if (temp.equals("-s")) {
			doubleSpaced = false;
		} else if (temp.equals("-i")) {
			indent = true;
		} else if (temp.equals("-b")) {
			block = true;
		} else if (temp.equals("-e")) {
			if (twoColumn = false) {
				System.out.print("\n");
			}
		} else if (temp.equals("-2")) {
			twoColumn = true;
		} else if (temp.equals("-1")) {
			twoColumn = false;
		} else {
			printSingleLine(line);
		}
	}

	public void printTwoColumn(String line) {
		spillOver = line;
		if (extra.length() > 0) {
			spillOver = extra + spillOver;
			extra = "";
		}
		if (spillOver.length() >= 70) {
			currentString = spillOver.substring(0, 71);
			extra = spillOver.substring(71);
		} else {
			currentString = spillOver.substring(0);
		}
		if (currentString.length() < 36) {
			System.out.print(currentString + indentLength + indentLength + "\n");
		} else {
			String extra2 = currentString.substring(0, 36);
			currentString = currentString.substring(36);
			System.out.print(extra2 + indentLength + indentLength + currentString + "\n");
		}
		return;
	}

	public void printTitle(String line) {
		title = false;
		String temp = "";
		while (2 * temp.length() + line.length() < 80) {
			temp += " ";
		}
		temp = temp + line + temp;
		System.out.print(temp + "\n");
	}

	public void printSingleLine(String line) {
		spillOver = line;
		if (extra.length() > 0) {
			spillOver = extra + spillOver;
			extra = "";
		}
		if (indent) {
			spillOver = indentLength + spillOver;
			indent = false;
		} else if (block) {
			spillOver = indentLength + indentLength + spillOver;
		}
		if (spillOver.length() >= 80) {
			currentString = spillOver.substring(0, 81);
			extra = spillOver.substring(81);
		} else {
			currentString = spillOver.substring(0);
			if (textAlign == 2) {
				String temp = "";
				while (temp.length() + currentString.length() < 80) {
					temp += " ";
				}
				currentString = temp + currentString;
			} else if (textAlign == 1) {
				String temp = "";
				while (2 * temp.length() + currentString.length() < 80) {
					temp += " ";
				}
				currentString = temp + currentString + temp;
			}

		}
		System.out.print(currentString + "\n");
		return;
	}

	public String checkInitialErrors(String inputFileName, String outputFileName) {
		String errorMessage = "<html>";
		File scratch = new File(inputFileName);
		// Check to see if the output file has the .txt suffix
		if (outputFileName.indexOf(".txt", outputFileName.length()) == -1) {
			if (outputFileName.indexOf(".txt", outputFileName.length() - 4) == -1) {
				outputFileName += ".txt";
				errorMessage += "<font color=red>Error: Output file is not specified as type txt.</font><br>";
			}
			// Check to see if the input file has the .txt suffix
			if (inputFileName.indexOf(".txt", inputFileName.length()) == -1) {
				errorMessage += "Input file is not specified as type txt.\n";
			} // Check to see if the input file exists
			if (!scratch.exists()) {
				errorMessage += "A input file of this name is not found.\n";
				if (inputFileName.indexOf(".txt", inputFileName.length() - 4) == -1) {
					errorMessage += "<font color=red>Error: Input file is not specified as type txt.</font><br>";
				}
				// Check to see if the input file exists
				if (!scratch.exists()) {
					errorMessage += "<font color=red>Error: A input file of this name is not found.</font><br>";
				}
				if (inputFileName.equalsIgnoreCase(outputFileName)) {
					errorMessage += "Output file name is the same as the input file name.\n";
				} // None of the conditions above are true. Return 0, meaning all is clear

				if (inputFileName.equalsIgnoreCase(outputFileName)) {
					errorMessage += "<font color=red>Error: Output file name is the same as the input file name.</font><br>";
				}
				// None of the conditions above are true. Return "", meaning all is clear
				errorMessage += "</html>";
			}
		}

		return errorMessage;
	}

	/*
	 * Sets up the FileWriter and BufferedWriter objects for the output file
	 */
	public int createOutput() {
		int status;
		try {
			outputWriter = new BufferedWriter(new FileWriter(outputFileName));
			outputWriter.close();
			status = 1;
		} catch (IOException e) {
			e.printStackTrace();
			status = -1;
		}
		return status;

	}
}
