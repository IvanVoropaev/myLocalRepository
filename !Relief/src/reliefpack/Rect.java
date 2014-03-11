package reliefpack;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class Rect {
	
	private Rectangle2D rect;
	private double altitude;
	//private double Gradient[];
	
	public Rect(Rectangle2D rect, double altitude, double[] Gradient) {
		this.rect = rect;
		this.altitude = altitude;
		//this.Gradient = Gradient;
	}
	
	public Rect() {
		// TODO Auto-generated constructor stub
	}

	public void setRectangle(Rectangle2D rect) {
		this.rect = rect;
	}
	
	public void setAltitude(double altitude) {
		this.altitude = altitude;
	}
	
	//public void setGradient(double Gradient[]) {
		//this.Gradient = Gradient;
	//}
	
	public Rectangle2D getRectangle() {
		return rect;
	}
	
	public double getAltitude() {
		return altitude;
	}
	
	public Color getColor(double Gradient[]) {
		Color color = new Color(0, 0, 10);
		int j = 0;
		for(int i = 1; i < Gradient.length; i++) {
			if((altitude < Gradient[i]) && (altitude > Gradient[i - 1])) {
				//j++;
				break;
			}
			//color.darker().darker().darker();
			color = color.brighter();
			j++;	
		}
		System.out.println(color + " " + j);
		return color;
	}
}
