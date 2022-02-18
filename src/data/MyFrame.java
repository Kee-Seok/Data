package data;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements ActionListener{

	
	static LoginPanel loginPanel = new LoginPanel();
	static Data dataPanel = new Data();
	String realPassword = "mxl1020!";
	static Point p = new Point(321,159);
	int mouseX;
	int mouseY;
	public MyFrame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLayout(null);
		setLocation(p.x,p.y);
		setUndecorated(true);
		setResizable(false);
		setPanels();
		LoginPanel.loginBtn.addActionListener(this);
		LoginPanel.ansysBtn.addActionListener(this);
		loginPanel.addKeyListener(new KeyListener());
		addKeyListener(new KeyListener());
		LoginPanel.pwTf.addKeyListener(new KeyListener());
		setVisible(true);
		LoginPanel.idTf.requestFocus();
		Robot robot;
		try {
			robot = new Robot();
			Event.startKeyMacro("tlsrltjr");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addMouseListener(new MyMouseListener());
		addMouseMotionListener(new MyMouseListener());
		loginPanel.addMouseListener(new MyMouseListener());
		loginPanel.addMouseMotionListener(new MyMouseListener());
		
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
		LoginPanel.timer.stop();
	}

	public void actionPerformed(ActionEvent e) {
		String button = (String)e.getActionCommand();
		if(button == "로그인") {
			isCorrectId();
		}else if(button == "안시스") {
			Event.openAnsys();
		}
	}
	
	public void isCorrectId() {
			String password = "";
		for(char cha : LoginPanel.pwTf.getPassword()) {
			LoginPanel.pw = Character.toString(cha);
			password += LoginPanel.pw;
		}
		System.out.println(password);
		if(LoginPanel.idTf.getText().equals(LoginPanel.id)&&password.equals(realPassword)) {
			goDataPanel();
			LoginPanel.isTimer = false;
		}else {
			JOptionPane op = new JOptionPane();
			LoginPanel.pwTf.setText("");
			op.showMessageDialog(loginPanel, "아이디 비밀번호가 일치하지 않습니다.", "경고!", JOptionPane.WARNING_MESSAGE, new ImageIcon("./temp/warning.png"));
		}
	}
	

	
	class KeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				System.exit(0);
				break;
			case KeyEvent.VK_ENTER:
				isCorrectId();
				break;
			}
		}
	}
	
	
	class MyMouseListener extends MouseAdapter implements MouseMotionListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			mouseX = e.getPoint().x;
			mouseY = e.getPoint().y;
			p = new Point(e.getLocationOnScreen().x-e.getPoint().x,e.getLocationOnScreen().y-e.getPoint().y);
			System.out.println(p);
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			p.x = e.getLocationOnScreen().x-mouseX;
			p.y = e.getLocationOnScreen().y-mouseY;
			setLocation(p.x,p.y);
			System.out.println(p);

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			
		}
	}
}
	
