/**
 * 
 */
package dw317.lib.creditcard;

/**
 * The AbstractCreditCard is an abstract class that holds general data about a credit card.
 * This class is used as a superclass or a parent class for classes representing specific 
 * credit card that extend to this class in order to inherit its state and behavior.
 * The AbstractCreditCart class implements the CreditCard interface
 * @author Pengkim Sy
 * @version September 2016
 */
public abstract class AbstractCreditCard implements CreditCard{
	
	//class data declaration
	private static final long serialVersionUID = 42031768871L; //serial ID
	private final CardType cardType; //credit card type
	private final String number; //credit card number
	
	/**
	 * The constructor validates the input values and sets the credit card type and number
	 * with the respective input values.
	 * @param cardtype The credit card type
	 * @param number The credit card number
	 * @throws IllegalArgumentException if credit card type and number are invalid
	 */
	public AbstractCreditCard(CardType cardType, String number) throws IllegalArgumentException{
		
		//throw exception if credit card type and number are not empty nor null
		if (number == null || number == "" || cardType == null){
			throw new IllegalArgumentException("The credit card type and number"
					+ " must exist and should not be empty");
		} 
		
		//validate credit card number and assign validated value to this.number
		this.number = validateLuhnAlgorithm(number);
		this.cardType = cardType; //assign credit card type to this.cardType
		
	}//end of constructor
	
	/**
	 * The  equals method checks if two credit card objects are the same. Two credit card
	 * objects are considered equal if they both belong to the same class and they both have
	 * the same credit card type and credit card number.
	 * @param object The credit card object
	 * @return A boolean value representing if two credit card objects are equals or not
	 */
	@Override
	public boolean equals(Object object){
		//check if two credit card objects are from the same class
		if(object.getClass()==this.getClass()){
			//if yes, cast the object to a CreditCard type
			CreditCard otherCard = (CreditCard)object;
			//check if the two credit card objects have the same type and number
			if(this.getNumber().equals(otherCard.getNumber()) &&
					this.getType().equals(otherCard.getType())){
				return true; //if same type and number, return true
			}
			else{
				return false; //if not the same type and number, return false
			}
		}
		else{
			return false; //if credit cards are not from the same class, return false
		}
	}//end of equals method
	
	/**
	 * The getNumber method returns a String containing the credit card number
	 * @return A reference to a String
	 */
	@Override
	public String getNumber(){
		return this.number;
	} //end of getNumber method
	
	/**
	 * The getType method returns the credit card type
	 * @returns A reference to a CreditType enum
	 */
	@Override
	public CardType getType(){
		return this.cardType;
	} //end of getType method
	
	/**
	 * The toString method returns a String containing a credit card data 
	 * @returns A reference to a String
	 */
	@Override
	public String toString(){
		return this.cardType.toString() + "*" + this.number;
	} //end of toString method
	
	
	/*@Override
	public int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime*result+((number==null) ? 0 : number.hashCode());
		return result;
	}*/
	
	/**
	 * The validateLuhnAlgorithm method uses the Luhn Algorithm in order to check if a credit
	 * card number is a valid one.
	 * @param number The credit card number
	 * @return A reference to a String containing the validated credit card number
	 * @throws IllegalArgumentException if credit card number is invalid
	 */
	private static String validateLuhnAlgorithm(String number)throws IllegalArgumentException {
		
		//check if the credit card number is numeric by parsing it
		long num=0;
		try {
			num = Long.parseLong(number);
		}
		catch(NumberFormatException e){
			throw new IllegalArgumentException("The credit card number must be a non-decimal "
					+ "numeric value containing less than 18 digits with no spaces");
		}
		
		int position = 0;
		int digit = 0;
		int sum = 0;
		
		//The following code goes through each digits starting from the least significant
		//digits (starting from the right) with starting position 0 
		while(num>0){
			digit = (int)(num%10); //separate a digit
			if((position%2)!=0)//check if its position is odd
			{
				digit = digit*2;//if yes then multiply by two
			}
			if(digit>9)//check if digit is larger than 9
			{
				digit = digit - 9; //if yes then subtract 9
			}
			
			sum = sum + digit; //sum each digit
			
			position++; //update position
			num = num/10; //remove the digit  
		}
		
		//the following code checks if the total sum is multiple of ten
		if(sum%10==0){
			return number; //return valid number
		}
		else{
			throw new IllegalArgumentException("The credit card number is not a valid number "
					+ "for a credit card based on the Luhn Algorithm");
		}
		
	} //end of validateLuhnAlgorithm method
	
}
