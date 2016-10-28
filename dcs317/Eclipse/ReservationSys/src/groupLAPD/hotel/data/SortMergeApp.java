package groupLAPD.hotel.data;

import groupLAPD2016.util.ListUtilities;

import java.io.File;
import java.io.IOException;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class SortMergeApp {

	public static void main(String[] args){	

		String path = "datafiles"+File.separator + "unsorted" + File.separator;
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
		File database =  new File("datafiles" + File.separator + "database");
		File newRoom = new File("datafiles" + File.separator  +"database"+ File.separator + "rooms.txt");
		
		try{
			database.mkdir();
			newRoom.createNewFile();
			ListUtilities.saveListToTextFile(roomList, "datafiles"+File.separator +"database"+ File.separator + "rooms.txt");
		}catch(Exception e){
			
		}

		
		String path2 = "datafiles"+File.separator 
				+ "unsorted" + File.separator + "customers" + File.separator;
		String property2 = System.getProperty("user.dir") 
				+ File.separator + path2;
		
		String sortedPath = System.getProperty("user.dir") + File.separator + "datafiles"
								+ File.separator;
		
		File customerFiles = new File(property2);
		String[] custFile =  customerFiles.list();

		
		File sorted = new File(sortedPath + "sorted");
		try{
			sorted.mkdir();
		}catch(Exception e){
			
		}
	
		Customer[] custList = null;
		for(String c: custFile){
			try{
				custList = HotelFileLoader.getCustomerListFromSequentialFile(path2+ c);
				ListUtilities.sort(custList);
				String fileName = sorted+File.separator+"sorted"+c;
				ListUtilities.saveListToTextFile(custList, fileName);
			}catch(IOException e){
			
			}
		}
		
		
		
		
		
	
		String path3 = "datafiles"+File.separator 
				+ "unsorted" + File.separator + "reservations" + File.separator;
		String property3 = System.getProperty("user.dir")
				+ File.separator + path3 + File.separator;
		
		File reservationFiles = new File(property3);
		String[] resFile =  reservationFiles.list();
		
		Customer[] cust1 = null;
		try{
			cust1 = HotelFileLoader.getCustomerListFromSequentialFile(path2+ "customers1.txt");
		}catch(IOException e){
			
		}
		
		
	
		for(int i = 0 ; i<custList.length; i++){
		}
		Reservation[] resList = null;
		//for(String r: resFile){
			try{
				resList = HotelFileLoader.getReservationListFromSequentialFile
				(path3 + "reservations1.txt", cust1, roomList);
				ListUtilities.sort(resList);
				String fileName = sorted+File.separator+"sorted"+"reservations1.txt";
				ListUtilities.saveListToTextFile(resList, fileName);
			}catch(IOException e){
			}
			
			//}
		}
		
		
		
		
}