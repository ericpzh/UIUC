//**/**
 //* A program to print one actor's lines. 
 //* See ScriptPrinter.txt for more information.
 //* TODO: add your netid to the line below
 //* @author 2333
 //*/
public class MyScriptPrinter {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		boolean output=false; //Set to true when we find the desired character
		String name=""; // Only print lines for this character
		
		TextIO.putln("Which character's lines would you like? (NEO,MORPHEUS,ORACLE)");
		name = TextIO.getln();
		name = name.toUpperCase();

		TextIO.readFile("thematrix.txt"); // stop reading from the keyboard- use the script

		System.out.println(name.toUpperCase() + "'s lines:");//TODO: Print the name here (see ScriptPrinter.txt example output for format)
		// initially don't print anything
		String line;
		// This loop will read one line at a time from the script until it
		// reaches the end of the file and then TextIO.eof() will return true.
		// eof means end-of-file
		while ( TextIO.eof()== false) {
			line = TextIO.getln();
			output = false;// Read the next line
			if(line == ""){
				output = false;//TODO: If it's a blank line set 'output' to false			
			}
			//TODO: Correct the output format (see ScriptPrinter.txt example output)
			//TODO: Re-order the three if statements so the output is correct

			else if (line.indexOf(name) >= 0){
				output = true; // We found the character's name, time to start printing their lines
			}
			if (output == true){
				line = TextIO.getln();
				try{
				while(line.charAt(0) == ' ' || line.charAt(0) == '	'){
					line = line.substring(1,line.length());
				
				}
				}
				catch (StringIndexOutOfBoundsException e){}
				System.out.println(name.toUpperCase() + ":\"" + line + "\""); 
			}// Only print the line if 'output' is true

		}
		System.out.println("---");//TODO: Print 3 dashes here to indicate processing is complete
	}

}
