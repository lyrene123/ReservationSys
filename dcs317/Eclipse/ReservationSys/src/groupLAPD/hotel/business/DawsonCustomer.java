/**
 * 
 */
package groupLAPD.hotel.business;

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
	private CreditCard creditCard;
	
	public DawsonCustomer(){
		this.name = null;
		this.email = null;
		this.creditCard = null;
	}
	
	public DawsonCustomer(String firstName, String LastName, String email){
		
		try{
			this.name = new Name(firstName, LastName);
			this.email = new Email(email);
			this.creditCard = null;
		} catch (IllegalArgumentException e){
			System.out.println("\t" + e.getMessage());
		}
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
		
		/*Optional<CreditCard> card = Optional.empty();
		try{
			 card = Optional.ofNullable(this.creditCard);
		} catch(IllegalArgumentException e){
			System.out.println("\t" + e.getMessage());
			card = Optional.empty();
		}*/
		
		Optional<CreditCard> card = Optional.ofNullable(this.creditCard);
		return card;
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {

		this.creditCard = card.orElse(null);
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;

		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		String str = "";
		try{
			str = this.email.toString() + "*" + this.name.getFirstName() + "*" 
				+ this.name.getLastName() + "*" + ((this.creditCard == null) ? "" : this.creditCard.getType()) + "*"
				+ ((this.creditCard == null) ? "" : this.creditCard.getNumber());
		} catch (NullPointerException e){
			str = "";
		}
		
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
