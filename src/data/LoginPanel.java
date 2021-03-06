package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;


public class LoginPanel extends JPanel{
	static Timer timer;
	static Image titleImage = new ImageIcon("./temp/title.png").getImage();
	static Image loginImage = new ImageIcon("./temp/login.png").getImage();
	static JPanel loginAreaPanel = new JPanel(true) {
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.drawImage(loginImage, 0, 0, null);
		}
	};
	JLabel idLb = new JLabel("ID : ",JLabel.CENTER);
	JLabel pwLb = new JLabel("passowrd : ",JLabel.CENTER);
	static JTextField idTf = new JTextField(20);
	static JPasswordField pwTf = new JPasswordField(20);
	static JButton loginBtn = new JButton("로그인");
	static JButton ansysBtn = new JButton("안시스");
	static Font font = new Font("Serif",Font.BOLD,15);
	static String id = "tlsrltjr";
	static char[] pwChar = pwTf.getPassword();
	static String pw = "1";
	static String password = "1";
	
	static Bear bear = new Bear();
	static boolean isTimer = true;
	
	public LoginPanel() {
	setLayout(null);
	loginAreaPanel.setLayout(null);
	loginAreaPanel.setBounds(700,150,500,400);
	loginAreaPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	loginBtn.setBackground(Color.DARK_GRAY);
	loginBtn.setBorder(BorderFactory.createEmptyBorder());
	loginBtn.setForeground(Color.white);
	ansysBtn.setBackground(Color.DARK_GRAY);
	ansysBtn.setBorder(BorderFactory.createEmptyBorder());
	ansysBtn.setForeground(Color.white);
	idLb.setBounds(43,170,100,50); idTf.setBounds(120,170,200,50);
	pwLb.setBounds(20,250,100,50); pwTf.setBounds(120,250,200,50); 
	idLb.setFont(font); pwLb.setFont(font);
	loginBtn.setFont(font);
	loginBtn.setBounds(340,250,120,50);
	ansysBtn.setBounds(340,170,120,50);
	ansysBtn.setFont(font);
	pwTf.setEchoChar('*');
	loginAreaPanel.add(idLb);
	loginAreaPanel.add(idTf);
	loginAreaPanel.add(pwLb);
	loginAreaPanel.add(pwTf);
	loginAreaPanel.add(loginBtn);
	loginAreaPanel.add(ansysBtn);
	add(loginAreaPanel);
	
	System.out.println(pwTf.getText());
	addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
	});
	idTf.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
	});
	timer = new Timer(1,new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(bear.dir==0&&bear.x+bear.img.getWidth(null)<700) {
			bear.x += 1;
			repaint();
				if(bear.x+bear.img.getWidth(null)>=700) {
					bear.dir = 1;
					repaint();
				}
			}else if(bear.dir==1&&bear.x>0) {
				bear.x -= 1;
				repaint();
				if(bear.x<=0) {
					bear.dir = 0;
					repaint();
				}
			}
		}
		
	});
	if(isTimer) {
	timer.start();
	}else if(!isTimer) {
		timer.stop();
	}
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Data.backgroundColor);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		
		g.drawImage(titleImage, 60, 100, null);
		bear.paintComponent(g); 
	}

	}
