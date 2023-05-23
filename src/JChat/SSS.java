package JChat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//�Ƚ���������
public class SSS extends JFrame implements ActionListener, KeyListener{
	
	public static void main(String[] args) {
		//�������е��ù��췽��
		new SSS(); 	
	}
	
	//�����Ա���� ���ı��򡢹���������塢�ı��򡢰�ť 
	private JTextArea jta;

	private JScrollPane jsp;
	private JPanel jp;
	private JTextField jtf;
	private JButton jb;
	
	private BufferedWriter bw = null;
	
	//�ڹ��췽���г�ʼ���ɲ��趨
	public SSS() {
		
		//�趨�ı���ʹ�䲻�ɱ༭
		jta = new JTextArea();
		jta.setEditable(false);
		
		//��������ʼ������ӽ��ı���
		jsp = new JScrollPane(jta);

		//��ʼ����塢���Ͱ�ť�����ı����趨����
		jp = new JPanel();
		jb = new JButton("����");
		jtf = new JTextField(30);
		
		
		//���ı����밴ť��ӵ������
		jp.add(jtf);
		jp.add(jb);
				
		//���������������ӵ������в��趨λ��
		this.add(jsp,BorderLayout.CENTER);
		this.add(jp,BorderLayout.SOUTH);
		
		//�趨������Ϣ
		this.setTitle("JchatServer");
		this.setSize(500,500);
		this.setLocation(1000,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�������͡���ť�����̰��¼�����
		jb.addActionListener(this);
		jtf.addKeyListener(this);
		
		
		//���ó���¼ҳ�棨����ҳ�����ʾ��Login��ʵ�֣�
		new Login(this);
		
		
		
		//ҳ�洴���� Ϊ�ﵽһ��һ��ʱͨ�ŵ�Ŀ�ģ� ʹ���������е�TCPЭ����з������ͻ���֮������ݴ���
		try {
			//����һ���׽��� �������˿�
			ServerSocket serverSocket = new ServerSocket(1111);
			
			//�ȴ��ͻ��˵�����
			Socket socket = serverSocket.accept();
			
			//��ȡ����������ȡ����
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//��ȡ�������д������
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//ѭ����ȡ���ݣ���ƴ�ӵ��ı�������ʾ 
			String line = null;
			while((line = br.readLine()) != null) {
			jta.append(line + System.lineSeparator());
			}
			
			//�رշ����ͨ��
			serverSocket.close();
			//�����ͽ�������TCP�����
		
		} catch (IOException e) {

			e.printStackTrace();
		}
	}	

	

	//����һ��ʵ�����ݷ�����socketͨ���ķ���
	private void sendDataToSocket() {
		        //��ȡ��ƴ�� �ı����з��͵�����
				String text = jtf.getText();
				text = "Server��" + text;
				//�ı�������ʾ����
				jta.append(text+ System.lineSeparator());
				//�������� ���� �� ˢ��
				try {
					bw.write(text);
					bw.newLine();
					bw.flush();
				//��� ����ı���
					jtf.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}



//�������¼������������趨
	@Override
	public void actionPerformed(ActionEvent e) {
		//����������� ���÷������ݵķ���
		sendDataToSocket();
	}


	
	@Override
	public void keyPressed(KeyEvent e) {
		//����������  ���жϻس��� ���÷������ݷ���
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			sendDataToSocket();
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent e) {	
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
