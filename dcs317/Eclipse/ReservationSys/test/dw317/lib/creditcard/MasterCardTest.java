/**
 * 
 */
package dw317.lib.creditcard;

/**
 * The MasterCardTest class is used to test the MasterCard class and to make
 * sure that it behaves as expected and throws the right exceptions based on the
 * credit card number
 * 
 * @author Lyrene Labor
 * @version September 2016
 */
public class MasterCardTest {

	/**
	 * The main method contains different MasterCard number as test cases which
	 * are used to instantiate objects of type MasterCard and check if the right
	 * exceptions will be generated based on a particular test case.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("\nTesting the MasterCard constructor.");
		caseValidation("Case 1 - valid data (mastercard*5326385350239456)",
				"5326385350239456",true);
		caseValidation("Case 2 - invalid data card number's starting digits are invalid",
				"1926385350239456",false);
		caseValidation("Case 3 - invalid data card number does not contain 16 digits only",
				"53263853502",false);
		caseValidation("Case 4 - invalid data MasterCard number not a valid credit card "
				+ "number based on Luhn Algorithm",
				"5326385350239440",false);
		caseValidation("Case 5 - invalid data card number not numeric",
				"5326c85350239456",false);
		caseValidation("Case 6 - valid data card number contains trailing spaces",
				" 5326385350239456", true);
		caseValidation("case 7 - invalid data card number is a decimal number",
				"5326.85350239456", false);
		caseValidation("case 8 - invalid data card number is empty",
				"", false);
		caseValidation("case 9 - invalid data card number is just spaces",
				"    ", false);
		caseValidation("case 10 - invalid data card number is null",
				null, false);
		caseValidation("case 11 - invalid data card number contains spaces",
				"53263853 0239456", false);

	}
	
	
	
	public static void caseValidation(String testCase, String number, boolean expectedResult){
		System.out.println("   " + testCase);
		try{
			MasterCard card = new MasterCard(number);
			System.out.println("\tThe MasterCard instance has been created: " + card);
			
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
