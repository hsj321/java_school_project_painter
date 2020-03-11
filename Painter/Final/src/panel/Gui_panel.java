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
		//Icon icon_rec = new ImageIcon(getClass().getResource("�簢���׸���.png"));
		setLayout(null);
		setBackground(Color.GRAY);
		setBounds(0, 0, 135, 950);
		add(new Rec_Bt(new ImageIcon("�簢���׸���.png")));
		add(new Fill_Bt(new ImageIcon("ä���.png")));
		add(new Red_Bt(new ImageIcon("����.png")));
		add(new Black_Bt(new ImageIcon("����.png")));
		add(new Blue_Bt(new ImageIcon("�Ķ�.png")));
		add(new Move_Bt(new ImageIcon("�����̵�.png")));
		add(new Line_Bt(new ImageIcon("���׸���.png")));
		add(new Oval_Bt(new ImageIcon("���׸���.png")));
		add(new Tri_Bt(new ImageIcon("�ﰢ���׸���.png")));
		add(new Pencil_Bt(new ImageIcon("��.png")));
		add(new Erase_Bt(new ImageIcon("���찳.png")));
		add(new Text_Bt(new ImageIcon("�ؽ�Ʈ.png")));
	}

}
