/**
 * 
 */
package groupLAPD.hotel.data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;
import groupLAPD.hotel.business.DawsonCustomer;
import groupLAPD.hotel.business.DawsonReservation;
import groupLAPD.hotel.business.DawsonRoom;

/**
 * The HotelFileLoader class is an uninstantiable class that contains a 
 * series of static utility methods that can be used to load, read and
 * copy data from files such as text sequential files. The files that
 * are used in this class contain data related to Hotel information.  
 * @author Lyrene Labor and Ali Dali
 * @version October 2016
 */
public class HotelFileLoader {

	//private constants declaration for delimiters
	// (*) is for separating the fields in a line of a file
	// (,) is for separating each lines from a file
	private static final String DELIMETER = "\\*";
	private static final String ITEM_DELIMETER = ",";
	

	/**
	 * The private no-parameter constructor prevents
	 * the HotelFileLoader class to be instantiated.
	 * @author Lyrene Labor
	 */
	private HotelFileLoader() { 
		//no implementation inside constructor 
	}//end of constructor
	
	
	/**
	 * The getCustomerListFromSequentialFile method loads and reads every 
	 * customer data entry from the input customer filename and copies every
	 * customer data entry as a DawsonCustomer instance
	 * into a full-to-capacity array of type Customer.  
	 * @param filename 
	 * 			String containing the name of the file holding the
	 * 			customer data
	 * @return A full-to-capacity array of type Customer containing
	 * 			all valid customers data taken from the input file as 
	 * 			DawsonCustomer instances.
	 * @throws IOException 
	 * 			Any other file input and output related errors such as
	 * 			the scanner not closing properly must be handled
	 * 			by the class or method calling this method.
	 * @throws IllegalArgumentException
	 * 			When the filename does not exist or cannot be found,
	 * 			or the file cannot be properly read into.		
	 * @author Lyrene Labor
	 */
	public static Customer[] getCustomerListFromSequentialFile
	(String filename) throws IOException, IllegalArgumentException{
						
		//get the full path of the filename
		String path = System.getProperty("user.dir");
		String fullFilePath = path+File.separator+filename+File.separator;
		
		//initialize scanner to null
		Scanner scan=null;		
		
		//try creating an instance of Scanner in order to read from
		//the input file
		try{
			scan = new Scanner(new File(fullFilePath));			
		}
		catch(FileNotFoundException e){
			//if file not found, throw the following exception type
			//and message
			throw new IllegalArgumentException("\tThe file " + filename+ 
					"containing customer "
					+ "data does not exist"
					+ " or cannot be found");
		}
		
		//create a StringBuilder object
		StringBuilder sb = new StringBuilder();
		
		//count num of customers inside the input file
		//by calling the countNumOfItems method
		int noOfCustomers=countNumOfItems(scan, sb);
				
		///create a customers array with the size of number of customers
		Customer customers[] = new Customer[noOfCustomers];
		
		//build the customers array by calling the buildCustomerList method
		buildCustomerList(customers, sb, filename);
		
		//get rid of all the null values inside of the customers array
		//by calling buildCustomerListNoNull method and assign the new customers
		//array into customersListNoNull.
		Customer[] customersListNoNull = buildCustomerListNoNull(customers);
		
		//close scanner if not null
		if (scan != null) {
			scan.close();
		}
		
		return customersListNoNull; //return the customers array
		
	} //end of getCustomerListFromSequentialFile method
	
