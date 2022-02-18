package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class OnlineHopePerformance extends JPanel{

	//-----------------패널 고유 변경점-------------------------------------------------------
	static String groupName = OnlineHopePanel.groupname;
	static Color color = C.red;
	//-----------------------------------------------------------------------------------
	static JLabel title = new JLabel(groupName+" 실적",JLabel.CENTER);
	static Font font = new Font("Serif",Font.BOLD,30);
	static JLabel totalPerformance = new JLabel("총 실적",SwingConstants.CENTER); 
	static String[] contents = {"그룹","건","명"};
	static JLabel[] label = {new JLabel("1월",SwingConstants.CENTER), //1월~12월 실적 표시 라벨
	          new JLabel("2월",SwingConstants.CENTER),
	          new JLabel("3월",SwingConstants.CENTER),
	          new JLabel("4월",SwingConstants.CENTER),
	          new JLabel("5월",SwingConstants.CENTER),
	          new JLabel("6월",SwingConstants.CENTER),
	          new JLabel("7월",SwingConstants.CENTER),
	          new JLabel("8월",SwingConstants.CENTER),
	          new JLabel("9월",SwingConstants.CENTER),
	          new JLabel("10월",SwingConstants.CENTER),
	          new JLabel("11월",SwingConstants.CENTER),
	          new JLabel("12월",SwingConstants.CENTER)
};
	static DefaultTableModel[] model = {new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel(),
			                            new DefaultTableModel()
	};
	static JTable[] table = {new JTable(model[0]),
			                 new JTable(model[1]),
			                 new JTable(model[2]),
			                 new JTable(model[3]),
			                 new JTable(model[4]),
			                 new JTable(model[5]),
			                 new JTable(model[6]),
			                 new JTable(model[7]),
			                 new JTable(model[8]),
			                 new JTable(model[9]),
			                 new JTable(model[10]),
			                 new JTable(model[11])
	};
	static JScrollPane[] scroll = {new JScrollPane(table[0]),
			                       new JScrollPane(table[1]),
			                       new JScrollPane(table[2]),
			                       new JScrollPane(table[3]),
			                       new JScrollPane(table[4]),
			                       new JScrollPane(table[5]),
			                       new JScrollPane(table[6]),
			                       new JScrollPane(table[7]),
			                       new JScrollPane(table[8]),
			                       new JScrollPane(table[9]),
			                       new JScrollPane(table[10]),
			                       new JScrollPane(table[11])
	};
	
	static JPanel titlePanel, performancePanel, backBtnPanel;
	public OnlineHopePerformance() {
		setPanel();
	}
	
	public void setPanel() {
		setLayout(null);
		titlePanel = new JPanel();
		performancePanel = new JPanel();
		titlePanel.setBounds(0,30,1080,50);
		performancePanel.setBounds(0,30,1080,700);
		titlePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		title.setFont(font);
		titlePanel.add(title);
		
		int i = 0;
		performancePanel.setLayout(null);
		for(int r = 0; r < 2; r++) {
			for(int c = 0; c < 6; c++) {
				label[i].setBounds(10+177*c,50+200*r,170,50);
				label[i].setFont(font);
				scroll[i].setBounds(10+177*c,100+200*r,170,150);
				scroll[i].setBorder(BorderFactory.createLineBorder(color,1));
				scroll[i].setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				performancePanel.add(label[i]);
				performancePanel.add(scroll[i]);
				for(int j = 0; j < contents.length; j++) {
				model[i].addColumn(contents[j]);
				}
				
				
				i++;
			}
		}
		add(titlePanel);
		add(performancePanel);
		}
	
	public static void setPerformanceTable(DefaultTableModel model,DefaultTableModel getDataFromOtherTable,
										   ArrayList<String> group) {
		PM.setPerformance(getDataFromOtherTable); //월별 실적 클래스에서 계산된 데이터 가져오기.
		int groupSize[] = new int[group.size()];
		for(int i = 0; i < group.size(); i++) {
			for(int j = 0; j < getDataFromOtherTable.getRowCount(); j++) {
			if(getDataFromOtherTable.getValueAt(j, 0).toString().equals(group.get(groupSize[i]))) {
				groupSize[i]++;
			}
			}
		} 
	}
	
	public static void init() {
		
	}
	public void paintComponent(Graphics g) {
		g.setColor(C.red);
		g.fillRect(0, 0, getWidth(), getHeight());
		this.repaint();
	}
}
