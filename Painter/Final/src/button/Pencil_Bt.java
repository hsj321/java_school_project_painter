package button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;

import panel.Paint_panel;

public class Pencil_Bt extends JButton{
	private static Color mycolor;
	public Pencil_Bt(Icon icon) {
		setIcon(icon);
		setToolTipText("Ææ");
		setBackground(Color.LIGHT_GRAY);
		setBounds(66, 244, 65, 60);
		
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JColorChooser chooser = new JColorChooser(); 
	            mycolor = chooser.showDialog(null, "Color", Color.ORANGE); 
				Paint_panel.select = 0;
				Move_Bt.set_Move_Bt(0);
				Rec_Bt.set_Rec_Bt_status(0);
				Fill_Bt.set_fill_status(0);
			}
		});
	}
	public static Color get_color() {
	      return mycolor;
	   }
}
