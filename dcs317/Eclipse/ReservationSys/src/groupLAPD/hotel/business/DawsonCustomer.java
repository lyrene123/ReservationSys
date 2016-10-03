/**
 * 
 */
package groupLAPD.hotel.business;

import java.util.Optional;
import dw317.hotel.business.interfaces.Customer;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;

/**
 * The DawsonCustomer class implements every behavior of Customer's
 * interface. It overrides all the abstract methods and adds some
 * characteristic to its class. 
 * 
 * @author Pengkim Sy
 * @version September, 2016
 */
public class DawsonCustomer implements Customer{

	private static final long serialVersionUID = 42031768871L;
	private Name name;
	private Email email;
	private CreditCard creditCard;
	
	/**
	 * DawsonCustomer() has no parameter, and it will set every member of
	 * this class to null.
	 */
	public DawsonCustomer(){
		this.name = null;
		this.email = null;
		this.creditCard = null;
	}
	
	/**
	 * This 3 parameters constructor will set the value to name and email,
	 * and it also sets credit card to null.
	 * 
	 * @param firstName
	 * @param lastName
	 * 			firstName and lastName will be assigned to name.
	 * @param email
	 */
	public DawsonCustomer(String firstName, String lastName, String email){
		
		try{
			this.name = new Name(firstName, lastName);
			this.email = new Email(email);
			this.creditCard = null;
		} catch (IllegalArgumentException e){
			System.out.println("\t" + e.getMessage());
		}
	}

	/**
	 * This method is to compare the Customer in the parameter to 
	 * DawsonCustomer base on email in both objects.
	 *  
	 * @param Customer
	 * @return a positive number if Customer is lexicographically greater
	 * 			 than the Customer argument, a value of 0 if both Customer
	 * 			 objects are equal, a value of negative number if Customer
	 * 			 is lexicographically  less than the Customer argument.
	 */
	@Override
	public int compareTo(Customer customer) {
		return this.email.compareTo(customer.getEmail());
	}
	
	/**
	 * This method is to see if the object in parameter is equals to 
	 * DawsonCustoomer. Two customers is equal if they have the same email
	 *  and the same name.
	 *  
	 * @param Object
	 * @return boolean
	 */
	@Override
	public final boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		DawsonCustomer other = (DawsonCustomer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * This method will return a CreditCard, and if there's no CreditCard,
	 * it will return empty.
	 * 
	 * @return Optional<CreditCard>
	 * @since 1.8
	 */
	@Override
	public Optional<CreditCard> getCreditCard() {		
		Optional<CreditCard> card = Optional.ofNullable(this.creditCard);
		return card;
	}	
	
	/**
	 * This method is get Email of a DawsonCutomer.
	 * @return Email
	 */
	@Override
	public Email getEmail() {
		return this.email;
	}
	
	/**
	 * This method is get Name of a DawsonCutomer.
	 * 
	 * @return Name
	 * 			Name has both firstName and lastName as written in Name class
	 */
	@Override
	public Name getName(){
		return new Name(this.name.getFirstName(), this.name.getLastName());
	}	
	
	/**
	 * This method will return a hashCode number of DawsonCustomer base on
	 * Email and Name.
	 * 
	 * @return hashCode
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * This method will set a CreditCard, and if there's no CreditCard to
	 * be assigned, it will set default CreditCard to null.
	 * 
	 * @return void
	 * @since 1.8
	 */
	@Override
	public void setCreditCard(Optional<CreditCard> card) {

		this.creditCard = card.orElse(null);
	}	

	/**
	 * This method will return a string of Email, Name and CreditCard type and 
	 * number (if available) separated by "*". 
	 * For example, pengkimsy@gmail.com*Pengkim*Sy*VISA*1234567988
	 * 
	 * @return email*firstName*lastName*CreditCard type*CreditCard number
	 */
	@Override
	public String toString() {
		String str = "";
		try{
			str = this.email.toString() + "*" + this.name.getFirstName() + "*" 
				+ this.name.getLastName() + "*" 
				+ ((this.creditCard == null) ? "" : this.creditCard.getType())
				+ "*" 
				+ ((this.creditCard == null) ? "" : this.creditCard.getNumber());
		} catch (NullPointerException e){
			str = "";
		}		
		return str;
	}
}
