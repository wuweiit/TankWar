package com.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Tank {

	public static final int SPEED = 3;
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;

	private Image tanks;
	private Image tankImage;
	private Image tankImage_1;
	private Image tankImage_2;
	private Image tankImage_3;
	private Image tankImage_4;
	private Image tankImage_5;
	private Image tankImage_6;
	private int tankIndex; //坦克的步数
	private int x;
	private int y;
	private int oldX;
	private int oldY;
	private int imgx = 60;
	private int imgy = 0;
	int abc = 0;
	int[] img = { 0, 60, 120, 180, 240, 300, 360, 420, 480, 0, 60, 120, 180,
			240, 300, 360, 420, 480 };
	private boolean life = true;
	private boolean good = false;
	private Direction dir = Direction.STOP;
	private Direction ptdir = Direction.U;
	private boolean bL = false, bU = false, bR = false, bD = false;
	TankClient tc;
	Blink blink;
	private static Random r = new Random();
	private int step = r.nextInt(12) + 3;// 自动行进步数
	private int tankVersionNum = r.nextInt(6);// 随机的坦克型号

	public Tank(int x, int y, boolean good, Direction dir, TankClient tc) {
		this.dir = dir;
		this.good = good;
		this.tc = tc;
		this.x = x;
		this.y = y;
		iniImage();// 初始化图片
		iniVersion();// 初始化坦克版本

	}

	private void iniImage() {
		blink = new Blink(this.x, this.y, this, tc);
		try {
			tankImage = ImageIO.read(new File("images/Tanks.png"));
			tankImage_1 = ImageIO.read(new File("images/Tanks_1.png"));
			tankImage_2 = ImageIO.read(new File("images/Tanks_2.png"));
			tankImage_3 = ImageIO.read(new File("images/Tanks_3.png"));
			tankImage_4 = ImageIO.read(new File("images/Tanks_4.png"));
			tankImage_5 = ImageIO.read(new File("images/Tanks_5.png"));
			tankImage_6 = ImageIO.read(new File("images/Tanks_6.png"));
			MediaTracker MT = new MediaTracker(this.tc);

			MT.addImage(tankImage_1, 0);
			MT.addImage(tankImage_2, 1);
			MT.addImage(tankImage_3, 2);
			MT.addImage(tankImage_4, 3);
			MT.addImage(tankImage_5, 4);
			MT.addImage(tankImage_6, 5);
			MT.addImage(tankImage, 6);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "系统文件丢失\n原因：加载图片素材失败！");
		}
	}

	private void iniVersion() {
		switch (tankVersionNum) {
		case 0 : tanks = tankImage_1; tankIndex = 10; break;// 银色坦克
		case 1 : tanks = tankImage_2; tankIndex = 20; break;// 银色坦克
		case 2 : tanks = tankImage_3; tankIndex = 25; break;//
		case 3 : tanks = tankImage_4; tankIndex = 22; break;//
		case 4 : tanks = tankImage_5; tankIndex = 5; break;//
		case 5 : tanks = tankImage_6; tankIndex = 15; break;//
		}
	}

	public void draw(Graphics g){
		
		if(!life){
			if(!good){
				tc.tanksCount--;
				tc.tanks.remove(this);
			}
			return;
		}
		if(blink.isLife()){
			blink.draw(g);
			return;
		}
			if(good){
				g.drawImage(tankImage, x, y, x+Tank.WIDTH, y+Tank.WIDTH, imgx, imgy, imgx+60, imgy+60, tc);
			}else{
				g.drawImage(tanks, x, y, x+Tank.WIDTH, y+Tank.WIDTH, imgx, imgy, imgx+60, imgy+60, tc);
			}
			 move();
		
			

	}

	private void fire() {
		
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;
		int y = this.y + Tank.HEIGHT / 2 - Missile.HEIGHT / 2;
		tc.missiles.add(new Missile(x, y, good, ptdir, tc));
	}

	private void stay() {
		this.x = oldX;
		this.y = oldY;
	}

	private void move() {
		this.oldX = x;
		this.oldY = y;
		switch (dir) {
		case L:
			imgx = 0;
			x -= Tank.SPEED;
			break;
		case U:
			imgx = 60;
			y -= Tank.SPEED;
			break;
		case R:
			imgx = 180;
			x += Tank.SPEED;
			break;
		case D:
			imgx = 120;
			y += Tank.SPEED;
			break;
		}
		if (this.dir != Direction.STOP) {
			this.ptdir = this.dir;
		}
		/** 撞墙  */
		if (x < 0) x = 0;
		if (y < 0) y = 0;
		if (x + Tank.WIDTH > TankClient.WIDTH)
			x = TankClient.WIDTH - Tank.WIDTH;
		if (y + Tank.HEIGHT > TankClient.HEIGHT)
			y = TankClient.HEIGHT - Tank.HEIGHT;

		if (!good) {
			Direction[] dirs = Direction.values();
			if (step == 0) {
				step = r.nextInt(12) + 3;
				int rn = r.nextInt(dirs.length);
				dir = dirs[rn];
			}
			step--;
			
			if (r.nextInt(40) > 38) {
				this.fire();
			}
		}
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, Tank.WIDTH, Tank.HEIGHT);
	}

	/**
	 * 遇到墙
	 * */
	public boolean hatWall(Wall w) {
		if (this.life && this.getRect().intersects(w.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}

	public boolean hatWalls(Map map) {
		for (int i = 0; i < map.walls.size(); i++) {
			Wall wall = map.walls.get(i);
			if (hatWall(wall)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 遇到河
	 * */
	public boolean hatWater(Water w) {
		if (this.life && this.getRect().intersects(w.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}

	public boolean hatWaters(Map map) {
		for (int i = 0; i < map.waters.size(); i++) {
			Water water = map.waters.get(i);

			if (hatWater(water)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 遇到钢砖
	 * */
	public boolean hatSteel(Steel s) {
		if (this.life && this.getRect().intersects(s.getRect())) {
			this.stay();
			return true;
		}
		return false;
	}

	public boolean hatSteels(Map map) {
		for (int i = 0; i < map.steels.size(); i++) {
			Steel steel = map.steels.get(i);
			if (hatSteel(steel)) {
				return true;
			}
		}
		return false;
	}

	public boolean hatTanks(List<Tank> tanks) {
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.life && t.life
						&& this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();
					return true;
				}
			}
		}
		return true;
	}

	private void locationDirection() {
		if (bL && !bU && !bR && !bD)
			dir = Direction.L;
		if (!bL && bU && !bR && !bD)
			dir = Direction.U;
		if (!bL && !bU && bR && !bD)
			dir = Direction.R;
		if (!bL && !bU && !bR && bD)
			dir = Direction.D;
		if (!bL && !bU && !bR && !bD)
			dir = Direction.STOP;
	}

	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch (keycode) {
		case 37:
			bL = true;
			break;// 左
		case 38:
			bU = true;
			break;// 上
		case 39:
			bR = true;
			break;// 右
		case 40:
			bD = true;
			break;// 下
		}
		locationDirection();
	}

	public void keyReleased(KeyEvent e) {
		int keycode = e.getKeyCode();
		switch (keycode) {
		case 37:
			bL = false;
			break;
		case 38:
			bU = false;
			break;
		case 39:
			bR = false;
			break;
		case 40:
			bD = false;
			break;
		case 32:
			fire();
			if(life)new Thread(new MediaPlayer(MediaPlayer.PLAY_FIRE)).start();
			break;// 开火
		}
		locationDirection();
	}

	/** 对外开放的私有属性 */
	public boolean isLife() { return life; }
	public void setLife(boolean life) { this.life = life; }
	public boolean isGood() { return good; }

}
