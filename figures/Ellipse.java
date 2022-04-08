package figures;

import java.awt.*;

public class Ellipse extends Figure {

    int w, h;

    public Ellipse (int x, int y, int w, int h,Color line,Color background) {
      this.x = x;
      this.y = y;
      this.w = w;
      this.h = h;
      this.backgroundColor = background;
      this.lineColor = line;        
    }

    public void paint (Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(backgroundColor);
      g2d.fillOval(this.x,this.y, this.w*2,this.h*2);
      g2d.setColor(lineColor);
      g2d.drawOval(this.x,this.y, this.w*2,this.h*2);
    }

    public boolean itsInside(int xCoordinate,int yCoordinate){

    // ((x-x0)^2)/a^2 + ((y-y0)^2)/b^2 = raio^2

    int centerX = this.x + this.w;
    int centerY = this.y + this.h;

    int diffX = (xCoordinate - centerX);
    int diffY = (yCoordinate - centerY);

    diffX = diffX*diffX;
    diffY = diffY*diffY;

    float width = this.w * this.w;
    float heigth = this.h*this.h;
    float first = diffX/(width);
    float second = diffY/(heigth);
    float diffSum = first+second;

    if(diffSum<=1){
      return true;
    }

    return false;
  }

  public void changeColor(Color border,Color background){
    this.backgroundColor = background;
    this.lineColor = border;
  }

  public void increaseSize(){
    this.w = this.w+this.dMove;
    this.h = this.h+this.dMove;
  }

  public void decreaseSize(){
    if(this.w>3 && this.h>3){
      this.w = this.w-this.dMove;
      this.h = this.h-this.dMove;
    }
  }

}