package blackhole;

import java.util.ArrayList;
import java.util.Random;

public class BlackHole extends Object{

	private Random rand = new Random();
	
	public BlackHole(int m, double G) {
		super(G);
		
		mass = m;
		x = rand.nextInt(960);
		y = rand.nextInt(640);
	}
	
	public void update(ArrayList<Star> stars, ArrayList<Object> space) {
		for (int i = 0; i < stars.size(); i++) {
			Star o = (Star) stars.get(i);
			
			int ix = (int) x;
			int iy = (int) y;
			int x2 = (int) o.x;
			int y2 = (int) o.y;
			int disx = Math.abs(ix - x2);
			int disy = Math.abs(iy - y2);
			
			
			if (disx < 3 & disy < 3) {				
				o.dissapear();
				mass += o.mass;
			} 
			
			if (o.htime > 50) {
				o.dissapear();
				mass += o.mass;
			}
		}
		
		for (int i = 0; i < space.size(); i++) {
			Object s = (Object) space.get(i);
			
			int ix = (int) x;
			int iy = (int) y;
			int x2 = (int) s.x;
			int y2 = (int) s.y;
			int disx = Math.abs(ix - x2);
			int disy = Math.abs(iy - y2);
			
			
			if (disx < 1 && disy < 1) {				
				s.dissapear();
				mass += s.mass;
			} 
			
			if (s.htime > 50) {
				s.dissapear();
				mass += s.mass;
			}
		}
	}
	
}
