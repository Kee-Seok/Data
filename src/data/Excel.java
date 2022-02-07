package data;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
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

	public static File file = new File("./Data.xls");
	
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
	
 	public Excel() {
 		try {
			setFormat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}

	public static void setFormat() throws Exception {
		wb = Workbook.createWorkbook(file);
		for(sheetNum = 0; sheetNum < sheetNames.length; sheetNum++) { //시트 설정해주고 생성시키기
			ws[sheetNum] = wb.createSheet(sheetNames[sheetNum], sheetNum);
		}
		sheetFont = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false);
		format = new WritableCellFormat(sheetFont);
		format.setAlignment(Alignment.CENTRE);
		format.setBorder(Border.ALL, BorderLineStyle.THICK,Colour.BROWN);
	}
	public static void save() throws Exception {
			setFormat();
			if(OnlineHopePanel.model.getRowCount()==0) {
				System.out.println("OnlineHopePanel.model.getRowCount : " + OnlineHopePanel.model.getRowCount());
				System.out.println("HopePanel.model.getRowCount : " + HopePanel.model.getRowCount());
			}else {
				System.out.println("OnlineHopePanel.model.getRowCount : " + OnlineHopePanel.model.getRowCount());
				System.out.println("HopePanel.model.getRowCount : " + HopePanel.model.getRowCount());
			for(int r = 0; r < OnlineHopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < OnlineHopePanel.model.getColumnCount(); c++) {
					System.out.println(OnlineHopePanel.model.getValueAt(r, c));
					ws[0].addCell(new Label(c,r+1,OnlineHopePanel.model.getValueAt(r, c).toString(),format));
				}
			}}
			if(HopePanel.model.getRowCount()==0) {
				System.out.println("OnlineHopePanel.model.getRowCount : " + OnlineHopePanel.model.getRowCount());
				System.out.println("HopePanel.model.getRowCount : " + HopePanel.model.getRowCount());
			}else {
			for(int r = 0; r < HopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < HopePanel.model.getColumnCount(); c++)
					ws[1].addCell(new Label(c,r+1,OnlineHopePanel.model.getValueAt(r, c).toString(),format));
			}
			}
			int[] cellWidth = {18, 8, 18, 50};
			for(int i = 0; i < 4; i++) {
				ws[0].setColumnView(i, cellWidth[i]);
				ws[1].setColumnView(i, cellWidth[i]);
			}
			
			wb.write();
			wb.close();
	}
	
	public static void getDataFromExcel1() throws Exception{
		Workbook workbook = Workbook.getWorkbook(file);
		Sheet[] sheet = workbook.getSheets();
		for(int i = 0; i < sheet.length; i++) {
			for(int r = 0 ; r < sheet[i].getRows(); r++) {
				for(int c = 0; c < sheet[i].getColumns(); c++) {
				
		        Cell[] cell = {sheet[i].getCell(0,r),
		        		       sheet[i].getCell(1,r),
		        		       sheet[i].getCell(2,r),
		        		       sheet[i].getCell(3,r),
		        };
		        String[] cellData = {cell[0].getContents(), 
		        		             cell[1].getContents(), 
		        		             cell[2].getContents(), 
		        		             cell[3].getContents() 
		        };
		        System.out.println(sheet[i].getName());
		        System.out.println(sheet[i].getCell(c,r).getContents());
		        if(i == 0) {
		        OnlineHopePanel.model.addRow(cellData);
		        }else if(i == 1) {
		        HopePanel.model.addRow(cellData);
		        }
		                                  
		        };
			}
			}
		workbook.close();
		}
	public static void getDataFromExcel() {
		try {
			Workbook wb = Workbook.getWorkbook(file);
			
			for(int i = 0; i < 7; i++) {
				OnlineHopePanel.s[i] = wb.getSheet(i);
			}
			
			//j인덱스의 시트 정보에서 셀이 있는지 없는지를 모두 판단 후 JTable에 정보를 넣는다.
			for(int j = 0; j < 7; j++) {
			for(int i = 1; i < OnlineHopePanel.s[j].getRows(); i++) {
					OnlineHopePanel.model.addRow(new String[] {OnlineHopePanel.s[j].getCell(0,i).getContents(),OnlineHopePanel.s[j].getCell(1,i).getContents()
							                   ,OnlineHopePanel.s[j].getCell(2,i).getContents(),OnlineHopePanel.s[j].getCell(3,i).getContents()
					});
				}
			}
			for(int i = 0; i < 7; i++) {
				HopePanel.s[i] = wb.getSheet(i);
			}
			
			//j인덱스의 시트 정보에서 셀이 있는지 없는지를 모두 판단 후 JTable에 정보를 넣는다.
			for(int j = 0; j < 7; j++) {
			for(int i = 1; i < OnlineHopePanel.s[j].getRows(); i++) {
					HopePanel.model.addRow(new String[] {HopePanel.s[j].getCell(0,i).getContents(),HopePanel.s[j].getCell(1,i).getContents()
							                   ,HopePanel.s[j].getCell(2,i).getContents(),HopePanel.s[j].getCell(3,i).getContents()
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
	
	public static void main(String[] args) throws Exception {
		new Excel();
		getDataFromExcel();
	}
	}
