package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Rec extends Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color mycolor;
	private Color fillcolor;
	private int size;
	private int fill_status=0;
	public Rec (int x , int y,int width, int height) {
		super(x,y,width,height);
		this.x = x;
		this.y = y;
		this.width= width;
		this.height = height;
	}
	public int get_width() {
		return width;
	}
	public int get_height() {
		return height;
	}
	public int get_size() {
		return this.size;
	}
	public void set_size(int size) {
		this.size= size;
	}
	public void set_fill_status(int fill_status) {
		this.fill_status = fill_status; 
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND,0));
		if(fill_status == 1) {
			g2.setColor(fillcolor);
			g2.fillRect(x, y, width, height);
			g2.setColor(mycolor);
			g2.drawRect(x, y, width, height);
		}
		
		g2.setColor(mycolor);
		g2.drawRect(x, y, width, height);
		
	}
	public void set_color(Color color) {
		mycolor = color;
	}
	public void set_fillcolor(Color color) {
		fillcolor = color;
	}
	public void set_x(int x) {
		this.x = x;
	}
	public void set_y(int y) {
		this.y = y;
	}
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public void set_width(int a) {
		this.width = a;
	}
	public void set_height(int a) {
		this.height = a;
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