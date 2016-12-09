package groupLAPD.hotel.ui;

import dw317.hotel.business.interfaces.*;
import dw317.hotel.data.interfaces.*;
import groupLAPD.hotel.business.*;
import groupLAPD.hotel.data.*;

public class TextApp {
	public static void main(String[] args) {
		HotelFactory factory = 
				DawsonHotelFactory.DAWSON;
		CustomerDAO customerDb =
				new CustomerListDB(new ObjectSerializedList
						("datafiles/database/rooms.ser" ,
							"datafiles/database/customers.ser",
							"datafiles/database/reservations.ser"));
		ReservationDAO reservationDb = 
				new ReservationListDB(new ObjectSerializedList
						("datafiles/database/rooms.ser" ,
							"datafiles/database/customers.ser",
							"datafiles/database/reservations.ser"));

		Hotel model = new Hotel(factory, customerDb, reservationDb);
		TextView view = new TextView(model);
		TextController controller = new TextController(model);
		controller.run();
	}
}
