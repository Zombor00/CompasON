package pads.musicPlayer.exceptions;

/**
 * Exception signalling the player is busy playing. This is most likely caused by calling play() twice (before calling stop())  
 * @author PADSOF professors 
 */
public class Mp3BusyPlayerException extends Mp3PlayerException {
	public Mp3BusyPlayerException (String fileName) {
		super(fileName);
	}
	@Override
	public String toString() {
		return "The Player is already playing file: "+this.fileName;
	}
}
