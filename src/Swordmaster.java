import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Swordmaster {
    private Image image;

    private final String IMAGE_FILE = "Files/swordmaster.png";
    private ArrayList<Weapon> inv;
    Weapon w;
    Dungeon d;

    public Swordmaster(){
        image = loadImage(IMAGE_FILE).getScaledInstance(250,250,20);
        inv = new ArrayList<>();
        w = new Weapon("Fillet Blade","Rare");
        obtainWeapon(w);
    }
    public void searchBag(){
        for (int i = 0; i < inv.size(); i++){
            if (inv.get(i).getRarity().contains("Mythical")){
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
    public void obtainWeapon(Weapon w) {
        inv.add(w);
    }

    public Image getImage() {
        return image;
    }
    public ArrayList<Weapon> getInventory(){
        return inv;
    }
}
