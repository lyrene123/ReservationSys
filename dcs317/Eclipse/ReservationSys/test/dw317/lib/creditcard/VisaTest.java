/**
 * 
 */
package dw317.lib.creditcard;

import dw317.hotel.business.RoomType;
import dw317.lib.creditcard.CreditCard.CardType;
import groupLAPD.hotel.business.DawsonRoom;

/**
 * The VisaTest class is used to test the concrete Visa class
 * 
 * @author Daniel Bezerra
 * @version September 2016
 * 
 */
public class VisaTest {

	/**
	 * The main method contains different Visa Credit Card number as test cases which
	 * are used to instantiate objects of type Visa and check if the right
	 * exceptions will be generated based on a particular test case.
	 * @param args String array
	 * 
	 */
	public static void main(String[] args) 
	{
		//method calls for each methods from Visa class to test
		testTheOneParameterConstructor();
		testEquals();
		testGetNumber();
		testGetType();
		testHashCode();

	}
	/**
	 * testTheOneParameterConstructor method will call the overloaded 
	 * testTheOneParameterConstructor with the a specific test case
	 */
	private static void testTheOneParameterConstructor()
	{
		System.out.println("\nTesting the Visa Class constructor.");
		System.out.println("-------------------------------------");
		testTheOneParameterConstructor("Case 1 - valid data (visa*4774341863020040)",
				"4774341863020040", true);
		testTheOneParameterConstructor("Case 2 - invalid data card number's starting digit",
				"8774341863020040",false);
		testTheOneParameterConstructor("Case 3 - invalid data card number does not contain "
				+ "16 digits only", "863020040", false);
		testTheOneParameterConstructor("Case 4 - invalid data Visa number not a valid credit "
				+ "card " + "number based on Luhn Algorithm",
				"4774341863120048", false);
		testTheOneParameterConstructor("Case 5 - invalid data card " + "number not "
				+ "numeric", "4774341v63020040", false);
		testTheOneParameterConstructor("Case 6 - valid data card number contains trailing "
				+ "spaces", " 4774341863020040", true);
		testTheOneParameterConstructor("case 7 - invalid data card number is a decimal "
				+ "number", "4774341.863020040", false);
		testTheOneParameterConstructor("case 8 - invalid data card number is empty", "", 
				false);
		testTheOneParameterConstructor("case 9 - invalid data card number is just spaces", 
				"    ", false);
		testTheOneParameterConstructor("case 10 - invalid data card number is null", null, 
				false);
		testTheOneParameterConstructor("case 11 - invalid data card number contains spaces", 
				"47743418 63020040", false);
		testTheOneParameterConstructor("case 12 - invalid data card number contains other "
				+ "character", "53263853?0239456", false);
		testTheOneParameterConstructor("case 13 - invalid data card number contains other "
				+ "character", 
				"47743418..63020040", false);
		testTheOneParameterConstructor("case 14 - invalid data card number contains "
				+ "less than 16 digits", 
				"47743418630", false);
		testTheOneParameterConstructor("case 15 - valid data card number contains "
				+ "spaces at the end", 
				"4774341863020040     ", true);
		testTheOneParameterConstructor("case 16 - invalid data card number contains "
				+ "dots at the end", "4774341863020040..", false);
		testTheOneParameterConstructor("case 17 - invalid data card number contains "
				+ "dots at the beginning", "..4774341863020040", false);
		testTheOneParameterConstructor("case 18 - invalid data card number contains "
				+ "spaces", "4774 34186 3020040", false);
		testTheOneParameterConstructor("case 19 - invalid data card number contains "
				+ "non numeric characters", 
				"47743418$&020040", false);
		testTheOneParameterConstructor("case 20 - invalid data card number contains "
				+ "non numeric Characters in the beginning", 
				"%4774341863020040", false);
		testTheOneParameterConstructor("case 21 - invalid data card number contains "
				+ "non numeric characters at the end", 
				"4774341863020040^", false);
		testTheOneParameterConstructor("case 22 - invalid data card number contains "
				+ "space and dot", 
				"   .   ", false);
	}
	/**
	 * The overloaded testTheOneParameterConstructor will 
	 * test the constructor of the Visa class
	 * @param testCase The test case
	 * @param number The credit card number 
	 * @param expectedValid A condition True or False for the test case
	 */
	private static void testTheOneParameterConstructor(String testCase, String number, 
			boolean expectedValid) 
	{
		System.out.println("   " + testCase);
		try 
		{
			Visa card = new Visa(number);
			System.out.print("\tThe Visa instance was created " 
					+ card);
			System.out.print("\n\tExpected return: " 
					+ expectedValid);
			if(expectedValid == true){
				System.out.println("\n\tValid Data. == TEST OK");
				//System.out.println();
			} else {
				System.out.println("\n\t" + "Error! Invalid data. ==== FAILED TEST ====");
				//System.out.println();
			}
		}
		catch (IllegalArgumentException iae)	{
			System.out.print("\t"+ iae.getMessage());
			if (expectedValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 					
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}
	/**
	 * The testEquals method will call the overloaded testEquals
	 * with the a specific test case
	 */
	private static void testEquals() 
	{
		System.out.println("\nTesting the equals method from Visa Class.");
		System.out.println("--------------------------------------------");
		Visa card = new Visa("4774341863020040");
		testEquals("case 1 - visa*4774341863020040 with " 
				+ "visa*4774341863020040","Expected result:" + " both cards are equal", card);
		System.out.println();
		card = new Visa("4929593056772795");
		testEquals("case 2 - visa*4929593056772795 with "
				+ "visa*4774341863020040",
				"Expected result:" + " both cards are not equal", card);
		System.out.println();
		DawsonRoom room = new DawsonRoom(101, RoomType.NORMAL);
		testEquals("case 3 - visa*4929593056772795 with 206*normal",
				"Expected result:" + " both objects are not equal", room);
	}
	/**
	 * The overloaded testEquals will test the equals method 
	 * of the Visa class
	 * @param testCase The test case
	 * @param expectedResult described expected result
	 * @param obj an object of type Object
	 */
	private static void testEquals(String testCase, String expectedResult,
			Object obj) 
	{
		System.out.println("   " + testCase);
		System.out.println("\t    " + expectedResult);
		Visa testCard = new Visa("4774341863020040");
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
	private static void testGetNumber() 
	{
		System.out.println("\nTesting the getNumber method from Visa Class.");
		System.out.println("-----------------------------------------------");
		testGetNumber("case 1 - valid returned value for Visa credit number " + 
				"4774341863020040", "4774341863020040",
				"4774341863020040");
		testGetNumber("case 2 - valid returned value for Visa credit number " + 
				"4929593056772795", "4929593056772795",
				"4929593056772795");

	}
	/**
	 * The overloaded testGetNumber will test the getNumber method 
	 * of the Visa class
	 * @param testCase described test case
	 * @param creditNumber the credit card number
	 * @param expectedResult the expected credit card number
	 */
	private static void testGetNumber(String testCase, String creditNumber, 
			String expectedResult)
	{

		System.out.println("   " + testCase);
		Visa card = new Visa(creditNumber);
		System.out.print("\tThe Visa instance was created: " + card);
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
	private static void testGetType() 
	{
		System.out.println("\nTesting the getType method from Visa Class.");
		System.out.println("---------------------------------------------");
		testGetType("case 1 - valid returned value for visa type", 
				"4774341863020040", CardType.VISA);
		testGetType("case 1 - valid returned value for visa type", 
				"4774341863020040", CardType.VISA);

	}
	/**
	 * The overloaded testGetType will test the getType method 
	 * of the Visa class
	 * @param testCase described test case
	 * @param creditNumber the credit card number
	 * @param expectedResult the expected card type 
	 */
	private static void testGetType(String testCase, String creditNumber,
			CardType expectedResult) 
	{
		System.out.println("   " + testCase);

		Visa card = new Visa(creditNumber);
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
	private static void testHashCode()
	{
		System.out.println("\nTesting the hashCode method.");
		System.out.println("------------------------------");
		Visa card1 = new Visa("4774341863020040");
		Visa card2 = new Visa("4774341863020040");

		testHashCode("case 1 - equal objects, same hash code",
				card1, card2, "Expected result: same hash codes");
		System.out.println();
		card2 = new Visa("4929593056772795");
		testHashCode("case 2 - not equal objects, different hash code",
				card1, card2, "Expected result: different hash codes");
		System.out.println();
		DawsonRoom room1 = new DawsonRoom(101,RoomType.NORMAL);
		testHashCode("case 3 - different objects, different hash code",
				card1, room1, "Expected result: different hash codes");
	}
	/**
	 * The overloaded testHashCode will test the hashCode method 
	 * of the Visa class
	 * @param testCase the described test case
	 * @param obj1 object of type Object
	 * @param obj2 object of type Object
	 * @param expectedResult described expected result
	 */
	private static void testHashCode(String testCase, Object obj1, 
			Object obj2,String expectedResult) 
	{
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