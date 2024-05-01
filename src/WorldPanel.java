import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


class WorldPanel extends JPanel implements MouseListener, KeyListener {
    private Dungeon dungeon;
    private Rectangle start;
    private Rectangle save;
    private Rectangle load;

    public WorldPanel() {
        start = new Rectangle(150, 100, 200, 30);
        save = new Rectangle(350, 100, 200, 30);
        load = new Rectangle(550, 100, 200, 30);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 100;
        int y = 100;

        int currentRow = dungeon.getS().getRow();
        int currentCol = dungeon.getS().getCol();

        for (int row = 0; row < dungeon.getTiles().length; row++) {
            for (int col = 0; col < dungeon.getTiles()[0].length; col++) {
                if (row == currentRow && col == currentCol) {
                    g.drawImage(dungeon.getS().getImage(), x+10, y+10, null);
                }
                x = x + 24;
            }
            x = 10;
            y = y + 24;
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
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}