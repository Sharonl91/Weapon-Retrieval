import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Swordmaster {
    private Image image;

    private final String IMAGE_FILE = "Files/swordmaster.png";
    private ArrayList<String> inv;
    Weapon w = new Weapon("Fillet Blade","Rare");
    Dungeon d;

    public Swordmaster(){
        image = loadImage(IMAGE_FILE).getScaledInstance(250,250,20);
        inv = w.getInv();
    }
    public void searchBag(){
        for (String weapon: inv){
            if (weapon.contains("Mythical")){
                d.setGameEnded();
            }
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
    public ArrayList<String> getInventory(){
        return inv;
    }
}
