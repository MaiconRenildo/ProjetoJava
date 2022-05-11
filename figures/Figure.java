package figures;
import java.awt.*;
import interfaces.*;
import java.io.Serializable;
public abstract class Figure implements  IVisible,Serializable {

  public abstract int[] getFocusCoordinates();
  public abstract void increaseSize();
  public abstract void decreaseSize();
  public abstract void paint(Graphics g);
  public abstract boolean clicked(int xCoordinate,int yCoordinate);

  protected Color lineColor,backgroundColor;
  protected int x,y;
  protected int previousX = -1;
  protected int previousY = -1;
  protected int dMove = 3;

  public void drag(int newX,int newY){

    int dx = newX - this.previousX;
    int dy = newY - this.previousY;

    this.x = this.x+dx;
    this.y = this.y+dy;

    previousX = newX;
    previousY = newY;

  };

  public void updateFocus(int mouseX,int mouseY){
    this.previousX = mouseX;
    this.previousY = mouseY;
  }

  public Color getBackgroundColor(){
    return this.backgroundColor;
  }

  public Color getLineColor(){
    return this.lineColor;
  }

  public void changeLineColor(Color color){
    this.lineColor = color; 
  }

  public void changeBackgroundColor(Color color){
    this.backgroundColor = color;
  }

  public void moveRight(){
    this.x = this.x+this.dMove;
  }

  public void moveLeft(){
    this.x = this.x-this.dMove;
  }

  public void moveUp(){
    this.y = this.y-this.dMove;
  }

  public void moveDown(){
    this.y = this.y+this.dMove;
  }

}
