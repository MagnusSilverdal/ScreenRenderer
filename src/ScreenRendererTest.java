/**
 * This is a class
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public class ScreenRendererTest extends ScreenRenderer{
    private static double aspectRatio = 16/9.0;
    private static int width = 400;
    private static int height = (int)(width/aspectRatio);
    private static int scale = 4;

    private Triangle t1 = new Triangle(new Point(30,50),new Point(50,20),new Point(70,50));
    private Triangle t2 = new Triangle(new Point(90,50),new Point(110,20),new Point(130,40));
    private Triangle t3 = new Triangle(new Point(150,50),new Point(170,20),new Point(190,60));
    private Triangle t4 = new Triangle(new Point(210,50),new Point(230,50),new Point(250,60));

    public ScreenRendererTest() {
        super(width*scale, height*scale, scale);
    }

    public void update() {
        getScreen().fillTriangle(t1,0x00ffff);
        getScreen().fillTriangle(t2,0xff00ff);
        getScreen().fillTriangle(t3,0xffff00);
        getScreen().fillTriangle(t4,0x00ff00);
        getScreen().drawTriangle(t1,0xffffff);
        getScreen().drawTriangle(t2,0xffffff);
        getScreen().drawTriangle(t3,0xffffff);
        getScreen().drawTriangle(t4,0xffffff);
    }

    public static void main(String[] args) {
        ScreenRendererTest srt = new ScreenRendererTest();
        srt.start();
    }
}
