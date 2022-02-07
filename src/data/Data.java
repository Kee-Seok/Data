package data;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Data extends JFrame implements ActionListener{

	static Color backgroundColor = new Color(249,228,183);
	Toolkit tk = Toolkit.getDefaultToolkit();
	//수박모양 커서
	Cursor cursor = tk.createCustomCursor(new ImageIcon("./temp/cursor.png").getImage()
            .getScaledInstance(100, 50, Image.SCALE_SMOOTH), new Point(0,0), "watermelon");

	
	Point p = new Point(100,100);
	int mouseX, mouseY, getPointX, getPointY;
	
	JPanel mainScreen = new JPanel(true) { //메인패널 패널
		Image buffImg;
		Graphics graphic;
		Image img = new ImageIcon("./temp/mainScreen.png").getImage().getScaledInstance(1080,720,Image.SCALE_SMOOTH);
		public void paintComponent(Graphics g) {
			buffImg = createImage(getWidth(),getHeight());
			graphic = buffImg.getGraphics();
			drawScreen(graphic);
			g.drawImage(buffImg, 0, 0, null);
		}
		public void drawScreen(Graphics g) {
			g.drawImage(img, 0, 0, null);
			this.repaint();
		}
	};
	static JPanel emptyPanel = new JPanel(true) { //빈 패널
		public void paintComponent(Graphics g) {
			g.setColor(new Color(94,130,130));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			Date.paintComponent(g,Date.getTime());
			this.repaint();
			}
	};
	JPanel leftBtnPanel = new JPanel(true) {
		public void paintComponent(Graphics g) {
			g.setColor(backgroundColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			this.repaint();
		}
	};
	OnlineHopePanel onlineHopePanel = new OnlineHopePanel();
	HopePanel hopePanel = new HopePanel();
	JButton[] mainBtn = {new JButton("온라인 희망다이어리"), new JButton("희망다이어리"), new JButton("헤아림"),  new JButton("힐링프로그램"), 
			         new JButton("자조모임"), new JButton("e희망교실"), new JButton("희망메신저"), new JButton("데이터")};
	JButton[] onlineBtn = {new JButton("등록"), new JButton("삭제"), new JButton("저장"), 
			               new JButton("엑셀켜기")}; 
	
	public Data() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLayout(null);
		setLocation(p.x,p.y);
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		
		setPanels();
		addKeyListener();
		init();
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			public void run() {
				Date.getTime();
				emptyPanel.repaint();
			}
		};
		timer.schedule(task, 10, 1000);
		try {
			Excel.getDataFromExcel();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() { //패널간 이동 setVisible로 조절할꺼임.
		mainScreen.setVisible(true);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
	}
	
	public void goOnlineHopePanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(true);
		hopePanel.setVisible(false);
	}
	
	public void goHopePanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(true);
	}
	
	public void setBtns() {
		for(int i = 0; i < mainBtn.length; i++) {
		mainBtn[i].setBackground(C.col[i]);
		mainBtn[i].setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		mainBtn[i].addActionListener(this);
		mainBtn[i].addMouseListener(new MouseListener());
		}
	}
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if(str == "온라인 희망다이어리") {
			goOnlineHopePanel();
			OnlineHopePanel.setPerformanceLabel();
		}else if(str == "희망다이어리") {
			goHopePanel();
			hopePanel.setPerformanceLabel();
		}else if(str == "헤아림") {
			
		}else if(str == "자조모임") {
			
		}else if(str == "e희망교실") {
			
		}else if(str == "희망메신저") {
			
		}else if(str == "힐링프로그램") {
			
		}
		requestFocus();
	}
	
	public void setPanels() { //패널 크기 위치 세팅
		setBtns();
		emptyPanel.setBounds(10,10,180,196);
		emptyPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(44,27,1), new Color(153,102,0)));

		emptyPanel.addMouseMotionListener(new PanelMouseListener());
		emptyPanel.addMouseListener(new PanelMouseListener());
		mainScreen.setBounds(200,0,1080,720);
		mainScreen.setLayout(null);
		onlineHopePanel.setBounds(200,0,1080,720);
		onlineHopePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		hopePanel.setBounds(200,0,1080,720);
		hopePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		leftBtnPanel.setBounds(0,0,200,720);
		leftBtnPanel.setLayout(null);
		leftBtnPanel.setBorder(BorderFactory.createLineBorder(new Color(255,200,140),4,true));
		
		for(int i = 0; i < mainBtn.length; i++) {
			mainBtn[i].setBounds(10,216+72*i,180,62);
		}
		//leftBtnPanel에 버튼들 추가
		for(int i = 0; i < 3; i++) {
		leftBtnPanel.add(emptyPanel);
		}
		for(int i = 0; i < mainBtn.length; i++) {
		leftBtnPanel.add(mainBtn[i]);
		}
		add(leftBtnPanel);
		add(mainScreen);
		add(onlineHopePanel);
		add(hopePanel);
		mainScreen.setFocusable(true);
	}
	public void addKeyListener() {
		addKeyListener(new KeyListener());
		mainScreen.addKeyListener(new KeyListener());
	}
	class KeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_ESCAPE :
				System.exit(0);
				break;
			case KeyEvent.VK_BACK_SPACE :
				init();
				break;
			}
		}
	}
	class MouseListener extends MouseAdapter{
		public void mouseEntered(MouseEvent e) {
			JButton btn = (JButton)e.getComponent();
			String str = btn.getActionCommand();
			if(str == "온라인 희망다이어리")setCursor(cursor);
			if(str == "희망다이어리")setCursor(cursor);
			if(str == "헤아림")setCursor(cursor);
			if(str == "힐링프로그램")setCursor(cursor);
			if(str == "자조모임")setCursor(cursor);
			if(str == "e희망교실")setCursor(cursor);
			if(str == "희망메신저")setCursor(cursor);
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(DEFAULT_CURSOR));
		}
	}
	class PanelMouseListener extends MouseListener implements MouseMotionListener{
		public void mouseEntered(MouseEvent e) {
			JPanel panel = (JPanel) e.getComponent();
			if(panel == emptyPanel) {
				setCursor(new Cursor(HAND_CURSOR));
			}
			requestFocus();
		}
		public void mouseExited(MouseEvent e) {
			setCursor(new Cursor(DEFAULT_CURSOR));
		}
		public void mousePressed(MouseEvent e) {
			mouseX = e.getLocationOnScreen().x;
			mouseY = e.getLocationOnScreen().y;
			getPointX = e.getPoint().x+10;
			getPointY = e.getPoint().y+10;
			p = new Point(e.getLocationOnScreen().x-e.getPoint().x-10,e.getLocationOnScreen().y-e.getPoint().y-10);
			System.out.println("getPointX는 : "+getPointX);
			System.out.println("getPointY는 : "+getPointY);
		}
		public void mouseDragged(MouseEvent e) {
			
			int mouseX1 = e.getLocationOnScreen().x-mouseX;
			int mouseY1 = e.getLocationOnScreen().y-mouseY;
			p.x = e.getLocationOnScreen().x-(getPointX+10);
			p.y = e.getLocationOnScreen().y-(getPointY+10);
			setLocation(p.x,p.y);
			System.out.println("p.X는 : "+ p.x);
			System.out.println("getLocationOnScreen은 : "+ e.getLocationOnScreen());
			System.out.println("mouseX1은 : "+mouseX1);
			System.out.println("mouseY1은 : "+mouseY1);
		}
		public void mouseReleased(MouseEvent e) {
//			Point p1 = new Point(e.getLocationOnScreen().x-mouseX,e.getLocationOnScreen().y-mouseY);
//			p = new Point(e.getLocationOnScreen().x-e.getPoint().x-10+p1.x,e.getLocationOnScreen().y-e.getPoint().y-10+p1.y);
//			setLocation(p.x,p.y);
//			System.out.println(p.x);
//			System.out.println(p.y);
		}
		public void mouseMoved(MouseEvent e) {
			
		}
		public void mouseClicked(MouseEvent e) {
			JPanel panel =(JPanel)e.getSource();
			if(panel.equals(emptyPanel)) {
				init();
			}
		}
	}
}
