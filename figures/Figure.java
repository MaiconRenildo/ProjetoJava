package figures;

import java.awt.*;

public abstract class Figure {

  public abstract void increaseSize();
  public abstract void decreaseSize();
  public abstract void paint(Graphics g);
  public abstract boolean itsInside(int xCoordinate,int yCoordinate);

  Color lineColor;
  int x,y;

  int previousX = -1;
  int previousY = -1;

  int dMove = 3;

  public void drag(int newX,int newY){

    int dx = newX - this.previousX;
    int dy = newY - this.previousY;

    this.x = this.x+dx;
    this.y = this.y+dy;

    previousX = newX;
    previousY = newY;

  };

  public void setFocus(int mouseX,int mouseY){
    this.previousX = mouseX;
    this.previousY = mouseY;
    lineColor = new Color(255,0,0);
  }

  public void removeFocus(){
    lineColor = new Color(0,0,0);
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
