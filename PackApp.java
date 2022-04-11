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

    Rect rectFocus = null;
    Figure focus = null;
    ArrayList<Figure> figs = new ArrayList<Figure>();
    Menu menu = new Menu(480,480);
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
      this.getContentPane().setBackground(Color.white);
      this.setSize(480, 480);
      this.setVisible(true);
      this.addMouseMotionListener(this);
      this.addMouseListener(this);       
      this.setFocusTraversalKeysEnabled(false);
      this.addKeyListener(new KeyAdapter(){

        public void keyPressed(KeyEvent key){

          patternSize = 60;

          switch(key.getKeyCode()){
            case 9:
              changeFocus();
              repaint();
              break;
            case 27:
              changeMenuStatus();
              repaint();
              break;
            case 37:
              focus.moveLeft();
              updateRectFocusPosition();
              repaint();
              break;
            case 38:
              focus.moveUp();
              updateRectFocusPosition();
              repaint();
              break;
            case 39:
              focus.moveRight();
              updateRectFocusPosition();
              repaint();
              break;
            case 40:
              focus.moveDown();
              updateRectFocusPosition();
              repaint();
              break;
            case 32:
              changeColorToUp();
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
            case 84:
              figs.add(new Triangle(x,y, new Color(0,0,0),new Color(255,255,255)));
              repaint();
              break;
            case 85:
              up_z_order();
              repaint();
              break;
            case 107:
              focus.increaseSize();
              updateRectFocusPosition();
              repaint();
              break;
            case 109:
              focus.decreaseSize();
              updateRectFocusPosition();
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

    private void changeColorToUp(){
      this.menu.changeColorToUp();
    }

    private void changeFocus(){

      int size = figs.size();

      if(size>=1){

        if(focus!=null){

          int index = figs.indexOf(focus);

          if(index<size-1){
            this.focus = figs.get(index+1);
            this.focus.updateFocus(this.x, this.y);
            updateRectFocusPosition();
          }else{
            this.focus = figs.get(0);
            this.focus.updateFocus(this.x,this.y);
            updateRectFocusPosition();
          }
          
        }else{
          this.focus = figs.get(0);
          this.focus.updateFocus(this.x, this.y);
          updateRectFocusPosition();
          repaint();
        }
      }
    }

    private void verifyFocus(MouseEvent e){

      if(this.focus!=null){
        this.focus = null;
      }

      for (int i = figs.size()-1; i >= 0; i--) {
        if(this.figs.get(i).itsInside(e.getX(), e.getY())){
          this.figs.get(i).updateFocus(e.getX(), e.getY());
          this.focus = figs.get(i);
          updateRectFocusPosition();;
          repaint();
          i = 0;
        }
      }
      repaint();
    }

    private void up_z_order(){
      if(this.focus!=null){
        this.figs.remove(focus);
        this.figs.add(focus);
      }
    }

    private void updateRectFocusPosition(){
      int[] focusArray = this.focus.getFocusCoordinates();
      this.rectFocus = new Rect(focusArray[0],focusArray[1],focusArray[2],focusArray[3],Color.red,Color.white);
    }

    private void down_z_order(){
      if(this.focus!=null){
        for (int i = this.figs.size(); i >0; i--) {
          if(i==this.figs.size()){
            this.figs.add(figs.get(i-1));
          }else{
            this.figs.set(i,figs.get(i-1));
          }
        }
        this.figs.remove(focus);
        this.figs.set(0,focus);
      }
    }

    private void drawFigures(Graphics g){

      int index = this.figs.indexOf(this.focus);
      for(int i=0;i<this.figs.size();i++){
        if(i==index){
          this.rectFocus.paint(g);
        }
        this.figs.get(i).paint(g);
      }

      // for(Figure i:this.figs){

      //   if(index==this.figs.indexOf(i)){
      //     if(this.rectFocus!=null){
      //       this.rectFocus.paint(g);
      //     }
      //   }

      //   i.paint(g);
      // }
    }

    private void getScreenLimits(){
      w = this.getWidth();
      h = this.getHeight();
    }

    private void upMenu(Graphics g){     
      this.menu.changeLimits(this.getWidth(), this.getHeight());
      this.menu.paint(g);
    }

    private void changeMenuStatus(){
      this.menu.changeMenuStatus();
    }

    public void paint (Graphics g) {
      super.paint(g);
      this.getScreenLimits();
      this.drawFigures(g);
      upMenu(g);
    }
    
    @Override
    public void mouseMoved(MouseEvent e) {
      x = e.getX();
      y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      if(this.focus!=null){
        this.focus.drag(e.getX(), e.getY());
        updateRectFocusPosition();
      }
      this.x = e.getX();
      this.y = e.getY();
      repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      Figure newFocus = this.menu.verifyFocus(e.getX(), e.getY(), this.focus);
      if(newFocus!=null){
        this.figs.set(figs.indexOf(this.focus), newFocus);
        this.focus = newFocus;
        updateRectFocusPosition();
      }else{
        verifyFocus(e);
      }      
      repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      // System.out.println("coordenadas : ["+e.getX()+","+e.getY()+"]");
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
      Figure newFocus = this.menu.verifyFocus(e.getX(), e.getY(), this.focus);
      if(newFocus!=null){
        figs.set(figs.indexOf(this.focus), newFocus);
        this.focus = newFocus;
      }else{
        verifyFocus(e);
      }      
      repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      this.x = e.getX();
      this.y = e.getY();
      repaint();
    }
}