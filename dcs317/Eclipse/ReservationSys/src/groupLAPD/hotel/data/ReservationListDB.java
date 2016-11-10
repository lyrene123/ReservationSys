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
