package cs345bakerj32;
import java.util.*;


public class VocabItem {
	public String name;
	public List<Word> words;
	
	
	public VocabItem(String name, List<Word> words){
		this.name = name;
		this.words = words;
	}
	public static VocabItem makeVocab(String name, Word... words){
		List<Word> word_list = new ArrayList<Word>();
		for (Word w : words) {
			if(w != null){ word_list.add(w); }
		}
		VocabItem vocab = new VocabItem(name, word_list);
		return vocab;
	}
}
