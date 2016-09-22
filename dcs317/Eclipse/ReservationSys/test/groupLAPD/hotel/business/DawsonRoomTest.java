/**
 * 
 */
package groupLAPD.hotel.business;

import dw317.hotel.business.RoomType;

import dw317.lib.creditcard.MasterCard;

/**
 * The DawsonRoomTest class is used to test the methods of the DawsonRoom class
 * @author Lyrene Labor
 * @version September 2016
 */
public class DawsonRoomTest {

	/**
	 * The main method will call private methods that will each test a method 
	 * in the DawsonRoom class
	 * @param args
	 */
	public static void main(String[] args) {
		//method calls
		testCompareTo();
		
		testEquals();
		testGetFloor();
		testGetNumber();
		testGetRoomNumber();
		testGetRoomType();
		testHashCode();
		testTheTwoParameterConstructor();
	}
	
	/**
	 * The testCompareTo method will call the overloaded testCompareTo
	 * with the a specific test case
	 */
	private static void testCompareTo(){
		System.out.println("\nTesting the compareTo method");
		DawsonRoom room = new DawsonRoom(101, RoomType.NORMAL);
		testCompareTo("case 1 - room 101 with 508", "Expected result: "
				+ "508 > 101", room);
		room = new DawsonRoom(508, RoomType.NORMAL);
		testCompareTo("case 2 - room 508 with 508", "Expected result: "
				+ "508=508", room);
		room = new DawsonRoom(604, RoomType.SUITE);
		testCompareTo("case 3 - room 508 with 604", "Expected result: "
				+ "604>508", room);
	}
	
	/**
	 * The overloaded testCompareTo will test the overridden compareTo method
	 * of the DawsonRoom class
	 * @param testCase the test case
	 * @param expectedResult the described expected result
	 * @param roomNumber the instance of a DawsonRoom object
	 */
	private static void testCompareTo(String testCase, String expectedResult,
			DawsonRoom roomNumber){
		
		System.out.println("   " + testCase);
		System.out.println("\t    " + expectedResult);
		
		DawsonRoom test = new DawsonRoom(508, RoomType.NORMAL);
		int compare = test.compareTo(roomNumber);
		if(compare>0){
			System.out.println("\tresult: "+test+" is superior than " + roomNumber);
			
		}
		else if(compare<0){
			System.out.println("\tresult: "+test+" is less superior than "+roomNumber);
		}
		else{
			System.out.println("\tresult: "+test+" is equals to " + roomNumber);
		}
	}
	
	
	
	
	/**
	 * The testEquals method will call the overloaded testEquals
	 * with the a specific test case
	 */
	private static void testEquals(){
		System.out.println("\nTesting the equals method");
		
		DawsonRoom room = new DawsonRoom(206, RoomType.NORMAL);
		testEquals("case 1 - 206*normal with 206*normal", "Expected result:"
				+ " both rooms are equal", room);
		room = new DawsonRoom(801, RoomType.PENTHOUSE);
		testEquals("case 2 - 801*penthouse with 206*normal", "Expected result:"
				+ " 801*penthouse not equals to 206*normal", room);
		
		MasterCard test = new MasterCard("5326385350239456");
		testEquals("case 3 - another class with 206*normal", "Expected result:"
				+ " another class not equals to 206*normal", test);
	}
	
	/**
	 * The overloaded testEquals will test the overridden equals method
	 * of the DawsonRoom class
	 * @param testCase the test case
	 * @param expectedResult the described expected result
	 * @param obj an object of type Object
	 */
	private static void testEquals(String testCase, String expectedResult,
			Object obj){
		System.out.println("   " + testCase);
		System.out.println("\t    " + expectedResult);
		
		DawsonRoom test = new DawsonRoom(206,RoomType.NORMAL);
		boolean compare = test.equals(obj);
		
		if(compare){
			System.out.println("\tresult: "+ test + " and " + obj + " are equal.");
		}
		else {
			System.out.println("\tresult:" + test + " and " + obj + " are not equal.");
		}
	}
	
	
	/**
	 * The testGetFloor method will call the overloaded testGetFloor
	 * with the a specific test case
	 */
	private static void testGetFloor(){
		System.out.println("\nTesting the getFloor method");
		testGetFloor("case 1 - valid returned value for room 302",
				302,RoomType.NORMAL,3);
		testGetFloor("case 2 - valid returned value for room 602",
				602,RoomType.SUITE,6);
	}
	
