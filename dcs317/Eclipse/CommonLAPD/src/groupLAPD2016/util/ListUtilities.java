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
	/*
	 * Sorts a list of objects in ascending natural order using * selection
	 * sort.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 * @param list A list of objects. Assumes that the list's capacity is equal
	 * to the list's size.
	 *
	 * @throws IllegalArgumentException if the parameter is * not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the list is null.
	 */

	/*
	 * public static void sort(Comparable[] list) { int min; Comparable temp;
	 * 
	 * for (int index = 0; index < list.length-1; index++) { min = index; for
	 * (int scan = index+1; scan < list.length; scan++) if
	 * (list[scan].compareTo(list[min]) < 0) min = scan;
	 * 
	 * // Swap the values temp = list[min]; list[min] = list[index]; list[index]
	 * = temp; } }
	 */
	public static <E extends Comparable<E>> void sort(E[] list) 
	{
		for (int n = 0; n < list.length; n++) 
		{
			if (list[n] == null) 
			{
				throw new NullPointerException("Error sorting list! Can't handle null element arrays");
			} else if (list[n] == "") 
			{
				throw new IllegalArgumentException("Error sorting list! Can't handle empty element arrays.");
			}
		}
		if (list.length == 0) 
		{
			throw new IllegalArgumentException("Error sorting list! Can't handle zero-length arrays.");
		}
		//Sorting Array 
		int min;
	      E temp;

	      for (int index = 0; index < list.length-1; index++)
	      {
	         min = index;
	         for (int i = index+1; i < list.length; i++)
	            if (list[i].compareTo(list[min]) < 0)
	               min = i;

	         // Swap the values
	         temp = list[min];
	         list[min] = list[index];
	         list[index] = temp;
		}
	}

}
