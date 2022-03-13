import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import figures.*;
import menu.Menu;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame {

    ArrayList<Figure> figs = new ArrayList<Figure>();
    Menu menu;
    int w,h;

    PackFrame () {
        this.addWindowListener (
            new WindowAdapter() {
                public void windowClosing (WindowEvent e) {
                    System.exit(0);
                }
            }
        );
        this.setTitle("Java Packages");
        this.setSize(480, 480);
        this.setVisible(true);

        this.addKeyListener(new KeyAdapter(){

          public void keyPressed(KeyEvent key){

            Random random =  new Random();

            int xPosition = random.nextInt(w)+50;
            int yPosition = random.nextInt(h)+25;
            int width = random.nextInt(50);
            int height = random.nextInt(50);

            if(xPosition+width>w){
              xPosition=xPosition-width-53;
            }

            if(yPosition+height>h){
              yPosition=yPosition-height-28;
            }

            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);

            if(key.getKeyChar() == 'e'){
              figs.add(new Ellipse(xPosition,yPosition, width,height,new Color(r,g,b),new Color(g,b,r)));
              repaint();
            }

            if(key.getKeyChar()=='r'){
              figs.add(new Rect(xPosition,yPosition, width,height,new Color(r,g,b),new Color(g,b,r)));
              repaint();
            }

            if(key.getKeyChar()=='l'){
              figs.add(new Line(xPosition,yPosition,xPosition+width,yPosition+height,new Color(r,g,b)));
              repaint();
            }

            if(key.getKeyChar()=='c'){

              if(yPosition+width>h){
                yPosition=yPosition-height-28;
              }

              figs.add(new Circle(xPosition,yPosition,width,new Color(r,g,b),new Color(g,b,r)));
              repaint();
            }
            
          }

        });
    }


    public void paint (Graphics g) {
        super.paint(g);
        this.getScreenLimits();
        this.drawFigures(g);
        this.Menu(g);
    }


    public void drawFigures(Graphics g){
      for(Figure i:this.figs){
        i.paint(g);
      }
    }

    public void getScreenLimits(){
      w = this.getWidth();
      h = this.getHeight();
    };

    public void Menu(Graphics g){
      this.menu = new Menu(w,h);
      this.menu.paint(g);
    }

    
}