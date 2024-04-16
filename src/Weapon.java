import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Weapon{
    private String name;
    private ArrayList<Weapon> inv;
    private String rarity;
    private int amount;
    private boolean show;
    Bag b;

    public Weapon(String name, String rarity, int amount) {
        this.name = name;
        inv = b.getInv();
        this.rarity = rarity;
        this.amount = amount;
        this.show = true;
    }
    public String getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return rarity + " " + name;
    }

    public static ArrayList<Weapon> obtainWeapon() {
        inv.remove(Mo)
        return inv;
    }
    public static ArrayList<Weapon> getInv(ArrayList<Weapon> inv){
        return inv;
    }
}