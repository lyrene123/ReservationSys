package dw317.hotel.data;

/**
 * This exception signals that the provided Reservation exists already.
 * @author Ali Dali     
 */
public class DuplicateReservationException extends Exception{
	
	private static final long  serialVersionUID = 42031768871L;

	public DuplicateReservationException() {
		super("The provided reservation already exists.");
	}

	public DuplicateReservationException(String message){
		super(message);
	}
	
}
