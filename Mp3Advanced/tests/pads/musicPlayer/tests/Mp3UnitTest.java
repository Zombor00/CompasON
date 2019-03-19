package pads.musicPlayer.tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import pads.musicPlayer.Mp3Player;
import pads.musicPlayer.exceptions.Mp3BusyPlayerException;
import pads.musicPlayer.exceptions.Mp3InvalidFileException;
import pads.musicPlayer.exceptions.Mp3PlayerException;

public class Mp3UnitTest {

/*	@Test(expected=Mp3InvalidFileException.class)
	public void testUnexistentFile() throws FileNotFoundException, Mp3PlayerException {
		new Mp3Player("no-file.mp3");
	}

	@Test(expected=Mp3PlayerException.class)
	public void testInvalidMp3File() throws FileNotFoundException, Mp3PlayerException {
		new Mp3Player("invalid.mp3");
	}
	
	@Test
	public void testValidMp3File() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		Mp3Player player = new Mp3Player("hive.mp3");
		player.play();
		Thread.sleep(3000);
		player.stop();
	}
	
	@Test
	public void testDurationMp3File() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		assertEquals(24.39836, Mp3Player.getDuration("chicle3.mp3"), 0.0001);
	}
	
	@Test
	public void testStopBeforePlay() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		Mp3Player player = new Mp3Player("hive.mp3");
		player.stop();
		player.play();
		Thread.sleep(3000);
		player.stop();
	}*/
	
	@Test
	public void test2StopBeforePlay() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		Mp3Player player = new Mp3Player("hive.mp3");
		player.stop();
		player.stop();
		player.play();
		Thread.sleep(3000);
		player.stop();
		player.play();
		Thread.sleep(3000);
		player.stop();
	}
	
/*	@Test(expected=Mp3BusyPlayerException.class)
	public void testPlayAfterPlay() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		Mp3Player player = new Mp3Player("hive.mp3");
		player.play();
		player.play();
		player.stop();
	}	
	
	@Test
	public void testPlayList() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		Mp3Player player = new Mp3Player();
		player.add("np.mp3", "hive.mp3", "chicle3.mp3");
		player.play();
		Thread.sleep(13000);
		player.stop();
		System.out.println("Stopped, starting");
		player.play();
		Thread.sleep(3000);
		player.stop();
	}	
	
	@Test
	public void testPlayList2() throws FileNotFoundException, Mp3PlayerException, InterruptedException {
		Mp3Player player = new Mp3Player();
		player.add("np.mp3", "hive.mp3");
		player.play();
		Thread.sleep(13000);
		player.stop();
		System.out.println("Stopped, starting");
		player.play();
		Thread.sleep(3000);
		player.stop();
	}*/
}
