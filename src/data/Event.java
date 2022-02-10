package data;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JFrame;

public class Event extends JFrame{

	static int enter = KeyEvent.VK_ENTER;
	static int up = KeyEvent.VK_UP;
	static int down = KeyEvent.VK_DOWN;
	static int left = KeyEvent.VK_LEFT;
	static int right = KeyEvent.VK_RIGHT;
	static int space = KeyEvent.VK_SPACE;
	static int escape = KeyEvent.VK_ESCAPE;
	static int one = KeyEvent.VK_1;
	static int two = KeyEvent.VK_2;
	static int three = KeyEvent.VK_3;
	static int four = KeyEvent.VK_4;
	static int five = KeyEvent.VK_5;
	static int six = KeyEvent.VK_6;
	static int seven = KeyEvent.VK_7;
	static int eight = KeyEvent.VK_8;
	static int nine = KeyEvent.VK_9;
	static int zero = KeyEvent.VK_0;
	static int q = KeyEvent.VK_Q;
	static int w = KeyEvent.VK_W;
	static int e = KeyEvent.VK_E;
	static int r = KeyEvent.VK_R;
	static int t = KeyEvent.VK_T;
	static int y = KeyEvent.VK_Y;
	static int u = KeyEvent.VK_U;
	static int i = KeyEvent.VK_I;
	static int o = KeyEvent.VK_O;
	static int p = KeyEvent.VK_P;
	static int a = KeyEvent.VK_A;
	static int s = KeyEvent.VK_S;
	static int d = KeyEvent.VK_D;
	static int f = KeyEvent.VK_F;
	static int g = KeyEvent.VK_G;
	static int h = KeyEvent.VK_H;
	static int j = KeyEvent.VK_J;
	static int k = KeyEvent.VK_K;
	static int l = KeyEvent.VK_L;
	static int z = KeyEvent.VK_Z;
	static int x = KeyEvent.VK_X;
	static int c = KeyEvent.VK_C;
	static int v = KeyEvent.VK_V;
	static int b = KeyEvent.VK_B;
	static int n = KeyEvent.VK_N;
	static int m = KeyEvent.VK_M;
	
	static int mousePress = MouseEvent.MOUSE_PRESSED;
	static int mouseRelease = MouseEvent.MOUSE_RELEASED;
	static Robot robot;
	static String id = "tlsrltjr";
	static char[] cha;
	
	public Event() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
		Toolkit tk = Toolkit.getDefaultToolkit();

	}
	
	public static void startKeyMacro(String id) {
		Event.id = id;
		char[] cha = Event.id.toCharArray();
		Event.cha = Event.id.toCharArray();
		int[] a = new int[cha.length];
			for(int i = 0; i < Event.cha.length; i++) {
				System.out.println(cha[i]);
				a[i]=Character.getNumericValue((cha[i]))+55;
			}
		try {
			robot = new Robot();
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
			robot.keyPress(a[i]);
}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void openAnsys() {
		Desktop desk = Desktop.getDesktop();
		try {
			System.out.println("안시스버튼 작동합니까?");
			desk.browse(new URI("https://ansys.nid.or.kr/login/login.aspx"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void keyPress(int keyEvent) {
		robot.keyPress(keyEvent);
	}
	
	
	public static void keyRelease(int keyEvent) {
		robot.keyRelease(keyEvent);
	}
	
	public static void mousePress(int xPoint, int yPoint, int delay) {
		try {
			robot = new Robot();
			robot.mouseMove(xPoint, yPoint);
			robot.mousePress(InputEvent.BUTTON1_MASK);
			Thread.sleep(delay);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void mouseRelease() {
		robot.mousePress(mouseRelease);
	}
	
	public static void main(String[] args) throws AWTException {
		new Event();
	}
	
}
