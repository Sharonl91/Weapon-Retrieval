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
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        g.drawString("Start game!", 170, 123);
        g.drawRect((int)start.getX(), (int)start.getY(), (int)start.getWidth(), (int)start.getHeight());
        g.drawString("Save game!", 380, 123);
        g.drawRect((int)save.getX(), (int)save.getY(), (int)save.getWidth(), (int)save.getHeight());
        g.drawString("Load game!", 580, 123);
        g.drawRect((int)load.getX(), (int)load.getY(), (int)load.getWidth(), (int)load.getHeight());
        int x = 0;
        int y = 0;

        int playerRow = dungeon.getS().getRow();
        int playerCol = dungeon.getS().getCol();

        for (int row = 0; row < dungeon.getTiles().length; row++) {
            for (int col = 0; col < dungeon.getTiles()[0].length; col++) {
                if (row == playerRow && col == playerCol) {
                    g.drawImage(dungeon.getS().getImage(), x+2, y+2, null);
                }
                x = x + 24;
            }
            x = 10;
            y = y + 24;
        }
    }
    public void mousePressed(MouseEvent e) {
        if(start.contains(e.getPoint())){
            JOptionPane.showMessageDialog(this, "Game Started!");
        }
    }

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