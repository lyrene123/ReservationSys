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

public class DawsonHotelAllocationPolicy implements AllocationPolicy{
	
	private static final long serialVersionUID = 42031768871L;

	ReservationDAO reservation;
	
	public DawsonHotelAllocationPolicy(ReservationDAO reservation){
		this.reservation = reservation;
	}
	
	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		
		if(this.getAvailableFloor(checkin, checkout, roomType) == null)	
			return Optional.empty();
		
		List<Room> availableFloor = this.getAvailableFloor(checkin, checkout, roomType);
		
		int randomRoom = (int)(Math.random()*availableFloor.size());		
		return Optional.of(availableFloor.get(randomRoom));
	}
	
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
			
		if(roomType.equals(RoomType.NORMAL)){
			if(findArrayWithTheBiggestSize(normalFloor).size() != 0)
				return findArrayWithTheBiggestSize(normalFloor);
		}
			
		if(roomType.equals(RoomType.SUITE)){
			if(findArrayWithTheBiggestSize(suiteFloor).size() != 0)
				return findArrayWithTheBiggestSize(suiteFloor);
		}

		if(roomType.equals(RoomType.PENTHOUSE)){
			if(floor8.size() != 0)
				return floor8;
		}

		return null;
	}

	private static <E extends Comparable<E>> List<E> findArrayWithTheBiggestSize(ArrayList<List<E>> list){		
		List<E> max = list.get(0);
		
		for(int i=0; i<list.size(); i++){
			if(list.get(i).size() > max.size())
				max = list.get(i);
		}
		return max;		
	}
}
