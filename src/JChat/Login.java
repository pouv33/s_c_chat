package JChat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.FlatteningPathIterator;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.NetworkChannel;
import java.sql.ClientInfoStatus;
import java.util.FormatFlagsConversionMismatchException;

import javax.print.attribute.standard.PrinterLocation;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;
import javax.xml.transform.Source;


//�Ƚ���������
public class Login extends JFrame implements ActionListener { 
	
	//���岢��ʼ������ 
	JButton jb = new JButton("��¼");
	JFrame fra = new JFrame("Jchat Log");
	static SSS sss=null;
	static CCC ccc=null;
	
	//�ڹ��췽�����趨����
	public Login(SSS sss) {
		this.sss=sss;
		
		//�趨������Ϣ���ر�
		fra.setBounds(400, 300, 500, 350);
		fra.setLocation(1250,500);
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//���崰������ӿ����
		JPanel jpl = new JPanel();
		jpl.setLayout(null);
		fra.add(jpl);
		
		//���岢�趨�ı��� ��ӽ����
		JTextField jtf = new JTextField();
		jtf.setBounds(170, 150, 180, 30);
		jpl.add(jtf);
		
		//���岢�趨����� ��ӽ����
		JPasswordField jps = new JPasswordField();
		jps.setBounds(170, 200, 180, 30);
		jpl.add(jps);
		
		//���岢�趨�ı�����  ��ӽ����
		JLabel lab1 = new JLabel("�˺�");
		JLabel lab2 = new JLabel("����");
		JLabel lab3 = new JLabel("�������������ҡ�ԤԼ�û������ڲ���,�ݲ�����ע�ᡣ");
		JLabel lab4 = new JLabel("*�˰汾ΪAlpha1.1.0    ��½ҳ�湦���ݲ�����*");
		
		lab1.setBounds(130, 155, 80, 20);
		lab2.setBounds(130, 205, 80, 20);
		lab1.setForeground(Color.black);
		lab2.setForeground(Color.black);
		jpl.add(lab1);
		jpl.add(lab2);
		
		lab3.setBounds(30, 60, 400, 15);
		lab3.setForeground(Color.blue);
		jpl.add(lab3);
		
		lab4.setBounds(30, 80, 400, 15);
		lab4.setForeground(Color.black);
		jpl.add(lab4);
		
		
		//�趨��¼��ť
		jb.setBounds(375, 170, 80, 40);
		jpl.add(jb);

		fra.setVisible(true);
		
		//������¼����ť���¼�����
		jb.addActionListener(this);
		
	}

//�������¼������������趨
	@Override
	public void actionPerformed(ActionEvent e) {
		//�趨 �������Ѳ��رյ�¼���
		JOptionPane.showMessageDialog(null,"��½�ɹ�");
		fra.setVisible(false);
		//����ʾ������˴���
		sss.setVisible(true);
		
		//����һ���µ��߳��������ͻ���
		new Thread(new Runnable() {
			@Override
			public void run() {
				//�趨�������Ѳ����ÿͻ����� �Դﵽ��ʾ���ͻ��˴��ڵ�Ŀ��
				JOptionPane.showMessageDialog(null,"����һ���µ��������룡");
				ccc=new CCC(); 
			}
		}).start();
	}
	
}