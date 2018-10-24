package shootgame;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	//���캯��  ����Ϸ�Ĵ��循�ѳ�ʼ��
	public GameFrame() {
		
		//��ȡ��Ļ�Ŀ��
		int sw=Toolkit.getDefaultToolkit().getScreenSize().width;
		int sh=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		//���崰��Ŀ��
		int fw=400;
		int fh=600;
		
		//���㴰�������
		int fx=(sw-fw)/2;
		int fy=(sh-fh)/2;
		
		//���ô���ı���
		this.setTitle("�ɻ���ս");
		
		//���ô���������С
		this.setBounds(fx,fy,fw,fh);
		
		//���ùرշ�ʽ
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//����һ����������
		GamePanel panel =new GamePanel();
		//��h����������ӵ�������
		
		this.add(panel);
		//���ÿɼ�
		this.setVisible(true);
		
	}


public static void main(String  arg[]) {
	new GameFrame();
	
}
}