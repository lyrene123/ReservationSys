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

	//private constants declaration for delimeters
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
	 * customer data entry into a full-to-capacity array of type Customer.  
	 * @param filename 
	 * 			String containing the name of the file holding the
	 * 			customer data
	 * @return A full-to-capacity array of type Customer
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
		
		//initialize scanner and noOfCustomers
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
		
		Customer[] customersListNoNull = buildCustomerListNoNull(customers);
		
		//close scanner if not null
		if (scan != null) {
			scan.close();
		}
		
		return customersListNoNull; //return the customers array
		
	} 
	
	private static Customer[] buildCustomerListNoNull
	(Customer[] customers){
		int nullCount = 0;
		for(int i = 0; i<customers.length; i++){
			if(customers[i]==null){
				nullCount++;
			}
		}
		
		Customer[] customersNoNull = new Customer[customers.length-nullCount];
		int customersNoNullIndex = 0;
		for(int i =0; i<customers.length; i++){
			if(!(customers[i]==null)){
				customersNoNull[customersNoNullIndex] = customers[i];
				customersNoNullIndex++;
			}
		}
		
		return customersNoNull;
		
	}

	
	/**
	 * The getRoomListFromSequentialFile method loads and reads every 
	 * room data entry from the input room filename and copies every
	 * room data entry into a full-to-capacity array of type Room.  
	 * @param filename 
	 * 			String containing the name of the file holding the
	 * 			room data
	 * @return A full-to-capacity array of type Room
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
		catch(Exception e){
			//if the file caused any other exceptions,
			//throw the following exception and message
			throw new IllegalArgumentException("The file " + filename +
					" caused the following error: " + e.getMessage());
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
		
		Room[] roomsListNoNull = buildRoomListNoNull(rooms);
		
		//close scanner if not null
		if (scan != null) {
			scan.close();
		}
		//return the rooms array
		return roomsListNoNull;
	}//end of getRoomListFromSequentialFile method
	
	
	private static Room[] buildRoomListNoNull(Room[] rooms){
		int nullCount = 0;
		for(int i = 0; i<rooms.length; i++){
			if(rooms[i]==null){
				nullCount++;
			}
		}
		
		Room[] roomsNoNull = new Room[rooms.length-nullCount];
		int roomsNoNullIndex = 0;
		for(int i =0; i<rooms.length; i++){
			if(!(rooms[i]==null)){
				roomsNoNull[roomsNoNullIndex] = rooms[i];
				roomsNoNullIndex++;
			}
		}
		
		return roomsNoNull;
	}
	
	/**
	 * The getReservationListFromSequentialFile method 
	 * loads and reads a reservation file, it will check 
	 * the customer array and room array to make sure the 
	 * the customer and room exist and will store everything
	 * in a separate arrayList which will be used at the end 
	 * to create a Reservation array with all the arrayList
	 * @param filename: String containing the path of reservations file
	 * 			customer[]: to check if customer in reservation exists
	 * 			room[] : to check if room in reservation exists
	 * @return an array of Reservations that is filled
	 * @throws IOException: When file does not exist or not found
	 * 			IllegalArgumentException: When Room or Customer
	 * 				not found
	 * @return a Reservation Array
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
			//if room or customer does not exist 
			try{
				reservations[i] = new DawsonReservation(customerArray.get(i),
						roomArray.get(i),checkInYearArr.get(i), checkInMonthArr.
						get(i), checkInDayArr.get(i), checkOutYearArr.get(i), 
						checkOutMonthArr.get(i), checkOutDayArr.get(i));
				
				//throw illegalArgumentException
			}catch(IndexOutOfBoundsException e){
				System.out.println("Invalid "
						+ "entry in " + filename + ". A customer or"
								+ " room does not exist.");	
			}
		if(reservations[i] == null){
			nullCount++;
		}
		}
		Reservation[] filledReservation = new 
				Reservation[reservations.length -nullCount];
		
		for(int i = 0; i<filledReservation.length; i++){
			if(reservations[i] != null){
			filledReservation[i] = reservations[i];
			}
		}
		return filledReservation;
	}//End of method
	
	/**
	 * The buildCustomerList method will populate the customers 
	 * array based on the data found inside the customers input file
	 * @param customers - Array of type customers holding all
	 * 					customer data from the input file
	 * @param filename reference string for the input filename
	 * @param sb - reference of type StringBuilder containing
	 * 			all lines of the input file
	 * @throws IllegalArgumentException
	 * 			when the input file contains invalid room data
	 * 			such as invalid email, customer name, 
	 * 			credit card number, credit type. 
	 * @author Lyrene Labor
	 */
	private static void buildCustomerList(Customer customers[], 
			StringBuilder sb,String filename) 
					throws IllegalArgumentException{
		
		//create a String array holding each line of the input file
		String customerArray[] = sb.toString().trim().split(ITEM_DELIMETER);
				
		//iterate through each entry of the String customerArray array
		for (int i = 0; i < customers.length; i++) {
			//for each entry of the customerArray, separate each field(*)
			//and assign them to the customerEntry array of type String
			String[] customerEntry = customerArray[i].split(DELIMETER);

			try{
				//validate the customerEntry array
				validateCustomerEntry(customerEntry, filename);

				//validate for each entry the email, firstname and lastname
				//fields and throw the IllegalArgumentException generated
				//from the Email or Name class if they occur


				Email customerEmail = new Email(customerEntry[0]);
				Name customerName = new Name(customerEntry[1], 
						customerEntry[2]);

				//once validated, create a DawsonCustomer instance with 
				//the values of the customerEntry array
				customers[i] = new DawsonCustomer(customerName.getFirstName(), 
						customerName.getLastName(), customerEmail.toString());


				//if the customer entry contains information about 
				//the customer's credit card then do the following
				if(customerEntry.length==5){
					//store the customer's credit card type
					//and the credit card number in a variable
					String creditName = customerEntry[3].trim();
					String creditNumber = customerEntry[4].trim();

					//If the credit card type is not amex, visa
					// or mastercard, then throw an exception
					if(!(creditName.equalsIgnoreCase("amex")||
							creditName.equalsIgnoreCase("visa")||
							creditName.equalsIgnoreCase("mastercard"))){
						System.out.println("\tThe credit card type"
								+ " of a customer must not be empty and must"
								+ " be amex, visa or mastercard only");
					}

					//if the  credit card is amex, set that card
					if(creditName.equalsIgnoreCase("amex")){


						Amex card = new Amex(creditNumber);
						customers[i].setCreditCard(Optional.of(card));

					}
					//if the credit card is visa, set that card
					else if(creditName.equalsIgnoreCase("visa")){

						Visa card = new Visa(creditNumber);
						customers[i].setCreditCard(Optional.of(card));


					}
					//if the credit card is mastercard, set that card
					else{

						MasterCard card = new MasterCard(creditNumber);
						customers[i].setCreditCard(Optional.of(card));

					}				
				}
			}
			catch(IllegalArgumentException e){
				System.out.println("Invalid entry inside file " + filename +
						" containing customers data. " +
						"\n" + e.getMessage());
			}
		}
	}//end of buildCustomerList method
	
	
	/**
	 * The buildRoomList method will populate the array of type
	 * Room based on the room data of an input filename
	 * @param rooms reference of type room for the room array 
	 * 			holding all room data from the room input data
	 * @param sb reference of type StringBuilder containing
	 * 			all lines of the input file
	 * @param filename reference string for the filename
	 * @throws IllegalArgumentException
	 * 			when the input file contains invalid room data
	 * 			such as invalid room number and invalid room type
	 * @author Lyrene Labor
	 */
	private static void buildRoomList(Room rooms[], StringBuilder sb,
			String filename) throws IllegalArgumentException{
		//create a String array holding each line of the input file
		String[] roomArray = sb.toString().split(ITEM_DELIMETER);
		
		//iterate through each entry of the roomArray 
		for (int i = 0; i < rooms.length; i++) {
			//for each entry of the roomArray, separate each field(*)
			//and assign them to the roomEntry array of type String
			String[] roomEntry = roomArray[i].split(DELIMETER);
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
					throw new IllegalArgumentException("\tThe file " + filename +
							" have an invalid room entry: " +
							x.getMessage() + "\n\t And the room type must not "
							+ "be empty and must be "
							+ "NORMAL, SUITE or PENTHOUSE.");
				}

			}
			catch(IllegalArgumentException e){
				System.out.println("Invalid entry inside file " + filename +
						" containing rooms data. " +
						"\n" + e.getMessage());
			}
		}
		
	}//end of buildRoomList method
	
	/**
	 * countNumOfRooms will count the number of rooms present in the 
	 * input file of getRoomListFromSequentialFile method and will also
	 * store each line of the input file into a StringBuilder instance
	 * @param scan Reference variable of type Scanner used
	 * 			to scan the input data file of 
	 *  		getRoomListFromSequentialFile
	 * @param sb reference of type StringBuilder containing
	 * 			all lines of the input room file
	 * @return int value representing the number of rooms 
	 * 			inside the input file
	 * @author Lyrene Labor
	 */
	private static int countNumOfItems(Scanner scan, StringBuilder sb){
		
		//initialize the variable of # of room to 0
		int noOfItems=0; 
						
		//check each non empty line of the input file
		while (scan.hasNext()) {
			
			String line = scan.nextLine();
			//append to the StringBuilder object each
			//non-empty line with a new delimeter(,) to separate
			//each line
			if(!(line.trim().isEmpty())){
				sb.append(line).append(ITEM_DELIMETER);
				noOfItems++; //increment number of rooms
			}
		}
		
		return noOfItems; //return number of rooms
	}//end of countNumOfRooms....

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
	 * line only contains 5 fields
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
	  
}
