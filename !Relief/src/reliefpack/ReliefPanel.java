package reliefpack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;



public class ReliefPanel extends JPanel {
	
	//int HEIGHT = this.getHeight();
	//int WIDTH = this.getWidth();
			
	private int HEIGHT = 700;
	private int WIDTH = 1100;
	private int step = 10;
	
	private ArrayList<Point3D> points = new ArrayList<Point3D>();
	private ArrayList<Point3D> newpoints = new ArrayList<Point3D>();
	
	private double Xmax;
	private double Xmin;
	private double Ymax;
	private double Ymin;
	
	private double[] Gradient = new double[10];
	ArrayList<Rect> net;
		
	ReliefPanel(ArrayList<Point3D> points) {
		
		this.points = points;
		newpoints = newPoints(points);
		Gradient = Gradient(points);
		net = setka();
		
		/*for(int i = 0; i < net.size(); i++) {
			System.out.println(net.get(i).getRectangle().getCenterX() + " " + net.get(i).getRectangle().getCenterY() + " " + net.get(i).getAltitude());
		}*/
		
		/*for(int i = 0; i < Gradient.length; i++) {
			System.out.println(Gradient[i]);
		}*/
		
		/*Point2D myPoint = new Point2D.Double(500, 500);
		int round = 10;
		ArrayList<Point2D> aroundPoint = new ArrayList<Point2D>();
		
		for(int i = 0; i < points.size(); i++) {
			if(((points.get(i).getX() < (myPoint.getX() + round)) && (points.get(i).getX() > (myPoint.getX() - round))) && 
					((points.get(i).getY() < (myPoint.getY() + round)) && (points.get(i).getY() > (myPoint.getY() - round)))) aroundPoint.add(points.get(i));
		}
		
		for(int i = 0; i < aroundPoint.size(); i++) {
			System.out.println(aroundPoint.get(i).getX() + "; " + aroundPoint.get(i).getY());
		}*/
		
		//for(int i = 0)
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		//ArrayList<Rect> net = setka();
		
		for(int i = 0; i < net.size(); i++) {
			g2.setPaint(net.get(i).getColor(Gradient));
			g2.fill(net.get(i).getRectangle());
		}
		
		//g2.draw(net.get(100).getRectangle());
		
		/*for(int i = 0; i < newpoints.size(); i++) {
			g2.drawLine((int) newpoints.get(i).getX(), (int) newpoints.get(i).getY(), (int) newpoints.get(i).getX(), (int) newpoints.get(i).getY());
		}*/
				
		/*ArrayList<Point2D> net = setka();
		ArrayList<Integer> high = highs(net);
		//Color color = new Color();
		int hiMax = high.get(0);
		int hiMin = high.get(0);
		for(int i = 0; i < high.size(); i++) {
			if(high.get(i) > hiMax) hiMax = high.get(i);
			if(high.get(i) < hiMin) hiMin = high.get(i);
		}
		
		double gradientStep = (hiMax - hiMin) / 6;
		System.out.println(hiMin + "; " + hiMax + "; " + gradientStep);
		for(int i = 0; i < high.size(); i++) {
			//System.out.println(net.get(i).getX() + "; " + net.get(i).getY() + "; " + high.get(i));
			Rectangle2D rect = new Rectangle2D.Double();
			rect.setFrameFromCenter(net.get(i), new Point2D.Double(net.get(i).getX() - step / 2, net.get(i).getY() - step / 2));
			if ((high.get(i) > hiMin) && (high.get(i) < hiMin + gradientStep)) {
				g2.setPaint(new Color(0, 0, 0));
				//g2.setPaint(Color.RED);
			}
			else if ((high.get(i) > (hiMin + gradientStep)) && (high.get(i) < hiMin + 2 * gradientStep)) {
				g2.setPaint(new Color(0, 0, 50));
				//g2.setPaint(Color.GREEN);
			}
			else if ((high.get(i) > (hiMin + 2 * gradientStep)) && (high.get(i) < hiMin + 3 * gradientStep)) {
				g2.setPaint(new Color(0, 0, 100));
				//g2.setPaint(Color.BLUE);
			}
			else if ((high.get(i) > (hiMin + 3 * gradientStep)) && (high.get(i) < hiMin + 4 * gradientStep)) {
				g2.setPaint(new Color(0, 0, 150));
				//g2.setPaint(Color.CYAN);
			}
			else if ((high.get(i) > (hiMin + 4 * gradientStep)) && (high.get(i) < hiMin + 5 * gradientStep)) {
				g2.setPaint(new Color(0, 0, 200));
				//g2.setPaint(Color.GRAY);
			}
			else if ((high.get(i) > (hiMin + 5 * gradientStep)) && (high.get(i) < hiMin + 6 * gradientStep)) {
				g2.setPaint(new Color(0, 0, 250));
				//g2.setPaint(Color.DARK_GRAY);
			}
			//g2.setPaint(Color.RED);
			g2.fill(rect);
		}*/
		
	
	}
	
