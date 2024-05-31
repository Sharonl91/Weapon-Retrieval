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
    private Rectangle attack;
    private Rectangle run;
    private boolean started;
    private boolean alive;

    public WorldPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon();
        s = new Swordmaster();
        alive = true;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 10;
        int y = 250;

        basicSetup(g);
        g.setFont(new Font("Courier New", Font.BOLD, 25));

        if (started){
            setBackground(Color.white);
            g.clearRect(start.x,start.y, start.width + 1, start.height+1);
            g.drawString("Press the spacebar to start exploring the dungeon.", 111, 50);
            g.drawImage(dungeon.getImage(),x+30,y+30,null);
            g.drawImage(dungeon.getS().getImage(), x+80, y+200, null);

        }
        if(dungeon.isGameEnded()){
            g.drawString("You have obtained the mythical weapon!! \n Yay -_-", 700, 150);
            dungeon.getS().getImage().getGraphics().dispose();
            System.exit(0);
        }
    }
    private void next(Graphics g){
        mon = new Monster();
        int random = (int)(Math.random() * 10);
        if (random > 8){
            g.drawImage(mon.getImage(), 560, 520, null);
            int maxHP = mon.getHp();
            g.drawString("Enemy hp: " + mon.getHp() + "/ " + maxHP,700,300);
            while(alive){
                attack = new Rectangle(250,100,200,100);
                g.drawRect(250,100,200,100);
                g.drawString("Attack",330,150);

                run = new Rectangle(450,100,200,100);
                g.drawRect(450,100,200,100);
                g.drawString("Run",530,150);

            }
        }
    }

    protected void basicSetup(Graphics g){
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        start = new Rectangle(350,100,300,100);
        attack = new Rectangle();
        run = new Rectangle();
        g.drawRect(350,100,300,100);
        g.drawString("START", 430, 175);
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
            if(attack.contains(p)){
                dealDamage();
                if(mon.getHp() < 0){
                    alive = false;
                }
            }
            if(run.contains(p)){
                mon.getImage().getGraphics().dispose();
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
            Graphics g = super.getGraphics();
            next(g);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}