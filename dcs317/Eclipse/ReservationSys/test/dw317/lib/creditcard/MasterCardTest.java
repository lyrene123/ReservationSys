/**
 * 
 */
package dw317.lib.creditcard;

import dw317.hotel.business.RoomType;
import dw317.lib.creditcard.CreditCard.CardType;
import groupLAPD.hotel.business.DawsonRoom;

/**
 * The MasterCardTest class is used to test the MasterCard class and
 * to make sure that it behaves as expected and throws the right
 * exceptions based on the credit card number
 * 
 * @author Lyrene Labor
 * @version September 2016
 */
public class MasterCardTest {

	/**
	 * The main method will call private methods that will each test 
	 * a specific method in the MasterCard class and inherited ones
	 * from AbstractCreditCard class
	 * 
	 * @param args String array
	 */
	public static void main(String[] args) {

		//method calls for each methods from MasterCard class to test
		testConstructor();
		testEquals();
		testGetNumber();
		testGetType();
		testHashCode();

	}
	
	/**
	 * The testConstructor method will call the overloaded testConstructor
	 * with the a specific test case
	 */
	private static void testConstructor() {

		System.out.println("\nTesting the MasterCard constructor.");
		testConstructor("Case 1 - valid data (mastercard*5326385350239456)",
				"5326385350239456", true);
		testConstructor("Case 2 - invalid data card number's starting digits "
				+ "1926385350239456"
				+ " are invalid", "1926385350239456", false);
		testConstructor("Case 3 - invalid data card number does not contain "
				+ "16 digits only 53263853502", "53263853502", false);
		testConstructor(
				"Case 4 - invalid data MasterCard number not a valid credit "
				+ "card " + "number based on Luhn Algorithm 5326385350239440",
				"5326385350239440", false);
		testConstructor("Case 5 - invalid data card " + "number not "
				+ "numeric 5326c85350239456",
				"5326c85350239456", false);
		testConstructor("Case 6 - valid data card number contains trailing "
				+ "spaces  5326385350239456", " 5326385350239456", true);
		testConstructor("case 7 - invalid data card number is a decimal "
				+ "number 5326.85350239456",
				"5326.85350239456", false);
		testConstructor("case 8 - invalid data card number is empty", "", 
				false);
		testConstructor("case 9 - invalid data card number is just spaces", 
				"    ", false);
		testConstructor("case 10 - invalid data card number is null", null, 
				false);
		testConstructor("case 11 - invalid data card number contains spaces", 
				"53263853 0239456", false);
		testConstructor("case 12 - invalid data card number contains other "
				+ "character 53263853?0239456", 
				"53263853?0239456", false);
		testConstructor("case 13 - invalid data card number contains other "
				+ "character 53263853..239456", 
				"53263853..239456", false);
		testConstructor("case 14 - invalid data card number based on"
				+ "Luhn algorithms", 
				"5396385350239456", false);
		testConstructor("case 15 - invalid data card number contains "
				+ "less than 16 digits 53965350239456", 
				"53965350239456", false);
		testConstructor("case 16 - valid data card number contains "
				+ "spaces at the end", 
				"5326385350239456     ", true);
		testConstructor("case 17 - invalid data card number contains "
				+ "dots at the end 53263853502394..", 
				"53263853502394..", false);
		testConstructor("case 18 - invalid data card number contains "
				+ "dots at the beginning ..26385350239456", 
				"..26385350239456", false);
		testConstructor("case 19 - invalid data card number contains "
				+ "spaces 53263 53 0239456", 
				"53263 53 0239456", false);
		testConstructor("case 20 - invalid data card number contains "
				+ "5326385$&0239456", 
				"5326385$&0239456", false);
		testConstructor("case 22 - invalid data card number contains "
				+ "%326385350239456", 
				"%326385350239456", false);
		testConstructor("case 23 - invalid data card number contains "
				+ "%!26385350239456", 
				"%!26385350239456", false);
		testConstructor("case 24 - invalid data card number contains "
				+ "532638535023945^", 
				"532638535023945^", false);
		testConstructor("case 25 - invalid data card number contains "
				+ "532638#350239456", 
				"532638#350239456", false);
		testConstructor("case 26 - invalid data card number contains "
				+ "  .   ", 
				"   .   ", false);
		testConstructor("case 27 - invalid data card number contains "
				+ "51473929", 
				"51473929", false);
		testConstructor("case 28 - valid data card number contains trailing spaces "
				+ " 5326385350239456", 
				" 5326385350239456", true);
		testConstructor("case 29 - invalid data card number contains spaces "
				+ " 532638 535 0239456", 
				" 532638 535 0239456", false);
		testConstructor("case 30 - invalid data card number contains "
				+ "9026385350239456", 
				"9026385350239456", false);

	}

	/**
	 * The overloaded testConstructor will test the constructor 
	 * of the MasterCard class
	 * @param testCase described test case
	 * @param number the credit card number 
	 * @param expectedResult the boolean if the result is expected
	 */
	private static void testConstructor(String testCase, String number, 
			boolean expectedResult) {
		System.out.println("   " + testCase);
		try 
		{
			MasterCard card = new MasterCard(number);
			System.out.println("\tThe MasterCard instance has been created: " 
			+ card);

			if (!expectedResult) {
				System.out.print("\n\tError! You expected this case to "
						+ "fail but"
						+ " it didn't. ");
			}
		} 
		catch (IllegalArgumentException e) 
		{
			
			System.out.print("\t" + e.getMessage());
			if (expectedResult)
			{
				System.out.print("\n\tError! You expected this case to "
						+ "succeed "
						+ "but it didnt. ");
			}
		}
			
		catch (Exception x) 
		{
			System.out
					.print("\n\tError! There was an unexpected exception "
							+ "type " 
			+ x.getClass() + " " + x.getMessage());
			if (expectedResult)
			{
				System.out.print("\n\tYou expected this case to succeed "
						+ "but it "
						+ "didn't ");
			}
		}
		System.out.println("\n");
	}
	
