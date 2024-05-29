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
    private Swordmaster s;
    private Monster mon;

    public WorldPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon();
        s = new Swordmaster();
        mon = new Monster();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10;
        int y = 300;

        g.drawImage(dungeon.getImage(),x+30,y+30,null);
        g.drawImage(dungeon.getS().getImage(), x+80, y+120, null);

        if (!(dungeon.isGameEnded())){
            int random = (int)(Math.random() * 10);
            if (random > 8){
                g.drawImage(mon.getImage(), x + 30, y + 30, null);
                dealDamage();
            }
        }
        s.searchBag();
        if(dungeon.isGameEnded()){
            g.drawString("You have obtained the mythical weapon!! \n Yay -_-", 700, 150);
        }
        g.setFont(new Font("Courier New", Font.BOLD, 50));

    }

    protected void dealDamage() {
        if (s.getInventory().contains("Fillet Blade")) {
            mon.loseHP((int) (Math.random() * mon.getHp()) - 20);
        }
        if (s.getInventory().contains("The Flute")) {
            mon.loseHP((int) (Math.random() * mon.getHp()) - 5);
        }
        if (s.getInventory().contains("Skyward Blade")) {
            mon.loseHP(mon.getHp());
        }
    }

    public void mousePressed(MouseEvent e) { }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String direction = String.valueOf(e.getKeyChar());
        if (direction.equalsIgnoreCase(" ")){
            dungeon.setGameEnded();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}