import java.awt.*;
import java.util.ArrayList;

public class Bag {
    ArrayList<Weapon> inv;
    Rectangle bag;

    public Bag(ArrayList i ){
        inv = i;
        bag = new Rectangle(200,400,50,60);
    }

    public ArrayList getInv() {
        return inv;
    }

    public Rectangle getBag() {
        return bag;
    }
}
