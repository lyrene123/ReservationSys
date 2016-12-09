package groupLAPD.hotel.ui;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import dw317.hotel.data.*;
import dw317.lib.Email;
import dw317.lib.creditcard.CreditCard;
import groupLAPD.hotel.business.DawsonCustomer;
import groupLAPD.hotel.business.DawsonReservation;
import groupLAPD.hotel.business.DawsonRoom;
import dw317.hotel.business.RoomType;
import dw317.hotel.business.interfaces.*;

/**
 * Partial code was provided.
 * 
 * @author Pengkim Sy
 */
public class TextController {
	private HotelManager model;  
	
    private enum Command
    {
        CUSTOMER_INFO, REGISTER_CUSTOMER, CREATE_RESERVATION, RESERVATION_INFO, CHANGE_CREDITCARD, STOP
    }
 	
    public TextController(HotelManager model) {
		this.model = model;
	}

	public void run() {
	       Scanner keyboard = new Scanner (System.in);
	        Command[] commands = Command.values ();
	        String menu = createMenu (commands);
	        Command choice;
	        // you should validate the input.
	        do
	        {       
	            System.out.print (menu);
	            choice = getUserChoice (commands,keyboard);
	            switch (choice)
	            {
	                case REGISTER_CUSTOMER:
	                    newCustomer(keyboard);
	                    break;
	                case CREATE_RESERVATION:
	                	newReservation(keyboard);
	                	break;
	               case CUSTOMER_INFO:
	                	customerInfo(keyboard);
	                	break; 
	                case RESERVATION_INFO:
	                	reservationInfo(keyboard);
	                    break;                    
	                case CHANGE_CREDITCARD:
	                	updateCard(keyboard);
	                	break;
	                
	                case STOP:
	                		//nothing
	            }
	        }
	        while (choice != Command.STOP);
	        
	        //When stopped, need to disconnect
	        try {
	        	this.model.closeHotel();
	        }
	        catch (IOException ioe) {
	        	System.out.println("An error occured when closing the database.");
	        }
	}
	

	/**
	 * newCustomer is to register a new customer to the database. This will ask
	 * for the first name, last name, and an email. 
	 * 
	 * @author Pengkim Sy
	 * @param keyboard
	 */
    private void newCustomer(Scanner keyboard) {
        keyboard.nextLine (); //consume any previous value
        
		//get name
		String firstName = getInput(keyboard, "\nPlease enter the first name: ");
		String lastName = getInput(keyboard, "Please enter the last name: ");
		
		//get email
    	String email = getEmail(keyboard);

		try {
			this.model.registerCustomer(firstName, lastName, email);
		} catch (DuplicateCustomerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e){
		}
		
	}
    
