package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameUtil {

	private int x;
	private int y;
	private int meun_x = 190;
	private int meun_y = 250;
	
	private int index = 1;
	private Image img;
	
	TankClient tc;
	 
	public GameUtil(TankClient tc) {
		this.tc = tc;
		
		iniImage();
	}
	private void iniImage(){
		try {
			img = ImageIO.read(new File("images/walls.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g){
		for(int i=0;i<22; i++){
			for(int j = 0;j<19; j++){
				if(i != 5 && j != 6)
				g.drawImage(img,i*30, j*30, (i+1)*30, (j+1)*30, 0, 0, 60, 60, tc);
			}
		}
		
		g.setFont(new Font("隶书", Font.BOLD, 25));
		g.setColor(Color.white);
		Color c = g.getColor();

		if (index == 1) {
			g.setColor(Color.orange);
		}
		g.drawString("游戏声音：", meun_x, meun_y);

		g.setColor(c);
		if (index == 2) {
			g.setColor(Color.orange);
		}
		g.drawString("游戏速度：", meun_x, meun_y + 60);
		g.setColor(c);
		if (index == 3) {
			g.setColor(Color.orange);
		}
		g.drawString("角色颜色：", meun_x, meun_y + 120);
		g.setColor(c);
		if (index == 4) {
			g.setColor(Color.orange);
		}
		g.drawString("游戏帮助", meun_x, meun_y + 180);
		g.setColor(c);
		if (index == 5) {
			g.setColor(Color.orange);
		}
		g.drawString("返回主界面", meun_x, meun_y + 280);
	}
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode){
		case 38:
			if (index == 1) {
				return;
			} else {
				music(0);
				index -= 1;
			}
			break;// U
		case 40:
			if (index == 5) {
				return;
			} else {
				music(0);
				index += 1;
			}
			break;// D
		case 37 : 
			switch(index){
			case 1: 
				break;
			};
			break;
		case 39 : break;

		case 10:
			music(1);
			select();
			break;
		}
		
	}
	private void select() {
		switch(index){
		case 1: break;
		case 2: break;
		case 3: break;
		case 4: tc.help = new Help(tc); tc.gu = null; break;
		case 5: tc.title = new Title(tc); tc.gu = null; break;
		}
		
	}
	public void music(int index){
		switch(index){
		case 0: new Thread(new MediaPlayer(MediaPlayer.PLAY_MENU)).start();
		case 1: new Thread(new MediaPlayer(MediaPlayer.PLAY_MENU)).start();
		}
	}
}
