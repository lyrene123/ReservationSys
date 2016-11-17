package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

import dw317.hotel.business.RoomType;

public interface AllocationPolicy extends Serializable {

	/**
	 * Returns the availlable room.
	 * 
	 * @return The visit of the next client booking the room.
	 */
	Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType);
}
