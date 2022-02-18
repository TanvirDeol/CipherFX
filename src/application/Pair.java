package application;
import java.util.Comparator;

/**
 * An obsolete class to hold a string and its frequency
 * @author Tanvir
 *
 */
public class Pair {
	String word;
	int freq;
	public Pair(String w,int f) {
		this.word = w;
		this.freq = f;
	}
	public String toString(){
		return this.word +" "+this.freq;
	}
}
/**
 * Implements the comparator interface for the pair class
 * It can't be used in Task 2, because this class is private 
 * @author Tanvir
 */
class FreqSort implements Comparator<Pair>{
	@Override
	public int compare(Pair first, Pair second) {
		return second.freq-first.freq;
	}
	
}