package button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

import panel.Paint_panel;

public class Text_Bt extends JButton {
	public Text_Bt(Icon icon) {
		setIcon(icon);
	      setToolTipText("±Û »óÀÚ");
	      setBackground(Color.LIGHT_GRAY);
	      setBounds(66, 304, 65, 60);
	      
	      addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Paint_panel.select = 6;
	            Move_Bt.set_Move_Bt(0);
	            Rec_Bt.set_Rec_Bt_status(0);
	            Fill_Bt.set_fill_status(0);
				
			}
		});
	}
}
