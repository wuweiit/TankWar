package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 初始化坦克闪光效果
 * */
public class Blink {

	private int x;
	private int y;
	private int step     =    0;
	private boolean life = true;
	
	
	int[] imgx = {
			0, 60, 120, 180, 240, 300, 360, 420, 480,
			480, 420 ,360, 300, 240, 180, 120, 60, 0,
			0, 60, 120, 180, 240, 300, 360, 420, 480,
			480, 420 ,360, 300, 240, 180, 120, 60, 0,
			};

	private Image startImage;
	TankClient tc;
	Tank t ;
	
	public Blink(int x, int y,Tank t, TankClient tc){
		this.t = t;
		this.tc = tc;
		this.x = x;
		this.y = y;
		iniImage();//初始化闪光图片
	}
	
	private void iniImage(){
		try {
			startImage = ImageIO.read(new File("images/start.png"));
		} catch (IOException e) {e.printStackTrace();}
	}
	public void draw(Graphics g){
		if(!life)return;
		if(step == imgx.length-1){//如果步长等于数组的长度（也就是说闪光图片显示完毕）
			life = false;
			step = 0;
			return;
		}
		g.drawImage(startImage, x, y, x+30, y+30,imgx[step], 0,imgx[step+1], 60, tc);
		step++;
	}

	public boolean isLife() { return life; }
	public void setLife(boolean life) { this.life = life; }
	
}
