package figures;
import java.awt.*;

public class Triangle extends Figure {

  int size = 40;
  int dMove = 2;

  public Triangle (int x, int y,Color line,Color background) {
    this.x = x;
    this.y = y;
    this.lineColor = line;
    this.backgroundColor = background;
  }

  public boolean clicked(int xCoordinate,int yCoordinate){
      if(xCoordinate>this.x-1 && xCoordinate<this.x+this.size+1){
        int difference = xCoordinate - this.x;

        if(difference==this.size/2){
          if(yCoordinate<this.y+1 && yCoordinate>this.y-size-1){
            return true;
          }
        }

        if(difference<this.size/2){
          if(yCoordinate<this.y+1 && yCoordinate>this.y-difference-1){
            return true;
          }
        }

        if(difference>this.size/2){
          int middleDifference = xCoordinate - (this.x+this.size/2);
          if(middleDifference<this.size+1){
            if(yCoordinate<this.y+1 && yCoordinate>((this.y-size/2)+middleDifference+1)){
              return true;
            }
          }
        }
      }
      return false;
  }

  public void paint (Graphics g) {
    
    Graphics2D g2d = (Graphics2D) g;

    int xv[] = {this.x,this.x+this.size/2,this.x+this.size};
    int yv[] = {this.y,this.y-this.size/2,this.y};

    g2d.setPaint(this.backgroundColor);
    g2d.fillPolygon(xv, yv,3);
    g2d.setPaint(this.lineColor);
    g2d.drawPolygon(xv,yv,3);

  }

  public void drag(int newX,int newY){
    int dx = newX - this.previousX;
    int dy = newY - this.previousY;
    this.x = this.x+dx;
    this.y = this.y+dy;
    previousX = newX;
    previousY = newY;
  };

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

  public void increaseSize(){
    this.size = this.size+this.dMove;
  }

  public void decreaseSize(){
    if(this.size>12){
      this.size = this.size -this.dMove;
    }
  }

  public int[] getFocusCoordinates(){
    int[] rectArray={this.x-this.dMove-1,this.y-(this.size/2)-this.dMove-1,this.size+(this.dMove+1)*2,(this.size/2)+(this.dMove+1)*2};
    return rectArray;
  };

}
