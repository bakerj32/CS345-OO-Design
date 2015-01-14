package cs345bakerj32;
import java.util.*;

public class Room {
	public String name;
	public String description;
	public List<Path> paths;
	
	public Room(String name, String description){
		this.name = name;
		this.description = description;
		paths = new ArrayList<Path>();
	}
	
	public static Room makeRoom(String name, String description){
		Room room = new Room(name, description);
		return room;
	}
}
