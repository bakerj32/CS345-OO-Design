package cs345bakerj32;

public class GameItem {
	public String name;
	public VocabItem vocab;
	public String inventory_desc;
	public String here_is_desc;
	public String long_desc;
	
	public GameItem(String name, VocabItem vocab, String inventory_desc, String here_is_desc, String long_desc){
		this.name = name;
		this.vocab = vocab;
		this.inventory_desc = inventory_desc;
		this.here_is_desc = here_is_desc;
		this.long_desc = long_desc;
	}
	
	public static GameItem makeItem(String name, VocabItem vocab, String inventory_desc, String here_is_desc, String long_desc){
		GameItem game_item = new GameItem(name, vocab, inventory_desc, here_is_desc, long_desc);
		return game_item;
	}
	
	public String getInventoryDesc(){
		return this.inventory_desc;
	}
	
	public String getHereIsDesc(){
		return this.here_is_desc;
	}
	
	public String getLongDesc(){
		return this.long_desc;
	}
}
