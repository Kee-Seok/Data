package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Performance extends JPanel implements ActionListener{

	JPanel mainPanel = new JPanel(true) {
		public void paintComponent(Graphics g) {
			g.setColor(Data.backgroundColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			repaint();
		}
	};
	JButton[] btn = {new JButton("온라인 희망다이어리"),
			         new JButton("희망다이어리"),
			         new JButton("헤아림"),
			         new JButton("힐링프로그램"),
			         new JButton("자조모임"),
			         new JButton("e희망교실"),
			         new JButton("희망메신저"),
			         new JButton("총 실적"),
	};
	static OnlineHopePerformance onlineHopePerformance = new OnlineHopePerformance();
	
	//온라인 희망다이어리의 모든 컬러, 시트번호, JComboBox 네이밍 등 한꺼번에 바뀌어야 될 것들
	Color color = C.violet;
	int sheetNum = 7;
	String groupname = "실적";
	//-------------------------------------------------------------------
	
	public Performance() {
		setPanel();
		init();
	}
	
	public void setPanel() {
		setLayout(null);
		mainPanel.setLayout(null);
		mainPanel.setBounds(0,0,1100,720);
		onlineHopePerformance.setBounds(0,0,1100,800);
		int i = 0;
			for(int r = 0; r < 4; r++) {
				for(int c = 0; c < 2; c++) {
					btn[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
					btn[i].setBackground(C.col[i]);
					btn[i].setFont(new Font("Serif",Font.BOLD,20));
					btn[i].setBounds(220+c*360,200+r*120,300,100);
					btn[i].addActionListener(this);
					mainPanel.add(btn[i]);
					i++;
				}
			}
			add(mainPanel);
			add(onlineHopePerformance);
		}
	
	public void init() {
		mainPanel.setVisible(true);
		onlineHopePerformance.setVisible(false);
	}
	
	public void goOnlineHopePerformance() {
		mainPanel.setVisible(false);
		onlineHopePerformance.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		String str = (String)e.getActionCommand();
		if(str=="온라인 희망다이어리") {
			goOnlineHopePerformance();
		}else if(str=="희망다이어리") {
			
		}else if(str=="헤아림") {
			
		}else if(str=="힐링프로그램") {
			
		}else if(str=="자조모임") {
			
		}else if(str=="e희망교실") {
			
		}else if(str=="희망메신저") {
			
		}else if(str=="총 실적") {
			
		}
		
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Data.backgroundColor);//연한빨강
		g.fillRect(0, 0, getWidth(), getHeight());
		this.repaint();
	}
	
	
}
