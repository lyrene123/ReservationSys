package groupLAPD.hotel.data;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Reservation;
import groupLAPD.hotel.business.*;

public class ReservationByCustSortedTest {
	
	public static void main (String[] args){
		
		testCompareMethod();				
	}
	
	public static void testCompareMethod(){
		
		String testCase1 = "Case 1: 2 Reservations are equals.";
		DawsonCustomer customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		DawsonCustomer customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		DawsonRoom room1 = new DawsonRoom(602, RoomType.SUITE);
		DawsonRoom room2 = new DawsonRoom(602, RoomType.SUITE);
		DawsonReservation r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
		DawsonReservation r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase1, r1, r2, "0");
		
		String testCase2 = "Case 2: Reservation 1 is greater than Reservation 2 (Different customer's name).";
		customer1 = new DawsonCustomer("Pengkim", "Sy", "pengkimsy@gmail.com");
		customer2 = new DawsonCustomer("Ali", "Dali", "alidali@gmail.com");
		room1 = new DawsonRoom(602, RoomType.SUITE);
		room2 = new DawsonRoom(602, RoomType.SUITE);
		r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
		r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase2, r1, r2, "positive number");
		
		String testCase3 = "Case 3: Reservation 1 is less than Reservation 2 (Different room numer).";
		customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		room1 = new DawsonRoom(602, RoomType.SUITE);
		room2 = new DawsonRoom(603, RoomType.SUITE);
		r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
		r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase3, r1, r2, "negative number");
		
		String testCase4 = "Case 4: Reservation 1 is greater than Reservation 2 (Different room numer).";
		customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		room1 = new DawsonRoom(604, RoomType.SUITE);
		room2 = new DawsonRoom(603, RoomType.SUITE);
		r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
		r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase4, r1, r2, "positive number");
		
		String testCase5 = "Case 5: Reservation 1 is less than Reservation 2 (Different room number and type).";
		customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		room1 = new DawsonRoom(602, RoomType.SUITE);
		room2 = new DawsonRoom(801, RoomType.PENTHOUSE);
		r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
		r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase5, r1, r2, "negative number");
		
		String testCase6 = "Case 6: Reservation 1 is less than Reservation 2 (Different date).";
		customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		room1 = new DawsonRoom(602, RoomType.SUITE);
		room2 = new DawsonRoom(602, RoomType.SUITE);
		r1 = new DawsonReservation(customer1, room1, 2016, 2, 1, 2016, 2, 29);
		r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase6, r1, r2, "negative number");
		
		String testCase7 = "Case 7: Reservation 1 is greater than Reservation 2 (Different date).";
		customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
		room1 = new DawsonRoom(602, RoomType.SUITE);
		room2 = new DawsonRoom(602, RoomType.SUITE);
		r1 = new DawsonReservation(customer1, room1, 2016, 4, 1, 2016, 4, 29);
		r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
		testCompareMethod(testCase7, r1, r2, "positie number");
	}
	
	public static void testCompareMethod(String testCase, Reservation r1
			, Reservation r2, String expectedResult){
		
		System.out.println();
		System.out.println("Test compare method:");
				
		ReservationByCustSorted reservationByCustSorted = new ReservationByCustSorted();
		int result = reservationByCustSorted.compare(r1, r2);
		System.out.println("\t" + testCase);
		System.out.println("\t\tReservation 1 : " + r1.toString());
		System.out.println("\t\tReservation 2 : " + r2.toString());
		System.out.println("\t\tExpected Result : " + expectedResult);
		System.out.println("\t\tResult from the method : " + result);
		
		System.out.println();
	}
}
