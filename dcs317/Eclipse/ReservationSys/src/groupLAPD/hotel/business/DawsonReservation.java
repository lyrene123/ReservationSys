package groupLAPD.hotel.business;

import java.time.DateTimeException; 
import java.time.LocalDate;
import java.time.Period;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

/**
 * This is Reservation class, it allows the Customer to Make a Reservation
 * By Taking as input the CheckIn Date and the CheckOut Date, his
 * Customer info, and his Room.
 * 
 * @author Ali Dali
 *
 */

public class DawsonReservation implements Reservation {

	private static final long serialVersionUID = 42031768871L;

	private final Customer ACUSTOMER;
	private final Room AROOM;
	private int inYear;
	private int inMonth;
	private int inDay;
	private int outYear;
	private int outMonth;
	private int outDay;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;


	/**
	 * Constructor, uses the isValid method to validate the input 
	 * if input is Valid it'll assign parameters to fields;
	 * @param ACUSTOMER
	 * @param AROOM
	 * @param check in Year
	 * @param check in Month
	 * @param check in Day
	 * @param check out Year
	 * @param check out Month
	 * @param check out Day
	 * @since 1.8
	 */
	public DawsonReservation(Customer ACUSTOMER, Room AROOM, int inYear, 
			int inMonth, int inDay, int outYear, int outMonth, int outDay){

		this.ACUSTOMER = ACUSTOMER;
		this.AROOM = AROOM;

		//Using isValid() to validate data and if valid assign values 
		//to fields
		if(isValid(inYear, inMonth, inDay, outYear, outMonth, outDay)){
			this.inYear = inYear;
			this.inMonth = inMonth;
			this.inDay = inDay;
			this.outYear = outYear;
			this.outMonth = outMonth;
			this.outDay = outDay;
			checkInDate = checkInDate.of(inYear, inMonth, inDay);
			checkOutDate = checkOutDate.of(outYear, outMonth, outDay);
		}

	}//end of DawsonReservation(...)


	/** 
	 * the compareTo() method is overridden from the object class
	 * it compares an instance of a Reservation with another,
	 * according to their checkIn Date then their Room
	 * @Override
	 * @param an another Instance to compare with
	 * @return Returns a negative number, 0, or positive number depending
	 * if the first instance is smaller, equal to or bigger than the second  
	 */
	public int compareTo(Reservation otherRes){
		//checks checkIn Date is equal to other checkIn date
		if(this.checkInDate.compareTo(otherRes.getCheckInDate()) == 0){
			//check which Room is bigger
			return this.AROOM.compareTo(otherRes.getRoom());
		}else
			//checks which checkIn date is bigger
			return this.checkInDate.compareTo(otherRes.getCheckInDate());
	}//end of compareTo()


	/** 
	 * equals() Method checks if two objects are equal; Two Reservations are
	 * equal if the customer, Room, Check In Date check Out Date are equal.
	 * Or if they are of same class. 
	 * @Override
	 * @param an object to check equality with
	 * @return boolean true if they are equal, false if not
	 */
	@Override
	final public boolean equals(Object obj) {
		if (this == obj)//checks if same object
			return true;
		if (obj == null)//if object is null
			return false;
		if (getClass() != obj.getClass())//check if its the same class
			return false;
		//casting obj as DawsonReservation
		DawsonReservation other = (DawsonReservation) obj;
		if (ACUSTOMER == null) {//if customer null
			if (other.ACUSTOMER != null)//if other customer is not null
				return false;
			//if the first customer is not equal to the second
		} else if (!ACUSTOMER.equals(other.ACUSTOMER))
			return false;
		if (AROOM == null) {//checks Room is null
			if (other.AROOM != null)//checks if other room is not null
				return false;
			//checks if first room is not equal to second
		} else if (!AROOM.equals(other.AROOM))
			return false;
		if (checkInDate == null) {//checks checkIn Date if null
			//checks if other checkIn Date is null
			if (other.checkInDate != null)
				return false;
			//checks if checkIn date is not equal to other check in Date
		} else if (!checkInDate.equals(other.checkInDate))
			return false;
		if (checkOutDate == null) {//checks if checkOut Date is null
			//checks if other checkOut Date is null
			if (other.checkOutDate != null)
				return false;
			//checks if first checkOut date is not equal to other checkOut Date
		} else if (!checkOutDate.equals(other.checkOutDate))
			return false;
		return true;
	}//end of equals()


	/** 
	 * this method returns the CheckIn Date of a customer
	 * @since 1.8
	 * @return checkInDate
	 */
	public LocalDate getCheckInDate(){
		return checkInDate;
	}//end of getCheckInDate()


	/** 
	 * this method returns the CheckOut Date of a customer
	 * @since java 8 java.time
	 * @return checkOutDate
	 */
	public LocalDate getCheckOutDate(){
		return checkOutDate;
	}//end of getCheckOutDate()


