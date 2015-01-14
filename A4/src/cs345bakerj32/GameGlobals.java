package cs345bakerj32;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class GameGlobals {
		
	public static List<Action> allActions = new ArrayList<Action>();
	public static List<Word> allWords = new ArrayList<Word>();
	public static Player thePlayer;
	public static CommandInterp interp = new CommandInterp(
			new BufferedReader(
					new InputStreamReader(System.in)), System.out);;
//	public static PrintStream messageOut = new PrintStream();
	
}
