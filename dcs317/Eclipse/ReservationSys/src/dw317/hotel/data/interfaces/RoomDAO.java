package dw317.hotel.data.interfaces;

import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;

public interface RoomDAO {
	
	/**
	 * Returns a list of all rooms with a given RoomType
	 * @param roomType
	 * @return list of rooms
	 */
	List<Room> getRooms(RoomType roomType);

}
