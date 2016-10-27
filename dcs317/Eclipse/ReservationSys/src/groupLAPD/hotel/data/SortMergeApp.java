package groupLAPD.hotel.data;

import groupLAPD2016.util.ListUtilities;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class SortMergeApp {

	public static void main(String[] args){	

		String property = System.getProperty("user.dir");
		System.out.println(property);
		property += File.separator + "datafiles"+File.separator;
		String roomFile = property + "rooms.txt";
		
		Room[] roomList = null;
		try{
			roomList = HotelFileLoader.getRoomListFromSequentialFile(roomFile);
		}catch(IOException e){
			
		}

		ListUtilities.sort(roomList);
		File database =  new File(property + "database");
		File newRoom = new File(property +"database"+ File.separator + "rooms.txt");
		
		try{
			database.mkdir();
			newRoom.createNewFile();
			ListUtilities.saveListToTextFile(roomList, property +"database"+ File.separator + "rooms.txt");
		}catch(Exception e){
			
		}
				
	
		
		String property2 = System.getProperty("user.dir");
		property2 += File.separator + "datafiles"+File.separator 
				+ "unsorted" + File.separator + "customers" + File.separator;
		File customerFiles = new File(property2);
		String[] custFile =  customerFiles.list();

		
	
		Customer[] custList = null;
		for(String custfile: custFile){
			try{
				custList = HotelFileLoader.getCustomerListFromSequentialFile(property2+custfile);
				ListUtilities.sort(custList);
			}catch(IOException e){
				
			}catch(IllegalArgumentException c){
				
			}
		}
		
	
		String property3 = System.getProperty("user.dir");
		property3 += File.separator + "datafiles"+File.separator 
				+ "unsorted" + File.separator + "reservations" + File.separator;
		File reservationFiles = new File(property3);
		String[] resFile =  reservationFiles.list();
		
		for(String r : resFile){
			System.out.println(property3+ r);
		}

		
	
		Reservation[] resList = null;
		for(String resfile: resFile){
			try{
				resList = HotelFileLoader.getReservationListFromSequentialFile
				(property3 + resFile, custList, roomList);
				ListUtilities.sort(resList);
			}catch(IOException e){
				
			}catch(IllegalArgumentException c){
				
			}
		}
		
		
		
		
		
	}
}