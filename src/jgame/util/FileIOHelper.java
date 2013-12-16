package jgame.util;

/**
 * Class used to make loading files easier.
 * Uses InputStream instead of File to allow usage in Applets and other OS's.
 * @author Kareem El-Faramawi
 */
import java.io.InputStream;

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
}