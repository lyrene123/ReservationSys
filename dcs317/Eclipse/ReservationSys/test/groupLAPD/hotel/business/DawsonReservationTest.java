package groupLAPD.hotel.business;

import java.time.LocalDate;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;

/**
 * This test Class tests the DawsonReservation class very thoroughly.
 * It uses two overLoaded methods one that takes parameters and the other 
 * calls the parameterized method. Every method test a different method in
 * the DawsonReservation class. Every method returns the case being tested,
 * the result returned and the expected result.
 * @author Ali Dali
 *
 */
public class DawsonReservationTest {


	public static void main(String[] args){	

		testGetCustomer();
		System.out.println();
		testGetNumberOfDays();
		System.out.println();
		testCompareTo();
		System.out.println();
		testEquals();
		System.out.println();
		testConstructor();
		System.out.println();
		testHashCode();
		System.out.println();
		testOverlap();
	}//end of Main

	//test getCutomer()
	public static void testGetCustomer(){
		
		//Creating a new instance of Room
		DawsonRoom aRoom = new DawsonRoom(603,RoomType.SUITE);
		DawsonRoom anotherRoom = new DawsonRoom(801, RoomType.PENTHOUSE);

		//Creating a new instance of Customer
		DawsonCustomer customer = new DawsonCustomer("Ali", "Dali", "ali@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Lyrene", "Labor", "Ly.Lab@gamil.com");

		//Creating a new instance of DawsonReservation
		DawsonReservation aliRes = new DawsonReservation(customer, aRoom, 2016, 1, 1, 2016, 1, 30);
		DawsonReservation lyreneRes = new DawsonReservation(customer2, anotherRoom,2016, 1, 1, 2016, 1, 30);
		
		//Calling parameterized overloaded testGetCustomer().
		System.out.println("testing getCustomer() method:");
		testGetCustomer("case 1 Valid Customer:  ", aliRes,  customer);
		testGetCustomer("case 2 second Valid Customer, different room, different customer info: ", lyreneRes, customer2);
	}

	public static void testGetCustomer(String testCase, DawsonReservation aCustomer, DawsonCustomer expectedResult){

		System.out.println(testCase);
		System.out.println("Returned Value: " + aCustomer.getCustomer());
		System.out.println("Expected Result: " + expectedResult);
	}




	//test getNumberofDaysLeft
	public static void testGetNumberOfDays(){

		//Creating LocalDates
		LocalDate checkInAli = null;
		LocalDate checkOutAli = null;
		LocalDate checkInLyrene = null;
		LocalDate checkOutLyrene = null;

		//Creating LocalDates
		checkInAli = checkInAli.of(2016, 1, 1);
		checkOutAli = checkOutAli.of(2016, 1, 30);
		checkInLyrene = checkInLyrene.of(2016, 2, 17);
		checkOutLyrene = checkOutLyrene.of(2016, 3, 17);
		
		//getting number of days left using methods provided by LocalDate
		int numDaysAli = checkInAli.until(checkOutAli).getDays();
		int numDaysLyrene = checkInLyrene.until(checkOutLyrene).getDays();

		//Creating a new instance of Room
		DawsonRoom aRoom = new DawsonRoom(603,RoomType.SUITE);
		DawsonRoom anotherRoom = new DawsonRoom(801, RoomType.PENTHOUSE);

		//Creating a new instance of Customer
		DawsonCustomer customer = new DawsonCustomer("Ali", "Dali", "ali@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Lyrene", "Labor", "Ly.Lab@gamil.com");

		//Creating a new instance of DawsonReservation
		DawsonReservation aliRes = new DawsonReservation(customer, aRoom, 2016, 1, 1, 2016, 1, 30);
		DawsonReservation lyreneRes = new DawsonReservation(customer2, anotherRoom,2016, 2, 17, 2016, 3, 17);
		
		//Calling parameterized testGetNumberOfDaysLeft()
		System.out.println("testing getNumberOfDays() method:");
		testGetNumberOfDays("case 1: Valid Customer", aliRes, numDaysAli);
		testGetNumberOfDays("Case 2: Valid Customer #2: Different Dates", lyreneRes, numDaysLyrene);
	}

	public static void testGetNumberOfDays(String testCase, DawsonReservation aCustomer, int expectedResult ){

		System.out.println(testCase);
		System.out.println("Retuned Result: " + aCustomer.getNumberOfDays());
		System.out.println("Expected Result: " + expectedResult);
	}




