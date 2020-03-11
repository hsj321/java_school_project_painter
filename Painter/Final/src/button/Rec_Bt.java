package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import panel.Paint_panel;

public class Rec_Bt extends JButton{
	/**
	 * 
	 */
	private static int Rec_Bt_status = 0; // 0 선택안된 상태 1 선택된 상태
	public Rec_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("사각형 그리기");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("함초롱체",Font.BOLD,20));
		setBounds(0, 183, 65, 60);
		
		//익명 클래스
		addActionListener(new ActionListener() {
			//이버튼이 클릭되면 다른 버튼 선택해제
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Move_Bt.set_Move_Bt(0);
				Fill_Bt.set_fill_status(0);
				Paint_panel.select = -1;
				if(Rec_Bt_status == 0) Rec_Bt_status = 1;
				else Rec_Bt_status = 0;
			}
		});
	}
	public static void set_Rec_Bt_status(int a) {
		Rec_Bt_status = a;
	}
	public static int get_Rec_Bt_status() {
		return Rec_Bt_status;
	}
}