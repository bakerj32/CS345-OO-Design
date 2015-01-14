package cs345bakerj32;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.OutputStream;

public class GameGlobals {
		
	public static List<Action> allActions = new ArrayList<Action>();
	public static List<Word> allWords = new ArrayList<Word>();
	public static List<GameItem> allItems = new ArrayList<GameItem>();
	public static String[] noiseWords = {"a", "an", "the", "and", "it", "that", "this", "to", "at", "with", "room"};
	public static Player thePlayer;
	public static CommandInterp interp = new CommandInterp(
			new BufferedReader( new InputStreamReader(System.in)), System.out);
	
	public static StreamPrinter messageOut = new StreamPrinter(System.out, 72);
	
	public static GameItem findItem(Word w){
		for(GameItem item : allItems){
			for(Word word : item.vocab.words){
				if(word.value.equals(w.value)){ return item; }
			}
		}
		return null;
	}
	
}
