package com.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
/**
 * 读取地图配置文件的类
 * 
 * */
public class MapFileReader {
	
	File mapFile;
	String url[] = {
			"map/map1.ini",
			"map/map2.ini",
			"map/map3.ini",
			"map/map4.ini",
			"map/map5.ini",
			"map/map6.ini",
			"map/map7.ini",
			"map/map8.ini"
	};
	InputStream is;
	InputStreamReader isr;
	BufferedReader br;
	
	int wallCount;//墙的个数
	int waterCount;//水的个数
	int grassCount;//草坪
	int steelCount;//草坪

	
	public MapFileReader(int num) {

		try {
			mapFile = new File(url[num]);
			InputStream is = new FileInputStream(mapFile);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"系统文件丢失\n原因：地图文件未读取");
		}
	}
	public int[][] loadMap() {
		String str = "";
		String[] mapRow = new String[22];
		int[][] map = new int[20][22];
		try {
			int step = 0;
			while((str = br.readLine()) != null){
				mapRow = str.split(" ");				
				for( int j = 0; j < mapRow.length; j++){
					map[step][j] = Integer.parseInt(mapRow[j]);
					switch(map[step][j]){
					case 0 :  wallCount++; break;
					case 1 : waterCount++; break;
					case 2 : grassCount++; break;
					case 3 : steelCount++; break;
					}
				}
				step++;
			}
			is.close();
			isr.close();
			br.close();
		} catch (Exception e) {}
		return map;
	}

	
}
