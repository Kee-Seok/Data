package data;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Calendar;

public class Date{

	static Calendar c = Calendar.getInstance();
	static int year = c.get(Calendar.YEAR);
	static int month = c.get(Calendar.MONTH)+1;
	static int weekI = c.get(Calendar.DAY_OF_WEEK);
	static String week;
	static int day = c.get(Calendar.DAY_OF_MONTH);
	static int hour = c.get(Calendar.HOUR_OF_DAY);
	static int minute = c.get(Calendar.MINUTE);
	static int am_pmI = c.get(Calendar.AM_PM);
	static String am_pm;
	static int second = c.get(Calendar.SECOND);
	public static String getDate() {
		switch(weekI) {
		case 2 :
			week = "월";
			break;
		case 3 :
			week = "화";
			break;
		case 4 :
			week = "수";
			break;
		case 5 :
			week = "목";
			break;
		case 6 :
			week = "금";
			break;
		case 0 :
			week = "토";
			break;
		case 1 :
			week = "일";
			break;
		}
		switch(am_pmI) {
		case 0 :
			am_pm = "AM";
			break;
		case 1 :
			am_pm = "PM";
			break;
		
		}
		System.out.println(am_pm+" "+hour+"시 " +minute+"분 입니다.");
		return year+"년 "+month+"월 "+day+"일 "+"("+week+")";
	}
	public static String getTime() {
		c = c.getInstance();
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		second = c.get(Calendar.SECOND);
		return hour+"시 "+minute+"분 "+second+ "초";
	}

	public static void paintComponent(Graphics g, String date) {
		g.setColor(Color.white);
		g.setFont(new Font("Serif",Font.BOLD,20));
		g.drawString(date, 27, 100);
	}
	
}
