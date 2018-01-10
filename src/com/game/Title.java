package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Title implements Runnable {

	private Image img;
	private TankClient tc;

	private int x = -550;
	private int y;

	private int meun_x = 275;
	private int meun_y = 300;

	private int index = 1;
	private Thread backgroundThread;
	public Title(TankClient tc) {
		this.tc = tc;
		iniImage();

		backgroundThread = new Thread(new MediaPlayer(
				MediaPlayer.PLAY_STARTCARTOON));
		backgroundThread.start();// 播放背景音乐

		new Thread(this).start();
	}

	private void iniImage() {
		try {
			img = ImageIO.read(new File("images/maintitle.jpg"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
			g.drawImage(img, x, y, 650 + x, 550 + y, 0, 0, 650, 550, this.tc);
			g.setFont(new Font("宋体", Font.BOLD, 25));
			g.setColor(Color.white);
			Color c = g.getColor();

			if (index == 1) {
				g.setColor(Color.orange);
			}
			g.drawString("开始游戏", meun_x, meun_y);

			g.setColor(c);
			if (index == 2) {
				g.setColor(Color.orange);
			}
			g.drawString("继续游戏", meun_x, meun_y + 50);
			g.setColor(c);
			if (index == 3) {
				g.setColor(Color.orange);
			}
			g.drawString("设置游戏", meun_x, meun_y + 100);
			g.setColor(c);
			if (index == 4) {
				g.setColor(Color.orange);
			}
			g.drawString("游戏排行", meun_x, meun_y + 150);
			g.setColor(c);
			if (index == 5) {
				g.setColor(Color.red);
			}
			g.drawString("退出游戏", meun_x, meun_y + 200);
			
			//游戏说明
			g.setColor(Color.gray);
			g.setFont(new Font("隶书",Font.ITALIC,15));
			g.drawString("作者：吴伟      个人主页：http://wuwei.tk", x*2+170, 550);
	}

	public void run() {
		try {
			while (true) {
				if (x != 12) {
					x += 2;
				}
				Thread.sleep(25);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void select() {
		switch (index) {
		case 1:
//			Thread th = new Thread(new MediaPlayer(MediaPlayer.PLAY_ENTERGAME));
//			th.start();
			backgroundThread.stop();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				tc.title = null;
				tc.level = new Level(tc);
			}
			break;

		case 2:
			break;

		case 3:
			backgroundThread.stop(); // 关闭背景音乐
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				
				tc.gu = new GameUtil(tc);
				tc.title = null;
			}
			break;

		case 4:
			break;

		case 5:
			System.exit(0);
			break;// 退出啊
		}
	}

	public void keyPressed(KeyEvent e) {

		int keycode = e.getKeyCode();
		switch (keycode) {
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

		case 10:
			music(1);
			select();
			break;
		}
	}
	
	public void music(int index){
		switch(index){
		case 0: new Thread(new MediaPlayer(MediaPlayer.PLAY_MENU)).start();break;
		case 1: new Thread(new MediaPlayer(MediaPlayer.PLAY_MENU)).start();break;
		}
	}
}