	/**
	 * The getRoomListFromSequentialFile method loads and reads every 
	 * room data entry from the input room filename and copies every
	 * room data entry as DawsonRoom instances into a full-to-capacity 
	 * array of type Room.  
	 * @param filename 
	 * 			String containing the name of the file holding the
	 * 			room data
	 * @return A full-to-capacity array of type Room containing 
	 * 			DawsonRoom objects 
	 * @throws IOException 
	 * 			Any other file input and output related errors such as
	 * 			the scanner not closing properly must be handled
	 * 			by the class or method calling this method.
	 * @throws IllegalArgumentException
	 * 			When the filename does not exist or cannot be found,
	 * 			or the file cannot be properly read into.			
	 * @author Lyrene Labor
	 */
	public static Room[] getRoomListFromSequentialFile(String filename)
			throws IOException, IllegalArgumentException {
		
		//get the fullpath of the filename
		String path = System.getProperty("user.dir");
		String fullFilePath = path+File.separator+filename+File.separator;
		
		Scanner scan=null; //initialize scanner to null
		
		//create instance of Scanner to read a file and catch exceptions
		//if they occur
		try{
			scan = new Scanner(new File(fullFilePath));			
		}
		catch(FileNotFoundException e){
			//if file not found, throw the following exception type
			//and message
			throw new IllegalArgumentException("The file " + filename + 
					" containing room"
					+ " data does not exist"
					+ " or cannot be found");
		}
		
		//create a StringBuilder object
		StringBuilder sb = new StringBuilder();
				
		//count the number of rooms from the input file 
		//by calling the countNumOfItems method
		int noOfRooms = countNumOfItems(scan, sb);
		
		//create a Room array with the size of number of rooms
		Room rooms[] = new Room[noOfRooms];
		
		//build the room list by calling the roomListBuilder method
		buildRoomList(rooms, sb, filename);
		
		//build another room list containing no null values
		//store the non null rooms array as roomsListNoNull
		Room[] roomsListNoNull = buildRoomListNoNull(rooms);
		
		//close scanner if not null
		if (scan != null) {
			scan.close();
		}
		//return the rooms array
		return roomsListNoNull;
	}//end of getRoomListFromSequentialFile method
		
