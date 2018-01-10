package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall {

	private int x;
	private int y;
	private int index = 4;//Ç½µÄÉúÃüÖµ
	private boolean life = true;

	private Image wallsImage;
	private Image wallImage;
	TankClient tc;
	public Wall(int x, int y, TankClient tc) {
		this.tc = tc;
		this.x = x;
		this.y = y;
		iniImage();
	}
	public void iniImage(){
		try {
			wallImage = ImageIO.read(new File("images/wall.gif"));
			wallsImage = ImageIO.read(new File("images/walls.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	public void draw(Graphics g){
		if(!life){
			tc.map.walls.remove(this);
			return;
		}
		
		switch( index ){
		case 1 : g.drawImage(wallsImage, x, y, x+30, y+30, 0, 0, 60, 60, tc); break;
		case 2 : g.drawImage(wallsImage, x, y, x+30, y+30, 0, 0, 60, 60, tc); break;
		case 3 : g.drawImage(wallsImage, x, y, x+30, y+30, 0, 0, 60, 60, tc); break;
		case 4 : g.drawImage(wallsImage, x, y, x+30, y+30, 0, 0, 60, 60, tc); break;
		}
	}
	/** Åö×²¼ì²â */
	public Rectangle getRect(){
		return new Rectangle(x,y,30,30);
	}
	
	
	public void setIndex(int index) { this.index = index; }
	public int getIndex() { return index; }
	public void setLife(boolean life) { this.life = life; }
	public boolean isLife() { return life; }
	
}
