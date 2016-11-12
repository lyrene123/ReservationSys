package groupLAPD.hotel.data;

import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.RoomDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;

/**
 * Concrete class that provides RoomDAO functionality
 * @author Jaya
 */
public class RoomListDB implements RoomDAO {

	private List<Room> database;
	private final ListPersistenceObject listPersistenceObject;
		
	/**
	 * Constructor requires a ListPersistenceObject to read from file.
	 * @param listPersistenceObject
	 */
	public RoomListDB (ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getRoomDatabase();
	}

	@Override
	public List<Room> getRooms(RoomType roomType) {
		List<Room> result = new ArrayList<Room>();
		for (Room room : database) {
			if (room.getRoomType().equals(roomType))
				result.add(room);
		}
		return result;
	}

	@Override
	public String toString() {
		int num = database.size();
		StringBuilder str = new StringBuilder("Number of rooms in database: " + num);
		for (Room r : database) {
			str.append("\n" + r.toString());
		}
		return str.toString();
	}

}

