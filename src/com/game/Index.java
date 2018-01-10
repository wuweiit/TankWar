package com.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
/**
 * @author ��ΰ
 * QQ��90359558@qq.com
 * 
 * ��ΪJFrame����
 * */
public class Index extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//����Ŀ�͸�
	public static final int WIDTH = 666;
	public static final int HEIGHT = 598;
	
	private TankClient tc;//�ڴ��ഫ�ݰ����¼�ʱ��ʹ�õ�
	
	public Index(){
		this.setLayout(null);
		
		tc = new TankClient(this);//����JPanel���
		this.getContentPane().add(tc);//����ӵ�JFrame��������
		
		
		this.addKeyListener(this);
		this.setTitle("Tank War 2011 ��  ");
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
