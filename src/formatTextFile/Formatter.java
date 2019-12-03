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
