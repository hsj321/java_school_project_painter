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

	private static String fileName;		//파일명
	private static File saveFile = null;	//저장파일	
	private static boolean isNew = true;	//새로저장시 식별자

	public static void Open()
	{//파일 열기
		JFileChooser fileChooser = new JFileChooser();	//객체생성
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF image", "jpg","gif","png");	//포맷설정
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	//파일선택모드 설정
		fileChooser.setFileFilter(filter);	//필터 설정

		JLabel ImgBox;

		int result = fileChooser.showOpenDialog(null);	//창 오픈
  
		if(result == JFileChooser.CANCEL_OPTION)	//취소시 종료
			return;

		File file = fileChooser.getSelectedFile();	//선택된 파일
				
		ImageIcon icon = new ImageIcon(file.getAbsolutePath()); //파일을 imageicon화
		ImgBox = new JLabel(icon); //jlabel에 붙임
		ImgBox.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); //0,0좌표에 크기만큼 준비
				
		Jframe01.pp.add(ImgBox);	//그림 그려질 패널에 jlabel을 붙이기
		Jframe01.pp.repaint(); //다시 그리기
	}

	public static void Save()
	{//파일 저장
		if(isNew)
		{//새로저장시
			JFileChooser fileChooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("jpeg file", "jpg", "jpeg");//jpg포맷
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("gif file", "gif");//gif포맷
			FileNameExtensionFilter filter3 = new FileNameExtensionFilter("png file", "png");//png포맷
			fileChooser.addChoosableFileFilter(filter);//chooser에 추가
			fileChooser.addChoosableFileFilter(filter2);//chooser에 추가
			fileChooser.addChoosableFileFilter(filter3);//chooser에 추가
			fileChooser.setFileFilter(filter);//필터 설정
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//선택모드 설정

			int result = fileChooser.showSaveDialog(null); //창 오픈

			if(result == JFileChooser.CANCEL_OPTION)
			{
				return;//취소 선택시 종료
			}

			//파일 포맷 선택
			File temp = fileChooser.getSelectedFile();
			File file;
			if(filter == fileChooser.getFileFilter())//jpg이면
			{
				file = new File(temp.toString()+".jpg");//".jpg" 스트링 추가
			}
			else if(filter2 == fileChooser.getFileFilter())//gif이면
			{
				file = new File(temp.toString()+".gif");//".gif" 스트링 추가
			}
			else if(filter3 == fileChooser.getFileFilter())//gif이면

			{
				file = new File(temp.toString()+".png");//".gif" 스트링 추가
			}
			else
			{
				file = new File(temp.toString());
			}
			//예외처리.
			if((file == null) || (file.getName().equals("")))
			{//공백이면
				JOptionPane.showMessageDialog(null, "파일이름을 입력하지 않으셨습니다!", "파일이름을 입력하지 않으셨습니다!", JOptionPane.ERROR_MESSAGE);
			}
			else if(file.exists())
			{//같은 이름 존재시
				if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, saveFile.getPath() + "\n같은 이름의 파일이 이미 존재합니다.\n"
											+ "기존 파일을 이 파일로 대체하시겠습니까?", "다른 이름으로 저장", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))	 
				{
					return;		// No를 클릭하면 저장하지 않는다.
				}
			}
			saveFile = file; //파일경로
		}

		try //저장이나 다른 이름으로 저장이나 여기서 최종 작업
		{//엑세스 가능한 이미지 
			BufferedImage img = new BufferedImage(Jframe01.pp.getWidth(), Jframe01.pp.getHeight(), BufferedImage.TYPE_INT_RGB);//사이즈 설정및 RGB설정
			ImageIO.write(img, "jpg", saveFile); //저장. 윗줄과 더불어 핵심
			isNew = false;	//플래그 설정
		}
		catch (Exception e)
		{
			System.out.println(e);
		}			

		fileName = saveFile.getName();	//파일명
		PainterMain.jf.setTitle("현재 파일명 : " + fileName + " , 위치 : " + saveFile.getPath());//그림판 title 변경
	}

	public static int checkForSave()
	{//변경사항 저장
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "변경 사항을 저장하시겠습니까?",
				"파일 저장하기", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
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
	{//변경사항 저장 ; 새로만들기 선택시 호출
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "변경 사항을 저장하시겠습니까?",
				"파일 저장하기", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE))
		{
			Save();//저장
		}
		else
		{
			return;
		}
	}

	public static void setSaveFile(File _saveFile) { saveFile=_saveFile; } //savefile 설정
	public static File getSaveFile(){return saveFile;}//saveFile 리턴
	public static boolean getNew(){return isNew;} //새로저장여부 flag 리턴
	public static void setNew(boolean i){isNew = i;	} //새로저장 여부 flag 설정
}