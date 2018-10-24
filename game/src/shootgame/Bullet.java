package shootgame;

import java.awt.Graphics;
import java.awt.Image;

/*
 * 子弹类
 *    类和对象：模具和食物的关系
 * 类中的普通变量设置为私有类型
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
  


//画子弹的方法 g 画笔
  public void paintBullet(Graphics g) {
	g.drawImage(img,x,y,null);  
  }
  
  //子弹移动方法
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
