package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class Red_Bt extends JButton{
	private static int Red_Bt_status = 0;//0은 안눌러진 상태
	public Red_Bt (Icon icon) {
		setIcon(icon);
		setToolTipText("빨간색");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("함초롱체",Font.BOLD,20));
		setBounds(0, 61, 65, 60);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Red_Bt_status == 1) Red_Bt_status = 0;
				else {
					Red_Bt_status = 1;//빨간버튼만 1 나머진 다 0
					Black_Bt.set_Black_Bt(0);
					Blue_Bt.set_Blue_Bt(0);
				}
				
			}
		});
	}
	public static int get_Red_Bt_status() {
		return Red_Bt_status;
	}
	public static void set_Red_Bt(int a) {
		Red_Bt_status = a;
	}
}
