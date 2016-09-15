/**
 * 
 */
package dw317.hotel.business.interfaces;
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
	public RoomType getRoomType();
	public int getRoomNumber();
	public int getFloor();
	public int getNumber();
	
	/**
	 * The RoomType enum holds constants for normal, suite and penthouse rooms
	 * @author Lyrene Labor
	 * @version September 2016
	 */
	//enum of type RoomType containing constants for Normal, Suite and Penthouse
	public enum RoomType {   
		NORMAL, SUITE, PENTHOUSE;   
		/**
		 * The overridden toString method returns the String value of the enum
		 * in lower case
		 * @return a reference to a String 
		 */
		@Override
		public String toString() {
			return this.name().toLowerCase();
		}
	} 

}
