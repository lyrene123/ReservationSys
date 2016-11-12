package groupLAPD.hotel.data;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import dw317.hotel.business.interfaces.Customer;
import dw317.hotel.business.interfaces.HotelFactory;
import dw317.hotel.data.DuplicateCustomerException;
import dw317.hotel.data.NonExistingCustomerException;
import dw317.hotel.data.interfaces.CustomerDAO;
import dw317.hotel.data.interfaces.ListPersistenceObject;
import dw317.lib.Email;
import dw317.lib.Name;
import dw317.lib.creditcard.CreditCard;
import groupLAPD.hotel.business.DawsonHotelFactory;
import groupLAPD2016.util.ListUtilities;

public class CustomerListDB implements CustomerDAO{
	
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
		
	/**
	 * Constructor requires a ListPersistenceObject to read from file.
	 * @param listPersistenceObject
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = DawsonHotelFactory.DAWSON;
	}
	
	public CustomerListDB (ListPersistenceObject listPersistenceObject, 
				HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	@Override
	public String toString() {
		int num = this.database.size();
		StringBuilder str = new StringBuilder("Number of customers in database: " + num);
		for (Customer cust : this.database) {
			str.append("\n" + cust.toString());
		}
		return str.toString();
	}

	@Override
	public void add(Customer cust) throws DuplicateCustomerException {
		if(binarySearch(database, cust.getEmail()) != -1){
			throw new DuplicateCustomerException("This customer " + cust.toString()
				+ " already exists in the database.");
		} 
		
		Name name = cust.getName();
		Customer customer = factory.getCustomerInstance(name.getFirstName()
				, name.getLastName(), cust.getEmail().toString());
		
		
		int indexToAdd = findIndexToAdd(database, customer.getEmail());
		database.add(indexToAdd, customer);
	}
	
	private static int findIndexToAdd(List<Customer> customerList, Email email){
		int startIndex = 0; 
		int endIndex = customerList.size() - 1;
		int midIndex = (startIndex + endIndex) / 2;

		if(customerList.get(startIndex).getEmail().compareTo(email) > 0){
			return startIndex;
		}

		if(customerList.get(endIndex).getEmail().compareTo(email) < 0){
			return endIndex;
		}
		
		while(startIndex <= endIndex){

			if(customerList.get(midIndex + 1).getEmail().compareTo(email) > 0){
				if(customerList.get(midIndex).getEmail().compareTo(email) < 0)
					return midIndex + 1;
			}
			if(customerList.get(midIndex).getEmail().compareTo(email) > 0){
				endIndex = midIndex - 1;
			}
			
			if(customerList.get(midIndex).getEmail().compareTo(email) < 0){
				startIndex = midIndex + 1;
			}
				
			midIndex = (startIndex + endIndex)/2;
		}

		return -1;
	}

	@Override
	public void disconnect() throws IOException {
		File dir = new File("backupDatabase");
		try{
			if (!dir.exists()){  
				dir.mkdirs();
			}
			Customer[] customer = new Customer[database.size()];
			database.toArray(customer);
			ListUtilities.saveListToTextFile(customer, 
					"backupDatabase/customerDatabase.txt");
		}
		catch(IOException io){
			System.out.println("Error saving to file");
		}
		database = null;
	}

	@Override
	public Customer getCustomer(Email email) throws NonExistingCustomerException {
		if(binarySearch(database, email) == -1){
			throw new NonExistingCustomerException("The provided email " + email
					+ " is not matched with the "
					+ "customer's email in the database.");
		}
			
		return database.get(binarySearch(database, email));
	}
	
	/**
	 * This method is to search all the customer list base on the email
	 *  This will return the index of matched customer in the list.
	 * @param customerList	List of customer that needs to be searched
	 * @param email	
	 * @return index of the matched customer
	 */
	private static int binarySearch(List<Customer> customerList, Email email) {
		int startIndex = 0; 
		int endIndex = customerList.size() - 1;
		int midIndex = (startIndex + endIndex) / 2;
		/*System.out.println("startIndex : " + startIndex);
		System.out.println("endIndex : " + endIndex);
		System.out.println("midIndex : " + midIndex);

		if(arrList.get(startIndex).getEmail().compareTo(email) == 0){
			return startIndex;
		}
		if(arrList.get(endIndex).getEmail().compareTo(email) == 0){
			return endIndex;
		}*/
		
		while(startIndex <= endIndex){
			//System.out.println("result of compareTo : " + arrList.get(midIndex).getEmail().compareTo(email));
			if(customerList.get(midIndex).getEmail().compareTo(email) == 0){
				return midIndex;
			}
			if(customerList.get(midIndex).getEmail().compareTo(email) > 0){
				endIndex = midIndex - 1;
			}
			
			if(customerList.get(midIndex).getEmail().compareTo(email) < 0){
				startIndex = midIndex + 1;
			}
				
			midIndex = (startIndex + endIndex)/2;
			/*System.out.println("startIndex : " + startIndex);
			System.out.println("endIndex : " + endIndex);
			System.out.println("midIndex : " + midIndex);*/
		}

		//System.out.println("return reached");
		return -1;
	}

	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		if(binarySearch(database, email) == -1)
			throw new NonExistingCustomerException("The customer with email " + email
					+ " is not exist in the database");
		
		int indexOfMatchedEmail = binarySearch(database, email);
		database.get(indexOfMatchedEmail).setCreditCard(Optional.of(card));;
	}
}
