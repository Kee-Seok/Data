package data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements ActionListener{

	
	static LoginPanel loginPanel = new LoginPanel();
	static Data dataPanel = new Data();
	public MyFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		setPanels();
		LoginPanel.loginBtn.addActionListener(this);
		addKeyListener(new KeyListener());
		setVisible(true);
		requestFocus();
	}
	char[] cha;
	public void setPanels() {
		loginPanel.setBounds(0,0,Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		add(loginPanel);
		init();
	}
	
	public void init() {
		loginPanel.setVisible(true);
		dataPanel.setVisible(false);
	}
	
	public void goDataPanel() {
		loginPanel.setVisible(false);
		setVisible(false);
		dataPanel.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String button = (String)e.getActionCommand();
		if(button == "로그인") {
			LoginPanel.pwChar = LoginPanel.pwTf.getPassword();
			for(char cha : LoginPanel.pwChar) {
				LoginPanel.pw = Character.toString(cha);
			}
			System.out.println(LoginPanel.pw);
			System.out.println(LoginPanel.password);
			System.out.println(LoginPanel.id);
			System.out.println(LoginPanel.idTf.getText());
			if(LoginPanel.idTf.getText().equals(LoginPanel.id)&&LoginPanel.pw.equals(LoginPanel.password)) {
				goDataPanel();
			}else {
				JOptionPane op = new JOptionPane();
				op.showMessageDialog(loginPanel, "아이디 비밀번호가 일치하지 않습니다.", "경고!", JOptionPane.WARNING_MESSAGE, new ImageIcon("./temp/dice6.png"));
			}
		}
	}
	
	class KeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				System.exit(0);
			}
		}
	}
	
	
	
	
}
