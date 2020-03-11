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
	private JMenuItem save, saveas, open, create; //filemenu�� ���
	private JMenuItem helpitem, makers; //helpmenu�� ���
	public static Gui_panel gp = new Gui_panel();
	public static Paint_panel pp = new Paint_panel();
	public Jframe01 () {
		super("�׸���");
		
		JMenuBar menubar = new JMenuBar();
		
		menubar.add(filemenu = new JMenu("����"));
		menubar.add(helpmenu = new JMenu("����"));
		setJMenuBar(menubar);
		
		filemenu.add(create = new JMenuItem("���� �����", new ImageIcon("new.png")));
		filemenu.add(open = new JMenuItem("����", new ImageIcon("open.png")));
		filemenu.addSeparator();
		filemenu.add(save = new JMenuItem("����", new ImageIcon("save.png")));
		filemenu.add(saveas = new JMenuItem("�ٸ� �̸����� ����", new ImageIcon("save_as.png")));
		
		setLayout(null);
		//�̺κ� ��������
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
					JOptionPane.showMessageDialog(null, "����");
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
		
		helpmenu.add(helpitem = new JMenuItem("����", new ImageIcon("help.png")));
		helpmenu.addSeparator();
		helpmenu.add(makers = new JMenuItem("����", new ImageIcon("makers.png")));
		
		helpitem.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null, "�� ���ڸ� �׸� �� �� ���� ���� ������ ���콺�� Ŭ���ϸ� �� ������ �Ұ����մϴ�.\n"
							+ "���� �׸���� �ѹ� �׸��� �����˴ϴ�. �ٽ� �׸��� ������ ��ư�� �ٽ� �����ּ��� :)\n");
				}
			}
		);
		makers.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null,"2014122299 ������\n"
							+ "2015125030 ������\n"+"2015125085 Ȳ����\n"+"2016125082 ȫ����");
				}
			}
		);
	}
}
