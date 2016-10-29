package groupLAPD2016.util;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
		File unsortedDir = new File("datafiles/unsorted");
		unsortedDir.mkdirs();

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
	 * sort..
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

		// validating the list
		for (int n = 0; n < list.length; n++) {
			if (list[n] == null) {
				// if a null value is found, then the list is not full to
				// capacity
				throw new IllegalArgumentException("Error sorting list! The list is not full to capacity");
			}
		}
		if (list.length == 0 || list == null) {
			// if the list is null, then throw the nullpointerexception
			throw new NullPointerException("Error sorting list! Can't handle empty or null arrays.");
		}

		// the following loop will sort the list with selection sort method
		for (int i = 0; i < list.length - 1; i++) {
			// store the index of the smallest object so far
			int smallest = i;

			// this nested loop will look for the minimum value
			// in the list at a specific starting index
			for (int j = i + 1; j < list.length; j++) {
				if (list[smallest].compareTo((list[j])) > 0) {
					smallest = j;
				}
			}
			// place the smallest object found at position i
			// and the object at position i will be placed at
			// the smallest's previous position
			E temp = list[smallest];
			list[smallest] = list[i];
			list[i] = temp;
		}
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <E extends Comparable<E>> Comparable[] merge(E[] list1, E[] list2, String duplicateFileName)
			throws IllegalArgumentException, NullPointerException {

		// validating the list1
		for (int n = 0; n < list1.length; n++) {
			if (list1[n] == null) {
				// if a null value is found, then the list is not full to
				// capacity
				throw new IllegalArgumentException("Error merging list1! The list is not full to capacity");
			}
		}
		// validating the list2
		for (int n = 0; n < list2.length; n++) {
			if (list2[n] == null) {
				// if a null value is found, then the list is not full to
				// capacity
				throw new IllegalArgumentException("Error merging list2! The list is not full to capacity");
			}
		}
		if (list1.length == 0 || list1 == null) {
			// if the list is null, then throw the nullpointerexception
			throw new NullPointerException("Error merging list1! Can't handle empty or null arrays.");
		}
		if (list2.length == 0 || list2 == null) {
			// if the list is null, then throw the nullpointerexception
			throw new NullPointerException("Error sorting list2! Can't handle empty or null arrays.");
		}

		int indexL1 = 0;
		int indexL2 = 0;
		int indexL3 = 0;
		int indexL4 = 0;

		Comparable[] list3 = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);
		Comparable[] arrayDuplicates = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				list1.length + list2.length);

		while (indexL1 < list1.length && indexL2 < list2.length) {
			if (list1[indexL1].compareTo(list2[indexL2]) == 0) {
				arrayDuplicates[indexL4] = list1[indexL1];
				indexL4++;
			}
			if (list1[indexL1].compareTo(list2[indexL2]) < 0) {
				list3[indexL3] = list1[indexL1];
				indexL1++;
			} else {
				list3[indexL3] = list2[indexL2];
				indexL2++;
			}
			indexL3++;
		}
		int noCount = 0;
		for (int i = 0; i < arrayDuplicates.length; i++) {
			if (arrayDuplicates[i] == null) {
				noCount++;
			}
		}

		Comparable[] arrayDuplicatesNoNull = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(),
				arrayDuplicates.length - noCount);

		int arrayDuplicatesNoNullIndex = 0;
		for (int i = 0; i < arrayDuplicates.length; i++) {
			if (!(arrayDuplicates[i] == null)) {
				arrayDuplicatesNoNull[arrayDuplicatesNoNullIndex] = arrayDuplicates[i];
				arrayDuplicatesNoNullIndex++;
			}
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
		System.out.print("\tArray DuplicatesNoNull: ");
		System.out.println(Arrays.toString(arrayDuplicatesNoNull));

		// create Path
		StringBuilder path = new StringBuilder("datafiles/duplicates/");
		path.append(duplicateFileName);
		System.out.println(path);
		// Saving merged Object list into a file
		try {
			ListUtilities.saveListToTextFile(arrayDuplicatesNoNull, path.toString(), true);

		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

		return list3;
	}

	@SuppressWarnings({ "rawtypes" })
	public static <E extends Comparable<E>> Comparable[] removeDuplicates(E[] arrayWithDuplicates) {

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
		return arrayUnique;
	}

	/**
	 * Sorts a list of objects in the given order.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 * 
	 * @author Pengkim Sy
	 *
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * @param sortOrder
	 *            A Comparator object that defines the sort order
	 * @throws IllegalArgumentException
	 *             if the parameter is not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list or sortOrder * are null.
	 */
	public static <E extends Comparable<E>> void sort(E[] list, Comparator<E> sortOrder)
			throws IllegalArgumentException, NullPointerException {

		for (int i = 0; i < list.length; i++) {
			if (list[i] == null)
				throw new NullPointerException("Exception error! Null elements in: " + list[i]);
		}

		if (list.length == 0)
			throw new IllegalArgumentException("Exception erro in: " + list + "Can't handle empty arrays.");

		Arrays.sort(list, sortOrder);
	}

}
