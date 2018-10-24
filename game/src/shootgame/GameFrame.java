package shootgame;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	//构造函数  对游戏的窗体惊醒初始化
	public GameFrame() {
		
		//获取屏幕的宽高
		int sw=Toolkit.getDefaultToolkit().getScreenSize().width;
		int sh=Toolkit.getDefaultToolkit().getScreenSize().height;
		
		//定义窗体的宽高
		int fw=400;
		int fh=600;
		
		//计算窗体的坐标
		int fx=(sw-fw)/2;
		int fy=(sh-fh)/2;
		
		//设置窗体的标题
		this.setTitle("飞机大战");
		
		//设置窗体的坐标大小
		this.setBounds(fx,fy,fw,fh);
		
		//设置关闭方式
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//创建一个画布对象
		GamePanel panel =new GamePanel();
		//将h画布对象添加到画板上
		
		this.add(panel);
		//设置可见
		this.setVisible(true);
		
	}


public static void main(String  arg[]) {
	new GameFrame();
	
}
}