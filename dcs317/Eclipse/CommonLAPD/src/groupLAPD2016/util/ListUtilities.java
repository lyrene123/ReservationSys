package groupLAPD2016.util;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

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

	public static <E extends Comparable<E>> void sort(E[] list) throws IllegalArgumentException, NullPointerException {

		for (int n = 0; n < list.length; n++) {
			if (list[n] == null) {
				throw new NullPointerException("Error sorting list! Can't handle null element arrays");
			}
		}
		if (list.length == 0) {
			throw new IllegalArgumentException("Error sorting list! Can't handle empty arrays.");
		}
		// Sorting Array
		Arrays.sort(list);
	}

	/*
	 * Efficiently merges two sorted lists of objects in ascending natural
	 * order. If the duplicate objects are in both lists, the object from list1
	 * is merged into the resulting list, and both objects are written to the
	 * duplicate file.
	 *
	 * Precondition: Assumes that the lists are not null and that both lists
	 * contain objects that can be compared to each other and are filled to
	 * capacity.
	 *
	 * @param list1 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param list2 A naturally sorted list of objects. Assumes that the list
	 * contains no duplicates and that its capacity is equal to its size.
	 * 
	 * @param duplicateFileName The name of the file in datafiles\duplicates to
	 * which duplicate pairs will be appended.
	 *
	 * @throws IllegalArgumentException if either parameter is not full to
	 * capacity.
	 *
	 * @throws NullPointerException if the either list is null.
	 */
	@SuppressWarnings("unchecked")
	public static <E extends Comparable<E>> E[] merge(E[] list1, E[] list2, String duplicateFileName)
			throws IllegalArgumentException, NullPointerException {

		for (int n = 0; n < list1.length; n++) {
			if (list1[n] == null) {
				throw new NullPointerException("Exception error! Null elements in: " + list1);
			}
		}
		for (int n = 0; n < list2.length; n++) {
			if (list2[n] == null) {
				throw new NullPointerException("Exception error! Null elements in: " + list1);
			}
		}

		if (list1.length == 0) {
			throw new IllegalArgumentException("Exception erro in: " + list1 + "Can't handle empty arrays.");
		}
		if (list2.length == 0) {
			throw new IllegalArgumentException("Exception erro in: " + list2 + "Can't handle empty arrays.");
		}

		int indexL1 = 0;
		int indexL2 = 0;
		int indexL3 = 0;

		@SuppressWarnings("rawtypes")
		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);
		int x = list1[indexL1].compareTo(list2[indexL2]);
		System.out.println(x);
		while (indexL1 < list1.length && indexL2 < list2.length) {
			if (list1[indexL1].compareTo(list2[indexL2]) < 0) {
				list3[indexL3] = list1[indexL1];
				indexL1++;
			} else {
				list3[indexL3] = list2[indexL2];
				indexL2++;
			}
			indexL3++;
		}
		if (indexL1 < list1.length) {
			for (int indexR = indexL1; indexR < list1.length; indexR++) {
				list3[indexL3] = list1[indexR];
				indexL3++;
			}
		} else {
			for (int indexR = indexL2; indexR < list2.length; indexR++) {
				list3[indexL3] = list2[indexR];
				indexL3++;
			}
		}
		list3 = removeDuplicates(list3);

		System.out.print("\tArray List3: ");
		System.out.println(Arrays.toString(list3));
		
		// create File object
		StringBuilder path = new StringBuilder("datafiles/duplicates/");
		path.append(duplicateFileName);
		System.out.println(path);
		// Saving merged Object list into a file
		try {
			ListUtilities.saveListToTextFile(list3, path.toString(), true);
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

		return (E[]) list3;
	}

	@SuppressWarnings("unchecked")
	public static <E extends Comparable<E>> E[] removeDuplicates(E[] arrayWithDuplicates) {

		@SuppressWarnings("rawtypes")
		Comparable[] arrayUnique = (Comparable[]) Array.newInstance(arrayWithDuplicates.getClass().getComponentType(),
				arrayWithDuplicates.length);
		int j = 0;
		int i = 1;
		// return if the array length is less than 2
		if (arrayWithDuplicates.length < 2) {
			return arrayWithDuplicates;
		}
		while (i < arrayWithDuplicates.length) {
			if (arrayWithDuplicates[i] == arrayWithDuplicates[j]) {
				i++;
			} else {
				arrayWithDuplicates[++j] = arrayWithDuplicates[i++];
			}
		}
		arrayUnique = new Comparable[j + 1];

		for (int indexU = 0; indexU < arrayUnique.length; indexU++) {
			arrayUnique[indexU] = arrayWithDuplicates[indexU];
		}
		return (E[]) arrayUnique;
	}

}
