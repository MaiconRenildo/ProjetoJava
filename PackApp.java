import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import figures.*;
import menu.Menu;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame implements MouseMotionListener,MouseListener{

    Figure focus = null;
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Menu menu;
    int w,h,x,y,patternSize;
    JLabel mousePosition;

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
        this.addMouseMotionListener(this);
        this.addMouseListener(this);       

        this.addKeyListener(new KeyAdapter(){

          public void keyPressed(KeyEvent key){

            patternSize = 60;

            switch(key.getKeyCode()){
              case 37:
                focus.moveLeft();
                repaint();
                break;
              case 38:
                focus.moveUp();
                repaint();
                break;
              case 39:
                focus.moveRight();
                repaint();
                break;
              case 40:
                focus.moveDown();
                repaint();
                break;
              case 67:
                figs.add(new Circle(x,y,patternSize/2,new Color(0,0,0),new Color(255,255,255)));
                repaint();
                break;
              case 69:
                figs.add(new Ellipse(x,y, patternSize/2,patternSize/4,new Color(0,0,0),new Color(255,255,255)));
                repaint();
                break;
              case 76:
                figs.add(new Line(x,y,x+patternSize,y,new Color(0,0,0)));
                repaint();
                break;
              case 82:
                figs.add(new Rect(x,y, patternSize,patternSize,new Color(0,0,0),new Color(255,255,255)));
                repaint();
                break;
              case 127:
                figs.remove(focus);
                focus = null;
                repaint();
                break;

            }
          }

        });
    }

    private void verifyFocus(MouseEvent e){

      if(focus!=null){
        focus.removeFocus();
        repaint();
        focus = null;
      }

      for(Figure i:this.figs){
        if(i.itsInside(e.getX(), e.getY())){
          this.focus = i;
          focus.setFocus(e.getX(), e.getY());
          repaint();
        }
      }
    }

    //Mouse motion
    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      focus.drag(e.getX(), e.getY());
      repaint();
    }

    //Mouse listener
    @Override
    public void mouseClicked(MouseEvent e) {
      verifyFocus(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      System.out.println("coordenadas : ["+e.getX()+","+e.getY()+"]");
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
      verifyFocus(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void paint (Graphics g) {
        super.paint(g);
        this.getScreenLimits();
        this.drawFigures(g);
        //this.Menu(g);
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