	//test compareTo()
	public static void testCompareTo(){

		//Creating a new instance of Room
		DawsonRoom aRoom = new DawsonRoom(603,RoomType.SUITE);
		DawsonRoom anotherRoom = new DawsonRoom(801, RoomType.PENTHOUSE);
		
		//Creating a new instance of Customer
		DawsonCustomer customer = new DawsonCustomer("Ali", "Dali", "ali@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Lyrene", "Labor", "Ly.Lab@gamil.com");

		//Creating a new instance of DawsonReservation
		DawsonReservation aliRes = new DawsonReservation(customer, aRoom, 2016, 3, 1, 2016, 3, 30);
		DawsonReservation lyreneRes = new DawsonReservation(customer2, anotherRoom,2016, 1, 17, 2016, 1, 30);
		DawsonReservation aliRes2 = new DawsonReservation(customer, aRoom, 2016, 1, 1, 2016, 1, 30);
		DawsonReservation lyreneRes2 = new DawsonReservation(customer2, anotherRoom,2016, 3, 17, 2016, 3, 30);
		DawsonReservation aliRes3 = new DawsonReservation(customer, aRoom, 2016, 3, 1, 2016, 3, 30);
		
		//calling parameterized testCompareTo() method:
		System.out.println("testing CompareTo() method:");
		testCompareTo("case 1: reserv1 is bigger than reserv2 (checkIn bigger)", aliRes, lyreneRes, "positive Number");
		testCompareTo("Case 2: reserv1 is smaller than reserv2 ()checkIn smaller",lyreneRes, aliRes,"negative Number");
		testCompareTo("Case 3: reserv1 is smaller than reserv2 ()checkIn is equal But Room is smaller",aliRes2, lyreneRes2,"negative Number");
		testCompareTo("Case 4: reserv1 is smaller than reserv2 ()checkIn is equal But Room is bigger", lyreneRes2, aliRes2, "positive Number");
		testCompareTo("case 5: reserv1 is equal reserv2 (both checkIn date and Room are equal", aliRes, aliRes3,"0");
	}

	public static void testCompareTo(String testCase, DawsonReservation reserv1, DawsonReservation reserv2, String expectedResult){

		System.out.println(testCase);
		System.out.println("Returned Result: " + reserv1.compareTo(reserv2));
		System.out.println("Expected Resutl: " + expectedResult);
	}




	//test equals method
	public static void testEquals(){

		//Creating a new instance of Room
		DawsonRoom aRoom = new DawsonRoom(603,RoomType.SUITE);
		DawsonRoom anotherRoom = new DawsonRoom(801, RoomType.PENTHOUSE);

		//Creating a new instance of Customer
		DawsonCustomer customer = new DawsonCustomer("Ali", "Dali", "ali@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Lyrene", "Labor", "Ly.Lab@gamil.com");

		//Creating a new instance of DawsonReservation
		DawsonReservation aliRes = new DawsonReservation(customer, aRoom, 2016, 3, 1, 2016, 3, 30);
		DawsonReservation lyreneRes = new DawsonReservation(customer2, anotherRoom,2016, 1, 17, 2016, 1, 30);
		DawsonReservation aliRes2 = new DawsonReservation(customer, aRoom, 2016, 1, 1, 2016, 1, 30);
		DawsonReservation lyreneRes2 = new DawsonReservation(customer2, anotherRoom,2016, 3, 17, 2016, 3, 30);
		DawsonReservation aliRes3 = new DawsonReservation(customer, aRoom, 2016, 3, 1, 2016, 3, 30);

		//Calling parameterized 
		System.out.println("testing equals() method:");
		testEquals("Case 1: Same Reservation Object", aliRes, aliRes, "true");
		testEquals("Case 2: Different Reservation", aliRes, lyreneRes,"False");		
	}

	public static  void testEquals(String testCase, DawsonReservation res1, DawsonReservation res2, String expectedResult){

		System.out.println(testCase);
		System.out.println("Returned Result: " + res1.equals(res2));//print returned result
		System.out.println("Expected Result: " + expectedResult);//print expected Result
	}




	//test hashCode()
	public static void testHashCode(){

		//Creating a new instance of Room
		DawsonRoom aRoom = new DawsonRoom(603,RoomType.SUITE);
		DawsonRoom anotherRoom = new DawsonRoom(801, RoomType.PENTHOUSE);

		//Creating a new instance of Customer
		DawsonCustomer customer = new DawsonCustomer("Ali", "Dali", "ali@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Lyrene", "Labor", "Ly.Lab@gamil.com");

		//Creating a new instance DawsonReservation
		DawsonReservation aliRes = new DawsonReservation(customer, aRoom, 2016, 3, 1, 2016, 3, 30);
		DawsonReservation lyreneRes = new DawsonReservation(customer2, anotherRoom,2016, 1, 17, 2016, 1, 30);
		DawsonReservation aliRes2 = aliRes;

		//Calling parameterized testHashCode()
		System.out.println("testing hashCode() method:");
		testHashCode("Case 1: Same Reservations.", aliRes, aliRes2, "Same Hash code");
		testHashCode("Case 2: different Reservations.", aliRes, lyreneRes, "Different Hash code");
	}

