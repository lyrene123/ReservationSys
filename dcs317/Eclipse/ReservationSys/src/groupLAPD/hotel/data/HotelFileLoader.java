/**
 * 
 */
package groupLAPD.hotel.data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;
import groupLAPD.hotel.business.DawsonCustomer;
import groupLAPD.hotel.business.DawsonRoom;

/**
 * The HotelFileLoader class is an uninstantiable class that contains a 
 * series of static utility methods that can be used to load, read and
 * copy data from files such as text sequential files. The files that
 * are used in this class contain data related to Hotel information.  
 * @author Lyrene Labor
 * @version October 2016
 */
public class HotelFileLoader {

	//private constants declaration
	private static final String DELIMETER = "\\*";
	private static final String ITEM_DELIMETER = ",";
	

	/**
	 * The private no-parameter constructor prevents
	 * the HotelFileLoader class to be instantiated.
	 */
	private HotelFileLoader() { 
		//no implementation inside constructor 
	}//end of constructor

	/**
	 * The getRoomListFromSequentialFile method loads and reads every 
	 * room data entry from the input room filename and copies every
	 * room data entry into a full-to-capacity array of type Room.  
	 * @param filename 
	 * 			String containing the name of the file holding the
	 * 			room data
	 * @return A full-to-capacity array of type Room
	 * @throws IOException 
	 * 			When the input file does not exist or cannot be found
	 * 			or any other input and output related errors such as
	 * 			the scanner not closing properly.
	 */
	public static Room[] getRoomListFromSequentialFile(String filename)
			throws IOException {
		
		Scanner scan=null; //initialize scanner to null
		
		//try the following block of code and catch any exceptions
		try{
			scan = new Scanner(new File(filename));			
		}
		catch(FileNotFoundException e){
			//if file not found, throw the following exception type
			//and message
			throw new IllegalArgumentException("The file " + filename + 
					" containing room"
					+ " data does not exist"
					+ " or cannot be found");
		}
		catch(Exception e){
			//if the file caused any other exceptions,
			//throw the following exception and message
			throw new IllegalArgumentException("The file " + filename +
					" caused the following error: " + e.getMessage());
		}
		
		//create a StringBuilder object
		StringBuilder sb = new StringBuilder();
		int noOfRooms=0; //initialize the variable to 0
		
		//check each non empty line of the input file
		while (scan.hasNext()) {
			//append to the StringBuilder object each
			//non-empty line with a new delimeter to seperate
			//each different line
			sb.append(scan.nextLine()).append(ITEM_DELIMETER);
			noOfRooms++; //increment number of rooms
		}
		
		//create a Room array with the size of number of rooms
		Room rooms[] = new Room[noOfRooms];
		//create a String array holding each line of the input file
		String[] roomArray = sb.toString().split(ITEM_DELIMETER);;
  		//iterate through each entry of the String roomArray 
		for (int i = 0; i < noOfRooms; i++) {
			//for each line of the roomArray, seperate each field
			//and assign them to the roomEntry array of type String
			String[] roomEntry = roomArray[i].split(DELIMETER);
			//validate the value of each field of the roomEntry array
			validateRoomEntry(roomEntry);
			//once validated, try assigning the value of the entry
			//into the rooms array by creating an instance of 
			//DawsonRoom
			try{
				rooms[i] = new DawsonRoom(Integer.parseInt(roomEntry[0]),
						RoomType.valueOf(roomEntry[1].toUpperCase()));
				
			}
			//catch NumberFormatException if it occurs
			//and throw the following exception and message
			catch(java.lang.NumberFormatException e){
				throw new IllegalArgumentException("The file " + filename +
						" have an invalid room entry: " +
						e.getMessage() + "\nA room number must be numeric "
								+ "and not empty.");
			}
			//catch IllegalArugmentException if it occurs
			//and throw the following exception and message
			catch(IllegalArgumentException x){
				throw new IllegalArgumentException("The file " + filename +
						" have an invalid room entry: " +
						x.getMessage() + "\nThe room type must not "
								+ "be empty and must be "
						+ "NORMAL, SUITE or PENTHOUSE.");
			}
			//catch any other Exception if it occurs
			//and throw the following exception and message
			catch(Exception y){
				throw new IllegalArgumentException("The file " + filename +
						" have an invalid room entry: " +
						y.getMessage());
			}
													
		}
		
		//close scanner if not null
		if (scan != null) {
			scan.close();
		}
		//return the rooms array
		return rooms;
	}//end of getRoomListFromSequentialFile method

