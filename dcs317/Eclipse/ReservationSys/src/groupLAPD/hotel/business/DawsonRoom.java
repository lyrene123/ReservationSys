/**
 * 
 */
package groupLAPD.hotel.business;
import dw317.hotel.business.interfaces.Room;
import dw317.hotel.business.*;

/**
 * The DawsonRoom class implements the Room interface and inherits 
 * the general attributes and characteristics of a hotel room from the
 * Room interface
 * @author Lyrene Labor
 * @version September 2016
 */
public class DawsonRoom implements Room {
	
	//declaring class fields
	private static final long serialVersionUID = 42031768871L; //for serializable
	private int roomNumber; //the room number
	private RoomType roomType; //the room type
	
	/**
	 * The 2-parameter constructor method accepts input values and assigns them to the
	 * room number and room type fields of the DawsonRoom class 
	 * @param roomNumber The room number
	 * @param roomType The room type
	 * @throws IllegalArgumentException If roomNumber is invalid
	 */
	public DawsonRoom(int roomNumber, RoomType roomType)throws IllegalArgumentException{
		//validate if the input value for the room number is valid
		//before assigning it to the field
		this.roomNumber = validateRoomNumber(roomNumber);
		this.roomType = roomType;
	}//end of constructor
	
	
	/**
	 * The validateRoomNumber will accept a room number value as input and 
	 * will checks if it is a valid dawson hotel room number
	 * @param roomNumber The room number
	 * @return an integer value representing the validated room number
	 * @throws IllegalArgumentException If the room number value is invalid
	 */
	private static int validateRoomNumber(int roomNumber)throws IllegalArgumentException{
		
		//check if the room number has 3 digits only
		int length = String.valueOf(roomNumber).length();
		if(length!=3){
			//throw an exception if not a 3-digit number
			throw new IllegalArgumentException("Invalid room number value - must be a three digit "
					+ " number");
		}
		
		
		//check if the 1st digit is between 1 to 8
		int floorNum = roomNumber/100; //get 1st digit
		if(!(floorNum>=1 && floorNum<=8)){
			//throw an exception if 1st digit not between 1-8
			throw new IllegalArgumentException("Invalid room number value - the first digit "
					+ "representing the floor number must be between 1 to 8");
		}
		
		//check if the last two number is between 01-08 
		int doorNumber = roomNumber%100;
		if(!(doorNumber>=0.01 && doorNumber<=0.08)){
			//throw an exception if last 2 digits not between 01-08
			throw new IllegalArgumentException("Invalid room number value - the last two digits "
					+ "representing the door number must be between 01 to 08");
		}
		
		return roomNumber;//return validated roomNumber
	
	}//end of validateRoomNumber
	

	/**
	 * The getRoomNumber method  returns the integer value od the room number
	 * @return the roomNumber - int value
	 */
	public int getRoomNumber() {
		return this.roomNumber;
	}//end of getRoomNumber method

	/**
	 * The getRoomType method returns the room type enum value
	 * @return the roomType - enum roomType
	 */
	public RoomType getRoomType() {
		return this.roomType;
	}//end of getRoomType method
	
	/**
	 * The getFloor method returns the value of the floor number
	 *  where the room is located
	 * @return an integer value representing the floor number
	 */
	public int getFloor(){
		return this.roomNumber / 100;
	}//end of getFloor method
	
	/**
	 * The getNumber method returns the number the room on a particular floor
	 * @return an integer value representing the number of the room 
	 *        
	 */
	public int getNumber(){
		return this.roomNumber % 10;
	}//end of getNumber method


	/**
	 * The overridden final hashCode method returns an value representing 
	 * the hash code of the room number
	 * @return int value representing a hash code 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31; 
		int result = 1;
		//calculate the hash code for integer room number 
		result = prime * result + this.roomNumber;
		return result;//return the hash code
	}//end of hashCode method


	/**
	 * The overridden final equals method will compare two room objects
	 * if they are instance of the same class and if they have the same
	 * room number
	 * @return a boolean value - if true or not that the objects are equal 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		//if two objects point to same reference address,
		//then they are the same object
		if (this == obj) {
			return true;
		}
		
		//if the input object is null, return false
		if (obj == null) {
			return false;
		}
		//if the input object is not an instance of DawsonRoom
		//return false if that's the case
		if (!(obj instanceof DawsonRoom)) {
			return false;
		}
		//cast the input object to a DawsonRoom object
		DawsonRoom other = (DawsonRoom) obj;
		//check if both objects have the same room number
		//if not, return false
		if (this.roomNumber != other.roomNumber) {
			return false;
		}
		return true; //if none of the above if statements are true,
		//return true
	} //end of equals method
	
	/**
	 * The toString method returns a formatted string containing
	 * the room number and the room type
	 * @return a reference to a String containing room number and 
	 *         room type
	 */
	@Override
	public String toString(){
		return this.roomNumber + "*" + this.roomType.toString();
	}//end of toString method
	
	
	/**
	 * The compareTo method compares two room objects based on
	 * their room number and returns an integer 1,-1 or 0 if
	 * the current room object's room number bigger, smaller or equal
	 * than the compared room object's room number
	 * @param roomNumber The room number
	 * @return an integer value of 1, -1 or 0
	 */
	public int compareTo(Room roomNumber){
		
		if(this.roomNumber > roomNumber.getRoomNumber()){
			//if the current room number is bigger, return 1
			return 1;
		}
		else if (this.roomNumber < roomNumber.getRoomNumber()){
			//if the current room number is smaller, return -1
			return -1;
		}
		else {
			//if the current number is equals to the compared object
			//return 0
			return 0;
		}
	}
	
	

	
	

}
