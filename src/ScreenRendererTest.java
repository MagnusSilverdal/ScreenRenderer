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

    public ScreenRendererTest() {
        super(width*scale, height*scale, scale);
    }

    public void update() {
        Line l = new Line(new Point(20,30), new Point(40,60));
        getScreen().drawLine(l,0xFF00FF);
        getScreen().drawPixel((int)(Math.random()*width),(int)(Math.random()*height),0xFFFFFF);
    }

    public static void main(String[] args) {
        ScreenRendererTest srt = new ScreenRendererTest();
        srt.start();
    }
}
