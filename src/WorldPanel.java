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
    private Swordmaster s;
    private Monster mon;
    private Rectangle start;
    private boolean started;

    public WorldPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon();
        s = new Swordmaster();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10;
        int y = 250;

        g.setFont(new Font("Courier New", Font.BOLD, 50));
        start = new Rectangle(350,100,300,100);
        g.drawRect(350,100,300,100);
        g.drawString("START", 430, 175);

        if (started){
            setBackground(Color.white);
            g.drawImage(dungeon.getImage(),x+30,y+30,null);
            g.drawImage(dungeon.getS().getImage(), x+80, y+200, null);
            int random = (int)(Math.random() * 10);
            if (random > 8){
                mon = new Monster();
                while(mon.getHp() > 0){
                    g.drawImage(mon.getImage(), x + 550, y + 270, null);
                }
            }
        }
        if(dungeon.isGameEnded()){
            g.setFont(new Font("Courier New", Font.BOLD, 20));
            g.drawString("You have obtained the mythical weapon!! \n Yay -_-", 700, 150);
            System.exit(0);
        }


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
        Point p = e.getPoint();
        if(e.getButton() == 1){
            if(start.contains(p)){
                started = true;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        String direction = String.valueOf(e.getKeyChar());
        if (direction.equalsIgnoreCase(" ")){
            dealDamage();
            s.searchBag();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}