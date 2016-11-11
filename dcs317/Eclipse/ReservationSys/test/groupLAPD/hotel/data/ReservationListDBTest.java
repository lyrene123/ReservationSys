/**
 * 
 */
package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLAPD.hotel.business.DawsonCustomer;
import groupLAPD.hotel.business.DawsonHotelFactory;
import groupLAPD.hotel.business.DawsonReservation;
import groupLAPD.hotel.business.DawsonRoom;
import groupLAPD2016.util.ListUtilities;

/**
 * 
 * 
 * @author Lyrene Labor, Daniel Cavalcanti
 *
 */
public class ReservationListDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testOneParamConst();
		testTwoParamConst();
		testToString();
		testAdd();
		/**ListPersistenceObject file = new 
				SequentialTextFileList("datafiles/database/rooms.txt", 
										"datafiles/database/customers.txt",
										"datafiles/database/reservations.txt");
		ReservationListDB reserv = new ReservationListDB(file);
		System.out.println(reserv.toString());
		
		
		DawsonCustomer c1 = new DawsonCustomer("LYRENE", "LABOR", "leaveMeAlone@live.com");
		DawsonCustomer c2 = new DawsonCustomer("WATERMELON", "LABOR", "StopAndLeave@live.com");
		DawsonRoom room1 = new DawsonRoom(601, RoomType.SUITE);
		DawsonRoom room2 = new DawsonRoom(101, RoomType.NORMAL);
		DawsonReservation r1 = new DawsonReservation(c1,room1, 2016, 
				9, 9, 2016, 10, 19);
		DawsonReservation r2 = new DawsonReservation(c2,room2, 2015, 
				10, 11, 2015, 10, 12);
		System.out.println("---------------------------");
		System.out.println();
		System.out.println("JUST A TEST: " + r1.compareTo(r2));
		
		
		try{
			reserv.add(r1);
		} catch(DuplicateReservationException e){
			System.out.println("ERROR: " + e.getMessage());
		}
		System.out.println(reserv.toString());*/
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
		
		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
			ListUtilities.saveListToTextFile(reservs2, 
					"testfiles/testReservations2.txt");
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
	}
	
	private static void testOneParamConst(){
		System.out.println("TESTING THE 1 PARAM CONTRUCTOR");
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
		System.out.println("TESTING THE 2 PARAM CONTRUCTOR");
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
		System.out.println("TESTING THE TOSTRING ");
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
		System.out.println("TESTING THE ADD METHOD ");
		setupReservations();
		DawsonCustomer c1 = new DawsonCustomer("LYRENE", "LABOR", "leaveMeAlone@live.com");
		DawsonRoom room1 = new DawsonRoom(404, RoomType.NORMAL);
		DawsonReservation r1 = new DawsonReservation(c1,room1, 2020, 
				9, 9, 2020, 9, 10);
		testAdd("case 1: testing with good merged reservation file"
							+ " from datafiles/database folder", 
							"datafiles/database/rooms.txt",
							"datafiles/database/customers.txt",
							"datafiles/database/reservations.txt",
							r1,true);
		testAdd("case 2: testing with another reservation file"
							+ " testfiles/testReservations.txt", 
				"datafiles/database/rooms.txt",
				"datafiles/database/customers.txt",
				"testfiles/testReservations.txt",
				r1,true);
		testAdd("case 3: testing with another reservation file"
				+ " testfiles/testReservations2.txt", 
	"datafiles/database/rooms.txt",
	"datafiles/database/customers.txt",
	"testfiles/testReservations2.txt",
	r1,true);
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

}
