/**
 * 
 */
package dw317.lib.creditcard;



/**
 * The CreditCardTest class is used to test 
 * the CreditCard Interface. 
 * 
 * @author Daniel Bezerra
 * @version September 2016
 */
public class CreditCardTest implements CreditCard
{
	private static final long serialVersionUID = 42031768871L; // serial ID
	// Private member variables
	private CardType cardType; // credit card type
	private String number; // credit card number

	/**
	 * The CreditCardTest constructor sets the Credit type and its number.
	 * @param CardType The Credit Card type 
	 * @param number The number of the Credit Card
	 */
	public CreditCardTest(CardType cardType, String number){
		this.cardType = cardType;
		this.number = number;
	}
	/**
	 * The overridden getNumber method returns a 
	 * String containing the credit card number
	 * 
	 * @return A reference to a String
	 */
	@Override
	public String getNumber() {
		return this.number;
	} // end of getNumber method

	/**
	 * The getType method returns the credit card type
	 * 
	 * @returns A reference to a CreditType enum
	 */
	@Override
	public CardType getType() {
		return this.cardType;
	} // end of getType method

	/**
	 * The main method contains different CreditCardTest objects that
	 * are used to demonstrate that an object with an interface implementation
	 * may be used to create a polymorphic reference.
	 * @param args
	 * 
	 */

	public static void main(String[] args)
	{
		System.out.println("\nTesting the CrediCard Interface Class");
		System.out.println("****************************************");
		// Creates three CreditCardTest objects 
		CreditCardTest amexCard = new CreditCardTest(CardType.AMEX, "371604312147093");
		CreditCardTest visaCard = new CreditCardTest(CardType.VISA, "4774341863020040");
		CreditCardTest mastercardCard = new CreditCardTest(CardType.MASTERCARD, "5326385350239456");

		//Demonstrating hierarchical inheritance using the static method from
		// the CreditCard Interface
		System.out.println("Test 1: Calling the static method in the CreditCard interface");
		System.out.println("-------------------------------------------------------");
		CreditCard cardAmex = CreditCard.getInstance(amexCard.getType(),amexCard.getNumber());
		// Hierarchical inheritance - Amex Credit Card
		System.out.println("cardAmex Is an instance of Object: " 
				+ (cardAmex instanceof Object));
		System.out.println("cardAmex Is an instance of AbstractCreditCard: " 
				+ (cardAmex instanceof AbstractCreditCard));
		System.out.println("cardAmex Is an instance of Amex: " 
				+ (cardAmex instanceof Amex));
		System.out.println("cardAmex Is an instance of CreditCard: " 
				+ (cardAmex instanceof CreditCard));
		cardAmex = null;
		System.out.println("But if the reference is null the result is:" 
				+ (cardAmex instanceof Amex));
		System.out.println(" ");
		// Hierarchical inheritance - Visa Credit Card
		CreditCard cardVisa = CreditCard.getInstance(visaCard.getType(),visaCard.getNumber());
		System.out.println("cardVisa Is an instance of Object: " 
				+ (cardVisa instanceof Object));
		System.out.println("cardVisa Is an instance of AbstractCreditCard: " 
				+ (cardVisa instanceof AbstractCreditCard));
		System.out.println("cardVisa Is an instance of Visa: " 
				+ (cardVisa instanceof Visa));
		System.out.println("cardVisa Is an instance of CreditCard: " 
				+ (cardVisa instanceof CreditCard));
		cardAmex = null;
		System.out.println("But if the reference is null the result is:" 
				+ (cardVisa instanceof Visa));
		System.out.println(" ");
		// Hierarchical inheritance - MasterCard Credit Card
		CreditCard cardMastercard = CreditCard.getInstance(mastercardCard.getType(),mastercardCard.getNumber());
		System.out.println("cardMastercard Is an instance of Object: " 
				+ (cardMastercard instanceof Object));
		System.out.println("cardMastercard Is an instance of AbstractCreditCard: " 
				+ (cardMastercard instanceof AbstractCreditCard));
		System.out.println("cardMastercard Is an instance of MasterCard: " 
				+ (cardMastercard instanceof MasterCard));
		System.out.println("cardMastercard Is an instance of CreditCard: " 
				+ (cardMastercard instanceof CreditCard));
		cardAmex = null;
		System.out.println("But if the reference is null the result is:" 
				+ (cardMastercard instanceof Visa));
		System.out.println(" ");


		System.out.println("Test 2 - Using the CreditCard Interface as a reference data type");
		System.out.println("-------------------------------------------------------");
		// Reference of type CreditCard and assign an object of type Amex.
		CreditCard amexCard2 = new Amex("371604312147093");
		System.out.println("amexCard2 is a reference to an object of type " + amexCard2.getType()); 
		// Reference of type CreditCard and assign an object of type Visa.
		CreditCard visaCard2 = new Visa("4774341863020040");
		System.out.println("visaCard2 is a reference to an object of type " + visaCard2.getType()); 
		// Reference of type CreditCard and assign an object of type MasterCard.
		CreditCard mastercardCard2 = new MasterCard("5326385350239456");
		System.out.println("mastercardCard2 is a reference to an object of type " + mastercardCard2.getType()); 
		// Print the Card Type using the toString from CardType method 
		// inherited from CrediCard Interface. 

		System.out.println(" ");
		System.out.println("Test 3 - Checking the inheritance hierarchy");
		System.out.println("-------------------------------------------");
		// Checking the amexCard of type CreditCardTest hierarchy
		System.out.println("amexCard Is an instance of Object: " 
				+ (amexCard instanceof Object));
		System.out.println("amexCard Is an instance of CreditCardTest: " 
				+ (amexCard instanceof CreditCardTest));
		System.out.println("amexCard Is an instance of CreditCard: " 
				+ (amexCard instanceof CreditCard));
		System.out.println(" ");
		// Checking the visaCard of type CreditCardTest hierarchy
		System.out.println("visaCard Is an instance of Object: " 
				+ (visaCard instanceof Object));
		System.out.println("visaCard Is an instance of CreditCardTest: " 
				+ (visaCard instanceof CreditCardTest));
		System.out.println("visaCard Is an instance of CreditCard: " 
				+ (visaCard instanceof CreditCard));
		System.out.println(" ");
		// Checking the mastercardCard of type CreditCardTest hierarchy
		System.out.println("mastercardCard Is an instance of Object: " 
				+ (mastercardCard instanceof Object));
		System.out.println("mastercardCard Is an instance of CreditCardTest: " 
				+ (mastercardCard instanceof CreditCardTest));
		System.out.println("mastercardCard Is an instance of CreditCard: " 
				+ (mastercardCard instanceof CreditCard));
		System.out.println(" ");

		// Assigning null to the object - must return false from "instanceOf" 
		amexCard = null; 
		System.out.println("But if the reference is null the result is always false");
		System.out.println("amexCard Is an instance of Object: " 
				+ (amexCard instanceof Object)); // false expected
		System.out.println("amexCard Is an instance of CreditCardTest: " 
				+ (amexCard instanceof CreditCardTest)); // false expected
		System.out.println("amexCard Is an instance of CreditCard: " 
				+ (amexCard instanceof CreditCard)); // false expected
		System.out.println(" ");
		
	}

}




