package shootgame;
/*
 * �л���
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class Elan {
   private Image img;
   private int x,y;
   //�������ֵ
   private int hp;
   //����ٶ�����
    int espeed;
   public Elan(Image img,int x,int y,int hp,int espeed) {
	   this.img=img;
	   this.x=x;
	   this.y=y;
	   this.hp=hp;
	   this.espeed=espeed;
  }

public int getEspeed() {
	return espeed;
}

public void setEspeed(int espeed) {
	this.espeed = espeed;
}

/*
    * ���л�
    */
 public void paintElan(Graphics g) {
	 g.drawImage(img, x, y, null);
 }
 
 public void ElanMove() {
	 y+=espeed;
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
public int getHp() {
	return hp;
}
public void setHp(int hp) {
	this.hp =hp;
}
}
