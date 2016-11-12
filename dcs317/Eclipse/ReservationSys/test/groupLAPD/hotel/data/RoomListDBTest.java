package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import groupLAPD2016.util.ListUtilities;

public class RoomListDBTest {

	public static void main(String[] args) {
		testGetRooms();
		testToString();
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

	private static void testGetRooms() {
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		RoomListDB db = new RoomListDB(file);
		
		System.out.println("Normal rooms:");
		List<Room> normal = db.getRooms(RoomType.NORMAL);
		for (Room r : normal)
			System.out.println(r.toString());			

		System.out.println("Suites:");
		List<Room> suite = db.getRooms(RoomType.SUITE);
		for (Room r : suite)
			System.out.println(r.toString());
		
		System.out.println("Penthouse rooms:");
		List<Room> ph = db.getRooms(RoomType.PENTHOUSE);
		for (Room r : ph)
			System.out.println(r.toString());
		
		teardown();
	}

	private static void testToString(){
		setup();
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		RoomListDB db = new RoomListDB(file);
		
		System.out.println(db.toString());

		teardown();
	}

}

