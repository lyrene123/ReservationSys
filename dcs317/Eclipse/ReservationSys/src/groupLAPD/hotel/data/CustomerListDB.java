package groupLAPD.hotel.data;

import java.io.IOException;
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

/**
 * The CustomerListDB class which implements the
 * CustomerDAO interface represents the reservation 
 * database as an internal list. The concrete 
 * CustomerDB provides CustomerDAO 
 * functionality. 
 * @version November 2016
 * @author Pengkim Sy
 */
public class CustomerListDB implements CustomerDAO{
	
	private List<Customer> database;
	private final ListPersistenceObject listPersistenceObject;
	private final HotelFactory factory;
		
	/**
	 * The 1 parameter CustomerDB Constructor method will assign
	 * a value for the final ListPersistenceObject field with the input
	 * value and will use that field to fill the customer and rooms
	 * database fields. In addition, the constructor will also set a value
	 * for the final HotelFactory field. 
	 * @param listPersistenceObject - Reference value of a 
	 * 								listPersistenceObject object
	 * @author Pengkim Sy
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = DawsonHotelFactory.DAWSON;
	}
	
	/**
	 * The 2 parameter CustomerDB Constructor method will take as 
	 * input two values: one for the final ListPersistenceObject field 
	 * which will then be used to fill the customer and rooms database 
	 * fields and another input value used to set a value for the final
	 * HotelFactory field. 
	 * @param listPersistenceObject - Reference value of a 
	 * 								listPersistenceObject object
	 * @param factory - Reference value of a 
	 * 					HotelFactory object
	 */
	public CustomerListDB (ListPersistenceObject listPersistenceObject, 
				HotelFactory factory) {
		this.listPersistenceObject = listPersistenceObject;
		this.database = this.listPersistenceObject.getCustomerDatabase();
		this.factory = factory;
	}

	/**
	 * The toString method will return a string representation containing
	 * all customer instances of the customer database
	 * @return A String representation of all customers from database 
	 */
	@Override
	public String toString() {
		int num = this.database.size();
		StringBuilder str = new StringBuilder("Number of customers in database: " + num);
		for (Customer cust : this.database) {
			str.append("\n" + cust.toString());
		}
		return str.toString();
	}

	/**
	 * The add method takes as input a customer object and will add
	 * a copy of the new customer object into the customer database 
	 * if it does not overlap with any existing reservations in the database.
	 * The add method will add the new reservations object in the 
	 * correct order.
	 * @param cust - Reference value of a Customer object
	 */
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
	

	/**
	 * The disconnect method is implement to make the customer 
	 * database transactions persistent. This method will save the database 
	 * to disk and will then assign null to the database field.
	 * @throws IOException - if any error occurs when saving database
	 * 							to a file.					
	 */
	@Override
	public void disconnect() throws IOException {
		this.listPersistenceObject.saveCustomerDatabase(this.database);
	}

	/**
	 * This method takes an input a customer's email and checks the
	 * database for that customer if it is found it will return that customer.
	 * if not it will throw NonExistingCustomerException
	 * @param Email email - a customer's email to look for
	 * @throws NonExistingCustomerException
	 * @return Customer
	 */
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
	 * This will return the index of matched customer in the list if it matches
	 * the email, if not, it will return -1.
	 * @param customerList	List of customer that needs to be searched
	 * @param email	
	 * @return index - if matched
	 * @return -1 - if not matched
	 */
	private static int binarySearch(List<Customer> customerList, Email email) {
		int startIndex = 0; 
		int endIndex = customerList.size() - 1;
		int midIndex = (startIndex + endIndex) / 2;
		
		while(startIndex <= endIndex){
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
		}

		return -1;
	}

	/**
	 * The  method takes 2 input: a List of reservations and an email.
	 * The method will find the perfect index in customer list base on
	 * the provided email, and it will return that index.
	 * @param customerList - A list of customer objects
	 */
	private static int findIndexToAdd(List<Customer> customerList, Email email){
		int startIndex = 0; 
		int endIndex = customerList.size() - 1;
		int midIndex = (startIndex + endIndex) / 2;

		if(customerList.get(startIndex).getEmail().compareTo(email) > 0){
			return startIndex;
		}

		if(customerList.get(endIndex).getEmail().compareTo(email) < 0){
			return endIndex+1;
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
	
	/**
	 * This method is used to update the customer in the database.
	 * It accepts 2 parameters, an email to search for customer and
	 * a creditcard to add to that customer. If email doesn't match 
	 * any customer's email in the database, it will throw NonExistingCustomerException.
	 * 
	 * @param Email email - an email to search customer in the database
	 * @param CreditCard card - a credit card to add to the customer
	 */
	@Override
	public void update(Email email, CreditCard card) throws NonExistingCustomerException {
		if(binarySearch(database, email) == -1)
			throw new NonExistingCustomerException("The customer with email " + email
					+ " is not exist in the database");
		
		int indexOfMatchedEmail = binarySearch(database, email);
		database.get(indexOfMatchedEmail).setCreditCard(Optional.of(card));;
	}
}
