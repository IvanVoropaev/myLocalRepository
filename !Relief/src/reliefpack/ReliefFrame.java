package reliefpack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;

public class ReliefFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Scanner in;
	private String line;
	private ArrayList<Point3D> points = new ArrayList<Point3D>();
	
	ReliefFrame() {
		setSize(500, 500);
		File file = new File("coord.csv");
		
		
		try {
			in = new Scanner(file);
			while(in.hasNextLine()) {
				line = in.nextLine();
				StringTokenizer token = new StringTokenizer(line, ",");
				while(token.hasMoreTokens()) {
					double x = Double.parseDouble(token.nextToken());
					double y = Double.parseDouble(token.nextToken());
					double z = Double.parseDouble(token.nextToken());
					points.add(new Point3D(x, y, z));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ReliefPanel panel = new ReliefPanel(points);
		add(panel);
	}
}
