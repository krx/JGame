package jgame.util;

/**
 * Class used to make loading files easier.
 * Uses InputStream instead of File to allow usage in Applets and other OS's.
 * @author Kareem El-Faramawi
 */
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class FileIOHelper {
	
	/**
	 * Loads a file as an InputStream. Fixes invalid path.
	 * @param path Path to the file
	 * @return InputStream to the loaded file. Returns null if no file is found.
	 */
	public static InputStream loadResource(String path) {
		String p = (path.startsWith("/") ? "" : "/") + path;
		return FileIOHelper.class.getResourceAsStream(p);
	}
	
	public static BufferedImage loadImage(String path) {
		try {
			return toCompatibleImage(ImageIO.read(loadResource(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage toCompatibleImage(BufferedImage image) {
		// Get the current system graphics settings
		GraphicsConfiguration gfx_config = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		
		// If image is already compatible, return it
		if(image.getColorModel().equals(gfx_config.getColorModel())) {
			return image;
		}
		
		// Image is not optimized, so create a new one that is
		BufferedImage new_image = gfx_config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
		
		// Get the graphics context of the new image to draw the old image on
		Graphics2D g2d = (Graphics2D) new_image.getGraphics();
		
		// Draw old image and dispose of context
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
		
		// Return newly optimized image
		return new_image;
	}
}