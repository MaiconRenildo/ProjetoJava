package figures;

import java.awt.*;

public abstract class Figure {

  public abstract void paint(Graphics g);
  public abstract boolean itsInside(int xCoordinate,int yCoordinate);

  Color lineColor;
  int x,y;

  public void drag(int newX,int newY){
    this.x=newX;
    this.y=newY;
  };
  
}
