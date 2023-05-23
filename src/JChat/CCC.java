package JChat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class CCC extends JFrame implements ActionListener, KeyListener{
	public static void main(String[] args) {
		new CCC(); 	
	}
	

	private JTextArea jta;
	private JScrollPane jsp;
	private JPanel jp;
	private JTextField jtf;
	private JButton jb;
	
	private BufferedWriter bw = null;
	

	public CCC() {

		jta = new JTextArea();
		jta.setEditable(false);

		jsp = new JScrollPane(jta);
		jp = new JPanel();
		jtf = new JTextField(30);
		jb = new JButton("����");

		jp.add(jtf);
		jp.add(jb);
				
		this.add(jsp,BorderLayout.CENTER);
		this.add(jp,BorderLayout.SOUTH);
		

		this.setTitle("JchatClient");
		this.setSize(500,500);
		this.setLocation(1500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		//�¼�����
		jb.addActionListener(this);
		jtf.addKeyListener(this);
		
		
		
		//����TCP�ͻ���
		try {
			//�����׽��ֲ��趨�����˶�Ӧip���˿�  ������������ȡ�����д������һ��
			Socket socket = new Socket("127.0.0.1",1111);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			String line = null;
			while((line = br.readLine()) != null) {
				jta.append(line + System.lineSeparator());
			}
			
			socket.close();
			
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		
	}

	
	
		//���巽��
		private void sendDataToSocket() {
				String text = jtf.getText();
				text = "Client��" + text;
				jta.append(text+ System.lineSeparator());
				try {
					bw.write(text);
					bw.newLine();
					bw.flush();
					jtf.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

	
	
	


	@Override
	public void actionPerformed(ActionEvent e) {
		sendDataToSocket();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			sendDataToSocket();
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {	
	}
	@Override
	public void keyTyped(KeyEvent e) {}
}
	

