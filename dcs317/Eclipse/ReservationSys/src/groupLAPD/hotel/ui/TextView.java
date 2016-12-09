package groupLAPD.hotel.ui;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.AbstractCreditCard;
import dw317.lib.creditcard.CreditCard;

/**
 * TextView class which implements the Observer interface 
 * handles the display of all Hotel data and information queries
 * of a user made in the TextController class
 * @author Lyrene Labor
 * @version December 2016
 */
public class TextView implements Observer {

	/**
	 * The constructor takes as input an Observable object
	 * and adds this instance into the observer list of the
	 * Observable model
	 * @param model - an Observable object
	 */
	public TextView(Observable model) {
		model.addObserver(this); //register
	}
	
	/**
	 * The update method is called automatically when the model that this
	 * instance is observering updates itself and notifies all its observers
	 * (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		//optional reservation
		if(arg instanceof Optional<?>){
			displayReservation(arg);
		}
		//
		//customer
		if(arg instanceof Customer){
			displayCustomer(arg);
		}
		//
		//list of reservations
		if(arg instanceof List<?>){
			displayListReservation(arg);
		}
		
	}
	
	/**
	 * displayListReservation method takes as input an Object object
	 * and uses that object to display a number of reservations and
	 * a list of reservation
	 * @param arg - an Object object
	 */
	private void displayListReservation(Object arg){
		List<Reservation> reservs = (List<Reservation>)arg;
		System.out.println("\nYou have " + reservs.size() +  " reservations: ");
		if(!reservs.isEmpty()){
			for(int i = 0; i<reservs.size(); i++){
				System.out.println(reservs.get(i).toString());
			}
		}
	}////
	
	/**
	 * displayReservation method takes as input an Object object
	 * and uses it to display a reservation
	 * @param arg - an Object object
	 */
	private void displayReservation(Object arg){
		System.out.println("\nYour reservation is for: ");
		Optional<Reservation> reserv = (Optional<Reservation>)arg;
		if(reserv.isPresent()){
			Reservation dawsonReserv = reserv.get();
			Customer cust = dawsonReserv.getCustomer();			
			Name name = cust.getName();
			String fullname = name.getFullName();
			Email email = cust.getEmail();
			String emailstr = email.toString();
			System.out.println(fullname + "\n at email: " + emailstr);
			System.out.println("Check in on " + dawsonReserv.getCheckInDate());
			System.out.println("Check out on " + dawsonReserv.getCheckOutDate());
			System.out.println("Your room: " + dawsonReserv.getRoom().getRoomNumber()
					+ " is a " + dawsonReserv.getRoom().getRoomType() + " on the " 
					+ dawsonReserv.getRoom().getFloor() + "floor");
		}
		else{
			System.out.println("No reservation can be provided for your particular "
					+ "check in, check out dates and room number and room type");
		}
	}
	
	/**
	 * displayCustomer method takes as input an Object object
	 * and uses it to display a customer information
	 * @param arg - an Object object
	 */
	private void displayCustomer(Object arg){
		System.out.println("\nCustomer information");
		Customer cust = (Customer)arg;
		Name name = cust.getName();
		String fullname = name.getFullName();
		Email email = cust.getEmail();
		String emailstr = email.toString();
		Optional<CreditCard> card = cust.getCreditCard();
		
		
		System.out.println("Name: " + fullname);
		System.out.println("Email: " + emailstr);
		
		if(card.isPresent()){
			AbstractCreditCard creditCard = (AbstractCreditCard) card.get();			
			System.out.println("Credit Card: " + creditCard.toString());
		}
		else{
			System.out.println("\nNo credit card on file.");
		}
	}
	
	
	

}
