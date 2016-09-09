/**
 * 
 */
package dw317.lib.creditcard;

/**
 * The MasterCardTest class is used to test the MasterCard class and to make sure 
 * that it behaves as expected and throws the right exceptions based on the credit
 * card number
 * @author Lyrene Labor
 * @version September 2016
 */
public class MasterCardTest {

	/**
	 * The main method contains different MasterCard number as test cases which are
	 * used to instantiate objects of type MasterCard and check if the right exceptions 
	 * will be generated based on a particular test case.
	 * @param args
	 */
	public static void main(String[] args) {
		
		//test case 1 - valid MasterCard number
		MasterCard testCard = new MasterCard("5326385350239456");
		System.out.println("test case 1 " + testCard.toString() + " is a valid card");
		
		//checks if equals method from AbstractCreditCard class
		//works with two equal credit card
		//should print true
		MasterCard sameCard = new MasterCard("5326385350239456");
		boolean isSame = testCard.equals(sameCard);
		System.out.println("testCard and sameCard the same?: " + isSame);
		
		//checks if equals method from AbstractCreditCard class
		//works with two different credit card
		//should print false
		MasterCard anotherCard = new MasterCard("5588326411349366");
		isSame = testCard.equals(anotherCard);
		System.out.println("testCard and anotherCard the same?: " + isSame);
		
		//test case 2 - card number's starting digits are invalid
		try{
			testCard = new MasterCard("1963853502394569");
		}
		catch(IllegalArgumentException e){
			System.out.println("test case 2 - 1963853502394569: " + e.getMessage());
		}
		
		//test case 3 - card number does not contain 16 digits only
		try {
			testCard = new MasterCard("53263853502");
		}
		catch(IllegalArgumentException e){
			System.out.println("test case 3 - 53263853502: "+ e.getMessage());
		}
		
		//test case 4 - MasterCard number not a valid credit card number
		//based on Luhn Algorithm
		try {
			testCard = new MasterCard("5326385350239440");
		}
		catch(IllegalArgumentException e){
			System.out.println("test case 4 - 5326385350239440: " + e.getMessage());
		}
		
		//test case 5 - card number not numeric
		try {
			testCard = new MasterCard("5326c85350239456");
		}
		catch(IllegalArgumentException e){
			System.out.println("test case 5 - 5326c85350239456: " + e.getMessage());
		}
		
		//test case 6 - card number contains spaces
		try {
			testCard = new MasterCard("5326 85350239456");
		}
		catch(IllegalArgumentException e){
			System.out.println("test case 6 - 5326 5350239456: " + e.getMessage());
		}
		
		//test case 7 - card number is a decimal number
		try {
			testCard = new MasterCard("5326.85350239456");
		}
		catch(IllegalArgumentException e){
			System.out.println("test case 7 - 5326.85350239456: " + e.getMessage());
		}

	}

}
