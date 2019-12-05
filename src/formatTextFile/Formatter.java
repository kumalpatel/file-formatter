package formatTextFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Formatter {
	protected int total;
	protected String currentLine, spillOver, inputFileName, outputFileName, indexLength;
	protected BufferedReader reader;
	protected BufferedWriter outputWriter;
	protected FileWriter outputFile;

	public Formatter(String inputFileName, String outputFileName) {
		// check for initial errors
		String errors = checkInitialErrors(inputFileName, outputFileName);
		// if there are no initial errors, set inputFileName and outputFileName
		if(errors == "") {
			this.inputFileName = inputFileName;
			this.outputFileName = outputFileName;
		}
		indexLength = "     "; // 4 spaces long
	}

	
	public void readFile() {
		try {
			reader = new BufferedReader(new FileReader(inputFileName));
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
	
	/*
	 * @description: This method checks to see whether or not the associated input and output files
	 * exist, if they're the same, and if they're of .txt.
	 * @return: An integer describing the type of error that has occurred.
	 */
	public String checkInitialErrors(String inputFileName, String outputFileName) {
		String errorMessage = "";
		File scratch = new File(inputFileName);
		// Check to see if the output file has the .txt suffix
		if(outputFileName.indexOf(".txt", outputFileName.length()) == -1) {
			outputFileName += ".txt";
			errorMessage += "Output file is not specified as type txt.\n";
		}
		// Check to see if the input file has the .txt suffix
		if(inputFileName.indexOf(".txt", inputFileName.length()) == -1) {
			errorMessage += "Input file is not specified as type txt.\n";
		}
		// Check to see if the input file exists
		if(!scratch.exists()) {
			errorMessage += "A input file of this name is not found.\n";
		}
		if(inputFileName.equalsIgnoreCase(outputFileName)) {
			errorMessage += "Output file name is the same as the input file name.\n";
		}
		// None of the conditions above are true. Return "", meaning all is clear
		
		return errorMessage;
		
	}
	
	/*
	 * Sets up the FileWriter and BufferedWriter objects for the output file
	 */
	public void createOutput() {
		try {
			outputFile = new FileWriter(outputFileName);
			BufferedWriter outputWriter = new BufferedWriter(outputFile);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
