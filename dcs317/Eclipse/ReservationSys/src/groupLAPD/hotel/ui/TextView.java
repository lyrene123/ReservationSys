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

public class TextView implements Observer {

	public TextView(Observable model) {
		model.addObserver(this); //register
	}
	
	
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
	
	
	private void displayListReservation(Object arg){
		List<Reservation> reservs = (List<Reservation>)arg;
		System.out.println("\nYou have " + reservs.size() +  " reservations: ");
		if(!reservs.isEmpty()){
			for(int i = 0; i<reservs.size(); i++){
				System.out.println(reservs.get(i).toString());
			}
		}
	}////
	
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
