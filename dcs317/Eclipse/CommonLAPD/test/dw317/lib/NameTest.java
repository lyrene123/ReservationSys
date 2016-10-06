/**
 * 
 */
package dw317.lib;

/**
 * Test class for the name class
 * @author Lyrene Labor And Ali Dali
 *
 */
public class NameTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTheConstructor();
		testGetFirstName();
		testGetLastName();
		testSetFirstName();
		testSetLastName();
		testGetFullName();
		testHashCode();
		testEquals();
	}

	public static void testTheConstructor(){
		System.out.println("\nTesting the constructor.");
		testTheConstructor("Case 1 - valid data (Lyrene Labor)", "Lyrene",
				"Labor", true);
		testTheConstructor("Case 2 - invalid data (empty Labor)", "",
				"Labor", false);
		testTheConstructor("Case 3 - invalid data (Lyrene empty)", "Lyrene",
				"", false);
		testTheConstructor("Case 4 - invalid data (Lyrene null)", "Lyrene",
				null, false);
		testTheConstructor("Case 5 - invalid data (Lyrene spaces)", "Lyrene",
				"           ", false);
		testTheConstructor("Case 6 - invalid data (L Labor)", "L",
				"Labor", false);
		testTheConstructor("Case 7 - invalid data (Lyrene L)", "Lyrene",
				"L", false);
		testTheConstructor("Case 8 - valid data (Lyrene La-bor)", "Lyrene",
				"La-bor", true);
		testTheConstructor("Case 9 - valid data (Lyre'ne Labor)", "Lyre'ne",
				"Labor", true);
		testTheConstructor("Case 10 - valid data (Lyre ne Labor)", "Lyre ne",
				"Labor", true);
		testTheConstructor("Case 11 - invalid data (Lyre!ne Labor)", "Lyre!ne",
				"Labor", false);
		testTheConstructor("Case 12 - invalid data (Lyrene La?bor)", "Lyre!ne",
				"La?bor", false);
		testTheConstructor("Case 13 - invalid data (Lyrene La.bor)", "Lyre!ne",
				"La.bor", false);
		testTheConstructor("Case 14 - invalid data (Lyrene La     bor)", "Lyrene",
				"La     bor", false);
		testTheConstructor("Case 15 - invalid data (Lyrene La--bor)", "Lyrene",
				"La--bor", false);
		testTheConstructor("Case 15 - invalid data (Lyrene La''bor)", "Lyrene",
				"La''bor", false);

	}

	public static void testTheConstructor(String testCase, String firstName,
			String lastName, boolean expectValid){
		System.out.println("   " + testCase);
		try{
			Name myName = new Name(firstName, lastName);
			System.out.println("\tThe Name instance has been created: " + myName);

			if(!expectValid){
				System.out.print("  Error! Expected Invalid. "
						+ "==== FAILED TEST ====");
			}
		}
		catch(IllegalArgumentException e){
			System.out.print("\t"+ e.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch(Exception x){
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + x.getClass() +  " "  + 				
					x.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}
		System.out.println("\n");
	}

	public static void testGetFirstName(){
		System.out.println("\nTesting the getFirstName method.");
		testGetFirstName("Case 1: Lyrene without leading/trailing spaces",
				"Lyrene","Lyrene");
	}

	public static void testGetFirstName(String testCase, 
			String firstName, String expectedFirstName){

		System.out.println("   " + testCase);
		Name myName = new Name(firstName, "Labor");
		System.out.print("\tThe Name instance was created: " + myName);

		if (!myName.getFirstName().equals(expectedFirstName))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
	}

	public static void testGetLastName(){
		System.out.println("\nTesting the getLastName method.");
		testGetFirstName("Case 1: Labor without leading/trailing spaces",
				"Labor","Labor");
	}

	public static void testGetLastName(String testCase, 
			String lastName, String expectedLastName){
		System.out.println("   " + testCase);
		Name myName = new Name("Lyrene", lastName);
		System.out.print("\tThe Name instance was created: " + myName);

		if (!myName.getLastName().equals(expectedLastName))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		System.out.println("\n");
	}

	public static void testSetFirstName(){
		System.out.println("\nTesting the setFirstName method.");
		testSetFirstName("Case 1: Valid - Lyrene without leading/trailing spaces",
				"Lyrene","Lyrene",true);
		testSetFirstName("Case 2: Valid - Lyrene + spaces",
				"    Lyrene","Lyrene",true);
		testSetFirstName("Case 3: inValid - empty",
				"","Lyrene",false);
		testSetFirstName("Case 4: inValid - null",
				null,"Lyrene",false);
	}

	public static void testSetFirstName(String testCase, 
			String firstName, String expectedFirstName,boolean expectValid){
		System.out.println("   " + testCase);
		Name myName = new Name("Lyrene", "Labor");
		try {
			myName.setFirstName(firstName);
			System.out.print("\tThe Name instance was created: " + myName);

			if (!myName.getFirstName().equals(expectedFirstName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException e) {
			System.out.print("\t"+ e.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception x) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + x.getClass() + " " +
					x.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

	public static void testSetLastName(){
		System.out.println("\nTesting the setLastName method.");
		testSetLastName("Case 1: Valid - Labor without leading/trailing spaces",
				"Labor","Labor",true);
		testSetLastName("Case 2: Valid - Labor + spaces",
				"    Labor","Labor",true);
		testSetLastName("Case 3: inValid - empty",
				"","Labor",false);
		testSetLastName("Case 4: inValid - null",
				null,"Labor",false);
	}

	public static void testSetLastName(String testCase, 
			String lastName, String expectedLastName,boolean expectValid){

		System.out.println("   " + testCase);
		Name myName = new Name("Lyrene", "Labor");
		try {
			myName.setLastName(lastName);
			System.out.print("\tThe Name instance was created: " + myName);

			if (!myName.getLastName().equals(expectedLastName))
				System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}
		catch (IllegalArgumentException e) {
			System.out.print("\t"+ e.getMessage());
			if (expectValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception x) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + x.getClass() + " " +
					x.getMessage() + " ==== FAILED TEST ====");
			if (expectValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}


	public static void testGetFullName(){
		System.out.println("\nTesting the getFullName method.");
		testGetFullName("Case 1: Valid input", "Ali","Dali","Ali Dali");
	}


	public static void testGetFullName(String testCase,
			String firstName, String lastName, String expectedResult){

		System.out.println("   " + testCase);
		Name myName = new Name(firstName, lastName);

		if(!myName.getFullName().equalsIgnoreCase(expectedResult)){
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");
		}else
			System.out.println("        " + "Returned: " + myName.getFullName() +" ----> " + "Exepected: "+ expectedResult);
		System.out.println("\n");
	}

	public static void testHashCode(){
		System.out.println("\nTesting the HashCode method.");
		Name myName = new Name("Ali", "Dali");
		Name otherName = new Name("Lyrene", "Labor");
		Name myName2 = myName;
		
		testHashCode("Case 1: Both Names have different addresses", myName, otherName,"Different Numbers");
		testHashCode("Case 2: Both Names are the same object, Same addresses",myName, myName2,"Same Numbers");
	}

	public static void testHashCode(String testCase, Name myName, Name otherName, String expectedResult){
		System.out.println("   " + testCase);

		System.out.print("	"+"Returned result 1rst Name:  " + myName.hashCode() + "	");
		System.out.println("	"+"Returned result 2nd Name:  " + otherName.hashCode());
		System.out.println("	"+"expected result: " + expectedResult);
		System.out.println();
	}
	
	public static void testEquals(){
		System.out.println("\nTesting the equals method.");

		Name myName = new Name("Ali", "Dali");
		Name otherName = new Name("Lyrene", "Labor");
		Name myName2 = new Name ("ALI", "Dali");
		Name thirdName = new Name("Ali", "Jaber");
		Name fourthName = new Name("Raneem", "Dali");
		
		testEquals("Case 1: Different Names", myName, otherName, false);
		testEquals("Case 2: Same Names", myName, myName, true);
		testEquals("Case 3: Same Names but Different UpperCase", myName, myName2, true);
		testEquals("Case 4: Same FirstName but different Last Name",
				myName, thirdName, false);
		testEquals("Case 5: Different FirstName But Same Last Name",
				myName, fourthName, false);
		
	}
	
	public static void testEquals(String testCase, Name myName, Name otherName, boolean expectedResult){
		System.out.println("   " + testCase);

		if(myName.equals(otherName) == expectedResult){
			System.out.println("	"+"Success!  " + "Returned Result: " + myName.equals(otherName));
			System.out.println("	"+"Expected result: " + expectedResult);
			if(expectedResult == false){
				System.out.println("	"+ myName + "  NOT EQUAL  " + otherName);
			}else
				System.out.println("	" +myName + "  EQUAL  " + otherName);
		}else
			System.out.println("	"+"Error! Test Failed");
		
		System.out.println();
	}

}




