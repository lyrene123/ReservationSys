package dw317.lib.creditcard;

import java.io.Serializable;

/**
 * @author Daniel Bezerra
 *
 */

public interface CreditCard extends Serializable {
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

	public enum CardType {
		MASTERCARD, VISA, AMEX;
		public String toString() {
			return this.name().toLowerCase();
		}
	}
}
