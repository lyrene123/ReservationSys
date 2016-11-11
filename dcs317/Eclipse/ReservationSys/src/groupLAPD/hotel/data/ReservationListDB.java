/**
 * 
 */
package groupLAPD.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.DuplicateReservationException;
import dw317.hotel.data.NonExistingReservationException;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.hotel.data.interfaces.ReservationDAO;
import groupLAPD.hotel.business.DawsonHotelFactory;
import groupLAPD2016.util.ListUtilities;

/**
 * The ReservationListDB class which implements the
 * ReservationDAO interface represents the reservation 
 * database as an internal list. The concrete 
 * ReservationListDB provides ReservationDAO 
 * functionality. 
 * @version November 2016
 * @author Lyrene Labor, Daniel Cavalcanti, Ali Dali
 */
public class ReservationListDB implements ReservationDAO {
	//fields declaration
	private List<Reservation> database; //List for reservations
	private List<Room> allRooms; //List for rooms  
	//ListPersistenceObject to read from a file
	private final ListPersistenceObject listPersistenceObject; 
	//HotelFactory in order to create instances of Reservation 
	private final HotelFactory factory; 
	
	/**
	 * The 1 parameter ReservationListDB Constructor method will assign
	 * a value for the final ListPersistenceObject field with the input
	 * value and will use that field to fill the reservations and rooms
	 * database fields. In addition, the constructor will also set a value
	 * for the final HotelFactory field. 
	 * @param listPersistenceObject - Reference value of a 
	 * 								listPersistenceObject object
	 * @author Lyrene Labor
	 */
	public ReservationListDB (ListPersistenceObject listPersistenceObject){
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.database = this.listPersistenceObject.getReservationDatabase();
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
	}
	
	/**
	 * The 2 parameter ReservationListDB Constructor method will take as 
	 * input two values: one for the final ListPersistenceObject field 
	 * which will then be used to fill the reservations and rooms database 
	 * fields and another input value used to set a value for the final
	 * HotelFactory field. 
	 * @param listPersistenceObject - Reference value of a 
	 * 								listPersistenceObject object
	 * @param factory - Reference value of a 
	 * 					HotelFactory object
	 * @author Lyrene Labor
	 */
	public ReservationListDB (ListPersistenceObject listPersistenceObject,
			HotelFactory factory){
		this.listPersistenceObject = listPersistenceObject;
		this.factory = factory;
		this.database = this.listPersistenceObject.getReservationDatabase();
		this.allRooms = this.listPersistenceObject.getRoomDatabase();		
	}
	
	
	/**
	 * The toString method will return a string representation containing
	 * all reservation instances of the reservations database
	 * @return A String representation of all reservations from database 
	 * @author Lyrene Labor
	 */
	@Override
	public String toString(){
		String dataString = "Number of reservations in database: " + 
								this.database.size() + "\n";
		for(int i = 0; i<this.database.size(); i++){
			dataString += this.database.get(i).toString() + "\n";
		}
		return dataString;
	}
	
	
	/**
	 * The add method takes as input a reservation object and will add
	 * a copy of the new reservation object into the reservations database 
	 * if it does not overlap with any existing reservations in the database.
	 * The add method will add the new reservations object in the 
	 * correct order.
	 * @param reserv - Reference value of a Reservation object
	 * @author Lyrene Labor
	 */
	@Override
	public void add(Reservation reserv) 
			throws DuplicateReservationException {
		//check for any overlap with reserv and an existing reservation
		for(int i = 0; i<this.database.size(); i++){
			if(this.database.get(i).overlap(reserv)){
				throw new DuplicateReservationException();
			}
		}
		//make a copy of reserv
		Reservation copyReserv = this.factory.getReservationInstance
				(reserv.getCustomer(), 
				reserv.getRoom(), reserv.getCheckInDate().getYear(), 
				reserv.getCheckInDate().getMonthValue(), 
				reserv.getCheckInDate().getDayOfMonth(), 
				reserv.getCheckOutDate().getYear(), 
				reserv.getCheckOutDate().getMonthValue(),
				reserv.getCheckOutDate().getDayOfMonth());
		//add the new reservation in the correct order
		insertToOrderedList(copyReserv, this.database);
	}
	
