/**
 * 
 */
package dw317.hotel.business;

import java.util.Optional;
import dw317.hotel.business.interfaces.Customer;
import dw317.lib.*;
import dw317.lib.creditcard.CreditCard;

/**
 * @author kimhyonh
 *
 */
public class DawsonCustomer implements Customer{

	private static final long serialVersionUID = 42031768871L;
	private Name name;
	private Email email;
	private Optional<CreditCard> creditCard;
	
	public DawsonCustomer(String firstName, String LastName, String email){
		
		this.name = new Name(firstName, LastName);
		this.email = new Email(email);
		this.creditCard = null;
	}
	
	@Override
	public Name getName(){
		return new Name(this.name.getFirstName(), this.name.getLastName());
	}
	
	@Override
	public Email getEmail() {
		// TODO Auto-generated method stub
		return this.email;
	}

	@Override
	public Optional<CreditCard> getCreditCard() {
		return this.creditCard.orElse(CreditCard.getInstance(null, ""));
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creditCard == null) ? 0 : creditCard.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String str = this.email.toString() + "*" + this.name.getFirstName() + "*" 
				+ this.name.getLastName() + "*" + this.creditCard.orElse(CreditCard.getInstance(null, "")).getType().toString() 
				+ "*" + this.creditCard.orElse(CreditCard.getInstance(null, "")).getNumber();
		
		return str;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DawsonCustomer other = (DawsonCustomer) obj;
		if (creditCard == null) {
			if (other.creditCard != null)
				return false;
		} else if (!creditCard.equals(other.creditCard))
			return false;
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

	@Override
	public int compareTo(Customer customer) {
		// TODO Auto-generated method stub		
		return this.email.compareTo(customer.getEmail());
	}

	
}
