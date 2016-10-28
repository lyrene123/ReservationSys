package groupLAPD2016.util;

import java.util.Arrays;
import dw317.lib.Email;

public class ListUtilitiesTest {

	public static void main(String[] args) {

		//testSort();
		testMerge();

		Email[] emailList = new Email[3];
		emailList[0] = new Email("danielhbc@gmail.com");
		emailList[1] = new Email("laborlyrene@gmail.com");
		emailList[2] = new Email("alidali@gmail.com");
		
				
		/*String[] nameList = new String[] { "PengKim", "Ali", "Daniel", "Lyrene" };
		File unsortedDir = new File("/datafiles/unsorted");
		unsortedDir.mkdirs();
		*/
		/*StringBuilder path = new StringBuilder("datafiles/duplicates/");
		path.append("duplicateTest.txt");
		System.out.println(path);
		
		try {
			ListUtilities.saveListToTextFile(emailList,
					path.toString(), true);
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}
		*/
		/*
		 * try { ListUtilities.saveListToTextFile (nameList,
		 * "D:/LAPD2016/dcs317/Eclipse/ReservationSys/datafiles/duplicates.txt")
		 * ; } catch (IOException e) { System.out.println(e.getMessage() +
		 * "\n\nExiting the Application."); System.exit(1); }
		 */
	}

	public static void testSort() {
		Email[] emailList = new Email[4];
		emailList[0] = new Email("danielhbc@gmail.com");
		emailList[1] = new Email("laborlyrene@gmail.com");
		emailList[2] = new Email("alidali@gmail.com");

		Integer[] values = new Integer[] { 9, -3, 5, 0, 1 };
		Double[] numbers = new Double[] { 8.1, 0.0, -1.1, 3.2, 5.4 };
		String[] names = new String[] { "PengKim", "Ali", "Daniel", "Lyrene" };
		Boolean[] bol = new Boolean[] { true, false, true, false };
		Integer[] values2 = new Integer[] {};
		Integer[] values3 = new Integer[8];

		System.out.println("\nTesting the generic static sort method from ListUtilities Class");
		System.out.println("-----------------------------------------------------------------");
		ListUtilitiesTest.<Integer>
		testSort("Case 1: A valid Integer Array with full capacity", values, true);
		ListUtilitiesTest.<String>
		testSort("Case 2: A valid String Array with full capacity", names, true);
		ListUtilitiesTest.<Double>
		testSort("Case 3: A valid Double Array with full capacity", numbers, true);
		ListUtilitiesTest.<Email>
		testSort("Case 4: A Double Array not full to capacity", emailList, false);
		ListUtilitiesTest.<Boolean>
		testSort("Case 5: A valid Boolean Array with full capacity", bol, true);
		ListUtilitiesTest.<Integer>
		testSort("Case 6: Empty Array", values2, false);
		ListUtilitiesTest.<Integer>
		testSort("Case 7: Empty Array", values3, false);

	}
	public static void testMerge(){
		
		String[] nameList2 = new String[] { "Ali", "Carlos", "Frank", "Lyrene" };
		String[] nameList1 = new String[] { "Ali", "Frank","Lyrene"};
		String[] nameList3 = new String[] { "j@doe.com*John*Doe*visa*4556737586899855"};
		String[] nameList4 = new String[] { "j@doe.com*John*Doe**"};
		

		
			
		ListUtilitiesTest.<String>
		testMerge("Case 1: Merging", nameList1, nameList2, "duplicateNames.txt", true);
		
	}

	public static <T extends Comparable<T>> void testMerge(String testCase, T[] list1, T[] list2,
			String duplicateFileName, boolean expectedValid) {

		try {
			System.out.println("   " + testCase);
			System.out.print("\tArray List1: ");
			System.out.println(Arrays.toString(list1));
			System.out.print("\tArray List2: ");
			System.out.println(Arrays.toString(list2));
			ListUtilities.<T>merge(list1, list2, duplicateFileName);

			if (expectedValid) {
				System.out.println("\tExpected Result! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if (!expectedValid) {
				System.out.println("\tExpected Error! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

		} finally {
			System.out.println("____________________________________________________________");
		}

	}

	public static <T extends Comparable<T>> void testSort(String testCase, T[] obj, boolean expectedValid) {

		try {
			System.out.println("   " + testCase);
			System.out.print("\tArray Before Sorting: ");
			System.out.println(Arrays.toString(obj));
			ListUtilities.<T>sort(obj);
			System.out.print("\tArray After Sorting:  ");
			System.out.println(Arrays.toString(obj));
			if (expectedValid) {
				System.out.println("\tExpected Result! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if (!expectedValid) {
				System.out.println("\tExpected Error! TEST OK");
			} else {
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
