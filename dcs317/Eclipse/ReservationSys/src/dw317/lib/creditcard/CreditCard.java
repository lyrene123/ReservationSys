package dw317.lib.creditcard;

import java.io.Serializable;

/**
 * The CreditCard class is an interface class containing abstract methods that define
 * the behavior of classes related to credit cards. When a class implements this interface,
 * it has to provide all the interface's abstract methods. This class provides 
 * the CardType enum and extends to Serializable interface
 * @author provided by teacher
 * @version September 2016
 */

public interface CreditCard extends Serializable {
	//abstract methods
	String getNumber();
	CardType getType();

	
	/**
	 * The getInstance method creates an instance of type CreditCard
	 * based on the input values of the method and returns the reference to
	 * the instance made
	 * @param type Credit card type 
	 * @param number credit card number
	 * @return a reference to a CreditCard instance
	 */
	// Credit card factory method based on the type
	public static CreditCard getInstance(CardType type, String number) {
		CreditCard card = null;
		switch (type) {
		case MASTERCARD:
			card = new MasterCard(number);
			break;
		case VISA:
			card = new Visa(number);
			break;
		case AMEX:
			card = new Amex(number);
		}
		return card;
	}

	
	/**
	 * The enum of type CardType holds constants MasterCard, Visa and Amex
	 * @author Provided by the teacher
	 * @version September 2016
	 */
	//enum of type CardType containing constants for MasterCard, Visa and Amex
	public enum CardType {
		//constants
		MASTERCARD, VISA, AMEX;
		
		/**
		 * The overridden toString method returns the String value of
		 * enum in lower case
		 * @return a reference to a String 
		 */
		public String toString() {
			return this.name().toLowerCase();
		}
	}
}
