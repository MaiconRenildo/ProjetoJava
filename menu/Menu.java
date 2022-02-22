package menu;

import java.awt.*;
import javax.swing.JFrame;
import figures.Rect;
import figures.Ellipse;
import figures.Line;

public class Menu extends JFrame{

  int xLeftLimit,xRightLimit,yTopLimit,yBottomLimit;
  Rect rect,leftRect;
  Ellipse ellipse;
  Line FirstleftLine,endLeftLine,rightVerticalLine,upHorizantaLine,underVerticalLine;

  public Menu(int wScreen,int hScreen){
    this.defineScreenLimites(wScreen, hScreen);
    this.leftRect = new Rect(xLeftLimit, yTopLimit, xLeftLimit+50, yBottomLimit-1, Color.red, Color.black);
    this.rect = new Rect(15, 40, 20, 20, Color.blue, Color.black);
    this.ellipse = new Ellipse(15, 65, 20, 20, Color.red, Color.black);
  }

  public void paint (Graphics g) {
    super.paint(g);
    this.leftRect.paint(g);
    this.rect.paint(g);
    this.ellipse.paint(g);
    this.drawScreenLines(g);
  }

  private void drawScreenLines(Graphics g){
    
    g.drawLine(xLeftLimit, yTopLimit, xRightLimit, yTopLimit); 
    g.drawLine(xLeftLimit,yBottomLimit,xRightLimit,yBottomLimit);
    g.drawLine(xLeftLimit,yBottomLimit,xLeftLimit,yTopLimit);
    g.drawLine(xRightLimit,yBottomLimit,xRightLimit,yTopLimit); 
  }

  private void defineScreenLimites(int w,int h){
    this.xLeftLimit = 0;
    this.xRightLimit = w-1;
    this.yBottomLimit = h-1;
    this.yTopLimit = 24;
  }
  
}