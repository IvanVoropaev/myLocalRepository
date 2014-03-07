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
			
	private int HEIGHT = 500;
	private int WIDTH = 500;
	private int step = 5;
	
	private ArrayList<Point2D> points = new ArrayList<Point2D>();
	private ArrayList<Point2D> newpoints = new ArrayList<Point2D>();
	
	ArrayList<Double> Xpoints;
	ArrayList<Double> Ypoints;
	ArrayList<Integer> Zpoints;
	
	ReliefPanel(ArrayList<Double> Xpoints, ArrayList<Double> Ypoints, ArrayList<Integer> Zpoints) {
		
		this.Xpoints = Xpoints;
		this.Ypoints = Ypoints;
		this.Zpoints = Zpoints;
		
		double Xmax = Xpoints.get(0);
		double Xmin = Xpoints.get(0);
		double Ymax = Ypoints.get(0);
		double Ymin = Ypoints.get(0);
		for(int i = 0; i < Xpoints.size(); i++) {
			if(Xpoints.get(i) > Xmax) Xmax = Xpoints.get(i);
			if(Xpoints.get(i) < Xmin) Xmin = Xpoints.get(i);
			if(Ypoints.get(i) > Ymax) Ymax = Ypoints.get(i);
			if(Ypoints.get(i) < Ymin) Ymin = Ypoints.get(i);
		}
		
		System.out.println(Xmax + "; " + Ymax + "\n" + Xmin + "; " + Ymin);
		
		double newX;
		double newY;
		for(int i = 0; i < Xpoints.size(); i++) {
			newX = (((Xpoints.get(i) - Xmin) * WIDTH) / (Xmax - Xmin));
			newY = (HEIGHT - ((Ypoints.get(i) - Ymin) * HEIGHT) / (Ymax - Ymin));
			points.add(new Point2D.Double(newX, newY));
			//System.out.println(HEIGHT + "; " + WIDTH);
			//System.out.println(newX + "; " + newY);
		}
		
		
		
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
		
		
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		/*for(int i = 0; i < points.size(); i++) {
			g2.drawLine((int) points.get(i).getX(), (int) points.get(i).getY(), (int) points.get(i).getX(), (int) points.get(i).getY());
		}*/
		
		/*int hig = 1600;
		
		ArrayList<Point2D> hpoint = new ArrayList<Point2D>();
		for(int i = 0; i < points.size(); i++) {
			if(Zpoints.get(i) == hig) {
				hpoint = points;
			}
		}
		
		Polygon poly = new Polygon();
		
		for(int i = 0; i < hpoint.size(); i++) {
			poly.addPoint((int) hpoint.get(i).getX(), (int) hpoint.get(i).getY());
		}
		
		g2.draw(poly);*/
		
		ArrayList<Point2D> net = setka();
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
		}
		
	
	}
	
	private ArrayList<Point2D> setka() {
		
		ArrayList<Point2D> net = new ArrayList<Point2D>();
		Point2D point;
		for(int i = step / 2; i < WIDTH; i = i + step){
			for(int j = step / 2; j < HEIGHT; j = j + step) {
				point = new Point2D.Double(i, j);
				net.add(point);
			}
		}
		
		
		/*for(int i = 0; i < net.size(); i++) {
			System.out.println(net.get(i).getX() + "; " + net.get(i).getY());
		}*/
		
		return net;
		
		
	}
	
	public ArrayList<Integer> highs(ArrayList<Point2D> net) {
		
		ArrayList<Integer> high = new ArrayList<Integer>();
		
		for(int i = 0; i < net.size(); i++) {
			
			int dSum = 0;
			int k = 0;
			//ArrayList<Point2D> aroundPoint = new ArrayList<Point2D>();
			for(int j = 0; j < points.size(); j++) {
				if(((points.get(j).getX() < (net.get(i).getX() + step)) && (points.get(j).getX() > (net.get(i).getX() - step))) && 
						((points.get(j).getY() < (net.get(i).getY() + step)) && (points.get(j).getY() > (net.get(i).getY() - step)))) {
					dSum = dSum + Zpoints.get(j);
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
	}
}
