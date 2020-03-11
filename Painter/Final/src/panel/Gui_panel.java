package panel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import button.Black_Bt;
import button.Blue_Bt;
import button.Erase_Bt;
import button.Fill_Bt;
import button.Line_Bt;
import button.Move_Bt;
import button.Oval_Bt;
import button.Pencil_Bt;
import button.Rec_Bt;
import button.Red_Bt;
import button.Text_Bt;
import button.Tri_Bt;


public class Gui_panel extends JPanel {
	
	public Gui_panel() {
		//Icon icon_rec = new ImageIcon(getClass().getResource("사각형그리기.png"));
		setLayout(null);
		setBackground(Color.GRAY);
		setBounds(0, 0, 135, 950);
		add(new Rec_Bt(new ImageIcon("사각형그리기.png")));
		add(new Fill_Bt(new ImageIcon("채우기.png")));
		add(new Red_Bt(new ImageIcon("빨강.png")));
		add(new Black_Bt(new ImageIcon("검정.png")));
		add(new Blue_Bt(new ImageIcon("파랑.png")));
		add(new Move_Bt(new ImageIcon("도형이동.png")));
		add(new Line_Bt(new ImageIcon("선그리기.png")));
		add(new Oval_Bt(new ImageIcon("원그리기.png")));
		add(new Tri_Bt(new ImageIcon("삼각형그리기.png")));
		add(new Pencil_Bt(new ImageIcon("펜.png")));
		add(new Erase_Bt(new ImageIcon("지우개.png")));
		add(new Text_Bt(new ImageIcon("텍스트.png")));
	}

}
