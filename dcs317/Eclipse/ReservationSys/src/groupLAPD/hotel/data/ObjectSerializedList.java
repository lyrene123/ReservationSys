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

public class ObjectSerializedList implements ListPersistenceObject{



	private String roomFile;
	private String customerFile;
	private String reservationFile;

	public ObjectSerializedList(String roomFile, String customerFile, String reservFile){
		this.roomFile = roomFile;
		this.customerFile = customerFile;
		this.reservationFile = reservFile;
	}



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

	@Override
	public void saveCustomerDatabase(List<Customer> custs) throws IOException {

		Utilities.serializeObject(custs, customerFile);
	}

	@Override
	public void saveReservationDatabase(List<Reservation> reservs) throws IOException {
		
		Utilities.serializeObject(reservs, reservationFile);
	}




}
