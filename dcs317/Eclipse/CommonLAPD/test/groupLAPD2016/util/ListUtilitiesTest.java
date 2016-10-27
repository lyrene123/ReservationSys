package groupLAPD2016.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import dw317.lib.Email;

public class ListUtilitiesTest {

	public static void main(String[] args) {

		testSortArrays();

		
		Email[] emailList = new Email[4];
		emailList[0] = new Email("danielhbc@gmail.com");
		emailList[1] = new Email("laborlyrene@gmail.com");
		emailList[2] = new Email("alidali@gmail.com");
		emailList[3] = new Email("pengKim@gmail.com");

		File unsortedDir = new File("/datafiles/unsorted");
		unsortedDir.mkdirs();

		try {
			ListUtilities.saveListToTextFile(emailList, "/datafiles/unsorted/nameDB.txt");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

	}

	public static void testSortArrays() {
		Email[] emailList = new Email[4];
		emailList[0] = new Email("danielhbc@gmail.com");
		emailList[1] = new Email("laborlyrene@gmail.com");
		emailList[2] = new Email("alidali@gmail.com");
		
		

		Integer[] values = new Integer[] { 9, -3, 5, 0, 1 };
		Double[] numbers = new Double[] { 8.1, 0.0, -1.1, 3.2, 5.4 };
		String[] names = new String[] { "PengKim", "Ali", "Daniel", "Lyrene" };
		Boolean[] bol = new Boolean[] {true, false, true, false};
		Integer[] values2 = new Integer[] {};
		Integer[] values3 = new Integer[8];

		System.out.println("\nTesting the generic static sort method from ListUtilities Class");
		System.out.println("-----------------------------------------------------------------");
		ListUtilitiesTest.<Integer>
		testSortArrays("Case 1: A valid Integer Array with full capacity", values, true);
		ListUtilitiesTest.<String>
		testSortArrays("Case 2: A valid String Array with full capacity", names, true);
		ListUtilitiesTest.<Double>
		testSortArrays("Case 3: A valid Double Array with full capacity", numbers, true);
		ListUtilitiesTest.<Email>
		testSortArrays("Case 4: A Double Array not full to capacity", emailList, false);
		ListUtilitiesTest.<Boolean>
		testSortArrays("Case 5: A valid Boolean Array with full capacity", bol, true);
		ListUtilitiesTest.<Integer>
		testSortArrays("Case 6: Empty Array", values2, false);
		ListUtilitiesTest.<Integer>
		testSortArrays("Case 7: Empty Array", values3, false);
		
	}

	public static void testSortEmptyArray() {

		Integer[] values = new Integer[4];
		try {
			ListUtilities.<Integer>sort(values);
			printArrayObjects(values);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

	}

	public static void testSortOneElementArray() {
		Integer[] values = new Integer[] { 42 };

		ListUtilities.<Integer>sort(values);
		printArrayObjects(values);

	}

	public static <T extends Comparable<T>> void testSortArrays(String testCase, T[] obj, boolean expectedValid) {

		try {
			System.out.println("   " + testCase);
			System.out.print("\tArray Before Sorting: ");
			System.out.println(Arrays.toString(obj));
			ListUtilities.<T>sort(obj);
			System.out.print("\tArray After Sorting:  ");
			System.out.println(Arrays.toString(obj));
			if(expectedValid)
			{
				System.out.println("\tExpected Result! TEST OK");
			}else{
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print(
					"\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if(!expectedValid)
			{
				System.out.println("\tExpected Error! TEST OK");
			}else{
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

		} finally {
			System.out.println("____________________________________________________________");
		}

	}

	private static void printArrayObjects(Object[] anArrayObject) {
		for (int i = 0; i < anArrayObject.length; i++) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(anArrayObject[i]);
		}
	}
	
}
