package groupLAPD.hotel.business;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
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

	private static void testGetAvailableRoom() {		
		setupReservations();
		
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
		ReservationListDB reservation = new ReservationListDB(file);
		DawsonHotelAllocationPolicy allocation = new DawsonHotelAllocationPolicy(reservation);
		
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
