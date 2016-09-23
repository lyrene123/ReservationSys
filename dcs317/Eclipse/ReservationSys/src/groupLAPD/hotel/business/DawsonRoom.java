 /**
 * 
 */
package groupLAPD.hotel.business;

import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.*;

/**
 * The DawsonRoom class implements the Room interface and inherits the general
 * attributes and characteristics of a hotel room from the Room interface
 * 
 * @author Lyrene Labor
 * @version September 2016
 */
public class DawsonRoom implements Room {

	// declaring class fields:
	
	// for serializable
	private static final long serialVersionUID = 42031768871L; 
	private final int roomNumber; // the room number
	private final RoomType roomType; // the room type

	/**
	 * The 2-parameter constructor method accepts input values and assigns them
	 * to the room number and room type fields of the DawsonRoom class
	 * 
	 * @param roomNumber
	 *            The room number
	 * @param roomType
	 *            The room type
	 * @throws IllegalArgumentException
	 *             If roomNumber is invalid
	 */
	public DawsonRoom(int roomNumber, RoomType roomType) throws IllegalArgumentException {
		// validate if the input value for the room number is valid
		// before assigning it to the field
		this.roomNumber = validateRoomNumber(roomNumber);
		// validate room type before assigning
		this.roomType = validateRoomType(roomType, this.roomNumber);
	}// end of constructor
	


	/**
	 * The compareTo method compares two room objects based on their room number
	 * and returns an integer 1,-1 or 0 if the current room object's room number
	 * bigger, smaller or equal than the compared room object's room number
	 *  
	 * @param roomNumber
	 *            The room number of type Room
	 * @return an integer value of 1, -1 or 0
	 */
	@Override
	public final int compareTo(Room roomNumber) {

		//if both objects refer to the same reference variable
		//then they are for sure equals
		if(this==roomNumber){
			return 0;
		}
		
		if (this.roomNumber > roomNumber.getRoomNumber()) {
			// if the current room number is bigger, return 1
			return 1;
		} else if (this.roomNumber < roomNumber.getRoomNumber()) {
			// if the current room number is smaller, return -1
			return -1;
		} else {
			// if the current number is equals to the compared object
			// return 0
			return 0;
		}
	}

	/**
	 * The overridden final equals method will compare two room objects if they
	 * are instance of the same class and if they have the same room number
	 * @param obj A reference of type Object
	 * @return a boolean value - if true or not that the objects are equal
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		// if two objects point to same reference address,
		// then they are the same object
		if (this == obj) {
			return true;
		}

		// if the input object is null, return false
		if (obj == null) {
			return false;
		}
		// if the input object is not an instance of DawsonRoom
		// return false if that's the case
		if (!(obj instanceof DawsonRoom)) {
			return false;
		}
		// cast the input object to a DawsonRoom object
		DawsonRoom other = (DawsonRoom) obj;
		
		// if roomType is set to null, check if other is null as well
		if (this.roomType == null) {
			if (other.roomType != null) {
				return false;
			}
		} else {
			// check if both objects don't have the same room type
			if (!(this.roomType.equals(other.roomType))) {
				return false;
			}
		}
		
		// check if both objects have the same room number
		// if not, return false
		if (this.roomNumber != other.roomNumber) {
			return false;
		}

		
		return true; // if none of the above if statements are true,
		// return true
	} // end of equals method

	/**
	 * The getFloor method returns the value of the floor number where the room
	 * is located
	 * 
	 * @return an integer value representing the floor number
	 */
	public int getFloor() {
		return this.roomNumber / 100;
	}// end of getFloor method

	/**
	 * The getNumber method returns the number the room on a particular floor
	 * 
	 * @return an integer value representing the number of the room
	 * 
	 */
	public int getNumber() {
		return this.roomNumber % 10;
	}// end of getNumber method

	/**
	 * The getRoomNumber method returns the integer value od the room number
	 * 
	 * @return the roomNumber - int value
	 */
	public int getRoomNumber() {
		return this.roomNumber;
	}// end of getRoomNumber method

	/**
	 * The getRoomType method returns the room type enum value
	 * 
	 * @return the roomType - enum roomType
	 */
	public RoomType getRoomType() {
		return this.roomType;
	}// end of getRoomType method

