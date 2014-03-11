package reliefpack;

import java.awt.geom.Point2D;

public class Point3D extends Point2D {


	private double x;
	private double y;
	private double z;
	
	public Point3D (double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		setLocation(x, y);
	}
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
	public double getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	
	public Point2D getPoint2D() {
		return new Point2D.Double(x, y);
	}

	@Override
	public void setLocation(double x, double y) {
		// TODO Auto-generated method stub
		this.x = x;
		this.y = y;
	}

}
