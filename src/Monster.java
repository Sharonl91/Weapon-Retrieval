import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Monster {
    Swordmaster s;
    Bag b;
    Weapon w;
    private BufferedImage image;
    private String monsterType;
    private int row;
    private int column;
    private final String easy = "Monster/goblin.png";
    private final String medium = "Monster/wolf.png";
    private final String hard = "Monster/wendigo.png";
    public Monster(){
        row = (int)(Math.random() * 21);
        column = (int)(Math.random() * 15);
        generateMonster();
    }

    public void generateMonster() {
        int ran = (int) (Math.random() * 6) + 1;
        if (ran == 1 || ran == 2 || ran == 3 ) {
            loadImage(easy);
            monsterType = "E";
        }
        if (ran == 4 || ran == 5) {
            loadImage(medium);
            monsterType = "M";
        }
        if (ran == 6) {
            loadImage(hard);
            monsterType = "H";
        }
    }
    public void encounter(){
        if( s.getCol() == column && s.getRow() == row){
            if(getMonsterType().equals("E")){
                int r = (int)(Math.random() * 11);
                if(r == 0){
                    w = new Weapon("Skyward Blade","Mythical");
                    w.obtainWeapon();
                }
                if(r > 0 && r < 3){
                    w = new Weapon("The Flute","Legendary");
                    w.obtainWeapon();
                }
                if(r >= 3){
                    w = new Weapon("Fillet Blade","Rare");
                    w.obtainWeapon();
                }
            }
            if(getMonsterType().equals("M")){
                int r = (int)(Math.random() * 11);
                if(r < 3){
                    w = new Weapon("Skyward Blade","Mythical");
                    w.obtainWeapon();
                }
                if(r >= 3 && r < 7){
                    w = new Weapon("The Flute","Legendary");
                    w.obtainWeapon();
                }
                if(r >= 7){
                    w = new Weapon("Fillet Blade","Rare");
                    w.obtainWeapon();
                }
            }
            if(getMonsterType().equals("H")){
                int r = (int)(Math.random() * 11);
                if(r < 5){
                    w = new Weapon("Skyward Blade","Mythical");
                    w.obtainWeapon();
                }
                if(r >= 5){
                    w = new Weapon("The Flute","Legendary");
                    w.obtainWeapon();
                }
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

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getMonsterType() {
        return monsterType;
    }

    @Override
    public String toString() {
        return "Monster{" + ", row=" + row + ", column=" + column + '}';
    }
}
