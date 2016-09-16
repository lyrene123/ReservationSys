package dw317.lib;
import java.io.*;

 
/**
 *  
 * @author Ali Dali
 *
 **/
public class Name implements Serializable {


	

	private String firstName;
	private String lastName;
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Constructer: Sets values
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Gets firstName
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets lastName
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets Value to firstName
 	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets value to LastName 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Concatinates firstName and lastName
	 * @return firstName +" "+ lastName
	 */
	public String getFullName(){
		return firstName +" "+ lastName;
	}

	/* 
	 * @override
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return firstName +" "+ lastName;
	}

	/*
	@Override
	 */
	public int hashCode() {
		firstName = firstName.toUpperCase();
		lastName = lastName.toUpperCase();

		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	
	/*
	@Override
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Name other = (Name) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
	
	/**
	 * Validate name, checks if name is null, empty or invalid
	 * @return True 
	 */
	private boolean isValide(){
		
		if(firstName == null || lastName == null){
			throw new IllegalArgumentException();
		}
		
		for(int i = 0; i < firstName.length(); i++){
			if(!Character.isAlphabetic(firstName.charAt(i)) && firstName.charAt(i) != '\'' 
					&& firstName.charAt(i) != '-' && firstName.charAt(i) != ' '){
				throw new IllegalArgumentException();
			}
		}
		for(int i = 0; i < lastName.length(); i++){
			if(!Character.isAlphabetic(lastName.charAt(i)) && lastName.charAt(i) != '\'' 
					&& lastName.charAt(i) != '-' && lastName.charAt(i) != ' '){
				throw new IllegalArgumentException();
			}
		}
		return true;
	}
}
