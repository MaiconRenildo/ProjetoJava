package figures;
import java.awt.*;

public class Rect extends Figure{

    private int w, h;

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

    public boolean clicked(int xCoordinate,int yCoordinate){
        return ((xCoordinate>=this.x && xCoordinate<=this.x+w) && (yCoordinate>=this.y && yCoordinate<=this.y+h)) ? true : false;
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
        if(this.w>6 && this.h>6){
            this.w = this.w-this.dMove;
            this.h = this.h-this.dMove;
        }
    }
    
    public int[] getFocusCoordinates(){
        int[] rectArray={this.x-this.dMove,this.y-this.dMove,this.w+this.dMove*2,this.h+this.dMove*2};
        return rectArray;
    };
}