package groupLAPD2016.util;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * The ListUtilities class is a non instantiable class that contains a set of
 * static utilities methods for manipulating list objects. The methods can be
 * used to save a list of objects to a text file, to sort a list of objects
 * using selection sort, a method to sort a list of objects using a given order
 * and a method to merge two sorted lists of objects in ascending natural order.
 * 
 * @author Daniel Cavalcanti and Pengkim Sy
 * @version October 2016
 */
public class ListUtilities {

	// private constant used as a default character encoding scheme
	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	/*
	 * A private no-parameter constructor. In order to prevent instantiation of
	 * the class
	 */
	private ListUtilities() {
	}

	/**
	 * The saveListToTextFile method with two parameters invokes its own
	 * overloaded four parameters method. The overloaded saveListToTextFile
	 * method saves the string representation of all elements from any array of
	 * any type of objects to a text file specified by the user. The text file
	 * is automatically saved in the relative path datafiles/unsorted.
	 * 
	 * @param objects
	 *            Array of any type of object to be saved in the text file.
	 * @param filename
	 *            String representing the name of the text file with a relative
	 *            path name that will be stored in the relative path
	 *            datafiles/unsorted.
	 * @throws FileNotFoundException
	 *             This exception will be thrown if a file with the specified
	 *             pathname does not exist. It will also be thrown if the file
	 *             does exist but for some reason is inaccessible.
	 * @throws UnsupportedEncodingException
	 *             This exception will be thrown If The Character Encoding is
	 *             not supported.
	 * 
	 * @author provided by the teacher
	 */

