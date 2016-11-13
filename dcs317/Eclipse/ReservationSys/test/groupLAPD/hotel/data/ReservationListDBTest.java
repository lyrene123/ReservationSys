
package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLAPD.hotel.business.DawsonCustomer;
import groupLAPD.hotel.business.DawsonHotelFactory;
import groupLAPD.hotel.business.DawsonReservation;
import groupLAPD.hotel.business.DawsonRoom;
import groupLAPD2016.util.ListUtilities;

/**
 * 
 * ReservationListDBTest class contains all unit test
 * methods used to fully all methods of the ReservationLIstDB 
 * class in order to make sure that they are working as 
 * expected
 * @version November 2016
 * @author Lyrene Labor, Daniel Cavalcanti, Ali Dali
 *
 */
public class ReservationListDBTest {

	/**
	 * The main method will call all unit test methods that 
	 * will test all methods from the ReservationListDB class
	 * @param args - array
	 */
	public static void main(String[] args) {
		
		//testOneParamConst();
		//testTwoParamConst();
		//testToString();
		//testAdd();
		//testGetReservation();
		//testCancel();
		//testDisconnect();
		//testGetFreeRooms();
		//testGetReservedRooms();
		//testOverloadedGeFreeRooms();
		testClearAllPast();

	}
	
