package dw317.lib;
import java.io.*;

/**
 * This Class Lets a user create An email Address. The constructor uses the ValidateEmail()
 * method to make sure the user input is a valid email address. There are also some getters
 * getAddress() which returns the email address,  getHost() returns the host, getUserId() returns
 * UserId. There are Overridden methods like toString(), equals(), hashCode() and compareTo().
 * @author Ali Dali
 *
 */

public class Email implements Comparable<Email>, Serializable {

	private static final long serialVersionUID = 42031768871L;
	private final String address;

	/**
	 * Constructor, Uses validateEmail() to validate the email address inputed and
	 * if valid assign it to private field.
	 * @param address 
	 */
	public Email(String address){
		this.address = validateEmail(address);//Validate email address
	}

	/**
	 * Gets Email address;
	 * @return address
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * Gets host which is everything after @ sign 
	 * @return host 
	 */
	public String getHost(){
		String host = address.substring(address.indexOf('@')+1);
		return host;
	}

	/**
	 * Gets UserId, which is everything before @ sign
	 * @return userId
	 */
	public String getUserId(){
		String userId = address.substring(0, address.indexOf('@'));
		return userId;
	}

	/**
	 * Returns String representation of email address
	 * @Override
	 * @return address
	 */
	public String toString(){
		return address;
	}

	/** 
	 * Allocates a place in memory for this object
	 * @return result 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (serialVersionUID ^ (serialVersionUID >>> 32));
		return result;
	}

	/** 
	 * This overridden method checks if two email addresses are equal
	 * @Return boolean true if both addresses are equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)//if its the same object
			return true;
		if (obj == null)//if object is null
			return false;
		if (getClass() != obj.getClass())//if its the same class 
			return false;
		Email other = (Email) obj;//Casting object to as Email type
		if (address == null) {//checks if the address is null
			if (other.address != null)//checks if the other address is null
				return false;
		} else if (!address.equals(other.address))//checks if both addresses are equal
			return false;
		return true;
	}


	/**
	 * This compareTo() method compares this object's email address with another
	 * first according to their host name, then if they have the same host name 
	 * we compare the user Id.
	 * @param email
	 * @return 1, 0 or -1 depending if this object's email this bigger, equal or 
	 * 			smaller to the other email address
	 */
	@Override
	public int compareTo(Email email){

		if(email == null){//checks if email is null
			throw new IllegalArgumentException("Please enter email addresss");
		}

		//Making the subStrings Lower case Because the method Compares in an insensitive way
		String emailHost = email.getHost().toLowerCase();
		String emailUId = email.getUserId().toLowerCase();

		if(this.getHost().compareToIgnoreCase(emailHost) != 0){//comparing host
			return this.getHost().compareToIgnoreCase(emailHost);
		}
		//We get here only if the two hosts are equal
		if(this.getUserId().compareToIgnoreCase(emailUId) != 0){//compare UserId
			return this.getUserId().compareToIgnoreCase(emailUId);
		}
		return 0;//returns 0 if they're equal
	}

	/**
	 * This method validates the email address. Its used by the constructor before
	 * assigning the address to the private field.
	 * 
	 * @param email
	 * @return email if Valid
	 * @throws IllegalArgumentException if invalid input  
	 */
	private static String validateEmail(String email)throws IllegalArgumentException{

		if(email == null){//checks if email is null
			throw new IllegalArgumentException("Invalid Input-- Must provide Email");
		}else if(!email.contains("@")){//checks if email contains @ sign
			throw new IllegalArgumentException("Invalid Input-- Email Must contain \"@\"");
		}else if(email.trim().isEmpty()){//trims spaces then checks if email is empty
			throw new IllegalArgumentException("Invalid Input-- Email Must Not be Empty");
		}

		//Dividing email into two subStrings, userId and host
		String userId = email.substring(0, email.indexOf('@'));
		String host = email.substring(email.indexOf('@') +1);

		try{
		for(int i = 0; i < email.length(); i++){
			if((email.charAt(i) == '.' && email.charAt(i-1) == '.')){//checks for two consecutive periods 
				throw new IllegalArgumentException("Invalid Input-- Cannot have concecutive \".\"");
			}else if((email.charAt(i) == '.' && email.charAt(i-1) == '-')){// checks for consecutive hyphen then period
				throw new IllegalArgumentException("Invalid Input-- Cannot Have consecutive \"-\" \".\"");
			}else if((email.charAt(i) == '-' && email.charAt(i-1) == '.')){//checks for consecutive period then hyphen
				throw new IllegalArgumentException("Invalid Input-- Cannot Have consecutive \".\" \"-\" ");
			}
		}
		}catch(StringIndexOutOfBoundsException e){
			
		}

		if(userId.length() < 1 || userId.length() > 32){//checks userId for appropriate length
			throw new IllegalArgumentException("Invalid Input-- User ID must be Between 1 and 32 Characters");
		}else{
			for(int i = 0; i<userId.length(); i++){
				if(!Character.isAlphabetic(userId.charAt(i)) && !Character.isDigit(userId.charAt(i))//checks if User ID contains anything other than alphabets, digits
						&& userId.charAt(i) != '_' && userId.charAt(i) != '-' && userId.charAt(i) != '.'){ //dashes, hyphens and periods
					throw new IllegalArgumentException("Invalid Input-- User ID can ONLY include \"upper or lower case letters\", \"Digits\", \"Hyphens\"(-), \"Dashe\"(_) and \"Dots\"(.)");
				} 
			}
			if(userId.charAt(0) == '.' || userId.charAt(userId.length()-1) == '.'){// checks if userId begins or ends with a period
				throw new IllegalArgumentException("Invalid Input-- User ID CANNOT begin or end with \".\"");
			}else if(userId.charAt(userId.length()-1) == '-'){
				throw new IllegalArgumentException("Invalid Input--User ID CANNOT end with \"-\"");//checks if user id ends with a hyphen
			}	
		}

		if(host.length() < 1 || host.length() > 32){//Checks host for appropriate length
			throw new IllegalArgumentException("Invalid Input-- Host Name must be Between 1 and 32 Characters");
		}

		for(int i = 0; i < host.length(); i++){
			if(!Character.isAlphabetic(host.charAt(i)) && !Character.isDigit(host.charAt(i))//checks if host is contains anything other than alphabets, digits
					&& host.charAt(i) != '-' && host.charAt(i) != '.'){//hyphens and periods
				throw new IllegalArgumentException("Invalid Input-- Host Name can ONLY include \"upper or lower case letters\", \"Digits\" and \"Hyphens\"(-)");
			}
		}

		if(host.charAt(0) == '-' || host.charAt(host.length()-1) == '-'){//checks if host begins or ends with hyphen
			throw new IllegalArgumentException("Invalid Input-- Host Name CANNOT begin or end With \"-\"");
		}else if(host.charAt(host.length()-1) == '.'){//checks if host ends with a period
			throw new IllegalArgumentException("Invalid Input-- Host Name CANNOT end with \".\"");
		}
		return email;//returns email if valid
	}//end of validateEmail()

}//end of Email class
