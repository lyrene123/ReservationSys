/**
 * 
 */
package dw317.hotel.business.interfaces;

import java.io.Serializable;
import java.util.Optional;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;


/**
 * @author kimhyonh
 *
 */
public interface Customer extends Comparable<Customer>, Serializable{
	
	Name getName();
	Email getEmail();
	Optional<CreditCard> getCreditCard();
	void setCreditCard(Optional<CreditCard> card);

}
