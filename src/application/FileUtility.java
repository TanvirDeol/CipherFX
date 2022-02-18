package application;
import java.util.*;
import java.io.*;
public class FileUtility implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Saves object to file by serializing it.
	 * @param obj - Object to save
	 * @param dir - Directory to save to
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void saveObject(Object obj, String dir) throws FileNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(dir));
		out.writeObject(obj); 
		out.close();
	}
	/**
	 * Reads serialized object from file
	 * @param dir - Directory to read from
	 * @return - A Hash Map containing all distinct words and their frequencies
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static HashMap<String,Integer> readObject(String dir) throws FileNotFoundException, IOException,ClassNotFoundException{
		ObjectInputStream inp = new ObjectInputStream(new FileInputStream(dir));
		@SuppressWarnings("unchecked")
		HashMap<String,Integer> hMap = (HashMap<String, Integer>) inp.readObject();
		inp.close();
		return hMap;
	}
	/**
	 * Gets a file from a directory and moves all of its content to a single string
	 * @param dir - Directory to read from
	 * @return - Extracted text
	 * @throws IOException
	 */
	public static String readInput(String dir) throws IOException {
		BufferedReader buffread = new BufferedReader(new FileReader(dir));
		String temp; String input="";
		 while((temp =buffread.readLine())!=null){
			 input+=temp+" \n";
		 }
		//System.out.println(input);
		buffread.close();
		return input;
	}
	/**
	 * Merges two databases, keeps only one existing database.
	 * @param custom - The new database to extract info from
	 * @param central - The main database to move info into
	 * @return - Returns main updated database
	 */
	public static HashMap<String,Integer> mergeDataBases(HashMap<String,Integer> custom, HashMap<String,Integer> central){
		for(Map.Entry<String, Integer> data : custom.entrySet()) {
			String s = data.getKey();
			if(central.containsKey(s)) {
				central.put(s, central.get(s)+data.getValue());
			}else {
				central.put(s, data.getValue());
			}
		}
		return central;
	}
	/**
	 * Writes a string to a text File
	 * @param text - String to write
	 * @param dir - Directory to write to 
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(String text, String dir) throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter(dir+"\\ENCRYPTED.txt")){
		out.println(text);
		}
	}
	/**
	 * Writes a string to a text File with a custom name
	 * @param text - String to write
	 * @param dir - Directory to write to 
	 * @param name - String to use name of 
	 * @throws FileNotFoundException
	 */
	public static void writeToFile(String text, String dir, String name) throws FileNotFoundException {
		try (PrintWriter out = new PrintWriter(dir+"\\"+name+".txt")){
		out.println(text);
		}
	}
}
