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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class HearimPanel extends JPanel implements ActionListener{

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
	static int sum;
	static JLabel performanceLabel = new JLabel("-",JLabel.CENTER);
	//온라인 희망다이어리의 모든 컬러, 시트번호, JComboBox 네이밍 등 한꺼번에 바뀌어야 될 것들
	Color color = C.yellow;
	int sheetNum = 2;
	String groupname = "헤아림";
	//-------------------------------------------------------------------
	
	
	
	String[] group = {groupname, "없음"};
	JComboBox<String> groupName = new JComboBox<String>(group);
	JSlider slide = new JSlider(0,20);
	JLabel slideLabel = new JLabel("10명",SwingConstants.CENTER);
	JButton[] rightBtn = {new JButton("등록"),
			              new JButton("삭제"),
			              new JButton("저장"),
			              new JButton("엑셀")};
	JTextField dateTf = new JTextField(20);
	static int theNumberOfEntrance; //참가인원 라벨에서 명을 빼고 숫자텍스트만 변수에 넣을꺼임 (그래야 계산하기 수월함)
	JTextArea ta = new JTextArea();
	
	
	static String[] titles = {"반이름", "참가인원", "날짜", "운영일지"};
	static DefaultTableModel model = new DefaultTableModel(titles,0);
	static JTable table = new JTable(model);
	JScrollPane scroll = new JScrollPane(table);
	
	static File file = new File("./Data.xls");
	
	static WritableWorkbook wb;
	static WritableSheet[] ws = new WritableSheet[7];
	static Sheet[] s = new Sheet[7];
	public HearimPanel(){
		setLayout(null);

		setPanel();
		setSlider();
		add(firstPanel);
		setPerformanceLabel();
	}

	public static void setPerformanceLabel() {
		performanceLabel.setText("<html>"+model.getRowCount()+"건"
                + "<br>"
                + getPerformanceNumber() +"명"
                		+ "</html>");
		sum = 0; //참가인원의 ~명에서 글자를 빼고 숫자만 산출 후 더할꺼임. 이 메소드를 실행 후에는 반드시 sum을 =0으로 초기화시켜줘야된다.
	}
	public static int getPerformanceNumber() {  //참가인원의 ~명에서 글자를 빼고 숫자만 산출 후 더할꺼임. 이 메소드를 실행 후에는 반드시 sum을 =0으로 초기화시켜줘야된다.
		for(int r = 0; r < model.getRowCount(); r++) {
		    theNumberOfEntrance = Integer.parseInt(model.getValueAt(r,1).toString().replace("명", ""));
			sum += theNumberOfEntrance;
		}
		return sum;
	}
	
	public void setTable() {
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(450);
		scroll.setBounds(11,10,970,450);
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER); //DefaultTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for(int r = 0; r < model.getColumnCount(); r++) {
		table.getColumnModel().getColumn(r).setCellRenderer(render);
		}
	}
	
	public void setBtn() {
		for(int i = 0; i < rightBtn.length; i++) {
		rightBtn[i].setFont(new Font("Serif",Font.BOLD,15));
		rightBtn[i].setBackground(color);
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
		registerPanel.setBorder(BorderFactory.createLineBorder(color,5,true));
//		label[0].setBounds(30,10,100,50); groupName.setBounds(140,10,100,50);
//		label[1].setBounds(30,120,100,50); slide.setBounds(140,120,100,50); slideLabel.setBounds(200,120,50,50);
		tablePanel.setLayout(null);
		tablePanel.setBorder(BorderFactory.createLineBorder(color,5,true));
		
		dateTf.setHorizontalAlignment(JTextField.CENTER);
		ta.setToolTipText("운영 내용을 입력하세요.");
		ta.setLineWrap(true);
		ta.setWrapStyleWord(true);
		
		ta.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		dateTf.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		dateTf.setText(Date.getDate());
		performanceLabel.setFont(new Font("Serif",Font.BOLD,20));
		performanceLabel.setForeground(Color.black);
		performanceLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black,2),"실적"));

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
		registerPanel.add(performanceLabel);
		
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
		if(e.getActionCommand()=="등록") { //JTable에 정보를 등록할때 사용할 버튼이다.
			if(model.getRowCount()==0) {
				model.addRow(new String[] {groupName.getSelectedItem().toString(), slideLabel.getText(), dateTf.getText(), ta.getText()});
			}else {
			model.insertRow(table.getSelectedRow()+1,new String[] {groupName.getSelectedItem().toString(), slideLabel.getText(), dateTf.getText(), ta.getText()});
			}
			table.changeSelection(table.getSelectedRow()+1, 0, false, false);
			setPerformanceLabel();
		}else if(e.getActionCommand()=="삭제") {
			if(table.getRowCount()<=0) { //JTable에 아무것도 없으면 return한다.
				return;
			}
			if(table.getSelectedRow() < model.getRowCount()-1) {
				int getSelectedRow = table.getSelectedRow();
				model.removeRow(table.getSelectedRow());
				table.changeSelection(getSelectedRow, 0, false, false);
			}else if(table.getSelectedRow()==model.getRowCount()-1) {
				int getSelectedRow = table.getSelectedRow();
				model.removeRow(table.getSelectedRow());
				table.changeSelection(getSelectedRow-1, 0, false, false);
			}
			setPerformanceLabel();
		}else if(e.getActionCommand()=="저장") {
			try {
				Excel.save();
				setPerformanceLabel();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}else if(e.getActionCommand()=="엑셀") {
			try {
				Desktop.getDesktop().open(Excel.file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public static void getDataFromExcel() {
		try {
			Workbook wb = Workbook.getWorkbook(Excel.file);
			
			for(int i = 0; i < 7; i++) {
				s[i] = wb.getSheet(i);
			}
			
			//j인덱스의 시트 정보에서 셀이 있는지 없는지를 모두 판단 후 JTable에 정보를 넣는다.
			for(int j = 0; j < 7; j++) {
			for(int i = 1; i < s[j].getRows(); i++) {
					model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
							                   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
					});
				}
			}
			wb.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void save() {
		file = new File("./data.xls");
			try {
				wb = Workbook.createWorkbook(file);
				String[] className = {"온라인 희망다이어리", "희망다이어리", "헤아림", "힐링프로그램", "자조모임","e희망교실","희망메신저"};
				for(int i = 0; i< className.length; i++) {
				ws[i] = wb.createSheet(className[i], i);
				}
				WritableFont font = new WritableFont(WritableFont.TAHOMA,10,WritableFont.BOLD,false);
				WritableCellFormat format = new WritableCellFormat(font);
				format.setBorder(Border.ALL, BorderLineStyle.THIN);
				format.setAlignment(Alignment.CENTRE);
			for(int i = 0; i < titles.length;i++) {
			ws[sheetNum].addCell(new Label(i,0,titles[i],format));
			}
			for(int i = 0; i < model.getRowCount(); i++) {
				for(int j = 0; j < model.getColumnCount(); j++) {
					ws[sheetNum].addCell(new Label(j,i+1,(String)model.getValueAt(i, j),format));
				}
			}
			ws[sheetNum].setColumnView(0, 22);
			ws[sheetNum].setColumnView(1, 10);
			ws[sheetNum].setColumnView(2, 22);
			ws[sheetNum].setColumnView(3, 50);
			wb.write();
			wb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	
	public void paintComponent(Graphics g) {
		g.setColor(color);//연한빨강
		g.fillRect(0, 0, getWidth(), getHeight());
		this.repaint();
	}
	
	
}
