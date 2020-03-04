import java.io.File;
import java.util.Scanner;

/**
 * 
 * @author angrave
 *
 */
public class ExampleFileSearch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File f = new File(".");
		search(f);

	}

	private static void search(File directory) {
		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++) {
			File f = files[i];
// To recursively search all sub-directories			
//			if (f.isDirectory())
//				search(f);
			 if (f.isFile())
				printContents(f);
		}
	}

	private static void printContents(File f) {

		try {
			String name = f.getName();
			if (!name.contains(".java") || name.contains("Zen") || name.contains("TextIO") || name.contains("AutomaticGrader"))
				return;
			System.out.println(f.getCanonicalPath());
			Scanner s = new Scanner(f);
			while (s.hasNext()) {
				String line = s.nextLine();
				if (line.trim().indexOf("public") ==0)
					System.out.println(line);
			}
			System.out.println("-----");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
