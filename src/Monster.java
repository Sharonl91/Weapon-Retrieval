import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Monster {
    Swordmaster s;
    Weapon w;
    private Image image;
    private String monsterType;
    private int row;
    private int column;
    private int hp;
    private final String easy = "Monster/goblin.png";
    private final String medium = "Monster/wolf.png";
    private final String hard = "Monster/wendigo.png";

    public Monster(){
        row = (int)(Math.random() * 21);
        column = (int)(Math.random() * 15);
        hp = 50;
        generateMonster();
    }

    public void generateMonster() {
        int ran = (int) (Math.random() * 6) + 1;
        if (ran == 1 || ran == 2 || ran == 3 ) {
            image = loadImage(easy);
            setMonsterType("E");
        }
        if (ran == 4 || ran == 5) {
            image = loadImage(medium);
            setMonsterType("M");
        }
        if (ran == 6) {
            image = loadImage(hard);
            setMonsterType("H");
        }
    }
    public void generateMultipleMonster(int num, ArrayList<Monster> monsters) {
        for (int i = 0; i < num; i++){
            Monster gen = new Monster();
            gen.generateMonster();
            monsters.add(gen);
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
                if(r < 10){
                    w = new Weapon("Skyward Blade","Mythical");
                    w.obtainWeapon();
                }
                if(r >= 10){
                    w = new Weapon("The Flute","Legendary");
                    w.obtainWeapon();
                }
            }
        }
    }
    public void loseHP(int hp){
        this.hp -= hp;
        encounter();
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

    public void setMonsterType(String monsterType) {
        this.monsterType = monsterType;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Monster{" + ", row=" + row + ", column=" + column + '}';
    }
}
