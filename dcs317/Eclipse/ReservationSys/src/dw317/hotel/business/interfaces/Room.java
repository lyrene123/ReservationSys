/**
 * 
 */
package dw317.hotel.business.interfaces;
import dw317.hotel.business.*;
import java.io.Serializable;

/**
 * The room interface class contains the abstract methods that a class
 * related to hotel rooms needs to provide if this interface is implemented.
 * These abstract methods define the behavior of the class that implements
 * the Room interface. The Room interface extends to the Comparable and
 * Serializable interfaces.
 * @author Lyrene Labor
 * @version September 2016
 *
 */
public interface Room extends Comparable<Room>, Serializable {
	
	//abstract classes that classes need to provide
	//if this interface is implemented
	public RoomType getRoomType(); //method to return room type 
	public int getRoomNumber();//method to return full room number
	public int getFloor(); //method to return floor number
	public int getNumber(); //method to return number of room
	

}

