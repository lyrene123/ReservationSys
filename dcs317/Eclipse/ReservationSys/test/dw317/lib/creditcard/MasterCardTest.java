/**
 * 
 */
package dw317.lib.creditcard;

import dw317.hotel.business.RoomType;
import dw317.lib.creditcard.CreditCard.CardType;
import groupLAPD.hotel.business.DawsonRoom;

/**
 * The MasterCardTest class is used to test the MasterCard class and to make
 * sure that it behaves as expected and throws the right exceptions based on the
 * credit card number
 * 
 * @author Lyrene Labor
 * @version September 2016
 */
public class MasterCardTest {

	/**
	 * The main method will call private methods that will each test a specific
	 * method in the MasterCard class and inherited ones from AbstractCreditCard
	 * class
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		testConstructor();
		testEquals();
		testGetNumber();
		testGetType();
		testHashCode();

	}

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

	private static void testHashCode(String testCase, Object obj1, Object obj2, String expectedResult) {
		System.out.println("   " + testCase);
		System.out.println("   " + expectedResult);
		// get the hash code of each object
		int hash1 = obj1.hashCode();
		int hash2 = obj2.hashCode();
		// display each hash code and compare
		System.out.println("\tobject 1 - " + obj1 + ": " + hash1);
		System.out.println("\tobject 2 - " + obj2 + ": " + hash2);

	}

	private static void testGetType() {
		System.out.println("\nTesting the getType method.");
		testGetType("case 1 - valid returned value for mastercard type", "5361496937200808", CardType.MASTERCARD);
		testGetType("case 1 - valid returned value for mastercard type", "5547361938332165", CardType.MASTERCARD);

	}

	private static void testGetType(String testCase, String creditNumber, CardType expectedResult) {
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

	private static void testGetNumber() {
		System.out.println("\nTesting the getNumber method.");
		testGetNumber("case 1 - valid returned value for credit number " + "5361496937200808", "5361496937200808",
				"5361496937200808");
		testGetNumber("case 2 - valid returned value for credit number " + "5547361938332165", "5547361938332165",
				"5547361938332165");

	}

	private static void testGetNumber(String testCase, String creditNumber, String expectedResult) {

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

	private static void testEquals() {
		System.out.println("\nTesting the equals method.");

		MasterCard card = new MasterCard("5361496937200808");
		testEquals("case 1 - mastercard*5361496937200808 with " + "mastercard*5361496937200808",
				"Expected result:" + " both rooms are equal", card);
		card = new MasterCard("5547361938332165");
		testEquals("case 2 - mastercard*5361496937200808 with mastercard*5547361938332165",
				"Expected result:" + " both rooms are not equal", card);
		DawsonRoom room = new DawsonRoom(206, RoomType.NORMAL);
		testEquals("case 2 - mastercard*5361496937200808 with 206*normal",
				"Expected result:" + " both objects are not equal", room);

	}

	private static void testEquals(String testCase, String expectedResult, Object obj) {

		System.out.println("   " + testCase);
		System.out.println("\t    " + expectedResult);

		MasterCard testCard = new MasterCard("5361496937200808");
		boolean compare = testCard.equals(obj);

		if (compare) {
			System.out.println("\tresult: " + testCard + " and " + obj + " are equal.");
		} else {
			System.out.println("\tresult:" + testCard + " and " + obj + " are not equal.");
		}

	}

	private static void testConstructor() {

		System.out.println("\nTesting the MasterCard constructor.");
		testConstructor("Case 1 - valid data (mastercard*5326385350239456)", "5326385350239456", true);
		testConstructor("Case 2 - invalid data card number's starting digits are invalid", "1926385350239456", false);
		testConstructor("Case 3 - invalid data card number does not contain 16 digits only", "53263853502", false);
		testConstructor(
				"Case 4 - invalid data MasterCard number not a valid credit card " + "number based on Luhn Algorithm",
				"5326385350239440", false);
		testConstructor("Case 5 - invalid data card b  " + "number not numeric", "5326c85350239456", false);
		testConstructor("Case 6 - valid data card number contains trailing spaces", " 5326385350239456", true);
		testConstructor("case 7 - invalid data card number is a decimal number", "5326.85350239456", false);
		testConstructor("case 8 - invalid data card number is empty", "", false);
		testConstructor("case 9 - invalid data card number is just spaces", "    ", false);
		testConstructor("case 10 - invalid data card number is null", null, false);
		testConstructor("case 11 - invalid data card number contains spaces", "53263853 0239456", false);

	}

	private static void testConstructor(String testCase, String number, boolean expectedResult) {
		System.out.println("   " + testCase);
		try {
			MasterCard card = new MasterCard(number);
			System.out.println("\tThe MasterCard instance has been created: " + card);

			if (!expectedResult) {
				System.out.print("\n\tError! You expected this case to fail but it didn't. ");
			}
		} catch (IllegalArgumentException e) {
			System.out.print("\t" + e.getMessage());
			if (expectedResult)
				System.out.print("\n\tError! You expected this case to succeed but it didnt. ");
		} catch (Exception x) {
			System.out
					.print("\n\tError! There was an unexpected exception type " + x.getClass() + " " + x.getMessage());
			if (expectedResult)
				System.out.print("\n\tYou expected this case to succeed but it didn't ");
		}
		System.out.println("\n");
	}

}
