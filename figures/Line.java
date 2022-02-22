package figures;

import java.awt.*;

public class Line {
    int startX, startY,endX,endY;
    Color lineColor;

    public Line (int startX, int startY, int endX, int endY,Color line) {
      this.startX= startX;
      this.startY = startY;
      this.endX = endX;
      this.endY = endY;
      this.lineColor = line;
    }

    public void paint (Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setPaint(lineColor);
      g2d.drawLine(startX, startY, endX, startY); 
    }
}