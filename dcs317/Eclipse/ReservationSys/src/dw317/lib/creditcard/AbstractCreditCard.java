/**
 * 
 */
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
		
		if (number == null || number == "" || cardType == null){
			throw new IllegalArgumentException("The credit card type and number"
					+ " must exist and should not be empty");
		} 
		
		this.number = validateLuhnAlgorithm(number);
		this.cardType = cardType;
		
	}
	
	@Override
	public String getNumber(){
		return this.number;
	} 
	
	@Override
	public CardType getType(){
		return this.cardType;
	}
	
	@Override
	public String toString(){
		return this.cardType.toString() + "*" + this.number;
	}
	
	@Override
	public boolean equals(Object object){
		if(object.getClass()==this.getClass()){
			CreditCard otherCard = (CreditCard)object;
			if(this.getNumber().equals(otherCard.getNumber()) &&
					this.getType().equals(otherCard.getType())){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
	
	/*@Override
	public int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime*result+((number==null) ? 0 : number.hashCode());
		return result;
	}*/
	
	private static String validateLuhnAlgorithm(String number)throws IllegalArgumentException {
		
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
		
		while(num>0){
			digit = (int)(num%10);
			if((position%2)!=0){
				digit = digit*2;
			}
			if(digit>9){
				digit = digit - 9;
			}
			
			sum = sum + digit;
			
			position++;
			num = num/10;   
		}
		
		if(sum%10==0){
			return number;
		}
		else{
			throw new IllegalArgumentException("The credit card number is not a valid number "
					+ "for a credit card based on the Luhn Algorithm");
		}
		
	}
	
}