	/**
	 * The overloaded testGetFloor will test the getFloor method
	 * of the DawsonRoom class
	 * @param testCase the test case
	 * @param roomNumber an int room number
	 * @param type a room type enum
	 * @param expectedFloor the expected floor number to return
	 */
	private static void testGetFloor(String testCase, int roomNumber,
			RoomType type,int expectedFloor){
		System.out.println("   " + testCase);
		DawsonRoom room = new DawsonRoom(roomNumber,type);
		System.out.print("\tThe DawsonRoom instance was created: " + room);
		//compare the 2 object's floor number
		System.out.print("\n\texpected returned value: " + expectedFloor);
		System.out.print("\n\treturned value: " + room.getFloor());
		
		if(room.getFloor()!=expectedFloor){
			System.out.print("  Error! unexected returned value!");
		}
		System.out.println("\n");
	}
	
	/**
	 * The testGetNumber method will call the overloaded testGetNumber
	 * with the a specific test case
	 */
	private static void testGetNumber(){
		System.out.println("\nTesting the getNumber method");
		testGetNumber("case 1 - valid returned value for room 407", 
				407,RoomType.NORMAL,7);
		testGetNumber("case 2 - valid returned value for room 704", 
				704,RoomType.SUITE,4);
	}
	
	
	/**
	 * The overloaded testGetNumber will test the getNumber method
	 * of the DawsonRoom class
	 * @param testCase the test case
	 * @param roomNumber an int room number
	 * @param type a room type enum
	 * @param expectedNumber the expected number to return
	 */
	private static void testGetNumber(String testCase, int roomNumber,
			RoomType type, int expectedNumber){
		System.out.println("   " + testCase);
		DawsonRoom room = new DawsonRoom(roomNumber,type);
		System.out.print("\tThe DawsonRoom instance was created: " + room);
		//compare the 2 object's number
		System.out.print("\n\texpected returned value: " + expectedNumber);
		System.out.print("\n\treturned value: " + room.getNumber());
		
		if(room.getNumber()!=expectedNumber){
			System.out.print("  Error! unexected returned value!");
		}
		System.out.println("\n");
	}
	
	/**
	 * The testGetRoomNumber method will call the overloaded testGetRoomNumber
	 * with the a specific test case
	 */
	private static void testGetRoomNumber(){
		System.out.println("\nTesting the getRoomNumber method");
		testGetRoomNumber("case 1 - valid returned value for room 506",
				506, RoomType.NORMAL, 506);
		testGetRoomNumber("case 2 - valid returned value for room 301",
				301, RoomType.NORMAL, 301);
	}
	
	/**
	 * The overloaded testGetRoomNumber will test the getRoomNumber method
	 * of the DawsonRoom class
	 * @param testCase the test case 
	 * @param roomNumber an int room tnumber
	 * @param type a room type enum
	 * @param expectedRoomNumber the expected room number returned
	 */
	private static void testGetRoomNumber(String testCase, int roomNumber,
			RoomType type, int expectedRoomNumber){
		System.out.println("   " + testCase);
		DawsonRoom room = new DawsonRoom(roomNumber,type);
		System.out.print("\tThe DawsonRoom instance was created: " + room);
		//compare the 2 object's room number
		System.out.print("\n\texpected returned value: " + expectedRoomNumber);
		System.out.print("\n\treturned value: " + room.getRoomNumber());
		
		if(room.getRoomNumber()!=expectedRoomNumber){
			System.out.print("  Error! unexected returned value!");
		}
		System.out.println("\n");
	}
	
	/**
	 * The testGetRoomType method will call the overloaded testGetRoomType
	 * with the a specific test case 
	 */
	private static void testGetRoomType(){
		System.out.println("\nTesting the getRoomType method");
		testGetRoomType("case 1 - valid returned value for 208*normal",
				208,RoomType.NORMAL,RoomType.NORMAL);
		testGetRoomType("case 2 - valid returned value for 702*suite",
				702,RoomType.SUITE,RoomType.SUITE);
		testGetRoomType("case 2 - valid returned value for 801*penthouse",
				801,RoomType.PENTHOUSE,RoomType.PENTHOUSE);
	}
	
	/**
	 * The overloaded testGetRoomType will test the getRoomType method
	 * of the DawsonRoom class
	 * @param testCase the described test case
	 * @param roomNumber an integer room number
	 * @param type a room type enum
	 * @param expectedType the  expected type returned
	 */
	private static void testGetRoomType(String testCase, int roomNumber,
			RoomType type, RoomType expectedType){
		System.out.println("   " + testCase);
		DawsonRoom room = new DawsonRoom(roomNumber,type);
		System.out.print("\tThe DawsonRoom instance was created: " + room);
		//compare the two object's room type
		System.out.print("\n\texpected returned value: " + expectedType.toString());
		System.out.print("\n\treturned value: " + room.getRoomType().toString());
		
		if(!(room.getRoomType().equals(expectedType))){
			System.out.print("  Error! unexected returned value!");
		}
		System.out.println("\n");
			
	}
	
	

