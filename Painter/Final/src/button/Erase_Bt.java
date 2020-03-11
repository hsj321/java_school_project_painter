package button;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import panel.Paint_panel;

public class Erase_Bt extends JButton{
   private static Color mycolor = Color.white;
   public Erase_Bt(Icon icon) {
      setIcon(icon);
      setToolTipText("Áö¿ì°³");
      setBackground(Color.LIGHT_GRAY);
      setBounds(0, 304, 65, 60);
      
      addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            Paint_panel.select = 5;
            Move_Bt.set_Move_Bt(0);
            Rec_Bt.set_Rec_Bt_status(0);
            Fill_Bt.set_fill_status(0);
         }
      });
   }
   public static Color give_color() {
      return mycolor;
   }

}