/**
 * This is a class
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public class Screen {
    private int[] pixels;
    private int width;
    private int height;

    public Screen(int[] pixels, int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public void drawPixel(int x, int y , int color) {
        pixels[y*width+x] = color;
    }

    public void drawPixel(Point p, int color) {
        drawPixel(p.getX(), p.getY(), color);
    }

    public void drawLine(Line l, int color) {
        drawLine(l.getStart(),l.getEnd(),color);
    }

    // Bresenhams algorithm for fast lines
    public void drawLine(Point start, Point end, int color) {
        int x, y;
        int dx, dy;
        int incx, incy;
        int balance;

        if (end.getX() >= start.getX()) {
            dx = end.getX() - start.getX();
            incx = 1;
        } else {
            dx = start.getX() - end.getX();
            incx = -1;
        }

        if (end.getY() >= start.getY()) {
            dy = end.getY() - start.getY();
            incy = 1;
        } else {
            dy = start.getY() - end.getY();
            incy = -1;
        }

        x = start.getX();
        y = start.getY();

        if (dx >= dy) {
            dy <<= 1;
            balance = dy - dx;
            dx <<= 1;

            while (x != end.getX()) {
                drawPixel(x, y, color);
                if (balance >= 0) {
                    y += incy;
                    balance -= dx;
                }
                balance += dy;
                x += incx;
            }
            drawPixel(x, y, color);
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != end.getY()) {
                drawPixel(x, y, color);
                if (balance >= 0) {
                    x += incx;
                    balance -= dy;
                }
                balance += dx;
                y += incy;
            }
            drawPixel (x, y, color);
        }
    }
}
