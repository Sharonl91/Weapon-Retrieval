import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


class WorldPanel extends JPanel implements MouseListener, KeyListener {
    Dungeon dungeon;
    private final Rectangle start;
    private final Rectangle save;
    private final Rectangle load;
    private Swordmaster s;
    private ArrayList<Monster> m;
    private Monster monster;

    public WorldPanel() {
        start = new Rectangle(150, 100, 200, 30);
        save = new Rectangle(350, 100, 200, 30);
        load = new Rectangle(550, 100, 200, 30);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon("background/map");
        s = new Swordmaster(0,0);
        m = new ArrayList<>();
        monster = new Monster();
        monster.generateMultipleMonster((int)(Math.random()*10),m);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 100;
        int y = 150;

        int currentRow = dungeon.getS().getRow();
        int currentCol = dungeon.getS().getCol();

        for (int row = 0; row < dungeon.getTiles().length; row++) {
            for (int col = 0; col < dungeon.getTiles()[0].length; col++) {
                Tile tile = dungeon.getTiles()[row][col];
                if (tile.isPath()){
                    g.drawImage(tile.getImage(),x+30,y+30,null);
                    for (Monster monster : m) {
                        if (row == monster.getRow() && col == monster.getColumn()) {
                            g.drawImage(monster.getImage(), x + 30, y + 30, null);
                            if (currentRow == monster.getRow() && currentCol == monster.getColumn()) {
                                dealDamage();

                            }
                        }
                    }
                    if (row == currentRow && col == currentCol) {
                        g.drawImage(dungeon.getS().getImage(), x+30, y+30, null);
                    }
                }
                x += 30;
            }
            x = 100;
            y += 30;
        }
        s.searchBag();
        if(dungeon.isGameEnded()){
            g.drawString("You have obtained the mythical weapon!! \n Yay -_-", 700, 150);
        }
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString("Start game!", 170, 123);
        g.drawRect((int)start.getX(), (int)start.getY(), (int)start.getWidth(), (int)start.getHeight());
        g.drawString("Save game!", 380, 123);
        g.drawRect((int)save.getX(), (int)save.getY(), (int)save.getWidth(), (int)save.getHeight());
        g.drawString("Load game!", 580, 123);
        g.drawRect((int)load.getX(), (int)load.getY(), (int)load.getWidth(), (int)load.getHeight());
    }

    protected void dealDamage(){
        for (Monster monster : m) {
            if (s.getRow() == monster.getRow() && s.getCol() == monster.getColumn()) {
                if (s.getInventory().contains("Fillet Blade")) {
                    monster.loseHP((int) (Math.random() * monster.getHp()) - 20);
                }
            }
            if (s.getRow() == monster.getRow() && s.getCol() == monster.getColumn()) {
                if (s.getInventory().contains("The Flute")) {
                    monster.loseHP((int) (Math.random() * monster.getHp()) - 5);
                }
            }
            if (s.getRow() == monster.getRow() && s.getCol() == monster.getColumn()) {
                if (s.getInventory().contains("Skyward Blade")) {
                    monster.loseHP(monster.getHp());
                }
            }
        }
    }
    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String direction = String.valueOf(e.getKeyChar());
        if (direction.equals("w")){
            dungeon.move("w");
        }
        if (direction.equals("a")){
            dungeon.move("a");
        }
        if (direction.equals("s")){
            dungeon.move("s");
        }
        if (direction.equals("d")){
            dungeon.move("d");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}