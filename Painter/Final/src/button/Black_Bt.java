package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class Black_Bt extends JButton{
	private static int Black_Bt_status = 1;//0은 안눌러진 상태
	public Black_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("검은색");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("함초롱체",Font.BOLD,20));
		setBounds(66, 61, 65, 60);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Black_Bt_status == 1) {
					Black_Bt_status = 0;
				}
				else {
					
					Black_Bt_status = 1;
					Red_Bt.set_Red_Bt(0);
					Blue_Bt.set_Blue_Bt(0);
				}
			}
		});
		
	}
	public static int get_Black_Bt_status() {
		return Black_Bt_status;
	}
	public static void set_Black_Bt(int a) {
		Black_Bt_status = a;
	}
}
