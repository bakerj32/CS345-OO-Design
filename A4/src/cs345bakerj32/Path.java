package cs345bakerj32;

public class Path {
	public String name;
	public VocabItem vocab;
	public Room room_begin;
	public Room room_end;
	
	public Path(String name, VocabItem vocab, Room room_begin, Room room_end){
		this.name = name;
		this.vocab = vocab;
		this.room_begin = room_begin;
		this.room_end = room_end;
	}
	public static Path makePath(String name, VocabItem vocab, Room room_begin, Room room_end){
		Path path = new Path(name, vocab, room_begin, room_end);
		room_begin.paths.add(path);
		return path;
	}
}
