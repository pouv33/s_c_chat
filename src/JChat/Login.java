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


//先建立窗口类
public class Login extends JFrame implements ActionListener { 
	
	//定义并初始化变量 
	JButton jb = new JButton("登录");
	JFrame fra = new JFrame("Jchat Log");
	static SSS sss=null;
	static CCC ccc=null;
	
	//在构造方法中设定变量
	public Login(SSS sss) {
		this.sss=sss;
		
		//设定窗口信息及关闭
		fra.setBounds(400, 300, 500, 350);
		fra.setLocation(1250,500);
		fra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//定义窗口中添加空面板
		JPanel jpl = new JPanel();
		jpl.setLayout(null);
		fra.add(jpl);
		
		//定义并设定文本框 添加进面板
		JTextField jtf = new JTextField();
		jtf.setBounds(170, 150, 180, 30);
		jpl.add(jtf);
		
		//定义并设定密码框 添加进面板
		JPasswordField jps = new JPasswordField();
		jps.setBounds(170, 200, 180, 30);
		jpl.add(jps);
		
		//定义并设定文本属性  添加进面板
		JLabel lab1 = new JLabel("账号");
		JLabel lab2 = new JLabel("密码");
		JLabel lab3 = new JLabel("请登入进入聊天室。预约用户二次内测中,暂不开放注册。");
		JLabel lab4 = new JLabel("*此版本为Alpha1.1.0    登陆页面功能暂不可用*");
		
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
		
		
		//设定登录按钮
		jb.setBounds(375, 170, 80, 40);
		jpl.add(jb);

		fra.setVisible(true);
		
		//给“登录”按钮绑定事件监听
		jb.addActionListener(this);
		
	}

//最后进行事件监听方法的设定
	@Override
	public void actionPerformed(ActionEvent e) {
		//设定 弹窗提醒并关闭登录面板
		JOptionPane.showMessageDialog(null,"登陆成功");
		fra.setVisible(false);
		//再显示出服务端窗口
		sss.setVisible(true);
		
		//定义一个新的线程来启动客户端
		new Thread(new Runnable() {
			@Override
			public void run() {
				//设定弹窗提醒并调用客户端类 以达到显示出客户端窗口的目的
				JOptionPane.showMessageDialog(null,"您有一条新的聊天申请！");
				ccc=new CCC(); 
			}
		}).start();
	}
	
}