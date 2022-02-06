package data;

import java.io.File;

import javax.swing.JPanel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Excel{

	static File file = new File("./temp/data.xls");
	
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
	static JPanel[] panels = {new OnlineHopePanel(), new HopePanel()};
 	
	public Excel() throws Exception {
		wb = Workbook.createWorkbook(file);
		for(sheetNum = 0; sheetNum < sheetNames.length; sheetNum++) { //시트 설정해주고 생성시키기
		ws[sheetNum] = wb.createSheet(sheetNames[sheetNum], sheetNum);
		}
		sheetFont = new WritableFont(WritableFont.ARIAL,12,WritableFont.BOLD,false);
		format = new WritableCellFormat(sheetFont);
		format.setAlignment(Alignment.CENTRE);
		format.setBorder(Border.ALL, BorderLineStyle.THICK,Colour.BROWN);
		save();
	}
	public static void save() throws Exception {
			
			for(int r = 0; r < OnlineHopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < OnlineHopePanel.model.getColumnCount(); c++)
					ws[0].addCell(new Label(c,r,OnlineHopePanel.model.getValueAt(r, c).toString()));
			}
			for(int r = 0; r < HopePanel.model.getRowCount(); r++) { 
				for(int c = 0; c < HopePanel.model.getColumnCount(); c++)
					ws[1].addCell(new Label(c,r,OnlineHopePanel.model.getValueAt(r, c).toString()));
			}
			wb.write();
			wb.close();
	}
	
	public static void getDataFromExcel() throws Exception{
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
	
	public static void main(String[] args) throws Exception {
		new Excel();
		getDataFromExcel();
	}
	}
