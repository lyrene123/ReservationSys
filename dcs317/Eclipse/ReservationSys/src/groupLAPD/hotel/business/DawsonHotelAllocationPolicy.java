package groupLAPD.hotel.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;
import groupLAPD.hotel.data.ReservationListDB;
import groupLAPD2016.util.ListUtilities;

/**
 * The DawsonHotelAllocationPolicy class that implements the AllocationPolicy 
 * interface. It gets a ReservationDAO object in its constructor
 * and it keeps a reference to this object as an instance variable.
 * 
 * @version November 2016
 * 
 * @author Pengkim Sy
 * @author Daniel Cavalcanti
 */
public class DawsonHotelAllocationPolicy implements AllocationPolicy{
	
	private static final long serialVersionUID = 42031768871L;

	ReservationDAO reservation;
	
	/**
	 * The DawsonHotelAllocationPolicy constructor gets a ReservationDAO object
	 * and keeps a reference to this object as an instance variable.
	 * 
	 * @param ReservationDAO reservation
	 */
	public DawsonHotelAllocationPolicy(ReservationDAO reservation){
		this.reservation = reservation;
	}
	
	/**
	 * The getAvailableRoom method checks for an available room of the given room 
	 * type during the desired period. If multiple rooms have the same positive 
	 * number of available rooms it chooses the lowest floor. If there are no 
	 * free rooms available with the search criteria, return an empty Optional.
	 * 
	 * @param LocalDate checkin - The Check In Date provided for the search period.
	 * @param LocalDate checkout - The Check Out Date provided for the search period.
	 * @param RoomType roomType - a room type (Normal, Suite or Penthouse)
	 * 
	 * @return Optional<Room> - if there's an available room.
	 * @return Optional.empty - if there's no available room.
	 */	
	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		
		if(this.getAvailableFloor(checkin, checkout, roomType) == null)	
			return Optional.empty();
		
		List<Room> availableFloor = this.getAvailableFloor(checkin, checkout, roomType);
		
		int randomRoom = (int)(Math.random()*availableFloor.size());		
		return Optional.of(availableFloor.get(randomRoom));
	}
	
	/**
	 * The getAvailableFloor method checks the floor with the most number of free rooms
	 * of the given room type during the desired period. If multiple rooms have the same
	 * positive number of available rooms it chooses the lowest floor. If there are no 
	 * free rooms available with the search criteria, return null.
	 * 
	 * @param LocalDate checkin - Checkin date
	 * @param LocalDate checkout - Checkout date
	 * @param RoomType roomType - a room type (Normal, Suite or Penthouse)
	 * 
	 * @return List<Room> - a floor that has the most availabe rooms
	 * @return null - If there's no available room, it returns null.
	 */
	private List<Room> getAvailableFloor(LocalDate checkin, LocalDate checkout, RoomType roomType){
		
		List<Room> freeRoom = this.reservation.getFreeRooms(checkin, checkout);
		
		List<Room> floor1 = new ArrayList<>();
		List<Room> floor2 = new ArrayList<>();
		List<Room> floor3 = new ArrayList<>();
		List<Room> floor4 = new ArrayList<>();
		List<Room> floor5 = new ArrayList<>();
		List<Room> floor6 = new ArrayList<>();
		List<Room> floor7 = new ArrayList<>();
		List<Room> floor8 = new ArrayList<>();
		
		for(Room room : freeRoom){
			switch (room.getFloor()){
				case 1:
					floor1.add(room);
					break;
				case 2:
					floor2.add(room);
					break;
				case 3:
					floor3.add(room);
					break;
				case 4:
					floor4.add(room);
					break;
				case 5:
					floor5.add(room);
					break;
				case 6:
					floor6.add(room);
					break;
				case 7:
					floor7.add(room);
					break;
				case 8:
					floor8.add(room);
					break;
			}				
		}			
			
		ArrayList<List<Room>> normalFloor = new ArrayList<>();
		normalFloor.add(floor1);
		normalFloor.add(floor2);
		normalFloor.add(floor3);
		normalFloor.add(floor4);
		normalFloor.add(floor5);
			
		ArrayList<List<Room>> suiteFloor = new ArrayList<>();
		suiteFloor.add(floor6);
		suiteFloor.add(floor7);
			
		// findLargestElementsIn2dArrayList method is to find the list with the
		// largest size.
		if(roomType.equals(RoomType.NORMAL)){
			if(ListUtilities.findLargestElementsIn2dArrayList(normalFloor).size() != 0)
				return ListUtilities.findLargestElementsIn2dArrayList(normalFloor);
		}
			
		if(roomType.equals(RoomType.SUITE)){
			if(ListUtilities.findLargestElementsIn2dArrayList(suiteFloor).size() != 0)
				return ListUtilities.findLargestElementsIn2dArrayList(suiteFloor);
		}

		if(roomType.equals(RoomType.PENTHOUSE)){
			if(floor8.size() != 0)
				return floor8;
		}

		return null;
	}

}
