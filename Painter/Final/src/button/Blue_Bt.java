package button;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

public class Blue_Bt extends JButton{
	private static int Blue_Bt_status = 0;
	public Blue_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("파란색");
		setBackground(Color.LIGHT_GRAY);
		setFont(new Font("함초롱체",Font.BOLD,20));
		setBounds(0, 122, 65, 60);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Blue_Bt_status == 1)Blue_Bt_status = 0;
				else {
					Blue_Bt_status = 1;
					Red_Bt.set_Red_Bt(0);
					Black_Bt.set_Black_Bt(0);
				}
			}
		});
	}
	public static int get_Blue_Bt() {
		return Blue_Bt_status;
	}
	public static void set_Blue_Bt(int a) {
		Blue_Bt_status = a;
	}
}

