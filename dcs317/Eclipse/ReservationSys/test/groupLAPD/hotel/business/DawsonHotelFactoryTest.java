package groupLAPD.hotel.business;

import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import dw317.lib.creditcard.MasterCard;
import dw317.lib.creditcard.Visa;


public class DawsonHotelFactoryTest {

	public static void main(String[] args) {
		
		//Calling getCustomerInstance from DawsonHotelFactory 
		// and assigning to a reference type Customer.
		System.out.println("Testing DawsonHotelFactory Enum Class");
		System.out.println("-------------------------------------");
		Customer daniel = DawsonHotelFactory.DAWSON.getCustomerInstance
				("Daniel", "Cavalcanti", "danielhbc@gmail.com");
		Customer henrique = DawsonHotelFactory.DAWSON.getCustomerInstance
				("Daniel", "Cavalcanti", "danielhbc@gmail.com");
		Customer lyrene = DawsonHotelFactory.DAWSON.getCustomerInstance
				("Lyrene", "Labor", "laborlyrene@gmail.com");
		
		//Instantiating Credit Card objects and Optional Credit Card instances. 
		Amex danielcard = new Amex("371604312147093");
		Optional<CreditCard> danielcard1 = Optional.of(danielcard);
		
		MasterCard henriquecard = new MasterCard("5326385350239456");
		Optional<CreditCard> henriquecard1 = Optional.of(henriquecard);
		
		Visa lyrenecard = new Visa("4774341863020040");
		Optional<CreditCard> lyrenecard1 = Optional.of(lyrenecard);
		
		
		//Setting the Credit Card for the created customers
		daniel.setCreditCard(danielcard1);
		lyrene.setCreditCard(lyrenecard1);
		henrique.setCreditCard(henriquecard1);
		// Getting Full Name, email and Credit Card
		System.out.println("Displaying Customers's Full Name");
		System.out.println("--------------------------------");
		System.out.println(daniel.getName());
		System.out.println(henrique.getName());
		System.out.println(lyrene.getName());
		System.out.println(" ");
		System.out.println("Getting Customers's Email");
		System.out.println("-------------------------");
		System.out.println(daniel.getEmail());
		System.out.println(henrique.getEmail());
		System.out.println(lyrene.getEmail());
		System.out.println(" ");
		System.out.println("Getting Customers's Credit Card");
		System.out.println("-------------------------------");
		System.out.println(daniel.getCreditCard());
		System.out.println(henrique.getCreditCard());
		System.out.println(lyrene.getCreditCard());
		System.out.println(" ");
		//System.out.println(daniel.toString());
		System.out.println("Comparing two Customers using Equals method");
		System.out.println("----------------------------------------------");
		System.out.println("Is daniel equal to lyrene: " + daniel.equals(lyrene));
		System.out.println("Is daniel equal to henrique: " + daniel.equals(henrique));
		System.out.println("Is lyrene equal to henrique: " + lyrene.equals(henrique));
		
		System.out.println(" ");
		System.out.println("Comparing two Customers using CompareTo method");
		System.out.println("----------------------------------------------");
		System.out.println("Is daniel equal to lyrene: " + daniel.compareTo(lyrene));
		System.out.println("Is daniel equal to lyrene: " + lyrene.compareTo(daniel));
		System.out.println("Is daniel equal to henrique: " + daniel.compareTo(henrique));
		System.out.println("Is daniel equal to henrique: " + henrique.compareTo(daniel));
		System.out.println(" ");
		System.out.println("Calling HashCode method");
		System.out.println("-----------------------");
		System.out.println("The HashCode of daniel object is: " + daniel.hashCode());
		System.out.println("The HashCode of henrique object is: " + henrique.hashCode());
		System.out.println("The HashCode of lyrene object is: " + lyrene.hashCode());
		System.out.println(" ");
		System.out.println("Calling toString method");
		System.out.println("-----------------------");
		System.out.println(daniel.toString());
		System.out.println(henrique.toString());
		System.out.println(lyrene.toString());
		
		

	}

}
