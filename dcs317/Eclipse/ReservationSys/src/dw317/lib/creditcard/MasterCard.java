
package dw317.lib.creditcard;

/**
 * The MasterCard class is a final implementation class that extends from the
 * AbstractCreditCard class. As a specific credit card class , 
 * the MasterCard class inherits the general data and behavior of a credit 
 * card from AbstractCreditCard.
 * 
 * @author Lyrene Labor
 * @version September 2016
 */
public final class MasterCard extends AbstractCreditCard  {
	
	//class data declaration
	private static final long serialVersionUID = 42031768871L;
	
	/**
	 * The MasterCard constructor calls the super constructor and sets the MasterCard
	 * credit card type and validated number 
	 * @param number The MasterCard number
	 * @throws IllegalArgumentException If number is not a valid MasterCard number
	 */
	public MasterCard(String number)throws IllegalArgumentException{
		super(CardType.MASTERCARD,validateNumber(number));
		
	}//end of constructor
	
	/**
	 * The validateNumber method checks if the credit card number is a valid 
	 * MasterCard number
	 * @param number The MasterCard number
	 * @return A reference to a String containing the validated MasterCard number
	 * @throws IllegalArgumentException If number is not a valid MasterCard number
	 */
	private static String validateNumber(String number)throws IllegalArgumentException
	{
		//if number is null or empty, throw an exception
		if(number==null || number==""){
			throw new IllegalArgumentException("A credit card of type MasterCard must"
					+ " exists with 16 digits only");
		}
		
		String trimmedNum = number.trim(); //trim any spaces contained in the number
		if(trimmedNum.isEmpty()){
			//throw an exception if number is empty
			throw new IllegalArgumentException("A credit card of type MasterCard must"
					+ " exists with 16 digits only");
		}
		
		//check if card number has only 16 digits
		int numberLength = trimmedNum.length();
		if(numberLength!=16){
			throw new IllegalArgumentException("A credit card of type MasterCard must"
					+ " have 16 digits only");
		}
		
		//check if card number starts with digits between 51-55
		String firstTwoDigits = trimmedNum.substring(0, 2);
		int startingDigits = Integer.parseInt(firstTwoDigits);
		if(!(startingDigits>=51 && startingDigits<=55)){
			throw new IllegalArgumentException("A credit card of type MasterCard must"
					+ " have starting digits between 51 to 55");
		}
		
		return trimmedNum;
	}//end of validateNumber method
	
	

}
