/**
 * 
 */
package dw317.lib;




/**
 * @author Lyrene Labor
 *
 */
public class EmailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		testTheConstructor();
		testGetAddress();
		testGetHost();
		testGetUserID();
		testHashCode();
		

	}
	
	private static void testHashCode(){
		
		System.out.println("\nTesting the hashCode method.");
		Email email1 = new Email("alidali123@gmail.com");
		Email email2 = new Email("ali_dalihello@gmail.com");
		
		testHashCode("case 1 - equal objects, same hash code",
				email1, email2, "Expected result: same hash codes");
		email2 = new Email("hello@localhost");
		testHashCode("case 2 - not equal objects, different hash code",
				email1, email2, "Expected result: different hash codes");
		
	}
	
	private static void testHashCode(String testCase, Object obj1, Object obj2, String expectedResult) {
		System.out.println("   " + testCase);
		System.out.println("   " + expectedResult);
		// get the hash code of each object
		int hash1 = obj1.hashCode();
		int hash2 = obj2.hashCode();
		// display each hash code and compare
		System.out.println("\tobject 1 - " + obj1 + ": " + hash1);
		System.out.println("\tobject 2 - " + obj2 + ": " + hash2);

	} 
	
	private static void testGetUserID(){
		System.out.println("\nTesting the testGetUserID method.");
		System.out.println("\nTesting the getUserId method.");
		testGetUserId("case 1 - valid returned value: alidali123@hotmail.com",
				"alidali123@hotmail.com", "alidali123");
		testGetUserId("case 2 - valid returned value: hello@localhost",
				"hello@localhost", "hello");
	}
	
	private static void testGetUserId(String testCase, String address, String expectedID){
		System.out.println("   " + testCase);
		Email email = new Email(address);
		System.out.print("\tThe Email instance was created: " + email);
		System.out.println("expected result returned: " + expectedID);

		if (!email.getUserId().equals(expectedID))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
		
		
	}
	
	private static void testGetHost(){
		System.out.println("\nTesting the getHost method.");
		testGetHost("case 1 - valid returned value: alidali123@hotmail.com",
				"alidali123@hotmail.com", "hotmail.com");
		testGetHost("case 2 - valid returned value: hello@localhost",
				"hello@localhost", "localhost");

	}
	
	private static void testGetHost(String testCase, String address, String expectedHost){
		System.out.println("   " + testCase);
		Email email = new Email(address);
		System.out.print("\tThe Email instance was created: " + email);
		System.out.println("expected result returned: " + expectedHost);

		if (!email.getHost().equals(expectedHost))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
		
	}
	
	
	private static void testGetAddress(){
		System.out.println("\nTesting the getAddress method.");
		
		testGetAddress("case 1 - valid returned value: alidali123@hotmail.com",
				"alidali123@hotmail.com", "alidali123@hotmail.com");
		testGetAddress("case 2 - valid returned value: hello@localhost",
				"hello@localhost", "hello@localhost");

	}
	
	private static void testGetAddress(String testCase, String address, 
			String expectedAddress){
		
		System.out.println("   " + testCase);
		Email email = new Email(address);
		System.out.print("\tThe Email instance was created: " + email);
		System.out.println("expected result returned: " + expectedAddress);
		

		if (!email.getAddress().equals(expectedAddress))
			System.out.print("  Error! Expected Invalid. ==== FAILED TEST ====");

		System.out.println("\n");
		
	}
	
	private static void testTheConstructor(){
		
		System.out.println("\nTesting the constructor.");
		testTheConstructor("case 1 - valid email address: alidali69@hotmail.com",
				"alidali69@hotmail.com", true);
		testTheConstructor("case 2 - valid email address: ali_dali69@hotmail.com",
				"ali_dali69@hotmail.com", true);
		testTheConstructor("case 3 - invalid email address: .alidali69@hotmail.com",
				".alidali69@hotmail.com", false);
		testTheConstructor("case 4 - invalid email address: alidali69.@hotmail.com",
				"alidali69@hotmail.com.", false);
		testTheConstructor("case 5 - invalid email address: alid..ali69@hotmail.com",
				"alid..ali69@hotmail.com.", false);
		testTheConstructor("case 6 - invalid email address: @hotmail.com",
				"@hotmail.com.", false);
		testTheConstructor("case 7 - invalid email address: alidali69@",
				"alidali69@", false);
		testTheConstructor("case 8 - invalid email address: alidali69@-hotmail.com",
				"alidali69@-hotmail.com", false);
		testTheConstructor("case 9 - invalid email address: alidali69@hotmail.com-",
				"alidali69@hotmail.com-", false);
		testTheConstructor("case 10 - valid email address: ALI-dali69@hot-mail.com",
				"ALI-dali69@hot-mail.com", true);
		testTheConstructor("case 11 - invalid email address: alidali69@hot,mail.com",
				"alidali69@hot,mail.com", false);
		testTheConstructor("case 12 - valid email address: ali.dali69@Hotmail123.Example.com",
				"ali.dali69@Hotmail123.Example.com", true);
		testTheConstructor("case 13 - invalid email address: alidali69hotmail.com",
				"alidali69hotmail.com", false);
		testTheConstructor("case 14 - invalid email address: alidali69asdfghjklqwertyuiopzxcvbnmqwesdfdfgfgfg@hotmail.com",
				"alidali69asdfghjklqwertyuiopzxcvbnmqwesdfdfgfgfg@hotmail.com", false);
		testTheConstructor("case 15 - invalid email address: alidali69@hotmailasdfghjklqwertyuiopzxcvbnmqwesdfdfgfgfg.com",
				"alidali69@hotmailasdfghjklqwertyuiopzxcvbnmqwesdfdfgfgfg.com", false);
		testTheConstructor("case 16 - valid email address: alidali69@localhost",
				"alidali69@localhost", true);
		testTheConstructor("case 17 - invalid email address: alidali69@localhost.-com",
				"alidali69@localhost.-com", false);
		testTheConstructor("case 18 - invalid email address: alidali69@localhost-.com",
				"alidali69@localhost-.com", false);
		testTheConstructor("case 19 - invalid email address: alidali69@local_host.com",
				"alidali69@local_host.com", false);
		testTheConstructor("case 20 - invalid email address: empty string",
				"", false);
		testTheConstructor("case 21 - invalid email address: null string",
				null, false);
		testTheConstructor("case 22 - invalid email address: alidali123@hotmail..com",
				"alidali123@hotmail..com", false);
		testTheConstructor("case 23 - invalid email address: alidali123@hotmail.com.",
				"alidali123@hotmail.com.", false);
		testTheConstructor("case 24 - invalid email address: alida li 123@hotmail.com",
				"alida li 123@hotmail.com", false);

	}
	
	private static void testTheConstructor(String testCase, String address,
			boolean expectedResult){
		
		System.out.println("   " + testCase);
		
		try {
			Email email = new Email(address);
			System.out.print("\tThe Email instance was created: " + email);
			
			if (!expectedResult)
				System.out.print("  \n\tError! Expected Invalid. "
						+ "==== FAILED TEST ====");
		}
		catch (IllegalArgumentException iae)	{
			System.out.print("\t"+ iae.getMessage());
			if (expectedResult)
				System.out.print("  \n\tError! Expected Valid. ==== FAILED TEST ====");
		}
		catch (Exception e) {
			System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() +  " "  + 				e.getMessage() + " ==== FAILED TEST ====");
			if (expectedResult)
				System.out.print(" Expected Valid.");
		}

		System.out.println("\n");
	}

}
