package shape;

import java.awt.Color;
import java.awt.Graphics;

abstract public class Shape {
	private int x;
	private int y;
	private int width;
	private int height;
	private Color mycolor;
	private int fill_status;
	private Color fillcolor;
	private int size;
	public Shape (int x, int y,int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	public Shape (int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int get_size() {
		return size;
	}
	public void set_color(Color color) {
		mycolor = color;
	}
	public void set_fill_status(int fill_status) {
		this.fill_status = fill_status; 
	}
	abstract public void draw(Graphics g);
	public int get_x() {
		return x;
	}
	public int get_y() {
		return y;
	}
	public void set_x(int x) {
		this.x = x ;
	}
	public void set_y(int y) {
		this.y = y  ;
	}
	public void set_fillcolor(Color color) {
		fillcolor = color;
	}
	public int get_width() {
		return width;
	}
	public void set_width(int a) {
		this.width = a;
	}
	public void set_height(int a) {
		this.height = a;
	}
	public int get_height() {
		return height;
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