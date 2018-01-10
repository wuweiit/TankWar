package com.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class TankClient extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final int WIDTH  = Index.WIDTH  -  6;
	public static final int HEIGHT = Index.HEIGHT - 27;
	

	//����û�����η�����Ϊ�ڱ�������
	Title title = new Title(this);
	Level level;
	Help help;
	GameUtil gu;
	GameOver gameover = new GameOver(this);
	Map map;
	
	//���̹��
	Tank tank = new Tank(240, 540, true, Direction.STOP, this);
	//�Ϲ�
	Symbol symbol = new Symbol(300, 540, this);
	
	
	//���¼��ϱ����������
	List<Missile> missiles = new ArrayList<Missile>();//�ӵ�
	List<Tank> tanks = new ArrayList<Tank>();//��̹��
	List<Explode> explodes = new ArrayList<Explode>();//��ը
	
	//֮���ڻ���ͼ�α���ʹ�õ���
	private Missile m;
	private Tank t;
	private Explode e;
	
	Index index;
	
	public static int tanksCount = 3;
	public static int tankCount = 3;
	
	public TankClient(Index index) {
		this.index = index;
		this.setLayout(null);//���þ��Բ��ַ�ʽ
		this.setBackground(Color.black);//���ñ�����ɫ
		this.setSize(TankClient.WIDTH, TankClient.HEIGHT);
		

		new Thread(new ThreadManager()).start();  //�����߳�
	}


	/**
	 * ����
	 * */
	public void paint(Graphics g) {
		super.paint(g);
		Color c = g.getColor();
		
		
		if (title != null) {
			title.draw(g);
			return;
		}
		if(level != null){
			level.draw(g);
			return;
		}
		if (gu != null) {
			gu.draw(g);
			return;
		}if(help != null){
			help.draw(g);
			return;
		}
		/*
		 * ����ͼ
		 */
		map.draw(g);
		symbol.draw(g);
		if(!symbol.isLife()){
			tank.setLife(false);
			
		}
		
		if(tanks.size() == 0){
			for (int i = 0; i < 3; i++) {
				if(tanksCount <= 0){
					map = null;
					missiles.removeAll(missiles);
					level = new Level(this);
					tanksCount = 3;
					return;
				}
				tanks.add(new Tank(i*315, 0, false, Direction.D,this));
				
			}
		}
		for (int i = 0; i < missiles.size(); i++) {
			m = missiles.get(i);
			m.hatSymbol(symbol);//�����
			m.hatTanks(tanks);
			m.hatTank(tank);
			m.hatWalls(map);
			m.hatSteels(map);
			m.draw(g);
		}
		
		
		for (int i = 0; i < tanks.size(); i++) {
			t = tanks.get(i);
			t.hatTanks(tanks);
			t.hatWalls(map);
			t.hatWaters(map);
			t.hatSteels(map);
			t.draw(g);
		}
		for (int i = 0; i < explodes.size(); i++) {
			e = explodes.get(i);
			e.draw(g);

		}
		/** ���          �Լ����м����Ŀ*/
		tank.draw(g);
		if(map != null){
			for( int i = 0; i < map.walls.size(); i++ ){
				tank.hatWall(map.walls.get(i));
			}
			for( int i = 0; i < map.steels.size(); i++ ){
				tank.hatSteel(map.steels.get(i));
			}
			for( int i = 0; i < map.waters.size(); i++ ){
				tank.hatWater(map.waters.get(i));
			}
			
			if(!symbol.isLife()){
				gameover.draw(g);
			}
		}
		
		g.setColor(Color.orange);
		g.setFont(new Font("",Font.BOLD,10));
	}

	public void keyPressed(KeyEvent e) {
		if (title != null) {
			title.keyPressed(e);
			return;
		}
		if (level != null){
			level.keyPressed(e);
			return;
		}
		if (gu != null) {
			gu.keyPressed(e);
			return;
		}
		if (help != null){
			help.keyPressed(e);
			return;
		}
		if(!symbol.isLife()){
			gameover.keyPressed(e);
		}
		tank.keyPressed(e);

	}

	public void keyReleased(KeyEvent e) {
		if (title != null) {
			return;
		}
		if (level != null){
			return;
		}
		if (gu != null) {
			return;
		}

		tank.keyReleased(e);

	}

	
	
	private class ThreadManager implements Runnable {
		public void run() {
			try {while (true) {
				if( map != null &&title == null && level == null && gu == null && help == null){
					index.setTitle("Tank War 2011 ��  " + "������" + tanksCount);
				}
				Thread.sleep(40); repaint(); }} 
			catch (InterruptedException e) { e.printStackTrace(); }
		}
	}

}
