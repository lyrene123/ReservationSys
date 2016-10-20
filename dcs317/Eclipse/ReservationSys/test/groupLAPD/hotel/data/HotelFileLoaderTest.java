package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/**
 * 
 * @author Lyrene Labor
 *
 */
public class HotelFileLoaderTest {
	
	public static void main(String[] args) throws IOException {
		
		String property = System.getProperty("user.dir");
		property += File.separator + "datafiles"+File.separator;
		System.out.println(property+"rooms.txt");
		Room[] roomList = 
				HotelFileLoader.getRoomListFromSequentialFile
				(property+"rooms.txt");
		for(Room r: roomList){
			System.out.println(r);
		}
		
		String property2 = System.getProperty("user.dir");
		property2 += File.separator + "datafiles"+File.separator;
		System.out.println(property2+"customersLAPD.txt");
		Customer[] CustomerList =
				HotelFileLoader.getCustomerListFromSequentialFile
				(property2+"customersLAPD.txt");
		for(Customer c: CustomerList){
			System.out.println(c);
		}
		
		String property3 = System.getProperty("user.dir");
		property3+= File.separator + "datafiles"+File.separator;
		System.out.println(property3+"reservationsLAPD.txt");
		Reservation[] reservList = 
				HotelFileLoader.getReservationListFromSequentialFile
				(property3+"reservationsLAPD.txt", CustomerList, roomList);
		for(Reservation r: reservList){
			System.out.println(r);
		}
	}
	
	public static void TestGetRoomListFromSequentialFile(){
		System.out.println("\nTesting the "
				+ "getRoomListFromSequentialFile method");
		
		String property = System.getProperty("user.dir");
		property += File.separator + "datafiles"+File.separator;
		String goodFile = property+"rooms.txt";
		TestGetRoomListFromSequentialFile
			("Case 1: Room file with correct entries", 
					true, goodFile);
		
	}
	
	public static void TestGetRoomListFromSequentialFile
				(String testCase, boolean expectedResult, String filename){
		
		System.out.println("   " + testCase);
		
		
	}
	
	

}
