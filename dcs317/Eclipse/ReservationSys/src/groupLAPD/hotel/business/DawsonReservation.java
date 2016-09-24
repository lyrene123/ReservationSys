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
	 * @param inYear
	 * @param inMonth
	 * @param inDay
	 * @param outYear
	 * @param outMonth
	 * @param outDay
	 */
	public DawsonReservation(Customer ACUSTOMER, Room AROOM, int inYear, int inMonth, int inDay, 
			int outYear, int outMonth, int outDay){

		this.ACUSTOMER = ACUSTOMER;
		this.AROOM = AROOM;
		
		//Using isValid() to validate data and if valid assign values to fields
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
		DawsonCustomer dcCustomer =  new DawsonCustomer(fName, lName, email);//creating a deep copy of the Customer
		return dcCustomer;
	}//end of getCustomer

	/** 
	 * this method computes the numbers of days Left a customer
	 * has according to their checkIn Date and CheckOut Date;
	 * it uses the until() method thats in the LocalDate Class
	 * @return number of days left
	 */
	public int getNumberOfDays(){
		Period numDaysLeft;
		numDaysLeft = checkInDate.until(checkOutDate);//getting number of days left from checkIn to checkOut Date
		return numDaysLeft.getDays();//converts Period to Integer Type
	}//end of getNumberOfDays()

	/** 
	 * this method returns the CheckIn Date of a customer
	 * @see dw317.hotel.business.interfaces.Reservation#getCheckInDate()
	 * @return checkInDate
	 */
	public LocalDate getCheckInDate(){
		return checkInDate;
	}//end of getCheckInDate()

	/** 
	 * this method returns the CheckOut Date of a customer
	 * @see dw317.hotel.business.interfaces.Reservation#getCheckOutDate()
	 * @return checkOutDate
	 */
	public LocalDate getCheckOutDate(){
		return checkOutDate;
	}//end of getCheckOutDate()

	/** 
	 * this method returns the room Info a customer is occupying
	 * @see dw317.hotel.business.interfaces.Reservation#getRoom()
	 * @return AROOM 
	 */
	public Room getRoom(){
		return AROOM;
	}//end of getRoom()

	/** 
	 * the compareTo() method is overridden from the object class
	 * it compares an instance of a Reservation with another,
	 * according to their checkIn Date then their Room
	 * @Override
	 * @param an another Instance to compare with
	 * @return Returns a negative number, 0, or positive number depending
	 * 			if the first instance is smaller, equal to or bigger than the second  
	 */
	public int compareTo(Reservation otherRes){

		if(this.checkInDate.compareTo(otherRes.getCheckInDate()) == 0){//checks checkIn Date is equal to other checkIn date
			return this.AROOM.compareTo(otherRes.getRoom());//check which Room is bigger
		}else
			return this.checkInDate.compareTo(otherRes.getCheckInDate());//checks which checkIn date is bigger
	}//end of compareTo()

	/**
	 * HashCode() 
	 * @Override
	 * @return the place in memory of a Reservation 
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
	 * equals() Method checks if two objects are equal;
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
		DawsonReservation other = (DawsonReservation) obj;//casting obj as DawsonReservation
		if (ACUSTOMER == null) {//if customer null
			if (other.ACUSTOMER != null)//if other customer is not null
				return false;
		} else if (!ACUSTOMER.equals(other.ACUSTOMER))//if the first customer is not equal to the second
			return false;
		if (AROOM == null) {//checks Room is null
			if (other.AROOM != null)//checks if other room is not null
				return false;
		} else if (!AROOM.equals(other.AROOM))//checks if first room is not equal to second
			return false;
		if (checkInDate == null) {//checks checkIn Date if null
			if (other.checkInDate != null)//checks if other checkIn Date is null
				return false;
		} else if (!checkInDate.equals(other.checkInDate))//checks if checkIn date is not equal to other check in Date
			return false;
		if (checkOutDate == null) {//checks if checkOut Date is null
			if (other.checkOutDate != null)//checks if other checkOut Date is null
				return false;
		} else if (!checkOutDate.equals(other.checkOutDate))//checks if first checkOut date is not equal to other checkOut Date
			return false;
		return true;
	}//end of equals()


	/**
	 * toString() is an overridden method from the Object class that give a 
	 * String representation of the object 
	 * @Override
	 * @return a String representation of the class
	 */
	public String toString(){
		String email = ACUSTOMER.getEmail().toString();//getting the user email
		int roomNumber = AROOM.getNumber();//getting user Room Number

		return email +"*"+ inYear +"*"+ inMonth +"*"+ inDay +"* "+ outYear +"*"+ outMonth +"*"+ outDay +"*"+ roomNumber; 
	}//end of toString()


	/**
	 * isValid() method is used to validate the user input
	 * Makes sure that dates are well inputed and that
	 * the checkIn date is not after checkOut Date.
	 * @param inYear
	 * @param inMonth
	 * @param inDay
	 * @param outYear
	 * @param outMonth
	 * @param outDay
	 * @return true if data is valid, false if not
	 */
	public boolean isValid(int inYear, int inMonth, int inDay, int outYear, int outMonth, int outDay){

		LocalDate checkInDate = null;//initializing checkIn Date
		LocalDate checkOutDate = null;

		//put it in a try because LocalDate throws a DateTimeException()
		try{
			checkInDate = checkInDate.of(inYear, inMonth, inDay);// assigning input to CheckIn Date
			checkOutDate = checkOutDate.of(outYear, outMonth, outDay);
		}catch(DateTimeException e){//if input invalid catch the DateTimeException thrown
			if((inMonth < 1 || outMonth < 1) || (inMonth > 12 || outMonth > 12)){//checks if the invalid input was because of Month
				throw new IllegalArgumentException("Invalid Input-- Month must be between 1(January) to 12(December)");//throw illegalArgumentException saying that Month is invalid
			}else if((inDay < 1 || outDay < 1) || (inDay > 31 || outDay > 31)){//checks if invalid input was because of Day
				throw new IllegalArgumentException("Invaid Input-- Day must be between 1 to 31");//throw IllegalArgumentException saying that Day is invalid
			}
		}
		if(checkInDate.isAfter(checkOutDate)){//checks if checkInDate in after CheckOutDate
			throw new IllegalArgumentException("Invalid Input-- checkout date cannot be before checkin Date");//throw IllegalArgumentExpection if true
		}
		
		return true;
	}//end of isValid()

}//end of class
