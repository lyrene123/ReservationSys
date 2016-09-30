/**
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;

/**
 * The Customer interface contains the abstract methods that a class
 * related to customer needs to provide if this interface is implemented.
 * These abstract methods define the behavior of the class that implements
 * the Customer interface. The Customer interface extends to Comparable and
 * Serializable interfaces.
 * 
 * @author Pengkim Sy
 * @version September, 2016
 * @since 1.8
 */
public interface Customer extends Comparable<Customer>, Serializable{
	
	// Method to get an optional credit card
	Optional<CreditCard> getCreditCard();
	// Method to return email
	Email getEmail(); 
	// Method to return the name
	Name getName(); 
	// Method to set a credit card
	void setCreditCard(Optional<CreditCard> card);
}
