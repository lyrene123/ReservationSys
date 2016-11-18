package groupLAPD.hotel.business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;
import groupLAPD.hotel.data.ReservationListDB;
import groupLAPD.hotel.data.SequentialTextFileList;
import groupLAPD2016.util.ListUtilities;

public class DawsonHotelAllocationPolicyTest {

	public static void main(String[] args) {
		
		testGetAvailableRoom();
		//testMax();
	}

	private static void setup()
	{
		String roomPath = "datafiles" + File.separator + "rooms.txt";
		File roomTxt = new File(roomPath);
		Scanner roomReader = null;
		
		String[] rooms = new String[49];
		int index = 0;
		try {
			roomReader = new Scanner(roomTxt);				
			while(roomReader.hasNextLine()){
				rooms[index] = roomReader.nextLine();
				index++;
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally{
			roomReader.close();
		}
		
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
		reservs [0] = "raj@aing.ru*2016*10*10*2016*10*15*101";
 		reservs [1] = "d@zzz.com*2016*10*12*2016*10*15*102";
		reservs [2] = "pengkim@abc.ca*2016*10*12*2016*10*20*103";
		reservs [3] = "daniel.cavalcati@hotmail.com*2016*10*12*2016*10*22*104";
		reservs [4] = "aliDali@mail.com*2016*10*5*2016*10*10*601";
		reservs [5] = "lyrence.l.labor@yahoo.com*2016*10*10*2016*10*15*602";
		reservs [6] = "123Jonh.cena@321.com*2016*10*7*2016*10*9*603";
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
		LocalDate checkin = LocalDate.parse("2016-Oct-05", dtf);
		LocalDate checkout = LocalDate.parse("2016-Oct-15", dtf);
		
		testGetAvailableRoom("Case 1: ", checkin, checkout, RoomType.NORMAL, allocation, reservation);
		
		teardown();
	}

	private static void testGetAvailableRoom(String testCase, LocalDate checkin
			, LocalDate checkout, RoomType roomType
			, DawsonHotelAllocationPolicy allocation, ReservationDAO reservation){
		
		System.out.println();		
		System.out.println("\t" + testCase);
				
		allocation = new DawsonHotelAllocationPolicy(reservation);
		Optional<Room> availableRoom = allocation.getAvailableRoom(checkin, checkout, roomType);
		
		System.out.println("\t\t" + "Checkin date : " + checkin);
		System.out.println("\t\t" + "Checkout date : " + checkout);
		System.out.println("\t\t" + "Availabe room : " + availableRoom.toString());
		
		System.out.println();
	}
}
