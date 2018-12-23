import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class MyListener extends KeyAdapter implements ActionListener {

	/**
	 * �ٶ�һ��С������
	 */
	private Game UI;// �������
	private int Numbers[][];// ������ݵ�����
	private Random rand = new Random();
	private int BackUp[][]= new int[4][4];//���ڱ������飬������ʱʹ��
	private int BackUp2[][]= new int[4][4];//���ڱ������飬����������ʱʹ��
	public JLabel lb;
	int score = 0;
	int tempscore,tempscore2;//��¼���˵ķ���ֵ
	public JButton bt,about,back;
	public JCheckBox isSoundBox;
	private boolean isWin=false,relive=false,hasBack=false,isSound=true;
	

    /*
     * ���캯�������г�ʼ��
     */
	public MyListener(Game UI, int Numbers[][], JLabel lb,JButton bt,JButton back) {
		this.UI = UI;
		this.Numbers = Numbers;
		this.lb = lb;
		this.bt=bt;
		this.about=about;
		this.back=back;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() ==bt ){
			isWin=false;
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				Numbers[i][j] = 0;

		score = 0;// ��֤ÿ��������Ϸ����0�ֿ�ʼ
		lb.setText("������" + score);
		/*
		 * �������λ������;
		 */
		//��һ����
		int r1 = rand.nextInt(4);
		int c1 = rand.nextInt(4);
	    
		//�ڶ�����
		int r2 = rand.nextInt(4);
		int c2 = rand.nextInt(4);

		while (r1 == r2 && c1 == c2) {
			r2 = rand.nextInt(4);
			c2 = rand.nextInt(4);
		}
        
		// �������֣�2����4��
		int value1 = rand.nextInt(2) * 2 + 2;
		int value2 = rand.nextInt(2) * 2 + 2;

		// �����ִ����Ӧ��λ��
		Numbers[r1][c1] = value1;
		Numbers[r2][c2] = value2;
		UI.paint(UI.getGraphics());
		}

		else if(e.getSource()==back&&hasBack==false){
			hasBack=true;
			if(relive==false){
			score=tempscore;
			lb.setText("������" + score);
			for(int i=0;i<BackUp.length;i++){
				Numbers[i]=Arrays.copyOf(BackUp[i], BackUp[i].length);
			}
			}
			else{
					score=tempscore2;
					lb.setText("������" + score);
					for(int i=0;i<BackUp2.length;i++){
						Numbers[i]=Arrays.copyOf(BackUp2[i], BackUp2[i].length);
					}
					relive=false;
			}
			UI.paint(UI.getGraphics());	
		}
		else if(e.getSource().equals(isSoundBox)){
			 if (isSoundBox.isSelected())
				 isSound=false;
		       else
		    	   isSound=true;
		    }
		}
	

	

	    
	// ���̼���
	public void keyPressed(KeyEvent event) {

		int Counter = 0;// ���������ж��Ƿ��ƶ���
		int NumCounter = 0;// ����ͳ�������󷽿������ֵĸ������ж��Ƿ�����
		int NumNearCounter = 0;// ����ͳ�����ڸ���������ͬ�ĸ���
		/*
		 * �������ֵ����37�ϣ�38�ң�39�£�40
		 */

		hasBack = false;

		if (BackUp != null || BackUp.length != 0) {
			tempscore2 = tempscore;// �Ȱѷ������ݺ�
			// �����forѭ������java.util.Arrays.copyOf()�����������飬ʵ�ֱ���
			for (int i = 0; i < BackUp.length; i++) {
				BackUp2[i] = Arrays.copyOf(BackUp[i], BackUp[i].length);
			}
		}

		tempscore = score;// �Ȱѷ������ݺ�
		// �����forѭ������java.util.Arrays.copyOf()�����������飬ʵ�ֱ���
		for (int i = 0; i < Numbers.length; i++) {
			BackUp[i] = Arrays.copyOf(Numbers[i], Numbers[i].length);
		}

		if (isWin == false) {
			switch (event.getKeyCode()) {

			case 37:
				// �����ƶ�
				if (isSound == true)
				for (int h = 0; h < 4; h++)
					for (int l = 0; l < 4; l++)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = l - 1;
							while (pre >= 0 && Numbers[h][pre] == 0) {
								Numbers[h][pre] = temp;
								Numbers[h][pre + 1] = 0;
								pre--;
								Counter++;
							}
						}//�ƶ�
				for (int h = 0; h < 4; h++)
					for (int l = 0; l < 4; l++)
						if (l + 1 < 4
								&& (Numbers[h][l] == Numbers[h][l + 1])
								&& (Numbers[h][l] != 0 || Numbers[h][l + 1] != 0)) {
							if (isSound == true)
							Numbers[h][l] = Numbers[h][l] + Numbers[h][l + 1];
							Numbers[h][l + 1] = 0;
							Counter++;
							score += Numbers[h][l];
							if (Numbers[h][l] == 2048) {
								isWin = true;
							}
						}
                        //�ж��Ƿ����Ȼ��ϲ�
				for (int h = 0; h < 4; h++)
					for (int l = 0; l < 4; l++)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = l - 1;
							while (pre >= 0 && Numbers[h][pre] == 0) {
								Numbers[h][pre] = temp;
								Numbers[h][pre + 1] = 0;
								pre--;
								Counter++;
							}
						}
				break;

			case 39:// �����ƶ�
				if (isSound == true)
				for (int h = 3; h >= 0; h--)
					for (int l = 3; l >= 0; l--)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = l + 1;
							while (pre <= 3 && Numbers[h][pre] == 0) {
								Numbers[h][pre] = temp;
								Numbers[h][pre - 1] = 0;
								pre++;
								Counter++;
							}
						}

				for (int h = 3; h >= 0; h--)
					for (int l = 3; l >= 0; l--)
						if (l + 1 < 4
								&& (Numbers[h][l] == Numbers[h][l + 1])
								&& (Numbers[h][l] != 0 || Numbers[h][l + 1] != 0)) {
							if (isSound == true)
								
							Numbers[h][l + 1] = Numbers[h][l]
									+ Numbers[h][l + 1];
							Numbers[h][l] = 0;
							Counter++;
							score += Numbers[h][l + 1];
							if (Numbers[h][l + 1] == 2048) {
								isWin = true;
							}
						}
				for (int h = 3; h >= 0; h--)
					for (int l = 3; l >= 0; l--)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = l + 1;
							while (pre <= 3 && Numbers[h][pre] == 0) {
								Numbers[h][pre] = temp;
								Numbers[h][pre - 1] = 0;
								pre++;
								Counter++;
							}
						}
				break;

			case 38:
				// �����ƶ�
				if (isSound == true)
					
				for (int l = 0; l < 4; l++)
					for (int h = 0; h < 4; h++)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = h - 1;
							while (pre >= 0 && Numbers[pre][l] == 0) {
								Numbers[pre][l] = temp;
								Numbers[pre + 1][l] = 0;
								pre--;
								Counter++;
							}
						}
				for (int l = 0; l < 4; l++)
					for (int h = 0; h < 4; h++)
						if (h + 1 < 4
								&& (Numbers[h][l] == Numbers[h + 1][l])
								&& (Numbers[h][l] != 0 || Numbers[h + 1][l] != 0)) {
							if (isSound == true)
							Numbers[h][l] = Numbers[h][l] + Numbers[h + 1][l];
							Numbers[h + 1][l] = 0;
							Counter++;
							score += Numbers[h][l];
							if (Numbers[h][l] == 2048) {
								isWin = true;
							}
						}

				for (int l = 0; l < 4; l++)
					for (int h = 0; h < 4; h++)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = h - 1;
							while (pre >= 0 && Numbers[pre][l] == 0) {
								Numbers[pre][l] = temp;
								Numbers[pre + 1][l] = 0;
								pre--;
								Counter++;
							}
						}
				break;

			case 40:
				// �����ƶ�
				if (isSound == true)
				for (int l = 3; l >= 0; l--)
					for (int h = 3; h >= 0; h--)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = h + 1;
							while (pre <= 3 && Numbers[pre][l] == 0) {
								Numbers[pre][l] = temp;
								Numbers[pre - 1][l] = 0;
								pre++;
								Counter++;
							}
						}
				for (int l = 3; l >= 0; l--)
					for (int h = 3; h >= 0; h--)
						if (h + 1 < 4
								&& (Numbers[h][l] == Numbers[h + 1][l])
								&& (Numbers[h][l] != 0 || Numbers[h + 1][l] != 0)) {
							if (isSound == true)
							Numbers[h + 1][l] = Numbers[h][l]
									+ Numbers[h + 1][l];
							Numbers[h][l] = 0;
							Counter++;
							score += Numbers[h + 1][l];
							if (Numbers[h + 1][l] == 2048) {
								isWin = true;
							}
						}

				for (int l = 3; l >= 0; l--)
					for (int h = 3; h >= 0; h--)
						if (Numbers[h][l] != 0) {
							int temp = Numbers[h][l];
							int pre = h + 1;
							while (pre <= 3 && Numbers[pre][l] == 0) {
								Numbers[pre][l] = temp;
								Numbers[pre - 1][l] = 0;
								pre++;
								Counter++;
							}
						}
				break;

			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (Numbers[i][j] == Numbers[i][j + 1]
							&& Numbers[i][j] != 0) {
						NumNearCounter++;
					}
					if (Numbers[i][j] == Numbers[i + 1][j]
							&& Numbers[i][j] != 0) {
						NumNearCounter++;
					}
					if (Numbers[3][j] == Numbers[3][j + 1]
							&& Numbers[3][j] != 0) {
						NumNearCounter++;
					}
					if (Numbers[i][3] == Numbers[i + 1][3]
							&& Numbers[i][3] != 0) {
						NumNearCounter++;
					}
				}
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (Numbers[i][j] != 0) {
						NumCounter++;
					}
				}
			}
			if (Counter > 0) {

				lb.setText("������" + score);
				int r1 = rand.nextInt(4);
				int c1 = rand.nextInt(4);
				while (Numbers[r1][c1] != 0) {
					r1 = rand.nextInt(4);
					c1 = rand.nextInt(4);
				}
				int value1 = rand.nextInt(2) * 2 + 2;
				Numbers[r1][c1] = value1;
			}

			if (isWin == true){
				UI.paint(UI.getGraphics());
				JOptionPane.showMessageDialog(UI, "��ϲ��Ӯ��!\n�������յ÷�Ϊ��" + score);
			}
				
			if (NumCounter == 16 && NumNearCounter == 0) {
				relive = true;
				JOptionPane.showMessageDialog(UI, "û�ط����Ժϲ���!!"
						+ "\n���ź���������~>_<~" + "\n���ĸ����㣬��Ϸ��������������Ŷ�������㡰��һ�������ԣ�"
						+ "\n˵������ŤתǬ���� ��^_~��");
			}
			UI.paint(UI.getGraphics());
		}

	}

}
