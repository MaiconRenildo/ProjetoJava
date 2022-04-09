package figures;

import java.awt.*;
public abstract class Figure {

  public abstract void increaseSize();
  public abstract void decreaseSize();
  public abstract void paint(Graphics g);
  public abstract boolean itsInside(int xCoordinate,int yCoordinate);

  Color previousLineColor,previousBackgroundColor;
  Color lineColor,backgroundColor;
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
    this.previousLineColor = this.lineColor;
    this.lineColor = new Color(255,0,0);
  }

  public Color getBackgroundColor(){
    return this.backgroundColor;
  }

  public Color getLineColor(){
    return this.lineColor;
  }

  public void removeFocus(){
    this.lineColor = this.previousLineColor;
  }

  public void changeLineColor(Color color){
    this.previousLineColor = this.lineColor;
    this.lineColor = color; 
  }

  public void changeBackgroundColor(Color color){
    this.previousBackgroundColor = this.lineColor;
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
