package com.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
/**
 * @author 吴伟
 * QQ：90359558@qq.com
 * 
 * 此为JFrame窗体
 * */
public class Index extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//窗体的宽和高
	public static final int WIDTH = 666;
	public static final int HEIGHT = 598;
	
	private TankClient tc;//在此类传递按键事件时候使用到
	
	public Index(){
		this.setLayout(null);
		
		tc = new TankClient(this);//这是JPanel面板
		this.getContentPane().add(tc);//把添加到JFrame窗体容器
		
		
		this.addKeyListener(this);
		this.setTitle("Tank War 2011 █  ");
		this.setSize(Index.WIDTH,Index.HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public void keyPressed(KeyEvent e) {
		tc.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		tc.keyReleased(e);
	}
	public void keyTyped(KeyEvent arg0) {}
	
}
