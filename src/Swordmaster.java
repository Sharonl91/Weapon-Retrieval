import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Swordmaster {
    private BufferedImage image;
    private int row;
    private int column;
    private final String IMAGE_FILE = "Swordmaster/front.png";
    private ArrayList<String> inv;
    Dungeon d;
    Bag b;

    public Swordmaster(int row, int col){
        this.row = row;
        this.column = col;
        image = loadImage(IMAGE_FILE);
        b = new Bag(inv);
    }
    public void searchBag(){
        for (String weapon: inv){
            if (weapon.contains("M")){
                d.setGameEnded();
            }
        }
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

    public int getRow() {
        return row;
    }

    public int getCol() {
        return column;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int column) {
        this.column = column;
    }

    public String toString() {
        return "Player at " + row + "," + column;
    }
}
