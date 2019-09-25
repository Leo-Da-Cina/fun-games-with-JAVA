//Ling Zheng
//10/08/2015
//CSE 143X
//HW #2 
// draw CafeWall

import java.awt.*;

public class CafeWall {
      
      public static void main(String[] args) {
         //draw the panel that hosts the CafeWall
         DrawingPanel panel = new DrawingPanel(650,400);
         //set the backbround as gray
         panel.setBackground(Color.LIGHT_GRAY);
         Graphics g = panel.getGraphics();
         //plot upper left free-standing row
         drawArow(g,0,0,4,20);
         //plot middle left free-standing row
         drawArow(g,50,70,5,30);
         //plot lower left grid
         drawAgrid(g,10,150,4,25,0);
         //plot lower middle grid
         drawAgrid(g,250,200,3,25,10);
         //plot upper right grid
         drawAgrid(g,400,20,2,35,35);
         //plot lower right grid
         drawAgrid(g,425,180,5,20,10);
 
      }
      
      // method for drawing a single row of given size and position
      public static void drawArow (Graphics g, int xcoord, int ycoord, int npair, int boxsize) {   
         
         for(int i=1; i <= npair; i++){
         
            int x0 = xcoord+2*boxsize*(i-1);
            // draw black square
            g.setColor(Color.BLACK);
            g.fillRect(x0,ycoord,boxsize,boxsize);
            // draw X line
            g.setColor(Color.BLUE);
            g.drawLine(x0,ycoord,x0+boxsize,ycoord+boxsize);
            g.drawLine(x0,ycoord+boxsize,x0+boxsize,ycoord);
            //draw while square
            g.setColor(Color.WHITE);
            g.fillRect(x0+boxsize,ycoord,boxsize,boxsize);
            
         }
      
      }
      
      // method for drawing a grid with give size and position     
      public static void drawAgrid(Graphics g, int xcoord,int ycoord,int npair,int boxsize,int rowoffset){
         //set the motar constant that seperates rows
         int MOTAR = 2;
         for(int i =1;i <= npair; i++){
            //draw first row
            drawArow(g,xcoord,ycoord+2*(boxsize+MOTAR)*(i-1),npair,boxsize);
            //draw second row
            drawArow(g,xcoord+rowoffset,ycoord+boxsize+MOTAR+2*(boxsize+MOTAR)*(i-1),npair,boxsize);
         
         }   
      
      }  

}