package groupLAPD.hotel.data;

import groupLAPD2016.util.ListUtilities;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class SortMergeApp {

	public static void main(String[] args){	

		String path = "datafiles"+File.separator;
		String property = System.getProperty("user.dir") 
				+ File.separator + path;
		System.out.println(property);
		String roomFile = path + "rooms.txt";
		
		
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
				
	
		String path2 = "datafiles"+File.separator 
				+ "unsorted" + File.separator + "customers" + File.separator;
		String property2 = System.getProperty("user.dir") 
				+ File.separator + path2;
		File customerFiles = new File(property2);
		String[] custFile =  customerFiles.list();

		
	
		Customer[] custList = null;
		for(String custfile: custFile){
			try{
				custList = HotelFileLoader.getCustomerListFromSequentialFile(path2+custfile);
				System.out.println("custLis filled");
			}catch(IOException e){
			}
			System.out.println(custList);
		}
		
		for(Customer c : custList){
			System.out.println(c);
		}
		
	
		String path3 = "datafiles"+File.separator 
				+ "unsorted" + File.separator + "reservations" + File.separator;
		String property3 = System.getProperty("user.dir")
				+ File.separator + path3;
		
		File reservationFiles = new File(property3);
		String[] resFile =  reservationFiles.list();
		
		
	
		Reservation[] resList = null;
		for(String resfile: resFile){
			try{
				resList = HotelFileLoader.getReservationListFromSequentialFile
				(property3 + resFile, custList, roomList);
				ListUtilities.sort(resList);
			}catch(IOException e){
				
			}catch(IllegalArgumentException c){
				
			}
			System.out.println(resList);
		}
		
		
		
		
		
	}
}