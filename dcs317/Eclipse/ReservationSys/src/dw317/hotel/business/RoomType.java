/**
 * 
 */
package dw317.hotel.business;

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