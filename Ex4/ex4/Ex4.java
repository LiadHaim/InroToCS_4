package ex4;

import geo.*;
import gui.Ex4_GUI;
import gui.GUIShape;
import gui.GUI_Shape;
import gui.StdDraw_Ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw with the Map class.
 * Written for 101 java course it uses simple static functions to allow a
 * "Singleton-like" implementation.
 * @author boaz.benmoshe
 *
 */
public class Ex4 implements Ex4_GUI {
	private  GUI_Shape_Collection _shapes = new ShapeCollection();
	private GUI_Shape _gs;
	private  Color _color = Color.blue;
	private  boolean _fill = false;
	private  String _mode = "";
	private Point_2D _p1;
	private Point_2D _p2;
	private int cnt_theTag = 0; // counter for the tag
	private ArrayList<Point_2D> poly = new ArrayList<Point_2D>();
	private ArrayList<Point_2D> poly2 = new ArrayList<Point_2D>();

	private  static Ex4 _winEx4 = null;

	private Ex4() {
		init(null);
	}

	public void init(GUI_Shape_Collection s) {
		if(s==null) {_shapes = new ShapeCollection();} else {_shapes = s.copy();}
		_gs = null;
		_color = Color.blue;
		_fill = false;
		_mode = "";
		_p1 = null;
	}

	public void show(double d) {
		StdDraw_Ex4.setScale(0,d);
		StdDraw_Ex4.show();
		drawShapes();
	}

	public static Ex4 getInstance() {
		if(_winEx4 ==null) {
			_winEx4 = new Ex4();
		}
		return _winEx4;
	}

