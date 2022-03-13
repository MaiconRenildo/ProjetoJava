package figures;
import java.awt.*;

public class Circle extends Figure {

  int radius;
  Color backgroundColor;

  public Circle (int x, int y, int radius,Color line,Color background) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.backgroundColor = background;
    this.lineColor = line;
  }

  public void drag(int dx,int dy,int dradius){
    this.x=this.x+dx;
    this.y=this.y+dy;
    this.radius=this.radius+dradius;
  };

  public void paint (Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setPaint(lineColor);
    g2d.setColor(backgroundColor);
    g2d.fillRoundRect(this.x, this.y, this.radius, this.radius, this.radius, this.radius);
    g2d.setColor(lineColor);
    g2d.drawRoundRect(this.x, this.y, this.radius, this.radius, this.radius, this.radius);
  }

  public void changeColor(Color line,Color background){
    this.backgroundColor = background;
    this.lineColor = line;
  }

}
