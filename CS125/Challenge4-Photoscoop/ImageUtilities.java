import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/** A set of general purpose image utility methods.
 * These methods encapsulate the tricky code to interact with the java virtual machine.
 * You do not need to modify this file.
 * @author angrave
 *
 */
public class ImageUtilities {
	/**
	 * Reads the given file in as an image.
	 * 
	 * @param filename
	 * @return new image or null
	 */
	public static BufferedImage loadImage(String filename) {
		try {
			TextIO.putln("Loading image " + filename);
			return ImageIO.read(Photoscoop.class.getResourceAsStream(filename));
		} catch (Exception e) {
		}
		try {
			return ImageIO.read(new FileInputStream(filename));
		} catch (Exception e) {
			TextIO.putln("Failed to load " + filename);
			e.printStackTrace();
			return null;
		}
	}

	/* attempts to save the image */
	public static void saveImage(BufferedImage img, File file)
			throws IOException {
		if (img == null)
			return;
		String format = file.getPath().toLowerCase();
		int lastIndex = format.lastIndexOf('.');
		if (lastIndex >= 0)
			format = format.substring(lastIndex + 1);
		if (format.equals("jpg"))
			format = "jpeg";
		ImageIO.write(img, format, file);
	}

	/**
	 * Sets the pixels of the image using the RGB 2D array. If the given image
	 * is null or the incorrect size a new BufferedImage is used.
	 * 
	 * @param img
	 * @param pixels
	 * @return the image with the pixels set (possibly a new image)
	 */
	public static BufferedImage setRGBPixels(BufferedImage img, int[][] pixels) {
		// assumes width >0
		if (pixels == null) {
			TextIO.putln("setRGBPixels: pixel array was null !");
			return img;
		}
		int width = pixels.length, height = pixels[0].length;
		boolean reuse = img != null && img.getWidth() == pixels.length
				&& img.getHeight() == pixels[0].length;
		BufferedImage target = reuse ? img : new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				target.setRGB(i, j, pixels[i][j]);
		return target;
	}

	/**
	 * Gets the BufferedImage as a 2D array of RGB pixel values
	 * 
	 * @param img
	 * @return
	 */
	public static int[][] getRGBPixels(BufferedImage img) {
		if (img == null)
			return null;
		int[][] result = null;
		try {
			PixelGrabber g = new PixelGrabber(img, 0, 0, -1, -1, true);
			g.grabPixels();
			int[] pixels = (int[]) g.getPixels();

			int w = g.getWidth(), h = g.getHeight();
			result = new int[w][h];

			for (int j = 0, count = 0; j < h; j++)
				for (int i = 0; i < w; i++)
					result[i][j] = pixels[count++];

			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	} // end method

	/* Sets the clipboard with a BufferedImage */
	// see http://www.exampledepot.com/egs/java.awt.datatransfer/ToClipImg.html
	public static void setClipboardImage(Image image) {
		if (image == null)
			return;
		final BufferedImage bImage = toBufferedImage(image);
		Transferable t = new Transferable() {
			// Returns supported flavors
			public DataFlavor[] getTransferDataFlavors() {
				return new DataFlavor[] { DataFlavor.imageFlavor };
			}

			// Returns true if flavor is supported
			public boolean isDataFlavorSupported(DataFlavor flavor) {
				return DataFlavor.imageFlavor.equals(flavor);
			}

			public Object getTransferData(DataFlavor flavor)
					throws UnsupportedFlavorException, IOException {
				if (!DataFlavor.imageFlavor.equals(flavor)) {
					throw new UnsupportedFlavorException(flavor);
				}
				return bImage;
			}
		}; // end inner class
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(t, null);
	}
	/* Attempts to read the system clipboard */
	
	public static BufferedImage toBufferedImage(Image src) {
		BufferedImage result = new BufferedImage(src.getWidth(null), src
				.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();
		g.drawImage(src, 0, 0, null);
		g.dispose();
		return result;
	}
	/* Returns a buffered image from the system clipboard */
	public static BufferedImage getClipboardImage() {
		Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
				.getContents(null);

		try {
			if (t != null && t.isDataFlavorSupported(DataFlavor.imageFlavor)) {
				BufferedImage img = (BufferedImage) t
						.getTransferData(DataFlavor.imageFlavor);
				return img;
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "Failed:" + e.getMessage());
		}
		return null;
	}
/* Captures the current state of the screen and creates a new screen document. */
	//see	http://schmidt.devlib.org/java/save-screenshot.html
	public static BufferedImage captureScreen() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		Rectangle screenRect = new Rectangle(screenSize);
		Robot robot;
		try {
			robot = new Robot();
			Thread.sleep(2000);
			return robot.createScreenCapture(screenRect);
		} catch (Exception e) {
			return null;
		}
	}
}