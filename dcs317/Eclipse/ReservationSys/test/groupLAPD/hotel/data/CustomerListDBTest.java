package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.lib.Email;
import dw317.lib.creditcard.Amex;
import dw317.lib.creditcard.CreditCard;
import dw317.lib.creditcard.Visa;
import groupLAPD.hotel.business.DawsonCustomer;
import groupLAPD.hotel.business.DawsonHotelFactory;
import groupLAPD2016.util.ListUtilities;

public class CustomerListDBTest {

	public static void main(String[] args) {

		testToString();
		testAdd();
		testDisconnect();
		testGetCustomer();
		testUpdate();
	}
	
	private static void setup()
	{
		String[] rooms = new String[4];
		rooms[0] = "101*normal";
		rooms[1] = "102*normal";
		rooms[2] = "601*suite";
		rooms[3] = "801*penthouse";
		
		String[] custs = new String[8];
		custs [0] = "123Jonh.cena@321.com*Cena*John*Amex*344322624384908";
		custs [1] = "pengkim@abc.ca*pengkim*sy*mastercard*5326385350239456";
		custs [2] = "raj@aing.ru*Raj*Wong*visa*4556737586899855";
		custs [3] = "daniel.cavalcati@hotmail.com*daniel*cavalcati**";
		custs [4] = "aliDali@mail.com*Ali*Dali*amex*344322624384908";
		custs [5] = "joe.mancini@mail.me*Joe*Mancini**";
		custs [6] = "lyrence.l.labor@yahoo.com*lyrence*labor**";
		custs [7] = "d@zzz.com*Da*Ja**";


		String[] reservs = new String[8];
		reservs [0] = "123Jonh.cena@321.com*2016*7*7*2016*7*9*602";
		reservs [1] = "pengkim@abc.ca*2016*10*20*2016*10*30*103";
		reservs [2] = "raj@aing.ru*2016*9*10*2016*9*15*101";
		reservs [3] = "daniel.cavalcati@hotmail.com*2016*11*12*2016*11*22*104";
		reservs [4] = "aliDali@mail.com*2016*12*5*2016*12*10*601";
		reservs [5] = "joe.mancini@mail.me*2016*10*10*2016*10*20*801";
		reservs [6] = "lyrence.l.labor@yahoo.com*2016*5*10*2016*5*15*602";
 		reservs [7] = "d@zzz.com*2016*10*12*2016*10*15*102";

		File dir = new File("testfiles");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			ListUtilities.saveListToTextFile(rooms, 
					"testfiles/testRooms.txt");
			ListUtilities.saveListToTextFile(custs, 
					"testfiles/testCustomers.txt");
			ListUtilities.saveListToTextFile(reservs, 
					"testfiles/testReservations.txt");
		}
		catch(IOException io){
			System.out.println("Error creating file in setUp()");
		}
	}	
	
	private static void teardown() {
		File theFile = new File("testfiles/testRooms.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testCustomers.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
		theFile = new File("testfiles/testReservations.txt");
		if (theFile.exists()) {
			theFile.delete();
		}
	}
	
	private static void testToString(){
		setup();
		
		System.out.println("Testing toString()");
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		
		CustomerListDB db = new CustomerListDB(file);
		
		System.out.println("Case 1: 1 parametor constructor");
		System.out.println(db.toString());
		System.out.println();
		
		teardown();
	}
	
	private static void testAdd(){
		setup();
		System.out.println("Testing Add() : ");	
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		
		CustomerListDB db = new CustomerListDB(file);
		
		Customer alreadyExistCust = new DawsonCustomer("Raj", "Wong", "lyrence.l.labor@yahoo.com");
		Customer lastOflistCust = new DawsonCustomer("Donald", "Trump", "donald.trump@zzzz.com");
		Customer startOflistCust = new DawsonCustomer("hilary", "clinton", "hilaryclinton@012.com");
		
		try {			
			System.out.println("Case 1 : added " + lastOflistCust + " to the last of the list");
			db.add(lastOflistCust);
			System.out.println(db.toString());
			System.out.println();
			
			System.out.println("Case 2 : added " + startOflistCust + " to the first of the list");
			db.add(startOflistCust);
			System.out.println(db.toString());
			System.out.println();
			
			System.out.println("Case 3 : added an existed customer to database");
			db.add(alreadyExistCust);
		} catch (DuplicateCustomerException e){
			System.out.println(e.getMessage());
		}
		
		
		System.out.println();
		
		teardown();
	}
	
	private static void testDisconnect(){
		setup();
		
		System.out.println("Testing Diconnect() :");
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		
		CustomerListDB db = new CustomerListDB(file);
		
		Customer customer = new DawsonCustomer("Halary", "Clinton", "halary.clinton@yahoo.com");
		Customer customer1 = new DawsonCustomer("Pengkim", "Sy", "pengkim@abc.ca");
		
		try {
			db.add(customer);			
			System.out.println("Case 1 : added an existed customer to database");
			db.add(customer1);
		} catch (DuplicateCustomerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			db.disconnect();	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		db = new CustomerListDB(file);
		
		System.out.println("Case 2 : added a non-existed customer to database");
		System.out.println(db.toString());
		System.out.println();
		
		teardown();
	}
	
	private static void testGetCustomer(){
		setup();
		
		System.out.println("Testing getCustomer() :");
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		
		CustomerListDB db = new CustomerListDB(file);
		
		Email notMatchedEmail = new Email("halary.clinton@yahoo.com");
		Email matchedEmail = new Email("raj@aing.ru");
		try {
			System.out.println("Case 1: not matched email");
			System.out.println(db.getCustomer(matchedEmail).toString());
			System.out.println("Case 2: matched email");
			db.getCustomer(notMatchedEmail);
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		teardown();
	}
	
	private static void testUpdate(){
		setup();
		System.out.println("Testing update() :");
		
		SequentialTextFileList file = new SequentialTextFileList
				("testfiles/testRooms.txt", "testfiles/testCustomers.txt",
						"testfiles/testReservations.txt");
		
		CustomerListDB db = new CustomerListDB(file);
		
		Email maciniEmail = new Email("joe.mancini@mail.me");
		CreditCard amexCard = new Amex("344322624384908");
		
		Email pengkimEmail = new Email("pengkim@abc.ca");
		CreditCard visaCard = new Visa("4774341863020040");
		
		Email notExistEmail = new Email("asldfkj@laskdjf.com");
		try {
			db.update(maciniEmail, amexCard);
			db.update(pengkimEmail, visaCard);
			db.update(notExistEmail, amexCard);
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println("Case 1 : No credit card before");
			System.out.println(db.getCustomer(maciniEmail).toString());
			
			System.out.println("Case 2 : already have a creditcard");
			System.out.println(db.getCustomer(pengkimEmail).toString());
			
			System.out.println("Case 3 : Not existing email");
			System.out.println(db.getCustomer(notExistEmail).toString());
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println();
		teardown();
	}
}
