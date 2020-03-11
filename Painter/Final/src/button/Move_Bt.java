package button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import panel.Paint_panel;

public class Move_Bt extends JButton{
	private static int Move_Bt_status;
	public Move_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("도형 선택");
		setBackground(Color.LIGHT_GRAY);
		setBounds(66,122,65,60);
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Paint_panel.select = -1;
				Fill_Bt.set_fill_status(0);
				Rec_Bt.set_Rec_Bt_status(0);
				if (Move_Bt_status == 1) {
					Move_Bt_status = 0;
				}
				else Move_Bt_status = 1;
			}
		});
	}
	public static void set_Move_Bt(int a) {
		Move_Bt_status = a;
	}
	public static int get_Move_Bt() {
		return Move_Bt_status;
	}
}
