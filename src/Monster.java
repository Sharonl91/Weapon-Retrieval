import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
public class Monster {
    Swordmaster s;
    Bag b;
    private BufferedImage image;
    private int row;
    private int column;
    private final String easy = "Monster/goblin.png";
    private final String medium = "Monster/goblin.png";
    private final String hard = "Monster/goblin.png";
    public Monster(){
        row = (int)(Math.random() * 20);
        column = (int)(Math.random() * 14);
    }

    private void generateMonster() {
        int ran = (int) (Math.random() * 6) + 1;
        if (ran == 1 || ran == 2 || ran == 3 ) {
            loadImage(easy);
            b.getInv().
        }
        if (ran == 4 || ran == 5) {
            loadImage(medium);
        }
        if (ran == 6) {
            loadImage(hard);
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public BufferedImage loadImage(String fileName) {
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

    public BufferedImage getImage() {
        return image;
    }

    public Swordmaster getS() {
        return s;
    }
}
