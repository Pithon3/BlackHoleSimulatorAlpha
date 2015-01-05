package blackhole;

public class Object {

	int htime;
	double mass;
	double x;
	double y;
	double xd = 0;
	double yd = 0;
	boolean appear = true;
	final long m = (long) (3.21424747916667 * Math.pow(10, 14));
	final long kg = (long) (2 * Math.pow(10, 30));
	final double s = 525601 * Math.pow(10, 10);
	double G;
	
	public Object(double m, double x, double y, double c) {
		mass = m;
		this.x = x;
		this.y = y;
		G = c;
	}
	
	public Object(double c) {
		G = c;
	}
	
	public void update() {
		
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
	
	public void gravity(double x2, double y2, double m2) {
		double m3 = m2 * kg;
        double dis = Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2)) * m;
		double disx = x - x2;
		double disy = y - y2;
		
		double mdis = ((G * m3 * s) / (Math.pow(dis, 2)));
 		
		if (x >= x2) {
			mdis = -mdis;
		}
		
		double angle = Math.atan(disy/disx);
		
		xd += Math.cos(angle) * mdis;
		yd += Math.sin(angle) * mdis;
	}
	
	public void dissapear() {
		appear = false;		
	}
	
	public boolean isThere() {
		return appear;
	}
	
}