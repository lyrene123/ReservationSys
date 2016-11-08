package dw317.hotel.data;

/**
 * This exception signals that the provided Email address does 
 * not exist.
 * @author Ali Dali
 */
public class NonExistingCustomerException extends Exception {

	private static final long  serialVersionUID = 42031768871L;

	public NonExistingCustomerException(){
		super("The provided Email address does not exists.");
	}

	public NonExistingCustomerException(String message){
		super(message);
	}
}


