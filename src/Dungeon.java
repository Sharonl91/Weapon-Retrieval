import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
public class Dungeon {
    private Swordmaster s;

    private boolean gameEnded;
    private Image image;
    private final String IMAGE_FILE = "Files/dungeon.png";

    public Dungeon(){
        image = loadImage(IMAGE_FILE).getScaledInstance(900,600,10);
        s = new Swordmaster();
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
    public void setGameEnded() {
        gameEnded = true;
    }

    public boolean isGameEnded() {
        return gameEnded;
    }
    public Image getImage() {
        return image;
    }

    public Swordmaster getS(){
        return s;
    }

}
