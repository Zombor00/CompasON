package pads.musicPlayer.exceptions;

/**
 * Exception thrown when the Player cannot be created
 * @author PADSOF professors 
 */
public class Mp3PlayerCreationException extends Mp3PlayerException {
	public Mp3PlayerCreationException (String fileName) {
		super(fileName);
	}
	@Override
	public String toString() {
		return "Error creating Player with file: "+this.fileName;
	}
}
