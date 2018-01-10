package com.game;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Map {
	
	
	private int[][] map ;
	private int level;
	private int wallCount;
	private int waterCount;
	private int grassCount;
	private int steelCount;
	
	List<Wall> walls   = new ArrayList<Wall>(); 
	List<Water> waters = new ArrayList<Water>(); 
	List<Grass> grasss = new ArrayList<Grass>();
	List<Steel> steels = new ArrayList<Steel>();
	Wall wall;
	Water water;
	Grass grass;
	Steel steel;
	TankClient tc;
	MapFileReader mfr;
	
	public Map(TankClient tc,int level){
		this.tc = tc;
		switch(level){
		case 0 : mfr = new MapFileReader(0); break;
		case 1 : mfr = new MapFileReader(1); break;
		case 2 : mfr = new MapFileReader(2); break;
		case 3 : mfr = new MapFileReader(3); break;
		case 4 : mfr = new MapFileReader(4); break;
		case 5 : mfr = new MapFileReader(5); break;
		case 6 : mfr = new MapFileReader(6); break;
		case 7 : mfr = new MapFileReader(7); break;
		}
		map = mfr.loadMap();
		
		this.wallCount  = mfr.wallCount;
		this.waterCount = mfr.waterCount;
		this.grassCount = mfr.grassCount;
		this.steelCount = mfr.steelCount;
		
		
		for(int i=0;i<map.length;i++){
			for(int j=0; j<map[0].length; j++){
				if( wallCount == 0 && waterCount == 0 && grassCount == 0 && steelCount == 0 ){
					wallCount  = mfr.wallCount;
					waterCount = mfr.waterCount;
					grassCount = mfr.grassCount;
					steelCount = mfr.steelCount;
					return;
				}
				switch(map[i][j]){
				case 0 : walls.add(new Wall(j*30,i*30,tc));    wallCount--; break;
				case 1 : waters.add(new Water(j*30,i*30,tc)); waterCount--; break;
				case 2 : grasss.add(new Grass(j*30,i*30,tc)); grassCount--; break;
				case 3 : steels.add(new Steel(j*30,i*30,tc)); steelCount--; break;
				}
			}
		}
		
	}
	
	public void draw(Graphics g){
		
		for( int j = 0; j < waters.size(); j++){
			water = waters.get(j);
			water.draw(g);
		}
		
		for( int i = 0; i < walls.size(); i++){
			wall = walls.get(i);
			wall.draw(g);
		}
		
		for( int k = 0; k < grasss.size(); k++){
			grass = grasss.get(k);
			grass.draw(g);
		}
		for( int n = 0; n < steels.size(); n++){
			steel = steels.get(n);
			steel.draw(g);
		}
	}
	
	
	public void setLevel(int level){
		this.level = level;
	}
}
