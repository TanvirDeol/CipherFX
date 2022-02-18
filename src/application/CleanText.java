package application;
import java.util.*;
public class CleanText {

	/**
	 * This method uses a language as a parameter and removes
	 * every character and word that doesn't belong in the
	 * Language
	 * @param text - the input text
	 * @param lang - the language to detect for
	 * @return - an array of separated words of the given language
	 */
	public static String[] clean(String text, String lang) {
		String alpha ="";
		HashSet<String>characters = new HashSet<String>();
		switch(lang) {
			case "ENG": alpha = Alphabet.ENGLISH.getLetters(); break;
			case "FRN": alpha = Alphabet.FRENCH.getLetters();  break;
			case "SPN": alpha = Alphabet.SPANISH.getLetters(); break;
			case "ITA": alpha = Alphabet.ITALIAN.getLetters(); break;
			case "GRK": alpha = Alphabet.GREEK.getLetters(); break;
		}
		
		for(int i=0;i<alpha.length();i++) 
		{characters.add(alpha.charAt(i)+"");}
		String[] result = text.toUpperCase().split(" ");
		ArrayList<String>words  = new ArrayList<String>();
		//Using a HashSet for lookups, makes the function below run in O(n) time.
		//If the alphabet was in a string instead, this code would run in O(n^2) time
		String temp="";
		for(String word:result) {
			temp ="";
			for(int i=0;i<word.length();i++) {
				if(characters.contains(word.charAt(i)+"")) 
					{temp+=word.charAt(i);}
			}
			if(temp.length()>0) {words.add(temp);}
		}
		return words.toArray(new String[words.size()]);
	}
	
	/**
	 * This method is used to prepare the text by cleaning it, 
	 * so it can be used in the detectLanguage() method. It capitalizes words
	 * and removed punctuation so the program can find exact matches.
	 * @param text - Input text
	 * @return - Returns an array of separated words, that is cleaned up.
	 */
	public static String[] fastClean(String text) {
		//This method is much simpler than the clean() method
		//It runs in O(n) time, but has some drawbacks with accuracy
		String[] result =  text.replaceAll("[^\\p{IsAlphabetic} ]", "").toUpperCase().split(" ");
		return result;
	}

}
