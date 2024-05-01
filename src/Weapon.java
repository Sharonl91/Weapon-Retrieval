import java.util.ArrayList;

public class Weapon{
    private String name;
    private ArrayList inv;
    private String rarity;
    private boolean show;
    Bag b;

    public Weapon(String name, String rarity) {
        this.name = name;
        inv = b.getInv();
        this.rarity = rarity;
        this.show = true;
    }
    public String getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }


    public String toString() {
        return rarity + " " + name;
    }

    public void obtainWeapon() {
        inv.add(name);


    }
    public ArrayList<Weapon> getInv(ArrayList<Weapon> inv){
        return inv;
    }
}