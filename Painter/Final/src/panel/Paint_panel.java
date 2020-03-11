package panel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import button.Black_Bt;
import button.Blue_Bt;
import button.Fill_Bt;
import button.Move_Bt;
import button.Pencil_Bt;
import button.Rec_Bt;
import button.Red_Bt;
import shape.Line;
import shape.Oval;
import shape.Rec;
import shape.Shape;
import shape.Triangle;

public class Paint_panel extends JPanel{
	public static int select = -1;
	private int startX,startY;
	private int endX,endY;
	private int released_count;
	private int w,h;
	// �׷��� ������ ������ �迭
	ArrayList<Shape> arraylist_shape = new ArrayList<Shape>();
	
	private Color select_color;
	JTextField thicknessControl_tf;//���� �ؽ�Ʈ �ʵ�
	private int size;//���⸦ ������ ����
	
	//�����̵�
	private boolean select_status;//������ ���õǾ��ִ� �����ΰ�?
	private int change_status;// ������ �ø��ų� ���̱��� �����ΰ�? 1�� ���� �� �𼭸� 2 �� ������ �� �𼭸� 3�� �Ʒ� �� �𼭸� 4�� �Ʒ� ���� �𼭸�
	private int move_shape_num;//������ ������ �迭�� ���° �������� ������ ����
	private int move_x;// �̵��� ��ü�� startX , startY
	private int move_y;
	// ���� ���������� �׷����� �簢��
	private Rec select_rec ;
	
	
	public Paint_panel() {
		//paint panel ����
		setLayout(null);
		setBackground(Color.white);
		setBounds(135, 0, 800, 900);
		
		thicknessControl_tf = new JTextField("10", 4); // �������� �Է� �ؽ�Ʈ�ʵ� ����
        thicknessControl_tf.setHorizontalAlignment(JTextField.CENTER); // �ؽ�Ʈ �߾� ����
        thicknessControl_tf.setFont(new Font("�ü�ü", Font.PLAIN, 20)); // �ؽ�Ʈ�ʵ� ��ũ�� �� ��Ʈ , ����
        thicknessControl_tf.setBounds(0,0,50,35); // ���� �ؽ�Ʈ�ʵ� ��ġ ����
        thicknessControl_tf.setBackground(Color.GRAY);
        thicknessControl_tf.setToolTipText("����");
        
        
        add(thicknessControl_tf);
        
        
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				if(select == 6) {
					w = Math.abs(e.getX()-startX);
					h = Math.abs(e.getY()-startY);
					
					t = new JTextArea();
					t.setBounds(startX,startY,w,h);
					Border border = BorderFactory.createDashedBorder(Color.black);
					t.setBorder(border);
					t.setLineWrap(true);
					t.setOpaque(false);
					add(t);
				}
				if(select == 3) {//��
					Line line = new Line(startX,startY);
					line.set_width(e.getX() - startX);
					line.set_height(e.getY() - startY);
					line.set_color(select_color);
					size = Integer.parseInt(thicknessControl_tf.getText());
					line.set_size(size);
					arraylist_shape.add(line);
				}
				if(select == 2) {//�ﰢ��
					Triangle tri = new Triangle(startX,startY);
					tri.set_width(e.getX() - startX);
					tri.set_height(e.getY() - startY);
					tri.set_color(select_color);
					size = Integer.parseInt(thicknessControl_tf.getText());
					tri.set_size(size);
					arraylist_shape.add(tri);
				}
				if(select == 1) {//��
					Oval oval = new Oval(startX,startY);
					oval.set_width(e.getX() - startX);
					oval.set_height(e.getY() - startY);
					oval.set_color(select_color);
					size = Integer.parseInt(thicknessControl_tf.getText());
					oval.set_size(size);
					arraylist_shape.add(oval);
				}
				if(released_count == 1) {//released_count�� �巡�װ� �Ͼ�� 1�̵� .
					if(select == 0 || select == 5) {
						
						repaint();
					}
					if(change_status>0) {
						change_status = 0;
						select_status = false;
						repaint();
					}
					if(select_status) {//�̶��� ������ ���õ� ���¿��� �巡�� �ߴٰ� ���콺�� �������� (�̵��� ��ü�� �����Ұ���)
						arraylist_shape.get(move_shape_num).set_x(e.getX());
						arraylist_shape.get(move_shape_num).set_y(e.getY());
						arraylist_shape.add(arraylist_shape.get(move_shape_num));
						arraylist_shape.remove(move_shape_num);//���� �� ����� ���ο�� ���� , �̷����ؾ� �迭 �ǵڿ� �����
						select_status = false;
						repaint();
					}
					
					if(Rec_Bt.get_Rec_Bt_status() == 1) {
						Rec rec = new Rec(startX,startY,e.getX() - startX,e.getY() - startY);
						rec.set_color(select_color);
						size = Integer.parseInt(thicknessControl_tf.getText());
						rec.set_size(size);
						arraylist_shape.add(rec);
						Rec_Bt.set_Rec_Bt_status(0);
						repaint();
					}
					
					released_count = 0;//�ٽ� 0����
					repaint();
				}
			}
			private JTextArea t;
			@Override
			public void mousePressed(MouseEvent e) {
				
				startX = e.getX();
				startY = e.getY();
				
				
				if(Red_Bt.get_Red_Bt_status() == 1) select_color = Color.red;
				if(Black_Bt.get_Black_Bt_status() == 1) select_color = Color.BLACK;
				if(Blue_Bt.get_Blue_Bt()==1)select_color = Color.BLUE;
				
				if(select == 0 || select == 5) {
					endX = e.getX();
					endY = e.getY();
				}
				
				if(select == 6) {
					if(e.getButton()==MouseEvent.BUTTON3) {
						t.setEditable(false);
						Border border = BorderFactory.createEmptyBorder();
						t.setBorder(border);
					}
				}
				/////////////////////  �� ��  �� ��, ũ �� �� ��  /////////////////////////
				if(select_status) {//���� ��ü�� ���õǾ��ִ� �����ΰ�?
					if(!select_rec.is_in(e.getX(), e.getY())) {//�ٱ� �׵θ� ���� ������ ��������!!!!
						select_status = false;
						repaint();
					}
					// if ( select_rect �� �𼭸� �κ� ����?) > select_status = false 
					// change status = true >ũ�� ���� > paintComponent���� �׸��� > released�Ҷ� ���� 
					if(e.getX()>select_rec.get_x() && e.getX()<select_rec.get_x()+15) {
						if((e.getY()>select_rec.get_y()) && (e.getY() < select_rec.get_y()+15)){
							//���� �� �𼭸� Ŭ���ߴ°�?
							select_status = false;
							change_status = 1;
							
						}
						if( ( e.getY() < select_rec.get_y()+select_rec.get_height() )  
								&& (e.getY()>select_rec.get_y()+select_rec.get_height()-15)) {
							//���� �Ʒ� �𼭸� Ŭ���ߴ°�?
							select_status = false;
							change_status = 2;
						
							
						}
					}
					if((e.getX()<select_rec.get_x()+select_rec.get_width())&&
							(e.getX() > select_rec.get_x()+ select_rec.get_width()-15)){
						if((e.getY()>select_rec.get_y()) && (e.getY() < select_rec.get_y()+15)) {
							select_status = false;
							change_status = 3;
						}
						
					}
					if((e.getX()<select_rec.get_x()+select_rec.get_width())&&
							(e.getX() > select_rec.get_x()+ select_rec.get_width()-15)){
						if( ( e.getY() < select_rec.get_y()+select_rec.get_height() )  
								&& (e.getY()>select_rec.get_y()+select_rec.get_height()-15)) {
							select_status = false;
							change_status =4;
						}
					}
				}
				//////////////////////////// ����  ���� ////////////////////////////////////////////////
				if(Move_Bt.get_Move_Bt()==1) {//���� �̵� ��ư�� ���õǾ��°�?
					
					for(int i = arraylist_shape.size()-1; i>=0; i-- ) {
						
						if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//������ ���õǾ���?
							
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Rec")) {//�簢���ΰ�?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//������ ��ü�� �迭�� ����ִ��� ����ϰڴ�
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
								//break�ؾ� ��ġ�� ���� Ŭ�������� �� ���� �͸� ���õ�.
								}
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Line")) {//���ΰ�?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//������ ��ü�� �迭�� ����ִ��� ����ϰڴ�
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
							}
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Oval")) {//���ΰ�?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//������ ��ü�� �迭�� ����ִ��� ����ϰڴ�
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
							}
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Triangle")) {//���ΰ�?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//������ ��ü�� �迭�� ����ִ��� ����ϰڴ�
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
							}
						}

					}
					
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				///////////////////////////   �� ä���   /////////////////////////////////////////////
				if(Fill_Bt.get_fill_status()==1) {//��ä��� ��ư�� ���õǾ��°�?
					
					for(int i = arraylist_shape.size()-1; i>=0; i-- ) {
						
						if(arraylist_shape.get(i).getClass().getName().equals("shape.Rec")) {//�簢���̳�?
							if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//���õȰ��� �������̳�?
								arraylist_shape.get(i).set_fillcolor(select_color);
								arraylist_shape.get(i).set_fill_status(1);
								arraylist_shape.add(arraylist_shape.get(i));//����Ʈ �ǵڿ� �ְ�
								arraylist_shape.remove(i);//���� ���� ����, �̷����ϸ� �׷����� �Ǿտ� �׷���
								//�簢�� ��ü�� ��ä��� ���¸� 1�� ����
								Fill_Bt.set_fill_status(0);
								//��ư ������ 0���κ���
								break;//break�ؾ� ��ġ�� ���� Ŭ�������� �� ���� �͸� ��ĥ��.
							}
						}
						if(arraylist_shape.get(i).getClass().getName().equals("shape.Oval")) {//���̳�?
							if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//���õȰ��� �������̳�?
								arraylist_shape.get(i).set_fillcolor(select_color);
								arraylist_shape.get(i).set_fill_status(1);
								arraylist_shape.add(arraylist_shape.get(i));//����Ʈ �ǵڿ� �ְ�
								arraylist_shape.remove(i);//���� ���� ����, �̷����ϸ� �׷����� �Ǿտ� �׷���
								//�簢�� ��ü�� ��ä��� ���¸� 1�� ����
								Fill_Bt.set_fill_status(0);
								//��ư ������ 0���κ���
								break;//break�ؾ� ��ġ�� ���� Ŭ�������� �� ���� �͸� ��ĥ��.
							}
						}
						if(arraylist_shape.get(i).getClass().getName().equals("shape.Triangle")) {//�ﰢ�̳�?
							if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//���õȰ��� �������̳�?
								arraylist_shape.get(i).set_fillcolor(select_color);
								arraylist_shape.get(i).set_fill_status(1);
								arraylist_shape.add(arraylist_shape.get(i));//����Ʈ �ǵڿ� �ְ�
								arraylist_shape.remove(i);//���� ���� ����, �̷����ϸ� �׷����� �Ǿտ� �׷���
								//�簢�� ��ü�� ��ä��� ���¸� 1�� ����
								Fill_Bt.set_fill_status(0);
								//��ư ������ 0���κ���
								break;//break�ؾ� ��ġ�� ���� Ŭ�������� �� ���� �͸� ��ĥ��.
							}
						}
						
					}
					
					repaint();//for���� ������ repaint �ؾ���.
				}
					
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				released_count = 1;
				
				repaint();
				if(select==0) {
					startX = endX;
					startY = endY;
					endX=e.getX();
					endY=e.getY();
					Line line = new Line(startX,startY);
					line.set_width(endX-startX);
					line.set_height(endY-startY);
					line.set_color(Pencil_Bt.get_color());
					size = Integer.parseInt(thicknessControl_tf.getText());
					line.set_size(size);
					arraylist_shape.add(line);
					
					
				}else if(select == 5) {
					startX = endX;
					startY = endY;
					endX=e.getX();
					endY=e.getY();
					Line line = new Line(startX,startY);
					line.set_width(endX-startX);
					line.set_height(endY-startY);
					line.set_color(Color.WHITE);
					size = Integer.parseInt(thicknessControl_tf.getText());
					line.set_size(size);
					arraylist_shape.add(line);
					
				}else {
					endX = e.getX();
					endY = e.getY();
				}
				if(Rec_Bt.get_Rec_Bt_status() == 1) {
					endX= e.getX();
					endY= e.getY();
					repaint();
				}
				if(select_status) {
					endX= e.getX();
					endY= e.getY();
					repaint();
				}
				if(change_status>0) {
					endX= e.getX();
					endY= e.getY();
					repaint();
				}
			}
		});
	}
	////// �� /////
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(select_status) {//�����̵� ��ư�� ���õǾ��� ���� ��ü�� ���õȻ����ΰ�?
			//�µθ��� �׸���
			int x = arraylist_shape.get(move_shape_num).get_x();
			int y = arraylist_shape.get(move_shape_num).get_y();
			int width = arraylist_shape.get(move_shape_num).get_width();
			int height = arraylist_shape.get(move_shape_num).get_height();
			int size = arraylist_shape.get(move_shape_num).get_size();
			g2.setColor(Color.black);
			select_rec = new Rec(x-(size+20), y-(size+20), width+2*(size+20), height+2*(size+20));
			select_rec.draw(g2);
			
			
			//�̵�
			move_x = x+(endX-x);
			move_y = y+(endY-y);
			arraylist_shape.get(move_shape_num).set_x(move_x);
			arraylist_shape.get(move_shape_num).set_y(move_y);
			arraylist_shape.get(move_shape_num).draw(g);
			
		}
		if(change_status==1) {//��ü�� �������� �� ����� �簢���� �׵θ��� ���� �� �𼭸��� �����ߴ°�?
			int x = select_rec.get_x();
			int y = select_rec.get_y();
			int width = select_rec.get_width();
			int height = select_rec.get_height();
			select_rec.set_x(endX);
			select_rec.set_y(endY);
			select_rec.set_width(x-endX+width);
			select_rec.set_height(y-endY+height);
			g2.setColor(Color.black);
			select_rec.draw(g);
			
			
			arraylist_shape.get(move_shape_num).set_x(x+30);
			arraylist_shape.get(move_shape_num).set_y(y+30);
			arraylist_shape.get(move_shape_num).set_width(x-endX+width-60);
			arraylist_shape.get(move_shape_num).set_height(y-endY+height-60);
			arraylist_shape.get(move_shape_num).draw(g);
			
		}
		if(change_status==2) {//���� �Ʒ� �𼭸��� �����ߴ°�?
			int x = select_rec.get_x();
			int y = select_rec.get_y();
			int width = select_rec.get_width();
			select_rec.set_x(endX);
			select_rec.set_width(x-endX+width);
			select_rec.set_height(endY-y);
			g2.setColor(Color.black);
			select_rec.draw(g);
			
			arraylist_shape.get(move_shape_num).set_x(x+30);
			arraylist_shape.get(move_shape_num).set_y(y+30);
			arraylist_shape.get(move_shape_num).set_width(x-endX+width-60);
			arraylist_shape.get(move_shape_num).set_height(endY-y-60);
			arraylist_shape.get(move_shape_num).draw(g);
		}
		if(change_status==3) {//������ �� �𼭸��� �����ߴ°�?
			int x = select_rec.get_x();
			int y = select_rec.get_y();
			int height = select_rec.get_height();
			select_rec.set_y(endY);
			select_rec.set_width(endX-x);
			select_rec.set_height(y-endY+height);
			g2.setColor(Color.black);
			select_rec.draw(g);
			
			arraylist_shape.get(move_shape_num).set_x(x+30);
			arraylist_shape.get(move_shape_num).set_y(y+30);
			arraylist_shape.get(move_shape_num).set_width(endX-x-60);
			arraylist_shape.get(move_shape_num).set_height(y-endY+height-60);
			arraylist_shape.get(move_shape_num).draw(g);
			
		}
		if(change_status == 4) {
			int x = select_rec.get_x();
			int y = select_rec.get_y();
			select_rec.set_width(endX-x);
			select_rec.set_height(endY-y);
			g2.setColor(Color.black);
			select_rec.draw(g);
			
			arraylist_shape.get(move_shape_num).set_x(x+30);
			arraylist_shape.get(move_shape_num).set_y(y+30);
			arraylist_shape.get(move_shape_num).set_width(endX-x-60);
			arraylist_shape.get(move_shape_num).set_height(endY-y-60);
			arraylist_shape.get(move_shape_num).draw(g);
		}
		
		if(Rec_Bt.get_Rec_Bt_status()==1) {//�簢��
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND,0));
			g2.setColor(select_color);
			g2.drawRect(startX, startY, endX-startX, endY-startY);
		}
		if(select == 1) {//���׸���
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(select_color);
			g2.drawOval(startX,startY,endX-startX,endY-startY);
		} 
		if(select == 2) {//�ﰢ��
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			int xP[] = {startX  + ((endX-startX)/2), startX, endX};
			int yP[] = {startY, endY, endY};
			g2.setColor(select_color);
			g2.drawPolygon(xP, yP, 3);
		}
		if(select == 3) {//��
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(select_color);
			g2.drawLine(startX, startY, endX, endY);
		}
		if(select ==0) {//��
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(Pencil_Bt.get_color());
			g2.drawLine(startX, startY, endX, endY);
			
		}
		if(select ==5) {//���찳
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(Color.WHITE);
			g2.drawLine(startX, startY, endX, endY);
			
		}
		for(int i = 0 ; i<arraylist_shape.size();i++) {
			arraylist_shape.get(i).draw(g);
		}
		
	}

}