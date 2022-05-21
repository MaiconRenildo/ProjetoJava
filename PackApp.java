import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import figures.*;
import menu.Menu;
import java.io.*;

class PackApp {
    public static void main (String[] args) {
        PackFrame frame = new PackFrame();
        frame.setVisible(true);
    }
}

class PackFrame extends JFrame implements MouseMotionListener,MouseListener{
    private Rect rectFocus = null;
    private Figure focus = null;
    private ArrayList<Figure> figs = new ArrayList<Figure>();
    private Menu menu = new Menu(480,480);
    private int idFigureToCreate = 0;
    
    protected int w,h,x,y,patternSize=60;
    protected JLabel mousePosition;

    public PackFrame () {
      
      try {
        FileInputStream file = new FileInputStream("proj.bin");
        ObjectInputStream object = new ObjectInputStream(file);
        this.figs = (ArrayList<Figure>) object.readObject();
        object.close();
    } catch (Exception exception) {
        System.out.println(exception.getMessage());
    }

      this.addWindowListener (
        new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    FileOutputStream file = new FileOutputStream("proj.bin");
                    ObjectOutputStream object = new ObjectOutputStream(file);
                    object.writeObject(figs);
                    object.flush();
                    object.close();
                } catch (Exception exception) {
                    System.out.println(exception.getLocalizedMessage());
                    System.out.println(exception.getMessage());
                    System.out.println(exception.toString());
                }
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

          // System.out.println(key.getKeyCode());

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
              moveFocusToLeft();
              updateRectFocusPosition();
              repaint();
              break;
            case 38:
              moveFocusToUp();
              updateRectFocusPosition();
              repaint();
              break;
            case 39:
              moveFocusToRight();
              updateRectFocusPosition();
              repaint();
              break;
            case 40:
              moveFocusToDown();
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
              increaseFocusSize();
              updateRectFocusPosition();
              repaint();
              break;
            case 109:
              decreaseFocusSize();
              updateRectFocusPosition();
              repaint();
              break;
            case 127:
              deleteFigure();
              repaint();
              break;
          }
        }
      });
    }

    private void deleteFigure(){
      this.figs.remove(focus);
      this.focus = null;
    }

    private void moveFocusToLeft(){
      if(focusIsNotNull())
        this.focus.moveLeft();
    }

    private void moveFocusToRight(){
      if(focusIsNotNull())
        this.focus.moveRight();
    }

    private void moveFocusToDown(){
      if(focusIsNotNull())
        this.focus.moveDown();
    }

    private void moveFocusToUp(){
      if(focusIsNotNull())
        this.focus.moveUp();
    }

    private void decreaseFocusSize(){
      if(focusIsNotNull())
        this.focus.decreaseSize();
    }

    private void increaseFocusSize(){
      if(focusIsNotNull())
        this.focus.increaseSize();
    }

    private void updateCoordinates(MouseEvent e){
      this.x = e.getX();
      this.y = e.getY();
    }

    private void draggFocusAndCallUpdateRectFocusPosition(MouseEvent e){
      if(focusIsNotNull()){
        this.focus.drag(e.getX(), e.getY());
        updateRectFocusPosition();
      }
    }

    private void changeColorToUp(){
      this.menu.changeColorToUp();
    }

    private void getFigureInFigsByIndexAndAddToFocus(int index){
      this.focus = figs.get(index);
      this.focus.updateFocus(this.x,this.y);
    }

    private int figsSize(){
      return this.figs.size();
    }

    private int indexOfFocusInFigs(){
      return this.figs.indexOf(this.focus);
    }

    private boolean focusIsNotNull(){
      return this.focus != null ? true : false; 
    }

    private void changeFocus(){
      if(figsSize() > 0){
        if(focusIsNotNull() && (indexOfFocusInFigs() < figsSize()-1)) getFigureInFigsByIndexAndAddToFocus(indexOfFocusInFigs()+1);
        else getFigureInFigsByIndexAndAddToFocus(0);

        updateRectFocusPosition();
        repaint();
      }
    }

    private boolean verifyFocus(MouseEvent e){

      this.focus = null;  

      for (int i = figsSize()-1; i >= 0; i--) {

        Figure selected = figs.get(i);

        if(selected.clicked(e.getX(), e.getY())){
          selected.updateFocus(e.getX(), e.getY());
          this.focus = selected;
          updateRectFocusPosition();
          i = 0;
        }
      }

      repaint();
      return this.focusIsNotNull();  
    }

    private void up_z_order(){
      if(focusIsNotNull()){
        this.figs.remove(focus);
        this.figs.add(focus);
      }
    }

    private void updateRectFocusPosition(){
      if(focusIsNotNull()){
        int[] focusArray = this.focus.getFocusCoordinates();
        this.rectFocus = new Rect(focusArray[0],focusArray[1],focusArray[2],focusArray[3],Color.red,Color.white);
      }
    }

    private void down_z_order(){
      if(focusIsNotNull()){
        for (int i = figsSize(); i > 0 ; i--) {
          if(i == figsSize()) this.figs.add(figs.get(i-1));
          else this.figs.set(i,figs.get(i-1));
        }
        this.figs.remove(focus);
        this.figs.set(0,focus);
      }
    }

    private void drawFigures(Graphics g){
      for(int i = 0; i < figsSize(); i++){
        if(i == indexOfFocusInFigs()) this.rectFocus.paint(g);
        this.figs.get(i).paint(g);
      }
    }

    private void getScreenLimits(){
      this.w = this.getWidth();
      this.h = this.getHeight();
    }

    private void upMenu(Graphics g){     
      this.menu.changeLimits(this.getWidth(), this.getHeight());
      if(this.menu.getMenuStatus()==1) this.menu.paint(g);
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
      updateCoordinates(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
      draggFocusAndCallUpdateRectFocusPosition(e);
      updateCoordinates(e);
      repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      verifyAndUpdateFocus(e);
      repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    private void verifyAndUpdateFocus(MouseEvent e ){

      Figure newFocus = this.menu.getMenuStatus() ==1 ? this.menu.verifyFocus(e.getX(), e.getY(), this.focus) : null;    

      if(newFocus!=null){
        figs.set(indexOfFocusInFigs(), newFocus);
        this.focus = newFocus;
        updateRectFocusPosition();
      }else{

        boolean result = verifyFocus(e);

        if(this.menu.getMenuStatus()==1 && result==false){

          if(this.idFigureToCreate!=0){
  
            int newId = this.menu.checksIfOneOfTheMenuOptionsWasClicked(e.getX(), e.getY());

            if(newId==0){

              switch(this.idFigureToCreate){
                case 1:
                  this.figs.add(new Ellipse(x,y, patternSize/2,patternSize/4,new Color(0,0,0),new Color(255,255,255)));
                  break;
                case 2:
                  this.figs.add(new Circle(x,y,patternSize/2,new Color(0,0,0),new Color(255,255,255)));
                  break;
                case 3:
                  this.figs.add(new Rect(x,y, patternSize,patternSize,new Color(0,0,0),new Color(255,255,255)));
                  break;
                case 4:
                  this.figs.add(new Triangle(x,y, new Color(0,0,0),new Color(255,255,255)));
                  break;
              }

              this.idFigureToCreate=0;

            }else{
              this.idFigureToCreate = newId;
            }
          }else{
            this.idFigureToCreate = this.menu.checksIfOneOfTheMenuOptionsWasClicked(e.getX(), e.getY());
          }
          repaint();
        }else{
          this.menu.checksIfOneOfTheMenuOptionsWasClicked(e.getX(), e.getY());
          this.idFigureToCreate = 0;
        }
      }
    }

    @Override
    public void mousePressed(MouseEvent e) {
      verifyAndUpdateFocus(e);
      repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
      updateCoordinates(e);
      repaint();
    }
}