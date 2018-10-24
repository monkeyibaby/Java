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
	// 图片对象 设置为静态的 在整个游戏中只保存一份
	//定义四个游戏状态
	static int start=0;
	static int run=1;
	static int pause=2;
	static int game_over=3;
	//当前游戏的状态
	static int  state=0;
	

	static Image bgImg;// 背景图片
	static Image heroImg;// 英雄机图片
	static Image bulletImg;// 子弹图片
	static Image[] elanImg=new Image[5];
	static int hblood;//设置英雄机血量
	static Image blastImg;
	// 通过静态代码块为静态变量赋值

	static Image hpImg;	//生命值图片
    static Image startImg;//开始图片
    static Image pauseImg;//暂停图片
    static Image  game_overImg;//结束图片
	static {

		try {
			hblood=1;
			bgImg = ImageIO.read(new File("img/background/background_1.png"));
			heroImg = ImageIO.read(new File("img/1.png"));
			bulletImg = ImageIO.read(new File("img/bullet/23.png"));
			
			//给数组初始化
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
	
	
     //设定英雄机的生命值
	int  heroHp=10;
	
	// 定义背景图片的y坐标
	int bgY = -5400;

	// 定义英雄机的坐标
	int heroX = 200;
	int heroY = 200;
	
	// 线程执行的次数
	int count = 0;
	
	// 定义保存子弹的集合
	ArrayList<Bullet> bulletlist = new ArrayList<Bullet>();
	
    //定义保存敌机的集合
	ArrayList<Elan> Elanlist=new ArrayList<Elan>();
   //创建保存爆炸效果对象的集合
	ArrayList<Blast> blastList=new ArrayList<Blast>();
	
	// 画笔
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		// 设置背景色 用来测试GamePanel 和GameFrame
		this.setBackground(Color.black);
		
		
      if(state==start) {
    	  g.drawImage(startImg, 0, 0, null);
      }else if(state==run) {
    	 
      // 绘制图片
   		g.drawImage(bgImg, 0, bgY, null);
   		// 绘制英雄机图片
   		g.drawImage(heroImg, heroX, heroY, null);
   		// 画子弹-for循环遍历
   		
   		for (int i = 0; i < bulletlist.size(); i++) {
   			// 获取每一个子弹对象
   			Bullet bullet = bulletlist.get(i);
   			// 调用子弹类中自定义的画子弹的方法
   			bullet.paintBullet(g);
   		}
   		//画敌机
   		for (int i = 0; i < Elanlist.size(); i++) {
   			Elan e=Elanlist.get(i);
   			e.paintElan(g);
   		}
   		//画爆炸效果对象
   		for(int i=0;i<blastList.size();i++) {
   			Blast b=blastList.get(i);
   			b.paintBlast(g);
   		}
   		//画英雄机的生命值
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
       
       
	

	
	// 创建构造函数
	public GamePanel() {

		this.move();
		// 添加键盘事件之前要让当前程序获取键盘焦点
		// this.setFocusable(true);
		// 添加鼠标事件
		this.addMouseMotionListener(this);
		this.addMouseListener(this);

	}

	// 自定义一个移动
	public void move() {
		new Thread() {
			public void run() {
				while (true) {
					
				if(state==start) {
					//清除上一次数据
					heroHp=10;
					bgY=-5400;
					count=0;
					
					//清空集合
					bulletlist.removeAll(bulletlist);
					Elanlist.removeAll(Elanlist);
					blastList.removeAll(blastList);					
					
				}else if(state==run) {// 业务逻辑
					// 背景图片移动
					bgY++;
					count++;
					
					if (count > 10000)
						count = 0;
					
					// 背景图片循环播放
					if (bgY >= 0) {
						bgY = -5400;
					}
					
					// 控制英雄机子弹生成的速度
					if (count % 20 == 0) {
						// 子弹坐标
						int b_x = heroX + (heroImg.getWidth(null) - bulletImg.getWidth(null)) / 2;
						int b_y = heroY;
						

						// 添加英雄机子弹
					    int  bspeed=(int)(Math.random()*10);
					    
						Bullet bullet = new Bullet(bulletImg, b_x, b_y,bspeed);
						
						// 将子弹对象保存在集合中
						bulletlist.add(bullet);
					}
					
					
					// 让英雄机子弹移动
					for (int i = 0; i < bulletlist.size(); i++) {
						// 获取每一个子弹对象
						Bullet b = bulletlist.get(i);
						// 调用子弹自己的移动方法
						b.bulletMove();
						
                        if(b.getY()<-b.getImg().getHeight(null)) {
							bulletlist.remove(i);
						}
                        
                        
					}
					//英雄机子弹和敌机的碰撞
					action();
					//英雄机和敌机的碰撞
					H_EAction();
					//判定爆炸效果是否失效
					for(int i=0;i<blastList.size();i++) {
						//获取每一个爆炸效果对象
						Blast b=blastList.get(i);
						//判断该对象是否失效
						if(!b.isState()) {
							//爆炸对象失效  从集合中移除
							blastList.remove(i);
							
						}
					}
                    //System.out.println(bulletlist.size()+"==");
                    
                    if(count%30==0) {
                    	
                    //创建敌机
                    //生成一个获取图片的随机下标
                    int index=(int)(Math.random()*5);
                    
                    //生成x的随机坐标；
                    int x=new Random().nextInt(400-50);
                    int y=-50;
                    
                    //设置敌机的速度
                    Elan elan=new Elan(elanImg[index],x,y,(index+1),(index+2));
                    Elanlist.add(elan);
                    
                    }
                    
                    
                    
                    //敌机移动遍历
                    for (int i = 0; i < Elanlist.size(); i++) {
						//获取一个敌机对象
                    	Elan e=Elanlist.get(i);
						//调用敌机自己的移动方法
                    	e.ElanMove();
                    	//判断敌机是否越界
                    	if(e.getY()>600) {
                    	Elanlist.remove(i);
                    	}
					}
                    
                    
					
				}
				
					
					// 重绘
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
	
	
	//英雄机子弹和敌机的碰撞
	public void action() {
		//获取子弹
		for(int i=0;i<bulletlist.size();i++) {
			Bullet b=bulletlist.get(i);
			
			//获取敌机
			for(int j=0;j<Elanlist.size();j++) {
				 Elan e=Elanlist.get(j);
				 
				 //判断敌机和子弹是否发生碰撞
				 if(IsAction(b, e)) {
					 
					 //只要进入if语句,表明发生碰撞
					 //让敌机的生命值减一
					e.setHp(e.getHp()-1);
					
					if(e.getHp()<=0)	
					{ 
						//创建爆炸效果对象 爆炸效果的坐标是由敌机来提供的
						 Blast blast =new Blast(blastImg, e.getX(), e.getY());
						 //将创建的爆炸效果对象添加到集合中
						 blastList.add(blast);
						 
						//删除敌机
						 Elanlist.remove(j);
					} 
				
					//删除子弹
					 bulletlist.remove(i);
					
					 //跳出当前循环（内层循环） break/continue/return
					 break;
				 }
			}
		}
	}

	//单个子弹是否发生碰撞  如果碰撞  返回true  否则返回false
	public boolean IsAction(Bullet b,Elan e) {
	    //获取子弹中心点的坐标
		int b_x=b.getX()+b.getImg().getWidth(null)/2;
		int b_y=b.getY()+b.getImg().getHeight(null)/2; 
		
		//获取敌机中心点坐标
		int e_x=e.getX()+e.getImg().getWidth(null)/2;
		int e_y=e.getY()+e.getImg().getHeight(null)/2;
		
		//判断子弹中心点坐标和敌机中心点坐标的关系
		boolean f_x=Math.abs(b_x-e_x)<=(b.getImg().getWidth(null)+e.getImg().getWidth(null))/2;
		boolean f_y=Math.abs(b_y-e_y)<=(b.getImg().getHeight(null)+e.getImg().getHeight(null))/2;
		
	     return  f_x&&f_y;
	     }
	
	
	//英雄机和敌机是否发生碰撞
	public  boolean isH_EAction(Elan e) {
		//英雄机的中心点坐标
		int h_x=heroX+heroImg.getWidth(null)/2;
		int h_y=heroY+heroImg.getHeight(null)/2;
		
		//敌机中心点坐标
		int e_x=e.getX()+e.getImg().getWidth(null)/2;
		int e_y=e.getY()+e.getImg().getHeight(null)/2;
		
		boolean f_x=Math.abs(h_x-e_x)<=(heroImg.getWidth(null)+e.getImg().getWidth(null))/2;
		boolean f_y=Math.abs(h_y-e_y)<=(heroImg.getHeight(null)+e.getImg().getHeight(null))/2;
		
		return f_x&&f_y;
	}
	
	//英雄机和敌机碰撞处理
	public void H_EAction() {
		for(int i=0;i<Elanlist.size();i++) {
			//获取每一个敌机
			Elan e=Elanlist.get(i);
			
			//判断敌机和英雄机是否发生碰撞
			if(isH_EAction(e)) {
				//英雄机的生命值减一
				heroHp--;
				if(heroHp<=0) {
					state=game_over;
				}
				//添加爆炸效果
				Blast blast=new Blast(blastImg, e.getX(), e.getY());
				blastList.add(blast);
				
				//删除敌机
				Elanlist.remove(i);
				
			}
		}
	}
	
	
	
	

	// 鼠标拖拽
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("拖拽");

	}
	// 鼠标移动
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		// 使鼠标指向图片的正中间，鼠标的坐标由物理的鼠标的坐标来确定的;
		heroX = e.getX() - heroImg.getWidth(null) / 2;
		heroY = e.getY() - heroImg.getHeight(null) / 2;

	}





	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(state==start) {
			//鼠标点击时如果是开始状态 就变为运行状态
			state=run;
		}
		else if(state==pause) {
			//鼠标点击时如果状态为暂停 就变为运行状态
			state=run;
			
		}
		else if(state==game_over) {
		//鼠标点击时 如果是结束状态 就变为开始状态
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
			//鼠标从窗体中移出时，如果状态是运行 则变为暂停状态
			state=pause;
		}
		
	}
}