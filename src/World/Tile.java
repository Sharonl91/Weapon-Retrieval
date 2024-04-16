package World;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Tile {
    private BufferedImage image;
    private int tileType;
    private final String floor = "background/floor.png";
    private final String shade = "background/floor.png";
    private final String wall = "background/floor.png";
    private boolean path;
    private boolean visible;

    public Tile(int tileType){
        this.tileType = tileType;
        if(tileType == 0 || tileType == 2) {
            path = true;
            this.visible = true;
        }
        else {
            path = false;
        }
        visible = false;
        this.setTileType(tileType);
    }

    public void setVisible() {
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setTileType(int tileType) {
        this.tileType = tileType;
        if (tileType == 0) {
            image = loadImage(floor);
        }
        if (tileType == 1) {
            image = loadImage(wall);
        }
        if (tileType == 2) {
            image = loadImage(shade);
        }
    }

    public int getTileType(){
        return tileType;
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
    public void setPath(){
        path = true;
        setTileType(tileType);
    }
    public BufferedImage getImage() {
        return image;
    }
    public String toString() {
        return tileType + " " + image;
    }
}