	public static void saveListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, false, CHARACTER_ENCODING);
	}

	/**
	 * The overloaded three parameters saveListToTextFile method saves the
	 * string representation of all elements from any array of any type of
	 * objects to a text file specified by the user. If append is set to true
	 * the list will be appended to the existing text file. If append is set to
	 * false the text file is created and list saved into the relative path
	 * datafiles/unsorted.
	 * 
	 * @param objects
	 *            Array of any type of object to be saved in the text file.
	 * 
	 * @param filename
	 *            String representing the name of the text file with a relative
	 *            path name that will be stored in the relative path
	 *            datafiles/unsorted.
	 * 
	 * @param append
	 *            Boolean indicating whether to append or overwrite an existing
	 *            file.
	 * 
	 * @throws FileNotFoundException
	 *             This exception will be thrown if a file with the specified
	 *             pathname does not exist. It will also be thrown if the file
	 *             does exist but for some reason is inaccessible.
	 * 
	 * @throws UnsupportedEncodingException
	 *             This exception will be thrown If The Character Encoding is
	 *             not supported.
	 * 
	 * @author provided by the teacher
	 */

	public static void saveListToTextFile(Object[] objects, String filename, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, filename, append, CHARACTER_ENCODING);
	}

	/**
	 * The overloaded three parameters saveDuplicateListToTextFile method saves
	 * the string representation of all elements from any array of any type of
	 * objects to a text file specified by the user and saves the file in the
	 * relative path specified by the user. If file already exist the list will
	 * be appended automatically. If the file does not exist the method creates
	 * it and saves the list to that file into the relative path.
	 * 
	 * @param objects
	 *            Array of any type of object to be saved in the text file.
	 * 
	 * @param filename
	 *            String containing the name of the text file with a relative
	 *            path name that will be stored in the relative path
	 *            datafiles/duplicates.
	 * 
	 * @param path
	 *            String containing the name of the relative path where the text
	 *            file will be saved.
	 * 
	 * @throws FileNotFoundException
	 *             This exception will be thrown if a file with the specified
	 *             pathname does not exist. It will also be thrown if the file
	 *             does exist but for some reason is inaccessible.
	 * 
	 * @throws UnsupportedEncodingException
	 *             This exception will be thrown If The Character Encoding is
	 *             not supported.
	 * 
	 * @author Daniel Cavalcanti
	 */
	public static void saveListToTextFile(Object[] objects, String filename, String path)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;
		File unsortedDir = new File(path);
		unsortedDir.mkdirs();

		try {
			FileOutputStream f = new FileOutputStream(filename, true);
			OutputStreamWriter out = new OutputStreamWriter(f, CHARACTER_ENCODING);
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

	/**
	 * The overloaded four parameters saveListToTextFile method saves the string
	 * representation of all elements from any array of any type of objects to a
	 * text file specified by the user. If append is set to true the list will
	 * be appended to the existing text file. If append is set to false the text
	 * file is created and list saved into the relative path datafiles/unsorted.
	 * 
	 * @param objects
	 *            Array of any type of object to be saved in the text file.
	 * 
	 * @param filename
	 *            String representing the name of the text file with a relative
	 *            path name that will be stored in the relative path
	 *            datafiles/unsorted.
	 * 
	 * @param append
	 *            Boolean indicating whether to append or overwrite an existing
	 *            file.
	 * 
	 * @param characterEncoding
	 *            Charset used to convert the written characters to the bytes
	 *            written to the text file.
	 * 
	 * @throws FileNotFoundException
	 *             This exception will be thrown if a file with the specified
	 *             pathname does not exist. It will also be thrown if the file
	 *             does exist but for some reason is inaccessible.
	 * 
	 * @throws UnsupportedEncodingException
	 *             This exception will be thrown If The Character Encoding is
	 *             not supported.
	 * 
	 * @author provided by the teacher
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;
		File unsortedDir = new File("datafiles" + File.separator + "unsorted");
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

	/**
	 * The saveDuplicateListToTextFile method saves the string representation of
	 * all elements from any array of any type of objects to a text file
	 * specified. If file already exist the list will be appended automatically.
	 * If the file does not exist the method creates it and saves the list to
	 * that file into the relative path datafiles/duplicates. Method created to
	 * be used only inside of this class.
	 * 
	 * @param objects
	 *            Array of any type of object to be saved in the text file.
	 * 
	 * @param filename
	 *            String containing the name of the text file with a relative
	 *            path name that will be stored in the relative path
	 *            datafiles/duplicates.
	 * 
	 * @throws FileNotFoundException
	 *             This exception will be thrown if a file with the specified
	 *             pathname does not exist. It will also be thrown if the file
	 *             does exist but for some reason is inaccessible.
	 * 
	 * @throws UnsupportedEncodingException
	 *             This exception will be thrown If The Character Encoding is
	 *             not supported.
	 * 
	 * @author Daniel Cavalcanti
	 */
	private static final void saveDuplicateListToTextFile(Object[] objects, String filename)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter duplicateOutputFile = null;
		File duplicatesDir = new File("datafiles" + File.separator + "duplicates");
		duplicatesDir.mkdirs();

		try {
			FileOutputStream duplicateFile = new FileOutputStream(filename, true);
			OutputStreamWriter out = new OutputStreamWriter(duplicateFile, CHARACTER_ENCODING);
			duplicateOutputFile = new PrintWriter(new BufferedWriter(out));

			for (int i = 0; i < objects.length; i = i + 2) {
				if (objects[i] != null) {
					duplicateOutputFile.println(objects[i] + "(merged)");
					duplicateOutputFile.println(objects[i + 1]);
				}
			}

		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error saving list! Unable to access the device " + filename);
		} finally {
			if (duplicateOutputFile != null)
				duplicateOutputFile.close();
		}
	}

	/**
	 * The sort method Sorts a list of objects in ascending natural order using
	 * selection sort.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 *
	 * @throws IllegalArgumentException
	 *             if the parameter is not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list is null.
	 * 
	 * @author Daniel Cavalcanti
	 */

	public static <E extends Comparable<E>> void sort(E[] list) throws IllegalArgumentException, NullPointerException {

		// validating array list
		validateArrayList(list);

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

	/**
	 * The overloaded two parameters sort method sorts a list of 
	 * objects in the given order.
	 *
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
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
	 *             if the list or sortOrder are null.
	 * 
	 * @author Pengkim Sy
	 */
	public static <E extends Comparable<E>> void sort(E[] list, Comparator<E> sortOrder)
			throws IllegalArgumentException, NullPointerException {

		validateArrayList(list);

		Arrays.sort(list, sortOrder);
	}

	/**
	 * The merge method Efficiently merges two sorted lists of objects in
	 * ascending natural order. The duplicates elements in both lists are
	 * written to a duplicate text file specified by the user and the file is
	 * saved into a relative path datafiles/duplicates
	 *
	 * Precondition: Assumes that the lists are not null and that both lists
	 * contain objects that can be compared to each other and are filled to
	 * capacity.
	 *
	 * @param list1
	 *            A naturally sorted list of objects. Assumes that the list
	 *            contains no duplicates and that its capacity is equal to its
	 *            size.
	 * 
	 * @param list2
	 *            A naturally sorted list of objects. Assumes that the list
	 *            contains no duplicates and that its capacity is equal to its
	 *            size.
	 * 
	 * @param duplicateFileName
	 *            The name of the file in datafiles\duplicates to which
	 *            duplicate pairs will be appended.
	 * 
	 * @return A full-to-capacity array of type Comparable containing the merged
	 *         elements of both lists without duplicates.
	 *
	 * @throws IllegalArgumentException
	 *             if either parameter is not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the either list is null.
	 * 
	 * @author Daniel Cavalcanti
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <E extends Comparable<E>> Comparable[] merge(E[] list1, E[] list2, String duplicateFileName)
			throws IllegalArgumentException, NullPointerException {

		// validating both array lists
		validateArrayList(list1);
		validateArrayList(list2);

		// Initializing indexes used inside the while loop
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
				arrayDuplicates[indexL4 + 1] = list2[indexL2];
				indexL4 = indexL4 + 2;
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
		// remove Null Elements from arrayDuplicates
		removeNullElements(arrayDuplicates);

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
		// remove duplicate elements from array list3
		list3 = removeDuplicates(list3);

		// create Path
		StringBuilder path = new StringBuilder("datafiles" + File.separator + "duplicates/");
		path.append(duplicateFileName);

		// Saving duplicate list into a file in the relative path
		// database/duplicates
		try {
			saveDuplicateListToTextFile(arrayDuplicates, path.toString());
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

		return list3;
	}

	/**
	 * The removeDuplicates method removes duplicate elements of a list of
	 * objects.Method created to be used only inside of this class.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 *
	 * @param list
	 *            A naturally sorted list of objects. Assumes that the list's
	 *            capacity is equal to the list's size.
	 * 
	 * @return A full-to-capacity array of type Comparable containing the
	 *         original array without duplicates.
	 *
	 * @throws IllegalArgumentException
	 *             if the list is not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list is null.
	 * 
	 * @author Daniel Cavalcanti
	 */
	@SuppressWarnings({ "rawtypes" })
	private static final <E extends Comparable<E>> Comparable[] removeDuplicates(E[] arrayWithDuplicates) {

		// validating array list
		validateArrayList(arrayWithDuplicates);

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
	 * The removeNullElements method removes null elements of an array resizing
	 * the original array if necessary.Method created to be used only inside of
	 * this class.
	 *
	 * @param arrayDuplicates
	 *            A naturally sorted list of objects with possible null
	 *            elements. Assumes that the list's capacity is equal to the
	 *            list's size.
	 *
	 * @throws NullPointerException
	 *             This exception is thrown if the list is empty.
	 * 
	 * @author Daniel Cavalcanti
	 */
	private static final <E extends Comparable<E>> void removeNullElements(E[] arrayDuplicates)
			throws NullPointerException {

		if (arrayDuplicates.length == 0) {
			// if the list is null, then throw the nullpointerexception
			throw new NullPointerException("Error found! Can't handle empty arrays.");
		}
		int noCount = 0;
		for (int i = 0; i < arrayDuplicates.length; i++) {
			if (arrayDuplicates[i] == null) {
				noCount++;
			}
		}

		@SuppressWarnings("rawtypes")
		Comparable[] arrayDuplicatesNoNull = (Comparable[]) Array
				.newInstance(arrayDuplicates.getClass().getComponentType(), arrayDuplicates.length - noCount);

		int arrayDuplicatesNoNullIndex = 0;
		for (int i = 0; i < arrayDuplicates.length; i++) {
			if (!(arrayDuplicates[i] == null)) {
				arrayDuplicatesNoNull[arrayDuplicatesNoNullIndex] = arrayDuplicates[i];
				arrayDuplicatesNoNullIndex++;
			}
		}

	}

	/**
	 * The validateArrayList method checks if an array list is not full to
	 * capacity or empty. Method created to be used only inside of this class.
	 *
	 * @param arrayDuplicates
	 *            A naturally sorted list of objects with possible null
	 *            elements. Assumes that the list's capacity is equal to the
	 *            list's size.
	 *
	 * @throws IllegalArgumentException
	 *             if the list is not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list is empty.
	 * 
	 * @author Daniel Cavalcanti
	 */
	private static final <E extends Comparable<E>> void validateArrayList(E[] list) {

		// validating the list
		for (int n = 0; n < list.length; n++) {
			if (list[n] == null) {
				// if a null value is found, then the list is not full to
				// capacity
				throw new IllegalArgumentException("Error found! The array list is not full to capacity");
			}
		}
		if (list.length == 0 || list == null) {
			// if the list is null, then throw the nullpointerexception
			throw new NullPointerException("Error found! Can't handle empty or null arrays.");
		}
	}
	
	/**
	 * findLargestElementsIn2dArrayList method compares the size of the list in ArrayList
	 * base on their size. It returns a list with the biggest size.
	 * 
	 * @param list - 2 dimentional array list
	 * @return List<E> - the list with the biggest size
	 */
	public static <E> List<E> findLargestElementsIn2dArrayList(ArrayList<List<E>> list){		
		List<E> max = list.get(0);
		
		for(int i=0; i<list.size(); i++){
			if(list.get(i).size() > max.size())
				max = list.get(i);
		}
		return max;		
	}
}
