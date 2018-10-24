package shootgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.ImageGraphicAttribute;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.text.DefaultEditorKit.BeepAction;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

public class GamePanel extends JPanel implements MouseMotionListener ,MouseListener{
	// ͼƬ���� ����Ϊ��̬�� ��������Ϸ��ֻ����һ��
	//�����ĸ���Ϸ״̬
	static int start=0;
	static int run=1;
	static int pause=2;
	static int game_over=3;
	//��ǰ��Ϸ��״̬
	static int  state=0;
	

	static Image bgImg;// ����ͼƬ
	static Image heroImg;// Ӣ�ۻ�ͼƬ
	static Image bulletImg;// �ӵ�ͼƬ
	static Image[] elanImg=new Image[5];
	static int hblood;//����Ӣ�ۻ�Ѫ��
	static Image blastImg;
	// ͨ����̬�����Ϊ��̬������ֵ

	static Image hpImg;	//����ֵͼƬ
    static Image startImg;//��ʼͼƬ
    static Image pauseImg;//��ͣͼƬ
    static Image  game_overImg;//����ͼƬ
	static {

		try {
			hblood=1;
			bgImg = ImageIO.read(new File("img/background/background_1.png"));
			heroImg = ImageIO.read(new File("img/1.png"));
			bulletImg = ImageIO.read(new File("img/bullet/23.png"));
			
			//�������ʼ��
			for (int i = 0; i < elanImg.length; i++) {
				elanImg[i]=ImageIO.read(new File("Img/LittlePlane/plane"+(i+2)+".png"));
				
				}
			blastImg=ImageIO.read(new File("Img/bang1.png"));
			hpImg=ImageIO.read(new File("Img/award/award_1.png"));
			startImg=ImageIO.read(new File("Img/GameInterface/interface_1.png"));
			pauseImg=ImageIO.read(new File("Img/GameInterface/pause.png"));
			game_overImg=ImageIO.read(new File("Img/GameInterface/jeimian_2.png"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
     //�趨Ӣ�ۻ�������ֵ
	int  heroHp=10;
	
	// ���屳��ͼƬ��y����
	int bgY = -5400;

	// ����Ӣ�ۻ�������
	int heroX = 200;
	int heroY = 200;
	
	// �߳�ִ�еĴ���
	int count = 0;
	
	// ���屣���ӵ��ļ���
	ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();
	
    //���屣��л��ļ���
	ArrayList<Elan> Elanlist=new ArrayList<Elan>();
   //�������汬ըЧ������ļ���
	ArrayList<Blast> blastList=new ArrayList<Blast>();
	
	// ����
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		// ���ñ���ɫ ��������GamePanel ��GameFrame
		this.setBackground(Color.black);
		
		
      if(state==start) {
    	  g.drawImage(startImg, 0, 0, null);
      }else if(state==run) {
    	 
      // ����ͼƬ
   		g.drawImage(bgImg, 0, bgY, null);
   		// ����Ӣ�ۻ�ͼƬ
   		g.drawImage(heroImg, heroX, heroY, null);
   		// ���ӵ�-forѭ������
   		
   		for (int i = 0; i < bulletlist.size(); i++) {
   			// ��ȡÿһ���ӵ�����
   			Bullet bullet = bulletlist.get(i);
   			// �����ӵ������Զ���Ļ��ӵ��ķ���
   			bullet.paintBullet(g);
   		}
   		//���л�
   		for (int i = 0; i < Elanlist.size(); i++) {
   			Elan e=Elanlist.get(i);
   			e.paintElan(g);
   		}
   		//����ըЧ������
   		for(int i=0;i<blastList.size();i++) {
   			Blast b=blastList.get(i);
   			b.paintBlast(g);
   		}
   		//��Ӣ�ۻ�������ֵ
   		for(int i=0;i<heroHp;i++) {
   			g.drawImage(hpImg, i*hpImg.getWidth(null), 10,null);
   		}	
   		  }
	        else if(state==pause) {
   			g.drawImage(pauseImg,0 ,0 ,null);
   				
   		}else if (state==game_over) {
   			g.drawImage(game_overImg,0 ,0 ,null);
		}
		  }
       
       
	

	
	// �������캯��
	public GamePanel() {

		this.move();
		// ��Ӽ����¼�֮ǰҪ�õ�ǰ�����ȡ���̽���
		// this.setFocusable(true);
		// �������¼�
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

	}

	// �Զ���һ���ƶ�
	public void move() {
		new Thread() {
			public void run() {
				while (true) {
					
				if(state==start) {
					//�����һ������
					heroHp=10;
					bgY=-5400;
					count=0;
					
					//��ռ���
					bulletlist.removeAll(bulletlist);
					Elanlist.removeAll(Elanlist);
					blastList.removeAll(blastList);					
					
				}else if(state==run) {// ҵ���߼�
					// ����ͼƬ�ƶ�
					bgY++;
					count++;
					
					if (count > 10000)
						count = 0;
					
					// ����ͼƬѭ������
					if (bgY >= 0) {
						bgY = -5400;
					}
					
					// ����Ӣ�ۻ��ӵ����ɵ��ٶ�
					if (count % 20 == 0) {
						// �ӵ�����
						int b_x = heroX + (heroImg.getWidth(null) - bulletImg.getWidth(null)) / 2;
						int b_y = heroY;
						

						// ���Ӣ�ۻ��ӵ�
					    int  bspeed=(int)(Math.random()*10);
					    
						Bullet bullet = new Bullet(bulletImg, b_x, b_y,bspeed);
						
						// ���ӵ����󱣴��ڼ�����
						bulletlist.add(bullet);
					}
					
					
					// ��Ӣ�ۻ��ӵ��ƶ�
					for (int i = 0; i < bulletlist.size(); i++) {
						// ��ȡÿһ���ӵ�����
						Bullet b = bulletlist.get(i);
						// �����ӵ��Լ����ƶ�����
						b.bulletMove();
						
                        if(b.getY()<-b.getImg().getHeight(null)) {
							bulletlist.remove(i);
						}
                        
                        
					}
					//Ӣ�ۻ��ӵ��͵л�����ײ
					action();
					//Ӣ�ۻ��͵л�����ײ
					H_EAction();
					//�ж���ըЧ���Ƿ�ʧЧ
					for(int i=0;i<blastList.size();i++) {
						//��ȡÿһ����ըЧ������
						Blast b=blastList.get(i);
						//�жϸö����Ƿ�ʧЧ
						if(!b.isState()) {
							//��ը����ʧЧ  �Ӽ������Ƴ�
							blastList.remove(i);
							
						}
					}
                    //System.out.println(bulletlist.size()+"==");
                    
                    if(count%30==0) {
                    	
                    //�����л�
                    //����һ����ȡͼƬ������±�
                    int index=(int)(Math.random()*5);
                    
                    //����x��������ꣻ
                    int x=new Random().nextInt(400-50);
                    int y=-50;
                    
                    //���õл����ٶ�
                    Elan elan=new Elan(elanImg[index],x,y,(index+1),(index+2));
                    Elanlist.add(elan);
                    
                    }
                    
                    
                    
                    //�л��ƶ�����
                    for (int i = 0; i < Elanlist.size(); i++) {
						//��ȡһ���л�����
                    	Elan e=Elanlist.get(i);
						//���õл��Լ����ƶ�����
                    	e.ElanMove();
                    	//�жϵл��Ƿ�Խ��
                    	if(e.getY()>600) {
                    	Elanlist.remove(i);
                    	}
					}
                    
                    
					
				}
				
					
					// �ػ�
					repaint();
					try {
						Thread.sleep(20);

					} catch (InterruptedException e) {
						e.printStackTrace();
						// TODO: handle exception
					}
				}
			};

		}.start();

	}
	
	
	//Ӣ�ۻ��ӵ��͵л�����ײ
	public void action() {
		//��ȡ�ӵ�
		for(int i=0;i<bulletlist.size();i++) {
			Bullet b=bulletlist.get(i);
			
			//��ȡ�л�
			for(int j=0;j<Elanlist.size();j++) {
				 Elan e=Elanlist.get(j);
				 
				 //�жϵл����ӵ��Ƿ�����ײ
				 if(IsAction(b, e)) {
					 
					 //ֻҪ����if���,����������ײ
					 //�õл�������ֵ��һ
					e.setHp(e.getHp()-1);
					
					if(e.getHp()<=0)	
					{ 
						//������ըЧ������ ��ըЧ�����������ɵл����ṩ��
						 Blast blast =new Blast(blastImg, e.getX(), e.getY());
						 //�������ı�ըЧ��������ӵ�������
						 blastList.add(blast);
						 
						//ɾ���л�
						 Elanlist.remove(j);
					} 
				
					//ɾ���ӵ�
					 bulletlist.remove(i);
					
					 //������ǰѭ�����ڲ�ѭ���� break/continue/return
					 break;
				 }
			}
		}
	}

	//�����ӵ��Ƿ�����ײ  �����ײ  ����true  ���򷵻�false
	public boolean IsAction(Bullet b,Elan e) {
	    //��ȡ�ӵ����ĵ������
		int b_x=b.getX()+b.getImg().getWidth(null)/2;
		int b_y=b.getY()+b.getImg().getHeight(null)/2; 
		
		//��ȡ�л����ĵ�����
		int e_x=e.getX()+e.getImg().getWidth(null)/2;
		int e_y=e.getY()+e.getImg().getHeight(null)/2;
		
		//�ж��ӵ����ĵ�����͵л����ĵ�����Ĺ�ϵ
		boolean f_x=Math.abs(b_x-e_x)<=(b.getImg().getWidth(null)+e.getImg().getWidth(null))/2;
		boolean f_y=Math.abs(b_y-e_y)<=(b.getImg().getHeight(null)+e.getImg().getHeight(null))/2;
		
	     return  f_x&&f_y;
	     }
	
	
	//Ӣ�ۻ��͵л��Ƿ�����ײ
	public  boolean isH_EAction(Elan e) {
		//Ӣ�ۻ������ĵ�����
		int h_x=heroX+heroImg.getWidth(null)/2;
		int h_y=heroY+heroImg.getHeight(null)/2;
		
		//�л����ĵ�����
		int e_x=e.getX()+e.getImg().getWidth(null)/2;
		int e_y=e.getY()+e.getImg().getHeight(null)/2;
		
		boolean f_x=Math.abs(h_x-e_x)<=(heroImg.getWidth(null)+e.getImg().getWidth(null))/2;
		boolean f_y=Math.abs(h_y-e_y)<=(heroImg.getHeight(null)+e.getImg().getHeight(null))/2;
		
		return f_x&&f_y;
	}
	
	//Ӣ�ۻ��͵л���ײ����
	public void H_EAction() {
		for(int i=0;i<Elanlist.size();i++) {
			//��ȡÿһ���л�
			Elan e=Elanlist.get(i);
			
			//�жϵл���Ӣ�ۻ��Ƿ�����ײ
			if(isH_EAction(e)) {
				//Ӣ�ۻ�������ֵ��һ
				heroHp--;
				if(heroHp<=0) {
					state=game_over;
				}
				//��ӱ�ըЧ��
				Blast blast=new Blast(blastImg, e.getX(), e.getY());
				blastList.add(blast);
				
				//ɾ���л�
				Elanlist.remove(i);
				
			}
		}
	}
	
	
	
	

	// �����ק
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("��ק");

	}
	// ����ƶ�
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		// ʹ���ָ��ͼƬ�����м䣬�������������������������ȷ����;
		heroX = e.getX() - heroImg.getWidth(null) / 2;
		heroY = e.getY() - heroImg.getHeight(null) / 2;

	}





	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==start) {
			//�����ʱ����ǿ�ʼ״̬ �ͱ�Ϊ����״̬
			state=run;
		}
		else if(state==pause) {
			//�����ʱ���״̬Ϊ��ͣ �ͱ�Ϊ����״̬
			state=run;
			
		}
		else if(state==game_over) {
		//�����ʱ ����ǽ���״̬ �ͱ�Ϊ��ʼ״̬
			state=start;
		}
	}





	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==run) {
			//���Ӵ������Ƴ�ʱ�����״̬������ ���Ϊ��ͣ״̬
			state=pause;
		}
		
	}
}