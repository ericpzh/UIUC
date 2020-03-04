public class PlayListApplication {
	public static void main(String[] args) {
		String[] data = new String[0];
		boolean keepGoing = true;
		while (keepGoing) {
			TextIO.putln("List has " + data.length + " item(s).");
			TextIO.putln("Enter [Q]uit [L]ist [A]ppend [P]repend [D]iscard [R]eset");
			char option = TextIO.getlnChar();
			boolean isPrepend = option == 'P';
			if (option == 'Q')
				keepGoing = false;
			else if (option == 'L') {
				TextIO.putln("How many to print? (-1 for all)");
				int max = TextIO.getlnInt();
				PlayListUtil.list(data, max);
			} else if (option == 'A' || isPrepend) {
				TextIO.putln("Name?");
				String name = TextIO.getln();
				data = PlayListUtil.add(data, name, isPrepend /* D.R.Y. */);
				PlayListUtil.list(data, -1);
			} else if (option == 'D') {
				PlayListUtil.list(data, -1);
				TextIO.putln("Which entry to discard?");
				int index = TextIO.getlnInt()-1;
				TextIO.putln("Discarding #"+(index+1));
				data = PlayListUtil.discard(data,index);
			} else if (option == 'R') {
				data = new String[0];
			} else
				TextIO.putln("Invalid Option:" + option);
		} // loop
		TextIO.putln("Bye");
	}
}
