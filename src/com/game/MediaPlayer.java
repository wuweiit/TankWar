package com.game;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;


/**
 * 音乐播放器，不解释网上找到的
 * 
 * 
 * */
public class MediaPlayer implements Runnable {
	/** 选择音效文件地址 */
	public final static String PLAY_MENU = "audio/menu.wav";
	
	/** 碰撞音效文件地址 */
	public final static String PLAY_HAT = "audio/hat.wav";
	
	/** 背景音乐文件地址 */
	public final static String PLAY_BACKGROUND = "audio/backgroundMusic.wav";

	/** BOSS音乐文件地址 */
	public final static String PLAY_BOSS = "audio/bossMusic.wav";

	/** 爆炸音乐文件地址 */
	public final static String PLAY_BOOM = "audio/boom.wav";

	/** 顺利通关音乐文件地址 */
	public final static String PLAY_ENDCARTOON = "audio/endCartoonMusic.wav";

	/** 进入游戏片头音乐文件地址 */
	public final static String PLAY_ENTERGAME = "audio/start.wav";

	/** 俘虏进入汽车音乐文件地址 */
	public final static String PLAY_ENTERJEEP = "audio/enterJeep.wav";

	/** 开火音乐文件地址 */
	public final static String PLAY_FIRE = "audio/fire.wav";

	/** 游戏未通关音乐文件地址 */
	public final static String PLAY_GAMEOVER = "audio/gameover.wav";

	/** 打招呼音乐文件地址 */
	public final static String PLAY_HI = "audio/hi.wav";

	/** 过关音乐文件地址 */
	public final static String PLAY_PASS = "audio/pass.wav";

	/** 直升飞机音乐文件地址 */
	public final static String PLAY_PLANE = "audio/planeMusic1.wav";

	/** 片头动画音乐文件地址 */
	public final static String PLAY_STARTCARTOON = "audio/startCartoonMusic.wav";

	/**死人音乐文件地址*/
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
		//这是缓冲
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