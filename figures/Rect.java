package figures;
import java.awt.*;

public class Rect {
    int x, y;
    int w, h;
    Color backgroundColor,borderColor;

    public Rect (int x, int y, int w, int h,Color border,Color background) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.backgroundColor = background;
        this.borderColor = border;
    }

    public void paint (Graphics g) {

      Graphics2D g2d = (Graphics2D) g;
      g2d.setColor(backgroundColor);
      g2d.fillRect(this.x,this.y, this.w,this.h);
      g2d.setColor(borderColor);
      g2d.drawRect(this.x,this.y, this.w,this.h);
    }

    public int area() {
        return this.x*this.y;
    };

    public void drag(int dx,int dy){
        this.x=this.x+dx;
        this.y=this.y+dy;
    };

    public void changeColor(Color border,Color background){
        this.backgroundColor = background;
        this.borderColor = border;
    }
}