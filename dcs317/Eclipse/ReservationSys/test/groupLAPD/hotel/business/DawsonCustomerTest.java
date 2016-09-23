/**
 * 
 */
package groupLAPD.hotel.business;

import java.util.Optional;

import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;

/**
 * @author kimhyonh
 *
 */
public class DawsonCustomerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testConstructor();
		
		/*DawsonCustomer customer = new DawsonCustomer("Pengkim", "Sy", "pengkimsy@gmail.com");
		Amex amex = new Amex("344322624384908");
		Optional<CreditCard> card = Optional.of(amex);
		customer.setCreditCard(card);
		System.out.println(customer.toString());*/
	}
	
	private static void testConstructor(){
		
		Amex amex = new Amex("344322624384908");
		Optional<CreditCard> card1 = Optional.of(amex);
		testConstructor("Case1: ", "Pengkim", "Sy", "pengkim@gmail.com", card1, true);
		
		Optional<CreditCard> card2 = Optional.empty();
		testConstructor("Case2: ", "Pengkim", "Sy", "pengkim@gmail.com", card2, false);
		
		
	}

	private static void testConstructor(String cases, String firstName, String lastName, String email,
			Optional<CreditCard> card, boolean expectedResult){
		System.out.println("\nTesting Constructor:");

				DawsonCustomer customer = new DawsonCustomer(firstName, lastName, email);
				customer.setCreditCard(card);
				System.out.println(customer.toString());
				System.out.println("Expected to run");

	}
}
