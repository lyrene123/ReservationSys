/**
 * 
 */
package dw317.lib.creditcard;

import dw317.lib.creditcard.CreditCard.CardType;

/**
 * The CreditCardTest class is used to test 
 * the CreditCard Interface. 
 * 
 * @author Daniel Bezerra
 * @version September 2016
 */
public class CreditCardTest
{
	public static void main(String[] args) 
	{
		testGetInstance();
		testCardTypeToString();
		
	}
	private static void testGetInstance()
	{
		System.out.println("\nTesting the getInstance method from CreditCard");
		System.out.println("------------------------------------------------");
		testGetInstance("Case 1: Valid Visa Card", CardType.VISA, "4774341863020040", true);
		System.out.println();
		testGetInstance("Case 2: Valid MasterCard Card", CardType.MASTERCARD, "5326385350239456", true);
		System.out.println();
		testGetInstance("Case 3: Valid Amex Card", CardType.AMEX, "344322624384908", true);
		System.out.println();
		testGetInstance("Case 4: Invalid Visa Card Number", CardType.VISA, "8774341863020040", false);
		System.out.println();
		testGetInstance("Case 5: Invalid MasterCard Number", CardType.MASTERCARD, "1326385350239456", false);
		System.out.println();
		testGetInstance("Case 6: Invalid Amex Card Number", CardType.AMEX, "944322624384908", false);
		System.out.println();
		
	}
	private static void testGetInstance(String testCase, CardType type, 
			String number, Boolean expectedValid)
	{
		try{
			System.out.println("   " + testCase);
			
			CreditCard card = CreditCard.getInstance(type, number);
					
			System.out.print("\tThe CreditCard instance was created " 
					+ card);
			System.out.print("\n\tExpected return: " 
					+ expectedValid);
			if(expectedValid == true){
				System.out.println("\n\tValid Data. == TEST OK");
				//System.out.println();
			} else {
				System.out.println("\n\t" + "Error! Invalid data. ==== FAILED TEST ====");
				//System.out.println();
			}

		}
		catch (IllegalArgumentException iae)	{
			System.out.print("\t"+ iae.getMessage());
			if (expectedValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 					
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println();
	}
	private static void testCardTypeToString()
	{
		CardType mastercard = CardType.MASTERCARD;	
		CardType visa = CardType.VISA;
		CardType amex = CardType.AMEX;
		System.out.println("\nTesting the CardType enum toString method from CreditCard");
		System.out.println("-----------------------------------------------------------");
		testCardTypeToString("Case 1: Valid Mastercard Creditcard Type", mastercard, true );
		System.out.println();
		testCardTypeToString("Case 2: Valid Visa Credicard Type", visa, true );
		System.out.println();
		testCardTypeToString("Case 3: Valid Amex Creditcard Type", amex, true );
		System.out.println();
				
	}
	private static void testCardTypeToString(String testCase, CardType card, Boolean expectedValid)
	{
		try{
			System.out.println("   " + testCase);
			
			System.out.print("\tThe CardType instance was created " 
					+ card.toString());
			System.out.print("\n\tExpected return: " 
					+ expectedValid);
			if(expectedValid == true){
				System.out.println("\n\tValid Data. == TEST OK");
				//System.out.println();
			} else {
				System.out.println("\n\t" + "Error! Invalid data. ==== FAILED TEST ====");
				//System.out.println();
			}

		}
		catch (IllegalArgumentException iae)	{
			System.out.print("\t"+ iae.getMessage());
			if (expectedValid)
				System.out.print("  Error! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 					
					e.getMessage() + " ==== FAILED TEST ====");
			if (expectedValid)
				System.out.print(" Expected Valid.");
		}

		System.out.println();
	}
}

	




