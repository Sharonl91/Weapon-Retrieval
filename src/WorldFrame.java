import World.WorldPanel;

import javax.swing.*;
import java.util.ArrayList;

public class WorldFrame extends JFrame implements Runnable {

    private WorldPanel p;
    private ArrayList<Weapon> inventory;
    private Thread windowThread;

    public WorldFrame(String display) {
        super(display);
        int frameWidth = 500;
        int frameHeight = 500;
        inventory = Weapon.buildInv();
        p = new WorldPanel(inventory);
        this.add(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(frameWidth, frameHeight);
        this.setLocation(600, 100);
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