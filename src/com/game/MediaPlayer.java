package com.game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


/**
 * ���ֲ������������������ҵ���
 * 
 * 
 * */
public class MediaPlayer implements Runnable {
	/** ѡ����Ч�ļ���ַ */
	public final static String PLAY_MENU = "audio/menu.wav";
	
	/** ��ײ��Ч�ļ���ַ */
	public final static String PLAY_HAT = "audio/hat.wav";
	
	/** ���������ļ���ַ */
	public final static String PLAY_BACKGROUND = "audio/backgroundMusic.wav";

	/** BOSS�����ļ���ַ */
	public final static String PLAY_BOSS = "audio/bossMusic.wav";

	/** ��ը�����ļ���ַ */
	public final static String PLAY_BOOM = "audio/boom.wav";

	/** ˳��ͨ�������ļ���ַ */
	public final static String PLAY_ENDCARTOON = "audio/endCartoonMusic.wav";

	/** ������ϷƬͷ�����ļ���ַ */
	public final static String PLAY_ENTERGAME = "audio/start.wav";

	/** ��²�������������ļ���ַ */
	public final static String PLAY_ENTERJEEP = "audio/enterJeep.wav";

	/** ���������ļ���ַ */
	public final static String PLAY_FIRE = "audio/fire.wav";

	/** ��Ϸδͨ�������ļ���ַ */
	public final static String PLAY_GAMEOVER = "audio/gameover.wav";

	/** ���к������ļ���ַ */
	public final static String PLAY_HI = "audio/hi.wav";

	/** ���������ļ���ַ */
	public final static String PLAY_PASS = "audio/pass.wav";

	/** ֱ���ɻ������ļ���ַ */
	public final static String PLAY_PLANE = "audio/planeMusic1.wav";

	/** Ƭͷ���������ļ���ַ */
	public final static String PLAY_STARTCARTOON = "audio/startCartoonMusic.wav";

	/**���������ļ���ַ*/
	public final static String PLAY_DIE = "audio/Die_High.wav";
	
	
	
	private String url;
	
	public MediaPlayer(String url){
		this.url = url;
	}
	public void run() {
		File soundFile = new File(url);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		//���ǻ���
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

		
	}
}