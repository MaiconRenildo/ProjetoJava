package menu;

import figures.*;
import java.awt.*;
import javax.swing.JFrame;
import java.util.ArrayList;

public class Menu extends JFrame{

  private int start = 40;
  private int menuStatus = 1;
  private int lineOrBackground = 1;
  private int xLeftLimit,xRightLimit,yTopLimit,yBottomLimit;
  private Figure line,background,border;
  private Figure selected = null;
  private ArrayList<Figure> figs = new ArrayList<Figure>();
  private ArrayList <Color> colors = new ArrayList<Color>();

  protected Figure area;
  
  public Menu(int wScreen,int hScreen){
    this.defineScreenLimites(wScreen, hScreen);
    this.createBorder();
    this.createRectsToChangeBackgrounds();
    this.fillColors();
    this.createColorOptions();
    this.createArea();
  }

  private void createRectsToChangeBackgrounds(){
    this.line = new Rect(8, start, 14, 14, Color.black, Color.white);
    this.background = new Rect(28, start, 14, 14, Color.black, Color.white);
  }

  private void createBorder(){
    this.border = new Rect(xLeftLimit, yTopLimit, xLeftLimit+50, yBottomLimit-19, Color.black, new Color(211,211,211));
  }

  private void fillColors(){
    colors.add(Color.white);
    colors.add(new Color(28,28,28));
    colors.add(new Color(105,105,105));
    colors.add(new Color(0,100,0));
    colors.add(Color.green);
    colors.add(new Color(0,255,255));
    colors.add(new Color(65,105,225));
    colors.add(Color.blue);
    colors.add(new Color(75,0,130));
    colors.add(new Color(220,20,60));
    colors.add(Color.red);
    colors.add(Color.pink);
    colors.add(new Color(230,230,250));
    colors.add( new Color(255,140,0));
    colors.add(Color.yellow);
  }

  private void createColorOptions(){
    start = start+10;
    for (int i = 0; i <= colors.size()-1; i++) {
      start = start +20;
      figs.add(new Rect(15, start, 20, 20, Color.black, colors.get(i)));
    }
  }

  private void createArea(){
    this.area = new Rect(xLeftLimit, yTopLimit, xRightLimit, yBottomLimit, Color.black, Color.white);
  }

  private void defineScreenLimites(int w,int h){
    this.xLeftLimit = 0;
    this.xRightLimit = w-1;
    this.yBottomLimit = h-1;
    this.yTopLimit = 24;
  }

  private void drawFigures(Graphics g){
    // this.area.paint(g);
    if(this.menuStatus==1){
      this.border.paint(g);
      this.line.paint(g);
      this.background.paint(g);
      for(Figure i:this.figs){
        i.paint(g);
      }
    }
  }

  public void paint (Graphics g) {
    super.paint(g);
    this.drawFigures(g);
  }

  public void changeLimits(int w, int h){
    this.xRightLimit = w-2;
    this.yBottomLimit = h-26;
    this.area = new Rect(xLeftLimit, yTopLimit, xRightLimit, yBottomLimit, Color.black, Color.white);
    this.border = new Rect(xLeftLimit, yTopLimit, xLeftLimit+50, yBottomLimit, Color.black, new Color(211,211,211));
  }

  public void changeColorToUp(){
    if(this.lineOrBackground==0){
      this.lineOrBackground=1;
    }else{
      this.lineOrBackground=0;
    }
  }

  public void changeMenuStatus(){
    if(this.menuStatus==1){
      this.menuStatus=0;
    }else{
      this.menuStatus=1;
    }
  }

  public Figure verifyFocus(int x,int y,Figure fig ){

    if(this.menuStatus==1){

      Figure figureReceived = fig;
      int found = 0;
  
      for (int i = figs.size()-1; i >= 0; i--) {
  
        if(figs.get(i).clicked(x,y)){
  
          selected = figs.get(i);
  
          if(this.lineOrBackground==0){
            
            line.changeBackgroundColor(figs.get(i).getBackgroundColor());
  
            if(fig!=null){
  
              Color colorReceived = figs.get(i).getBackgroundColor();
              figureReceived.changeLineColor(colorReceived);
              fig.changeLineColor(colorReceived);
              found = 1;
  
            }
  
          }else{
  
            background.changeBackgroundColor(figs.get(i).getBackgroundColor());
  
            if(fig!=null){
              
              Color colorReceived = figs.get(i).getBackgroundColor();
              figureReceived.changeBackgroundColor(colorReceived);
              fig.changeBackgroundColor(selected.getBackgroundColor());
              found = 1;
  
            }
  
          }
  
          i = 0;
        }
  
      }
  
      if(found==1){
        return figureReceived;
      }else{
        return null;
      }
      
    }else{
      return null;
    }
  }
}
