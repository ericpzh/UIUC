/**
 * A program to search for specific lines and print their line number.
 * See FindScriptLine.txt for more details
 * TODO: add your netid to the line below
 * @author 2333
 */
public class FindScriptLine {

	public static void main(String[] args) {
		System.out.println("Please enter the word(s) to search for");
		String a = TextIO.getln();
		String b = a.toLowerCase();
		String c = a.toUpperCase();
		System.out.println("Searching for '" + a + "'");
		TextIO.readFile("thematrix.txt");
		String s;
		int k = 0;
		boolean output = false;
		while ( TextIO.eof()== false) {
			s = TextIO.getln();
			k++;
			output = false;// Read the next line
			if(s == ""){
				output = false;//TODO: If it's a blank line set 'output' to false			
			}
			//TODO: Correct the output format (see ScriptPrinter.txt example output)
			//TODO: Re-order the three if statements so the output is correct

			else if (s.indexOf(a) >= 0 || s.indexOf(b) >= 0 || s.indexOf(c) >= 0){
				output = true; // We found the character's name, time to start printing their lines
			}
			if (output == true){
				while(s.charAt(0) == ' ' || s.charAt(0) == '	'){
					s = s.substring(1,s.length());
				}
				if (s.charAt(s.length()-1) == ' '){
					s = s.substring(0,s.length()-1);
				}

				
				System.out.println(k + " - " +s); 
			}// Only print the line if 'output' is true

		}
		
		System.out.println("Done Searching for '"+ a +"'");
// TODO: Implement the functionality described in FindScriptLine.txt
// TODO: Test your code manually and using the automated unit tests in FindScriptLineTest		
	}
}