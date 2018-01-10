package com.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

public class Missile {
	
	private static final int SPEED = 5;
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	
	private int x;
	private int y;
	private boolean life = true;
	private boolean good;
	private Direction dir;
	TankClient tc;
	public Missile(int x, int y,boolean good,Direction dir,TankClient tc){
		this.good = good;
		this.tc =tc;
		this.dir = dir;
		this.x = x;
		this.y = y;
//		iniImage();
	}
	
	public void iniImage(){
		
	} 
	
	public void draw(Graphics g){
		if(!life){
			tc.missiles.remove(this);
		}
		g.setColor(Color.white);
		g.fillOval(x, y, 5, 5);
		move();
	}
	
	
	private void move(){

		switch(dir){
		case L: x -= Missile.SPEED; break;
		case U: y -= Missile.SPEED; break;
		case R: x += Missile.SPEED; break;
		case D: y += Missile.SPEED; break;
		}
		if( x < 0 || y < 0 || x > TankClient.WIDTH || y > TankClient.HEIGHT ){
			if(good)new Thread(new MediaPlayer(MediaPlayer.PLAY_HAT)).start();
			tc.missiles.remove(this);
			
		}
	}

	/**
	 * Óöµ½Ì¹¿Ë
	 * */
	public boolean hatTank(Tank t){
		if(this.life && this.getRect().intersects(t.getRect()) && t.isLife() && this.good != t.isGood()){
			this.life = false;
			t.setLife(false);
			Explode e = new Explode(x,y,tc);
			tc.explodes.add(e);
			return true;
		}
		return false;
	}
	public boolean hatTanks(List<Tank> tanks){
		for(int i=0;i<tanks.size(); i++){
			if(hatTank(tanks.get(i))){
				return true;
			}
		}
		return false;
	}
	/**
	 * Óöµ½Ç½
	 * */
	public boolean hatWall(Wall w){
		if( this.life && this.getRect().intersects(w.getRect()) ){
			this.life = false;
			return true;
		}
		return false;
	}
	public boolean hatWalls(Map map){
		for( int i = 0; i < map.walls.size(); i++ ){
			Wall wall = map.walls.get(i);
			if( hatWall(wall) ){
				if(wall.getIndex() == 1){
					wall.setLife(false);
				}
				wall.setIndex(wall.getIndex()-1);
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Óöµ½¸Ö×©
	 * */
	public boolean hatSteel(Steel s){
		if( this.life && this.getRect().intersects(s.getRect()) ){
			if(good)new Thread(new MediaPlayer(MediaPlayer.PLAY_HAT)).start();
			this.life = false;
			return true;
		}
		return false;
	}
	public boolean hatSteels(Map map){
		for( int i = 0; i < map.steels.size(); i++ ){
			Steel steel = map.steels.get(i);
			if(hatSteel(steel)){
				return true;
			}
		}
		return false;
	}
	/** Óöµ½¹í¹í */
	public boolean hatSymbol(Symbol symbol){
		if(this.getRect().intersects(symbol.getRect())){
			this.life = false;
			if(symbol.isLife()){
				Explode e = new Explode(x,y,tc);
				tc.explodes.add(e);
				symbol.setLife(false);
			}
			return true;
		}
		return false;
		
		
	}
	
	/**
	 * Åö×²¼ì²â
	 * */
	public Rectangle getRect(){
		return new Rectangle(x,y,Missile.WIDTH,Missile.HEIGHT);
	}
	public boolean isGood(){
		return this.good;
	}
	
}