	public static void testHashCode(String testCase, DawsonReservation res1, DawsonReservation res2, String expectedResult){

		System.out.println(testCase);
		System.out.println("Returned Result: " + res1.hashCode() + " second HashCode: " + res2.hashCode());//Checking both hashCodes
		System.out.println("Expected Result: " + expectedResult);//Expected result
	}




	//test overlap()
	public static void testOverlap(){

		//Creating a new instance of Room
		DawsonRoom aRoom = new DawsonRoom(603,RoomType.SUITE);
		DawsonRoom anotherRoom = new DawsonRoom(801, RoomType.PENTHOUSE);

		//Creating a new instance of Customer
		DawsonCustomer customer = new DawsonCustomer("Ali", "Dali", "ali@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Lyrene", "Labor", "Ly.Lab@gamil.com");

		//Creating a new instance of DawsonReservation
		DawsonReservation aliRes = new DawsonReservation(customer, aRoom, 2016, 3, 1, 2016, 3, 30);
		DawsonReservation lyreneRes = new DawsonReservation(customer2, aRoom,2016, 3, 4, 2016, 3, 20);
		DawsonReservation kimRes = new DawsonReservation(customer, aRoom, 2016, 1, 4, 2016, 2, 17);
		DawsonReservation lyreneRes2 = new DawsonReservation(customer2, anotherRoom,2016, 3, 17, 2016, 3, 30);

		//Calling parameterized testOverlap()
		System.out.println("testing overlap() method: ");
		testOverlap("case 1: checkIn Date of first Reservation is before and "
				+ "checkOut date is after second Reservation", aliRes, lyreneRes, true);
		testOverlap("case 2: checkIn date of second Reservation is before and "
				+"checkOut Date is after the first Reservation.", lyreneRes, aliRes, true);
		testOverlap("case 3: Different Rooms", aliRes, lyreneRes2, false);
		testOverlap("case 4: CheckIn and CheckOut Dates don't over lap", aliRes, kimRes, false);
	}

	public static void testOverlap(String testCase, DawsonReservation res1, DawsonReservation res2, boolean expectedResult){

		System.out.println(testCase);
		System.out.println("Returned Result: " + res1.overlap(res2));//returned result
		System.out.println("expected result: " + expectedResult);//expected result
	}




//Test DawsonReservation() constructor
	public static void testConstructor(){

		//creating a new instance of Room
		DawsonRoom aliRoom = new DawsonRoom(603, RoomType.SUITE);
		
		//Creating a new instance of Customer
		DawsonCustomer ali = new DawsonCustomer("Ali", "Dali", "ali.dali@gmail.com");

		//Calling parameterized testConstructor()
		System.out.println("testing Constructor");
		testConstructor("Case 1: Valid Input", ali, aliRoom, 2016, 3, 1, 2016, 3, 30, true);
		testConstructor("Case 2: inValid Input- checkInDate Month not between 1 --> 12 ", ali, aliRoom, 2016, 13, 1, 2016, 3, 30, false);
		testConstructor("Case 3: inValid Input- checkInDate, Date not between 1 --> 31", ali, aliRoom, 2016, 3, 45, 2016, 3, 30, false);
		testConstructor("Case 4: inValid Input- checkOAutDate, Month not between 1 --> 12", ali, aliRoom, 2016, 3, 1, 2016, 0, 30, false);
		testConstructor("Case 5: inValid Input- checkOutDate, Day not between 1 --> 31", ali, aliRoom, 2016, 3, 1, 2016, 3, 0, false);
		testConstructor("Case 6: inValid Input- checkInDate is after checkOutDate", ali, aliRoom, 2016, 4, 1, 2016, 3, 30, false);
	}

	public static void testConstructor(String testCase, Customer aCustomer, Room aRoom, int inYear, int inMonth, int inDay,
			int outYear, int outMonth, int outDay, boolean expectedResult){

		System.out.println(testCase);
		try{
			DawsonReservation rd = new DawsonReservation(aCustomer, aRoom, inYear, inMonth, inDay, outYear, outMonth, outDay);
			System.out.println("DawsonReservation was created");
			if(!expectedResult){
				System.out.println("ERROR: it should have failed!!");
			}
		}catch(IllegalArgumentException e){
			if(expectedResult){
				System.out.println("ERROR: it shouldve succeded");
			}
		}catch(Exception c){
			System.out.println("unexpected exception here");
			if(expectedResult){
				System.out.println("ERROR: this should have succeded");
			}
		}
	}

}//end of DawsonReservationTest()
