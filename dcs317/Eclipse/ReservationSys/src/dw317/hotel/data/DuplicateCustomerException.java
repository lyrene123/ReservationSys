package dw317.hotel.data;

/**
 * This exception signals that the provided customer email exists already.
 */
public class DuplicateCustomerException extends Exception {
	private static final long  serialVersionUID = 42031768871L;
			
	public DuplicateCustomerException(){
		super("The provided Email address already exists.");
	}

	public DuplicateCustomerException(String message) {
		super(message);
	}
}

