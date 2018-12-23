import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
	/**
	 * �����˰ٶ�һ��С��
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		Game UI = new Game();
		UI.IntUI();
	}

	// ���ڴ�����ݵ�����
	private int Numbers[][] = new int[4][4];

	private void IntUI() {
		this.setTitle("2048С��Ϸ");
		this.setLocation(450, 100);// �O��λ��
		this.setSize(400, 500);// �O�ô����С
		this.setLayout(null);// ����ҳ�沼�֣�ΪĬ�ϲ��ַ�ʽ��
		ImageIcon imgicon = new ImageIcon("res/start.png");// ����Ϸ�DƬ����
		JButton bt = new JButton(imgicon);//����һ������--��ʼ��ť����
		bt.setFocusable(false);
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false);
		bt.setBounds(5, 10, 120, 30);// ���ð�ť��x��y����λ�úͿ����߶�
		this.add(bt);//����ť��ӵ����������;

		// ����һ����ť
		ImageIcon backicon = new ImageIcon("res/backicon.png");
		JButton back = new JButton(backicon);
		back.setFocusable(false);
		back.setBorderPainted(false);
		back.setFocusPainted(false);
		back.setContentAreaFilled(false);
		back.setBounds(270, 10, 120, 30);// ���ð�ť��x��y����λ�úͿ����߶�
		this.add(back);//����ť��ӵ����������;

		// ������ʾ
		JLabel lb = new JLabel("������0");
		lb.setBounds(40, 45, 120, 30);
		lb.setFont(new Font("��Բ", Font.CENTER_BASELINE, 18));//���������С������
		lb.setForeground(new Color(0x000000));//����������ɫ
		this.add(lb);//��ӱ�ǩ

		this.setDefaultCloseOperation(3);
		this.setResizable(false);
		this.setVisible(true);// ��ʾ����

		// �����¼�������
		MyListener cl = new MyListener(this, Numbers, lb, bt,  back);
		bt.addActionListener(cl);
		back.addActionListener(cl);
		this.addKeyListener(cl);

	}

	// ��д����
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0xBBADA0));//���û�����ɫ
		g.fillRoundRect(15, 110, 370, 370, 15, 15);// ����ο�

		g.setColor(new Color(0xCDC1B4));
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.fillRoundRect(25 + i * 90, 120 + j * 90, 80, 80, 15, 15);// С���ο�
			}
		}

		// �������ֵ�λ�ò���ɫ
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (Numbers[j][i] != 0) {
					int FontSize = 30;
					int MoveX = 0, MoveY = 0;
					switch (Numbers[j][i]) {
					case 2:
						g.setColor(new Color(0xeee4da));
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 4:
						g.setColor(new Color(0xede0c8));
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 8:
						g.setColor(new Color(0xf2b179));
						FontSize = 30;
						MoveX = 0;
						MoveY = 0;
						break;
					case 16:
						g.setColor(new Color(0xf59563));
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 32:
						g.setColor(new Color(0xf67c5f));
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 64:
						g.setColor(new Color(0xf65e3b));
						FontSize = 29;
						MoveX = -5;
						MoveY = 0;
						break;
					case 128:
						g.setColor(new Color(0xedcf72));
						FontSize = 28;
						MoveX = -10;
						MoveY = 0;
						break;
					case 256:
						g.setColor(new Color(0xedcc61));
						FontSize = 28;
						MoveX = -10;
						MoveY = 0;
						break;
					case 512:
						g.setColor(new Color(0xedc850));
						FontSize = 28;
						MoveX = -10;
						MoveY = 0;
						break;
					case 1024:
						g.setColor(new Color(0xedc53f));
						FontSize = 27;
						MoveX = -15;
						MoveY = 0;
						break;
					case 2048:
						g.setColor(new Color(0xedc22e));
						FontSize = 27;
						MoveX = -15;
						MoveY = 0;
						break;
					default:
						g.setColor(new Color(0x000000));
						break;
					}

					g.fillRoundRect(25 + i * 90, 120 + j * 90, 80, 80, 15, 15);// С���ο���ɫ
					g.setColor(new Color(0x000000));
					g.setFont(new Font("Kristen ITC", Font.PLAIN, FontSize));
					g.drawString(Numbers[j][i] + "", 25 + i * 90 + 30 + MoveX, 120 + j * 90 + 50 + MoveY);
				}
			}
		}
	}

}
