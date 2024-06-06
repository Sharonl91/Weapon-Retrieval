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
    private Rectangle attack;
    private Rectangle run;
    private boolean started = false;
    private boolean alive = false;
    private boolean atk = false;
    private boolean ran = false;
    private boolean spawned = false;
    private String hp = "";
    private int hpValue = 50;
    private int numOfMonsterEncountered = 0;

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
                g.drawString("You have obtained the mythical weapon: Skyward Blade \nYay -_-",700, 150);
            }
            else {
                g.drawString("The dungeon collapsed", 700, 150);}
        }
    }
    private void next(Graphics g){
        g.setFont(new Font("Courier New", Font.BOLD, 25));
        if (numOfMonsterEncountered < 5){
            int random = (int)(Math.random() * 10);
            if (random > 7) {
                spawned = true;
            }
            if (spawned){
                alive = true;
                g.drawImage(mon.getImage(), 560, 320, null);
                while (alive) {
                    g.drawRect(250, 700, 200, 100);
                    g.drawString("Attack", 300, 750);

                    g.drawRect(450, 700, 200, 100);
                    g.drawString("Run", 520, 750);

                    g.drawString(hp,700,710);

                    if (atk){
                        dealDamage();
                        hpValue = mon.getHp();
                        g.fillRect(250,700,400,100);
                        g.drawString(hp,700,250);
                        atk = false;
                    }
                    if (ran){
                        g.fillRect(250,700,400,100);
                        ran = false;
                        spawned = false;
                        alive = false;
                    }
                    if(hpValue == 0){
                        s.obtainWeapon(mon.getW());
                        mon = new Monster();
                        hpValue = 50;
                    }
                }
            }
            numOfMonsterEncountered++;
            s.searchBag();
        }
        if(numOfMonsterEncountered >= 5){
            dungeon.setGameEnded();
        }
    }

    protected void basicSetup(Graphics g){
        g.setFont(new Font("Courier New", Font.BOLD, 50));
        start = new Rectangle(350,100,300,100);
        attack = new Rectangle(250, 700, 200, 100);
        run = new Rectangle(450, 700, 200, 100);
        g.drawRect(350,100,300,100);
        g.drawString("START", 430, 175);
    }
    protected void dealDamage() {
      mon.loseHP((int) (Math.random() * mon.getHp()) - 5);
    }

    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        if (e.getButton() == 1){
            if(attack.contains(p)){
                atk = true;
            }
            if(run.contains(p)){
                hp = "";
                alive = false;
                ran = true;
            }
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
            Graphics g = super.getGraphics();
            next(g);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}