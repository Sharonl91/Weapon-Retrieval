import java.awt.*;
import java.util.ArrayList;

public class Bag {
    ArrayList<String> inv;
    Rectangle bag;
    Weapon w;

    public Bag(){
        inv = new ArrayList<>();
        bag = new Rectangle(200,400,50,60);
    }

    public ArrayList getInv() {
        return inv;
    }

    public void setInv() {
        inv = w.getInv();
    }

    public Rectangle getBag() {
        return bag;
    }
}
