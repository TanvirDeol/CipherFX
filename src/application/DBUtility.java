package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.control.TextArea;


public class DBUtility {

	/**
	 * Creates 5 databases (from each language) from a relative directory in the program.
	 * These databases are stored in the program, so they can be accessed from any computer
	 * @return - Returns a list of databases, one for each language
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static ArrayList<HashMap<String,Integer>> fetchDataBases(String dir) throws FileNotFoundException, IOException, ClassNotFoundException{
		ArrayList<HashMap<String, Integer>> finalMap= new ArrayList<HashMap<String,Integer>>();
		
		ArrayList<String> fileArr= new ArrayList<String>();
		fileArr.add(dir+"\\ENG.bin");
		fileArr.add(dir+"\\FRN.bin");
		fileArr.add(dir+"\\GRK.bin");
		fileArr.add(dir+"\\SPN.bin");
		fileArr.add(dir+"\\ITA.bin");
		
		for(int i=0;i<fileArr.size();i++) {
			finalMap.add(FileUtility.readObject(fileArr.get(i)));
		}
		return finalMap;
	}
	/**
	 * Detects language from a text
	 * @param text - Input text
	 * @param langMap - list of language databases, use it to compare each word from text
	 * @param showLangContent - TextArea object to display results to
	 * @param print - Only displays results if this boolean is true
	 * @return - Returns a string indicating what language it is
	 */
	public static String detectLang(String text, ArrayList<HashMap<String,Integer>> langMap, TextArea showLangContent,boolean print) {
		int freqArr[] = new int[5];
		String[] words =CleanText.fastClean(text);
		for(int i=0;i<words.length;i++) {
			int maxF=0; int mIdx=0;
			for(int j=0;j<langMap.size();j++) {
				if(langMap.get(j).containsKey(words[i])) {
					if(langMap.get(j).get(words[i])>0) {
						if(langMap.get(j).get(words[i])>maxF) {mIdx=j; maxF = langMap.get(j).get(words[i]);}
					}
				}
			}
			if(langMap.get(mIdx).get(words[i])!= null) freqArr[mIdx]++; 
			if(print) showLangContent.setText(showLangContent.getText()+"Word: "+words[i]+" ---> Freq: "+langMap.get(mIdx).get(words[i])+"\n");
		}
		int maxFreq= 0; int maxIdx = 0;
		for(int i=0;i<freqArr.length;i++) {
			//System.out.println(freqArr[i]);
			if(freqArr[i]>maxFreq) { maxFreq = freqArr[i]; maxIdx=i;}
			else continue;
		}
		String lang="";
	//	System.out.println(maxIdx);
		switch(maxIdx) {
		case 0:lang = "ENG";break;
		case 1:lang = "FRN";break;
		case 2:lang = "GRK";break;
		case 3:lang = "SPN";break;
		case 4:lang = "ITA";break;
		default: lang = "Cannot be Determined"; break;
		}
		return lang;
	}
	
	/**
	 * Gets file and moves content to a string
	 * @param file - File to read from
	 * @return - returns a string with containing content
	 * @throws IOException
	 */
	public static String fileToString(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String input="",temp;
		while((temp = br.readLine())!=null){
			input+= temp+"\n";
		}
		br.close();
		return input;
	}
	/**
	 * Updates a dataBase using an array of words
	 * @param words - String[] to insert into Database
	 * @param hMap - Database to Update
	 * @return - Returns updated database
	 */
	public static HashMap<String,Integer> updateDataBase(String[] words, HashMap<String,Integer> hMap){
		for(String word:words) {
			if(word.length() > 0) {
				if(hMap.containsKey(word)) hMap.put(word, hMap.get(word)+1);
				else hMap.put(word, 1);
			}
		}
		return hMap;
	}
	
	/**
	 * Prints a Database (A Hash Map with distinct words and their frequencies)
	 * to a TextArea object
	 * @param hMap - DataBase to print
	 * @param textArea - Text Area object to print to
	 */
	public static void printDB(HashMap<String,Integer> hMap, TextArea textArea) {
		textArea.appendText("\n");
		for(Map.Entry<String, Integer> data:hMap.entrySet()) {
			textArea.appendText(data.getKey()+": "+data.getValue()+"\n");
		}
	}
	/**
	 * Prints a sorted Database, a sorted Database is an ArrayList, which is a
	 * copy of the Hash Map. Since HashMaps can't be sorted, the ArrayList is 
	 * sorted and displayed.
	 * @param arr - ArrayList to print
	 * @param textArea - TextArea object to print to
	 */
	public static void printSortedDB(ArrayList<Node> arr, TextArea textArea) {
		textArea.appendText("\n");
		for(Node node:arr) textArea.appendText(node.word+": "+node.freq+"\n");
	}
	/**
	 * Sorts a data base in alphabetical order
	 * @param hMap - database to sort
	 * @return - returns a copy of the database (an ArrayList) that is sorted
	 */
	public static ArrayList<Node> sortByAlpha(HashMap<String,Integer> hMap){
		ArrayList<Node> sortedArr= new ArrayList<Node>();
		for(Map.Entry<String, Integer> data:hMap.entrySet()) {
			sortedArr.add(new Node(data.getKey(),data.getValue()));
		}
		Collections.sort(sortedArr,new sortAlpha());
		return sortedArr;
	}
	/**
	 * Sorts a database by frequency
	 * @param hMap - database to sort by frequency
	 * @return - returns a copy of the database (an ArrayList) that is sorted
	 */
	public static ArrayList<Node> sortByFreq(HashMap<String,Integer> hMap){
		ArrayList<Node> sortedArr = new ArrayList<Node>();
		for(Map.Entry<String, Integer> data:hMap.entrySet()) {
			sortedArr.add(new Node(data.getKey(),data.getValue()));
		}
		Collections.sort(sortedArr,new sortFreq());
		return sortedArr;
	}
	
	/**
	 * Removes low frequency words from a database
	 * @param hMap - DataBase to modify
	 * @param threshold - Any word with a frequency equal or below this threshold is removed
	 * @return - Returns the updated database
	 */
	public static HashMap<String,Integer> removeLowFreq(HashMap<String,Integer> hMap, int threshold){
		Iterator<Entry<String, Integer>> it = hMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Integer> map= it.next();
			if(map.getValue()<=threshold)it.remove();
		}
		return hMap;
	}
}
/**
 * A private node class to hold a word and its frequency
 * @author Tanvir
 *
 */
class Node {
	String word;
	int freq;
	public Node(String w,int f) {
		this.word = w;
		this.freq = f;
	}
	public String toString(){
		return this.word +" "+this.freq;
	}
}
/**
 * This class implements a comparable method to allow sorting alphabetically
 */
class sortAlpha implements Comparator<Node>{
	@Override
	public int compare(Node first, Node second) {
		return first.word.compareTo(second.word);
	}
}
/**
 * This class implements a comparable method to allow sorting by frequency
 */
class sortFreq implements Comparator<Node>{
	@Override
	public int compare(Node first, Node second) {
		return second.freq-first.freq;
	}
}
