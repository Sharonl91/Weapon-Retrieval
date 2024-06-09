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
    Swordmaster s;
    Monster mon;
    private Rectangle start;
    private boolean started = false;

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

        basicSetup(g);
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        if (started){
            setBackground(Color.white);
            g.clearRect(start.x,start.y, start.width + 1, start.height+1);
            g.drawString("Press the spacebar to start exploring the dungeon.", 111, 50);
            g.drawImage(dungeon.getImage(),30,80,null);
            g.drawImage(dungeon.getS().getImage(), 90, 150, null);
            g.drawImage(mon.getImage(), 560, 320, null);
        }
        if(dungeon.isGameEnded()){
            if(s.isFound()) {
                g.drawString("You have obtained the mythical weapon: Skyward Blade",200, 750);
                g.drawString("Yay -_-",200, 800);
            }
            else {
                g.drawString("The dungeon collapsed >_<", 200, 750);
                g.drawString("You were buried for eternity", 200, 800);
            }
        }
    }

    private void next() {
        int damage = (int) (Math.random() * mon.getHp()) - 5;
        mon.setHp(mon.getHp() - damage);
        if(mon.getHp() <= 0){
            s.obtainWeapon(mon.getW());
            s.searchBag();
        }
    }

    protected void basicSetup(Graphics g){
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        start = new Rectangle(350,100,300,100);
        g.drawRect(350,100,300,100);
        g.drawString("START", 430, 175);
    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if (e.getButton() == 1){
            if (start.contains(p)){
                started = true;
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
        String direction = String.valueOf(e.getKeyChar());
        if (started && direction.equalsIgnoreCase(" ")){
            next();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}