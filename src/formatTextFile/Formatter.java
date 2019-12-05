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

	public Formatter(String inputFileName, String outputFileName) {
		// check for initial errors
		String errors = checkInitialErrors(inputFileName, outputFileName);
		// if there are no initial errors, set inputFileName and outputFileName
		if(errors.contentEquals("<html></html>")) {
			this.inputFileName = inputFileName;
			this.outputFileName = outputFileName;
		}
		indexLength = "    "; // 4 spaces long
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
		String errorMessage = "<html>";
		File scratch = new File(inputFileName);
		// Check to see if the output file has the .txt suffix
		if(outputFileName.indexOf(".txt", outputFileName.length() - 4) == -1) {
			outputFileName += ".txt";
			errorMessage += "<font color=red>Error: Output file is not specified as type txt.</font><br>";
		}
		// Check to see if the input file has the .txt suffix
		if(inputFileName.indexOf(".txt", inputFileName.length() - 4) == -1) {
			errorMessage += "<font color=red>Error: Input file is not specified as type txt.</font><br>";
		}
		// Check to see if the input file exists
		if(!scratch.exists()) {
			errorMessage += "<font color=red>Error: A input file of this name is not found.</font><br>";
		}
		if(inputFileName.equalsIgnoreCase(outputFileName)) {
			errorMessage += "<font color=red>Error: Output file name is the same as the input file name.</font><br>";
		}
		// None of the conditions above are true. Return "", meaning all is clear
		
		errorMessage += "</html>";
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
		}
		catch (IOException e) {
			e.printStackTrace();
			status = -1;
		}
		return status;

	}
	
	
	
}
