package data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class OnlineHopePanel extends JPanel implements ActionListener{

	JPanel firstPanel = new JPanel(); //centerPanel, rightPanel 들어갈 패널  , BorderLayout(10,10)
	JPanel centerPanel = new JPanel(); //registerPanel, tablePanel 들어갈 패널 BorderLayout(10,10)
	JPanel rightPanel = new JPanel(); //dataPanel과 btnPanel 들어갈 자리 GridLayout(2,1);
	JPanel dataPanel = new JPanel(); //실적 한눈에 보이게끔 건/명 출력할것임.
	JPanel registerPanel = new JPanel(); //반 이름, 인원, 날짜, 내용 등 넣을꺼임.
	JPanel tablePanel = new JPanel(); //JTable 들어갈 자리임.
	JPanel btnPanel = new JPanel(); //등록,삭제,저장,엑셀켜기 버튼 들어갈 자리
	JLabel[] label = {new JLabel("반 이름 : ",SwingConstants.CENTER),
			          new JLabel("참가인원 : ",SwingConstants.CENTER),
			          new JLabel("날짜 : ",SwingConstants.CENTER),
			          new JLabel("운영일지 : ",SwingConstants.CENTER)};
	String[] group = {"온라인 희망다이어리", "없음"};
	JComboBox<String> groupName = new JComboBox<String>(group);
	JSlider slide = new JSlider(0,20);
	JLabel slideLabel = new JLabel("인원",SwingConstants.CENTER);
	JButton[] rightBtn = {new JButton("등록"),
			              new JButton("삭제"),
			              new JButton("저장"),
			              new JButton("엑셀")};
	JTextField dateTf = new JTextField(20);
	JTextArea ta = new JTextArea();
	
	String[] titles = {"반이름", "참가인원", "날짜", "운영일지"};
	DefaultTableModel model = new DefaultTableModel(titles,0);
	JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table);
	
	File file;
	
	public OnlineHopePanel() {
		setLayout(null);
		setPanel();
		setSlider();
		add(firstPanel);
	}
	
	public void setTable() {
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
	}
	
	public void setBtn() {
		for(int i = 0; i < rightBtn.length; i++) {
		rightBtn[i].setFont(new Font("Serif",Font.BOLD,15));
		rightBtn[i].setBackground(C.red);
		rightBtn[i].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		rightBtn[i].addActionListener(this);
		}
	}
	
	public void setPanel() {
		setBtn();
		setTable();
		firstPanel.setBounds(10,10,1060,700);
		firstPanel.setLayout(new BorderLayout(10,10));
		centerPanel.setLayout(new BorderLayout(10,10));
		rightPanel.setLayout(new GridLayout(2,1));
		btnPanel.setLayout(new GridLayout(4,1,10,10));
		btnPanel.setBorder(BorderFactory.createTitledBorder("처리"));
		registerPanel.setLayout(new GridLayout(4,3,50,10));
		registerPanel.setSize(880,400);
		registerPanel.setBorder(BorderFactory.createLineBorder(C.red,10,true));
//		label[0].setBounds(30,10,100,50); groupName.setBounds(140,10,100,50);
//		label[1].setBounds(30,120,100,50); slide.setBounds(140,120,100,50); slideLabel.setBounds(200,120,50,50);
		tablePanel.setBorder(BorderFactory.createLineBorder(C.red,10,true));
		
		ta.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		dateTf.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		
		registerPanel.add(label[0]);
		registerPanel.add(groupName);
		registerPanel.add(new JPanel());
		registerPanel.add(label[1]);
		registerPanel.add(slideLabel);
		registerPanel.add(slide);
		registerPanel.add(label[2]);
		registerPanel.add(dateTf);
		registerPanel.add(new JPanel());
		registerPanel.add(label[3]);
		registerPanel.add(ta);
		registerPanel.add(new JPanel());
		
		for(int i = 0; i < rightBtn.length; i++) {
			btnPanel.add(rightBtn[i]);
		}
		tablePanel.add(scroll);
		
		centerPanel.add(registerPanel,BorderLayout.NORTH);
		centerPanel.add(tablePanel,BorderLayout.CENTER);
		rightPanel.add(dataPanel);
		rightPanel.add(btnPanel);
		firstPanel.add(centerPanel,BorderLayout.CENTER);
		firstPanel.add(rightPanel,BorderLayout.EAST);
	}
	
	public void setSlider() {
		slide.setMajorTickSpacing(5);
		slide.setMinorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		slideLabel.setBackground(Color.white);
		slideLabel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		slide.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider s = (JSlider)e.getSource();
				if(!s.getValueIsAdjusting()) {
					int val = (int)slide.getValue();
					slideLabel.setText(val+"명");
			}
		}
	});
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand()=="등록") {
			model.addRow(new String[] {groupName.getSelectedItem().toString(), slideLabel.getText(), dateTf.getText(), ta.getText()});
			table.changeSelection(table.getRowCount()-1, 0, false, false);
		}else if(e.getActionCommand()=="삭제") {
			if(table.getRowCount()<=0) {
				return;
			}
			table.changeSelection(table.getRowCount()-1, 0, false, false);
			model.removeRow(table.getSelectedRow());
			table.changeSelection(table.getRowCount()-1, 0, false, false);
		}else if(e.getActionCommand()=="저장") {
			save();
		}else if(e.getActionCommand()=="엑셀") {
			try {
				Desktop.getDesktop().open(file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void save() {
		File file = new File("./data.xls");
		if(file.isFile()==false) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			WritableWorkbook wb = Workbook.createWorkbook(file);
			WritableSheet[] ws = {wb.createSheet("온라인 희망다이어리", 0),
					              wb.createSheet("희망다이어리", 1),
					              wb.createSheet("헤아림", 2),
					              wb.createSheet("힐링프로그램", 3),
					              wb.createSheet("자조모임", 4),
					              wb.createSheet("e희망교실", 5),
					              wb.createSheet("희망메신저", 6),
			};
			WritableFont font = new WritableFont(WritableFont.TAHOMA,10,WritableFont.BOLD,false);
			WritableCellFormat format = new WritableCellFormat(font);
			format.setBorder(Border.ALL, BorderLineStyle.THIN);
			for(int i = 0; i < titles.length;i++) {
			ws[0].addCell(new Label(i,0,titles[i]));
			}
			
			wb.write();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(C.red);//연한빨강
		g.fillRect(0, 0, getWidth(), getHeight());
		this.repaint();
	}
	
	
}
