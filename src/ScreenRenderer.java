import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * A system for drawing pixelgraphics to the screen using native Java.
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public abstract class ScreenRenderer extends Canvas implements Runnable{
    private int WIDTH;
    private int HEIGTH;
    private int scale;

    private JFrame frame;
    private String title = "";
    private BufferedImage image;
    private Screen screen;

    private Thread thread;
    private boolean running = false;
    private int fps = 25;
    private int ups = 1;

    public ScreenRenderer(int width, int height, int scale) {
        // Screen data
        this.WIDTH = width;
        this.HEIGTH = height;
        this.scale = scale;
        image = new BufferedImage(WIDTH/scale, HEIGTH/scale, BufferedImage.TYPE_INT_RGB);
        screen = new Screen(((DataBufferInt) image.getRaster().getDataBuffer()).getData(),image.getWidth(),
                image.getHeight());
        // Frame data
        setPreferredSize(new Dimension(WIDTH, HEIGTH));
        frame = new JFrame(title);
        frame.setResizable(false);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.requestFocus();
    }

    public synchronized void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double nsFPS = 1000000000.0 / fps;
        double nsUPS = 1000000000.0 / ups;
        double deltaFPS = 0;
        double deltaUPS = 0;
        int frames = 0;
        int updates = 0;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();

        while (running) {
            long now = System.nanoTime();
            deltaFPS += (now - lastTime) / nsFPS;
            deltaUPS += (now - lastTime) / nsUPS;
            lastTime = now;

            while(deltaUPS >= 1) {
                update();
                updates++;
                deltaUPS--;
            }

            if (deltaFPS >= 1) {
                render();
                frames++;
                deltaFPS--;
            }

            if(System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle(this.title + "  |  " + updates + " ups, " + frames + " fps");
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH, HEIGTH, null);
        g.dispose();
        bs.show();
    }

    public abstract void update();

    public Screen getScreen() {
        return screen;
    }

}
