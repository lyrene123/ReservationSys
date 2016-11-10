/**
 * 
 */
package groupLAPD.hotel.data;

import java.io.IOException;
import java.time.LocalDate;
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
import groupLAPD.hotel.business.DawsonReservation;

/**
 * 
 * 
 * @author Lyrene Labor, Daniel Cavalcanti
 *
 */
public class ReservationListDB implements ReservationDAO {
	
	private List<Reservation> database;  
	private List<Room> allRooms;  
	private final ListPersistenceObject listPersistenceObject;  
	private final HotelFactory factory; 
	
	/**
	 * 
	 * 
	 * @param listPersistenceObject
	 * @author Lyrene Labor
	 */
	public ReservationListDB (ListPersistenceObject listPersistenceObject){
		this.listPersistenceObject = listPersistenceObject;
		this.factory = DawsonHotelFactory.DAWSON;
		this.database = this.listPersistenceObject.getReservationDatabase();
		this.allRooms = this.listPersistenceObject.getRoomDatabase();
	}
	
	/**
	 * 
	 * @param listPersistenceObject
	 * @param factory
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
	 * 
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
	 * 
	 * @author Lyrene Labor
	 */
	@Override
	public void add(Reservation reserv) 
			throws DuplicateReservationException {
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
		insertToOrderedList(copyReserv, this.database);
	}
	
	/**
	 * 
	 * @param reserv
	 * @param database
	 */
	private static void insertToOrderedList(Reservation reserv, 
											List<Reservation> database ){
		boolean isAdded = false;
		
		int left = 0;
		int right = database.size() - 1;
		int middle = (left + right)/2;
		while(right>=left){
			if(database.get(middle).compareTo(reserv) < 0){
				left = middle + 1;
			}
			else if(database.get(middle).compareTo(reserv)>0){
				right = middle-1;
			}
			else{
				database.add(middle, reserv);
				isAdded = true;
			}
		}
		
		if(isAdded == false){
			database.add(reserv);
		}
		
		/**for(int i = 0; i< database.size(); i++){
			//if the element from database is smaller than reserv, 
			//skip to the next element
			if(database.get(i).compareTo(reserv) < 0){
				continue;
			}
			else{
				//otherwise add reserv to the current position of the loop
				database.add(reserv);
				isAdded = true;
			}
		}
		//if reserv hasn't been added, add at the end of the List
		if(isAdded == false){
			database.add(reserv);		
		}*/
	}
	

	/**
	 * 
	 */
	@Override
	public void disconnect() throws IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	@Override
	public List<Reservation> getReservations(Customer cust) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void cancel(Reservation reserv) 
			throws NonExistingReservationException {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 */
	@Override
	public List<Room> getReservedRooms(LocalDate checkin,
			LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin,
			LocalDate checkout) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public List<Room> getFreeRooms(LocalDate checkin,
			LocalDate checkout, RoomType roomType) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	@Override
	public void clearAllPast() {
		// TODO Auto-generated method stub

	}

}
