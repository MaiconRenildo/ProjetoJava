package figures;
import java.awt.*;

public class Line extends Figure {

    private int endX,endY;

    public Line (int startX, int startY, int endX, int endY,Color line) {
      this.x = startX;
      this.y = startY;
      this.endX = endX;
      this.endY = endY;
      this.lineColor = line;
    }

    public boolean clicked(int xCoordinate,int yCoordinate){
        if(xCoordinate>this.x-1 && xCoordinate<this.endX+1){
            if(yCoordinate>this.y-1 && yCoordinate<this.endY+1){
                return true;
            }
            return false;
        }
        return false;
    }

    public void paint (Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setPaint(lineColor);
      g2d.drawLine(x, y, endX, endY); 
    }

    public void drag(int newX,int newY){

      int dx = newX - this.previousX;
      int dy = newY - this.previousY;
  
      this.x = this.x+dx;
      this.y = this.y+dy;
      this.endX = this.endX+dx;
      this.endY = this.endY+dy;
  
      previousX = newX;
      previousY = newY;
      
    };

    public void moveRight(){
      this.x = this.x+this.dMove;
      this.endX = this.endX+this.dMove;
    }
  
    public void moveLeft(){
      this.x = this.x-this.dMove;
      this.endX = this.endX-this.dMove;
    }
  
    public void moveUp(){
      this.y = this.y-this.dMove;
      this.endY = this.endY-this.dMove;
    }
  
    public void moveDown(){
      this.y = this.y+this.dMove;
      this.endY = this.endY+this.dMove;
    }

    public void increaseSize(){

      if(this.endY == this.y){

        this.x = this.x-this.dMove;
        this.endX = this.endX+this.dMove;

      }else if(this.endX == this.x){

        this.y = this.y-this.dMove;
        this.endY = this.endY+this.dMove;

      }else{

        this.x = this.x-this.dMove;
        this.endX = this.endX+this.dMove;

        this.y = this.y-this.dMove;
        this.endY = this.endY+this.dMove;

      }

    }
  
    public void decreaseSize(){

      if(this.endX==this.x && this.endY==this.y){

      }else if((this.endX-this.x)<=6 && (this.endY-this.y)<=6){

      }else{
      
        if(this.endY == this.y){

          this.x = this.x+this.dMove;
          this.endX = this.endX-this.dMove;
  
        }else if(this.endX == this.x){
  
          this.y = this.y+this.dMove;
          this.endY = this.endY-this.dMove;
  
        }else{
  
          this.x = this.x+this.dMove;
          this.endX = this.endX-this.dMove;
  
          this.y = this.y+this.dMove;
          this.endY = this.endY-this.dMove;
  
        }
      
      }

    }

    public int[] getFocusCoordinates(){
      int[] rectArray={this.x-this.dMove,this.y-this.dMove,(this.endX-this.x)+this.dMove*2,(this.endY-this.y)+this.dMove*2};
      return rectArray;
    };
}