package com.game.eidt;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class GameEdit extends JFrame {

	public static final int WIDTH = 866;
	public static final int HEIGHT = 598;

	private Image wallsImage;
	private Image wallImage;
	private Image waterImage;
	private Image steelsImage;
	private Image grassImage;
	JPanel panel;
	JLabel[][] map = new JLabel[22][19]; 
	OutputStream os ;
	
	
	public GameEdit() {
		iniFile();//文件初始化
		iniImage();//图片加载
		this.setLayout(null);
		
		Container con =  this.getContentPane();
		con.add(new Panel());
		
		this.setTitle("Tank War 2010 地图编辑器");
		this.setSize(GameEdit.WIDTH,GameEdit.HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	private void iniFile(){
		String url = System.getProperty("user.dir");
		try {
			os = new FileOutputStream(url+"\\mapEdit.ini");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void iniImage(){
		try {
			wallsImage = ImageIO.read(new File("images/walls.gif"));
			wallImage = ImageIO.read(new File("images/wall.gif"));
			waterImage = ImageIO.read(new File("images/water.gif"));
			steelsImage = ImageIO.read(new File("images/steels.gif"));
			grassImage = ImageIO.read(new File("images/grass.gif"));
			
		} catch (IOException e) {e.printStackTrace();}
	}
	

	public static void main(String[] args) {
		new GameEdit();
	}
	private class Panel extends JPanel{
		public Panel(){
			this.setLayout(null);
			
			this.setFocusable(true);
			this.setBackground(Color.black);
			this.setSize(660, GameEdit.HEIGHT-27);
			this.setVisible(true);
		}
		public void paint(Graphics g){
			super.paint(g);
			for(int i=0; i < map.length; i++){
				for(int j =0; j< map[0].length; j++){
					map[i][j] = new JLabel(new ImageIcon("images/walls.gif"));
					map[i][j].setBounds(i*30,j*30,30,30);
		
					this.add(map[i][j]);
				}
			}
			
		}
		
	} 
	
}
