package dw317.lib.creditcard;

import java.io.Serializable;

/**
 * The CreditCard class is an interface class containing abstract methods that define
 * the behavior of classes related to credit cards. When a class implements this interface,
 * it has to provide all the interface's abstract methods.   
 * @author Daniel Bezerra
 * @version September 2016
 */

public interface CreditCard extends Serializable {
	//abstract methods
	String getNumber();
	CardType getType();

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

	//enum of type CardType containing constants for MasterCard, Visa and Amex
	public enum CardType {
		MASTERCARD, VISA, AMEX;
		public String toString() {
			return this.name().toLowerCase();
		}
	}
}
