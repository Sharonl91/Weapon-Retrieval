import java.util.ArrayList;

public class Bag {
    ArrayList<Weapon> inv;
    public Bag(ArrayList<Weapon> i ){
        inv = i;
    }

    public ArrayList<Weapon> getInv() {
        return inv;
    }
}
