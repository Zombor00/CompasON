package pads.musicPlayer.exceptions;

/**
 * Exception thrown when the file is not a valid mp3
 * @author PADSOF professors 
 */
public class Mp3InvalidFileException extends Mp3PlayerException {
	public Mp3InvalidFileException (String fileName) {
		super(fileName);
	}
	@Override
	public String toString() {
		return "Invalid mp3 file: "+this.fileName;
	}
}
