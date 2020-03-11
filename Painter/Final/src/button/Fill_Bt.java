package button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import panel.Paint_panel;

public class Fill_Bt extends JButton {
	private static int fill_status = 0;//0�̸� ��ä��� ���� �� �� ����  1�̸� ���� �� ����
	
	public Fill_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("�� ä���");
		setBackground(Color.LIGHT_GRAY);
		setBounds(66, 0, 65, 60);
		
		addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				//�� ��ư Ŭ���Ǹ� �ٸ� ��ư ��������
				Move_Bt.set_Move_Bt(0);
				Rec_Bt.set_Rec_Bt_status(0);
				Paint_panel.select = -1;
				if(fill_status == 0) fill_status = 1;
				else fill_status = 0;
				
			}
		});
	}
	public static int get_fill_status () {
		return fill_status;
	}
	public static void set_fill_status(int a) {
		fill_status = a;
	}
}