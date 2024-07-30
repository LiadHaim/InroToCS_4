package ex4;

import geo.Circle_2D;
import geo.GeoShape;
import geo.Point_2D;
import geo.Rect_2D;
import gui.GUIShape;
import gui.GUI_Shape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection{
	private ArrayList<GUI_Shape> _shapes;

	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		return _shapes.remove(i);
		//////////////////////////////////////////
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		//////////add your code below ///////////
		_shapes.add(i, s);
		//////////////////////////////////////////
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public ShapeCollection copy() {

		//////////add your code below ///////////
		ShapeCollection copy = new ShapeCollection();
		for (int i = 0; i<_shapes.size(); i++) {
			GeoShape g = _shapes.get(i).getShape().copy();
			GUIShape guiSh = new GUIShape(g,_shapes.get(i).isFilled(),_shapes.get(i).getColor(), _shapes.get(i).getTag());
			copy.add(guiSh);
		}
		return copy;
		//////////////////////////////////////////
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);

	}

	@Override
	public void removeAll() {

		_shapes.removeAll(_shapes);

	}

	@Override
	public void save(String file) {
		//////////add your code below ///////////
		try {
			FileWriter Writef = new FileWriter(file);
			for(int i = 0; i<_shapes.size(); i++) {
				Writef.write((_shapes.get(i).toString()+ "\n"));
			}
			Writef.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String file) {
		////////// add your code below ///////////
		_shapes.clear();
		try {
			// Open
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			// Read
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				_shapes.add(new GUIShape(line));
			}
			// Close
			bufferedReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Rect_2D getBoundingBox() {
		Rect_2D ans = null;
		//////////add your code below ///////////
		if(_shapes.size() < 1) {
			return null;
		}
		double maxX = 0;
		double maxY = 0;
		double minx = Ex4_Const.DIM_SIZE;
		double miny = Ex4_Const.DIM_SIZE;


		for(GUI_Shape geoshape : _shapes) {

			GeoShape geo = geoshape.getShape();
			if (geo instanceof Circle_2D) {
				Circle_2D cir = (Circle_2D) geo;
				double r = cir.getRadius();
				Point_2D center = cir.getAllPoints()[0];
				double cenX = center.x();
				double cenY = center.y();
				if (cenX - r < minx) {minx = cenX - r;}

				if (cenX + r > maxX) {maxX = cenX + r;}

				if (cenY - r < miny) {miny = cenY - r;}

				if (cenY + r > maxY) {	maxY = cenY + r;}
			}
			else {
				Point_2D[] points = geo.getAllPoints();
				for (Point_2D p : points) {
					if (p.x() < minx) {minx = p.x();}
					if (p.x() > maxX) {maxX = p.x();}
					if (p.y() < miny) {miny = p.y();}
					if (p.y() > maxY) {maxY = p.y();}
				}
			}
		}
		Point_2D p1 = new Point_2D(minx, miny);
		Point_2D p2 = new Point_2D(maxX, maxY);
		ans = new Rect_2D(p1, p2);

		return ans;
	}
	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
}
