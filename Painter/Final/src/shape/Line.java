package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	private int size;
	private Color mycolor;
	public Line (int x , int y) {
		super(x,y);
		this.x = x;
		this.y = y;
	}
	public void set_x(int x) {
		this.x = x;
	}
	public void set_y(int y) {
		this.y = y;
	}
	public int get_width() {
		return width;
	}
	public void set_width(int width) {
		this.width = width;
	}
	public int get_height() {
		return height;
	}
	public void set_height(int height) {
		this.height = height;
	}
	public void set_size(int size) {
		this.size = size;
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
		g2.setColor(mycolor);
		g2.drawLine(x, y, width + x, height + y);
	}
	public void set_color(Color color) {
		mycolor = color;
	}
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public boolean is_in(int _x,int _y) {
		if((_x>x) && (_x < x + width)) {
			if((_y<y +  height) && (_y > y)) {
				return true;
			}
		}
		return false;
	}
	
}
