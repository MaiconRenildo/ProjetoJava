package figures;
import java.awt.*;

public class Circle extends Figure {

  int radius,diameter;

  public Circle (int x, int y, int radius,Color line,Color background) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.backgroundColor = background;
    this.lineColor = line;
  }

  public boolean clicked(int xCoordinate,int yCoordinate){

    // (x-x0)^2 + (y-y0)^2 = raio^2

    int centerX = this.x + this.radius;
    int centerY = this.y + this.radius;
    int diffX = (xCoordinate - centerX);
    int diffY = (yCoordinate - centerY);

    diffX = diffX*diffX;
    diffY = diffY*diffY;
    
    int diffSum = diffX+diffY;

    if(diffSum<=(radius*radius)){
      return true;
    }

    return false;
}

  public void paint (Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(lineColor);
    g2d.setColor(backgroundColor);
    g2d.fillRoundRect(this.x, this.y, this.radius*2, this.radius*2, this.radius*2, this.radius*2);
    g2d.setColor(lineColor);
    g2d.drawRoundRect(this.x, this.y, this.radius*2, this.radius*2, this.radius*2, this.radius*2);
  }

  public void changeColor(Color line,Color background){
    this.backgroundColor = background;
    this.lineColor = line;
  }

  public void increaseSize(){
    this.radius = this.radius+this.dMove;
  }

  public void decreaseSize(){
    if(this.radius>6){
      this.radius = this.radius-this.dMove;
    }
  }

  public int[] getFocusCoordinates(){
    int[] rectArray={this.x-this.dMove,this.y-this.dMove,this.radius*2+this.dMove*2,this.radius*2+this.dMove*2};
    return rectArray;
  };
}