	/**
	 * The validateRoomEntry method will validate each line of
	 * the file containing the rooms data and will check if each
	 * line only contains 2 fields
	 * @param roomEntry An array of type String containing a 
	 * 			room entry or line 
	 * @throws IllegalArgumentException If a room entry contains 
	 * 			more or less than 2 fields 			
	 */
	private static void validateRoomEntry(String[] roomEntry) 
			throws IllegalArgumentException {
		//throw the following exception if a room entry
		//has more than 2 fields
		if (roomEntry.length > 2)
			throw new IllegalArgumentException
			("Invalid Room Entry found! A room entry must not have more"
					+ " than 2 fields");

	}
	
	/**
	 * The getCustomerListFromSequentialFile method loads and reads every 
	 * customer data entry from the input customer filename and copies every
	 * customer data entry into a full-to-capacity array of type Customer.  
	 * @param filename 
	 * 			String containing the name of the file holding the
	 * 			customer data
	 * @return A full-to-capacity array of type Customer
	 * @throws IOException 
	 * 			When the input file does not exist or cannot be found
	 * 			or any other input and output related errors such as
	 * 			the scanner not closing properly.
	 */
	public static Customer[] getCustomerListFromSequentialFile(String filename) 
			throws IOException{
		//initialize scanner and noOfCustomers
		Scanner scan=null;
		int noOfCustomers=0;
		//try the following block of code and catch any exceptions
		try{
			scan = new Scanner(new File(filename));			
		}
		catch(FileNotFoundException e){
			//if file not found, throw the following exception type
			//and message
			throw new IllegalArgumentException("The file " + filename+ 
					"containing customer "
					+ "data does not exist"
					+ " or cannot be found");
		}
		
		//create a StringBuilder object
		StringBuilder sb = new StringBuilder();
		//check each non empty line of the input file
		while (scan.hasNext()) {
			//append to the StringBuilder object each
			//non-empty line with a new delimeter to seperate
			//each different line
			sb.append(scan.nextLine()).append(ITEM_DELIMETER);
			noOfCustomers++;//increment noOfCustomers
		}
		///create a customers array with the size of number of customers
		Customer customers[] = new Customer[noOfCustomers];
		//create a String array holding each line of the input file
		String customerArray[] = sb.toString().split(ITEM_DELIMETER);
		//iterate through each entry of the String customerArray array
		for (int i = 0; i < noOfCustomers; i++) {
			//for each line of the customerArray, seperate each field
			//and assign them to the customerEntry array of type String
			String[] customerEntry = customerArray[i].split(DELIMETER);
			//validate the value of each field of the customerEntry array
			validateCustomerEntry(customerEntry);
			//once validated, try assigning the value of the entry
			//into the customers array by creating an instance of 
			//DawsonCustomer		
			customers[i] = new DawsonCustomer(customerEntry[1], 
				customerEntry[2], customerEntry[0]);
			
			if(customerEntry.length==5){
				String creditName = customerEntry[3];
				String creditNumber = customerEntry[4];
				
				if(creditName.equalsIgnoreCase("amex")&&
						creditName.equalsIgnoreCase("visa")&&
						creditName.equalsIgnoreCase("mastercard")){
					throw new IllegalArgumentException("The credit card type"
							+ " of a customer must be amex, visa or mastercard only");
				}
				
				if(creditName.equalsIgnoreCase("amex")){
					Amex card = new Amex(creditNumber);
					customers[i].setCreditCard(Optional.of(card));
				}
				else if(creditName.equalsIgnoreCase("visa")){
					Visa card = new Visa(creditNumber);
					customers[i].setCreditCard(Optional.of(card));
				}
				else{
					MasterCard card = new MasterCard(creditNumber);
					customers[i].setCreditCard(Optional.of(card));
				}
					
			}
			
			
		}
		//close scanner if not null
		if (scan != null) {
			scan.close();
		}
		
		return customers; //return the customers array
			
	} 
	
	/**
	 * The validateCustomerEntry method will validate each line of
	 * the file containing the customers data and will check if each
	 * line only contains 5 fields
	 * @param roomEntry An array of type String containing a 
	 * 			customer entry or line 
	 * @throws IllegalArgumentException If a customer entry contains 
	 * 			more or less than 5 fields 			
	 */
	private static void validateCustomerEntry(String[] customerEntry) 
			throws IllegalArgumentException {
		//throw the following exception if a customer entry
		//has more than 5 fields
		if (customerEntry.length > 5)
			throw new IllegalArgumentException
			("Invalid Customer Entry found!");
	}
	
	
}
