package dw317.lib;
import java.io.*;


/**
 * This Name class allows user to enter his first name and last name
 * validates, then if valid assigns the parameters to the fields.
 * @author Ali Dali
 *
 **/
public class Name implements Serializable {


	private String firstName;
	private String lastName;
	private static final long serialVersionUID = 42031768871L;

	/**
	 * Constructor: Sets the fields according to the parameters
	 * @param firstName
	 * @param lastName
	 */
	public Name(String firstName, String lastName){
		//Validate before assigning
		this.firstName = validateExistance("First Name", firstName).trim();
		this.lastName = validateExistance("Last Name", lastName).trim();
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
		//Validates before setting
		this.firstName = validateExistance("First Name", firstName).trim();
	}

	/**
	 * Sets value to LastName 
	 * @param lastName
	 */
	public void setLastName(String lastName){
		//Validates before setting 
		this.lastName = validateExistance("Last Name", lastName).trim();
	}

	/**
	 * Concatenates firstName and lastName
	 * @return firstName +" "+ lastName
	 */
	public String getFullName(){
		return firstName +" "+ lastName;
	}

	/**
	 * The toString returns a String representation of the person's
	 * first name then last name 
	 * return String firstName and lastName
	 * @override
	 */
	@Override
	public String toString(){
		return firstName +" "+ lastName;
	}

	/**
	 * allocates  a place in memory for the class
	 * @return integer that represents the memory address
	 * @Override
	 */
	@Override
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

	/**
	 * Checks if two Names are equal.
	 * Two Names are equal only if all their attributes are equal
	 * return boolean True if equal, false if not
	 * 	@Override
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)//checks if both objects are the same
			return true;
		if (obj == null)//checks if object is null
			return false;
		if (this.getClass() != obj.getClass())//checks if they have same class
			return false;
		Name other = (Name) obj;//Casting  obj to Name type
		if (firstName == null) {//checks if first name is null
			if (other.firstName != null)//checks if other first name is null
				return false;
			//checks if both first names are not equal in a case insensitive way
		} else if (!firstName.equalsIgnoreCase(other.firstName))
			return false;
		if (lastName == null) {//checks if last name is null
			if (other.lastName != null)//checks if other last name is null
				return false;
			//checks if both last names are not equal in a case insensitive way
		} else if (!lastName.equalsIgnoreCase(other.lastName))
			return false;
		return true;
	}//end of hashCode

	/**
	 * Validate fieldValue, checks if fieldValue is null, empty or invalid
	 * @return a boolean true if the name is Valid, false if not Valid
	 * @throws IllegalArgument Exception When input is invalid
	 */
	private String validateExistance(String fieldName, String fieldValue)
			throws IllegalArgumentException{


		if(fieldValue == null){// Checking if input is null
			throw new IllegalArgumentException("Invalid Input--Must Provide "
					+ fieldName);
		}
		//trimming spaces on both sides
		String trimmedFieldValue = fieldValue.trim();

		if(trimmedFieldValue.isEmpty()){//checking if input is empty
			throw new IllegalArgumentException("Invalid Input--Must Provide "
					+ fieldName);
		}else if(trimmedFieldValue.length() < 2){//checking If length is valid
			throw new IllegalArgumentException("Invalid Input--" + fieldName 
					+"Must be more than 2 Characters");
		}
		
		//We need a try because a StringIndexOutOfBoundsException 
		//will be thrown
		try{
			for(int i = 0; i<trimmedFieldValue.length(); i++){
				//checking for 2  spaces 
				if(trimmedFieldValue.charAt(i) == ' ' &&
						trimmedFieldValue.charAt(i-1) == ' '){
					throw new IllegalArgumentException("Invalid Input--Cannot"
							+ " contain 2 concecutive spaces in: " + fieldName);
					//checking for 2 consecutive dashes "-"
				}else if(trimmedFieldValue.charAt(i) == '-' &&
						trimmedFieldValue.charAt(i-1) == '-'){
					throw new IllegalArgumentException("Invalid Input--Cannot"
							+ " contain two consecutive \"-\" in: " + fieldName);
					//checking for 2 consecutive "'"
				}else if(trimmedFieldValue.charAt(i) == '\'' &&
						trimmedFieldValue.charAt(i-1) == '\''){
					throw new IllegalArgumentException("Invalid Input--Cannot"
							+ " contain two consecutive \"'\" in: " + fieldName);
				}
			}
		}catch(StringIndexOutOfBoundsException e){//catching exception
		}

		for(int i = 0; i < fieldValue.length(); i++){
			//checking if character is not an alphabet, a dash, a ' or a space
			if(!Character.isAlphabetic(fieldValue.charAt(i)) &&
					fieldValue.charAt(i) != '\'' && fieldValue.charAt(i)!='-' 
					&& fieldValue.charAt(i) != ' '){
				throw new IllegalArgumentException("Invalid Input--" + 
						fieldName + " must only contain alphabets, \"'\", \"-\" and space");
			}
		}
		return fieldValue;
	}//end of ValidateExistence()
}//end of Name
