package figures;
import java.awt.*;

public class Rect extends Figure{

    int w, h;
    Color backgroundColor;

    public Rect (int x, int y, int w, int h,Color line,Color background) {
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
      g2d.fillRect(this.x,this.y, this.w,this.h);
      g2d.setColor(lineColor);
      g2d.drawRect(this.x,this.y, this.w,this.h);
    }

    public boolean itsInside(int xCoordinate,int yCoordinate){
        if(xCoordinate>=this.x && xCoordinate<=this.x+w){
            if(yCoordinate>=this.y && yCoordinate<=this.y+h){
                return true;
            }
            return false;
        }
        return false;
    }

    public void changeColor(Color line,Color background){
        this.backgroundColor = background;
        this.lineColor = line;
    }

    public void increaseSize(){
        this.w = this.w+this.dMove;
        this.h = this.h+this.dMove;
    }
  
    public void decreaseSize(){
        this.w = this.w-this.dMove;
        this.h = this.h-this.dMove;
    }
    
}