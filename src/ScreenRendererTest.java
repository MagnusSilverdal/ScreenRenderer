/**
 * This is a class
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public class ScreenRendererTest extends ScreenRenderer{

    public ScreenRendererTest(int width, int height, int scale) {
        super(width, height, scale);
    }

    public void update() {
        System.out.println("test");
    }

    public static void main(String[] args) {
        ScreenRendererTest srt = new ScreenRendererTest(1200,800,2);
        srt.start();
    }
}
