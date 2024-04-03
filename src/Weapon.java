import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Weapon {
    private String rarity;
    private String name;
    private String imageFileName;
    private String backImageFileName;
    private boolean show;
    private BufferedImage image;
    private Rectangle cardBox;

    public Weapon(String rarity, String name) {
        this.rarity = rarity;
        this.name = name;
        this.imageFileName = "images/card_"+rarity+"_"+name+".png";
        this.show = true;
        this.backImageFileName = "images/card_back.png";
        this.image = readImage();
        this.cardBox = new Rectangle(-100, -100, image.getWidth(), image.getHeight());
    }

    public Rectangle getCardBox() {
        return cardBox;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRectangleLocation(int x, int y) {
        cardBox.setLocation(x, y);
    }

    public String getName() {
        return name;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String toString() {
        return rarity + " " + name
                ;
    }

    public void flipCard() {
        show = !show;
        this.image = readImage();
    }

    public BufferedImage getImage() {
        return image;
    }

    public BufferedImage readImage() {
        try {
            BufferedImage image;
            if (show) {
                image = ImageIO.read(new File(imageFileName));
            }
            else {
                image = ImageIO.read(new File(backImageFileName));
            }
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