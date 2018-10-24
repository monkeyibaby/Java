package shootgame;
//爆炸效果类

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Blast {
 private Image  img;
 private int x,y;
 //定义截取图片的辅助变量
 //爆炸效果的状态 true 有效状态   flase 无效状态
 private boolean state;
 
 private int dx;
 
 //计时器
 private  Timer  t;
 
 public Blast(Image img,int x,int y) {
	 this.img=img;
	 this.x=x;
	 this.y=y;
    //将爆炸效果设置为有效状态
	 state=true;
	 
	 dx=0;
	 //为计时器赋值
	 //第一个参数是毫秒值（每隔多长时间）
	 //第二个参数是执行任务
	 t=new Timer(80, new ActionListener() {
	 //计时器启动
	 public void actionPerformed(ActionEvent e) {
		 dx++;
		 if(dx > 7) {
			 //让爆炸对象变为无效状态
			 state=false;
			 t.stop();
			 }
		 }
	 });
	 t.start();
	 
 }
 
 //画爆炸效果的方法
 public void paintBlast(Graphics g) {
	 //前四个点就是确定图片在窗体上显示的位置（坐标《左上点的坐标》、宽高）
	 //后四个点就是确定图片在源文件中的位置
	 if(state)
	 g.drawImage(img, x, y,x+66,y+66, 66*dx, 0, 66*(dx+1), 66, null);
	 
	 
 }
 
 
 
public boolean isState() {
	return state;
}

public void setState(boolean state) {
	this.state = state;
}

public Image getImg() {
	return img;
}
public void setImg(Image img) {
	this.img = img;
}
public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
 
 
}