	/**
	 * The testEquals method will call the overloaded testEquals
	 * with the a specific test case
	 */
	private static void testEquals() {
		System.out.println("\nTesting the equals method.");

		MasterCard card = new MasterCard("5361496937200808");
		testEquals("case 1 - mastercard*5361496937200808 with " + 
		"mastercard*5361496937200808",
				"Expected result:" + " both rooms are equal", card);
		card = new MasterCard("5547361938332165");
		testEquals("case 2 - mastercard*5361496937200808 with "
				+ "mastercard*5547361938332165",
				"Expected result:" + " both rooms are not equal", card);
		DawsonRoom room = new DawsonRoom(206, RoomType.NORMAL);
		testEquals("case 2 - mastercard*5361496937200808 with 206*normal",
				"Expected result:" + " both objects are not equal", room);

	}

	/**
	 * The overloaded testEquals will test the equals method 
	 * of the MasterCard class
	 * @param testCase described test case
	 * @param expectedResult described expected result
	 * @param obj an object of type Object
	 */
	private static void testEquals(String testCase, String expectedResult,
			Object obj) {

		System.out.println("   " + testCase);
		System.out.println("\t    " + expectedResult);

		MasterCard testCard = new MasterCard("5361496937200808");
		boolean compare = testCard.equals(obj);

		if (compare) {
			System.out.println("\tresult: " + testCard + " and " + obj + 
					" are equal.");
		} else {
			System.out.println("\tresult:" + testCard + " and " + obj + 
					" are not equal.");
		}

	}


	/**
	 * The testGetNumber method will call the overloaded testGetNumber
	 * with the a specific test case
	 */
	private static void testGetNumber() {
		System.out.println("\nTesting the getNumber method.");
		testGetNumber("case 1 - valid returned value for credit number " + 
		"5361496937200808", "5361496937200808",
				"5361496937200808");
		testGetNumber("case 2 - valid returned value for credit number " + 
				"5547361938332165", "5547361938332165",
				"5547361938332165");

	}
/**
 * The overloaded testGetNumber will test the getNumber method 
 * of the MasterCard class
 * @param testCase described test case
 * @param creditNumber the credit card number
 * @param expectedResult the expected credit card number
 */
	private static void testGetNumber(String testCase, String creditNumber, 
			String expectedResult) {

		System.out.println("   " + testCase);
		MasterCard card = new MasterCard(creditNumber);
		System.out.print("\tThe MasterCard instance was created: " + card);
		// compare expected and returned value
		System.out.print("\n\texpected returned value: " + expectedResult);
		System.out.print("\n\treturned value: " + card.getNumber());

		if (card.getNumber() != expectedResult) {
			System.out.print("  Error! unexected returned value!");
		}
		System.out.println("\n");

	}

	/**
	 * The testGetType method will call the overloaded testGetType
	 * with the a specific test case
	 */
	private static void testGetType() {
		System.out.println("\nTesting the getType method.");
		testGetType("case 1 - valid returned value for mastercard type", 
				"5361496937200808", CardType.MASTERCARD);
		testGetType("case 1 - valid returned value for mastercard type", 
				"5547361938332165", CardType.MASTERCARD);

	}

	/**
	 * The overloaded testGetType will test the getType method 
	 * of the MasterCard class
	 * @param testCase described test case
	 * @param creditNumber the credit card number
	 * @param expectedResult the expected card type 
	 */
	private static void testGetType(String testCase, String creditNumber,
			CardType expectedResult) {
		System.out.println("   " + testCase);

		MasterCard card = new MasterCard(creditNumber);
		System.out.print("\tThe MasterCard instance was created: " + card);
		// compare expected and returned value
		System.out.print("\n\texpected returned value: " + expectedResult);
		System.out.print("\n\treturned value: " + card.getType());

		if (card.getType() != expectedResult) {
			System.out.print("  Error! unexected returned value!");
		}
		System.out.println("\n");
	}

	
	/**
	 * The testHashCode method will call the overloaded testHashCode
	 * with the a specific test case
	 */
	private static void testHashCode() {

		System.out.println("\nTesting the hashCode method.");
		MasterCard card1 = new MasterCard("5361496937200808");
		MasterCard card2 = new MasterCard("5361496937200808");
		
		testHashCode("case 1 - equal objects, same hash code",
				card1, card2, "Expected result: same hash codes");
		card2 = new MasterCard("5547361938332165");
		testHashCode("case 2 - not equal objects, different hash code",
				card1, card2, "Expected result: different hash codes");
		DawsonRoom room1 = new DawsonRoom(201,RoomType.NORMAL);
		testHashCode("case 3 - different objects, different hash code",
				card1, room1, "Expected result: different hash codes");
	}

	/**
	 * The overloaded testHashCode will test the hashCode method 
	 * of the MasterCard class
	 * @param testCase the described test case
	 * @param obj1 object of type Object
	 * @param obj2 object of type Object
	 * @param expectedResult described expected result
	 */
	private static void testHashCode(String testCase, Object obj1, 
			Object obj2,String expectedResult) {
			
		System.out.println("   " + testCase);
		System.out.println("   " + expectedResult);
		// get the hash code of each object
		int hash1 = obj1.hashCode();
		int hash2 = obj2.hashCode();
		// display each hash code and compare
		System.out.println("\tobject 1 - " + obj1 + ": " + hash1);
		System.out.println("\tobject 2 - " + obj2 + ": " + hash2);

	}

}
