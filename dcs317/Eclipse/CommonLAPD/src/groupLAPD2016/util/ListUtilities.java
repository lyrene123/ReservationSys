package groupLAPD2016.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class ListUtilities {
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	/*
	 * A private no-parameter constructor. In order to prevent instantiation of
	 * the class
	 */
	private ListUtilities() {
	}

	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;
		
		try {
			FileOutputStream f = new FileOutputStream(filename, append);
			OutputStreamWriter out = new OutputStreamWriter(f, characterEncoding);
			outputFile = new PrintWriter(new BufferedWriter(out));

			for (Object obj : objects)
				if (obj != null)
					outputFile.println(obj);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error saving list! Unable to access the device " + filename);
		} finally {
			if (outputFile != null)
				outputFile.close();
		}
	}
}
