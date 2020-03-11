package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Triangle extends Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	private int size;
	private Color fillcolor;
	private Color mycolor;
	private int fill_status=0;
	public Triangle (int x , int y) {
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
	public void set_fill_status(int fill_status) {
		this.fill_status = fill_status; 
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
		if(fill_status == 1) {
			g2.setColor(fillcolor);
			int xp[] = {x + (width/2), x, x + width};
			int yp[] = {y, y + height, y + height};
			g2.fillPolygon(xp, yp, 3);
		}
		g2.setColor(mycolor);
		int xp[] = {x + (width/2), x, x + width};
		int yp[] = {y, y + height, y + height};
		g2.drawPolygon(xp, yp, 3);
		
		
	}
	public void set_color(Color color) {
		mycolor = color;
	}
	public void set_fillcolor(Color color) {
		fillcolor = color;
	}
	public boolean is_in(int _x,int _y) {
		if((_x>x) && (_x < x + width)) {
			if((_y<y +  height) && (_y > y)) {
				return true;
			}
		}
		return false;
	}
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	
}