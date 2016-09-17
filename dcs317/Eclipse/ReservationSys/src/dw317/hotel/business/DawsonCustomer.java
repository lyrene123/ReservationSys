/**
 * 
 */
package dw317.hotel.business;

import java.util.Optional;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Email;
import dw317.lib.Name;
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
		// TODO Auto-generated method stub
		return this.creditCard.orElse("");;
	}

	@Override
	public void setCreditCard(Optional<CreditCard> card) {
		// TODO Auto-generated method stub
		this.creditCard = card.orElse("");
	}	
	
	@Override
	public final boolean equals(Object obj){
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;		
		
		this.name.getFirstName().toUpperCase();	
		this.name.getLastName().toUpperCase();
		
		Name other = (Name) obj;
		
		other.getFirstName().toUpperCase();
		other.getLastName().toUpperCase();
		
		if(this.email != other.email)
			return false;
		
		if(this.name.getFirstName() == null){
			if(other.getFirstName() != null){
				return false;
			}
		}else if(!this.name.getFirstName().equals(other.getFirstName())){
			return false;
		}
		
		if(this.name.getLastName() == null){
			if(other.getLastName() != null){
				return false;
			}
		}else if(!this.name.getLastName().equals(other.getLastName())){
			return false;
		}
		
		return true;		
	}
	
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result 
				+ ((this.name.getFirstName().toUpperCase() == null) ? 0 : this.name.getFirstName().toUpperCase().hashCode());
		result = prime * result 
				+ ((this.name.getLastName().toUpperCase() == null) ? 0 : this.name.getLastName().toUpperCase().hashCode());
		result = prime * result + ((this.email == null) ? 0 : this.email.hashCode());
		
		return result;
	}
	
	
	/*@Override
	public String toString(){
		String str;
		str = 
	}*/	
	
	@Override
	public int compareTo(Customer customer) {
		// TODO Auto-generated method stub		
		return this.email.compareToIgnoreCase(customer.getEmail());
	}
	
}
