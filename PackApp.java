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
              case 73:
                down_z_order();
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
              case 85:
                up_z_order();
                repaint();
                break;
              case 107:
                focus.increaseSize();
                repaint();
                break;
              case 109:
                focus.decreaseSize();
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
        focus = null;
      }

      for (int i = figs.size()-1; i >= 0; i--) {
        if(figs.get(i).itsInside(e.getX(), e.getY())){
          figs.get(i).setFocus(e.getX(), e.getY());
          focus = figs.get(i);
          repaint();
          i = 0;
        }
      }

      repaint();
    }

    private void up_z_order(){
      if(focus!=null){
        figs.remove(focus);
        figs.add(focus);
      }
    }

    private void down_z_order(){

      if(focus!=null){

        for (int i = this.figs.size(); i >0; i--) {
          if(i==this.figs.size()){
            this.figs.add(figs.get(i-1));
          }else{
            this.figs.set(i,figs.get(i-1));
          }
        }
        figs.remove(focus);
        this.figs.set(0,focus);
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
      this.x = e.getX();
      this.y = e.getY();
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
      this.x = e.getX();
      this.y = e.getY();
      repaint();
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