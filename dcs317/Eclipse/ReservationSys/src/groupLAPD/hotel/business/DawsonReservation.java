package groupLAPD.hotel.business;

import java.time.LocalDate;
import java.time.Period;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.hotel.business.interfaces.Room;

public class DawsonReservation implements Reservation {

	private static final long serialVersionUID = 42031768871L;

	private final DawsonCustomer ACUSTOMER;
	private final Room AROOM;
	private int inYear;
	private int inMonth;
	private int inDay;
	private int outYear;
	private int outMonth;
	private int outDay;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	
	public DawsonReservation(DawsonCustomer ACUSTOMER, Room AROOM, int inYear, int inMonth, int inDay, 
								int outYear, int outMonth, int outDay){
		
		this.ACUSTOMER = ACUSTOMER;
		this.AROOM = AROOM;
		checkInDate = checkInDate.withYear(inYear);			this.inYear = inYear;
		checkInDate = checkInDate.withMonth(inMonth);		this.inMonth = inMonth;
		checkInDate = checkInDate.withDayOfMonth(inDay);	this.inDay = inDay;
		checkOutDate = checkInDate.withYear(outYear);		this.outYear = outYear;
		checkOutDate = checkInDate.withMonth(outMonth);		this.outMonth = outMonth;
		checkOutDate = checkInDate.withDayOfMonth(outDay);	this.outDay = outDay;
		
			 
	}
	
	
	public Customer getCustomer(){
		String fName = ACUSTOMER.getName().getFirstName();
		String lName = ACUSTOMER.getName().getLastName();
		String email = ACUSTOMER.getEmail().toString();
		DawsonCustomer dcCustomer =  new DawsonCustomer(fName, lName, email);
		return dcCustomer;
		
	}
	
	public int getNumberOfDays(){
		Period numDaysLeft;
		numDaysLeft = checkInDate.until(checkOutDate);
		return numDaysLeft.getDays();
	}

	public LocalDate getCheckInDate(){
		return checkInDate;
	}
	
	public LocalDate getCheckOutDate(){
		return checkOutDate;
	}
	
	public Room getRoom(){
		return AROOM;
	}
	

	public int compareTo(Reservation otherRes){
		
		if(this.checkInDate.compareTo(otherRes.getCheckInDate()) == 0){
			return this.AROOM.compareTo(otherRes.getRoom());
		}else
			return this.checkInDate.compareTo(otherRes.getCheckInDate());
		
	}
	
	
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
	}


	@Override
	final public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DawsonReservation other = (DawsonReservation) obj;
		if (ACUSTOMER == null) {
			if (other.ACUSTOMER != null)
				return false;
		} else if (!ACUSTOMER.equals(other.ACUSTOMER))
			return false;
		if (AROOM == null) {
			if (other.AROOM != null)
				return false;
		} else if (!AROOM.equals(other.AROOM))
			return false;
		if (checkInDate == null) {
			if (other.checkInDate != null)
				return false;
		} else if (!checkInDate.equals(other.checkInDate))
			return false;
		if (checkOutDate == null) {
			if (other.checkOutDate != null)
				return false;
		} else if (!checkOutDate.equals(other.checkOutDate))
			return false;
		return true;
	}
	
	
	@Override
	public String toString(){
		String email = ACUSTOMER.getEmail().toString();
		int roomNumber = AROOM.getNumber();
		
		return email +"*"+ inYear +"*"+ inMonth +"*"+ inDay +"* "+ outYear +"*"+ outMonth +"*"+ outDay +"*"+ roomNumber; 
	}
	
}
