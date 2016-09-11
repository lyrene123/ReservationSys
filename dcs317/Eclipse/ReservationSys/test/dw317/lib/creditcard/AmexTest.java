package dw317.lib.creditcard;

public class AmexTest {

	public static void main(String[] args) {
	
		System.out.println("Testing Amex class");
		System.out.println();
		
		//Case0: Valid Amex Card
		Amex case0 = new Amex("344322624384908");
		System.out.println("Case0 : " +case0.toString() + "is a valid credit card number.");
		System.out.println();
		
		//Case1: Check to see if case0 is the same as card1.
		//Expected result: false
		Amex card1 = new Amex("344055912380986");
		boolean case1 = card1.equals(case0);
		System.out.println("Case 1 : " +"Check if the two cards below are the same : " + case1);
		System.out.println("First Card : " + case0.toString());
		System.out.println("Second Card : " + card1.toString());
		System.out.println();
		
		//Case2: Check if card1 is the same as card2.
		//Expected result: true
		Amex card2 = new Amex("344055912380986");
		boolean case2 = card1.equals(card2);
		System.out.println("Case 2 : " +"Check if the two cards below are the same : " + case2);
		System.out.println("First Card : " + case0.toString());
		System.out.println("Second Card : " + card1.toString());
		System.out.println();
		
		//Case3: number is null	
		System.out.println("Case 3 : Check if the parameter is null.");
		try{
			Amex case3 = new Amex(null);
		} catch(IllegalArgumentException e){
			System.out.println("The Amex card can't be null!");
		}
		System.out.println();
		
		//Case4: Check if the Card is not 15 digits
		System.out.println("Case4 : Check when the card is not 15 digits only.");
		System.out.println("Amex*3440559123809864");
		try{
			Amex case4 = new Amex("3440559123809864");
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		//Case5: Check if the card is a valid credit card
		//base on Luhn Algorithm
		System.out.println("Case5 : Check the invalid card base on luhn algorithm.");
		System.out.println("Amex*345165798412345");
		try{
			Amex case5 = new Amex("345165798412345");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		//Case6: The parameter must be digits only
		System.out.println("Case6 : The parameter must only be number");
		System.out.println("Amex*344322a2438490d");
		try{
			Amex case6 = new Amex("344322a2438490d");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		//Case7: The parameter must not contain space
		System.out.println("Case7 : The parameter must not contain space");
		System.out.println("Amex*344322 62438490");
		try{
			Amex case7 = new Amex("344322 62438490");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		//Case8: The parameter must be non-decimal number
		System.out.println("Case8 : The parameter must be non-decimal number");
		System.out.println("Amex*344322.62438490");
		try{
			Amex case8 = new Amex("344322.62438490");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		System.out.println();
		
		//Case9: There's no parameter
		System.out.println("Case9 : The parameter is an empty string");
		try{
			Amex case9 = new Amex("");
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

	}

}
