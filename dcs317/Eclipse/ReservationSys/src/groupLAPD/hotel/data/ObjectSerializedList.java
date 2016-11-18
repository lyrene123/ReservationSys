package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.w3c.dom.ls.LSInput;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import groupLAPD2016.util.Utilities;


/**
 * This class deserialzes the serialized files that have been given as input 
 * from the constructor.
 * @author Ali Dali
 *
 */
public class ObjectSerializedList implements ListPersistenceObject{

	private String roomFile;
	private String customerFile;
	private String reservationFile;

	/**
	 * 3 parameters constructor takes serialized files and assigns
	 * them to the attributes
	 * @param roomFile : serialized room File
	 * @param customerFile : serialized File 
	 * @param reservFile : serialized File
	 */
	public ObjectSerializedList(String roomFile, String customerFile, String reservFile){
		this.roomFile = roomFile;
		this.customerFile = customerFile;
		this.reservationFile = reservFile;
	}

	/**
	 * this method deserializes the serialized room file and assigns
	 * the re-constructed object into a List
	 * @return roomDatabase
	 */
	@Override
	public List<Room> getRoomDatabase() {
		
		List<Room> roomDatabase = null;
		try{
			roomDatabase = (List<Room>) Utilities.deserializeObject(roomFile);
		}catch(IOException | ClassNotFoundException e){
			System.out.println(e.getMessage());
		}		
		return roomDatabase;
	}

	/**
	 * this method deserializes the serialized customer file and assigns
	 * the re-constructed object into a List
	 * @return customerDatabase
	 */
	@Override
	public List<Customer> getCustomerDatabase() {

		List<Customer> customerDatabase = null;
		
		try{
			customerDatabase = (List<Customer>) Utilities.deserializeObject(customerFile);
		}catch(IOException | ClassNotFoundException e){
			System.out.println(e.getMessage());
		}		
		return customerDatabase;
	}

	/**
	 * this method deserializes the serialized reservation file and assigns
	 * the re-constructed object into a List
	 * @return reservDatabase
	 */
	@Override
	public List<Reservation> getReservationDatabase() {

		List<Reservation> reservDatabase = null;
		try{
			reservDatabase = (List<Reservation>) Utilities.deserializeObject(reservationFile);
		}catch(IOException | ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		return reservDatabase;
	}

	
	/**
	 * this method takes as input a customers list and serializes
	 * it in the file that has been passed in as input in the constructor
	 * @param List<Customer> : customer file we want to serialize
	 */
	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {
		Utilities.serializeObject(custs, customerFile);
	}

	/**
	 * this method takes as input a  Reservation list and serializes
	 * it in the file that has been passed in as input in the constructor
	 * @param List<Reservation> : Reservation file we want to serialize
	 */
	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		Utilities.serializeObject(reservs, reservationFile);
	}
}
