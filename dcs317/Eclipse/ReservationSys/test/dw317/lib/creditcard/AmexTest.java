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
		
		caseValidation("Case 3 : check if the number is null", null, false);
		caseValidation("Case 4 : check if the Card is not 15 digits", "3440559123809864", false);
		caseValidation("Case 5 : check if the card is a valid credit card", "345165798412345"
				, false);
		caseValidation("Case 6 : check if the parameter is a digit only", "344322a2438490d", false);
		caseValidation("Case 7 : check if the parameter contain space", "344322 62438490", false);
		caseValidation("Case 8 : check if the parameter is a non-decimal number", "344322.62438490"
				, false);
		caseValidation("Case 9 : check if there is no parameter", "", false);
	}
	
	public static void caseValidation(String testCase, String number, boolean expectedResult){
		System.out.println(testCase);
		try{
			Amex card = new Amex(number);
			System.out.println("\tThe Amex instance has been created: " + card);
			
			if(!expectedResult){
				System.out.print("\n\tError! You expected this case to fail but it didn't. ");
			}
		}
		catch(IllegalArgumentException e){
			System.out.print("\t"+ e.getMessage());
			if (expectedResult)
				System.out.print("\n\tError! You expected this case to succeed but it didnt. ");
		}
		catch(Exception x){
			System.out.print("\n\tError! There was an unexpected exception type " + x.getClass() +  " "  + 				
		x.getMessage());
			if (expectedResult)
				System.out.print("\n\tYou expected this case to succeed but it didn't ");
		}
		System.out.println("\n");
	}

}
