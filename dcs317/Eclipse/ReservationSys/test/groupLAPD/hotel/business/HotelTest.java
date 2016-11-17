/**
 * 
 */
package groupLAPD.hotel.business;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.HotelFactory;
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
		testConstructor();

	}
	
	
	private static void setup()
	{
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "601*suite";
		rooms[3] = "801*penthouse";
		
		String[] custs = new String[8];
		custs [0] = "123Jonh.cena@321.com*Cena*John*Amex*344322624384908";
		custs [1] = "pengkim@abc.ca*pengkim*sy*mastercard*5326385350239456";
		custs [2] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs [3] = "daniel.cavalcati@hotmail.com*daniel*cavalcati**";
		custs [4] = "aliDali@mail.com*Ali*Dali*amex*344322624384908";
		custs [5] = "joe.mancini@mail.me*Joe*Mancini**";
		custs [6] = "lyrence.l.labor@yahoo.com*lyrence*labor**";
		custs [7] = "d@zzz.com*Da*Ja**";


		String[] reservs = new String[8];
		reservs [0] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [1] = "joe.mancini@mail.me*2016*10*10*2016*10*20*801";
		/*reservs [2] = "aliDali@mail.com*2016*12*5*2016*12*10*601";
		reservs [3] = "pengkim@abc.ca*2016*10*20*2016*10*30*103";
		reservs [4] = "daniel.cavalcati@hotmail.com*2016*11*12*2016*11*22*104";
		reservs [5] = "lyrence.l.labor@yahoo.com*2016*5*10*2016*5*15*602";
		reservs [6] = "123Jonh.cena@321.com*2016*7*7*2016*7*9*602";*/
 		reservs [7] = "d@zzz.com*2016*10*12*2016*10*15*102";

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
		
		testConstructor("case 1: testing with good merged reservation file",
						"testfiles/testRooms.txt",
						"testfiles/testCustomers.txt",
						"testfiles/testReservations.txt",
						DawsonHotelFactory.DAWSON, true);
		
		
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

}
