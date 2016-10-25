package groupLAPD2016.util;

import java.io.File;
import java.io.IOException;

import dw317.lib.Name;

public class ListUtilitiesTest {

	public static void main(String[] args) {

		Name[] nameList = new Name[4];
		nameList[0] = new Name("Daniel", "Cavalcanti");
		nameList[1] = new Name("Ali", "Dali");
		nameList[2] = new Name("PengKim", "Sy");
		nameList[3] = new Name("Lyrene", "Labor");

		//printArray(nameList);
		
		File unsortedDir = new File("/datafiles/unsorted");
		System.out.println(unsortedDir.mkdirs());
		System.out.println(unsortedDir.getPath());

		try {
			ListUtilities.saveListToTextFile(nameList, "/datafiles/unsorted/nameDB.txt");
		} catch (IOException e) {
			System.out.println(e.getMessage() + "\n\nExiting the Application.");
			System.exit(1);
		}

	}

	private static void printArray(Object[] anArray) {
		for (int i = 0; i < anArray.length; i++) {
			if (i > 0) {
				System.out.print("; ");
			}
			System.out.print(anArray[i]);
		}
	}

}
