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
    private Rectangle slash;
    private Rectangle chop;
    private boolean started = false;
    private boolean spawned = false;
    private String hp;
    private int hpValue = 50;
    private int turns = 0;

    public WorldPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon();
        s = new Swordmaster();
        mon = new Monster();
        hp = "Enemy hp: " + hpValue + "/ 50";
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
    private void next(Graphics g) {
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        int random = (int) (Math.random() * 10);
        if (random > 7) {
            spawned = true;
            if (spawned){
                g.drawImage(mon.getImage(), 560, 320, null);
//                g.drawRect(250, 700, 200, 100);
//                g.drawString("Slash", 300, 750);
//                g.drawRect(450, 700, 200, 100);
//                g.drawString("Chop", 520, 750);
//                g.drawString(hp, 700, 710);
                if (hpValue == 0) {
                    turns++;
                    s.obtainWeapon(mon.getW());
                    s.searchBag();
                    mon = new Monster();
                    hpValue = mon.getHp();
                }
                if (turns >= 5) {
                    dungeon.setGameEnded();
                }
            }
        }
    }



    protected void basicSetup(Graphics g){
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        start = new Rectangle(350,100,300,100);
        slash = new Rectangle(250, 700, 200, 100);
        chop = new Rectangle(450, 700, 200, 100);
        g.drawRect(350,100,300,100);
        g.drawString("START", 430, 175);
    }
    protected void dealDamage(int choice) {
        if (choice == 1){
            int damage = (int) (Math.random() * mon.getHp()) - 20;
            mon.setHp(hpValue - damage);
            hpValue = mon.getHp();
        }
        if(choice == 2){
            int damage = (int) (Math.random() * mon.getHp()) - 5;
            mon.setHp(hpValue - damage);
            hpValue = mon.getHp();
        }
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
        Graphics g = super.getGraphics();
        String direction = String.valueOf(e.getKeyChar());
        if (started && direction.equalsIgnoreCase(" ")){
            next(g);
        }
//        if (started && direction.equalsIgnoreCase("q")){
//            dealDamage(1);
//            g.fillRect(250,700,400,100);
//            g.drawString(hp,700,250);
//        }
//        if (started && direction.equalsIgnoreCase("e")){
//            dealDamage(2);
//            g.fillRect(250,700,400,100);
//            g.drawString(hp,700,250);
//        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}