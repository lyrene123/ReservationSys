package dw317.lib;
import java.io.*;

/**
 * 
 * @author Ali Dali
 *
 */

public class Email implements Comparable<Email>, Serializable {

	private static final long serialVersionUID = 42031768871L;
	private final String address;

	public Email(String address){

		this.address = validateEmail(address);
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
		String host = address.substring(address.indexOf('@')+1);
		return host;
	}


	public String getUserId(){
		String userId = address.substring(0, address.indexOf('@'));
		return userId;
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


	@Override
	public int compareTo(Email email){

		if(email == null){
			throw new IllegalArgumentException();
		}

		//Making the subStrings Lower case Because the method Compares in an insensitive way
		String emailHost = email.getHost().toLowerCase();
		String emailUId = email.getUserId().toLowerCase();

		if(this.getUserId().equals(emailUId)){
			return 0;
		}
		for(int i = 0; i < emailHost.length(); i++){
			if(this.getHost().toLowerCase().charAt(i) > emailHost.charAt(i)){
				return 1;
			}else if(this.getHost().toLowerCase().charAt(i) < emailHost.charAt(i)){
				return -1;
			}
		}
		for(int i = 0; i < emailUId.length(); i++){
			if(this.getUserId().toLowerCase().charAt(i) > emailUId.charAt(i)){
				return 1;
			}else if(this.getUserId().toLowerCase().charAt(i) < emailUId.charAt(i)){
				return -1;
			}
		}
		return 0;
	}

	/**
	 * @param email
	 * @return email if Valid
	 * @throws IllegalArgumentException   
	 */
	private static String validateEmail(String email)throws IllegalArgumentException{

		if(email == null){
			throw new IllegalArgumentException();
		}
		String userId = email.substring(0, email.indexOf('@'));
		String host = email.substring(email.indexOf('@') +1);

		if(userId.length() < 1 || userId.length() > 32){
			throw new IllegalArgumentException("User ID must be Between 1 and 32 Characters");
		}else{
			for(int i = 0; i<userId.length(); i++){
				if(!Character.isAlphabetic(userId.charAt(i)) && !Character.isDigit(userId.charAt(i))
						&& userId.charAt(i) != '_' && userId.charAt(i) != '-' && userId.charAt(i) != '.'){
					throw new IllegalArgumentException("User ID can ONLY include \"upper or lower case letters\", \"Digits\", \"Hyphens\"(-), \"Dashe\"(_) and \"Dots\"(.)");
				} 
			}
			if(userId.charAt(0) == '.' || userId.charAt(userId.length()-1) == '.'){
				throw new IllegalArgumentException("User ID CANNOT begin or end with \".");

			}
		}

		if(host.length() < 1 || host.length() > 32){
			throw new IllegalArgumentException("Host Name must be Between 1 and 32 Characters");
		}

		for(int i = 0; i < host.length(); i++){
			if(!Character.isAlphabetic(host.charAt(i)) && !Character.isDigit(host.charAt(i))
					&& host.charAt(i) != '-' && host.charAt(i) != '.'){
				throw new IllegalArgumentException("Host Name can ONLY include \"upper or lower case letters\", \"Digits\" and \"Hyphens\"(-)");
			}

		}
		if(host.charAt(0) == '-' || host.charAt(host.length()-1) == '-'){
			throw new IllegalArgumentException("Host Name CANNOT begin or end With \"-\"");
		}
		return email;

	}
}