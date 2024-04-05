import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;


class WorldPanel extends JPanel implements MouseListener, KeyListener {
    private ArrayList<Weapon> inventory;
    private Rectangle button;

    public WorldPanel(ArrayList<Weapon> inventory) {
        this.inventory = inventory;
        button = new Rectangle(297, 150, 160, 26);
        this.addMouseListener(this);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 30;
        int y = 30;
        for (int i = 0; i < inventory.size(); i++) {
            Weapon w = inventory.get(i);
            if (i % 3 == 0 && i != 0) {
                x = 30;
                y += 80;
            }

            w.setRectangleLocation(x, y);
            g.drawImage(w.getImage(), x, y, null);
            x = x + w.getImage().getWidth() + 10;
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("GET NEW CARDS", 300, 170);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }
    public void mousePressed(MouseEvent e) {
        Point clicked = e.getPoint();
        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                Weapon.obtainWeapon();
            }

            for (int i = 0; i < inventory.size(); i++) {
                Rectangle box = inventory.get(i).getCardBox();
            }
        }
        if (e.getButton() == 3) {
            for (int i = 0; i < inventory.size(); i++) {
                Rectangle box = inventory.get(i).getCardBox();
            }
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