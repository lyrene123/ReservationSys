/**
 * 
 */

package addressbook.app;
import dw317.lib.Address;
/**
 * @author 1541901 (Lyrene Labor)
 *
 */
public class AddressBook {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Address myAddress = new Address("3040", "Sherbrooke");
		System.out.println(myAddress.getAddress());

	}

}
