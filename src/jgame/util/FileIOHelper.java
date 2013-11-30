package jgame.util;

import java.io.InputStream;

public class FileIOHelper {
	public static InputStream loadResource(String path){
		String p = (path.startsWith("/") ? "" : "/") + path;
		return FileIOHelper.class.getResourceAsStream(p);
	}
}