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

    public void drawLine(Point start, Point end, int color) {
        int[] pixels = getPointsFromLine(start,end);
        for (int i = 0 ; i < pixels.length ; i++) {
            drawPixel(pixels[i++],pixels[i],color);
        }
    }

    public void drawTriangle(Triangle t, int color) {
        Line[] triangel = t.getEdges();
        for (Line l : triangel) {
            drawLine(l,color);
        }
    }

    public void fillTriangle(Triangle t, int color) {
        Line[] triangel = t.getEdges();
        for (Line l : triangel) {
            int[] pixels = getPointsFromLine(l.getStart(),l.getEnd());
            for (int i = 0 ; i < pixels.length ; i++) {
                drawPixel(pixels[i++],pixels[i],color);
            }
        }
    }

    public int[] getPointsFromLine(Point start, Point end) {
        int deltax = end.getX()-start.getX();
        int deltay = end.getY()-start.getY();
        if (deltax < 0)
            deltax = -deltax;
        if (deltay < 0)
            deltay = -deltay;
        int[] point = new int[(deltax>deltay?deltax+1:deltay+1)*2];
        // Bresenhams algorithm for fast lines
        int x, y;
        int dx, dy;
        int incx, incy;
        int balance;
        int count = 0;
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
                point[count++] = x;
                point[count++] = y;
                if (balance >= 0) {
                    y += incy;
                    balance -= dx;
                }
                balance += dy;
                x += incx;
            }
            point[count++] = x;
            point[count] = y;
        } else {
            dx <<= 1;
            balance = dx - dy;
            dy <<= 1;

            while (y != end.getY()) {
                point[count++] = x;
                point[count++] = y;
                if (balance >= 0) {
                    x += incx;
                    balance -= dy;
                }
                balance += dx;
                y += incy;
            }
            point[count++] = x;
            point[count] = y;
        }
        return point;
    }
}
