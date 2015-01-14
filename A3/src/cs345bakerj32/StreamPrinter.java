package cs345bakerj32;
import java.util.*;
import java.io.PrintStream;

public class StreamPrinter {
	public static StringBuilder buffer;
	public static PrintStream output;
	public static int max_line_length;
	
	public StreamPrinter(PrintStream output, int max_line_length){
		this.output = output;
		this.max_line_length = max_line_length;
		this.buffer = new StringBuilder();
	}
	
	public void check_buffer(){
		if(buffer.length() >= max_line_length){
			char currentChar;
			String substr;
			int i = max_line_length;
			// Print substrings from the buffer while the buffer's length exceeds the max line length.
			while(buffer.length() >= max_line_length || buffer.toString().contains("\n")){
				// Search backwards for a space starting at the max line length.
				if(i <= buffer.length()){ 
					currentChar = buffer.charAt(i);
					if(currentChar == ' ' || currentChar == '\n'){
						substr = buffer.substring(0, i+1);
						output.println(substr);
						buffer.delete(0, i+1);
						i = max_line_length;
					}
					else{ i--; }
					// one large word. Find the end and print it.
					if(i == 0){
						i = 72;
						while(i < buffer.length()){
							currentChar = buffer.charAt(i);
							if(currentChar == ' ' || currentChar == '\n'){
								substr = buffer.substring(0, i+1);
								output.println(substr);
								buffer.delete(0, i+1);
								i = max_line_length;
								break;
							}
							else{ i++; }
						}
						if(i == buffer.length()){
							flush();
						}
					}
				}
				else{
					output.println(buffer.toString());
					buffer.delete(0, buffer.length());
				}
			}
		}
		else if(buffer.toString().contains("\n")){
			output.println(buffer.toString());
			buffer.delete(0, buffer.length());
		}
	}
	
	public void print(String s){
		this.buffer.append(s);
		check_buffer();
	}
	
	public void println(String s){
		this.buffer.append(s);
		this.buffer.append('\n');
		check_buffer();
	}
	
	public void println(){
		this.buffer.append('\n');
		check_buffer();
	}
	
	public void printf(String format, Object... args){
		print(String.format(format, args));
	}
	
	public void flush(){
		output.print(buffer.toString());
		buffer.delete(0, buffer.length());
	}
}
