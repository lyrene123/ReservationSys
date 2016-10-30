package groupLAPDTestUtil;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.Reservation;
import dw317.lib.Email;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.Visa;
import groupLAPD.hotel.business.*;
import groupLAPD2016.util.ListUtilities;


public class ListUtilitiesTest2 {
	
		public static void main(String[] args) {

			testSort();
			testMerge();
			testSort2param();

		}

		public static void testSort() {
			Email[] emailList = new Email[4];
			emailList[0] = new Email("danielhbc@gmail.com");
			emailList[1] = new Email("laborlyrene@gmail.com");
			emailList[2] = new Email("alidali@gmail.com");

			Integer[] values = new Integer[] { 9, -3, 5, 0, 1 };
			Double[] numbers = new Double[] { 8.1, 0.0, -1.1, 3.2, 5.4 };
			String[] names = new String[] { "PengKim", "Ali", "Daniel", "Lyrene" };
			Boolean[] bol = new Boolean[] { true, false, true, false };
			Integer[] values2 = new Integer[] {};
			Integer[] values3 = new Integer[8];

			System.out.println("\nTesting the generic static sort method from ListUtilities Class");
			System.out.println("-----------------------------------------------------------------");
			ListUtilitiesTest2.<Integer>
			testSort("Case 1: A valid Integer Array with full capacity", values, true);
			ListUtilitiesTest2.<String>
			testSort("Case 2: A valid String Array with full capacity", names, true);
			ListUtilitiesTest2.<Double>
			testSort("Case 3: A valid Double Array with full capacity", numbers, true);
			ListUtilitiesTest2.<Email>
			testSort("Case 4: A Double Array not full to capacity", emailList, false);
			ListUtilitiesTest2.<Boolean>
			testSort("Case 5: A valid Boolean Array with full capacity", bol, true);
			ListUtilitiesTest2.<Integer>
			testSort("Case 6: Empty Array", values2, false);
			ListUtilitiesTest2.<Integer>
			testSort("Case 7: Empty Array", values3, false);

		}
		public static void testMerge(){
			
			String[] nameList1 = new String[] { "Ali", "Carlos", "Frank", "Lyrene" };
			String[] nameList2 = new String[] { "Ali", "Frank","Lyrene","Marcy"};
			String[] nameList3 = new String[] { "j@doe.com*John*Doe*visa*4556737586899855"};
			String[] nameList4 = new String[] { "j@doe.com*John*Doe**"};
			String[] nameList5 = new String[] { "Marcy", "Sarah", "Nancy", "Maria" };
			String[] nameList6 = new String[4];
			String[] nameList7 = new String[] { "Duardo", "Durin", "Marcio", "Marcos" };
			String[] nameList8 = new String[] {};
			String[] nameList9 = new String[] {};
			String[] nameList10 = new String[] {};
			
			DawsonCustomer daniel = new DawsonCustomer("Daniel", "Bezerra"
					,"danielhbc@gmail.com");
			Visa visacard = new Visa("4774341863020040");
			Optional<CreditCard> card = Optional.ofNullable(visacard);
			daniel.setCreditCard(card);
			
			DawsonCustomer pengkim = new DawsonCustomer("PengKim", "Sy"
					,"pengkim@gmail.com");
			Optional<CreditCard> card2 = Optional.ofNullable(visacard);
			pengkim.setCreditCard(card2);
			
			DawsonCustomer jaya = new DawsonCustomer("Jaya", "Naka"
					,"jaya@gmail.com");
			Amex visacard3 = new Amex("344322624384908");
			Optional<CreditCard> card3 = Optional.ofNullable(visacard3);
			jaya.setCreditCard(card3);
			
			Customer[] customerlist = new DawsonCustomer[2]; 
			customerlist[0] = daniel;
			customerlist[1] = pengkim;
			Customer[] customerlist2 = new DawsonCustomer[3]; 
			customerlist2[0] = daniel;
			customerlist2[1] = jaya;
			customerlist2[2] = pengkim;
					
			System.out.println("\nTesting the generic static merge method from ListUtilities Class");
			System.out.println("-----------------------------------------------------------------");
			ListUtilitiesTest2.<Customer>
			testMerge("Case 1: Merging two comparable objects", customerlist, customerlist2, "duplicateNames.txt", true);
			ListUtilitiesTest2.<String>
			testMerge("Case 2: Merging", nameList1, nameList2, "duplicateNames.txt", true);
			ListUtilitiesTest2.<String>
			testMerge("Case 3: Merging", nameList3, nameList4, "duplicateNames.txt", true);
			ListUtilitiesTest2.<String>
			testMerge("Case 4: An attempt to merge one array with nulls only ", nameList5, nameList6, "duplicateNames.txt", false);
			ListUtilitiesTest2.<String>
			testMerge("Case 5: An attempt to merge one empty array", nameList7, nameList8, "duplicateNames.txt", false);
			ListUtilitiesTest2.<String>
			testMerge("Case 6: An attempt to merge two empty arrays", nameList9, nameList10, "duplicateNames.txt", false);
			
			
		}

		public static <T extends Comparable<T>> void testMerge(String testCase, T[] list1, T[] list2,
				String duplicateFileName, boolean expectedValid) {

			try {
				System.out.println("   " + testCase);
				System.out.print("\tArray List1: ");
				System.out.println(Arrays.toString(list1));
				System.out.print("\tArray List2: ");
				System.out.println(Arrays.toString(list2));
				
				@SuppressWarnings("rawtypes")
				Comparable[] mergedList = new Comparable[0];
				
				mergedList = ListUtilities.<T>merge(list1, list2, duplicateFileName);
				
				System.out.print("\tArray mergedList: ");
				System.out.println(Arrays.toString(mergedList));
				if (expectedValid) {
					System.out.println("\tExpected Result! TEST OK");
				} else {
					System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
				}

				System.out.println("\tEnd of testeCase");

			} catch (Exception e) {
				System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
				System.out.println();
				if (!expectedValid) {
					System.out.println("\tExpected Error! TEST OK");
				} else {
					System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
				}

			} finally {
				System.out.println("____________________________________________________________");
			}

		}

