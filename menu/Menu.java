package menu;

import figures.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Menu extends JFrame{

  ArrayList<Figure> figs = new ArrayList<Figure>();
  int xLeftLimit,xRightLimit,yTopLimit,yBottomLimit;

  public Menu(int wScreen,int hScreen){

    int start = 40;
    int dist = 20;
    this.defineScreenLimites(wScreen, hScreen);

    figs.add(new Rect(xLeftLimit, yTopLimit, xLeftLimit+50, yBottomLimit-1, Color.blue, Color.black));
    figs.add(new Rect(15, start, 20, 20, Color.blue, Color.black));
    figs.add(new Ellipse(15,start+2*dist, 20, 13, Color.blue, Color.black));
    figs.add(new Circle(15, start+4*dist-4, 20, Color.blue, Color.black));

    figs.add(new Line(15, start+6*dist+6, 15, start+6*dist, Color.blue));
    figs.add(new Line(30, start+6*dist+6, 30, start+6*dist, Color.blue));
    figs.add(new Line(15, start+6*dist, 30, start+6*dist, Color.blue));
    figs.add(new Line(15, start+6*dist+6, 30, start+6*dist+6, Color.blue));
    figs.add(new Line(30, start+6*dist, 35, start+6*dist+3, Color.blue));
    figs.add(new Line(30, start+6*dist+6, 35, start+6*dist+3, Color.blue));
    
    figs.add(new Line(xLeftLimit, yTopLimit, xRightLimit, yTopLimit,Color.black));
    figs.add(new Line(xLeftLimit,yBottomLimit,xRightLimit,yBottomLimit,Color.black));
    figs.add(new Line(xLeftLimit,yBottomLimit,xLeftLimit,yTopLimit,Color.black));
    figs.add(new Line(xRightLimit,yBottomLimit,xRightLimit,yTopLimit,Color.black));
  }

  public void paint (Graphics g) {
    super.paint(g);
    this.drawFigures(g);
  }

  private void defineScreenLimites(int w,int h){
    this.xLeftLimit = 0;
    this.xRightLimit = w-1;
    this.yBottomLimit = h-1;
    this.yTopLimit = 24;
  }

  private void drawFigures(Graphics g){
    for(Figure i:this.figs){
      i.paint(g);
    }
  }

}
