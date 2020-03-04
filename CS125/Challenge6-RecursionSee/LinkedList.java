/**
 * @author 233
 *
 */
public class LinkedList {
	// Get and Set methods are NOT necessary!

	private LinkedList next; 	
	private final String word;

	/** Constructs this link.
	 * @param word ; a single word (never null).
	 * @param next ; the next item in the chain (null, if there are no more items).
	 */
	public LinkedList(String word){
		this.word = word;
		this.next = null;
	}
	public LinkedList(String word, LinkedList next) {
		this.next = next;
		this.word = word;
		//throw new IllegalArgumentException("Not Yet Implemented");
		
	}

	/**
	 * Converts the entire linked list into a string representation.
	 */
	public String toString() {
		if (next == null)
			return word;// BASE CASE; no more recursion required

		// Recursive case:
		String restOfString = next.toString(); // Forward Recursion
		return word + ";" + restOfString;
	}

	/**
	 * Returns the number of entries in the linked list.
	 * @return number of entries.
	 */
	public int getCount() {
		if (next == null) return 1;
		return next.getCount()+1;
		 // BASE CASE; no more recursion required!
		
		// Recursive case:
		// Forward recursion
		//throw new IllegalArgumentException("Not Yet Implemented");
		
	}
	
	/** Creates a new LinkedList entry at the end of this linked list.
	 * Recursively finds the last entry then adds a new link to the end.
	 * @param word
	 */
	public void append(String word) {
		if (this == null){
			this.next = new LinkedList(word);
		}
		else if (next == null) {
			this.next= new LinkedList(word);
		}
		else next.append(word);
		//throw new IllegalArgumentException("Not Yet Implemented");
		
	}
	/**
	 * Recursively counts the total number of letters used.
	 * 
	 * @return total number of letters in the words of the linked list
	 */
	public int getLetterCount() {
		int count = 0;
		if (next == null) return count+word.length();
		return count+word.length()+next.getLetterCount();
		//throw new IllegalArgumentException("Not Yet Implemented");
		// returns the total number of letters. word1.length() +
		// word2.length()+...
		// "A" -> "CAT" -> null returns 1 + 3 = 4.
		//throw new IllegalArgumentException("Not Yet Implemented");
		
	}

	/**
	 * Recursively searches for and the returns the longest word.
	 * @return the longest word i.e. word.length() is maximal.
	 */
	public String getLongestWord() {
		if (next == null) return this.word;
		/*
		else if(word.length() >= next.getLongestWord().length()) return word;
		else if(word.length() < next.getLongestWord().length()) return next.word;
		return next.getLongestWord();
		*/
		else if (Math.max(word.length(), next.getLongestWord().length()) == word.length()) return word;
		else if (Math.max(word.length(), next.getLongestWord().length()) != word.length()) return next.getLongestWord();
		else return word;
		// recursive searches for the longest word
		//throw new IllegalArgumentException("Not Yet Implemented");
		
	}

	/** Converts linked list into a sentence (a single string representation).
	* Each word pair is separated by a space.
	* A period (".") is appended after the last word.
	* The last link represents the last word in the sentence.*/
	public String getSentence() {
		if (next == null) {return word+".";}
		return word + " " +next.getSentence();
		//throw new IllegalArgumentException("Not Yet Implemented");
		
	}
	
	/**
	 * Converts linked list into a sentence (a single string representation).
	 * Each word pair is separated by a space. A period (".") is appended after
	 * the last word. The last link represents the first word in the sentence
	 * (and vice versa). The partialResult is the partial string constructed
	 * from earlier links. This partialResult is initially an empty string. 
	 */
	public String getReversedSentence(String partialResult) {
		//throw new IllegalArgumentException("Not Yet Implemented");
		/*
		String result = getSentence();
		for (int i = 0 ; i < result.length()/2 ; i++){
			char temp = result.charAt(i);
			result.charAt(i) = result.charAt(result.length()-i-1); 
			result.charAt(result.length()-i-1) = temp;
		}*/
		if (next == null) return word+partialResult+".";
		partialResult = " " +this.word + partialResult;
		//System.out.println(partialResult);
		return next.getReversedSentence(partialResult);
		//if(next == null) return word+" " +partialResult + ".";
		//return next.getReversedSentence(partialResult);
	}
	