	private static void setupReservations(){
		String[] reservs = new String[5];
		reservs [0] = "david_ferguson1@google.ca*2015*9*24*2015*9*29*102";
		reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*205";
		reservs [2] = "mamadou42@dawsoncollege.qc.ca*2016*1*1*2016*1*3*404";
		reservs [3] = "Du_Tan@peasant.mw*2016*3*5*2016*3*8*507";
		reservs [4] = "chrissytocool404@hitmall.com*2016*10*13*2016*10*27*701";
		
		String[] reservs2 = new String[6];
		reservs2 [0] = "Mr_Unidentified@yahoo.ca*2017*11*13*2017*11*14*103";
		reservs2 [1] = "frogster@hotmail.com*2011*7*14*2011*7*21*206";
		reservs2 [2] = "mofojones@hotmail.com*2016*7*23*2016*7*28*206";
		reservs2 [3] = "nicholas.hardacre2@gmail.com*2014*8*31*2014*9*3*305";
		reservs2 [4] = "buildco@house.com*2017*2*13*2017*2*15*601";	
		reservs2 [5] = "AlexWhite@gmail.com*2009*11*3*2009*11*17*604";	
		
		String[] reservs3 = new String[2];
		reservs3 [0] = "Mr_Unidentified@yahoo.ca*2017*11*13*2017*11*14*103";
		reservs3 [1] = "AlexWhite@gmail.com*2009*11*3*2009*11*17*604";	
		
		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
			ListUtilities.saveListToTextFile(reservs2, 
					"testfiles/testReservations2.txt");
			ListUtilities.saveListToTextFile(reservs3, 
					"testfiles/testReservations3.txt");
		}catch(IOException io){
			System.out.println
			("Error creating files in setupReservations()");
		}
	}
	
	
	private static void teardown() {
		File theFile = new File("testfiles/testReservations.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations2.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations3.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
	}
	
	
	public static void testCancel(){
		System.out.println
		("--------------------Testing Cancel-------------------");
		setupReservations();
		
		DawsonCustomer goodCustomer = new DawsonCustomer
				("Ginette","Tremblay","tremgin4@alloqc.ca");
		DawsonRoom goodRoom = new DawsonRoom (304, RoomType.NORMAL);
		DawsonReservation reserv1 = new DawsonReservation
				(goodCustomer, goodRoom, 2016, 12, 1, 2016, 12, 26);
		
		testCancel("Case 1: The reservation we want to remove exists", reserv1, 
				"datafiles/database/reservations.txt",true);
		
		DawsonCustomer customer2 = new DawsonCustomer
				("Ali","Tremblay","Ali@gmail.com");
		DawsonRoom room2 = new DawsonRoom (305, RoomType.NORMAL);
		DawsonReservation reserv2 = new DawsonReservation
				(customer2, room2, 2016, 12, 1, 2016, 12, 26);
		
		testCancel("Case 2: The reservation we want to remove "
				+ "does not exist", reserv2, 
				"datafiles/database/reservations.txt",false);
		
		DawsonCustomer customer3 = new DawsonCustomer("Joe","Mancini",
				"joe.mancini@mail.me");
		DawsonRoom room3 = new DawsonRoom (205, RoomType.NORMAL);
		DawsonReservation reserv3 = new DawsonReservation(customer3, room3, 2016, 
												10, 10, 2016, 10, 20);
		testCancel("Case 3: reservation removed from "
				+ "testFiles/testReservations.txt", reserv3, 
				"testFiles/testReservations.txt",true);
		
		teardown();
		
	}
	
	
	public static void testCancel(String testCase, Reservation reserv, String rfile, 
			boolean expected){
		System.out.println(testCase);

		String custFile = "datafiles/database/customers.txt";
		String roomFile = "datafiles/database/rooms.txt";
		String reservFile = rfile;
		ReservationListDB r1 =  null;
		
		try{
		ListPersistenceObject file = new 
				SequentialTextFileList(roomFile,custFile,reservFile);
		System.out.println("\tThe ListPersistenceObject instance was created");
		r1 = new ReservationListDB(file);
		r1.cancel(reserv);
		System.out.println(reserv + " --> removed");
		if(!expected){
			System.out.println("Expected test to fail but it didnt");
		}
		}catch(Exception e){
			System.out.println(e.getMessage());
			if(expected){
				System.out.println("Expected this to succeed but it didnt");
			}
		}	
		System.out.println();
		System.out.println(r1.toString());
		System.out.println("\n");
	}
	
	
	public static void testGetReservation(){

		System.out.println
		("--------------------Testing getReservations-------------------");

		setupReservations();

		DawsonCustomer goodCustomer = new DawsonCustomer
				("Ginette","Tremblay","tremgin4@alloqc.ca");

		testGetReservations("case 1: customer is availble in databe", 
				goodCustomer,"datafiles/database/reservations.txt", true);

		DawsonCustomer badCustomer = new DawsonCustomer("Ali","Dali",
				"ali@hotmail.com");

		testGetReservations("case 2: customer is not availble in databe", 
				badCustomer,"datafiles/database/reservations.txt", true);	
		
		DawsonCustomer customer3 = new DawsonCustomer("Joe","Mancini",
				"joe.mancini@mail.me");
		
		testGetReservations("case 3: customer is not availble in databe", 
				customer3,"testFiles/testReservations.txt", true);	
		
		teardown();
	}


	public static void testGetReservations(String testCase, Customer cust, 
			String rfile, boolean expectedResult){

		System.out.println(testCase);

		List<Reservation> reservationList = null;
		String custFile = "datafiles/database/customers.txt";
		String roomFile = "datafiles/database/rooms.txt";
		String reservFile = rfile;
		ReservationListDB r1 =  null;

		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(roomFile,custFile,reservFile);
			System.out.println("\tThe ListPersistenceObject "
					+ "instance was created");
			r1 = new ReservationListDB(file);
			reservationList = r1.getReservations(cust);
			if(!expectedResult){
				System.out.println("Expected test to fail but it didnt");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			if(expectedResult){
				System.out.println("Expected this to succeed but it didnt");
			}
		}

		for(Reservation c : reservationList){
			System.out.println(c);
		}
		System.out.println("\n");
	}
	
	
	private static void testOneParamConst(){
		System.out.println("--------TESTING THE 1 PARAM CONSTRUCTOR--------");
		setupReservations();
		testOneParamConst("case 1: testing with good merged reservation file"
							+ " from datafiles/database folder", 
							"datafiles/database/rooms.txt",
							"datafiles/database/customers.txt",
							"datafiles/database/reservations.txt", true);
		testOneParamConst("case 2: testing with another reservation file"
							+ " testfiles/testReservations.txt"
				+ " from datafiles/database folder", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt", true);
		teardown();				
	}
	
	
	
	private static void testOneParamConst(String test, String rooms, String cust, 
			String reserv, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\tThe ListPersistenceObject instance was created");
			ReservationListDB r1 = new ReservationListDB(file);
			System.out.println("\tThe ReservationListDB instance was created");
			if (!expected)
				System.out.print("\n\tError! You expected this case to "
						+ "fail but it didn't. ");
		}catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expected)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't ");
		}
		System.out.println("\n");		
	}
	
	
	
	private static void testTwoParamConst(){
		System.out.println("--------TESTING THE 2 PARAM CONSTRUCTOR---------");
		setupReservations();
		testTwoParamConst("case 1: testing with good merged reservation file"
							+ " from datafiles/database folder", 
							"datafiles/database/rooms.txt",
							"datafiles/database/customers.txt",
							"datafiles/database/reservations.txt",
							DawsonHotelFactory.DAWSON, true);
		testTwoParamConst("case 2: testing with another reservation file"
							+ " testfiles/testReservations.txt"
				+ " from datafiles/database folder", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				DawsonHotelFactory.DAWSON,true);
		teardown();				
	}
	
	
	
	private static void testTwoParamConst(String test, String rooms, String cust, 
			String reserv, HotelFactory fact, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\tThe ListPersistenceObject instance was created");
			ReservationListDB r1 = new ReservationListDB(file, fact);
			System.out.println("\tThe ReservationListDB instance was created");
			if (!expected)
				System.out.print("\n\tError! You expected this case to "
						+ "fail but it didn't. ");
		}catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expected)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't ");
		}
		System.out.println("\n");		
	}
	
	
	private static void testToString(){
		System.out.println("---------TESTING THE TOSTRING-------------");
		setupReservations();
		testToString("case 1: testing with good merged reservation file"
							+ " from datafiles/database folder", 
							"datafiles/database/rooms.txt",
							"datafiles/database/customers.txt",
							"datafiles/database/reservations.txt",
							true);
		testToString("case 2: testing with another reservation file"
							+ " testfiles/testReservations.txt", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				true);
		teardown();				
	}
	
	
	
	private static void testToString(String test, String rooms, String cust, 
			String reserv, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\tThe ListPersistenceObject instance was created");
			ReservationListDB r1 = new ReservationListDB(file);
			System.out.println("\tThe ReservationListDB instance was created");
			System.out.println("\tDatabase content:");
			System.out.println("\t" + r1.toString());
			if (!expected)
				System.out.print("\n\tError! You expected this case to "
						+ "fail but it didn't. ");
		}catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expected)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't ");
		}
		System.out.println("\n");		
	}
	
	
	private static void testAdd(){
		System.out.println("-----------TESTING THE ADD METHOD------------");
		setupReservations();
		DawsonCustomer c1 = new DawsonCustomer("LYRENE", "LABOR", 
									"leaveMeAlone@live.com");
		DawsonRoom room1 = new DawsonRoom(302, RoomType.NORMAL);
		DawsonReservation r1 = new DawsonReservation(c1,room1, 2010, 
				9, 9, 2010, 9, 10);
		testAdd("case 1: testing with good merged reservation file"
				+ " from datafiles/database folder. ADDING AT THE MIDDLE", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"datafiles/database/reservations.txt",
				r1,true);
		testAdd("case 2: testing with another reservation file"
				+ " testfiles/testReservations.txt. ADDING AT THE MIDDLE", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				r1,true);
		testAdd("case 3: testing with another reservation file"
				+ " testfiles/testReservations2.txt. ADDING AT THE MIDDLE", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations2.txt",
				r1,true);
		
		DawsonRoom room2 = new DawsonRoom(101, RoomType.NORMAL);
		DawsonReservation r2 = new DawsonReservation(c1,room2, 1990, 
				9, 9, 1990, 9, 10);
		testAdd("case 4: testing with good merged reservation file"
				+ " from datafiles/database folder. ADDING AT THE BEGINNING", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"datafiles/database/reservations.txt",
				r2,true);
		testAdd("case 5: testing with another reservation file"
				+ " testfiles/testReservations.txt. ADDING AT THE BEGINNING", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				r2,true);
		testAdd("case 6: testing with another reservation file"
				+ " testfiles/testReservations2.txt. ADDING AT THE BEGINNING", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations2.txt",
				r2,true);
		
		DawsonRoom room3 = new DawsonRoom(801, RoomType.PENTHOUSE);
		DawsonReservation r3 = new DawsonReservation(c1,room3, 3000, 
				9, 9, 3000, 9, 10);
		testAdd("case 7: testing with good merged reservation file"
				+ " from datafiles/database folder. ADDING AT THE END", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"datafiles/database/reservations.txt",
				r3,true);
		testAdd("case 8: testing with another reservation file"
				+ " testfiles/testReservations.txt. ADDING AT THE END", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				r3,true);
		testAdd("case 9: testing with another reservation file"
				+ " testfiles/testReservations2.txt. ADDING AT THE END", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations2.txt",
				r3,true);
		
		DawsonRoom room4 = new DawsonRoom(101, RoomType.NORMAL);
		DawsonReservation r4 = new DawsonReservation(c1,room4, 1990, 
				9, 9, 1990, 9, 10);
		testAdd("case 10: testing with another reservation file"
				+ " testfiles/testReservations3.txt. ADDING AT THE BEGINNING", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations3.txt",
				r4,true);
		teardown();				
	}

	
	private static void testAdd(String test, String rooms, String cust, 
			String reserv, Reservation r2, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\tThe ListPersistenceObject instance was created");
			ReservationListDB r1 = new ReservationListDB(file);
			System.out.println("\tThe ReservationListDB instance was created");
			r1.add(r2);
			System.out.println("\tDatabase content after adding:");
			System.out.println("\t" + r1.toString());
			if (!expected)
				System.out.print("\n\tError! You expected this case to "
						+ "fail but it didn't. ");
		}catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expected)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't ");
		}
		System.out.println("\n");		
	}
	
	
	private static void testDisconnect(){
		System.out.println("---------TESTING THE DISCONNECT METHOD--------");
		setupReservations();
		
		DawsonCustomer c1 = new DawsonCustomer("LYRENE", "LABOR", 
				"leaveMeAlone@live.com");
		DawsonCustomer c2 = new DawsonCustomer("ALI", "DALI", 
				"buyMeAGift@live.com");
		DawsonRoom room1 = new DawsonRoom(302, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(303, RoomType.NORMAL);
		DawsonReservation r1 = new DawsonReservation(c1,room1, 2010, 
				9, 9, 2010, 9, 10);
		DawsonReservation r2 = new DawsonReservation(c2,room2, 2000, 
				9, 9, 2000, 9, 10);
		
		testDisconnect("case 1: testing with testReservations.txt file"
				+ " from testFiles folder.", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				r1,r2,true);
		
		testDisconnect("case 2: testing with testReservations2.txt file"
				+ " from testFiles folder.", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations2.txt",
				r1,r2,true);	
	}
	
	
	
	private static void testDisconnect(String test, String rooms, String cust, 
			String reserv,Reservation r2, Reservation r3, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\n\tThe ListPersistenceObject instance was created");
			
			ReservationListDB r1 = new ReservationListDB(file);
			System.out.println("\tThe ReservationListDB instance was created");
			
			System.out.println("\n\tNow adding 2 new reservations: "
					+ "\n\t" + r2.toString()
					+"\n\t" + r3.toString());	
			r1.add(r2);
			r1.add(r3);
			
			System.out.println("\n\tContent of database after adding: ");
			System.out.println("\t" + r1.toString());
			
			System.out.println("\n\tNow disconnecting and reconnecting");
			r1.disconnect();
			ReservationListDB saved = new ReservationListDB(file);
			
			System.out.println("\n\tDatabase content after disconnecting:");
			System.out.println("\t" + saved.toString());
			
			if (!expected)
				System.out.print("\n\tError! You expected this case to "
						+ "fail but it didn't. ");
		}catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expected)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't ");
		}
		System.out.println("\n");		
	}

	public static void testGetReservedRooms() {

		// searched period test 1
		LocalDate checkin1 = LocalDate.of(2016, 4, 21);
		LocalDate checkout1 = LocalDate.of(2016, 4, 25);
		// searched period test 2
		LocalDate checkin2 = LocalDate.of(2016, 5, 12);
		LocalDate checkout2 = LocalDate.of(2016, 6, 05);
		// searched period test 3
		LocalDate checkin3 = LocalDate.of(2015, 4, 21);
		LocalDate checkout3 = LocalDate.of(2015, 5, 25);
		// searched period test 4
		LocalDate checkin4 = LocalDate.of(2008, 4, 21);
		LocalDate checkout4 = LocalDate.of(2008, 6, 25);
		// searched period test 5
		LocalDate checkin5 = LocalDate.of(2016, 10, 21);
		LocalDate checkout5 = LocalDate.of(2016, 11, 12);
		// searched period test 6
		LocalDate checkin6 = LocalDate.of(2016, 8, 21);
		LocalDate checkout6 = LocalDate.of(2016, 4, 25);
		// searched period test 7
		LocalDate checkin7 = LocalDate.of(1977, 6, 21);
		LocalDate checkout7 = LocalDate.of(1977, 7, 25);
		// searched period test 8
		LocalDate checkin8 = LocalDate.of(2016, 2, 21);
		LocalDate checkout8 = LocalDate.of(2016, 4, 25);

		System.out.println("\nTESTING THE THE GETRESERVEDROOMS METHOD FROM RESERVATIONLISTDB CLASS");
		System.out.println("-----------------------------------------------------------------");
		testGetReservedRooms("Case 1: Valid Reserved Rooms search", checkin1, checkout1, true);
		testGetReservedRooms("Case 2: Valid Reserved Rooms search", checkin2, checkout2, true);
		testGetReservedRooms("Case 3: Valid Reserved Rooms search", checkin3, checkout3, true);
		testGetReservedRooms("Case 4: Valid Reserved Rooms search", checkin4, checkout4, true);
		testGetReservedRooms("Case 5: Valid Reserved Rooms search", checkin5, checkout5, true);
		testGetReservedRooms("Case 6: Invalid Reserved Rooms search: Check In After Check Out", checkin6, checkout6,
				false);
		testGetReservedRooms("Case 7: Valid Reserved Rooms search", checkin7, checkout7, true);
		testGetReservedRooms("Case 8: Valid Reserved Rooms search", checkin8, checkout8, true);

	}

	public static void testGetReservedRooms(String testCase, LocalDate checkin, LocalDate checkout, boolean expected) {

		String roomsFile = "datafiles/database/rooms.txt";
		String customersFile = "datafiles/database/customers.txt";
		String reservListFile = "datafiles/database/reservations.txt";

		try {
			System.out.println("   " + testCase);
			System.out.print("\tPeriod Searched: ");
			System.out.println("\n\t-------------------------");
			System.out.print("\tCheck In Date: ");
			System.out.print(checkin.toString());
			System.out.print("\n\tCheck Out Date: ");
			System.out.println(checkout.toString());
			System.out.println("\t---------------------------");

			ListPersistenceObject dataBaseFile = new SequentialTextFileList(roomsFile, customersFile, reservListFile);
			System.out.println("\tThe ListPersistenceObject instance was created");

			ReservationListDB ListDB = new ReservationListDB(dataBaseFile);
			List<Room> reservedRooms = ListDB.getReservedRooms(checkin, checkout);

			System.out.println("\tList of Reserved Rooms Found: ");
			System.out.println("\t-------------------------------------------------------");
			printObject(reservedRooms);
			System.out.println("\t-------------------------------------------------------");
			if (expected) {
				System.out.println("\tExpected Result! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if (!expected) {
				System.out.println("\tExpected Error! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

		} finally {
			System.out.println("_____________________________________________________________________");
		}

	}

	public static void testGetFreeRooms() {

		// searched period test 1
		LocalDate checkin1 = LocalDate.of(2016, 4, 21);
		LocalDate checkout1 = LocalDate.of(2016, 4, 25);
		// searched period test 2
		LocalDate checkin2 = LocalDate.of(2016, 5, 12);
		LocalDate checkout2 = LocalDate.of(2016, 6, 05);
		// searched period test 3
		LocalDate checkin3 = LocalDate.of(2015, 4, 21);
		LocalDate checkout3 = LocalDate.of(2015, 4, 25);
		// searched period test 4
		LocalDate checkin4 = LocalDate.of(2008, 4, 01);
		LocalDate checkout4 = LocalDate.of(2008, 4, 03);
		// searched period test 5
		LocalDate checkin5 = LocalDate.of(2008, 10, 21);
		LocalDate checkout5 = LocalDate.of(2008, 11, 12);
		// searched period test 6
		LocalDate checkin6 = LocalDate.of(2009, 8, 21);
		LocalDate checkout6 = LocalDate.of(2009, 4, 25);
		// searched period test 7
		LocalDate checkin7 = LocalDate.of(2016, 6, 21);
		LocalDate checkout7 = LocalDate.of(2017, 11, 25);
		// searched period test 8
		LocalDate checkin8 = LocalDate.of(1977, 2, 12);
		LocalDate checkout8 = LocalDate.of(2018, 2, 15);

		System.out.println("\nTESTING THE THE GETFREEROOMS METHOD FROM RESERVATIONLISTDB CLASS");
		System.out.println("-----------------------------------------------------------------");
		testGetFreeRooms("Case 1: Valid Free Rooms search", checkin1, checkout1, true);
		testGetFreeRooms("Case 2: Valid Free Rooms search", checkin2, checkout2, true);
		testGetFreeRooms("Case 3: Valid Free Rooms search", checkin3, checkout3, true);
		testGetFreeRooms("Case 4: Valid Free Rooms search", checkin4, checkout4, true);
		testGetFreeRooms("Case 5: Valid Free Rooms search", checkin5, checkout5, true);
		testGetFreeRooms("Case 6: Invalid Free Rooms search: Check In After Check Out", checkin6, checkout6, false);
		testGetFreeRooms("Case 7: Valid Free Rooms search", checkin7, checkout7, true);
		testGetFreeRooms("Case 8: Valid Free Rooms search", checkin8, checkout8, true);

	}

	public static void testGetFreeRooms(String testCase, LocalDate checkin, LocalDate checkout, boolean expected) {

		String roomsFile = "datafiles/database/rooms.txt";
		String customersFile = "datafiles/database/customers.txt";
		String reservListFile = "datafiles/database/reservations.txt";

		try {
			System.out.println("   " + testCase);
			System.out.print("\tPeriod Searched: ");
			System.out.println("\n\t-------------------------");
			System.out.print("\tCheck In Date: ");
			System.out.print(checkin.toString());
			System.out.print("\n\tCheck Out Date: ");
			System.out.println(checkout.toString());
			System.out.println("\t---------------------------");

			ListPersistenceObject dataBaseFile = new SequentialTextFileList(roomsFile, customersFile, reservListFile);
			System.out.println("\tThe ListPersistenceObject instance was created");

			ReservationListDB ListDB = new ReservationListDB(dataBaseFile);
			List<Room> freeRooms = ListDB.getFreeRooms(checkin, checkout);

			System.out.println("\tList of Reserved Rooms: ");
			System.out.println("\t-------------------------------------------------------");
			printObject(freeRooms);
			System.out.println("\t-------------------------------------------------------");
			if (expected) {
				System.out.println("\tExpected Result! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if (!expected) {
				System.out.println("\tExpected Error! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

		} finally {
			System.out.println("_____________________________________________________________________");
		}

	}

	public static void testOverloadedGeFreeRooms() {

		// searched period test 1
		LocalDate checkin1 = LocalDate.of(2016, 4, 21);
		LocalDate checkout1 = LocalDate.of(2016, 4, 25);
		// searched period test 2
		LocalDate checkin2 = LocalDate.of(2016, 5, 12);
		LocalDate checkout2 = LocalDate.of(2016, 6, 05);
		// searched period test 3
		LocalDate checkin3 = LocalDate.of(2015, 4, 21);
		LocalDate checkout3 = LocalDate.of(2015, 4, 25);
		// searched period test 4
		LocalDate checkin4 = LocalDate.of(2008, 4, 01);
		LocalDate checkout4 = LocalDate.of(2008, 4, 03);
		// searched period test 5
		LocalDate checkin5 = LocalDate.of(2008, 10, 21);
		LocalDate checkout5 = LocalDate.of(2008, 11, 12);
		// searched period test 6
		LocalDate checkin6 = LocalDate.of(2009, 8, 21);
		LocalDate checkout6 = LocalDate.of(2009, 4, 25);
		// searched period test 7
		LocalDate checkin7 = LocalDate.of(2016, 6, 21);
		LocalDate checkout7 = LocalDate.of(2017, 11, 25);
		// searched period test 8
		LocalDate checkin8 = LocalDate.of(1977, 2, 12);
		LocalDate checkout8 = LocalDate.of(2018, 2, 15);

		System.out.println("\nTESTING THE THE OVERLOADED GETFREEROOMS METHOD FROM RESERVATIONLISTDB CLASS");
		System.out.println("-----------------------------------------------------------------------------");
		testOverloadedGeFreeRooms("Case 1: Valid Free Rooms search by given Room Type", checkin1, checkout1, RoomType.SUITE, true);
		testOverloadedGeFreeRooms("Case 2: Valid Free Rooms search by given Room Type", checkin2, checkout2, RoomType.PENTHOUSE, true);
		testOverloadedGeFreeRooms("Case 3: Valid Free Rooms search by given Room Type", checkin3, checkout3, RoomType.NORMAL, true);
		testOverloadedGeFreeRooms("Case 4: Valid Free Rooms search by given Room Type", checkin4, checkout4, RoomType.SUITE, true);
		testOverloadedGeFreeRooms("Case 5: Valid Free Rooms search by given Room Type", checkin5, checkout5, RoomType.PENTHOUSE, true);
		testOverloadedGeFreeRooms("Case 6: Invalid Free Rooms search by given Room Type: Check In After Check Out", checkin6, checkout6,
				RoomType.SUITE, false);
		testOverloadedGeFreeRooms("Case 7: Valid Free Rooms search by given Room Type", checkin7, checkout7, RoomType.PENTHOUSE, true);
		testOverloadedGeFreeRooms("Case 8: Valid Free Rooms search by given Room Type", checkin8, checkout8, RoomType.SUITE, true);

	}

	public static void testOverloadedGeFreeRooms(String testCase, LocalDate checkin, LocalDate checkout,
			RoomType roomType, boolean expected) {

		String roomsFile = "datafiles/database/rooms.txt";
		String customersFile = "datafiles/database/customers.txt";
		String reservListFile = "datafiles/database/reservations.txt";

		try {
			System.out.println("   " + testCase);
			System.out.print("\tPeriod Searched: ");
			System.out.println("\n\t-------------------------");
			System.out.print("\tCheck In Date: ");
			System.out.print(checkin.toString());
			System.out.print("\n\tCheck Out Date: ");
			System.out.println(checkout.toString());
			System.out.print("\tRoom Type Searched: ");
			System.out.println(roomType.toString());
			System.out.println("\t---------------------------");

			ListPersistenceObject dataBaseFile = new SequentialTextFileList(roomsFile, customersFile, reservListFile);
			System.out.println("\tThe ListPersistenceObject instance was created");

			ReservationListDB ListDB = new ReservationListDB(dataBaseFile);
			List<Room> freeRooms = ListDB.getFreeRooms(checkin, checkout, roomType);

			System.out.println("\tList of free Rooms of Type" + " " + roomType.toString());
			System.out.println("\t-------------------------------------------------------");
			printObject(freeRooms);
			System.out.println("\t-------------------------------------------------------");
			if (expected) {
				System.out.println("\tExpected Result! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if (!expected) {
				System.out.println("\tExpected Error! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

		} finally {
			System.out.println("_____________________________________________________________________");
		}

	}
	public static void testClearAllPast() {

		System.out.println("\nTESTING THE THE clearAllPast METHOD FROM RESERVATIONLISTDB CLASS");
		System.out.println("-----------------------------------------------------------------------------");
		testClearAllPast("Case 1: Removing all Reservations whose checkout date is before the current date ", true);
	}

	public static void testClearAllPast(String testCase, boolean expected){
		String roomsFile = "datafiles/database/rooms.txt";
		String customersFile = "datafiles/database/customers.txt";
		String reservListFile = "datafiles/database/reservations.txt";

		try {
			System.out.println("   " + testCase);
			
			ListPersistenceObject dataBaseFile = new SequentialTextFileList(roomsFile, customersFile, reservListFile);
			System.out.println("\tThe ListPersistenceObject instance was created");

			ReservationListDB ListDB = new ReservationListDB(dataBaseFile);
			System.out.println("\tList of Reservations before Clearing All Past before current date");
			System.out.println("\t-----------------------------------------------------------------");
			System.out.println(ListDB.toString());
			ListDB.clearAllPast();
			System.out.println("\tList of Reservations After Clearing All Past before current date");
			System.out.println("\t-----------------------------------------------------------------");
			System.out.println(ListDB.toString());
			System.out.println("\t-----------------------------------------------------------------");
			if (expected) {
				System.out.println("\tExpected Result! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

			System.out.println("\tEnd of testeCase");

		} catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
			System.out.println();
			if (!expected) {
				System.out.println("\tExpected Error! TEST OK");
			} else {
				System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
			}

		} finally {
			System.out.println("_____________________________________________________________________");
		}
		
	}

	public static <E> void printObject(List<E> obj) {

		if (obj.isEmpty()) {
			System.out.println("\tNo room found!");
		} else if (obj.size() == 1) {
			System.out.println("\t" + obj.get(0) + " ");
		} else {
			for (int i = 0; i < obj.size(); i++) {
				if (i == 0) {
					System.out.print("\t" + obj.get(i) + " ");
					i++;
				}
				if (i % 4 != 0) {
					System.out.print("\t" + obj.get(i) + " ");
				} else {
					System.out.print("\n\t" + obj.get(i) + " ");
				}

			}

			System.out.println();
		}

	}

}

