Factorial
---------

The factorial program asks for an integer number between 1 and 20.
If the number is outside of this range it repeats the question.
(For this assignment, do not worry about illegal input such as text or real numbers).

Example output: (the keyboard input is shown in []'s)

Enter a number between 1 and 20 inclusive. [Enter 21 and press return]
Enter a number between 1 and 20 inclusive. [Enter 5 and press return]
5! = 120

Note, if you have not read R-E-A-D-M-E--F-I-R-S-T.txt then you should do so now.

Open FactorialTest.java to read each test.
For example 'test1' mimics a user typing '1' followed by a newline (i.e. pressing return):
	String input = "1\n";
	String requiredOutput = 
		"Enter a number between 1 and 20 inclusive.\n" +
		"1! = 1\n";

The subsequent code checks that your program output is exactly the string shown above. ie.
Enter a number between 1 and 20 inclusive.
1! = 1

Compiling your program
If there is a compile error, you will find an entry in the Problems view and also red marks in the file editing view to indicate where the error is.
When you save the file (CTRL-S = Windows , Command-S = Mac) your file will be compiled again.

Testing your program and understanding the console output

Either 1) Right click on Factorial.java in the package view and choose 'Run As > Java Application'.
You can then enter text by opening or clicking on the consolve view and typing in some text for your program to process.
Note that you need to enter a complete line and press return before it is sent to your program.

Or 2) Run the automated test cases by right-clicking on 'FactorialTest.java' and selecting 'Run As > JUnit Test'

The JUnit window will open and you can see which tests pass and which fail. The Console window will also give details
about why your program's output is incorrect:

test1: Expected 2 lines. Actual 2 lines.
1. PASS > 'Enter a number between 1 and 20 inclusive.'
2. FAIL > '1' Should be - 
2.        '1! = 1'
test1: Program output above was incorrect on output line 2

The above output tells you that it was using 'test1' (look for a method inside FactorialTest.java named 'test1').
if you review the code in test1, you will see that the test input to your program is just "1\n"
i.e. the user types 1, presses return and that's all.
Your program produced 2 lines of output, as expected.
The first line of output passed; it was correct.
The second line of output did not meet the output requirements. The program printed just 1; it should have printed '1! = 1'

The console output can be verbose if you run all of the tests and difficult to wade through.
You can run just one test by right-clicking on the test's in the JUnit window.
e.g. test1

To re-run the program or re-run the last test(s) click on the green circle 'Play' button in the toolbar.
For the brave... Sometimes it is necessary to watch a program executing one program line at a time.
Eclipse has an inbuilt debugger to help you do this - click on the green bug symbol. You can then step through your code; ask a course assistant for more details.
