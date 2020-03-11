package frame01;


import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PainterFile {

	private static String fileName;		//���ϸ�
	private static File saveFile = null;	//��������	
	private static boolean isNew = true;	//��������� �ĺ���

	public static void Open()
	{//���� ����
		JFileChooser fileChooser = new JFileChooser();	//��ü����
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF image", "jpg","gif","png");	//���˼���
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	//���ϼ��ø�� ����
		fileChooser.setFileFilter(filter);	//���� ����

		JLabel ImgBox;

		int result = fileChooser.showOpenDialog(null);	//â ����
  
		if(result == JFileChooser.CANCEL_OPTION)	//��ҽ� ����
			return;

		File file = fileChooser.getSelectedFile();	//���õ� ����
				
		ImageIcon icon = new ImageIcon(file.getAbsolutePath()); //������ imageiconȭ
		ImgBox = new JLabel(icon); //jlabel�� ����
		ImgBox.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); //0,0��ǥ�� ũ�⸸ŭ �غ�
				
		Jframe01.pp.add(ImgBox);	//�׸� �׷��� �гο� jlabel�� ���̱�
		Jframe01.pp.repaint(); //�ٽ� �׸���
	}

	public static void Save()
	{//���� ����
		if(isNew)
		{//���������
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg file", "jpg", "jpeg");//jpg����
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("gif file", "gif");//gif����
			FileNameExtensionFilter filter3 = new FileNameExtensionFilter("png file", "png");//png����
			fileChooser.addChoosableFileFilter(filter);//chooser�� �߰�
			fileChooser.addChoosableFileFilter(filter2);//chooser�� �߰�
			fileChooser.addChoosableFileFilter(filter3);//chooser�� �߰�
			fileChooser.setFileFilter(filter);//���� ����
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//���ø�� ����

			int result = fileChooser.showSaveDialog(null); //â ����

			if(result == JFileChooser.CANCEL_OPTION)
			{
				return;//��� ���ý� ����
			}

			//���� ���� ����
			File temp = fileChooser.getSelectedFile();
			File file;
			if(filter == fileChooser.getFileFilter())//jpg�̸�
			{
				file = new File(temp.toString()+".jpg");//".jpg" ��Ʈ�� �߰�
			}
			else if(filter2 == fileChooser.getFileFilter())//gif�̸�
			{
				file = new File(temp.toString()+".gif");//".gif" ��Ʈ�� �߰�
			}
			else if(filter3 == fileChooser.getFileFilter())//gif�̸�

			{
				file = new File(temp.toString()+".png");//".gif" ��Ʈ�� �߰�
			}
			else
			{
				file = new File(temp.toString());
			}
			//����ó��.
			if((file == null) || (file.getName().equals("")))
			{//�����̸�
				JOptionPane.showMessageDialog(null, "�����̸��� �Է����� �����̽��ϴ�!", "�����̸��� �Է����� �����̽��ϴ�!", JOptionPane.ERROR_MESSAGE);
			}
			else if(file.exists())
			{//���� �̸� �����
				if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, saveFile.getPath() + "\n���� �̸��� ������ �̹� �����մϴ�.\n"
											+ "���� ������ �� ���Ϸ� ��ü�Ͻðڽ��ϱ�?", "�ٸ� �̸����� ����", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))	 
				{
					return;		// No�� Ŭ���ϸ� �������� �ʴ´�.
				}
			}
			saveFile = file; //���ϰ��
		}

		try //�����̳� �ٸ� �̸����� �����̳� ���⼭ ���� �۾�
		{//������ ������ �̹��� 
			BufferedImage img = new BufferedImage(Jframe01.pp.getWidth(), Jframe01.pp.getHeight(), BufferedImage.TYPE_INT_RGB);//������ ������ RGB����
			ImageIO.write(img, "jpg", saveFile); //����. ���ٰ� ���Ҿ� �ٽ�
			isNew = false;	//�÷��� ����
		}
		catch (Exception e)
		{
			System.out.println(e);
		}			

		fileName = saveFile.getName();	//���ϸ�
		PainterMain.jf.setTitle("���� ���ϸ� : " + fileName + " , ��ġ : " + saveFile.getPath());//�׸��� title ����
	}

	public static int checkForSave()
	{//������� ����
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "���� ������ �����Ͻðڽ��ϱ�?",
				"���� �����ϱ�", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
			Save();
			return 1;
		}
		else
		{
			System.exit(0);
			return 0;
		}
	}

	public static void newCheckForSave()
	{//������� ���� ; ���θ���� ���ý� ȣ��
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "���� ������ �����Ͻðڽ��ϱ�?",
				"���� �����ϱ�", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
			Save();//����
		}
		else
		{
			return;
		}
	}

	public static void setSaveFile(File _saveFile) { saveFile=_saveFile; } //savefile ����
	public static File getSaveFile(){return saveFile;}//saveFile ����
	public static boolean getNew(){return isNew;} //�������忩�� flag ����
	public static void setNew(boolean i){isNew = i;	} //�������� ���� flag ����
}