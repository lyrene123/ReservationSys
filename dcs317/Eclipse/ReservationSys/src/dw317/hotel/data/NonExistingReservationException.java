package dw317.hotel.data;

/**
 * This exception signals that the provided Reservation does
 * not exist.
 * @author Ali Dali
 */
public class NonExistingReservationException extends Exception {

	private static final long  serialVersionUID = 42031768871L;

	public NonExistingReservationException() {
		super("The provided reservation does not exists.");
	}

	public NonExistingReservationException(String message){
		super(message);
	}
}
	
