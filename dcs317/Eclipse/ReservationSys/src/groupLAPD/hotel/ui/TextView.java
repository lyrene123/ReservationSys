package groupLAPD.hotel.ui;

import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.AbstractCreditCard;
import dw317.lib.creditcard.CreditCard;

public class TextView implements Observer {

	public TextView(Observable model) {
		model.addObserver(this); //register
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		
		//reservation
		if(arg instanceof Optional<?>){
			displayReservation(arg);
		}
		
		//customer
		if(arg instanceof Customer){
			displayCustomer(arg);
		}
		
	}
	
	private void displayReservation(Object arg){
		
		Optional<Reservation> reserv = (Optional<Reservation>)arg;
		if(reserv.isPresent()){
			Reservation dawsonReserv = reserv.get();
			Customer cust = dawsonReserv.getCustomer();
			displayCustomer(cust);
		}	
	}
	
	private void displayCustomer(Object arg){
		System.out.println("\nCustomer information");
		Customer cust = (Customer)arg;
		Name name = cust.getName();
		String fullname = name.getFullName();
		Email email = cust.getEmail();
		String emailstr = email.toString();
		Optional<CreditCard> card = cust.getCreditCard();
		AbstractCreditCard creditCard = (AbstractCreditCard) card.get();			
		
		System.out.println("Name: " + fullname);
		System.out.println("Email: " + emailstr);
		
		if(card.isPresent()){
			System.out.println("Credit Card: " + creditCard.toString());
		}
		else{
			System.out.println("\nNo credit card on file.");
		}
	}
	
	
	

}
