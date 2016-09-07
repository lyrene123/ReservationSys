
package dw317.lib.creditcard;

/**
 * @author Pengkim Sy
 *
 */
public abstract class AbstractCreditCard implements CreditCard{
	
	private static final long serialVersionUID = 42031768871L;
	private final CardType cardType;
	private final String number;
	
	public AbstractCreditCard(CardType cardType, String number) throws IllegalArgumentException{
		this.cardType = cardType;
		this.number = number;
		
		if (number == null || number == "" || cardType == null){
			throw new IllegalArgumentException();
		} 
	}
	
}
