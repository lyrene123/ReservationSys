package groupLAPD.hotel.data;

import groupLAPD2016.util.ListUtilities;

import java.io.File;
import java.io.IOException;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;

/**
 * This application class Loads, sorts and Merges an unsorted sequential
 * customer and reservation files into a sorted sequential file customer files
 * are loaded, sorted and copied into a sorted folder and after sorting they are
 * merged into a database folder.
 * 
 * 
 * @author Ali Dali
 *
 */

public class SortMergeApp {

	public static void main(String[] args) {

		Room[] roomList = null;

		savingAndSortingRoomFile(roomList);
		loadSortAndMergeCustomers();
		loadSortAndMergeReservations(roomList);

	}

	/**
	 * The savingAndSortingRoomFile method loads an unsorted room File
	 * Sorts the file and saves it in database/rooms.txt
	 * 
	 * @param roomList
	 *            Array of type of Room[].
	 * @author Ali Dali 
	 */
	private static void savingAndSortingRoomFile(Room[] roomList) {

		// path of the unsorted rooms.txt
		String roomFile = "datafiles" + File.separator + "unsorted" + File.separator + "rooms.txt";
		// handling IOException
		try {
			// Loading rooms.txt
			roomList = HotelFileLoader.getRoomListFromSequentialFile(roomFile);
		} catch (IOException e) {// catching IOException in case File does not
			// exist

		}
		// Sorting roomFile
		ListUtilities.sort(roomList);
		// creating a file object with the specified path, to create
		// the directory database
		File database = new File("datafiles" + File.separator + "database");
		// creating a file object with the specified path, to create
		// the file rooms.txt in the database directory
		File newRoom = new File(database + File.separator + "rooms.txt");

		// try to create the directory and file
		try {
			database.mkdir();// create database directory
			newRoom.createNewFile();// create rooms file
			// saving the array roomList to file rooms.txt
			ListUtilities.saveListToTextFile(roomList, newRoom.toString());
		} catch (Exception e) {// catch any Exception
			System.out.println(e.getMessage() + "File could not be craeted");
		}
	}
	
