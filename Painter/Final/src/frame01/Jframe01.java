package frame01;

import javax.swing.JFrame;
import panel.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Jframe01 extends JFrame{
	private JMenu filemenu, helpmenu;
	private JMenuItem save, saveas, open, create; //filemenu의 요소
	private JMenuItem helpitem, makers; //helpmenu의 요소
	public static Gui_panel gp = new Gui_panel();
	public static Paint_panel pp = new Paint_panel();
	public Jframe01 () {
		super("그림판");
		
		JMenuBar menubar = new JMenuBar();
		
		menubar.add(filemenu = new JMenu("파일"));
		menubar.add(helpmenu = new JMenu("도움"));
		setJMenuBar(menubar);
		
		filemenu.add(create = new JMenuItem("새로 만들기", new ImageIcon("new.png")));
		filemenu.add(open = new JMenuItem("열기", new ImageIcon("open.png")));
		filemenu.addSeparator();
		filemenu.add(save = new JMenuItem("저장", new ImageIcon("save.png")));
		filemenu.add(saveas = new JMenuItem("다른 이름으로 저장", new ImageIcon("save_as.png")));
		
		setLayout(null);
		//이부분 수정했음
		add(gp);
		add(pp);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(990,990);
		setLocationRelativeTo(null);
		setVisible(true);
		
		create.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null, "생성");
				}
			}
		);
		open.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					PainterFile.Open();
				}
			}
		);
		save.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					PainterFile.Save();
				}
			}
		);
		saveas.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					PainterFile.checkForSave();
				}
			}
		);
		
		helpmenu.add(helpitem = new JMenuItem("도움말", new ImageIcon("help.png")));
		helpmenu.addSeparator();
		helpmenu.add(makers = new JMenuItem("제작", new ImageIcon("makers.png")));
		
		helpitem.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null, "글 상자를 그릴 때 글 상자 밖을 오른쪽 마우스로 클릭하면 글 수정이 불가능합니다.\n"
							+ "도형 그리기는 한번 그리면 해제됩니다. 다시 그리고 싶으면 버튼을 다시 눌러주세요 :)\n");
				}
			}
		);
		makers.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null,"2014122299 최정헌\n"
							+ "2015125030 도재준\n"+"2015125085 황보경\n"+"2016125082 홍순재");
				}
			}
		);
	}
}
