package cs345bakerj32;
import java.io.*;
import java.util.*;

import static cs345bakerj32.GameGlobals.*;

public class CommandInterp {
	public boolean exit;
	private BufferedReader buffer_reader;
	private PrintStream print_stream;
	public CommandInterp(BufferedReader br, PrintStream ps){
		buffer_reader = br;
		print_stream  = ps;
	}
	public void setExit(boolean exit){
		this.exit = exit;
	}
	
	public List<String> get_command(){
		String str_cmd = new String();
		try{  str_cmd = this.buffer_reader.readLine(); }
		catch(IOException ioe){
	         System.out.println("Error reading command.");
	         System.exit(1);
		}
		List<String> command = GameUtil.canonicalCommand(str_cmd);
		return command;
	}
	
	public Action get_action(List<String> cmd){
		Action ret_action;
		boolean first_match = false;
		boolean second_match = false;
		boolean valid = false;
		// Too many arguments in command.
		if(cmd.size() > 2){ System.out.println("Too many words provided."); }
		// 2-part command (like: "go north")
		else if(cmd.size() == 2){
			if(is_valid_word(cmd.get(0)) && is_valid_word(cmd.get(1))){
				for(Action action : allActions){
					if(action.vocab1 != null && action.vocab2 != null){
						for(Word word : action.vocab1.words){
							if(word.value.equals(cmd.get(0))
									|| (word.value.startsWith(cmd.get(0)) && word.type == MatchType.PREFIX)){ 
								first_match = true; 
							}
						}
						for(Word word: action.vocab2.words){
							if(word.value.equals(cmd.get(1))
									|| (word.value.startsWith(cmd.get(1)) && word.type == MatchType.PREFIX)){ 
								second_match = true; }
						}
						if(first_match && second_match){ return action; }
					}
				}
			}
		}
		else if(cmd.size() == 1){
			if(is_valid_word(cmd.get(0))){
				for(Action action : allActions){
					for(Word word : action.vocab1.words){
						// If word is an exact match
						if((word.value.equals(cmd.get(0))
								// Or it's a prefix match
								|| (word.value.startsWith(cmd.get(0)) && word.type == MatchType.PREFIX))
								// And the second vocab item is null.
								&& action.vocab2 == null){
							return action;
						}
					}
				}
			}
		}
		return null;
	}
	
	public static boolean is_valid_word(String cmd){
		boolean match = false;
		int match_count = 0;
		for(Word word : allWords){
			// Exact match
			if (word.value.equals(cmd)){ 
				match = true;
				match_count++;
			}
			// Prefix match
			else if(word.value.startsWith(cmd) && word.type == MatchType.PREFIX){
				match = true;
				match_count++;
			}
		}
		// Check for match ambiguity.
		if(match_count > 1 && match){
			System.out.println("Word was ambiguous. Be more specific.");
			match = false;
		}
		if(match_count == 0){ System.out.println("Command not recognized."); }
		return match;
	}
	
	public void run(){
		List<String> cmd = new ArrayList<String>();
		while(!this.exit){
			cmd = this.get_command();
			Action next_action = this.get_action(cmd);
			if(next_action != null){
				if(cmd.size() == 2){
					Word w1 = new Word(cmd.get(0), MatchType.EXACT);
					Word w2 = new Word(cmd.get(1), MatchType.EXACT);
					next_action.doAction(w1, w2);
				}
				else{
					Word w1 = new Word(cmd.get(0), MatchType.EXACT);
					Word w2 = new Word(cmd.get(0), MatchType.EXACT);
					next_action.doAction(w1, w2);
				}
			}
		}
		
	}
}
