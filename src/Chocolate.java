import java.awt.*;

public class Chocolate {


    public String name;
    public int xpos;
    public int ypos;
    public int dx = 3;  // speed in the x direction
    public int dy = -3;  // speed in the y direction
    public int width = 100;
    public int height = 100;
    public boolean isAlive;
    public boolean oliverAndJr = false;
    public boolean oliverAndJp = false;
    public boolean JrAndJp = false;
    public Rectangle rec;

    public Chocolate(String paramName, int paramXpos, int paramYpos) {
        name = paramName;
        xpos = paramXpos;
        ypos = paramYpos;
        rec = new Rectangle(xpos, ypos, width, height);
    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (ypos < 0) {
            dy = -dy;
        }   // top

        if (xpos > 1000 - width) {
            dx = -dx;
        }
        //right
        if (ypos > 700 - height) {
            dy = -dy;
        }   // bottom
        if (xpos < 0) {
            dx = -dx;
        }
        rec = new Rectangle(xpos, ypos, width, height);
        //left


    }

    public void wrap() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        if (xpos > 1000 && dx > 0) {
            xpos = -width;
            //right to left
        }
        if (ypos > 700 && dy > 0) {
            ypos = -height;
            //bottom to top
        }
        if (ypos < 0 && dy < 0) {
            ypos = 700;

        }
        if (xpos < 0 && dx < 0) {
            xpos = 1000;

        }
        rec = new Rectangle(xpos, ypos, width, height);
    }

}