import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Monster {
    Weapon w;
    private Image image;
    private int hp;
    private final String IMAGE_FILE = "Files/wendigo.png";

    public Monster(){
        hp = 50;
        image = loadImage(IMAGE_FILE).getScaledInstance(250,250,20);
        w = new Weapon("Skyward Blade","Mythical");
    }
    public void generateWeapon(){
        int r = (int)(Math.random() * 11);
        if(r < 9){
            w = new Weapon("Skyward Blade","Mythical");
        }
        if(r >= 9){
            w = new Weapon("The Flute","Legendary");
        }
    }

    public Image loadImage(String fileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(fileName));
            return image;
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    public Image getImage() {
        return image;
    }
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public Weapon getW() {
        return w;
    }
}