	public void drawShapes() {
		StdDraw_Ex4.clear();
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape sh = _shapes.get(i);

			drawShape(sh);
		}
		if(_gs!=null) {drawShape(_gs);}
		StdDraw_Ex4.show();
	}

	private static void drawShape(GUI_Shape g) {
		StdDraw_Ex4.setPenColor(g.getColor());
		if (g.isSelected()) {
			StdDraw_Ex4.setPenColor(Color.gray);
		}
		GeoShape gs = g.getShape();
		boolean isFill = g.isFilled();
		//Circle
		if (gs instanceof Circle_2D) {
			Circle_2D c = (Circle_2D) gs;
			Point_2D cen = c.getCenter();
			double rad = c.getRadius();
			if (isFill) {
				StdDraw_Ex4.filledCircle(cen.x(), cen.y(), rad);
			} else {
				StdDraw_Ex4.circle(cen.x(), cen.y(), rad);
			}
		}
		//Rectangle
		if (gs instanceof Rect_2D) {
			Rect_2D r = (Rect_2D) gs;
			if (isFill) {
				// Draw a filled polygon
				StdDraw_Ex4.filledPolygon(r.getx(), r.gety());
			} else {
				// Draw an unfilled polygon
				StdDraw_Ex4.polygon(r.getx(), r.gety());
			}
		}

//Segment
		if (gs instanceof Segment_2D) {
			Segment_2D s = (Segment_2D) gs;
			Point_2D[] arr = s.getAllPoints();

			Point_2D p0 = new Point_2D(arr[0]);
			Point_2D p1 = new Point_2D(arr[1]);

			// Draw a line
			StdDraw_Ex4.line(p0.x(), p0.y(), p1.x(), p1.y());
		}

//Triangle
		if (gs instanceof Triangle_2D) {
			Triangle_2D t = (Triangle_2D) gs;
			Point_2D[] arr = t.getAllPoints();

			double[] arr_x = new double[arr.length];
			double[] arr_y = new double[arr.length];

			for (int i = 0; i < arr.length; i++) {
				arr_x[i] = arr[i].x();
				arr_y[i] = arr[i].y();
			}
			if (isFill) {
				// Draw a filled triangle
				StdDraw_Ex4.filledPolygon(arr_x, arr_y);
			} else {
				// Draw an unfilled triangle
				StdDraw_Ex4.polygon(arr_x, arr_y);
			}
		}

//Polygon
		if (gs instanceof Polygon_2D) {
			Polygon_2D pol = (Polygon_2D) gs;
			Point_2D[] parr = pol.getAllPoints();
			StdDraw_Ex4.polygon(pol.get_x(), pol.get_y());
//			double[] arr_x = new double[parr.length];
//			double[] arr_y = new double[parr.length];
//			for (int i = 0; i < parr.length; i++) {
//				arr_x[i] = parr[i].x();
//				arr_y[i] = parr[i].y();
//			}
			if (isFill) {
				// Draw a filled polygon
				StdDraw_Ex4.filledPolygon(pol.get_x(), pol.get_y());
			} else {
				// Draw an unfilled polygon
				StdDraw_Ex4.polygon(pol.get_x(), pol.get_y());
			}
		}
	}

	private void setColor(Color c) {
		// Update the color of the selected shapes in the shape list (_shapes)
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setColor(c);
			}
		}
	}

	private void setFill() {
		// Set the fill status of the selected shapes in the shape list (_shapes)
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			if(s.isSelected()) {
				s.setFilled(_fill);
			}
		}
	}

	public void actionPerformed(String p) {
		// Set the color based on the selected option
		_mode = p;
		if(p.equals("Blue")) {_color = Color.BLUE; setColor(_color);}
		if(p.equals("Red")) {_color = Color.RED; setColor(_color);}
		if(p.equals("Green")) {_color = Color.GREEN; setColor(_color);}
		if(p.equals("White")) {_color = Color.WHITE; setColor(_color);}
		if(p.equals("Black")) {_color = Color.BLACK; setColor(_color);}
		if(p.equals("Yellow")) {_color = Color.YELLOW; setColor(_color);}
		if(p.equals("Fill")) {_fill = true; setFill();}
		if(p.equals("Empty")) {_fill = false; setFill();}
		if(p.equals("Clear")) {_shapes.removeAll(); cnt_theTag = 0;}
	// Set the fill status based on the selected option
		if(p.equals("ByArea")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Area));}
		if(p.equals("ByAntiArea")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Anti_Area));}
		if(p.equals("ByPerimeter")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Perimeter));}
		if(p.equals("ByAntiPerimeter")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Anti_Perimeter));}
		if(p.equals("ByTag")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Tag));}
		if(p.equals("ByAntiTag")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Anti_Tag));}
		if(p.equals("ByToString")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_toString));}
		if(p.equals("ByAntiToString")) {_shapes.sort(new Comparator(Ex4_Const.Sort_By_Anti_toString));}


		//None
		if(p.equals("None")) {
			// Deselect all shapes in the shape list (_shapes)

			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape shape = _shapes.get(i);
				shape.setSelected(false);
			}
		}
		//Anti
		if(p.equals("Anti")) {
			// Toggle the selection status of each shape in the shape list (_shapes)

			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape shape = _shapes.get(i);
				if(shape.isSelected()) {
					shape.setSelected(false);
				} else {
					shape.setSelected(true);
				}
			}
		}

		//All
		if(p.equals("All")) {
			// Select all shapes in the shape list (_shapes)

			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				s.setSelected(true);
			}
		}

		//Info
		if(p.equals("Info")) {
			// Get information about the shapes and print it

			String str = getInfo();
			System.out.println(str);
		}

		//Save
		if(p.equals("Save")) {
			// Open a file dialog to choose a location to save the shapes as an image file

			FileDialog chooser = new FileDialog(new JFrame(),"Use a .png or .jpg extension", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if(filename != null) {
				_shapes.save(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}

		//Load
		if (p.equals("Load")) {
			// Open a file dialog to choose an image file to load shapes from

			FileDialog chooser = new FileDialog(new JFrame(),"Use a .png or .jpg extension", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getFile();
			if(filename != null) {
				_shapes.load(chooser.getDirectory() + File.separator + chooser.getFile());
			}
		}

		drawShapes();
	}


	public void mouseClicked(Point_2D p) {
		System.out.println("Mode: "+_mode+"  "+p);
		if(_mode.equals("Circle")) {
			if(_gs==null) {
				// Creating a new circle shape

				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				cnt_theTag++;
			}
		}
		//Segment
		if(_mode.equals("Segment")) {
			if(_gs==null) {
				// Creating a new segment shape

				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				cnt_theTag++;
			}
		}
		//Rect
		if(_mode.equals("Rect")) {
			if(_gs==null) {
				// Creating a new rectangle shape

				_p1 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				cnt_theTag++;
			}
		}

		//Triangle
		if(_mode.equals("Triangle")) {
			if(_gs==null) {
				// Creating a new triangle shape

				_p1 = new Point_2D(p);
			} else if(_p2 == null) {
				_p2 = new Point_2D(p);
			} else {
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_p2 = null;
				_gs = null;
				_p1 = null;
				cnt_theTag++;
			}
		}

		//poly
		if(_mode.equals("Polygon")) {
			if(_gs==null) {
				// Add points to the polygon shape
				poly.add(p);
				_p1 = p;
			} else {poly.add(p);}
		}

		// scale 90%
		if(_mode.equals("Scale_90%")){
			// Scale the selected shapes by 90% around the given point

			_p1 = new Point_2D(p);
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				GeoShape g = s.getShape();
				if(s.isSelected() && g!=null) {
					g.scale(_p1, 0.9);
				}
			}
		}

		// scale 110%
		if(_mode.equals("Scale_110%")){
			// Scale the selected shapes by 110% around the given point

			_p1 = new Point_2D(p);
			for(int i=0;i<_shapes.size();i++) {
				GUI_Shape s = _shapes.get(i);
				GeoShape g = s.getShape();
				if(s.isSelected() && g!=null) {
					g.scale(_p1, 1.1);
				}
			}
		}


		//Rotate
		if (_mode.equals("Rotate")) {
			if (_p1 == null) {
				// Rotate the selected shapes around a given point

				_p1 = new Point_2D(p);
			} else {
				_p2 = new Point_2D(p);

				for (int i = 0; i < _shapes.size(); i++) {
					GUI_Shape s = _shapes.get(i);
					GeoShape g = s.getShape();
					if (s.isSelected() && g != null) {
						g.rotate(_p1, Math.toDegrees(_p1.getAngel(_p2)));
					}
				}
				_p1 = null;
				_p2 = null;
			}
		}


		//Move
		if(_mode.equals("Move")) {

			if(_p1==null) {_p1 = new Point_2D(p);} else {
				// Move the selected shapes based on the mouse movement

				_p1 = new Point_2D(p.x()-_p1.x(), p.y()-_p1.y());
				move();
				_p1 = null;
			}
		}

		if(_mode.equals("Point")) {
			// Toggle the selection of shapes that contain the given point

			select(p);
		}


		//copy
		if(_mode.equals("Copy")) {
			// Copy and move the selected shapes to a new location


			if (_p1== null) {
				_p1= new Point_2D(p);
			} else{

				Point_2D moveVector = new Point_2D(p.x() - _p1.x(), p.y() - _p1.y());
				for (int i = 0; i < _shapes.size(); ++i) {
					GUI_Shape shape = _shapes.get(i);
					GeoShape geo = shape.getShape();
					if (shape.isSelected() && geo != null) {
						GUI_Shape copy = shape.copy();
						copy.getShape().translate(moveVector);
						copy.setTag(cnt_theTag++);
						_shapes.add(copy);
					}
					_p1 = null;
				}
			}
		}

		//remove
		if(_mode.equals("Remove")) {	// Remove the selected shapes from the shape list

			for (int i = _shapes.size() - 1; i >= 0; --i) {
				GUI_Shape shape = _shapes.get(i);
				if (shape.isSelected()) {
					_shapes.removeElementAt(i);
				}
			}
		}

		drawShapes();
	}

	private void select(Point_2D p) {
		// Toggle the selection of shapes that contain the given point

		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(g!=null && g.contains(p)) {
				s.setSelected(!s.isSelected());
			}
		}
	}

	private void move() {
		// Move the selected shapes by the specified translation vector

		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			GeoShape g = s.getShape();
			if(s.isSelected() && g!=null) {
				g.translate(_p1);
			}
		}
	}

	public void mouseRightClicked(Point_2D p) {
		// Handle right-click events

		System.out.println("right click!");
		if (_gs != null) {
			if (_mode.equals("Polygon")) {
				Polygon_2D polygon = new Polygon_2D(poly);
				_gs = new GUIShape(polygon, _fill, _color, cnt_theTag);
				_gs.setColor(_color);
				_gs.setFilled(_fill);
				_shapes.add(_gs);
				_gs = null;
				_p1 = null;
				cnt_theTag++;
				poly.clear();
				drawShapes();
			}

//			if (_mode.equals("Circle") || _mode.equals("Rect") || _mode.equals("Triangle") || _mode.equals("Segment")) {
//				_gs = null;
//				_p1 = null;
//				_p2 = null;
//				drawShapes();
//			}
		}
	}

	public void mouseMoved(MouseEvent e) {
		if(_p1!=null) {
			// Get the current mouse coordinates

			double x1 = StdDraw_Ex4.mouseX();
			double y1 = StdDraw_Ex4.mouseY();
			GeoShape gs = null;
			//	System.out.println("M: "+x1+","+y1);
			Point_2D p = new Point_2D(x1,y1);
			if(_mode.equals("Circle")) {
				// Create a circle shape with the initial point _p1 as the center and the current point as the radius

				double r = _p1.distance(p);
				gs = new Circle_2D(_p1,r);
			}

			//Segment
			if (_mode.equals("Segment")){
				// Create a segment shape with the initial point _p1 and the current point

//				temp = new Point_2D(p)
				gs = new Segment_2D(_p1, p);
			}
//_p1.x(),_p1.y(),p.x(),p.y()
			//Rect
			if(_mode.equals("Rect")) {
				// Create a rectangle shape with the initial point _p1 and the current point

				gs = new Rect_2D(_p1,p);
			}


			//Triangle
			if(_mode.equals("Triangle")) {
				if(_p2 != null) {
					// If the second point _p2 is set, create a triangle shape with _p1, _p2, and the current point

					gs = new Triangle_2D(_p1, _p2, p);
				} else {
					// Otherwise, create a segment shape with _p1 and the current point

					gs = new Segment_2D(_p1,p);
				}
			}

			//Polygon
			if(_mode.equals("Polygon")){
				// Create a polygon shape with the collected points in poly, including the current point

				poly2 = poly;
				poly2.add(p);
				gs = new Polygon_2D(poly);
				poly2.remove(p);
			}

			_gs = new GUIShape(gs,false, Color.pink, 0);
			drawShapes();
		}
	}

	@Override
	public GUI_Shape_Collection getShape_Collection() {
		// TODO Auto-generated method stub
		return this._shapes;
	}

	@Override
	public void show() {show(Ex4_Const.DIM_SIZE); }

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String ans = "";
		for(int i=0;i<_shapes.size();i++) {
			GUI_Shape s = _shapes.get(i);
			ans +=s.toString()+"\n";
		}
		return ans;
	}
}
