/**
 * 
 */
package groupLAPD.hotel.business;

import java.util.Optional;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
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
		testHashCode();
		testEqual();
		testCompareTo();
	}

	private static void testConstructor(){
		
		System.out.println("Testing constructor and toString()");
		
		Amex amex = new Amex("344322624384908");
		testConstructor("Case1: a valide parameter", "Pengkim", "Sy"
				, "pengkim@gmail.com", amex, true);
		
		Visa nullVisa = null;
		testConstructor("Case2: a null creditcard", "Pengkim", "Sy"
				, "pengkim@gmail.com", nullVisa, true);
		
		testConstructor("Case3: not a valid email", "Pengkim", "Sy"
				, "pengkimgmail.com", amex, false);
		testConstructor("Case4: not a valid first name", "", "Sy"
				, "pengkimgmail.com", amex, false);
		testConstructor("Case5: not a valid last name", "Pengkim", ""
				, "pengkim.sy@gmail.com", amex, false);
		
		System.out.println();
	}

	private static void testConstructor(String cases, String firstName
			, String lastName, String email, CreditCard creditCard
			, boolean expectedResult){
		
		System.out.println("   " + cases + " :");
		
		DawsonCustomer customer = new DawsonCustomer(firstName, lastName
					, email);
		Optional<CreditCard> card = Optional.ofNullable(creditCard);
		customer.setCreditCard(card);
		
		if(expectedResult == true){
			System.out.println("\tReturn of toString() : " 
					+ customer.toString());
			System.out.println("\tExpected to run");
			System.out.println();
		} else {
			System.out.println("\tExpected to fail");
			System.out.println();
		}
	}
	
	private static void testGetName(){
		
		System.out.println("Testing getName() method");	
		
		// always a valid email because I only test getName()
		String email = "pengkim@gmail.com";
		
		testGetName("Case 1: a valid firstname and lastname", "Pengkim", "Sy"
				, email, true);
		testGetName("Case 2: not a valid firstname", "", "Sy", email
				, false);
		testGetName("Case 3: not a valid lastname", "Pengkim", "", email
				, false);
		
		System.out.println();
	}
	
	private static void testGetName(String cases, String firstName
			, String lastName, String email, boolean expectedResult){
		
		System.out.println("   " + cases);
		
		DawsonCustomer customer = new DawsonCustomer(firstName, lastName
					, email);
		
		if(expectedResult == true){
			System.out.println("\tReturn of getName() : " 
					+ customer.getName());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tExpected to fail");
			System.out.println();
		}
	}
	
	private static void testGetEmail(){
		
		System.out.println("Testing getEmail() method");
		
		// First name is always valid because I test getEmail()
		String firstName = "Pengkim";
		// last name is always valid because I only test getEmail()
		String lastName = "Sy"; 
		
		testGetEmail("Case 1: a valid email address", firstName, lastName
				, "pengkim@gmail.com", true);
		testGetEmail("Case 2: not a valid email", firstName, lastName
				, ".pengkim@gmail.com", false);
		
		System.out.println();
	}
	
	private static void testGetEmail(String cases, String firstName
			, String lastName, String email, boolean expectedResult){
		
		System.out.println("   " + cases);
		
		DawsonCustomer customer  = new DawsonCustomer(firstName, lastName
				, email);
		
		if(expectedResult == true){
			System.out.println("\tReturn of getEmail() : " 
					+ customer.getEmail());
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
	
	private static void testGetCreditCard(String cases, CreditCard creditCard
			, boolean expectedResult){
		
		System.out.println("   " + cases);
		
		DawsonCustomer customer = new DawsonCustomer();		
		Optional<CreditCard> card = Optional.ofNullable(creditCard);
		customer.setCreditCard(card);
		
		if(expectedResult == true){
			System.out.println("\tReturn of getCreditCard(): " 
					+ customer.getCreditCard());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tReturn of getCreditCard(): " 
					+ customer.getCreditCard());
			System.out.println("\tExpected to fail");
			System.out.println();
		}
		
	}
	
	private static void testSetCreditCard(){
		
		System.out.println("Testing setCreditCard(Optional<CreditCard> card)"
				+ " method");
		
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
	
	private static void testSetCreditCard(String cases, CreditCard creditCard
			, boolean expectedResult){
		
		System.out.println("   " + cases);
		
		DawsonCustomer customer = new DawsonCustomer();
		Optional<CreditCard> card = Optional.ofNullable(creditCard);
		customer.setCreditCard(card);
		
		if(expectedResult == true){
			System.out.println("\tsetCreditCard() : " 
					+ customer.getCreditCard());
			System.out.println("\tExpected to run");
			System.out.println();
		} else{
			System.out.println("\tsetCreditCard() : " 
					+ customer.getCreditCard());
			System.out.println("\tExpeted to fail");
			System.out.println();
		}
	}
	
	private static void testHashCode(){
		
		System.out.println("Testing the hashCode method");
		
		DawsonCustomer customer1 = new DawsonCustomer("Pengkim", "Sy"
				, "Pengkim@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Pengkim", "Sy"
				, "Pengkim@gmail.com");
		testHashCode("Case 1: equal objects, same hash code", customer1
				, customer2, true);
		
		Amex amex = new Amex("344322624384908");
		Optional<CreditCard> card = Optional.ofNullable(amex);
		customer1.setCreditCard(card);
		testHashCode("Case 2: add a credit card to customer1", customer1
				, customer2, false);
		
		customer2 = new DawsonCustomer("James", "Bone"
				, "james.bone@hotmail.com");
		testHashCode("Case 3: not equal objects, different hash code"
				, customer1, customer2, false);
		
		System.out.println();
	}

	private static void testHashCode(String cases, Object obj1, Object obj2
			, boolean expectedResult){
		
		System.out.println("   " + cases);
		
		int hash1 = obj1.hashCode();
		int hash2 = obj2.hashCode();

		if(expectedResult == true){
			System.out.println("\tExpected result: same hash codes");
			System.out.println("\tobject 1 - "+ obj1 + ": " + hash1);
			System.out.println("\tobject 2 - "+ obj2 + ": " + hash2);
			System.out.println();
		} else{
			System.out.println("\tExpected result: different hash codes");
			System.out.println("\tobject 1 - "+ obj1 + ": " + hash1);
			System.out.println("\tobject 2 - "+ obj2 + ": " + hash2);
			System.out.println();
		}
	}
	
	private static void testEqual(){
		
		System.out.println("Testing equal() method");

		DawsonCustomer customer1 = new DawsonCustomer("Pengkim", "Sy"
				, "Pengkim@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Pengkim", "Sy"
				, "Pengkim@gmail.com");
		testEqual("Case 1: object1 = object2, expected \"true\"", customer1
				, customer2);
		
		customer2 = new DawsonCustomer("Pengkim", "Sy", "Pengkimsy@gmail.com");
		testEqual("Case 2: same name but different emal, expected \"false\""
				, customer1, customer2);
		
		customer2 = new DawsonCustomer("Pengkim", "King", "Pengkim@gmail.com");
		testEqual("Case 3: different last name, expected \"false\""
				, customer1, customer2);
		
		customer2 = new DawsonCustomer("Kim", "Sy", "Pengkim@gmail.com");
		testEqual("Case 4: different first name, expected \"false\""
				, customer1, customer2);
		
		System.out.println();
	}
	
	private static void testEqual(String cases, Object obj1, Object obj2){
		
		System.out.println("   " + cases);
		
		if(obj1.equals(obj2)){
			System.out.println("\tObject1 : " + obj1);
			System.out.println("\tObject2 : " + obj2);
			System.out.println("\tTrue: these two objects are equal");
			System.out.println();
		} else{
			System.out.println("\tObject1 : " + obj1);
			System.out.println("\tObject2 : " + obj2);
			System.out.println("\tFalse: these two objects are not equal");
			System.out.println();
		}
		
	}
	
	private static void testCompareTo(){
		
		System.out.println("Testing compareTo() method");
		
		DawsonCustomer customer1 = new DawsonCustomer("Pengkim", "Sy"
				, "Pengkim@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("Pengkim", "Sy"
				, "Pengkim@gmail.com");
		testCompareTo("Case 1: the same object", customer1, customer2
				, "Expected result : 0");
		
		customer2 = new DawsonCustomer("Pengkim", "Sy", "Pengkim@hotmail.com");
		testCompareTo("Case 2: different domain of the email", customer1
				, customer2, "Expected result : negative number");
		
		customer2 = new DawsonCustomer("Pengkim", "Sy", "PengkimSy@gmail.com");
		testCompareTo("Case 3: different userID of the email", customer1
				, customer2, "Expected result : negative number");

		System.out.println();
	}
	
	private static void testCompareTo(String cases, DawsonCustomer customer1
			, DawsonCustomer customer2, String expectedResult){
		
		System.out.println("   " + cases);
		System.out.println("\t" + expectedResult);
		
		int result = customer1.compareTo(customer2);
		System.out.println("\tObject1 : " + customer1);
		System.out.println("\tObject2 : " + customer2);
		System.out.println("\tResult from test : " + result);
		System.out.println();		
	}
		
}
