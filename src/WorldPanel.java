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
    private boolean started = false;
    private boolean alive = false;
    private boolean atk = false;
    private boolean ran = false;
    private String hp = "";

    public WorldPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        dungeon = new Dungeon();
        s = new Swordmaster();
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        basicSetup(g);
        g.setFont(new Font("Courier New", Font.BOLD, 25));

        if (started){
            setBackground(Color.white);
            g.clearRect(start.x,start.y, start.width + 1, start.height+1);
            g.drawString("Press the spacebar to start exploring the dungeon.", 111, 50);
            g.drawImage(dungeon.getImage(),40,280,null);
            g.drawImage(dungeon.getS().getImage(), 90, 450, null);
        }
        if(dungeon.isGameEnded()){
            g.drawString("You have obtained the mythical weapon: Skyward Blade \nYay -_-", 700, 150);
            try {
                wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.exit(0);
        }
    }
    private void next(Graphics g){
        for (int i = 0; i < 5; i ++ ){
            mon = new Monster();
            int random = (int)(Math.random() * 10);
            if (random > 8) {
                g.drawImage(mon.getImage(), 560, 520, null);
                alive = true;

                while (alive) {
                    attack = new Rectangle(250, 100, 200, 100);
                    g.drawRect(250, 100, 200, 100);
                    g.drawString("Attack", 330, 150);

                    run = new Rectangle(450, 100, 200, 100);
                    g.drawRect(450, 100, 200, 100);
                    g.drawString("Run", 530, 150);

                    if (atk){
                        dealDamage();
                        hp = "Enemy hp: " + mon.getHp() + "/ 50";
                        setBackground(Color.white);
                        g.fillRect(650,150,200,50);
                        g.drawString(hp,700,250);
                    }
                    if (ran){
                        g.clearRect(250,100,651,201);
                    }
                }
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
        if (direction.equalsIgnoreCase(" ")){
            Graphics g = super.getGraphics();
            next(g);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}