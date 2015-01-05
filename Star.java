package blackhole;

import java.awt.Color;
import java.util.Random;

public class Star extends Object{
	
	private Random rand = new Random();
	
	Color color;
	
	public Star(double G) {
		super(G);
		
		x = rand.nextInt(960);
		y = rand.nextInt(640);
		
		int num = rand.nextInt(7);
		
		if (num < 2) {
			color = new Color(255, 203, 151);
			mass = 1;
		} else if (1 < num & num < 6) {
			color = Color.WHITE;
			mass = 1.5;
		} else if (num == 6) {
			color = new Color(153, 217, 234);
			mass = 2.5;
		}
	}
	
	public void update(Object obj) {		
		gravity(obj.x, obj.y, obj.mass);
		
		y += yd;
		x += xd;
		
		if (y > 639) {
			y = 0;
		} if (x > 959) {
			x = 0;
		} if (x < 0) {
			x = 959;
		} if (y < 0) {
			y = 639;
		}
		
		if (xd > 0.5 | yd > 0.5) {
			htime++;
		}
	}
	
}
