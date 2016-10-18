package reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class Reader {
	
	protected FileReader reader;
	
	protected BufferedReader buffer;
	
	protected String filename;
	
	public Reader(String filename){
		this.filename = filename;		
	}
	
	/**
	 * Open file for read it
	 * 
	 * @throws FileNotFoundException 
	 */
	public void open() throws FileNotFoundException {
		this.reader = new FileReader(filename);
		this.buffer = new BufferedReader(reader);		
	}

	/**
	 * Close file after read it
	 * 
	 * @throws IOException 
	 */
	public void close() throws IOException {
		if (buffer != null) {
			buffer.close();
		}
		if (reader != null) {
			reader.close();
		}		
	}
		
	/**
	 * Read a line
	 * 
	 * @return A line read	 * 
	 * @throws IOException 
	 */
	public String readLine() throws IOException{
		return buffer.readLine();
	}
}
