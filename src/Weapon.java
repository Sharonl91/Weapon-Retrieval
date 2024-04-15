import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Weapon {
    private String rarity;
    private String name;
    int amount;
    private String IMAGE;
    private boolean show;
    private BufferedImage image;


    public Weapon(String rarity, String name, int amount) {
        this.rarity = rarity;
        this.name = name;
        this.amount = amount;
        this.IMAGE = "images/card_"+rarity+"_"+name+".png";
        this.show = true;
        this.image = readImage();
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

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage readImage() {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(IMAGE));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    public static ArrayList<Weapon> buildInv(){
        ArrayList<Weapon> inventory = new ArrayList<>();
        return inventory;
    }

    public static ArrayList<Weapon> obtainWeapon() {
        ArrayList<Weapon> inventory = Weapon.buildInv();
        inventory.add(null);
        return inventory;
    }
    public static ArrayList<Weapon> getInv(ArrayList<Weapon> inv){
        return inv;
    }
}