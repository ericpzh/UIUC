/**
 * A program to test students' CS knowledge.
 * Watch out ! There may be deliberate bugs in some of the logic below :-)
 * @see QuizMaster-ReadMe.txt for details on how to complete this program.
 * @author 233
 *
 */
public class QuizMaster {

	public static void main(String[] args) {
		
		System.out.println("Please enter your zip code.");
		int zip = TextIO.getlnInt(); // Roughly, 61xxx-Illinois 48xxx = Michigan
	
		System.out.println("Which University CS Department was recently awarded $208 million to develop the worlds fastest computer?");
		System.out.println("1. Illinois");
		System.out.println("2. Michigan");
		System.out.println("3. MIT");
		int gridComputing = TextIO.getlnInt();
		
		System.out.println("Which University CS Department designed and built the pioneering ILLIAC series?");
		System.out.print("1. Illinois\n2. Wisconsin\n3. Berkeley\n");
		int illiac = TextIO.getlnInt();
		
		TextIO.putln("Which University released 'Mosaic' - the first multimedia cross-platform browser?");
		System.out.println("(Mosaic's source code was later licensed to Microsoft and Netscape Communications)");
		System.out.println("1. Illinois");
		System.out.println("2. Michigan");
		System.out.println("3. Wisconsin");
		int mosaic = TextIO.getlnInt();
		
		System.out.println("True/False? Variables have four things: a type, name, value and a memory location.");
		System.out.println("1. True");
		System.out.println("2. False");
		int varTF = TextIO.getlnInt();
		
		int score = 0;
		/* Computer score: + 10 points for each correct answer */
		if(gridComputing==1) {score = score + 10;}
		if(illiac==1) {score = score + 10;}
		if(varTF==1) {score = score + 10;}
		if(mosaic==1){score = score +10;}
		/* Michigan students (zip code 48xxx) need to get all of them correct to receive any points */
		if(zip/1000 == 48 && score<40) {score =0;}
		
		/* If an Illinois student gets all questions correct print congratulations */
		
		System.out.print("You scored:");
		System.out.print(score);
		if(score == 40 && zip/1000== 61){
			System.out.print("\nCongratulations!");}
	}
}