	/**
	 * The testHashCode method will call the overloaded testHashCode
	 * with the a specific test case   
	 */
	private static void testHashCode(){
		System.out.println("\nTesting the hashCode method");
		DawsonRoom room1 = new DawsonRoom(201,RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(201,RoomType.NORMAL);
		testHashCode("case 1 - equal objects, same hash code",
				room1, room2, "Expected result: same hash codes");
		room2 = new DawsonRoom(304,RoomType.NORMAL);
		testHashCode("case 2 - not equal objects, different hash code",
				room1, room2, "Expected result: different hash codes");
		MasterCard test = new MasterCard("5326385350239456");
		testHashCode("case 3 - different objects, different hash code",
				room1, test, "Expected result: different hash codes");
	}
	
	/**
	 * The overloaded testHashCode will test the overridden hashCode
	 * of the DawsonRoom class
	 * @param testCase The describes test case
	 * @param obj1 An object of type Object
	 * @param obj2 An object of type Object
	 * @param expectedResult The described expected result
	 */
	private static void testHashCode(String testCase, Object obj1, Object obj2, 
			String expectedResult){
		System.out.println("   " + testCase);
		System.out.println("   " + expectedResult);
		//get the hash code of each object
		int hash1 = obj1.hashCode();
		int hash2 = obj2.hashCode();
		//display each hash code and compare
		System.out.println("\tobject 1 - "+obj1 + ": " + hash1);
		System.out.println("\tobject 2 - "+obj2 + ": " + hash2);
	}
	
	
	


	/**
	 * The testTheTwoParameterConstructor method will call the 
	 * overloaded testTheTwoParameterConstructorwith the a specific 
	 * test case
	 * 
	 */
	private static void testTheTwoParameterConstructor(){
		System.out.println("\nTesting the two parameter constructor.");
		
		RoomType normal = RoomType.NORMAL;
		RoomType suite = RoomType.SUITE;
		RoomType penthouse = RoomType.PENTHOUSE;
		
		testTheTwoParameterConstructor("case 1 - valid data(101*normal): ", 101, normal, true);
		testTheTwoParameterConstructor("case 2 - invalid data(room 109), room number  not between 1-8:"
				+ ": ", 109, normal, false);
		testTheTwoParameterConstructor("case 3 - invalid data(room 139), room number  not between 01-08:"
				+ ": ", 139, normal, false);
		testTheTwoParameterConstructor("case 4 - valid data(604*suite):"
				+ ": ", 604, suite, true);
		testTheTwoParameterConstructor("case 5 - valid data(801*penthouse):"
				+ ": ", 801, penthouse, true);
		testTheTwoParameterConstructor("case 6 - invalid data(room 901), floor number not between 1-8"
				+ ": ", 901, penthouse, false);
		testTheTwoParameterConstructor("case 7 - invalid data(room 100), room number not between 1-8"
				+ ": ", 100, penthouse, false);
		testTheTwoParameterConstructor("case 8 - invalid data(null room type )"
				+ ": ", 101, null, false);
		testTheTwoParameterConstructor("case 9 - invalid data(room number not 3 digits)"
				+ ": ", 1, normal, false);
		testTheTwoParameterConstructor("case 10 - invalid data(room number doesn't exists (room 608)"
				+ ": ", 608, suite, false);
		testTheTwoParameterConstructor("case 11 - invalid data(room number doesn't exists (room 708)"
				+ ": ", 708, suite, false);
		testTheTwoParameterConstructor("case 12 - invalid data(room number doesn't exists (room 804)"
				+ ": ", 804, penthouse, false);
		testTheTwoParameterConstructor("case 13 - invalid data(room 101, type suite)"
				+ ": ", 101, suite, false);
		testTheTwoParameterConstructor("case 14 - invalid data(room 801, type normal)"
				+ ": ", 801, normal, false);
		testTheTwoParameterConstructor("case 15 - invalid data(room 700 doesn't exist)"
				+ ": ", 700, suite, false);
		
		
		
	}
	
	/**
	 * The overloaded testTheTwoParameterConstructor will test the 
	 * constructor method of the DawsonRoom class
	 * @param testCase the test case
	 * @param roomNumber the int room number
	 * @param roomType the room type enum
	 * @param expectedResult the boolean expected result 
	 */
	private static void testTheTwoParameterConstructor(String testCase,
			int roomNumber, RoomType roomType, boolean expectedResult){
		System.out.println("   " + testCase);
		
		try {
			DawsonRoom room = new DawsonRoom(roomNumber, roomType);
			System.out.print("\tThe DawsonRoom instance was created: " + room);
			
			if (!expectedResult)
				System.out.print("\n\tError! You expected this case to fail but it didn't. ");
		}
		catch (IllegalArgumentException e)	{
			System.out.print("\t"+ e.getMessage());
			if (expectedResult)
				System.out.print("\n\tError! You expected this case to succeed but it didnt. ");
		}
		catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expectedResult)
				System.out.print("\n\tYou expected this case to succeed but it didn't ");
		}

		System.out.println("\n");
		
	}

}

