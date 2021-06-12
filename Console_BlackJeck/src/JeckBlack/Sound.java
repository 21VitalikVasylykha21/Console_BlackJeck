/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

/**
 *
 * @author Vitalik
 */
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound implements AutoCloseable {
	private boolean released = false;
	private AudioInputStream stream = null;
	private Clip clip = null;
	private FloatControl volumeControl = null;
	private boolean playing = false;
	
        //Конструктор
	 public void Sound(File f) {
		try {
			stream = AudioSystem.getAudioInputStream(f);
			clip = AudioSystem.getClip();
			clip.open(stream);
			volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			released = true;
		} catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
			exc.printStackTrace();
			released = false;
			close();
		}
	}

	
	// перевірка чи грає звук в даний момент
	public boolean isPlaying() {
		return playing;
	}

        //Запуск відтворення  
	public void play(boolean breakOld) {
		if (released) {
			if (breakOld) {
				clip.stop();
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			} else if (!isPlaying()) {
				clip.setFramePosition(0);
				clip.start();
				playing = true;
			}
		}
	}
	
	// Зупиняє відтворення гучності
	public void stop() {
			clip.stop();
                       
	}
        
	public void close() {
		if (clip != null)
			clip.close();
		
		if (stream != null)
			try {
				stream.close();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
	}

	// Встановлення гучності приймає дробове число від 0 до 1
	public void setVolume(float x) {
		if (x<0) x = 0;
		if (x>1) x = 1;
		float min = volumeControl.getMinimum();
		float max = volumeControl.getMaximum();
		volumeControl.setValue((max-min)*x+min);
	}
	
	//Повертає гучність відтворення музики
	public float getVolume() {
		float v = volumeControl.getValue();
		float min = volumeControl.getMinimum();
		float max = volumeControl.getMaximum();
		return (v-min)/(max-min);
	}

	public void join() {
		if (!released) return;
		synchronized(clip) {
			try {
				while (playing)
					clip.wait();
			} catch (InterruptedException exc) {}
		}
	}
	
}