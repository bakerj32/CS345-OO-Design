package cs345bakerj32;

public class Word {
	public String value;
	public MatchType type;
	
	public Word(String value, MatchType type){
		this.value = value;
		this.type = type;
	}
	
	public static Word makeWord(String value, MatchType type){
		Word word = new Word(value, type);
		return word;
	}
	
	public String getWord(){
		return this.value;
	}
}
