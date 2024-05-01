import javax.swing.JFrame;

import java.util.ArrayList;

public class WorldFrame extends JFrame implements Runnable {

    private WorldPanel p;
    private Thread windowThread;

    public WorldFrame(String display) {
        super(display);
        p = new WorldPanel();
        this.add(p);
        this.addKeyListener(p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
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