package figures;

import java.awt.*;

public class Line extends Figure {

    int endX,endY;

    public Line (int startX, int startY, int endX, int endY,Color line) {
      this.x = startX;
      this.y = startY;
      this.endX = endX;
      this.endY = endY;
      this.lineColor = line;
    }

    public boolean itsInside(int xCoordinate,int yCoordinate){
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
}