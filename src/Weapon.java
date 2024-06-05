public class Weapon{
    private String name;
    private String rarity;

    public Weapon(String name, String rarity) {
        this.name = name;
        this.rarity = rarity;
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


}