	/**
	 * The loadSortAndMergeCustomers method loads unsorted customer
	 * files, sorts them and stores them in sorted/customers/"fileName"Sorted.txt
	 * After the sorting is don eit will merge all the sorted customer files in
	 * database/customers.txt
	 * 
	 * @author Ali Dali 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void loadSortAndMergeCustomers() {

		boolean isCustomerFirst = true;
		// setting the path to a String
		String relativePath = "datafiles" + File.separator + "unsorted" + File.separator + "customers" + File.separator;
		// setting the full path to the String property2
		String fullPath = System.getProperty("user.dir") + File.separator + relativePath;

		// storing path to string
		String sortedPath = System.getProperty("user.dir") + File.separator + "datafiles" + File.separator + "sorted";

		// creating a File object using the full path
		File customerFiles = new File(fullPath);
		// stores all the file names in a string array
		String[] custFile = customerFiles.list();

		// creating a File object using sorted path
		File sorted = new File(sortedPath);

		// try to create directory
		try {
			sorted.mkdir();// create directory
		} catch (Exception e) {// catch any exception
			System.out.println("File could not be created");
		}

		// creating File object with datafiles/duplicates as path to create
		// duplicates directory
		File duplicate = new File("datafiles" + File.separator + "duplicates");
		// creating File Object, to create duplicateCustomers.txt file
		File duplicateFile = new File(duplicate + File.separator + "duplicateCustomers.txt");

		// creating File Object to create customers.txt file, to store,
		// all merged customer files
		File mergeCustomers = new File("datafiles" + File.separator + "database" + File.separator + "customer.txt");

		// try to create files and directories
		try {
			duplicate.mkdir();// create duplicate directory
			duplicateFile.createNewFile();// create duplicateCustomers.txt
			// create duplicateReservations.txt
			mergeCustomers.createNewFile();// create customers.txt
		} catch (Exception e) {// catch any exception
			System.out.println("Files could not be created");
		}

		// creating Customer[]
		Comparable[] custList = null;
		// creating Comparable[]
		Comparable[] mergedList = null;

		// loop that goes through all the customer files
		for (String c : custFile) {
			try {
				// Loading the file and storing it in array
				custList = HotelFileLoader.getCustomerListFromSequentialFile(relativePath + c);
				// sort array
				ListUtilities.sort(custList);
				// creating a string with the new file name
				String fileName = sorted + File.separator + "sorted" + c;
				// saving the array content in the file
				ListUtilities.saveListToTextFile(custList, fileName);

				// checking if it is the first customer
				if (isCustomerFirst) {
					// assign first customer to mergedList
					mergedList = custList;
					// make sure not to go back in this statement
					isCustomerFirst = false;
				} else {
					// assigning merged array to mergedList
					mergedList = ListUtilities.merge(mergedList, custList, "duplicateCustomers.txt");
					// sorting merged array
					ListUtilities.sort(mergedList);
					// saving merged and sorted array to reservation.txt
					ListUtilities.saveListToTextFile(mergedList, mergeCustomers.toString());
				}

			} catch (IOException e) {

			}
		}
	}
	
	/**
	 * The loadSortAndMergeReservations method loads unsorted reservation
	 * files, sorts them and stores them in sorted/reservations/"fileName"Sorted.txt
	 * After the sorting is done it will merge all the sorted reservation files in
	 * database/reservations.txt
	 * 
	 * @param roomList Array of type of Room[].
	 * 
	 * @author Ali Dali 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void loadSortAndMergeReservations(Room[] roomList) {

		boolean isReservationFirst = true;

		// creating File object, to create duplicateReservations.txt
		File duplicateReservation = new File("datafiles" + File.separator + "duplicates" 
				+ File.separator + "duplicateReservations.txt");

		try {
			duplicateReservation.createNewFile();
		} catch (IOException e) {
			System.out.println("Files could not be created");

		}
		// setting the path to of the unsorted reservation files to the String
		String relativePath = "datafiles" + File.separator + "unsorted"
				+ File.separator + "reservations" + File.separator;

		String roomFile = "datafiles" + File.separator + "database" + File.separator + "rooms.txt";

		try {
			// Loading rooms.txt
			roomList = HotelFileLoader.getRoomListFromSequentialFile(roomFile);
		} catch (IOException e) {// catching IOException in case File does not
			// exist

		}

		// creating File Object to create reservations.txt file, to store,
		// all merged reservation files
		File mergeReservation = new File(
				"datafiles" + File.separator + "database" + File.separator + "reservations.txt");

		// Creating a File with a the full path of the unsorted reservations
				File reservationFiles = new File(relativePath);
		
		// storing all the files in the directory in a String[]
		String[] resFile = reservationFiles.list();


		// creating a Customer[]
		Customer[] cust1 = null;

		// creating File Object to create customers.txt file, to store,
		// all merged customer files
		File mergeCustomers = new File("datafiles" + File.separator + "database" + File.separator + "customer.txt");

		// try loading the merged customer file
		try {
			cust1 = HotelFileLoader.getCustomerListFromSequentialFile(mergeCustomers.toString());
		} catch (IOException e) {// catch an IOException if file does not exist

		}
		
		//creating sorted file
		File sorted = new File(System.getProperty("user.dir") + File.separator
				+ "datafiles" + File.separator + "sorted");

		// creating Comparable[]
				Comparable[] resList = null;
				Comparable[] mergedReser = null;

		for (String r : resFile) {
			// try to load, sort and save sorted file
			try {
				// loading corresponding reservation file
				resList = HotelFileLoader.getReservationListFromSequentialFile(relativePath + r, cust1, roomList);
				// sorting file
				ListUtilities.sort(resList);
				// specifying path to store sorted reservation files
				String fileName = sorted + File.separator + "sorted" + r;
				// save sorted array to file
				ListUtilities.saveListToTextFile(resList, fileName);

				// checking if it is the first reservation
				if (isReservationFirst) {
					// assign first reservation file to mergedReser
					mergedReser = resList;
					// make sure not to go back in this if statement
					isReservationFirst = false;
				} else {
					// assign merged array to mergedReser
					mergedReser = ListUtilities.merge(mergedReser, resList, "duplicateReservations.txt");
					// sort merged Reservation array
					ListUtilities.sort(mergedReser);
					// save the array into the reservation.txt file
					ListUtilities.saveListToTextFile(mergedReser, mergeReservation.toString());
				}

			} catch (IOException e) {// catch IOException
			}

		}

	}
}
