package com.game;

import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JFrame;

public class Calculate extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private TankClient tc;
	Point point;
	/**
	 * This is the default constructor
	 * @param point 
	 */
	public Calculate(TankClient tc, Point point) {
		this.tc = tc;
		this.point = point;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		tc.setFocusable(true);
		this.setLocation(point.x+666,point.y+200);
		this.setSize(243, 300);
		this.setContentPane(getJContentPane());
		this.setTitle("Tank War Calculate");
		this.setFocusable(false);
		this.setVisible(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