	/**
	 * this method is overridden from the customer class
	 * it returns a deep copy of the customer.toString()
	 * @see DawsonCustomer
	 * @return a deep copy of the customer Info
	 * @Override
	 */
	public Customer getCustomer(){
		String fName = ACUSTOMER.getName().getFirstName();//getting first name
		String lName = ACUSTOMER.getName().getLastName();//getting last name
		String email = ACUSTOMER.getEmail().toString();//getting email
		//creating a deep copy of the Customer
		DawsonCustomer dcCustomer =  new DawsonCustomer(fName, lName, email);
		return dcCustomer;
	}//end of getCustomer


	/** 
	 * this method computes the numbers of days Left a customer
	 * has according to their checkIn Date and CheckOut Date;
	 * it uses the until() method thats in the LocalDate Class
	 * @return number of days left
	 * @since 1.8
	 */
	public int getNumberOfDays(){
		Period numDaysLeft;
		//getting number of days left from checkIn to checkOut Date
		numDaysLeft = checkInDate.until(checkOutDate);
		return numDaysLeft.getDays();//converts Period to Integer Type
	}//end of getNumberOfDays()


	/** 
	 * this method returns the room Info a customer is occupying
	 * @return AROOM 
	 */
	public Room getRoom(){
		return AROOM;
	}//end of getRoom()


	/**
	 * HashCode() allocates a place in memory
	 * @Override
	 * @return the place in memory of a Reservation
	 * @since 1.8
	 */ 
	@Override
	final public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ACUSTOMER == null) ? 0 : ACUSTOMER.hashCode());
		result = prime * result + ((AROOM == null) ? 0 : AROOM.hashCode());
		result = prime * result
				+ ((checkInDate == null) ? 0 : checkInDate.hashCode());
		result = prime * result
				+ ((checkOutDate == null) ? 0 : checkOutDate.hashCode());
		return result;
	}//end of hashCode()


	/** 
	 * overlap() method checks if two two Reservations overlap;
	 * First it checks if its the same rooms, Then if so it'll if the checkIn
	 * Date of one Reservation is before the checkIn Date of an other and
	 * checkOut Date of one Reservation is  after the checkOut date of the 
	 * other reservation.
	 * @param Reservation 
	 * @return boolean True if there is overlap, false otherwise.
	 * @since java 8.
	 * 
	 **/
	public boolean overlap(Reservation other){

		//Check if both Reservation have the same Room
		if(this.getRoom().equals(other.getRoom())){
			//Check if the dates overlap
			if(this.checkInDate.isBefore(other.getCheckInDate()) && 
					this.checkOutDate.isAfter(other.getCheckOutDate())){
				return true;
			}else if(other.getCheckInDate().isBefore(this.checkInDate) &&
					other.getCheckOutDate().isAfter(this.checkOutDate)){
				return true;
			}
		}
		return false;
	}//end of overlap()



	/**
	 * toString() is an overridden method from the Object class that give a 
	 * String representation of the object 
	 * @Override
	 * @return a String representation of the class
	 */
	public String toString(){
		//getting the user email
		String email = ACUSTOMER.getEmail().toString();
		int roomNumber = AROOM.getNumber();//getting user Room Number

		return email +"*"+ inYear +"*"+ inMonth +"*"+ inDay +"* "+ outYear 
				+"*"+ outMonth +"*"+ outDay +"*"+ roomNumber; 
	}//end of toString()


	/**
	 * isValid() method is used to validate the user input
	 * Makes sure that dates are well inputed and that
	 * the checkIn date is not after checkOut Date.
	 * @param check in Year
	 * @param check in Month
	 * @param check in Day
	 * @param check out Year
	 * @param check out Month
	 * @param check out Day
	 * @return true if data is valid, false if not
	 * @throws IllegalArgumentException()
	 * @since 1.8
	 */
	private boolean isValid(int inYear, int inMonth, int inDay, int outYear,
			int outMonth, int outDay) throws IllegalArgumentException{

		LocalDate checkInDate = null;//initializing checkIn Date
		LocalDate checkOutDate = null;

		//put it in a try because LocalDate throws a DateTimeException()
		try{
			// assigning input to CheckIn Date
			checkInDate = checkInDate.of(inYear, inMonth, inDay);
			checkOutDate = checkOutDate.of(outYear, outMonth, outDay);
			//if input invalid catch the DateTimeException thrown
		}catch(DateTimeException e){
			//checks if the invalid input was because of Month
			if((inMonth < 1 || outMonth < 1) || 
					(inMonth > 12 || outMonth > 12)){
				//throw illegalArgumentException saying that Month is invalid
				throw new IllegalArgumentException("Invalid Input-- Month "
						+ "must be between 1(January) to 12(December)");
				//checks if invalid input was because of Day
			}else if((inDay < 1 || outDay < 1) || 
					(inDay > 31 || outDay > 31)){
				//throw IllegalArgumentException saying that Day is invalid
				throw new IllegalArgumentException("Invaid Input-- Day must"
						+ " be between 1 to 31");
			}
		}
		//checks if checkInDate in after CheckOutDate
		if(checkInDate.isAfter(checkOutDate)){
			//throw IllegalArgumentExpection if true
			throw new IllegalArgumentException("Invalid Input-- checkout date"
					+ " cannot be before checkin Date");
		}

		return true;
	}//end of isValid()

}//end of class
