package data;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Excel{

	public static File file = new File("./temp/data.xls");
	
	static WritableWorkbook wb;
	static String[] sheetNames = {"온라인 희망다이어리",
			                      "희망다이어리", 
			                      "헤아림", 
			                      "힐링프로그램", 
			                      "자조모임",
			                      "e희망교실",
			                      "희망메신저"};
	static int sheetNum = sheetNames.length;
	static WritableSheet[] ws = new WritableSheet[sheetNum];
	static WritableFont sheetFont;
	static WritableCellFormat format;
	static Workbook workbook; 
 	public Excel() {
 		try {
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

	public static void setFormat() throws Exception {
		if(!file.isFile()) {
			file.createNewFile();
		}
		wb = Workbook.createWorkbook(file);
		for(sheetNum = 0; sheetNum < sheetNames.length; sheetNum++) { //시트 설정해주고 생성시키기
			ws[sheetNum] = wb.createSheet(sheetNames[sheetNum], sheetNum);
		}
		sheetFont = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false);
		format = new WritableCellFormat(sheetFont);
		format.setAlignment(Alignment.CENTRE);
		format.setBorder(Border.ALL, BorderLineStyle.THIN,Colour.PALETTE_BLACK);
	}
	public static void save() throws Exception {
			setFormat();
			for(int c = 0; c < OnlineHopePanel.model.getColumnCount(); c++) {
				ws[0].addCell(new Label(c,0,OnlineHopePanel.titles[c],format));
			}
			for(int r = 0; r < OnlineHopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < OnlineHopePanel.model.getColumnCount(); c++) {
					System.out.println(OnlineHopePanel.model.getValueAt(r, c));
					ws[0].addCell(new Label(c,r+1,OnlineHopePanel.model.getValueAt(r, c).toString(),format));
				}
			}
			for(int c = 0; c < HopePanel.model.getColumnCount(); c++) {
				ws[1].addCell(new Label(c,0,HopePanel.titles[c],format));
			}
			for(int r = 0; r < HopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < HopePanel.model.getColumnCount(); c++)
					ws[1].addCell(new Label(c,r+1,HopePanel.model.getValueAt(r, c).toString(),format));
			}
			for(int c = 0; c < HearimPanel.model.getColumnCount(); c++) {
				ws[2].addCell(new Label(c,0,HearimPanel.titles[c],format));
			}
			for(int r = 0; r < HearimPanel.model.getRowCount(); r++) { 
				for(int c = 0; c < HearimPanel.model.getColumnCount(); c++) {
					System.out.println(HearimPanel.model.getValueAt(r, c));
					ws[2].addCell(new Label(c,r+1,HearimPanel.model.getValueAt(r, c).toString(),format));
				}
			}
			for(int c = 0; c < HealingPanel.model.getColumnCount(); c++) {
				ws[3].addCell(new Label(c,0,HealingPanel.titles[c],format));
			}
			for(int r = 0; r < HealingPanel.model.getRowCount(); r++) { 
				for(int c = 0; c < HealingPanel.model.getColumnCount(); c++) {
					System.out.println(HealingPanel.model.getValueAt(r, c));
					ws[3].addCell(new Label(c,r+1,HealingPanel.model.getValueAt(r, c).toString(),format));
				}
			}
			for(int c = 0; c < JajoPanel.model.getColumnCount(); c++) {
				ws[4].addCell(new Label(c,0,JajoPanel.titles[c],format));
			}
			for(int r = 0; r < JajoPanel.model.getRowCount(); r++) { 
				for(int c = 0; c < JajoPanel.model.getColumnCount(); c++) {
					System.out.println(JajoPanel.model.getValueAt(r, c));
					ws[4].addCell(new Label(c,r+1,JajoPanel.model.getValueAt(r, c).toString(),format));
				}
			}
			for(int c = 0; c < EHopePanel.model.getColumnCount(); c++) {
				ws[5].addCell(new Label(c,0,EHopePanel.titles[c],format));
			}
			for(int r = 0; r < EHopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < EHopePanel.model.getColumnCount(); c++) {
					System.out.println(EHopePanel.model.getValueAt(r, c));
					ws[5].addCell(new Label(c,r+1,EHopePanel.model.getValueAt(r, c).toString(),format));
				}
			}
			for(int c = 0; c < HopeMessengerPanel.model.getColumnCount(); c++) {
				ws[6].addCell(new Label(c,0,HopeMessengerPanel.titles[c],format));
			}
			for(int r = 0; r < HopeMessengerPanel.model.getRowCount(); r++) { 
				for(int c = 0; c < HopeMessengerPanel.model.getColumnCount(); c++) {
					System.out.println(HopeMessengerPanel.model.getValueAt(r, c));
					ws[6].addCell(new Label(c,r+1,HopeMessengerPanel.model.getValueAt(r, c).toString(),format));
				}
			}
			
			int[] cellWidth = {27, 10, 22, 80};
			for(int i = 0; i < 4; i++) {
				ws[0].setColumnView(i, cellWidth[i]);
				ws[1].setColumnView(i, cellWidth[i]);
				ws[2].setColumnView(i, cellWidth[i]);
				ws[3].setColumnView(i, cellWidth[i]);
				ws[4].setColumnView(i, cellWidth[i]);
				ws[5].setColumnView(i, cellWidth[i]);
			}
			
			wb.write();
			wb.close();
	}
	
	public static void getDataFromExcel() throws Exception {
		try {
			//save();
			workbook = Workbook.getWorkbook(file);
			Sheet[] s = new Sheet[workbook.getSheets().length];
			for(int i = 0; i < workbook.getSheets().length; i++) {
				s[i] = workbook.getSheet(i);
			    System.out.println(workbook.getSheet(0).getName());
			}
			int j = 0;
			//j인덱스의 시트 정보에서 셀이 있는지 없는지를 모두 판단 후 JTable에 정보를 넣는다.
			for(j = 0; j < workbook.getSheets().length; j++) {
			if(j == 0) {
			for(int i = 1; i < s[j].getRows(); i++) {
					OnlineHopePanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
							                   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
					});
				}
			}else if(j == 1) {
			for(int i = 1; i < s[j].getRows(); i++) {
					HopePanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
						                   	   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
				});
			}
			}else if(j == 2){
			for(int i = 1; i < s[j].getRows(); i++) {
					HearimPanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
						                   	   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
				});
			}
			}else if(j == 3){
			for(int i = 1; i < s[j].getRows(); i++) {
					HealingPanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
						                   	   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
				});
			}
			}else if(j == 4){
			for(int i = 1; i < s[j].getRows(); i++) {
					JajoPanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
						                   	   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
				});
			}
			}else if(j == 5){
			for(int i = 1; i < s[j].getRows(); i++) {
					EHopePanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
						                   	   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents(),s[j].getCell(4,i).getContents()
				});
			}			
			}else if(j == 6){
			for(int i = 1; i < s[j].getRows(); i++) {
					HopeMessengerPanel.model.addRow(new String[] {s[j].getCell(0,i).getContents(),s[j].getCell(1,i).getContents()
						                   	   ,s[j].getCell(2,i).getContents(),s[j].getCell(3,i).getContents()
				});
			}
			}else {
				return;
			}
			}
			//j인덱스의 시트 정보에서 셀이 있는지 없는지를 모두 판단 후 JTable에 정보를 넣는다.
			workbook.close();
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	}
