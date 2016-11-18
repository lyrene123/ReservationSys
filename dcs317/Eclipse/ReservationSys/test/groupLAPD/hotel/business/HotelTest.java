/**
 * 
 */
package groupLAPD.hotel.business;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLAPD.hotel.data.CustomerListDB;
import groupLAPD.hotel.data.ReservationListDB;
import groupLAPD.hotel.data.SequentialTextFileList;
import groupLAPD2016.util.ListUtilities;

/**
 * @author Lyrene Labor
 *
 */
public class HotelTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//testConstructor();
		//testCancel();
		testCloseHotel();
			
	}
	
	
	private static void setup()
	{
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "103*normal";
		rooms[3] = "106*normal";
		
		String[] reservs = new String[8];
		reservs [0] = "alonso@mclaren.com*2016*9*13*2016*9*27*101";
		reservs [1] = "mtahar1998@gmail.com*2016*10*15*2016*10*16*101";
		reservs [2] = "reallyC00L@gmail.com*2017*12*28*2018*1*3*101";
		reservs [3] = "david_ferguson1@google.ca*2015*9*24*2015*9*29*102";
		reservs [4] = "Adi_Mutu@cocacola.com*2016*7*29*2016*8*4*102";
		reservs [5] = "DarrenHart@gmail.com*2016*4*24*2016*4*30*103";
		reservs [6] = "Mr_Unidentified@yahoo.ca*2017*11*13*2017*11*14*103";
		reservs [7] = "brian.robertson33@outlook.com*2015*11*1*2015*11*10*106";


		String[] custs = new String[8];
		custs [0] = "Adi_Mutu@cocacola.com*Adrian*Mutu*mastercard*5537971109386493";
		custs [1] = "DarrenHart@gmail.com*Darren*Hart*visa*4323736728112567";
		custs [2] = "mtahar1998@gmail.com*Tahar*Mohamed*amex*346753761038329";
		custs [3] = "reallyC00L@gmail.com*Kanye*West*amex*346369558266849";
		custs [4] = "david_ferguson1@google.ca*David*Ferguson*visa*4024007160303235";
		custs [5] = "alonso@mclaren.com*Fernando*Alonso*visa*4556054416014030";	
		custs [6] = "brian.robertson33@outlook.com*Brian*Robertson*visa*4929177939955676";
		custs [7] = "Mr_Unidentified@yahoo.ca*John*Doe*mastercard*5572941705007864";

		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(rooms, 
					"testfiles/testRooms.txt");
			ListUtilities.saveListToTextFile(custs, 
					"testfiles/testCustomers.txt");
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
		}
		catch(IOException io){
			System.out.println
			("Error creating file in setUp()");
		}
	}	
	
	private static void teardown() {
		File theFile = new File("testfiles/testRooms.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testCustomers.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
	}
	
	
	private static void testConstructor(){
		System.out.println("--------TESTING THE HOTEL CONSTRUCTOR--------");
		setup();
		
		testConstructor("case 1: testing with good merged reservation, room and customer file",
						"testfiles/testRooms.txt",
						"testfiles/testCustomers.txt",
						"testfiles/testReservations.txt",
						DawsonHotelFactory.DAWSON, true);
		testConstructor("case 2: testing with an un-existing file",
				"testfiles/BLAHBLAH.txt",
				"testfiles/testCustomers.txt",
				"testfiles/testReservations.txt",
				DawsonHotelFactory.DAWSON, false);
		
		teardown();
		
	}
	
	private static void testConstructor(String test, String rooms, String cust, 
			String reserv, HotelFactory fact, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\tThe ListPersistenceObject instance was created");
			ReservationListDB r1 = new ReservationListDB(file, fact);
			System.out.println("\tThe ReservationListDB instance was created");
			CustomerListDB c1 = new CustomerListDB(file, fact);
			System.out.println("\tThe CustomerListDB instance was created");
			
			Hotel hotel = new Hotel(DawsonHotelFactory.DAWSON, c1, r1);
			System.out.println("\tThe Hotel instance was created");
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
	
	
	private static void testCancel(){
		System.out.println("--------TESTING THE HOTEL cancelReservation METHOD--------");
		setup();
		
		DawsonCustomer badCustomer = new DawsonCustomer
				("Joe","Mancini","joe.mancini@mail.me");
		DawsonRoom badRoom = new DawsonRoom (801, RoomType.PENTHOUSE);
		DawsonReservation reserv1 = new DawsonReservation
				(badCustomer, badRoom, 2016, 10,10, 2016, 10, 20);
		testCancel("Case 1: testing with an not existing reservation", 
					"testfiles/testRooms.txt",
					"testfiles/testCustomers.txt",
					"testfiles/testReservations.txt",
					DawsonHotelFactory.DAWSON,reserv1,false);
		
		DawsonCustomer goodCustomer = new DawsonCustomer
				("Kanye","West","reallyC00L@gmail.com");
		DawsonRoom goodRoom = new DawsonRoom (101, RoomType.NORMAL);
		DawsonReservation reserv2 = new DawsonReservation
				(goodCustomer, goodRoom, 2017, 12,28, 2018, 1, 3);
		testCancel("Case 2: testing with an existing reservation", 
				"testfiles/testRooms.txt",
				"testfiles/testCustomers.txt",
				"testfiles/testReservations.txt",
				DawsonHotelFactory.DAWSON,reserv2,true);
		
		teardown();
	}
	
	private static void testCancel(String test, String rooms, String cust, 
			String reserv, HotelFactory fact, Reservation aReserv, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\tThe ListPersistenceObject instance was created");
			ReservationListDB r1 = new ReservationListDB(file, fact);
			System.out.println("\tThe ReservationListDB instance was created");
			CustomerListDB c1 = new CustomerListDB(file, fact);
			System.out.println("\tThe CustomerListDB instance was created");
			
			Hotel hotel = new Hotel(DawsonHotelFactory.DAWSON, c1, r1);
			System.out.println("\tThe Hotel instance was created");
			
			System.out.println("\n\tContent of the reservation database: ");
			System.out.println(r1);
			
			hotel.cancelReservation(aReserv);
			System.out.println("\n\tContent of the reservation database after cancel: ");
			System.out.println(r1);
			
			if (!expected)
				System.out.print("\n\tError! You expected this case to "
						+ "fail but it didn't. ");
			}catch (Exception x) {
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expected)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't.");
		}
		System.out.println("\n");		
	}
	
	
	
	private static void testCloseHotel(){
		System.out.println("---------TESTING THE closeHotel METHOD--------");
		setup();
		
		DawsonCustomer c1 = new DawsonCustomer
				("Kanye","West","reallyC00L@gmail.com");
		DawsonCustomer c2 = new DawsonCustomer("Adrian", "Mutu", 
				"Adi_Mutu@cocacola.com");
		DawsonRoom room1 = new DawsonRoom(101, RoomType.NORMAL);
		DawsonRoom room2 = new DawsonRoom(102, RoomType.NORMAL);
		DawsonReservation r1 = new DawsonReservation
				(c1, room1, 2017, 12,28, 2018, 1, 3);
		DawsonReservation r2 = new DawsonReservation(c2,room2, 2016, 
				7, 29, 2016, 8, 4);
		
		testCloseHotel("case 1: testing with 2 already existing reservations added",
				"testfiles/testRooms.txt",
				"testfiles/testCustomers.txt",
				"testfiles/testReservations.txt",
				r1,r2,false);
		
		/*testDisconnect("case 2: testing with testReservations2.txt file"
				+ " from testFiles folder.", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations2.txt",
				r1,r2,true);
		testDisconnect("case 3: testing with testReservations3.txt file"
				+ " from testFiles folder.", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations3.txt",
				r1,r2,true);*/
		teardown();
	}
	
	
	
	private static void testCloseHotel(String test, String rooms, String cust, 
			String reserv,Reservation r2, Reservation r3, boolean expected){
		System.out.println("   " + test);
		try{
			ListPersistenceObject file = new 
					SequentialTextFileList(rooms,cust,reserv);
			System.out.println("\n\tThe ListPersistenceObject instance was created");		
			ReservationListDB r1 = new ReservationListDB(file);
			System.out.println("\tThe ReservationListDB instance was created");
			CustomerListDB c1 = new CustomerListDB(file);
			System.out.println("\tThe CustomerListDB instance was created");
			
			Hotel hotel = new Hotel(DawsonHotelFactory.DAWSON, c1, r1);
			System.out.println("\tThe Hotel instance was created");
			
			System.out.println("\n\tNow adding 2 new reservations: "
					+ "\n\t" + r2.toString()
					+"\n\t" + r3.toString());	
			r1.add(r2);
			r1.add(r3);
			
			System.out.println("\n\tContent of database after adding: ");
			System.out.println("\t" + r1.toString());
			
			System.out.println("\n\tNow closing Hotel");
			hotel.closeHotel();
			ReservationListDB saved = new ReservationListDB(file);
			
			System.out.println("\n\tDatabase content after opening Hotel:");
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


}
