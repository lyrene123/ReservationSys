package groupLAPD.hotel.data;

import java.io.File;
import java.io.FileNotFoundException;
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
		
		TestGetRoomListFromSequentialFile();
		TestGetCustomerListFromSequentialFile();
		
		/**String property3 = System.getProperty("user.dir");
		property3+= File.separator + "datafiles"+File.separator;
		System.out.println(property3+"reservationsLAPD.txt");
		Reservation[] reservList = 
				HotelFileLoader.getReservationListFromSequentialFile
				(property3+"reservationsLAPD.txt", CustomerList, roomList);
		for(Reservation r: reservList){
			System.out.println(r);
		}*/
	}
	
	public static void TestGetRoomListFromSequentialFile()
			throws IOException{
		System.out.println("\nTesting the "
				+ "getRoomListFromSequentialFile method");
		
		String property = System.getProperty("user.dir");
		property += File.separator + "datafiles"+File.separator;
		String goodRoomFile = property+"rooms.txt";
		TestGetRoomListFromSequentialFile
			("Case 1: Room file with all correct entries", 
					true, goodRoomFile);
		
		property = System.getProperty("user.dir")+File.separator+"groupLAPD"+
				File.separator+"hotel"+File.separator+"data"+
				File.separator+"datafilesTest"+File.separator;
		String badRoomFile1 = property+"badRoomFile1.txt";
		TestGetRoomListFromSequentialFile("Case 2: Room file "
				+ "with non existing room type bleh", false, badRoomFile1);
		
	}
	
	public static void TestGetRoomListFromSequentialFile
				(String testCase, boolean expectedResult, String filename) 
						throws IOException{
		
		System.out.println("   " + testCase);
		System.out.println("    Filename: " + filename);
		
		try {
			Room[] roomList = HotelFileLoader.getRoomListFromSequentialFile
				(filename);
			System.out.println("    Here is the room data file content");
			for(Room r: roomList){
				System.out.println("\t"+r);
			}
			if(!expectedResult){
				System.out.println("ERROR! You expected this test case to"
						+ " fail but it didn't");
			}
		}
		catch(IllegalArgumentException e){
			System.out.print("\t"+ e.getMessage());
			if(expectedResult){
				System.out.println("ERROR! You expected this test case to "
						+ "to succeed but it didn't!");
			}
		}
		catch(Exception x){
			System.out.print("\n\tError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expectedResult)
				System.out.print("\n\tYou expected this case to succeed but"
						+ " it didn't ");
		}
		
		System.out.println();	
	}
	
	public static void TestGetCustomerListFromSequentialFile()
			throws IOException{
		System.out.println("\nTesting the "
				+ "getCustomerListFromSequentialFile method");
		
		String property = System.getProperty("user.dir");
		property += File.separator + "datafiles"+File.separator;
		String goodCustFile = property+"customersLAPD.txt";
		TestGetCustomerListFromSequentialFile
			("Case 1: Customer file with all correct entries", 
					true, goodCustFile);
		
		
	}
	
	public static void TestGetCustomerListFromSequentialFile
	(String testCase, boolean expectedResult, String filename) 
			throws IOException{

		System.out.println("   " + testCase);
		System.out.println("    Filename: " + filename);

		try {
			Customer[] CustomerList =
					HotelFileLoader.getCustomerListFromSequentialFile
					(filename);
			System.out.println("    Here is the customer data file content:");
			for(Customer c: CustomerList){
				System.out.println("\t"+c);
			}
			if(!expectedResult){
				System.out.println("\nERROR! You expected this test case to"
						+ " fail but it didn't");
			}
		}
		catch(IllegalArgumentException e){
			System.out.print("\t"+ e.getMessage());
			if(expectedResult){
				System.out.println("\nERROR! You expected this test case to "
						+ "to succeed but it didn't!");
			}
		}
		catch(Exception x){
			System.out.print("\nError! There was an unexpected exception"
					+ " type " + x.getClass() +  " "  + 				
					x.getMessage());
			if (expectedResult)
				System.out.print("\nYou expected this case to succeed but"
						+ " it didn't ");
		}

		System.out.println();	
}
	
	
	

}
