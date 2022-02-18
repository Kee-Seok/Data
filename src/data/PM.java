package data;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class PM{

	
	static ArrayList<String> january, febrary, march, april, may, june, july, august, september,october, november, december;
	static ArrayList<Integer> januaryM, febraryM, marchM, aprilM, mayM, juneM, julyM, augustM, septemberM,octoberM, novemberM, decemberM;
	static int januaryMyung, febraryMyung, marchMyung, aprilMyung, mayMyung, juneMyung, julyMyung, augustMyung, septemberMyung,
				octoberMyung,novemberMyung, decemberMyung;
	static int month[] = new int[12];
	public static void setPerformance(DefaultTableModel model) {
		String[] a = new String[model.getRowCount()];
		
		january = new ArrayList<>(); //각 행의 날짜 텍스트에서 몇월인지만 확인할 변수임.
		febrary = new ArrayList<>();
		march = new ArrayList<>();
		april = new ArrayList<>();
		may = new ArrayList<>();
		june = new ArrayList<>();
		july = new ArrayList<>();
		august = new ArrayList<>();
		september = new ArrayList<>();
		october = new ArrayList<>();
		november = new ArrayList<>();
		december = new ArrayList<>();
		
		januaryM = new ArrayList<>(); //각 행의 인원만 int값으로 추출해서 넣을 변수임.
		febraryM = new ArrayList<>();
		marchM = new ArrayList<>();
		aprilM = new ArrayList<>();
		mayM = new ArrayList<>();
		juneM = new ArrayList<>();
		julyM = new ArrayList<>();
		augustM = new ArrayList<>();
		septemberM = new ArrayList<>();
		octoberM = new ArrayList<>();
		novemberM = new ArrayList<>();
		decemberM = new ArrayList<>();
		
		remove();
		
		for(int i = 0; i < model.getRowCount(); i++) {
		a[i] = model.getValueAt(i,2).toString().substring(6,8); //JTable 날짜에서 몇월인지만 카운팅한다.
		if(a[i].equals("1월")) { //i == 0,1,2,3이 나옴 (JTable 1월 인덱스임)
			january.add(model.getValueAt(i,2).toString());
			januaryM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			januaryMyung += januaryM.get(i);
			
		}else if(a[i].equals("2월")) { //i == 4,5,6가 나옴
			febrary.add(model.getValueAt(i,2).toString());
			febraryM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			febraryMyung += febraryM.get(i-january.size());
			
		}else if(a[i].equals("3월")) {
			march.add(model.getValueAt(i,2).toString());
			marchM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			marchMyung += marchM.get(i-january.size()-febrary.size());
		}else if(a[i].equals("4월")) {
			april.add(model.getValueAt(i,2).toString());
			aprilM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			aprilMyung += aprilM.get(i-january.size()-febrary.size()-march.size());
		}else if(a[i].equals("5월")) {
			may.add(model.getValueAt(i,2).toString());
			mayM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			mayMyung += mayM.get(i-january.size()-febrary.size()-march.size()-april.size());
		}else if(a[i].equals("6월")) {
			june.add(model.getValueAt(i,2).toString());
			juneM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			juneMyung += juneM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size());
		}else if(a[i].equals("7월")) {
			july.add(model.getValueAt(i,2).toString());
			julyM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			julyMyung += julyM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size()-june.size());
		}else if(a[i].equals("8월")) {
			august.add(model.getValueAt(i,2).toString());
			augustM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			augustMyung += augustM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size()-june.size()-july.size());
		}else if(a[i].equals("9월")) {
			september.add(model.getValueAt(i,2).toString());
			septemberM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			septemberMyung += septemberM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size()-june.size()-july.size()
					          -august.size());
		}else if(a[i].equals("10")) {
			october.add(model.getValueAt(i,2).toString());
			octoberM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			octoberMyung += octoberM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size()-june.size()-july.size()
					          -august.size()-september.size());
		}else if(a[i].equals("11")) {
			november.add(model.getValueAt(i,2).toString());
			novemberM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			novemberMyung += novemberM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size()-june.size()-july.size()
					          -august.size()-september.size()-october.size());
		}else if(a[i].equals("12")) {
			december.add(model.getValueAt(i,2).toString());
			decemberM.add(Integer.parseInt(model.getValueAt(i,1).toString().replace("명", "")));
			decemberMyung += decemberM.get(i-january.size()-febrary.size()-march.size()-april.size()-may.size()-june.size()-july.size()
					          -august.size()-september.size()-october.size()-november.size());
		}
		}
	}
	public static void remove() {
		january.removeAll(january);
		febrary.removeAll(febrary);
		march.removeAll(march);
		april.removeAll(april);
		may.removeAll(may);
		june.removeAll(june);
		july.removeAll(july);
		august.removeAll(august);
		september.removeAll(september);
		october.removeAll(october);
		november.removeAll(november);
		december.removeAll(december);
		
		januaryM.removeAll(januaryM);
		febraryM.removeAll(febraryM);
		marchM.removeAll(marchM);
		aprilM.removeAll(aprilM);
		mayM.removeAll(mayM);
		juneM.removeAll(juneM);
		julyM.removeAll(julyM);
		augustM.removeAll(augustM);
		septemberM.removeAll(septemberM);
		octoberM.removeAll(octoberM);
		novemberM.removeAll(novemberM);
		decemberM.removeAll(decemberM);
		januaryMyung = 0;
		febraryMyung = 0; 
		marchMyung = 0;
		aprilMyung = 0;
		mayMyung = 0;
		juneMyung = 0;
		julyMyung = 0;
		augustMyung = 0;
		septemberMyung = 0;
		octoberMyung = 0;
		novemberMyung = 0;
		decemberMyung = 0;
	}
	
}
	
