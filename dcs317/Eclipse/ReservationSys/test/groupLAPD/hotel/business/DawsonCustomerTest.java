/**
 * 
 */
package groupLAPD.hotel.business;

import java.util.Optional;

import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.CreditCard.CardType;
import dw317.lib.creditcard.Visa;

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
		testGetName();
		testGetEmail();
		testGetCreditCard();
		testSetCreditCard();
	}
	
	private static void testConstructor(){
		
		System.out.println("Testing constructor and toString()");
		
		Amex amex = new Amex("344322624384908");
		testConstructor("Case1: a valide parameter", "Pengkim", "Sy", "pengkim@gmail.com", amex, true);
		
		Visa nullVisa = null;
		testConstructor("Case2: a null creditcard", "Pengkim", "Sy", "pengkim@gmail.com", nullVisa, true);
		
		testConstructor("Case3: not a valid email", "Pengkim", "Sy", "pengkimgmail.com", amex, false);
		testConstructor("Case4: not a valid first name", "", "Sy", "pengkimgmail.com", amex, false);
		testConstructor("Case5: not a valid last name", "Pengkim", "", "pengkim.sy@gmail.com", amex, false);
		
		System.out.println();
	}

	private static void testConstructor(String cases, String firstName, String lastName, String email,
			 CreditCard creditCard, boolean expectedResult){
		
		System.out.println("\t" + cases + " :");
		
		DawsonCustomer customer = new DawsonCustomer(firstName, lastName, email);
		Optional<CreditCard> card = Optional.ofNullable(creditCard);
		customer.setCreditCard(card);
		
		if(expectedResult == true){
			System.out.println("\tReturn of toString() : " + customer.toString());
			System.out.println("\tExpected to run");
			System.out.println();
		} else {
			System.out.println("\tExpected to fail");
			System.out.println();
		}
	}
	
	private static void testGetName(){
		
		System.out.println("Testing getName() method");	
		
		String email = "pengkim@gmail.com"; // always a valid email because I only test getName()
		
		testGetName("Case 1: a valid firstname and lastname", "Pengkim", "Sy", email, true);
		testGetName("Case 2: not a valid firstname", "", "Sy", email, false);
		testGetName("Case 3: not a valid lastname", "Pengkim", "", email, false);
		
		System.out.println();
	}
	
	private static void testGetName(String cases, String firstName, String lastName, String email, boolean expectedResult){
		
		System.out.println("\t" + cases);
		
		DawsonCustomer customer = new DawsonCustomer(firstName, lastName, email);
		
		if(expectedResult == true){
			System.out.println("\tReturn of getName() : " + customer.getName());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tExpected to fail");
			System.out.println();
		}
	}
	
	private static void testGetEmail(){
		
		System.out.println("Testing getEmail() method");
		
		String firstName = "Pengkim"; // First name is always valid because I test getEmail()
		String lastName = "Sy"; // last name is always valid because I only test getEmail()
		
		testGetEmail("Case 1: a valid email address", firstName, lastName, "pengkim@gmail.com", true);
		testGetEmail("Case 2: not a valid email", firstName, lastName, ".pengkim@gmail.com", false);
		
		System.out.println();
	}
	
	private static void testGetEmail(String cases, String firstName, String lastName, String email, boolean expectedResult){
		
		System.out.println("\t" + cases);
		
		DawsonCustomer customer  = new DawsonCustomer(firstName, lastName, email);
		
		if(expectedResult == true){
			System.out.println("\tReturn of getEmail() : " + customer.getEmail());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tExpected to fail");
			System.out.println();
		}
		
	}
	
	private static void testGetCreditCard(){
		
		System.out.println("Testing getCreditCard() method");
		
		Amex amex = new Amex("344322624384908");
		testGetCreditCard("Case 1: a valid Amex card", amex, true);
		
		Amex nullCard = null;
		testGetCreditCard("Case 2: a null credit card", nullCard, false);
		
		Visa visa = new Visa("4774341863020040");
		testGetCreditCard("Case 3: a valid Visa card", visa, true);
		
		/*Visa visa2 = null;
		try{
			visa2 = new Visa("a56513213");
		} catch(IllegalArgumentException e){
			testGetCreditCard("Case 4: not a valid Visa card", visa2, false);
			//System.out.println("\t" + e.getMessage());
		}*/
		
		System.out.println();
	}
	
	private static void testGetCreditCard(String cases, CreditCard creditCard, boolean expectedResult){
		
		System.out.println("\t" + cases);
		
		DawsonCustomer customer = new DawsonCustomer("Pengkim", "Sy", "pengkim@gmail.com");		
		Optional<CreditCard> card = Optional.ofNullable(creditCard);
		customer.setCreditCard(card);
		
		if(expectedResult == true){
			System.out.println("\tReturn of getCreditCard()" + customer.getCreditCard());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tExpected to fail");
			System.out.println();
		}
		
	}
	
	private static void testSetCreditCard(){
		
		System.out.println("Testing setCreditCard(Optional<CreditCard> card) method");
		
		Amex amex = new Amex("344322624384908");
		testSetCreditCard("Case 1: set a valid credit card", amex, true);
		
		Amex nullCard = null;
		testSetCreditCard("Case 2: set a null card", nullCard, false);
		
		/*Amex amex2 = null;
		try{
			amex2 = new Amex("65646a54654");
		} catch(IllegalArgumentException e){
			testSetCreditCard("Case 3: test an invalid card", amex2, false);
		}*/
		System.out.println();
	}
	
	private static void testSetCreditCard(String cases, CreditCard creditCard, boolean expectedResult){
		
		System.out.println("\t" + cases);
		
		DawsonCustomer customer = new DawsonCustomer("Pengkim", "Sy", "pengkim@gmail.com");
		Optional<CreditCard> card = Optional.ofNullable(creditCard);
		customer.setCreditCard(card);
		
		if(expectedResult == true){
			System.out.println("\tsetCreditCard() : " + customer.getCreditCard());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tExpeted to fail");
			System.out.println();
		}
	}
		
}
