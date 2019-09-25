//Leo Zheng
//10/08/2015
// draw a red Sun with a blule pyramid inside, and then show a tower with steps that 
// connect the Earth to the Heaven symbolized by the Sun

import java.awt.*;

public class Doodle {
      
      public static void main(String[] args) {
         
         DrawingPanel panel = new DrawingPanel(500,300);
         panel.setBackground(Color.WHITE);
         Graphics g = panel.getGraphics();  
         //draw the Sun and the blue pyramid inside it
         mySun(g);
         panel.sleep(100);
         // draw the tower to the Sun
         g.setColor(Color.BLACK);
         road2heaven(g,20,100,-20,280,20);
         
         g.drawString("a rough guide to the Sun",100,50);
         
      }
      
      // the road to the Sun
      public static void road2heaven(Graphics g,int dx,int x0,int dy,int y0,int h) {
         
         for (int i=1; i<= 9; i++) {
         g.drawRect(dx*i+x0-dx,dy*i+y0-dy,-(8*dx/9)*i+8*dx+8*dx/9,h);
         }
      
      }
      
      // draw the Sun
      public static void mySun(Graphics g) {
         // draw the Sun
         g.setColor(Color.RED);
         g.fillOval(192,70,80,80);
         g.setColor(Color.BLUE);
         // draw the pyramid inside the Sun
         Polygon poly = new Polygon();
         poly.addPoint(230,100);
         poly.addPoint(210,130);
         poly.addPoint(250,130);
         g.fillPolygon(poly);
      
      }
      
      
}
   

