package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @author ÎâÎ°
 * ±¬Õ¨Ð§¹û
 * */
public class Explode {

	private int x;
	private int y;
	int[] imgx = {0, 136, 272, 408, 544, 680, 816, 952, 1088};
	int step = 0;
	private boolean life = true;
	private Image boomImage;
	TankClient tc;
	
	public Explode(int x, int y, TankClient tc) {
		this.tc = tc;
		this.x = x - 20;
		this.y = y - 20;
		iniImage();
		
	}
	public void iniImage(){
		try {
			boomImage = ImageIO.read(new File("images/boom.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	/**
	 * »æÖÆ±¬Õ¨Ð§¹û
	 * */
	public void draw(Graphics g){
		if(!life)return;
		if(step == 0){
			new Thread(new MediaPlayer(MediaPlayer.PLAY_BOOM)).start();//±¬Õ¨ÉùÒô
		}
		if(step == imgx.length-1){
			life = false;
			step = 0;
			return;
		}
		g.drawImage(boomImage, x, y, x+68, y+53,imgx[step], 0, imgx[step+1], 107, tc);
		step++;
	}
	public boolean isLife() { return life; }
	public void setLife(boolean life) {	this.life = life; }
	
}
