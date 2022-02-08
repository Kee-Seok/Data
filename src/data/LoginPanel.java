package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginPanel extends JPanel{

	JPanel loginAreaPanel = new JPanel(true) {
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	};
	JLabel idLb = new JLabel("ID : ",JLabel.CENTER);
	JLabel pwLb = new JLabel("passowrd : ",JLabel.CENTER);
	static JTextField idTf = new JTextField(20);
	static JPasswordField pwTf = new JPasswordField(20);
	static JButton loginBtn = new JButton("로그인");
	static Font font = new Font("Serif",Font.BOLD,15);
	static String id = "tlsrltjr";
	static char[] pwChar = pwTf.getPassword();
	static String pw = "1";
	static String password = "1";
	
	
	public LoginPanel() {
	setLayout(null);
	loginAreaPanel.setLayout(null);
	loginAreaPanel.setBounds(700,150,500,400);
	loginAreaPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	loginBtn.setBackground(Color.DARK_GRAY);
	loginBtn.setBorder(BorderFactory.createEmptyBorder());
	loginBtn.setForeground(Color.white);
	idLb.setBounds(43,170,100,50); idTf.setBounds(120,170,200,50);
	pwLb.setBounds(20,250,100,50); pwTf.setBounds(120,250,200,50); 
	idLb.setFont(font); pwLb.setFont(font); loginBtn.setFont(font);
	loginBtn.setBounds(340,250,120,50);
	pwTf.setEchoChar('*');
	loginAreaPanel.add(idLb);
	loginAreaPanel.add(idTf);
	loginAreaPanel.add(pwLb);
	loginAreaPanel.add(pwTf);
	loginAreaPanel.add(loginBtn);
	add(loginAreaPanel);
	System.out.println(pwTf.getText());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Data.backgroundColor);
		g.fillRect(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
	}
	
}