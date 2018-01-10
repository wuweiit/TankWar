package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Steel {

	private int x;
	private int y;
	private Image steelsImage;
	TankClient tc;
	
	public Steel(int x, int y, TankClient tc) {
		this.x = x;
		this.y = y;
		this.tc = tc;
		iniImage();
	}
	
	private void iniImage() {
		try {
			steelsImage = ImageIO.read(new File("images/steels.gif"));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "系统文件丢失\n原因：加载图片素材失败！");
		}
		
	}

	public void draw(Graphics g){
		g.drawImage(steelsImage, x, y, x+30, y+30, 0, 0, 60, 60, tc);
		
	}
	public Rectangle getRect(){
		return new Rectangle(x,y,30,30);
	}
}
