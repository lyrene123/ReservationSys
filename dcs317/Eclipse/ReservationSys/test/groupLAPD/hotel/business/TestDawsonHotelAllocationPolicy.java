package groupLAPD.hotel.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import groupLAPD.hotel.data.ReservationListDB;
import groupLAPD.hotel.data.SequentialTextFileList;
import groupLAPD2016.util.ListUtilities;

public class TestDawsonHotelAllocationPolicy {

	public static void main(String[] args) {
		
		testGetAvailableRoom();
	}
	private static void setup()
	{
		String[] rooms = new String[8];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "103*normal";
		rooms[3] = "104*normal";
		rooms[4] = "601*suite";
		rooms[5] = "602*suite";
		rooms[6] = "602*suite";
		rooms[7] = "801*penthouse";
		
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
 		reservs [1] = "d@zzz.com*2016*10*12*2016*10*15*102";
		reservs [2] = "pengkim@abc.ca*2016*10*20*2016*10*30*103";
		reservs [3] = "daniel.cavalcati@hotmail.com*2016*11*12*2016*11*22*104";
		reservs [4] = "aliDali@mail.com*2016*12*5*2016*12*10*601";
		reservs [5] = "lyrence.l.labor@yahoo.com*2016*5*10*2016*5*15*602";
		reservs [6] = "123Jonh.cena@321.com*2016*7*7*2016*7*9*602";
		reservs [7] = "joe.mancini@mail.me*2016*10*10*2016*10*20*801";


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
			System.out.println("Error creating file in setUp()");
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

	private static void testGetAvailableRoom() {		
		setup();
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		
		ReservationListDB reservation = new ReservationListDB(file);
		System.out.println(reservation.toString());
		
		DawsonHotelAllocationPolicy allocation = new DawsonHotelAllocationPolicy(reservation);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		LocalDate checkin = LocalDate.parse("2016-Oct-12", dtf);
		LocalDate checkout = LocalDate.parse("2016-Oct-15", dtf);
		
		testGetAvailableRoom("Case 1: ", checkin, checkout, RoomType.SUITE, allocation, reservation);
		
		teardown();
	}

	private static void testGetAvailableRoom(String testCase, LocalDate checkin
			, LocalDate checkout, RoomType roomType
			, DawsonHotelAllocationPolicy allocation, ReservationDAO reservation){
		
		System.out.println();		
		System.out.println("\t" + testCase);
				
		allocation = new DawsonHotelAllocationPolicy(reservation);
		Optional<Room> availableRoom = allocation.getAvailableRoom(checkin, checkout, roomType);
		
		System.out.println(availableRoom.toString());
		
		System.out.println();
	}
}
