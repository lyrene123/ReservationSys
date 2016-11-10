/**
 * 
 */
package groupLAPD.hotel.data;

import dw317.hotel.data.interfaces.ListPersistenceObject;

/**
 * 
 * 
 * @author Lyrene Labor, Daniel Cavalcanti
 *
 */
public class ReservationListDBTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ListPersistenceObject file = new 
				SequentialTextFileList("datafiles/database/rooms.txt", 
										"datafiles/database/customer.txt",
										"datafiles/database/reservations.txt");
		ReservationListDB reserv = new ReservationListDB(file);
		System.out.println(reserv.toString());
		
	}

}
