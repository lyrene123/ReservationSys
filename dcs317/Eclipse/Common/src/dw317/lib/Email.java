package dw317.lib;
import java.io.*;

/**
 * 
 * @author Ali Dali
 *
 */

public class Email implements Serializable Comparable<Email> {

	final private long serialVersionUID = 42031768871L;
	private final String address;

	public Email(String address){

		validateEmail(address);
	}

	

	/**
	 * @return
	 */
	public String getAddress(){
		return address;
	}

	/**
	 * @return
	 */
	public String getHost(){
		String host = address.substring(address.indexOf('@')+1, address.length());
		return host;
	}


	public String getUserId(){
		String userId = address.substring(0, address.indexOf('@'));
		return userId;
	}

	
	public String getNumber(){

	}

	public Scheme getType(){

	}

	
	public String toString(){
		return address;
	}

	//@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + (int) (serialVersionUID ^ (serialVersionUID >>> 32));
		return result;
	}

	//@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		return true;
	}
	
	
	public int compareTo(String email){
		
		if(email == null){
			throw new IllegalArgumentException();
		}
		
		//Making the subStrings Lower case Because the method Compares in an insensitive way
		String addressHost = address.substring(address.indexOf('@'), address.indexOf('.')).toLowerCase();
		String emailHost = email.substring(email.indexOf('@'), email.indexOf('.')).toLowerCase();
		String addressUId = address.substring(0, address.indexOf('@')).toLowerCase();
		String emailUId = email.substring(0, email.indexOf('@')).toLowerCase();
		
		if(this.address == email){
			return 0;
		}
		for(int i = 0; i < addressHost.length(); i++){
			if(addressHost.charAt(i) > emailHost.charAt(i)){
				return -1;
			}else if(addressHost.charAt(i) < emailHost.charAt(i)){
				return 1;
			}
		}
		for(int i = 0; i < addressUId.length(); i++){
			if(addressUId.charAt(i) > emailUId.charAt(i)){
				return -1;
			}else if(addressUId.charAt(i) < emailUId.charAt(i)){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * @param email
	 * @return email if Valid
	 * @throws IllegalArgumentException
	 */
	private String validateEmail(String email)throws IllegalArgumentException{

		if(email == null){
			throw new IllegalArgumentException();
		}

		if(email.indexOf('@')-1 < 1 || email.indexOf('@')-1 > 32){
			throw new IllegalArgumentException("User ID must be Between 1 and 32 Characters");
		}else{
			for(int i = 0; i<email.indexOf('@'); i++){
				if(!Character.isAlphabetic(email.charAt(i)) && !Character.isDigit(email.charAt(i))
						&& email.charAt(i) != '_' && email.charAt(i) != '-' && email.charAt(i) != '.'){
					throw new IllegalArgumentException("User ID can ONLY include \"upper or lower case letters\", \"Digits\", \"Hyphens\"(-), \"Dashe\"(_) and \"Dots\"(.)");
				}else if(email.charAt(0) == '.' || email.charAt(email.indexOf('@')-1) == '.'){
					throw new IllegalArgumentException("User ID CANNOT begin or end with \".");

				}
			}
		}
		
		if(email.substring(email.indexOf('@')+1, email.length()).length() < 1 || email.substring(email.indexOf('@')+1, email.length()).length() > 32){
			throw new IllegalArgumentException("Host Name must be Between 1 and 32 Characters");
		}
		
			int middle = email.indexOf('@')+1;
		
		for(int i = middle; i < email.length(); i++){
			if(!Character.isAlphabetic(email.charAt(i)) && !Character.isDigit(email.charAt(i))
					 && email.charAt(i) != '-'){
				throw new IllegalArgumentException("Host Name can ONLY include \"upper or lower case letters\", \"Digits\" and \"Hyphens\"(-)");
			}else if(email.charAt(middle) == '-' || email.charAt(email.indexOf('@')-1) == '-'){
				throw new IllegalArgumentException("Host Name CANNOT begin or end With \"-\"");
			}
			
		}
		return email;

	}
}
