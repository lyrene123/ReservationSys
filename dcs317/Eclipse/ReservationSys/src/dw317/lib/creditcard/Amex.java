/**
 * 
 */
package dw317.lib.creditcard;
/**
 * @author Pengkim Sy
 * @version September 2016
 */
public final class Amex extends AbstractCreditCard  {
	
	//class data declaration
	private static final long serialVersionUID = 42031768871L;
	
	
	public Amex(String number)throws IllegalArgumentException{
		super(CardType.AMEX,validateNumber(number));
	}//end of constructor
	
	
	private static String validateNumber(String number)throws IllegalArgumentException
	{
		if(number == null){
			throw new IllegalArgumentException("Number can't be null!!");
		}
		
		String trimDigits = number.trim();
		if(trimDigits.isEmpty()){
			throw new IllegalArgumentException("There are no input.");
		}
		
		int numberLength = number.length();
		if(numberLength!=15){
			throw new IllegalArgumentException("The Amex must"
					+ " be exactly 15 digits.");
		}
		
		String subDigits = number.substring(0, 2);
		int firstDigits = Integer.parseInt(subDigits);
		if(!(firstDigits!=34 || firstDigits!=37)){
			throw new IllegalArgumentException("The Amex card must"
					+ " be starting digits with 34 or 37.");
		}
		
		return number;
	}
	
	

}
