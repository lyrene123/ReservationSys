
package dw317.lib.creditcard;

/**
 * The Visa class holds data for a Visa Credit Card. 
 * 
 * @author Daniel Bezerra
 * @version September 2016
 */
public final class Visa extends AbstractCreditCard  {
	
	//class data declaration
	private static final long serialVersionUID = 42031768871L;
	
	/**
	 * The Visa constructor sets the Visa Credit Card number, 
	 * calls the super constructor and sets the Visa
	 * credit card type and validated number 
	 * @param number The Visa Credit Card number
	 * @throws IllegalArgumentException If number is not a valid Visa Credit Card number
	 */
	public Visa(String number)throws IllegalArgumentException{
		super(CardType.VISA,validateNumber(number));
		
	}//end of constructor
	
	/**
	 * The validateNumber method checks if the credit card number is a valid 
	 * Visa Credit Card number
	 * @param number The Visa Credit Card number
	 * @return A reference to a String containing the validated Visa Credit Card number
	 * @throws IllegalArgumentException If number is not a valid Visa Credit Card number
	 */
	private static String validateNumber(String number)throws IllegalArgumentException
	{
		//if number is null or empty, throw an exception
		if(number==null || number==""){
			throw new IllegalArgumentException("A credit card of type Visa must"
					+ " exist with 16 digits only");
		}
		
		String trimmedNum = number.trim(); //trim any spaces contained in the number
		if(trimmedNum.isEmpty()){
			//throw an exception if number is empty
			throw new IllegalArgumentException("A credit card of type Visa must"
					+ " exist with 16 digits only");
		}
		
		//check if card number has only 16 digits
		int numberLength = trimmedNum.length();
		if(numberLength!=16){
			throw new IllegalArgumentException("A credit card of type Visa must"
					+ " have 16 digits only");
		}
		
		//check if card number starts with digit 4
		String firstDigit = trimmedNum.substring(0, 1);
		int startingDigit = Integer.parseInt(firstDigit);
		if(startingDigit!=4){
			throw new IllegalArgumentException("A credit card number of type Visa must"
					+ "start with 4");
		}
		
		return trimmedNum;
	}//end of validateNumber method
	
}