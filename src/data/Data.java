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

	
	Point p = new Point(321,159);
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
	static OnlineHopePanel onlineHopePanel = new OnlineHopePanel();
	static HopePanel hopePanel = new HopePanel();
	static HearimPanel hearimPanel = new HearimPanel();
	static HealingPanel healingPanel = new HealingPanel();
	static JajoPanel jajoPanel = new JajoPanel();
	static EHopePanel eHopePanel = new EHopePanel();
	static HopeMessengerPanel hopeMessengerPanel = new HopeMessengerPanel();
	static Performance performancePanel = new Performance();
	JButton[] mainBtn = {new JButton("온라인 희망다이어리"), new JButton("희망다이어리"), new JButton("헤아림"),  new JButton("힐링프로그램"), 
			         new JButton("자조모임"), new JButton("e희망교실"), new JButton("희망메신저"), new JButton("실적")};
	JButton[] onlineBtn = {new JButton("등록"), new JButton("삭제"), new JButton("저장"), 
			               new JButton("엑셀켜기")}; 
	
	public Data() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Main.SCREEN_WIDTH,Main.SCREEN_HEIGHT);
		setLayout(null);
		setLocation(p.x,p.y);
		setUndecorated(true);
		setResizable(false);
		setVisible(false);
		
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
		hearimPanel.setVisible(false);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	
	public void goOnlineHopePanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(true);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	
	public void goHopePanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		hopePanel.setVisible(true);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	
	public void goHearimPanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(true);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	public void goHealingPanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		healingPanel.setVisible(true);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	public void goJajoPanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(true);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	public void goEHopePanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(true);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(false);
	}
	public void goHopeMessengerPanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(true);
		performancePanel.setVisible(false);
	}
	public void goPerformancePanel() {
		mainScreen.setVisible(false);
		onlineHopePanel.setVisible(false);
		hopePanel.setVisible(false);
		hearimPanel.setVisible(false);
		healingPanel.setVisible(false);
		jajoPanel.setVisible(false);
		eHopePanel.setVisible(false);
		hopeMessengerPanel.setVisible(false);
		performancePanel.setVisible(true);
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
			OnlineHopePanel.table.changeSelection(OnlineHopePanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
			PM.setPerformance(onlineHopePanel.model);
		}else if(str == "희망다이어리") {
			goHopePanel();
			HopePanel.setPerformanceLabel();
			HopePanel.table.changeSelection(HopePanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
			PM.setPerformance(hopePanel.model);
		}else if(str == "헤아림") {
			goHearimPanel();
			HearimPanel.setPerformanceLabel();
			HearimPanel.table.changeSelection(HearimPanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
		}else if(str == "힐링프로그램") {
			goHealingPanel();
			
			HealingPanel.getGroupName();//반 이름 몇개나오는지
			
			HealingPanel.setPerformanceLabel();
			HealingPanel.table.changeSelection(HealingPanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
			PM.setPerformance(healingPanel.model);
		}else if(str == "자조모임") {
			goJajoPanel();
			JajoPanel.setPerformanceLabel();
			JajoPanel.table.changeSelection(JajoPanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
		}else if(str == "e희망교실") {
			goEHopePanel();
			EHopePanel.setPerformanceLabel();
			EHopePanel.table.changeSelection(EHopePanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
		}else if(str == "희망메신저") {
			goHopeMessengerPanel();
			HopeMessengerPanel.setPerformanceLabel();
			HopeMessengerPanel.table.changeSelection(HopeMessengerPanel.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
		}else if(str == "실적") {
			goPerformancePanel();
			performancePanel.init();
			//Performance.setPerformanceLabel();
			//Performance.table.changeSelection(Performance.table.getRowCount()-1, 0, false, false); //rowcount는 만약에 4줄이면 4를 반환하는데 실제 row 인덱스는 3이라서 -1해줘야됨.
		}
		requestFocus();
	}
	
	public void setPanels() { //패널 크기 위치 세팅
		setBtns();
		emptyPanel.setBounds(10,10,180,186);
		emptyPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(44,27,1), new Color(153,102,0)));

		emptyPanel.addMouseMotionListener(new PanelMouseListener());
		emptyPanel.addMouseListener(new PanelMouseListener());
		mainScreen.setBounds(200,0,1080,720);
		mainScreen.setLayout(null);
		onlineHopePanel.setBounds(200,0,1080,720);
		onlineHopePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		hopePanel.setBounds(200,0,1080,720);
		hopePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		hearimPanel.setBounds(200,0,1080,720);
		hearimPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		healingPanel.setBounds(200,0,1080,720);
		healingPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		jajoPanel.setBounds(200,0,1080,720);
		jajoPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		eHopePanel.setBounds(200,0,1080,720);
		eHopePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		hopeMessengerPanel.setBounds(200,0,1080,720);
		hopeMessengerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		performancePanel.setBounds(200,0,1080,720);
		performancePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		leftBtnPanel.setBounds(0,0,200,720);
		leftBtnPanel.setLayout(null);
		leftBtnPanel.setBorder(BorderFactory.createLineBorder(new Color(255,200,140),4,true));
		
		for(int i = 0; i < mainBtn.length; i++) {
			mainBtn[i].setBounds(10,203+64*i,180,58);
		}
		//leftBtnPanel에 버튼들 추가신기석신깃
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
		add(hearimPanel);
		add(healingPanel);
		add(jajoPanel);
		add(eHopePanel);
		add(hopeMessengerPanel);
		add(performancePanel);
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
			if(str == "실적")setCursor(cursor);
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
		}
		public void mouseDragged(MouseEvent e) {
			
			p.x = e.getLocationOnScreen().x-(getPointX+10);
			p.y = e.getLocationOnScreen().y-(getPointY+10);
			setLocation(p.x,p.y);
		}
		public void mouseReleased(MouseEvent e) {
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