	/**
	 * The getReservationListFromSequentialFile method 
	 * loads and reads a reservation file, it will check 
	 * the customer array and room array to make sure the 
	 * the customer and room exist and will store everything
	 * in a separate arrayList which will be used at the end 
	 * to create a Reservation array with all the arrayList
	 * @param filename: String containing the path of reservations file
	 * @param customerList - array of type Customer containing objects 
	 * 			of DawsonCustomer
	 * @param roomList - array of type Room containing objects 
	 * 			of DawsonRoom
	 * @return an array of DawsonReservation objects that is filled
	 * @throws IOException: When file does not exist or not found
	 * @author Ali Dali
	 **/
	public static Reservation[] getReservationListFromSequentialFile
	(String filename,
			Customer[] customerList,
			Room[] roomList)
					throws IOException, IllegalArgumentException{

		//get the full path of the filename
		String path = System.getProperty("user.dir");
		String fullFilePath = path+File.separator+filename+File.separator;
		
		//creating file using filename	
		File fileObj = new File(fullFilePath);
		Scanner reader = null;
		
		//to check if file exist or not
		try{
			reader = new Scanner(fileObj);
		}catch(FileNotFoundException e){
			throw new IOException(fileObj + " file not found ");
		}

		//creating arrayLists to store each parameter
		ArrayList<Customer> customerArray = new ArrayList<>();
		ArrayList<Room> roomArray = new ArrayList<>();
		ArrayList<Integer> checkInYearArr = new ArrayList<>();
		ArrayList<Integer> checkInMonthArr = new ArrayList<>();
		ArrayList<Integer> checkInDayArr = new ArrayList<>();
		ArrayList<Integer> checkOutYearArr = new ArrayList<>();
		ArrayList<Integer> checkOutMonthArr = new ArrayList<>();
		ArrayList<Integer> checkOutDayArr = new ArrayList<>();	
		
		int numReserv = 0;//count number of Reservations

		//takes every line and splits it using the delimiter
		while(reader.hasNext()){
			//storing line into String
			String aLine = reader.nextLine();
			
			if(!(aLine.isEmpty())){
				//splitting non empty lines
				//according to the delimiter *
				String[] arrLineStr = aLine.split("\\*");


				try{
					//checking if customer from reservation file
					//exists in customer array
					for(int i = 0; i < customerList.length; i++){
						if(arrLineStr[0].equalsIgnoreCase(customerList[i].
								getEmail().getAddress())){
							customerArray.add(customerList[i]);	
						}		
					}
					//checking if Room from reservation file
					//exists in room array
					for(int i = 0; i < roomList.length; i++){
						if(arrLineStr[7].equals(String.valueOf(roomList[i].
								getRoomNumber())) ){
							roomArray.add(roomList[i]);	
						}
					}

					//storing dates in corresponding arrayLists
					checkInYearArr.add(Integer.parseInt(arrLineStr[1])); 
					checkInMonthArr.add(Integer.parseInt(arrLineStr[2]));
					checkInDayArr.add(Integer.parseInt(arrLineStr[3]));
					checkOutYearArr.add(Integer.parseInt(arrLineStr[4]));
					checkOutMonthArr.add(Integer.parseInt(arrLineStr[5]));
					checkOutDayArr.add(Integer.parseInt(arrLineStr[6]));

					numReserv++;
				}
				catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
				catch(IndexOutOfBoundsException x){
					System.out.println(x.getMessage());
				}

			}
		}
		//close scanner	
		reader.close();

		//Creating reservation array
		Reservation[] reservations = new Reservation[numReserv];

		int nullCount = 0;
		
		//Creating Reservations using arrayLists
		for(int i = 0; i < numReserv; i++){
			//if room or customer does not exist, catch the exception
			try{
				reservations[i] = new DawsonReservation(customerArray.get(i),
						roomArray.get(i),checkInYearArr.get(i), checkInMonthArr.
						get(i), checkInDayArr.get(i), checkOutYearArr.get(i), 
						checkOutMonthArr.get(i), checkOutDayArr.get(i));
			}catch(IndexOutOfBoundsException e){
				System.out.println("Invalid "
						+ "entry in " + filename + ". A customer or"
						+ " room does not exist.");	
			}
			catch(IllegalArgumentException x){
				System.out.println("Invalid "
						+ "entry in " + filename + "\n" + x.getMessage());	
			}
			//count the number of null values inside the array
			if(reservations[i] == null){
				nullCount++;
			}
		}
		
		//create another reservation array that will only hold 
		//non null objects
		Reservation[] filledReservation = new 
				Reservation[reservations.length -nullCount];
		int filledIndex = 0;
		
		for(int i = 0; i<reservations.length; i++){
			if(reservations[i] != null){
				filledReservation[filledIndex] = reservations[i];
				filledIndex++;
			}
		}
		return filledReservation;
	}//End of method

	
	/**
	 * The buildCustomerList method will populate the customers 
	 * array passed as input with DawsonCustomer objects
	 * based on the data found inside the customers input file
	 * @param customers - Array of type customers to populate
	 * 						with DawsonCustomer objects
	 * @param filename reference string for the input filename
	 * @param sb - reference of type StringBuilder containing
	 * 			all lines of the input file
	 * @author Lyrene Labor
	 */
	private static void buildCustomerList(Customer customers[], 
			StringBuilder sb,String filename) {
		
		//create a String array holding each line of the input file
		String customerArray[] = 
				sb.toString().trim().split(ITEM_DELIMETER);
				
		//iterate through each entry of the String customerArray 
		for (int i = 0; i < customers.length; i++) {
			//for an entry of the customerArray, separate each field(*)
			//and assign them to the customerEntry array of type String
			String[] customerEntry = customerArray[i].split(DELIMETER);

			//try the following block of code and catch any exception
			//and display the error message
			try{
				//validate the customerEntry array
				validateCustomerEntry(customerEntry, filename);

				//validate the email,first and last name
				//fields, any IllegalArgumentException generated
				//from the Email or Name class will be caught
				Email customerEmail = new Email(customerEntry[0]);
				Name customerName = new Name(customerEntry[1], 
						customerEntry[2]);

				CreditCard customerCard = null;
				
				//if the customer entry contains information about 
				//the customer's credit card then do the following
				if(customerEntry.length==5){
					//store the customer's credit card type
					//and the credit card number in a variable
					String creditName = customerEntry[3].trim();
					String creditNumber = customerEntry[4].trim();

					//If the credit card type is not amex, visa
					// or mastercard, then display an error message
					if(!(creditName.equalsIgnoreCase("amex")||
							creditName.equalsIgnoreCase("visa")||
							creditName.equalsIgnoreCase("mastercard"))){
						throw new IllegalArgumentException("\tThe credit card type"
								+ " of a customer must not be empty and must"
								+ " be amex, visa or mastercard only");
					}

					//if the  credit card is amex, set that card
					if(creditName.equalsIgnoreCase("amex")){
						customerCard = new Amex(creditNumber);
					}
					//if the credit card is visa, set that card
					if(creditName.equalsIgnoreCase("visa")){
						customerCard = new Visa(creditNumber);
					}
					//if the credit card is mastercard, set that card
					if(creditName.equalsIgnoreCase("mastercard")){
						customerCard = new MasterCard(creditNumber);
					}				
				}
							
				//if no errors occurred so far, 
				//create a DawsonCustomer instance 
				customers[i] = new DawsonCustomer(customerName.getFirstName(), 
						customerName.getLastName(), customerEmail.toString());
				
				//if the customer has info about their credit card, set them
				if(customerEntry.length==5){
					customers[i].setCreditCard(Optional.of(customerCard));
				}				
			}
			//any error that occurs, display the error message
			catch(IllegalArgumentException e){
				System.out.println("Invalid entry inside file " + filename +
						" containing customers data. " +
						"\n" + e.getMessage());
			}
		}
	}//end of buildCustomerList method
	
