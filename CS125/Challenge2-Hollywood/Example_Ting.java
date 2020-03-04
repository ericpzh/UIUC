public class Example_Ting {

// Here's one version of the 'ting' lecture problem.

	public static void main(String[] args) {
		TextIO.putln("Enter a word that includes the substring 'ting'");
		String blahdeblah = TextIO.getln();
		TextIO.put("You entered:");
		TextIO.putln(blahdeblah);
		TextIO.put("Found 'ting' at position " + (1 + blahdeblah.indexOf("ting")));
	}

}
