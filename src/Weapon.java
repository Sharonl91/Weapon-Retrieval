import java.util.ArrayList;

public class Weapon{
    private String name;
    private ArrayList<String> inv;
    private String rarity;

    public Weapon(String name, String rarity) {
        this.name = name;
        this.rarity = rarity;
        inv = new ArrayList<>();
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
        inv.add(getName() + " " + getRarity());
    }
    public ArrayList<String> getInv(){
        return inv;
    }
}