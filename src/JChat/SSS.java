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

//先建立窗体类
public class SSS extends JFrame implements ActionListener, KeyListener{
	
	public static void main(String[] args) {
		//主函数中调用构造方法
		new SSS(); 	
	}
	
	//定义成员变量 ：文本域、滚动条、面板、文本框、按钮 
	private JTextArea jta;

	private JScrollPane jsp;
	private JPanel jp;
	private JTextField jtf;
	private JButton jb;
	
	private BufferedWriter bw = null;
	
	//在构造方法中初始化成并设定
	public SSS() {
		
		//设定文本域并使其不可编辑
		jta = new JTextArea();
		jta.setEditable(false);
		
		//滚动条初始化并添加进文本域
		jsp = new JScrollPane(jta);

		//初始化面板、发送按钮、及文本框并设定长度
		jp = new JPanel();
		jb = new JButton("发送");
		jtf = new JTextField(30);
		
		
		//将文本框与按钮添加到面板中
		jp.add(jtf);
		jp.add(jb);
				
		//将滚动条与面板添加到窗口中并设定位置
		this.add(jsp,BorderLayout.CENTER);
		this.add(jp,BorderLayout.SOUTH);
		
		//设定窗口信息
		this.setTitle("JchatServer");
		this.setSize(500,500);
		this.setLocation(1000,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//给“发送”按钮及键盘绑定事件监听
		jb.addActionListener(this);
		jtf.addKeyListener(this);
		
		
		//调用出登录页面（聊天页面的显示由Login类实现）
		new Login(this);
		
		
		
		//页面创建后 为达到一对一即时通信的目的： 使用网络编程中的TCP协议进行服务端与客户端之间的数据传输
		try {
			//创建一个套接字 并给出端口
			ServerSocket serverSocket = new ServerSocket(1111);
			
			//等待客户端的连接
			Socket socket = serverSocket.accept();
			
			//获取输入流，读取数据
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			//获取输出流，写出数据
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//循环读取数据，并拼接到文本域中显示 
			String line = null;
			while((line = br.readLine()) != null) {
			jta.append(line + System.lineSeparator());
			}
			
			//关闭服务端通道
			serverSocket.close();
			//这样就建立好了TCP服务端
		
		} catch (IOException e) {

			e.printStackTrace();
		}
	}	

	

	//定义一个实现数据发送至socket通道的方法
	private void sendDataToSocket() {
		        //获取并拼接 文本框中发送的内容
				String text = jtf.getText();
				text = "Server：" + text;
				//文本域中显示内容
				jta.append(text+ System.lineSeparator());
				//发送内容 换行 并 刷新
				try {
					bw.write(text);
					bw.newLine();
					bw.flush();
				//最后 清空文本框
					jtf.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}



//最后进行事件监听方法的设定
	@Override
	public void actionPerformed(ActionEvent e) {
		//监听到鼠标点击 调用发送数据的方法
		sendDataToSocket();
	}


	
	@Override
	public void keyPressed(KeyEvent e) {
		//监听到键盘  并判断回车键 调用发送数据方法
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