	/*
	 * разбивка сетки в соответствии с заданным шагом
	 */
	private ArrayList<Rect> setka() {
		
		ArrayList<Rect> net = new ArrayList<Rect>();

		for(int i = step / 2; i < WIDTH; i = i + step) { //
			for(int j = step / 2; j < HEIGHT; j = j + step) {
				/*
				 * добавление прямоугольника сетки
				 */
				Rectangle2D rectangle = new Rectangle2D.Double();
				rectangle.setFrameFromCenter(i, j, i - step / 2, j - step / 2);
				Rect rect = new Rect();
				rect.setRectangle(rectangle);
				
				/*
				 * вычисление высоты в пределах прямоугольника
				 */
				double dSum = 0;
				int l = 0;
				for(int k = 0; k < newpoints.size(); k++) {
					if(rectangle.contains(newpoints.get(k).getPoint2D())) {
						dSum = dSum + newpoints.get(k).getZ();
						l++;
					}
				}
				if(l != 0) {
					dSum = dSum / l;
					rect.setAltitude(dSum);
				}
				else rect.setAltitude(0);
				
				net.add(rect);
			}
		}
		
		return net;
			
	}
	
	/*public ArrayList<Integer> highs(ArrayList<Point2D> net) {
		
		ArrayList<Integer> high = new ArrayList<Integer>();
		
		for(int i = 0; i < net.size(); i++) {
			
			int dSum = 0;
			int k = 0;
			//ArrayList<Point2D> aroundPoint = new ArrayList<Point2D>();
			for(int j = 0; j < points.size(); j++) {
				if(((points.get(j).getX() < (net.get(i).getX() + step)) && (points.get(j).getX() > (net.get(i).getX() - step))) && 
						((points.get(j).getY() < (net.get(i).getY() + step)) && (points.get(j).getY() > (net.get(i).getY() - step)))) {
					dSum = (int) (dSum + points.get(j).getZ());
					k++;
				}	
			}
			if(k != 0) {
				dSum = dSum / k;
				high.add((int) dSum);
			}
			else high.add(0);

		}
		return high;	
	}*/
	
	/*
	 * пересчет координат точек для отображения на панели
	 */
	private ArrayList<Point3D> newPoints(ArrayList<Point3D> points) {
		
		ArrayList<Point3D> newPoints = new ArrayList<Point3D>();
		
		Xmax = points.get(0).getX();
		Xmin = points.get(0).getX();
		Ymax = points.get(0).getY();
		Ymin = points.get(0).getY();
		
		for(int i = 0; i < points.size(); i++) {
			if(points.get(i).getX() > Xmax) Xmax = points.get(i).getX();
			if(points.get(i).getX() < Xmin) Xmin = points.get(i).getX();
			if(points.get(i).getY() > Ymax) Ymax = points.get(i).getY();
			if(points.get(i).getY() < Ymin) Ymin = points.get(i).getY();
		}
		
		double newX;
		double newY;
		for(int i = 0; i < points.size(); i++) {
			newX = (((points.get(i).getX() - Xmin) * WIDTH) / (Xmax - Xmin));
			newY = (HEIGHT - ((points.get(i).getY() - Ymin) * HEIGHT) / (Ymax - Ymin));
			newPoints.add(new Point3D(newX, newY, points.get(i).getZ()));
		}
		return newPoints;
	}
	
	
	/*
	 * получение 10-кратного градиента для высоты
	 */
	public double[] Gradient(ArrayList<Point3D> points) {
		
		double MaxZ = points.get(0).getZ();
		double MinZ = points.get(0).getZ();
		double step;
		double[] Gradient = new double[10];
		for(int i = 0; i < points.size(); i++) {
			if(points.get(i).getZ() > MaxZ) MaxZ = points.get(i).getZ();
			if(points.get(i).getZ() < MinZ) MinZ = points.get(i).getZ();
		}
		step = (MaxZ - MinZ) / 9;
		
		System.out.println(MinZ + " " + MaxZ + " " + step);
		
		Gradient[0] = MinZ;
		for(int i = 1; i < 10; i++) {
			Gradient[i] = Gradient[i - 1] + step;
		}
		
		return Gradient;
	}
}
