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
	// 그려진 도형을 저장할 배열
	ArrayList<Shape> arraylist_shape = new ArrayList<Shape>();
	
	private Color select_color;
	JTextField thicknessControl_tf;//굵기 텍스트 필드
	private int size;//굵기를 저장할 변수
	
	//도형이동
	private boolean select_status;//도형이 선택되어있는 상태인가?
	private int change_status;// 도형을 늘리거나 줄이기할 상태인가? 1은 왼쪽 위 모서리 2 는 오른쪽 위 모서리 3은 아래 왼 모서리 4는 아래 오른 모서리
	private int move_shape_num;//선택한 도형이 배열의 몇번째 원소인지 저장할 변수
	private int move_x;// 이동된 객체의 startX , startY
	private int move_y;
	// 도형 선택했을때 그려지는 사각형
	private Rec select_rec ;
	
	
	public Paint_panel() {
		//paint panel 세팅
		setLayout(null);
		setBackground(Color.white);
		setBounds(135, 0, 800, 900);
		
		thicknessControl_tf = new JTextField("10", 4); // 도구굵기 입력 텍스트필드 생성
        thicknessControl_tf.setHorizontalAlignment(JTextField.CENTER); // 텍스트 중앙 정렬
        thicknessControl_tf.setFont(new Font("궁서체", Font.PLAIN, 20)); // 텍스트필드 글크기 및 폰트 , 굵기
        thicknessControl_tf.setBounds(0,0,50,35); // 굵기 텍스트필드 위치 지정
        thicknessControl_tf.setBackground(Color.GRAY);
        thicknessControl_tf.setToolTipText("굵기");
        
        
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
				if(select == 3) {//선
					Line line = new Line(startX,startY);
					line.set_width(e.getX() - startX);
					line.set_height(e.getY() - startY);
					line.set_color(select_color);
					size = Integer.parseInt(thicknessControl_tf.getText());
					line.set_size(size);
					arraylist_shape.add(line);
				}
				if(select == 2) {//삼각형
					Triangle tri = new Triangle(startX,startY);
					tri.set_width(e.getX() - startX);
					tri.set_height(e.getY() - startY);
					tri.set_color(select_color);
					size = Integer.parseInt(thicknessControl_tf.getText());
					tri.set_size(size);
					arraylist_shape.add(tri);
				}
				if(select == 1) {//원
					Oval oval = new Oval(startX,startY);
					oval.set_width(e.getX() - startX);
					oval.set_height(e.getY() - startY);
					oval.set_color(select_color);
					size = Integer.parseInt(thicknessControl_tf.getText());
					oval.set_size(size);
					arraylist_shape.add(oval);
				}
				if(released_count == 1) {//released_count는 드래그가 일어나면 1이됨 .
					if(select == 0 || select == 5) {
						
						repaint();
					}
					if(change_status>0) {
						change_status = 0;
						select_status = false;
						repaint();
					}
					if(select_status) {//이때는 도형을 선택된 상태에서 드래그 했다가 마우스를 놓은상태 (이동된 객체를 저장할것임)
						arraylist_shape.get(move_shape_num).set_x(e.getX());
						arraylist_shape.get(move_shape_num).set_y(e.getY());
						arraylist_shape.add(arraylist_shape.get(move_shape_num));
						arraylist_shape.remove(move_shape_num);//원래 꺼 지우고 새로운거 저장 , 이렇게해야 배열 맨뒤에 저장됨
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
					
					released_count = 0;//다시 0으로
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
				/////////////////////  도 형  이 동, 크 기 조 절  /////////////////////////
				if(select_status) {//도형 객체가 선택되어있는 상태인가?
					if(!select_rec.is_in(e.getX(), e.getY())) {//바깥 테두리 밖을 눌리면 선택해제!!!!
						select_status = false;
						repaint();
					}
					// if ( select_rect 의 모서리 부분 선택?) > select_status = false 
					// change status = true >크기 조절 > paintComponent에서 그리고 > released할때 저장 
					if(e.getX()>select_rec.get_x() && e.getX()<select_rec.get_x()+15) {
						if((e.getY()>select_rec.get_y()) && (e.getY() < select_rec.get_y()+15)){
							//왼쪽 위 모서리 클릭했는가?
							select_status = false;
							change_status = 1;
							
						}
						if( ( e.getY() < select_rec.get_y()+select_rec.get_height() )  
								&& (e.getY()>select_rec.get_y()+select_rec.get_height()-15)) {
							//왼쪽 아래 모서리 클릭했는가?
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
				//////////////////////////// 도형  선택 ////////////////////////////////////////////////
				if(Move_Bt.get_Move_Bt()==1) {//도형 이동 버튼이 선택되었는가?
					
					for(int i = arraylist_shape.size()-1; i>=0; i-- ) {
						
						if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//도형이 선택되었나?
							
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Rec")) {//사각형인가?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//선택한 객체가 배열에 어디있는지 기억하겠다
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
								//break해야 겹치는 도형 클릭했을때 맨 위에 것만 선택됨.
								}
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Line")) {//선인가?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//선택한 객체가 배열에 어디있는지 기억하겠다
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
							}
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Oval")) {//선인가?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//선택한 객체가 배열에 어디있는지 기억하겠다
								repaint();
								Move_Bt.set_Move_Bt(0);
								break;
							}
							if(arraylist_shape.get(i).getClass().getName().equals("shape.Triangle")) {//선인가?
								select_status = true;
								move_shape_num = i;
								endX = arraylist_shape.get(i).get_x();
								endY = arraylist_shape.get(i).get_y();
								//선택한 객체가 배열에 어디있는지 기억하겠다
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
				///////////////////////////   색 채우기   /////////////////////////////////////////////
				if(Fill_Bt.get_fill_status()==1) {//색채우기 버튼이 선택되었는가?
					
					for(int i = arraylist_shape.size()-1; i>=0; i-- ) {
						
						if(arraylist_shape.get(i).getClass().getName().equals("shape.Rec")) {//사각형이냐?
							if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//선택된곳이 도형안이냐?
								arraylist_shape.get(i).set_fillcolor(select_color);
								arraylist_shape.get(i).set_fill_status(1);
								arraylist_shape.add(arraylist_shape.get(i));//리스트 맨뒤에 넣고
								arraylist_shape.remove(i);//원래 것은 제거, 이렇게하면 그려질때 맨앞에 그려짐
								//사각형 객체의 색채우기 상태를 1로 변경
								Fill_Bt.set_fill_status(0);
								//버튼 선택을 0으로변경
								break;//break해야 겹치는 도형 클릭했을때 맨 위에 것만 색칠됨.
							}
						}
						if(arraylist_shape.get(i).getClass().getName().equals("shape.Oval")) {//원이냐?
							if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//선택된곳이 도형안이냐?
								arraylist_shape.get(i).set_fillcolor(select_color);
								arraylist_shape.get(i).set_fill_status(1);
								arraylist_shape.add(arraylist_shape.get(i));//리스트 맨뒤에 넣고
								arraylist_shape.remove(i);//원래 것은 제거, 이렇게하면 그려질때 맨앞에 그려짐
								//사각형 객체의 색채우기 상태를 1로 변경
								Fill_Bt.set_fill_status(0);
								//버튼 선택을 0으로변경
								break;//break해야 겹치는 도형 클릭했을때 맨 위에 것만 색칠됨.
							}
						}
						if(arraylist_shape.get(i).getClass().getName().equals("shape.Triangle")) {//삼각이냐?
							if(arraylist_shape.get(i).is_in(e.getX(), e.getY())){//선택된곳이 도형안이냐?
								arraylist_shape.get(i).set_fillcolor(select_color);
								arraylist_shape.get(i).set_fill_status(1);
								arraylist_shape.add(arraylist_shape.get(i));//리스트 맨뒤에 넣고
								arraylist_shape.remove(i);//원래 것은 제거, 이렇게하면 그려질때 맨앞에 그려짐
								//사각형 객체의 색채우기 상태를 1로 변경
								Fill_Bt.set_fill_status(0);
								//버튼 선택을 0으로변경
								break;//break해야 겹치는 도형 클릭했을때 맨 위에 것만 색칠됨.
							}
						}
						
					}
					
					repaint();//for문이 끝나고 repaint 해야함.
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
	////// 펜 /////
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(select_status) {//도형이동 버튼이 선택되었고 도형 객체가 선택된상태인가?
			//태두리를 그리고
			int x = arraylist_shape.get(move_shape_num).get_x();
			int y = arraylist_shape.get(move_shape_num).get_y();
			int width = arraylist_shape.get(move_shape_num).get_width();
			int height = arraylist_shape.get(move_shape_num).get_height();
			int size = arraylist_shape.get(move_shape_num).get_size();
			g2.setColor(Color.black);
			select_rec = new Rec(x-(size+20), y-(size+20), width+2*(size+20), height+2*(size+20));
			select_rec.draw(g2);
			
			
			//이동
			move_x = x+(endX-x);
			move_y = y+(endY-y);
			arraylist_shape.get(move_shape_num).set_x(move_x);
			arraylist_shape.get(move_shape_num).set_y(move_y);
			arraylist_shape.get(move_shape_num).draw(g);
			
		}
		if(change_status==1) {//객체를 선택했을 떄 생기는 사각형의 테두리에 왼쪽 위 모서리를 선택했는가?
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
		if(change_status==2) {//왼쪽 아래 모서리를 선택했는가?
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
		if(change_status==3) {//오른쪽 위 모서리를 선택했는가?
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
		
		if(Rec_Bt.get_Rec_Bt_status()==1) {//사각형
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND,0));
			g2.setColor(select_color);
			g2.drawRect(startX, startY, endX-startX, endY-startY);
		}
		if(select == 1) {//원그리기
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(select_color);
			g2.drawOval(startX,startY,endX-startX,endY-startY);
		} 
		if(select == 2) {//삼각형
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			int xP[] = {startX  + ((endX-startX)/2), startX, endX};
			int yP[] = {startY, endY, endY};
			g2.setColor(select_color);
			g2.drawPolygon(xP, yP, 3);
		}
		if(select == 3) {//선
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(select_color);
			g2.drawLine(startX, startY, endX, endY);
		}
		if(select ==0) {//펜
			size = Integer.parseInt(thicknessControl_tf.getText());
			g2.setStroke(new BasicStroke(size,BasicStroke.CAP_ROUND,0));
			g2.setColor(Pencil_Bt.get_color());
			g2.drawLine(startX, startY, endX, endY);
			
		}
		if(select ==5) {//지우개
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