package pads.musicPlayer.exceptions;

/**
 * Base class for all Mp3Player exceptions
 * @author PADSOF professors
 */
public abstract class Mp3PlayerException extends Exception{
	protected static final long serialVersionUID = 1L;
	protected String fileName;

	public Mp3PlayerException (String fileName) {
		this.fileName = fileName;
	}
}
