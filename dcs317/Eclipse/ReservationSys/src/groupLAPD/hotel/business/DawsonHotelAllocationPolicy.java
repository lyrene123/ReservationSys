package groupLAPD.hotel.business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.AllocationPolicy;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.data.interfaces.ReservationDAO;
import groupLAPD.hotel.data.ReservationListDB;

public class DawsonHotelAllocationPolicy implements AllocationPolicy{
	
	private static final long serialVersionUID = 42031768871L;

	ReservationListDB reservation;
	
	public DawsonHotelAllocationPolicy(ReservationDAO reservation){
		this.reservation = (ReservationListDB) reservation;
	}
	@Override
	public Optional<Room> getAvailableRoom(LocalDate checkin, LocalDate checkout, RoomType roomType) {
		
		if(this.getAvailableFloor(checkin, checkout, roomType) == null)	
			return Optional.empty();
		
		List<Room> freeFloor = this.getAvailableFloor(checkin, checkout, roomType);
		
		int randomRoom = (int)(Math.random()*freeFloor.size());
		
		return Optional.of(freeFloor.get(randomRoom));
	}
	
	private List<Room> getAvailableFloor(LocalDate checkin, LocalDate checkout, RoomType roomType){
		List<Room> freeRoom = this.reservation.getFreeRooms(checkin, checkout, roomType);
		
		List<Room> floor1 = new ArrayList<>();
		List<Room> floor2 = new ArrayList<>();
		List<Room> floor3 = new ArrayList<>();
		List<Room> floor4 = new ArrayList<>();
		List<Room> floor5 = new ArrayList<>();
		List<Room> floor6 = new ArrayList<>();
		List<Room> floor7 = new ArrayList<>();
		List<Room> floor8 = new ArrayList<>();
		for(Room room : freeRoom){
			if(room.getRoomType().equals(RoomType.NORMAL)){
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
			}
			
			
			ArrayList<List<Room>> NormalfloorSize = new ArrayList<>();
			NormalfloorSize.add(floor1);
			NormalfloorSize.add(floor2);
			NormalfloorSize.add(floor3);
			NormalfloorSize.add(floor4);
			NormalfloorSize.add(floor5);
			
			ArrayList<Integer> SuitefloorSize = new ArrayList<>();
			SuitefloorSize.add(floor6.size());
			SuitefloorSize.add(floor7.size());
			
			//List<Room> mostAvailableNormalFloor = Collections.max(NormalfloorSize);
			//int mostAvailableSuiteFloor =  NormalfloorSize.lastIndexOf(Collections.max(SuitefloorSize));
			
			
			
			/*

			if(floor1.size() >= floor2.size()){
				if(floor1.size() >= floor3.size()){
					if(floor1.size() >= floor4.size()){
						if(floor1.size() >= floor5.size()){
							return floor1;
						}
					}
				}
			}

			if(floor2.size() > floor1.size()){
				if(floor2.size() >= floor3.size()){
					if(floor2.size() >= floor4.size()){
						if(floor2.size() >= floor5.size()){
							return floor2;
						}
					}
				}
			}

			if(floor3.size() > floor1.size()){
				if(floor3.size() > floor2.size()){
					if(floor3.size() >= floor4.size()){
						if(floor3.size() >= floor5.size()){
							return floor3;
						}
					}
				}
			}

			if(floor4.size() > floor1.size()){
				if(floor4.size() > floor2.size()){
					if(floor4.size() > floor3.size()){
						if(floor4.size() >= floor5.size()){
							return floor4;
						}
					}
				}
			}
			
			if(floor5.size() > floor1.size()){
				if(floor5.size() > floor2.size()){
					if(floor5.size() > floor3.size()){
						if(floor5.size() > floor4.size()){
							return floor5;
						}
					}
				}
			}
			
			if(floor6.size() >= floor7.size()){
				//System.out.println(floor6.toString());
				return floor6;
			} else if(floor7.size() > floor6.size()){
				return floor7;
			}

			if(roomType.equals(RoomType.PENTHOUSE))
				return floor8;
			
		//System.out.println(floor6.toString());
		 * 
		 */
		return null;
	}

	private static <E extends Comparable<E>> E max(List<E> list){
		
		
		return null;
		
	}
}
