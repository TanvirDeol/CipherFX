package application;
import java.util.*;

public class CipherUtil {
	public static Random rand;
	/**
	 * Encrypts an array of Strings using Caeser Cipher
	 * @param words - String[] to encrypt
	 * @param lang - language of encrypted text
	 * @return - Returns the key and encrypted text
	 */
	public static String[] encryptText(String[] words, String lang) {
		//converts String[] to String
		String text ="";
		for(int i=0;i<words.length;i++) {
			text+=words[i]+" ";
			if(i%10==0)text+="\n";
		}
		//creates random key to shift by
		rand= new Random();
		int shift = rand.nextInt(lang.length()-2);
		String newAlpha="";
		//for every character in original language, the new character set is
		//its index+shift. If that value is over 26(the length of language), start from the beginning.
		System.out.println(lang);
		for(int i=0;i<lang.length();i++) {
			newAlpha+=lang.charAt((i+shift)%lang.length());
		}
		System.out.println(newAlpha);
		char[] chrArr = text.toCharArray();
		for(int i=0;i<chrArr.length;i++) {
			if(lang.contains(chrArr[i]+"")) {
				int idx = lang.indexOf(chrArr[i]);
				chrArr[i] = newAlpha.charAt(idx);
			}
		}
		text = String.valueOf(chrArr);
		String[] res = {shift+"",text};
		return res;
	}
	/**
	 * Decrypts text using Caeser Cipher and a Key
	 * @param input - text to Decipher
	 * @param key - key to use
	 * @return - Returns deciphered text
	 */
	public static String decryptText(String input, int key) {
		int langIdx = Integer.parseInt(input.substring(0, 1));
		input = input.substring(1);
		langIdx--;
		String alpha ="";
		switch(langIdx) {
		case 0: alpha = Alphabet.ENGLISH.getLetters(); break;
		case 1: alpha = Alphabet.FRENCH.getLetters(); break;
		case 2: alpha = Alphabet.GREEK.getLetters(); break;
		case 3: alpha = Alphabet.SPANISH.getLetters(); break;
		case 4: alpha = Alphabet.ITALIAN.getLetters(); break;
		}
		String newAlpha ="";
		for(int i=0;i<alpha.length();i++) {
			newAlpha+= alpha.charAt((i+key)%alpha.length());
		}
		char[] chrArr = input.toCharArray();
		for(int i=0;i<chrArr.length;i++) {
			if(newAlpha.contains(chrArr[i]+"")) {
				int idx = newAlpha.indexOf(chrArr[i]);
				chrArr[i] = alpha.charAt(idx);
			}
		}
		input = String.valueOf(chrArr);
		
		return input;
	}
	/**
	 * Deciphers encrypted text without a key. Language is specified.
	 * @param input - text to hack
	 * @param langMap - HashMap dataBase
	 * @return - Returns deciphered text
	 */
	public static String hack(String input, ArrayList<HashMap<String,Integer>> langMap) {
		//chooses language
		int langIdx = Integer.parseInt(input.substring(0, 1));
		input = input.substring(1);
		langIdx--;
		String alpha ="";
		switch(langIdx) {
		case 0: alpha = Alphabet.ENGLISH.getLetters(); break;
		case 1: alpha = Alphabet.FRENCH.getLetters(); break;
		case 2: alpha = Alphabet.GREEK.getLetters(); break;
		case 3: alpha = Alphabet.SPANISH.getLetters(); break;
		case 4: alpha = Alphabet.ITALIAN.getLetters(); break;
		}
		int[] shiftFreq = new int [alpha.length()];
		String alphaHolder = alpha;
		String newAlpha = "";
		
		int max=0; String res = "";
		for(int q=0;q<alpha.length();q++) {
			newAlpha = "";
			//new Alphabet
			for(int i=0;i<alpha.length();i++) newAlpha += alphaHolder.charAt((i+1)%alphaHolder.length());
			alphaHolder = newAlpha;
			//changes text
			char [] chrArr = input.toCharArray();
			for(int i=0;i<chrArr.length;i++) {
				if(newAlpha.contains(chrArr[i]+"")) {
					int idx = newAlpha.indexOf(chrArr[i]);
					chrArr[i] = alpha.charAt(idx);
				}
			}
			//counts frequency
			String[] words = String.valueOf(chrArr).split(" ");
			for(int i=0;i<words.length;i++) {
				if(langMap.get(langIdx).containsKey(words[i])) {
					if(langMap.get(langIdx).get(words[i])>0) {
						shiftFreq[q]++;
					}
				}
			}	
			if(shiftFreq[q]>max){res = String.valueOf(chrArr); max = shiftFreq[q];}
		}
		return res;
	}
	/**
	 * Encrypts text using Robust Cipher
	 * @param input - Text to encrypt
	 * @return - Returns key and encrypted text
	 */
	public static String[] robustEncrypt(String input) {
		Set<Character> charSet = new HashSet<Character>();
		input = input.replaceAll("[^\\p{IsAlphabetic} ]", "").toUpperCase();
		char[] chrArr = input.toCharArray();
		for(int i=0;i<chrArr.length;i++) charSet.add(chrArr[i]);
		
		HashMap<Integer,Integer> hMap = new HashMap<Integer,Integer>();
		Set<Integer> usedNums = new HashSet<Integer>();
		Random rand = new Random();
		
		for(char c:charSet) {
			int x =  rand.nextInt(92)+33;
			while(usedNums.contains(x)) {
				x = rand.nextInt(92)+33;
			}
			usedNums.add(x);
			hMap.put((int)c, x);
		}
		
		for(int i=0;i<chrArr.length;i++) {
			int cur = hMap.get((int)chrArr[i]);
			chrArr[i] = (char)cur;			
		}
		input = String.valueOf(chrArr);
		String key="";
		for(Map.Entry<Integer, Integer> data:hMap.entrySet()) {
			key+=data.getValue()+"-"+data.getKey()+">";
		}
		String[] res = {key,input};
		return res;
	}
	/**
	 * Deciphers text using Robust Cipher and Key
	 * @param key - Key to use
	 * @param input - Text to decipher
	 * @return - Returns deciphered text
	 */
	public static String robustDecrypt(String key, String input) {
		HashMap<Integer,Integer> hMap = new HashMap<Integer,Integer>();
		key = key.replaceAll("[^0-9]", " ");
		System.out.println(key);
		Scanner sc = new Scanner(key);
		while(sc.hasNextInt()) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			System.out.println((char)a+" "+(char)b);
			hMap.put(a, b);
		}
		char[] chrArr = input.toCharArray();
		for(int i=0;i<chrArr.length;i++) {
			try {
			int cur = hMap.get((int)chrArr[i]);
			chrArr[i] = (char)cur;
			}catch(Exception e) {}
		}
		input = String.valueOf(chrArr);
		sc.close();
		return input;
	}
}
