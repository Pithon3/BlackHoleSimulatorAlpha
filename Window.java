package blackhole;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Window extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	ArrayList<Star> stars;
	ArrayList<Object> space;
	BlackHole BH;
	
	private boolean end = false;
	private double time;
	private boolean start = false;
	private String C = "";
	private double G;
	
	public Window() {
		setBackground(new Color(24, 24, 24));
		setFocusable(true);
		setDoubleBuffered(true);
		
		stars = new ArrayList<Star>();
		space = new ArrayList<Object>();
		BH = new BlackHole(10, G);
		
		KAdapter adapt = new KAdapter();
		addKeyListener(adapt);
		
		time = 1200;
		
		Timer timer = new Timer(25, this);
		timer.start();
	}
	
	public Window(double g) {
		setBackground(new Color(24, 24, 24));
		setFocusable(true);
		setDoubleBuffered(true);
		
		stars = new ArrayList<Star>();
		space = new ArrayList<Object>();
		BH = new BlackHole(10, G);
		
		KAdapter adapt = new KAdapter();
		addKeyListener(adapt);
		
		start();
		
		time = 1200;
		
		Timer timer = new Timer(20, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D G = (Graphics2D) g;
		
		if (!start & !end) {
			G.setColor(Color.white);
			G.drawString("What would you like the Universal Gravitational Constant to be for this simulation (type \"regular\" for the normal constant)? " + C + "|", 120, 250);
		} else if (!end) {
			for (int i = 0; i < stars.size(); i++) {
				Star s = (Star) stars.get(i);
				G.setColor(s.color);
				if (s.isThere()) {
					G.drawLine((int) s.x, (int) s.y, (int) s.x, (int) s.y);
				}
			}
			
			G.setColor(Color.RED);
			G.drawLine((int) BH.x, (int) BH.y, (int) BH.x, (int) BH.y);
			if (time > 1100) {
				G.drawLine((int) BH.x - 2, (int) BH.y, (int) BH.x + 1, (int) BH.y);
				G.drawLine((int) BH.x, (int) BH.y + 1, (int) BH.x, (int) BH.y - 2);
			}
			
			G.drawString(String.valueOf((int) (time / 20)), 10, 20);
			
		} else {
			G.setColor(Color.WHITE);
			G.drawString(String.valueOf(BH.mass - 10), 450, 250);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (!end & start) {
			for (int i = 0; i < stars.size(); i++) {
				Star s = (Star) stars.get(i);
				if (s.appear) {
					s.update(BH);
				} else {
					stars.remove(i);
				}
			}
			for (int i = 0; i < space.size(); i++) {
				Object s = space.get(i);
				if (s.appear) {
					s.update(BH);
				} else {
					space.remove(i);
				}
			}
			
			BH.update(stars, space);
			
			time--;
			if (time < 0) {
				start = false;
				end = true;
			}
		}		
		
		repaint();
	}
	
	private void setConstant() {
		if (C.equals("regular")) {
			G = 0.00000000006673;
			start();
		} else {
			G = Double.parseDouble(C);
			start();
		}
	}
	
	private void start() {
		start = true;
		for (int i = 0; i < 5000; i++) {
			stars.add(new Star(G));
		} for (int i = 0; i < 640; i+=2) {
			for(int j = 0; j < 960; j+=2) {
				space.add(new Object(0.005, j, i, G));
			}
		}
	}
	
	private class KAdapter extends KeyAdapter {
		
		public void keyTyped(KeyEvent e) {
			if (!start & !end) {
				char c = e.getKeyChar();
				if (c == '') {
					if (C.length() > 0){
						C = C.substring(0, C.length() - 1);
					}
				} else if (c == '\n') {
					setConstant();
				} else {
					C += c;
				}
			}
		}
	
	}

}
