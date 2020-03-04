Winner 
------
This program asks for three unique integer scores for players 'a' 'b' and 'c'.
The program prints which player has the highest score.
The program's behavior when two players have identical scores is not defined and not tested.

Example output.
if the user enters the following scores
100 (newline) 212 (newline) 99

Then the program's output is the following:

Enter three unique integer scores.
1st Place:b

Here's one test case that  tests your program's output when player 'a' has the highest score:
	public void testA() {
		String input = "3\n2\n1\n";
		String expectedOutput = "Enter three unique integer scores.\n1st Place:a\n";
		
		CheckInputOutput.setInputCaptureOutput(input);
		Winner.main(new String[0]);
		int line = CheckInputOutput.checkCompleteOutput(expectedOutput,"testA");
		if (line > 0)
			fail("Incorrect output on line " + line);
	}

	