    /**
     * newReservation is to book a reservation for the customer. This
     * will ask for the customer's email and assign that customer to
     * a reservation.
     * 
     * @author Pengkim Sy
     * @param keyboard
     */
    private void newReservation(Scanner keyboard) {
        keyboard.nextLine (); //consume any previous value   

        //get customer
        String email = getEmail(keyboard);
        Customer customer = null;        
        try {
			customer = this.model.findCustomer(email);
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
        
        //get dates
        LocalDate checkinDate = getDate(keyboard, "Please enter the checkin date.");
        LocalDate checkoutDate = getDate(keyboard, "Please enter the checkout date.");
        
        //get rooms
        RoomType roomType = getRoomType(keyboard);   
        
        try{
        	this.model.createReservation(customer, checkinDate, checkoutDate, roomType);
        } catch(IllegalArgumentException e){
        	System.out.println(e.getMessage());
        }
	}

    /**
     * customerInfo is to check the information of a customer based on
     * the email input.
     * 
     * @author Pengkim Sy
     * @param keyboard
     */
    private void customerInfo(Scanner keyboard) {
        keyboard.nextLine (); //consume any previous value   
        
        //get customer's email
        String email = getEmail(keyboard);
        
        try {
            this.model.findCustomer(email);
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		}
    }
    
    /**
     * reservationInfo is to the reservation information of a customer
     * based the input email.
     * @param keyboard
     */
    private void reservationInfo(Scanner keyboard) {
        keyboard.nextLine (); //consume any previous value   

        //get customer's email
        String email = getEmail(keyboard);
        
        try {
     			Customer customer = this.model.findCustomer(email);
     	        this.model.findReservations(customer);
     		} catch (NonExistingCustomerException e) {
     			System.out.println(e.getMessage());
     		}
    }
    
    /**
     * updateCard will ask for a customer email and card number, and it 
     * will update the credit card of the customer based on the input email
     * and card number.
     * 
     * @author Pengkim Sy
     * @param keyboard
     */
    private void updateCard (Scanner keyboard) {
    	keyboard.nextLine();

    	//get customer's email
        String email = getEmail(keyboard);

        //get card's number
    	String cardNumber = getInput(keyboard, "\nPlease enter the credit card number: ");

    	//get credit card
    	CreditCard.CardType cardType = getCardType(keyboard);
    	
    	try {
			this.model.updateCreditCard(email, cardType.toString(), cardNumber);
		} catch (NonExistingCustomerException e) {
			System.out.println(e.getMessage());
		} catch (IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
    }
    
    
	private RoomType getRoomType(Scanner keyboard) {
        String menu = "\nSelect the room type from the menu:\n";
        int maxChoiceValue = RoomType.values().length;
        RoomType result = null;

        for (int i = 0; i < maxChoiceValue ; i++)
            menu += "\t" + (i + 1) + " - " + RoomType.values() [i] + "\n";
        menu += "\nEnter your choice: ";
        
        System.out.print(menu);
		
		boolean invalid;
        int userChoice = 0;
        do
        {
            try
            {
                invalid = false;
                userChoice = keyboard.nextInt ();
                if (userChoice <= 0 || userChoice > maxChoiceValue)
                {
                    System.out.print ("Invalid choice! Enter a number in "
                            + " the range of 1 to " + maxChoiceValue+ " ");
                    invalid = true;
                }
            }
            catch (java.util.InputMismatchException e)
            {
                System.out.print ("Invalid choice! You must enter a" +
                        " numeric value in the range of 1 to " + maxChoiceValue+ " ");
                invalid = true;
                keyboard.nextLine (); //consume the invalid value
            }
        }
        while (invalid);
        result = RoomType.values()[userChoice-1];
        return result;
	}

	private CreditCard.CardType getCardType(Scanner keyboard) {
        String menu = "\nSelect the credit card type from the menu:\n";
        int maxChoiceValue = CreditCard.CardType.values().length;
        CreditCard.CardType result = null;

        for (int i = 0; i < maxChoiceValue ; i++)
            menu += "\t" + (i + 1) + " - " + CreditCard.CardType.values() [i] + "\n";
        menu += "\nEnter your choice: ";
        
        System.out.print(menu);
		
		boolean invalid;
        int userChoice = 0;
        do
        {
            try
            {
                invalid = false;
                userChoice = keyboard.nextInt();
                if (userChoice <= 0 || userChoice > maxChoiceValue)
                {
                    System.out.print ("Invalid choice! Enter a number in "
                            + " the range of 1 to " + maxChoiceValue+ " ");
                    invalid = true;
                }
            }
            catch (java.util.InputMismatchException e)
            {
                System.out.print ("Invalid choice! You must enter a" +
                        " numeric value in the range of 1 to " + maxChoiceValue+ " ");
                invalid = true;
                keyboard.nextLine (); //consume the invalid value
            }
        }
        while (invalid);
        result = CreditCard.CardType.values()[userChoice-1];
        return result;
	}

	
	private String getEmail(Scanner keyboard) {
        boolean invalid;
        String email;
        do
        {
                invalid = false;
                email = getInput(keyboard, "Please enter the email address: ");
                try {
                	@SuppressWarnings("unused")
					Email emailObj = new Email(email);
                }
                catch (IllegalArgumentException e){
                    System.out.println ("Invalid email!" + e.getMessage());
                    System.out.print("Please try again: ");
                    
                    invalid = true;
                }
        }
        while (invalid);
        return email;
	}
	
	private String getInput(Scanner keyboard, String message) {
		//used for any general string input
		System.out.print(message);
        return keyboard.nextLine();
	}
	
	private int getInt(Scanner keyboard, String message) {
		System.out.print(message);
		//user entered something that is not an int
		while (!keyboard.hasNextInt()) {
		  //consume the invalid token, including any leading whitespace
		  keyboard.next();
		  System.out.print("Invalid – Enter only a whole number ");
		}
		return keyboard.nextInt();
	}
	
	private LocalDate getDate (Scanner keyboard, String message) {
        boolean invalid;
        int year, month, day;
        LocalDate date = null;
        System.out.println(message);

        do
        {
                invalid = false;
                year = getInt(keyboard, "Please enter the year : ");
                month = getInt(keyboard, "Please enter the month (1-12): ");
                day = getInt(keyboard, "Please enter the day (1-31): ");
                try {
					date = LocalDate.of(year, month, day);
                }
                catch (DateTimeException e){
                    System.out.println ("Invalid date!" + e.getMessage());
                    System.out.print("Please try again: ");
                    
                    invalid = true;
                }
        }
        while (invalid);
        return date;
	}
	

	private String createMenu (Command[] commands)
    {
        String menu = "\nDawson Hotel Menu\nSelect a choice from the menu:\n";
        int numChoices = commands.length;
        for (int i = 0 ; i < numChoices ; i++)
            menu += "\t" + (i + 1) + " - " + commands [i] + "\n";
        menu += "\nEnter your choice: ";
       
        return menu;
    }


    private Command getUserChoice (Command[] commands,Scanner keyboard)
    { 
        boolean invalid;
        int maxChoiceValue = commands.length;
        int userChoice = 0;
        do
        {
            try
            {
                invalid = false;
                userChoice = keyboard.nextInt ();
                if (userChoice <= 0 || userChoice > maxChoiceValue)
                {
                    System.out.print ("Invalid choice! Enter a number in "
                            + " the range of 1 to " + maxChoiceValue+ " ");
                    invalid = true;
                }
            }
            catch (java.util.InputMismatchException e)
            {
                System.out.print ("Invalid choice! You must enter a" +
                        " numeric value in the range of 1 to " + maxChoiceValue+ " ");
                invalid = true;
                keyboard.nextLine (); //consume the invalid value
            }
        }
        while (invalid);
        return commands [userChoice - 1];
    }

}