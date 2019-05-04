package pads.musicPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.*;
import pads.musicPlayer.exceptions.Mp3BusyPlayerException;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import pads.musicPlayer.exceptions.Mp3PlayerCreationException;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class Mp3Player extends PlaybackListener {
	
	private Player player;
	private Thread playerThread;
	private FileInputStream fis;
	private List<String> songs = new ArrayList<>();
	private int current = 0;
	private MusicPlayer innerPlayer  = new MusicPlayer();

	private class MusicPlayer implements Runnable {
		
		private AtomicBoolean keep = new AtomicBoolean(true);
		
		@Override
		public void run() {
			if (player!=null && playerThread.getState().equals(State.RUNNABLE))
			{
				this.keep.set(true);
				while (current < songs.size() && keep.get()) {
					try
					{
						createPlayer();
						player.play();				
					}
					catch (JavaLayerException ex)
					{
						System.err.println("Problem playing audio: "+ex);
					} catch (FileNotFoundException | Mp3PlayerCreationException e) {						
						e.printStackTrace();
					} 
					current++;
				}
				current = 0;
			}				
		}	
		
		public void notifyFinish() {
			this.keep.set(false);
		}
	}
	
	/**
	 * Constructor for the Mp3Player, which expects a zero or more file names with valid mp3.
	 * @param mp3s : Zero or more strings with file names of mp3s to be played
	 * @throws Mp3PlayerException : Thrown either when the file is not found, or is not valid mp3, or when the Player could not be created
	 * @throws FileNotFoundException : Thrown when some file is not found 
	 */
	public Mp3Player(String ... mp3s) throws FileNotFoundException, Mp3PlayerException {
		this.songs.addAll(Arrays.asList(mp3s));
		if (this.songs.size()>0) {			
			this.add(mp3s);
			this.createPlayer();
			playerThread = new Thread(this.innerPlayer, "Audio player thread");
		}
	}

	private void createPlayer() throws FileNotFoundException, Mp3PlayerCreationException {
		fis = new FileInputStream(this.songs.get(current));
		try {
			player = new Player (fis);
		} catch (JavaLayerException e) {
			throw new Mp3PlayerCreationException(this.songs.get(current));
		}
	}
		
	/**
	 * The method to start playing the mp3 files. This method starts a thread, which can be stopped using stop.
	 * The method plays all mp3s in the list starting from the beginning. If there is no file to be played, it does nothing.
	 * @throws Mp3PlayerException when the Player is already playing
	 */
	public void play() throws Mp3PlayerException {
		if (this.playerThread!=null && !this.playerThread.getState().equals(State.NEW)) {
			throw new Mp3BusyPlayerException(current < this.songs.size() ? this.songs.get(current) : 
											 ( this.songs.size() > 0 ? this.songs.get(0) : "no file"));
		}
		if (current > this.songs.size()) return;
		if (this.fis == null) {
			try {
				this.fis = new FileInputStream(this.songs.get(current));
			} catch (FileNotFoundException e) {
				throw new Mp3InvalidFileException(this.songs.get(current));
			}
		}
		if (player==null) {
			try {
				player = new Player(fis);
			} catch (JavaLayerException e) {
				throw new Mp3PlayerCreationException(this.fis.toString());
			}			
		}
		if (playerThread==null) playerThread = new Thread(this.innerPlayer, "Audio player thread");
		playerThread.start();		
	}
	
	/**
	 * Method that stops playing the current song, if some is being played.
	 */
	public void stop() {
		if (player != null) {
			player.close();
			player = null;
			this.innerPlayer.notifyFinish();
			this.playerThread.interrupt();
			this.playerThread = null;
			try {
				this.fis.close();
				this.fis = null;
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * Adds a list of files to the queue of mp3s to be played 
	 * @param songs the songs to be added
	 * @throws Mp3InvalidFileException when some file does not exist or is invalid
	 */
	public void add(String... songs) throws Mp3InvalidFileException {
		this.songs.addAll(Arrays.asList(songs));
		for (String mp3 : this.songs)
			if (! isValidMp3File(mp3)) throw new Mp3InvalidFileException(mp3);
	}
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	// Utility methods
	
	/**
	 * Returns whether the file represented by mp3 is a valid mp3 file
	 * @param mp3 the file to be checked
	 * @return true if the file exists and it is a valid mp3 file, false otherwise
	 */
	public static boolean isValidMp3File(String mp3) {
		try {
			FileInputStream fis = new FileInputStream(mp3);
			Bitstream 	bitstream = new  Bitstream(fis);		
			Header 		h= bitstream.readFrame();		
			if (h == null) 
				return false;
		} catch (FileNotFoundException | BitstreamException e) {
			return false;
		}
	    return true;
	}
	
	/**
	 * Returns the duration of the current mp3 file in seconds
	 * @param file a string with the mp3 file 
	 * @return The duration id the current mp3 file in seconds
	 * @throws FileNotFoundException when the file does not exist
	 */
	public static double getDuration(String file) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(file);
		Bitstream 	bitstream = new  Bitstream(fis);
		
		Header 		h= null;		
		long 		tn = 0;
	    try {
	        h = bitstream.readFrame();	        
	        tn = fis.getChannel().size();
	    } 
	    catch (BitstreamException ex) {
	        System.err.println("Error in MP3 Header");
	    }
	    catch (IOException ex) {
	        System.err.println("Error reading file");
	    }
	    return h.total_ms((int) tn)/1000;
	}
	
	public int getQueueSize() {
		return this.songs.size();
	}
	

}
