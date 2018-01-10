package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * 帮助类
 * */
public class Help {

	private int x;
	private int y;
	private Image helpImage;
	public TankClient tc;

	public Help(TankClient tc){
		this.tc = tc;
		ini();
		iniImage();
	}
	/**  成员变量初始化  */
	public void ini(){
		y = Index.HEIGHT-27;
	}
	/** 图片初始化 */
	private void iniImage(){
		try {
			helpImage =ImageIO.read(new File("images/help.png"));//读取帮助图片
		} catch (IOException e) { e.printStackTrace(); }
	}
	public void draw(Graphics g){
		if(y <= 0){
			g.drawImage(helpImage, x, y, x+Index.WIDTH-6, y+Index.HEIGHT-26, 0, 0, 662, 572, tc);
			return;
		}
		g.drawImage(helpImage, x, y, x+Index.WIDTH-6, y+Index.HEIGHT-26, 0, 0, 662, 572, tc);
		y -= 3;
	}
	
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode){ case 10 : tc.title = new Title(tc); tc.help = null; break; }
	}
	
}
