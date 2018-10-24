package shootgame;
//��ըЧ����

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Blast {
 private Image  img;
 private int x,y;
 //�����ȡͼƬ�ĸ�������
 //��ըЧ����״̬ true ��Ч״̬   flase ��Ч״̬
 private boolean state;
 
 private int dx;
 
 //��ʱ��
 private  Timer  t;
 
 public Blast(Image img,int x,int y) {
	 this.img=img;
	 this.x=x;
	 this.y=y;
    //����ըЧ������Ϊ��Ч״̬
	 state=true;
	 
	 dx=0;
	 //Ϊ��ʱ����ֵ
	 //��һ�������Ǻ���ֵ��ÿ���೤ʱ�䣩
	 //�ڶ���������ִ������
	 t=new Timer(80, new ActionListener() {
	 //��ʱ������
	 public void actionPerformed(ActionEvent e) {
		 dx++;
		 if(dx > 7) {
			 //�ñ�ը�����Ϊ��Ч״̬
			 state=false;
			 t.stop();
			 }
		 }
	 });
	 t.start();
	 
 }
 
 //����ըЧ���ķ���
 public void paintBlast(Graphics g) {
	 //ǰ�ĸ������ȷ��ͼƬ�ڴ�������ʾ��λ�ã����꡶���ϵ�����꡷����ߣ�
	 //���ĸ������ȷ��ͼƬ��Դ�ļ��е�λ��
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
