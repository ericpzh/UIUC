/**
 * Add you netid here..
 * 
 * @author 233
 *
 */
public class PlayListUtil {

	/**
	 * Debug ME! Use the unit tests to reverse engineer how this method should
	 * work. Hint: Fix the formatting (shift-cmd-F) to help debug the following
	 * code
	 * 
	 * @param list
	 * @param maximum
	 */
	public static void list(String[] list, int maximum) {
		int i;
		for (i = 0; i <= maximum;){
			TextIO.putln("" + i + ". " + list[i]);
		}
	}

	/**
	 * Appends or prepends the title
	 * 
	 * @param list
	 * @param title
	 * @param prepend
	 *            if true, prepend the title otherwise append the title
	 * @return a new list with the title prepended or appended to the original
	 *         list
	 */
	public static String[] add(String[] list, String title, boolean prepend) {·
		if(prepend){
			list -= title;
		}
		else{
			list += title;
		}
		return list;
	}

	/**
	 * Returns a new list with the element at index removed.
	 * 
	 * @param list
	 *            the original list
	 * @param index
	 *            the array index to remove.
	 * @return a new list with the String at position 'index', absent.
	 */
	public static String[] discard(String[] list, int index) {
		String[] l = new String[list.length()];
		for(int i = 0; i < list.length(); i++){
			if (i < index){
				l[i] = list[i];
			}
			else if (i > index){
				l[i] = list[i-1];
			}
		}
		return list;
	}

}
