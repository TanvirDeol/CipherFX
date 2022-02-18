package application;
public enum Alphabet{
	
	ENGLISH("ENG","ABCDEFGHIJKLMNOPQRSTUVWXYZ",1),
	FRENCH("FRN","AÀÂÆBCÇDEÉÈÊËFGHIÎÏJKLMNOÔŒPQRSTUÙÛÜVWXYŸZ",2),
	SPANISH("SPN","ABCDEFGHIJKLMNÑOPQRSTUVWXYZ",3),
	ITALIAN("ITA","ABCDEFGHILMNOPQRSTUVZ",4),
	GREEK ("GRK","ΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩ",5);	
	private String language;
	private String letters;
	private int rank;
	
	private Alphabet(String language, String letters, int rank) {
		this.language = language;
		this.letters = letters;
		this.rank =  rank;
	}
	public String getLetters() {
		return this.letters;
	}
	public int getRank() {
		return this.rank;
	}
	public String getLanguage() {
		return this.language;
	}
}
