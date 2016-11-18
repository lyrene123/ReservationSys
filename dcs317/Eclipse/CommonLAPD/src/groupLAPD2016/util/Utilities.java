package groupLAPD2016.util;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * This Utilities class is a non instantiable class that is used to 
 * Serialize and deserialize a specific object.
 * @author Ali Dali, Code provided
 *
 */
public class Utilities {

	/**
	 * Private constructor so the class can not be instantiated
	 */
	private Utilities(){

	}


	/**
	 * serializeObject method takes a the object that you want to serialize
	 * and the file name. Then using ObjectOutputStream and FileOutputStream
	 * to convert the object to a sequence of bytes and then writes them to 
	 * the file using the writeObject method.
	 * 
	 * @param object --> to serialize
	 * @param fileSpecification --> to save byte
	 * @throws IOException
	 * @author Teacher
	 */
	public static void serializeObject (Object object, String fileSpecification) 
			throws IOException { 
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream (
					new FileOutputStream (fileSpecification));
			out.writeObject (object);

		}catch (IOException e){
			//normally the exception would be logged to file then thrown
			throw new IOException ("Error serializing object to \n" +
					fileSpecification  + " " + e);
		}
		finally {
			if (out != null)
				out.close ();
		} 
	}


	/**
	 * This method takes as input the file that contains the serialized
	 * object that we want to deserialize. Using ObjectInputStream and 
	 * FileInputStream we convert the stream of bytes back to the object  
	 * and using the readObject method we re-construct the object.
	 *
	 * @param fileSpecification
	 * @return deserialized object
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @author Teacher
	 */
	public static Object deserializeObject (String fileSpecification)
			throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			Object obj = null;
			in = new ObjectInputStream
					(new FileInputStream (fileSpecification));
			if (in != null)
				obj = in.readObject ();
			return obj;
		}
		catch (ClassNotFoundException | IOException e)
		{
			//normally the exception would be logged to file then thrown
			throw new IOException ("Error deserializing object from " +
					fileSpecification + "\n" + e);
		} finally {
			if (in != null)
				in.close ();
		} 
	}


}