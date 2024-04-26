import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Monster {
    Swordmaster s;
    Bag b;
    private BufferedImage image;
    private int row;
    private int column;
    private final String easy = "Monster/goblin.png";
    private final String medium = "Monster/wolf.png";
    private final String hard = "Monster/wendigo.png";
    public Monster(){
        row = (int)(Math.random() * 20);
        column = (int)(Math.random() * 14);
        generateMonster();
    }

    private void generateMonster() {
        int ran = (int) (Math.random() * 6) + 1;
        if (ran == 1 || ran == 2 || ran == 3 ) {
            loadImage(easy);
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

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Monster{" + ", row=" + row + ", column=" + column + '}';
    }
}
