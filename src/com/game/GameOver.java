package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class GameOver {

	private int x;
	private int y;
	TankClient tc;
	 
	public GameOver(TankClient tc){
		this.tc = tc;
		this.x = x;
		this.y = y;
		ini();
	}
		/**  成员变量初始化  */
		public void ini(){
			x = 200;
			y = Index.HEIGHT-27;
		}
	
	public void draw(Graphics g){
		g.setColor(Color.red);
		g.setFont(new Font("隶书",Font.BOLD,50));
		if(y <= 200){
			
			g.drawString("Game Over !", x, y);
			return;
		}
		g.drawString("Game Over !", x, y);
		y -= 3;
	}
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch(keycode){ 
		case 10 : 
			tc.title = new Title(tc);
			tc.gameover = null;
			tc.symbol.setLife(true);
			tc.tank = new Tank(240, 540, true, Direction.STOP, tc);
			break; 
		}
		
	}
}
