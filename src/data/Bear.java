package data;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bear extends Thread{

		
		Image img = new ImageIcon("./temp/bear.png").getImage();
		LoginPanel login = new LoginPanel();
		int x = 100;
		int y = 400;
		int dir = 0; // 0 : 오른쪽, 1 : 왼쪽
		public void run() {
			x = 100;
			y = 400;
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				move();
			}
		}
		
		public void move() {
			if(dir == 0 && x  < 700) x += 20;  
			if(dir == 1 && x > 0) x -= 20;
		}
		
		public void paintComponent(Graphics g) {
			g.drawImage(img, x, y, null);
		}
		
		public void setDir(int dir) {
			this.dir = dir;
		}
}
	