		public static <T extends Comparable<T>> void testSort(String testCase, T[] obj, boolean expectedValid) {

			try {
				System.out.println("   " + testCase);
				System.out.print("\tArray Before Sorting: ");
				System.out.println(Arrays.toString(obj));
				ListUtilities.<T>sort(obj);
				System.out.print("\tArray After Sorting:  ");
				System.out.println(Arrays.toString(obj));
				if (expectedValid) {
					System.out.println("\tExpected Result! TEST OK");
				} else {
					System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
				}

				System.out.println("\tEnd of testeCase");

			} catch (Exception e) {
				System.out.print("\tUNEXPECTED EXCEPTION TYPE! " + e.getClass() + " " + e.getMessage());
				System.out.println();
				if (!expectedValid) {
					System.out.println("\tExpected Error! TEST OK");
				} else {
					System.out.println("\n\tUnexpected Error==== FAILED TEST ====");
				}

			} finally {
				System.out.println("____________________________________________________________");
			}

		}

		private static void testSort2param(){
			
			testSortByCustomer();
			testSortByCheckoutDate();
		}
		
		private static void testSortByCustomer(){
			System.out.println("Test sort by customer");
			
			String testCase1 = "Case 1: The same reservation order";
			DawsonCustomer customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
			DawsonCustomer customer2 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
			DawsonCustomer customer3 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
			DawsonRoom room1 = new DawsonRoom(602, RoomType.SUITE);
			DawsonRoom room2 = new DawsonRoom(602, RoomType.SUITE);
			DawsonReservation r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
			DawsonReservation r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
			DawsonReservation r3 = new DawsonReservation(customer3, room2, 2016, 3, 1, 2016, 3, 30);
			Reservation[] reservation = {r1, r2, r3};			
			ReservationByCustSorted sortByCustomer = new ReservationByCustSorted();
			testSort2param(testCase1, reservation, sortByCustomer);
			
			String testCase2 = "Case 2: Different email";
			customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
			customer2 = new DawsonCustomer("Ali", "Dali", "Alidali@gmail.com");
			customer3 = new DawsonCustomer("Lyrene", "Labor", "Lyrenelabor@gmail.com");
			r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
			r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
			r3 = new DawsonReservation(customer3, room2, 2016, 3, 1, 2016, 3, 30);
			reservation[0] = r1;
			reservation[1] = r2;
			reservation[2] = r3;
			sortByCustomer = new ReservationByCustSorted();
			testSort2param(testCase2, reservation, sortByCustomer);
			
			System.out.println();		}
		
		private static void testSortByCheckoutDate(){
			System.out.println("Test sort by check out date");
			
			String testCase1 = "Case 1: Different Check out date";
			DawsonCustomer customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
			DawsonCustomer customer2 = new DawsonCustomer("Ali", "Dali", "Alidali@gmail.com");
			DawsonCustomer customer3 = new DawsonCustomer("Lyrene", "Labor", "lyrenelabor@gmail.com");
			DawsonRoom room1 = new DawsonRoom(602, RoomType.SUITE);
			DawsonRoom room2 = new DawsonRoom(602, RoomType.SUITE);
			DawsonReservation r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
			DawsonReservation r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 4, 30);
			DawsonReservation r3 = new DawsonReservation(customer3, room2, 2016, 3, 1, 2016, 5, 30);
			Reservation[] reservation = {r1, r2, r3};			
			ReservationByCheckoutSorted sortByCheckoutDate = new ReservationByCheckoutSorted();
			testSort2param(testCase1, reservation, sortByCheckoutDate);
			
			String testCase2 = "Case 2: Same Check out date";
			customer1 = new DawsonCustomer("pengkim", "Sy", "pengkimsy@gmail.com");
			customer2 = new DawsonCustomer("Ali", "Dali", "Alidali@gmail.com");
			customer3 = new DawsonCustomer("Lyrene", "Labor", "lyrenelabor@gmail.com");
			r1 = new DawsonReservation(customer1, room1, 2016, 3, 1, 2016, 3, 30);
			r2 = new DawsonReservation(customer2, room2, 2016, 3, 1, 2016, 3, 30);
			r3 = new DawsonReservation(customer3, room2, 2016, 3, 1, 2016, 3, 30);
			reservation[0] = r1;	
			reservation[1] = r2;
			reservation[2] = r3;
			sortByCheckoutDate = new ReservationByCheckoutSorted();
			testSort2param(testCase2, reservation, sortByCheckoutDate);
			
			System.out.println();
		}
		private static <E extends Comparable<E>>void testSort2param(String testCase
				, E[] list, Comparator<E> sortOrder ){

			System.out.println("\t" + testCase);
			
			System.out.println("\t\tBefore sort");
			for(int i=0; i<list.length; i++){
				System.out.print("\t\t"+ list[i].toString() + " ");
			}
			
			ListUtilities.sort(list, sortOrder);
			System.out.println("\n\t\tAfter sort");
			for(int i=0; i<list.length; i++){
				System.out.print("\t\t"+ list[i].toString() + " ");
			}
			
			System.out.println();
		}

	}