	/**
	 * The overridden final hashCode method returns an value representing the
	 * hash code of the room number
	 * 
	 * @return int value representing a hash code
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomType==null) ? 0 : roomType.hashCode());
		// calculate the hash code for integer room number
		result = prime * result + this.roomNumber;
		return result;// return the hash code
	}// end of hashCode method

	/**
	 * The toString method returns a formatted string containing the room number
	 * and the room type
	 * 
	 * @return a reference to a String containing room number and room type
	 */
	@Override
	public String toString() {
		return this.roomNumber + "*" + this.roomType.toString();
	}// end of toString method

	/**
	 * The validateRoomNumber will accept a room number value as input and will
	 * checks if it is a valid dawson hotel room number
	 * 
	 * @param roomNumber
	 *            The room number
	 * @return an integer value representing the validated room number
	 * @throws IllegalArgumentException
	 *             If the room number value is invalid
	 */
	private static int validateRoomNumber(int roomNumber) throws IllegalArgumentException {

		// check if the room number has 3 digits only
		int length = String.valueOf(roomNumber).length();
		if (length != 3) {
			// throw an exception if not a 3-digit number
			throw new IllegalArgumentException("Invalid room number value - must be a three digit"
					+ " number. 1st digit must be the floor number from 1 to 8 and the"
					+ " last two digits must be the number of the room from" + " 01 to 08");
		}

		// check if the 1st digit is between 1 to 8 and followed
		int floorNum = roomNumber / 100; // get 1st digit
		if (!(floorNum >= 1 && floorNum <= 8)) {
			// throw an exception if 1st digit not between 1-8
			throw new IllegalArgumentException("Invalid room number value - the first digit "
					+ "representing the floor number must be between 1 to 8");
		}

		// check if the middle value of the room number is 0 or not
		if ((roomNumber / 10) % 10 != 0) {
			throw new IllegalArgumentException("Invalid room number value - the last two digits "
					+ "representing the door number must be between 01 to 08");
		}

		
		int doorNumber = roomNumber % 10;

		//check the last digit of the room number
		if (floorNum >= 1 && floorNum <= 5) {
			if (!(doorNumber >= 1 && doorNumber <= 8)) {
				// throw an exception if last 2 digits not between 1-8
				throw new IllegalArgumentException("Invalid room number value - the last digit "
						+ "representing the door number must be between 1 to 8 for normal rooms");
			}
		}
		else if(floorNum>=6 && floorNum <= 7){
			if(!(doorNumber>=1 && doorNumber<=4)){
				throw new IllegalArgumentException("Invalid room number value - the last digit "
						+ "representing the door number must be between 1 to 4 for suite rooms");
			}
		}
		else{
			if(doorNumber!=1){
				throw new IllegalArgumentException("Invalid room number value - the last digit "
						+ "representing the door number must be 1 for the penthouse room");
			}
		}
		

		return roomNumber;// return validated roomNumber

	}// end of validateRoomNumber

	/**
	 * The validateRoomType will accept a room type enum value as input and will
	 * checks if it is a valid dawson hotel room type
	 * 
	 * @param roomType
	 *            the room type enum
	 * @param roomNumber the integer room number
	 * @return the validated room type enum
	 * @throws IllegalArgumentException if room type invalid
	 */
	private static RoomType validateRoomType(RoomType roomType, int roomNumber) 
			throws IllegalArgumentException {

		// throw exception if roomType is null
		if (roomType == null) {
			throw new IllegalArgumentException("Invalid room type - must exist and must "
		+ "not be null");
		}

		// check if the room type is normal for normal rooms
		if (roomNumber >= 101 && roomNumber <= 508) {
			if (!(roomType.equals(RoomType.NORMAL))) {
				throw new IllegalArgumentException("Invalid room type - your room type must"
			+ " be of type normal");
			}
		}

		// check if the room type is suite for rooms in suite
		if (roomNumber >= 601 && roomNumber <= 704) {
			if (!(roomType.equals(RoomType.SUITE))) {
				throw new IllegalArgumentException("Invalid room type - your room type must" 
			+ " be of type suite");
			}
		}

		// check if the room type is penthouse for the penthouse room
		if (roomNumber == 801) {
			if (!(roomType.equals(RoomType.PENTHOUSE))) {
				throw new IllegalArgumentException("Invalid room type - your room type must"
			+ " be of type penthouse");
			}
		}

		return roomType;// return validated room type

	}// end of validateRoomType method

}