	/** Creates a linked list of words from an array of strings.
	 * Each string in the array is a word. */
	public static LinkedList createLinkedList(String[] words) {
		if (words.length == 0) return null;
		else if(words.length == 1) return new LinkedList(words[0],null);
		//else if (words.length == 2) return new LinkedList(words[0],new LinkedList(words[1],null));
		//else if (words.length == 3) return new LinkedList(words[0],new LinkedList(words[1],new LinkedList(words[2],null)));
		String[] nextwords = new String[words.length-1];
		for (int k = 0; k < words.length-1 ; k++){
			nextwords[k] = words[k+1];
			//System.out.println(nextwords[k]);
		}
		return new LinkedList(words[0],createLinkedList(nextwords));
		
		//int i = 0;
		//if (i = words.length-1) return null;
		//return new LinkedList(words[i],createLinkedList()
		//throw new IllegalArgumentException("Not Yet Implemented");
		// Hint: This is a wrapper method. You'll need to create your
		// own recursive method.
		// Yes this is possible _without_ loops!
	}

	/**
	 * Searches for the following word in the linked list. Hint: use .equals not ==
	 * to compare strings.
	 * 
	 * @param word
	 * @return true if the linked list contains the word (case sensivitive)
	 */
	public boolean contains(String word) {
		//throw new IllegalArgumentException("Not Yet Implemented");
		if (this.word.equals(word)) return true;
		else if (next == null) return false;
		return next.contains(word);
	}

	/** Recursively searches for the given word in the linked list.
	 * If this link matches t{
	 * he given word then return this link.
	 * Otherwise search the next link.
	 * If no matching links are found return null.
	 * @param word the word to search for.
	 * @return The link that contains the search word.
	 */
	public LinkedList find(String word) {
		//throw new IllegalArgumentException("Not Yet Implemented");
		if (this.word.equals(word)) return this;
		else if (next == null) return null;
		return next.find(word);
			
	}

	/**
	 * Returns the last most link that has the given word, or returns null if
	 * the word cannot be found.
	 * Hint: Would forward recursion be useful?
	 * @param word the word to search for.
	 * @return the last LinkedList object that represents the given word, or null if it is not found.
	 */
	public LinkedList findLast(String word) {
		//throw new IllegalArgumentException("Not Yet Implemented");
		/*
		if(next.word.equals(word)) return next.findLast(word);
		else if(this.word.equals(word)&&next == null) return this;
		else if(next == null) return null;
		return findLast(word);
		*/
		/*
		if (word.equals(word) && next == null) return this;
		else if (!word.equals(word) && next == null) return null;
		else if (word.equals(word) && next != null) return next.findLast(word);
		return next.findLast(word);
		*/
		if (this.word.equals(word) && next == null) {
			return this;
		}
		else if (!this.word.equals(word) && next == null) {
			return null;
		}
		else if (word.equals(word) && next.findLast(word) == null){
			return this;//
		}
		
		else if (!word.equals(word) && next.findLast(word) == null) {
			return next.find(word);
		}
		else return next.find(word);		
	}
	
	public LinkedList compareLast(LinkedList a,String word1) {
		if (a.word.compareTo(word1) < 0 && a.next == null){ return a;}
		else if(a.word.compareTo(word1) >= 0) return a;
		return compareLast(a.next,word1);
	}
	
	public LinkedList insert(String string) {
		if (next == null){
			if(word.compareTo(string) >= 0){
				LinkedList result = new LinkedList(string,this);
			return result;
			}
			else {
				next = new LinkedList(string,null);
				return this;
			}
		}
		else{
			if(word.compareTo(string) >= 0){
				LinkedList result = new LinkedList(string,this);
				return result;
			}
			else{
				
				LinkedList result = this.compareLast(this,string);
				//System.out.println(result.word);
				if(result.next == null) result.next = new LinkedList(string,null);
				else {result.next = new LinkedList(string,next);}
				//System.out.println(result.next.word);
				//System.out.println(string+this.word+next.word);
				
				return this;
			}
		}
		
		//throw new IllegalArgumentException("Not Yet Implemented");
	}
	public String getword(LinkedList a){ return a.word;}
	public LinkedList getlist(LinkedList a){ return a.next;}

}
