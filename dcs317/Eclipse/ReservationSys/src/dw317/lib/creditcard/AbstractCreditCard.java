/**
 * 
 */
package dw317.lib.creditcard;


/**
 * The AbstractCreditCard is an abstract class that holds general
 * data about a credit card. This class is used as a superclass or 
 * a parent class for classes representing specific credit card that
 * extend to this class in order to inherit its state and behavior.
 * The AbstractCreditCart class implements the CreditCard interface
 * 
 * @author Lyrene Labor
 * @version September 2016
 */
public abstract class AbstractCreditCard implements CreditCard {

	// class data declaration: 
	
	// serial ID
	private static final long serialVersionUID = 42031768871L; 
	private final CardType cardType; // credit card type
	private final String number; // credit card number

	/**
	 * The constructor validates the input values and sets the 
	 * credit card type and credit card number with the
	 *  respective input values.
	 * 
	 * @param cardtype
	 *            The credit card type
	 * @param number
	 *            The credit card number
	 * @throws IllegalArgumentException
	 *             if credit card type and number are invalid
	 */
	public AbstractCreditCard(CardType cardType, String number) 
			throws IllegalArgumentException {
  
		// validate credit card number and assign validated value 
		//to this.number
		this.number = validateLuhnAlgorithm(number);
		this.cardType = cardType; // assign credit card type to 
		//this.cardType
		
		

	}// end of constructor

	/** 
	 * The overridden equals method checks if two credit card objects
	 * are the same. Two credit card objects are considered equal if
	 * they both belong to the same class and they both have the same
	 * credit card type and credit card number.

	 * @param object
	 *            The credit card object
	 * @return A boolean value representing if two credit card objects are
	 *         equals or not
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(Object obj) {
		//if both objects have the same reference address, return true
		if (this == obj) {
			return true;
		}
		//if object is null, return false
		if (obj == null) {
			return false;
		}
		//if both objects are not from the same class, return false
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		//cast the object 
		AbstractCreditCard other = (AbstractCreditCard) obj;
		//check if both object have the same cardType
		if (this.cardType != other.cardType) {
			return false;
		}
		//check if both objects have the same number
		if (this.number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!this.number.equals(other.number)) {
			return false;
		}
		
		//return true if non of the above statements are true
		return true;
	}//end of equals method
	

	/**
	 * The overridden getNumber method returns a String containing 
	 * the credit card number
	 * 
	 * @return A reference to a String containing credit card number
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
	 * The overridden hashCode method calculates the hash integer of each field
	 * and returns it
	 * @return an integer value representing the hash code
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = 31;
		int result = 1;
		//calculations of the hashCode based on the fields
		//used in the equals method
		result = prime * result + 
				((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + 
				((number == null) ? 0 : number.hashCode());
		return result;
	}//end of hashCode method

	/**
	 * The overridden toString method returns a String containing the 
	 * credit card data
	 * 
	 * @returns A reference to a String
	 */
	@Override
	public String toString() {
		return this.cardType.toString() + "*" + this.number;
	} // end of toString method
	

	/**
	 * The digitSum method is used by the method that validates 
	 * credit card numbers with the Luhn Algorithm. It will process each 
	 * digit of the credit card number and sum all of the digits 
	 * and return the value
	 * 
	 * @param num The credit card number 
	 * @return The integer sum of all digits of the credit number after 
	 *           processing each one of them
	 */
	private static int digitSum(long num){
		
		int position = 0; //the digit's current position
		int digit = 0; //the current digit value
		int sum = 0; //the sum of the digits

		// The following code goes through each digits starting from the least
		// significant of the number
		// digits (starting from the right) with starting position 0
		while (num > 0) {
			digit = (int) (num % 10); // separate a digit
			if ((position % 2) != 0)// check if its position is odd
			{
				digit = digit * 2;// if yes then multiply by two
			}
			if (digit > 9)// check if digit is larger than 9
			{
				digit = digit - 9; // if yes then subtract 9
			}

			sum = sum + digit; // sum each digit

			position++; // update current digit position
			num = num / 10; // remove the used digit
		}
		
		return sum; //return value of sum
	}//end of digitSum method


	/**
	 * The validateLuhnAlgorithm method uses the Luhn Algorithm in order to
	 * check if a credit card number is a valid one.
	 * 
	 * @param number
	 *            The credit card number
	 * @return A reference to a String containing the validated credit card
	 *         number
	 * @throws IllegalArgumentException
	 *             if credit card number is invalid
	 */
	private static String validateLuhnAlgorithm(String number) 
			throws IllegalArgumentException {

		//check if the credit card number is null or empty
		if(number==null || number==""){
			throw new IllegalArgumentException("The credit card number"
					+ " must not be null and must not be empty.");
		}
		
		String trimmedNum = number.trim();
		
		// check if the credit card number is numeric by parsing it
		long num = 0;
		try {
			num = Long.parseLong(trimmedNum);
		} catch (java.lang.NumberFormatException ex) {
			throw new IllegalArgumentException("The credit card number must be a "
					+ "non-decimal "
					+ "numeric value containing no spaces or other non-numerial "
					+ "characters.");
		}

		//pass the value of num to the digitSum method and 
		//assign returned value to variable sum
		int sum = digitSum(num);

		// the following code checks if the total sum is multiple of ten
		if (sum % 10 == 0) {
			return trimmedNum; // return valid number
		}
		else {
			throw new IllegalArgumentException(
					"The credit card number is not a valid number " + "for a "
							+ "credit"
							+ " card "
							+ "based on the Luhn Algorithm");
		}

	} // end of validateLuhnAlgorithm method
	
	

}
