package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;

import groupLAPD2016.util.Utilities;

public class SerializedFileLoaderApp {

	public static void main(String[] args){
		String path = "datafiles" + File.separator + "database" 
				+ File.separator;

		SequentialTextFileList loaded = new 
				SequentialTextFileList(path+"rooms.txt", path+"customers.txt", path+"reservations.txt");

		File roomFile = new File(path+"rooms.ser");
		File customerFile = new File(path+"customers.ser");
		File reservationFile = new File(path+"reservations.ser");

		try{
			roomFile.createNewFile();
			customerFile.createNewFile();
			reservationFile.createNewFile();
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
		
		serializeObj(loaded, roomFile, customerFile, reservationFile);

	}

	public static void serializeObj(SequentialTextFileList loaded,File roomFile, File custFile, File resFile){
		try{
			Utilities.serializeObject(loaded.getRoomDatabase(), roomFile.toString());
			Utilities.serializeObject(loaded.getCustomerDatabase(), custFile.toString());
			Utilities.serializeObject(loaded.getReservationDatabase(), resFile.toString());
		}catch(IOException io){
			System.out.println(io.getMessage());
		}
	}
}
