package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import panel.Paint_panel;

public class Tri_Bt extends JButton{
	/**
	 * 
	 */
	public Tri_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("삼각형 그리기");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("함초롱체",Font.BOLD,20));
		setBounds(0, 244, 65, 60);
		
		//익명 클래스
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				if(Tri_Bt_status == 0) {
					//Tri_Bt_status = 1;
					Paint_Panel.shapeN = 2;
				}
				else Tri_Bt_status = 0;
				*/
				Paint_panel.select = 2; 
				Rec_Bt.set_Rec_Bt_status(0);
				Fill_Bt.set_fill_status(0);
			}
		});
	}
}