	/**
	 * The insertToOrderedList method takes 2 input: a reservation
	 * object and a List of reservations. The method will add the 
	 * input reservation into the ordered List of reservations 
	 * by performing a Binary Search throughout the whole List.
	 * @param reserv - A reservation object to add to the 
	 * 					reservation List
	 * @param database - A List of reservation objects
	 * @author Lyrene Labor
	 */
	private static void insertToOrderedList(Reservation reserv, 
											List<Reservation> database ){	
		int left = 0;//lower bound position
		int right = database.size();//higher bound position
		int middle = (left + right)/2;//position of the middle value
		int position = -1;//position of the new reservation to add
		while(right>=left){			
			try{
				//if the value @ middle of database is smaller than reserv
				//and the next value after middle is bigger, then position
				//should be after the middle position
				if(database.get(middle).compareTo(reserv) < 0
						&& database.get(middle+1).compareTo(reserv) > 0){
					position = middle+1;
					break;//break since position is now found
				}	
				//if the value @ middle of database is smaller than reserv
				//and same thing for the next value after middle, then
				//update left position
				if(database.get(middle).compareTo(reserv)<0
						&& database.get(middle+1).compareTo(reserv) < 0){				
					left = middle+1;
				}	
				//if the value @ middle of database is bigger than reserv
				//then update right position
				if(database.get(middle).compareTo(reserv)>0){				
					right = middle-1;
				}

				middle = (left + right)/2; //update middle position
			}catch(IndexOutOfBoundsException e){	
				//if our search has now reached the right or left borders
				//then exit the while loop
				break;
			}
		}
		
		if(position!=-1){
			//if position has been updated, add the new reservation
			//in the List based on position
			database.add(position, reserv);
		}
		else{
			//if position has not been updated, do the following
			if(database.get(0).compareTo(reserv)>0){
				//if our searching reached the right border:
				database.add(0,reserv);
			}
			else{
				//if our searching reached the left border:
				database.add(reserv);
			}			
		}
	}//end of insertToOrderedList method
	
	
	
	/**
	 * This method takes an input a customer and checks the
	 * database for that customer if it is found it will add 
	 * all the reservations of that customer to an ArrayList if
	 * not it will return an empty arrayList
	 * @param Customer Cust (the customer we are looking for)
	 * @return ArrayList reservList (filed of reservations or empty)
	 * @author Lyrene Labor, Ali Dali
	 */
	@Override
	public List<Reservation> getReservations(Customer cust) {
		
		ArrayList<Reservation> reservList = new ArrayList<>();
		//loop through database to check if reservation of cust exists
		for(int i = 0; i < database.size(); i++){
			//if reservations of cust found, add it to the array list
			if(database.get(i).getCustomer().equals(cust)){
				reservList.add(database.get(i));
			}
		}
		return reservList;
	}//end of getReservations method

	
	/**
	 * This method method will look through the database for
	 * the specified Reservation and if found it will remove it
	 * if not it will throw a NonExistingReservationException
	 * @param Reservation reserv (the Reservation we want to remove)
	 * @throws  NonExistingReservationException if Reservation not 
	 * 			found.
	 * @author Lyrene Labor, Ali Dali
	 */
	@Override
	public void cancel(Reservation reserv) 
			throws NonExistingReservationException {

		boolean found = false;
		//loop through the database to check if reserv exists
		for(int i = 0; i < database.size(); i++){
			if(database.get(i).equals(reserv)){
				//remove if reserv exists in database
				database.remove(i);
				found = true;
			}
		}if(!found){
			//if not found, throw an exception
			throw new NonExistingReservationException();
		}
	}//end of cancel method
	
	
	/**
	 * The disconnect method is implement to make the reservation 
	 * database transactions persistent. This method will save the database 
	 * to disk and will then assign null to the database field.
	 * @throws IOException - if any error occurs when saving database
	 * 							to a file.
	 * @author Lyrene Labor						
	 */
	@Override
	public void disconnect() throws IOException {	 
		this.listPersistenceObject.saveReservationDatabase(this.database);
	}//end of disconnect method

	
	/**
	 * 
	 * @author Daniel Cavalcanti
	 */
	@Override
	public List<Room> getReservedRooms(LocalDate checkin,
			LocalDate checkout) {
		ArrayList<Room> reservedRooms = new ArrayList<Room>();

		for(int i = 0; i<this.database.size(); i++){
			if(this.database.get(i).getCheckInDate().isBefore(checkout)){
				if(this.database.get(i).getCheckOutDate().isAfter(checkin)){
					reservedRooms.add(this.database.get(i).getRoom());
				}
			}
		}
		HashSet<Room> listToSet = new HashSet<Room>(reservedRooms);
		List<Room> listWithoutDuplicates = new ArrayList<Room>(listToSet);
		Collections.sort(listWithoutDuplicates);

		return listWithoutDuplicates;
	}
	
	
	/**
	 * 
	 * @author Daniel Cavalcanti
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin,
			LocalDate checkout) {
		
		List<Room> reservedRoomstemp = getReservedRooms(checkin, checkout);
		List<Room> freeRooms = this.allRooms;
		freeRooms.removeAll(reservedRoomstemp);	

		return freeRooms;
	}

	/**
	 * 
	 * @author Daniel Cavalcanti
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin,
			LocalDate checkout, RoomType roomType) {

		List<Room> unreservedRoomtemp = getFreeRooms(checkin,checkout);
		List<Room> freeRoomsByType = new ArrayList<Room>();

		for(int i = 0; i<unreservedRoomtemp.size(); i++){
			if(unreservedRoomtemp.get(i).getRoomType().compareTo(roomType) == 0){
				freeRoomsByType.add(unreservedRoomtemp.get(i));
			}
		}

		return freeRoomsByType;

	}

	/**
	 * 
	 * @author Daniel Cavalcanti
	 */
	@Override
	public void clearAllPast() {

		LocalDate currentDate = LocalDate.now();
		for (int i = 0; i < this.database.size(); i++) {
			if (this.database.get(i).getCheckOutDate().isBefore(currentDate)) {
				this.database.remove(i);
				i--;
			}
		}

	}

}
