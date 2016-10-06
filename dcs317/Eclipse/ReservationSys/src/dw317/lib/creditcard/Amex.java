/**
 * 
 */
package dw317.lib.creditcard;

/**
 * The Amex class is a final implementation class that extends
 * from the AbstractCreditCard class. As a specific creditcard class,
 * the Amex class inherits the general data and behavior of a
 * creditcard from AbstractCreditCard.
 * 
 * @author Pengkim Sy
 * @version September 2016
 */
public final class Amex extends AbstractCreditCard  {
	
	//class data declaration
	private static final long serialVersionUID = 42031768871L;
	
	/**
	 * The Amex constructor calls the super constructor and sets 
	 * the Amex instance's credit card type and validated
	 * number.
	 * 
	 * @param number The Amex number of type String
	 * @throws IllegalArgumentException If number is not a valid 
	 *          Amex number
	 */
	public Amex(String number)throws IllegalArgumentException{
		super(CardType.AMEX,validateNumber(number));
	}//end of constructor
	
	/**
	 * The validateNumber method checks if the credit card number is 
	 * a valid Amex number
	 * 
	 * @param number The Amex number of type String
	 * @return A reference to a String containing the validated 
	 *          Amex number
	 * @throws IllegalArgumentException If number is not a valid 
	 *           Amex number
	 */
	private static String validateNumber(String number)
			throws IllegalArgumentException
	{
		if(number == null){
			throw new IllegalArgumentException("CreditCard number can't be"
					+ " null!!!");
		}
		
		String trimDigits = number.trim();
		if(trimDigits.isEmpty()){
			throw new IllegalArgumentException("There are no CreditCard"
					+ " number input.");
		}
		
		int numberLength = number.length();
		if(numberLength!=15){
			throw new IllegalArgumentException("The Amex card number must"
					+ " be exactly 15 digits.");
		}
		
		String subDigits = number.substring(0, 2);
		int firstDigits = Integer.parseInt(subDigits);
		if(firstDigits!=34)
		{
			if(firstDigits!=37)
			{
				throw new IllegalArgumentException("The Amex card number must"
						+ " be starting digits with 34 or 37.");
			}
			
		}
		
		return trimDigits;
	}
}
