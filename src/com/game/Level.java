package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Level {

	private int x = 0;
	private int y = 0;
	private int menu_x = 140;
	private int menu_y = 170;
	private int index = 0;

	TankClient tc;
	private File f;
	private int indexMax;
	private boolean life = true;
	private Image img;
	public Level(TankClient tc) {
		this.tc = tc;
		f = new File("map");
		indexMax = f.listFiles().length;
		iniImage();
	}
	private void iniImage(){
		try {
			img = ImageIO.read(new File("images/levels.jpg"));
		} catch (IOException e) {e.printStackTrace();}
	}
	
	public void draw(Graphics g){
		if(!life){
			tc.level = null;
			return;
		}
		g.drawImage(img, x, y, 650 + x, 550 + y, 0, 0, 650, 550, tc);
		g.setColor(Color.gray);
		for(int i=0; i<indexMax; i++){
			if(index == i){
				g.setColor(Color.orange);
				g.drawString("Level " + (i+1) + ":¡ô ", menu_x, menu_y+i*20);
				continue;
			}
				g.setColor(Color.gray);
				g.drawString("Level " + (i+1), menu_x, menu_y+i*20);
		}
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode){
		case 38 :
			if (index == 0) {
				return;
			} else {
				music(0);
				index -= 1;
			}
			break;
		case 40 : 
			if (index == indexMax-1) {
				return;
			} else {
				music(0);
				index += 1;
			}
			break;
		
		case 10:
			music(0);
			select();
			break;
		}
		
	}
	void select(){
		music(2);
		this.life = false;
		switch(index){
		case 0 : tc.map = new Map(tc,0); break;
		case 1 : tc.map = new Map(tc,1); break;
		case 2 : tc.map = new Map(tc,2); break;
		case 3 : tc.map = new Map(tc,3); break;
		case 4 : tc.map = new Map(tc,4); break;
		case 5 : tc.map = new Map(tc,5); break;
		case 6 : tc.map = new Map(tc,6); break;
		case 7 : tc.map = new Map(tc,7); break;
		case 8 : tc.map = new Map(tc,8); break;
		}
	}
	public void music(int index){
		switch(index){
		case 0: new Thread(new MediaPlayer(MediaPlayer.PLAY_MENU)).start();break;
		case 1: new Thread(new MediaPlayer(MediaPlayer.PLAY_MENU)).start();break;
		case 2: new Thread(new MediaPlayer(MediaPlayer.PLAY_ENTERGAME)).start();break;
		}
	}
}
