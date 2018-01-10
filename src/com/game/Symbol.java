package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * @author 吴伟
 * 老鬼
 * */
public class Symbol {

	
	private int x;
	private int y;
	private Image symbolImage;
	private Image destoryImage;
	private boolean life = true;
	TankClient tc;
	Explode explode = new Explode(x,y,tc);
	public Symbol(int x, int y, TankClient tc ){
		this.tc = tc;
		this.x = x;
		this.y = y;
		iniImage();//初始化图片
	}
	
	private void iniImage(){
		try {
			symbolImage  = ImageIO.read(new File("images/symbol.gif"));
			destoryImage = ImageIO.read(new File("images/destory.gif"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void draw(Graphics g){
		if(life){
			g.drawImage(symbolImage, x, y, x + 30, y + 30, 0, 0, 60, 60, tc);
		}else{
			g.drawImage(destoryImage, x, y, x + 30, y + 30, 0, 0, 60, 60, tc);
		}
	}
	
	/** 碰撞检测 */
	public Rectangle getRect(){
		return new Rectangle(x, y ,30,30);
	}

	/** 对外开放的私有属性  */
	public boolean isLife() { return life; }
	public void setLife(boolean life) { this.life = life; }
	
}
