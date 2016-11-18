package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;
import groupLAPD2016.util.Utilities;


/**
 * This App class creates the files that we want to store the serialized 
 * data in.
 * @author Ali Dali
 *
 */
public class SerializedFileLoaderApp {

	public static void main(String[] args){
		
		//path to the files 
		String path = "datafiles" + File.separator + "database" 
				+ File.separator;

		//Creating an instance of SequentialTextFileList
		//to be able to get the rooms, customers and reservations List
		SequentialTextFileList loaded = new 
				SequentialTextFileList(path+"rooms.txt", path+"customers.txt", 
						path+"reservations.txt");

		//creating the files
		File roomFile = new File(path+"rooms.ser");
		File customerFile = new File(path+"customers.ser");
		File reservationFile = new File(path+"reservations.ser");

		//creating the files
		try{
			roomFile.createNewFile();
			customerFile.createNewFile();
			reservationFile.createNewFile();
		}catch(Exception e){
			System.out.println(e.getMessage());		
		}
		
		//calling helper method
		serializeObj(loaded, roomFile, customerFile, reservationFile);

	}
	// helper method that serializes the files using Utilities class
	private static void serializeObj(SequentialTextFileList loaded,
			File roomFile, File custFile, File resFile){
		//try to serialize 
		try{
			Utilities.serializeObject(loaded.getRoomDatabase(), roomFile.toString());
			Utilities.serializeObject(loaded.getCustomerDatabase(), custFile.toString());
			Utilities.serializeObject(loaded.getReservationDatabase(), resFile.toString());
			//catch the exception
		}catch(IOException io){
			System.out.println(io.getMessage());
		}
	}//end of serializeObj method
}//end of class
