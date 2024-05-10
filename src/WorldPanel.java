import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


class WorldPanel extends JPanel implements MouseListener, KeyListener {
    Dungeon dungeon;
    private Rectangle start;
    private Rectangle save;
    private Rectangle load;
    private Swordmaster s;

    public WorldPanel() {
        start = new Rectangle(150, 100, 200, 30);
        save = new Rectangle(350, 100, 200, 30);
        load = new Rectangle(550, 100, 200, 30);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon.createDungeon();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;
        int y = 50;

        int currentRow = dungeon.getS().getRow();
        int currentCol = dungeon.getS().getCol();

        for (int row = 0; row < dungeon.getTiles().length; row++) {
            for (int col = 0; col < dungeon.getTiles()[0].length; col++) {
                Tile tile = dungeon.getTiles()[row][col];
                if (tile.isPath()){
                    g.drawImage(tile.getImage(),x+10,y+10,null);
                    if (row == currentRow && col == currentCol) {
                        g.drawImage(dungeon.getS().getImage(), x+10, y+10, null);
                    }
                }
                x += 20;
            }
            x = 0;
            y += 20;
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