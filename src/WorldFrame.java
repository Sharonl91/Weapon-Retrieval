import javax.swing.JFrame;

import java.awt.*;
import java.util.ArrayList;

public class WorldFrame extends JFrame implements Runnable {

    private WorldPanel p;
    private ArrayList<Weapon> inventory;
    private Thread windowThread;

    public WorldFrame(String display) {
        super(display);
        int frameWidth = 1000;
        int frameHeight = 1000;
        inventory = Weapon.buildInv();
        p = new WorldPanel(inventory);
        this.add(p);
        this.addKeyListener(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(10, 10);
        this.setVisible(true);
        startThread();
    }

    public void startThread() {
        windowThread = new Thread(this);
        windowThread.start();
    }

    public void run() {
        while (true) {
            p.repaint();
        }
    }
}