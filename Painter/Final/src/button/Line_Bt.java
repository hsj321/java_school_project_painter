package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import panel.Paint_panel;

public class Line_Bt extends JButton{
	/**
	 * 
	 */
	private static int Line_Bt_status = 0; // 0 선택안된 상태 1 선택된 상태
	public Line_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("선 그리기");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("함초롱체",Font.BOLD,20));
		setBounds(0, 0, 65, 60);
		
		//익명 클래스
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				if(Line_Bt_status == 0) {
					//Oval_Bt_status = 1;
					Paint_Panel.shapeN = 3;
				}
				else Line_Bt_status = 0;
				*/
				Paint_panel.select = 3; 
				Rec_Bt.set_Rec_Bt_status(0);
				Fill_Bt.set_fill_status(0);
			}
		});
	}
	public static void set_Rec_Bt_status(int a) {
		Line_Bt_status = a;
	}
	public static int get_Rec_Bt_status() {
		return Line_Bt_status;
	}
}
