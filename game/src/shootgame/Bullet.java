package shootgame;

import java.awt.Graphics;
import java.awt.Image;

/*
 * �ӵ���
 *    ��Ͷ���ģ�ߺ�ʳ��Ĺ�ϵ
 * ���е���ͨ��������Ϊ˽������
 */
public class Bullet {
  private Image img;
  private int x,y;
 private int bspeed;
  public Bullet(Image img,int x,int y,int bspeed) {
	  this.img=img;
	  this.x=x;
	  this.y=y;
	  this.bspeed=bspeed;
	  
  }
  


//���ӵ��ķ��� g ����
  public void paintBullet(Graphics g) {
	g.drawImage(img,x,y,null);  
  }
  
  //�ӵ��ƶ�����
  public void bulletMove() {
	  y-=bspeed;
	  
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


public int getSpeed() {
	return bspeed;
}


public void setSpeed(int speed) {
	this.bspeed = speed;
}

  
}
