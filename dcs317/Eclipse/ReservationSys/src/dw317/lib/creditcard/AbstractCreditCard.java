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
			throw new IllegalArgumentException("The input values of type CardType and "
					+ "String for AbstractCreditCard method are invalid");
		} 
		
		this.cardType = cardType;
		this.number = number;	
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
	
	@Override
	public int hashCode(){
		final int prime = 37;
		int result = 1;
		result = prime*result+((number==null) ? 0 : number.hashCode());
		return result;
	}
	
	
	
	
	
	
	
}