	/**
	 * The buildCustomerListNoNull method will count the number of
	 * null values from a Customer array and will copy the content of
	 * Customer array into another array but without the null values
	 * @param customers an array of type Customer with null values
	 * @return an array of type Customer containing instances
	 * 			of DawsonCustomer with no null values
	 * @author Lyrene Labor
	 */
	private static Customer[] buildCustomerListNoNull
	(Customer[] customers){
		
		int nullCount = 0;
		
		//the following loop will count the number of null values
		//inside the array
		for(int i = 0; i<customers.length; i++){
			if(customers[i]==null){
				nullCount++;
			}
		}
		
		//create another Customer array that will contain non null values
		Customer[] customersNoNull = new Customer[customers.length-nullCount];
		int customersNoNullIndex = 0;
		
		//the following loop will assign all non-null values of the customers
		//array into the customersNoNull array
		for(int i =0; i<customers.length; i++){
			if(!(customers[i]==null)){
				customersNoNull[customersNoNullIndex] = customers[i];
				customersNoNullIndex++;
			}
		}
		
		//return the array containing no null values
		return customersNoNull;
		
	}//end of buildCustomerListNoNull method
	
	
	/**
	 * The buildRoomList method will populate the array of type
	 * Room passed as input with DawsonRoom objects
	 * based on the room data of an input filename
	 * @param rooms - array of type rooms to populate with
	 * 			DawsonRoom objects
	 * @param sb reference of type StringBuilder containing
	 * 			all lines of the input file
	 * @param filename reference string for the filename
	 * @author Lyrene Labor
	 */
	private static void buildRoomList(Room rooms[], StringBuilder sb,
			String filename) {
		//create a String array holding each line of the input file
		String[] roomArray = sb.toString().split(ITEM_DELIMETER);
		
		//iterate through each entry of the roomArray 
		for (int i = 0; i < rooms.length; i++) {
			
			//for each entry of the roomArray, separate each field(*)
			//and assign them to the roomEntry array of type String
			String[] roomEntry = roomArray[i].split(DELIMETER);
			
			//try the following block of code and any error that
			//occur, display the error message
			try{
				//validate the the room entry 
				validateRoomEntry(roomEntry, filename);

				//once validated, try creating a DawsonRoom instance
				//with the values of the roomEntry and then assigning
				//the instance into the rooms Array.
				try{
					rooms[i] = new DawsonRoom(Integer.parseInt(roomEntry[0]),
							RoomType.valueOf(roomEntry[1].toUpperCase()));			
				}
				//catch NumberFormatException if it occurs
				//and throw the following exception and message
				catch(java.lang.NumberFormatException e){
					throw new IllegalArgumentException("The file " + filename +
							" have an invalid room entry: " +
							e.getMessage() + "\n\tA room number must be numeric "
							+ "and not empty.");
				}
				//catch IllegalArgumentException if it occurs
				//and throw the following exception and message
				catch(IllegalArgumentException x){
					throw new IllegalArgumentException("\tThe file "+filename+
							" have an invalid room entry: " +
							x.getMessage() + "\n\t And the room type must not "
							+ "be empty and must be "
							+ "NORMAL, SUITE or PENTHOUSE.");
				}
			}
			//any errors or exception that occur or thrown,
			//display the error message
			catch(IllegalArgumentException e){
				System.out.println("Invalid entry inside file "+filename+
						" containing rooms data. " +
						"\n" + e.getMessage());
			}
		}		
	}//end of buildRoomList method
	
	
	/**
	 * The buildRoomListNoNull method will count the number of
	 * null values from a Room array and will copy the content of
	 * Room array into another array but without its null values
	 * @param rooms an array of type Room containing DawsonRoom objects
	 * 			and null values
	 * @return an array of type Room containing DawsonRoom objects
	 * 			and no null values
	 * @author Lyrene Labor
	 */
	private static Room[] buildRoomListNoNull(Room[] rooms){
		
		int nullCount = 0;
		
		//the following loop will count the number null values
		//in rooms array
		for(int i = 0; i<rooms.length; i++){
			if(rooms[i]==null){
				nullCount++;
			}
		}
		
		//create another rooms array that will hold only non null 
		//objects
		Room[] roomsNoNull = new Room[rooms.length-nullCount];
		int roomsNoNullIndex = 0;
		
		//the following loop will copy the non null values of rooms array
		//into roomsNoNull method
		for(int i =0; i<rooms.length; i++){
			if(!(rooms[i]==null)){
				roomsNoNull[roomsNoNullIndex] = rooms[i];
				roomsNoNullIndex++;
			}
		}
		
		//return an array with no values
		return roomsNoNull;
	}// end of buildRoomListNoNull method
	
	
	/**
	 * countNumOfItems will count the number of items present in a 
	 * input file and will also store each line of the input file 
	 * into a StringBuilder instance
	 * @param scan Reference variable of type Scanner used
	 * 			to scan the input data file 
	 * @param sb reference of type StringBuilder containing
	 * 			all lines of the input file
	 * @return int value representing the number of items 
	 * 			inside the input file
	 * @author Lyrene Labor
	 */
	private static int countNumOfItems(Scanner scan, StringBuilder sb){
		
		//initialize the variable of # of items to 0
		int noOfItems=0; 
						
		//check each non empty line of the input file
		while (scan.hasNext()) {
			
			String line = scan.nextLine();
			//append to the StringBuilder object each
			//non-empty line with a new delimeter(,) to separate
			//each line
			if(!(line.trim().isEmpty())){
				sb.append(line).append(ITEM_DELIMETER);
				noOfItems++; //increment number of items
			}
		}
		
		return noOfItems; //return number of items
	}//end of countNumOfItems method

	
	/**
	 * The validateRoomEntry method will validate each line of
	 * the file containing the rooms data and will check if each
	 * line only contains 2 fields
	 * @param roomEntry An array of type String containing a 
	 * 			room entry or line 
	 * @throws IllegalArgumentException If a room entry contains 
	 * 			more or less than 2 fields 	
	 * @author Lyrene Labor		
	 */
	private static void validateRoomEntry(String[] roomEntry, String
			filename) 
			throws IllegalArgumentException {
		//throw the following exception if a room entry
		//has more than 2 fields
		if (roomEntry.length != 2)
			throw new IllegalArgumentException
			("\tInvalid Room Entry found in file " + filename + 
					". A room entry must have"
					+ " 2 fields only");
	}
	
	
	/**
	 * The validateCustomerEntry method will validate each line of
	 * the file containing the customers data and will check if each
	 * line strictly only contains 5 fields or 3 fields.
	 * @param roomEntry An array of type String containing a 
	 * 			customer entry or line 
	 * @throws IllegalArgumentException If a customer entry contains 
	 * 			more or less than 5 fields 	
	 * @author Lyrene Labor
	 *		
	 */
	private static void validateCustomerEntry(String[] customerEntry, 
			String filename) 
			throws IllegalArgumentException {
		//throw the following exception if a customer entry
		//has more than 5 fields
		if (!(customerEntry.length==5 || customerEntry.length==3))
			throw new IllegalArgumentException
			("\tInvalid Customer Entry found in file "+filename+
					". A customer entry must have"
					+ " 5 fields or 3 fields only");
	}//end of validateCustomerEntry method	  
}//end of HotelFileLoader class
