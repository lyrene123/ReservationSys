package groupLAPD.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;

/**
 * The DawsonHotelFactoryTest Class tests the methods
 * of the DawsonHotelFactory enum class.
 * 
 * @author Daniel Cavalcanti
 * @version September 2016
 */
public class DawsonHotelFactoryTest {

	/**
	 * The main method will call private methods that will each test 
	 * a method in the DawsonHotelFactory enum class.
	 * @param args String array
	 */
	public static void main(String[] args) 
	{
		testGetCustomerInstance();
		testGetCard();
		testGetRoomInstance();
		testgetReservationInstance();
	}
	/**
	 * The testGetCustomerInstance method will call the overloaded 
	 * testGetCustomerInstance with the a specific test case
	 */
	public static void testGetCustomerInstance()
	{
		System.out.println("\nTesting the getCustomer Instance method");
		System.out.println("----------------------------------------");
		testGetCustomerInstance("Case 1: A valid Full name and Email "
				+ "", "Daniel", "Cavalcanti", "danielhbc@gmail.com", true);
		testGetCustomerInstance("Case 2: Incorret email format "
				+ "", "Daniel", "Cavalcanti", "danielhbcgmail.com", false);
		testGetCustomerInstance("Case 3: Invalid First Name "
				+ "", "", "Cavalcanti", "danielhbc@gmail.com", false);
		testGetCustomerInstance("Case 4: Invalid Last Name "
				+ "", "Daniel", "Caval_canti", "danielhbc@gmail.com", false);
		testGetCustomerInstance("Case 5: Null email "
				+ "", "Daniel", "Cavalcanti", null, false);
		testGetCustomerInstance("Case 6: Empty Email "
				+ "", "Daniel", "Cavalcanti", " ", false);
		testGetCustomerInstance("Case 7: Null First Name "
				+ "", null, "Cavalcanti", " ", false);

	}
	/**
	 * The overloaded testGetCustomerInstance will test the overridden  
	 * getCustomerInstance method of the DawsonHotelFactory enum class.
	 * @param testCase The Test Case
	 * @param firstname The first name of the Customer
	 * @param lastname The last name of the Customer
	 * @param email The email of the Customer
	 * @param expectedResult A condition True or False for the test case
	 */
	private static void testGetCustomerInstance(String testCase, String firstName, 
			String lastName, String email, boolean expectedValid)
	{
		try{
			System.out.println("   " + testCase);
			Customer name = DawsonHotelFactory.DAWSON.getCustomerInstance
					(firstName, lastName, email);
			System.out.print("\tThe Customer instance was created " 
					+ name);
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
	 * The testGetCard method will call the overloaded 
	 * testGetCard with the a specific test case
	 */
	private static void testGetCard()
	{
		System.out.println("\nTesting the getCard method");
		System.out.println("----------------------------------------");
		testGetCard("Case 1: A Valid Card Type and Card Number ", 
				"Amex", "371604312147093", true);
		testGetCard("Case 2: A null Card Number ", 
				"Amex", null, false);
		testGetCard("Case 3: Empty Card Number ", 
				"Amex", "", false);
		testGetCard("Case 4: Invalid Card Type ", 
				"VisaTop", "", false);
		testGetCard("Case 5: null Card Type ", 
				null, "371604312147093", false);
		testGetCard("Case 6: Empty Card Type ", 
				"", "5326385350239456", false);
		testGetCard("Case 7: A Wrong Card Number ", 
				"Amex", "671604312147093", false);
	}
	/**
	 * The overloaded testGetCard will test the overridden  
	 * getCard method of the DawsonHotelFactory enum class.
	 * @param testCase The Test Case
	 * @param cardtype The type of the CreditCard
	 * @param number The number of the CreditCard
	 * @param expectedResult A condition True or False for the test case
	 */
	private static void testGetCard(String testCase, String cardtype, String number, 
			Boolean expectedValid)
	{
		System.out.println("   " + testCase);
		try{
			CreditCard card = DawsonHotelFactory.DAWSON.getCard(cardtype, number);
			System.out.print("\tThe CreditCard instance was created " + card);
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
	 * The testGetRoomInstance method will call the overloaded 
	 * testGetRoomInstance with the a specific test case
	 */
	private static void testGetRoomInstance()
	{
		System.out.println("\nTesting the GetRoomInstance method");
		System.out.println("----------------------------------------");
		testGetRoomInstance("Case 1: Valid room number of type Normal",
				101, "Normal", true);
		testGetRoomInstance("Case 2: Invalid room number of type Normal",
				109, "Normal", false);
		testGetRoomInstance("Case 3: Invalid room Type",
				101, "Suite", false);
		testGetRoomInstance("Case 4: Valid room Number and room type",
				604, "Suite", true);
		testGetRoomInstance("Case 5: Invalid room number of type Suite",
				101, "Suite", false);
		testGetRoomInstance("Case 6: Valid room Number and room type",
				801, "penthouse", true);
		testGetRoomInstance("Case 7: Invalid room number of type Penthouse",
				709, "penthouse", false);
		testGetRoomInstance("Case 8: Invalid room number of type Penthouse",
				801, "Normal", false);
		testGetRoomInstance("Case 8: Invalid room number",
				1001, "Normal", false);
		testGetRoomInstance("Case 8: Null room type",
				101, null, false);
		testGetRoomInstance("Case 8: Empty room number",
				000, "Normal", false);
		testGetRoomInstance("Case 8: Empty room type",
				809, "", false);
	}
	/**
	 * The overloaded testGetRoomInstance will test the overridden  
	 * getetRoomInstance method of the DawsonHotelFactory enum class.
	 * @param testCase The Test Case
	 * @param roomNumber The number of the room
	 * @param roomtype The type of the room
	 * @param expectedResult A condition True or False for the test case
	 */
	private static void testGetRoomInstance(String testCase, int roomNumber, 
			String roomtype, Boolean expectedValid)
	{
		System.out.println("   " + testCase);
		try
		{
			Room room = DawsonHotelFactory.DAWSON.getRoomInstance(roomNumber,roomtype);
			System.out.print("\tThe Room instance was created " + room);
			System.out.print("\n\tExpected return: " 
					+ expectedValid);
			if(expectedValid == true)
			{
				System.out.println("\n\tValid Data. == TEST OK");
			} else {
			System.out.println("\n\t" + "Error! Invalid data. ==== FAILED TEST ====");
			//System.out.println();
			}
		}
		catch (IllegalArgumentException iae)	
		{
			System.out.print("\t"+ iae.getMessage());
			if (expectedValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) 
		{
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 					
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValid)
				System.out.print(" Expected Valid.");
		}
		System.out.println("\n");
	}
	/**
	 * The testgetReservationInstance method will call the overloaded 
	 * testgetReservationInstance with the a specific test case
	 */
	private static void testgetReservationInstance()
	{

		//Creating Valid new Instances of Customer
		Customer customer1 = DawsonHotelFactory.DAWSON.getCustomerInstance
				("Daniel", "Cavalcanti", "danielhbc@gmail.com");
		Customer customer2 = DawsonHotelFactory.DAWSON.getCustomerInstance
				("PengKim", "Sy", "pengkim@gmail.com");
		Customer customer3 = DawsonHotelFactory.DAWSON.getCustomerInstance
				("Lyrene", "Labor", "laborlyrene@gmail.com");
		Customer customer4 = DawsonHotelFactory.DAWSON.getCustomerInstance
				("Ali", "Dali", "AliDali@gmail.com");
		//Creating valid new Instances of Room
		Room room1 = DawsonHotelFactory.DAWSON.getRoomInstance(101,"normal");
		Room room2 = DawsonHotelFactory.DAWSON.getRoomInstance(801,"penthouse");
		Room room3 = DawsonHotelFactory.DAWSON.getRoomInstance(604,"suite");
		/*		/*
		 * Customer and Room are always valid. Only
		 * getReservationInstance method will be tested
		 */
		System.out.println("\nTesting the getReservationInstance method");
		System.out.println("-------------------------------------------");
		testgetReservationInstance("Case 1: Valid data Customer1", customer1, room1, 
				2016, 12, 25, 2017, 01, 27, true );
		testgetReservationInstance("Case 2: Valid data Customer2", customer2, room2, 
				2016, 10, 25, 2017, 02, 20, true );
		testgetReservationInstance("Case 3: Valid data Customer3", customer3, room3, 
				2016, 11, 21, 2017, 02, 15, true );
		testgetReservationInstance("Case 4: Valid data Customer4", customer4, room3, 
				2016, 11, 21, 2017, 02, 15, true );
		testgetReservationInstance("Case 5: Invalid Check-In Month", customer1, room1, 
				2016, 15, 25, 2017, 01, 27, false);
		testgetReservationInstance("Case 6: Invalid Check-Out Month", customer2, room2, 
				2016, 10, 25, 2017, 14, 20, false);
		testgetReservationInstance("Case 7: Invalid Check-In Day", customer1, room3, 
				2016, 11, 33, 2017, 02, 10, false );
		testgetReservationInstance("Case 8: Invalid Check-Out Day", customer1, room3, 
				2016, 11, 21, 2017, 02, 35, false );
		testgetReservationInstance("Case 9: Invalid Check-In Year", customer1, room1, 
				0, 01, 25, 2017, 01, 27, false);
		testgetReservationInstance("Case 10: Invalid Check-Out Year", customer1, room3, 
				2016, 11, 21, 0, 02, 25, false );
		testgetReservationInstance("Case 11: Check-In Day after Check-Out day", customer1, room3, 
				2016, 11, 21, 2016, 10, 02, false );

	}
	/**
	 * The overloaded testgetReservationInstance will test the overridden  
	 * getReservationInstance method of the DawsonHotelFactory enum class.
	 * @param testCase The Test Case
	 * @param aCustomer The instance of Customer
	 * @param aRoom The instance of Room
	 * @param inYear The Year of the Check-In
	 * @param inMonth The Month of the Check-In
	 * @param inDay The Day of the Check-In
	 * @param outYear The Year of the Check-Out
	 * @param outMonth The Month of the Check-Out
	 * @param outDay The Day of the Check-Out
	 * @param expectedResult A condition True or False for the test case
	 */
	private static void testgetReservationInstance(String testCase, Customer aCustomer, Room aRoom, int inYear, 
			int inMonth, int inDay, int outYear, int outMonth, int outDay, Boolean expectedValid)
	{
		System.out.println("   " + testCase);
		try
		{
			Reservation reserv = DawsonHotelFactory.DAWSON.getReservationInstance(aCustomer, aRoom,
					inYear, inMonth, inDay, outYear, outMonth, outDay);
			System.out.print("\tThe Reservation instance was created " + reserv);
			System.out.print("\n\tExpected return: " 
					+ expectedValid);
			if(expectedValid == true){
				System.out.println("\n\tValid Data. == TEST OK");
			} else {
			System.out.println("\n\t" + "Error! Invalid data. ==== FAILED TEST ====");
			//System.out.println();
			}
		}
		catch (IllegalArgumentException iae)	
		{
			System.out.print("\t"+ iae.getMessage());
			if (expectedValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) 
		{
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 					
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValid)
				System.out.print(" Expected Valid.");
		}
		System.out.println();
	}
}







