package cs345bakerj32;

public class Player {
	public String name;
	public Room location;
	
	public Player(String name){
		this.name = name;
	}
	
	public Room getLocation(){
		return this.location;
	}
	
	public void apportTo(Room destination){
		this.location = destination;
		System.out.println(destination.description);
	}
	
	public void moveOnPath(Word word){
		// Iterate through paths in the current room.
		boolean success = false;
		for(Path p: this.location.paths){
			// Iterate through words on each path.
			for(Word w: p.vocab.words){
				//If words match, follow the path.
				if(w.value.startsWith(word.value)){
					apportTo(p.room_end);
					success = true;
				}
			}
		}
		if(!success){
			System.out.println("I can't move that way.");
		}
	}
	
	public void lookAround(){
		System.out.println(this.location.description);
	}
}
