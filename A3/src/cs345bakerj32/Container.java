package cs345bakerj32;
import java.util.*;

public abstract class Container {
	public Set<GameItem> contents;
	
	public Container(){
		contents = new HashSet<GameItem>();
	}
	
	public void addItem(GameItem item){
		this.contents.add(item);
	}
	
	public void removeItem(GameItem item){
		this.contents.remove(item);
	}
	
	public boolean contains(GameItem item){
		return this.contents.contains(item);
	}
	
	public Set<GameItem> getContents(){
		return this.contents;
	}
}
