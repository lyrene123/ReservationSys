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
		this.firstName = validateExistance("First Name", firstName);
		this.lastName = validateExistance("Last Name", lastName);
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
		this.firstName = validateExistance("First Name", firstName);
	}

	/**
	 * Sets value to LastName 
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = validateExistance("Last Name", lastName);
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
	 * Validate fieldValue, checks if fieldValue is null, empty or invalid
	 * @return a boolean true if the name is Valid, false if not Valid
	 */
	private String validateExistance(String fieldName, String fieldValue){
		
		if(fieldValue == null){
			throw new IllegalArgumentException("Invalid Input--Must Provide "+ fieldName);
		}
		 
		String trimmedFieldValue = fieldValue.trim();
		 
		if(trimmedFieldValue.isEmpty()){
			throw new IllegalArgumentException("Invalid Input--Must Provide "+ fieldName);
		}
		
		for(int i = 0; i < fieldValue.length(); i++){
			if(!Character.isAlphabetic(fieldValue.charAt(i)) && fieldValue.charAt(i) != '\'' 
					&& fieldValue.charAt(i) != '-' && fieldValue.charAt(i) != ' '){
				throw new IllegalArgumentException("Invalid Input--" + fieldName + " must only contain alphabets, \"'\", \"-\" and space");
			}
		}
		return fieldValue;
	}
	
	}
