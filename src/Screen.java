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
        System.out.println("--" + color);
        Line[] triangle = t.getEdges();
        // Only works for triangles with 2:nd Point as top, need to order them
        int[] pixelsLine1 = getPointsFromLine(triangle[0].getEnd(),triangle[0].getStart());
        int[] pixelsLine2 = getPointsFromLine(triangle[1].getStart(),triangle[1].getEnd());
        int[] pixelsLine3;
        if (triangle[2].getEnd().getY() < triangle[2].getStart().getY()) {
            pixelsLine3 = getPointsFromLine(triangle[2].getEnd(),triangle[2].getStart());
        } else {
            pixelsLine3 = getPointsFromLine(triangle[2].getStart(),triangle[2].getEnd());
        }

        // Debug...
        /*for (int i = 0 ; i < pixelsLine1.length ; i+=2) {
            System.out.println("("+pixelsLine1[i]+","+pixelsLine1[i+1]+")");
        }
        System.out.println();
        for (int i = 0 ; i < pixelsLine2.length ; i+=2) {
            System.out.println("("+pixelsLine2[i]+","+pixelsLine2[i+1]+")");
        }
        System.out.println();
        for (int i = 0 ; i < pixelsLine3.length ; i+=2) {
            System.out.println("("+pixelsLine3[i]+","+pixelsLine3[i+1]+")");
        }
        System.out.println();*/
        // end Debug..

        int index1 = 0;
        int index2 = 0;
        while (index1 <= pixelsLine1.length-1 && index2 <= pixelsLine2.length-1) {
            while(index2 < pixelsLine2.length-3 && pixelsLine2[index2+1] == pixelsLine2[index2+3]) {
                index2+=2;
            }
            System.out.println("u ("+pixelsLine1[index1]+","+pixelsLine1[index1+1]+") - ("+pixelsLine2[index2]+","+pixelsLine2[index2+1]+")");
            for (int x = pixelsLine1[index1] ; x <= pixelsLine2[index2] ; x++) {
                drawPixel(x,pixelsLine1[index1+1],color);
            }
            while(index1 < pixelsLine1.length-3 && pixelsLine1[index1+1] == pixelsLine1[index1+3]) {
                index1+=2;
            }
            index1+=2;
            index2+=2;
        }

        int index3 = 0;
        //while (index3 < pixelsLine3.length - 3 && pixelsLine3[index3 + 1] == pixelsLine1[index1 - 1]) {
        //    index3 += 2;
        //}
            if (triangle[2].getStart().getY() > triangle[2].getEnd().getY()) {
                System.out.println(index3 + " " + index2);

                while (index3 < pixelsLine3.length - 1 && index2 < pixelsLine2.length - 1 && pixelsLine3[index3 + 1] < pixelsLine2[index2 + 1]) {
                    index3 += 2;
                }
                while (index3 <= pixelsLine3.length - 1 && index2 <= pixelsLine2.length - 1) {
                    while (index2 < pixelsLine2.length - 3 && pixelsLine2[index2 + 1] == pixelsLine2[index2 + 3]) {
                        index2 += 2;
                    }
                    System.out.println("l\\ ("+pixelsLine3[index3]+","+pixelsLine3[index3+1]+") - ("+pixelsLine2[index2]+","+pixelsLine2[index2+1]+")");
                    for (int x = pixelsLine3[index3]; x <= pixelsLine2[index2]; x++) {
                        drawPixel(x, pixelsLine3[index3 + 1], color);
                    }
                    while (index3 <= pixelsLine3.length - 3 && pixelsLine3[index3 + 1] == pixelsLine3[index3 + 3]) {
                        index3 += 2;
                    }
                    index3 += 2;
                    index2 += 2;
                }
                /*System.out.println("l\\ ("+pixelsLine3[index3]+","+pixelsLine3[index3+1]+") - ("+pixelsLine2[index2]+
                        ","+pixelsLine2[index2+1]+")");
                for (int x = pixelsLine3[index3]; x <= pixelsLine2[index2]; x++) {
                    drawPixel(x, pixelsLine3[index3 + 1], color);
                }*/
                System.out.println("Halt");
            }else {
                System.out.println(index1 + " " + index3 );
                while (index3 < pixelsLine3.length - 1 && index1 < pixelsLine1.length - 1 && pixelsLine3[index3 + 1] < pixelsLine1[index1 + 1]) {
                        index3 += 2;
                    }
                    while (index3 <= pixelsLine3.length-1 && index1 <= pixelsLine1.length-1) {
                        while (index3 < pixelsLine3.length - 3 && pixelsLine3[index3 + 1] == pixelsLine3[index3 + 3]) {
                            index3 += 2;
                        }

                        System.out.println("l/ ("+pixelsLine1[index1]+","+pixelsLine1[index1+1]+") - ("+pixelsLine3[index3]+","+pixelsLine3[index3+1]+")");
                        for (int x = pixelsLine1[index1]; x <= pixelsLine3[index3]; x++) {
                            drawPixel(x, pixelsLine3[index3 + 1], color);
                        }
                        while (index1 < pixelsLine1.length - 3 && pixelsLine1[index1 + 1] == pixelsLine1[index1 + 3]) {
                            index1 += 2;
                        }
                        index3 += 2;
                        index1 += 2;
                    }
            }

        /*for (Line l : triangle) {
            int[] pixels = getPointsFromLine(l.getStart(),l.getEnd());
            System.out.println();
            for (int i = 0 ; i < pixels.length ; i++) {
                System.out.println("("+pixels[i]+","+pixels[i+1]+")");
                drawPixel(pixels[i++],pixels[i],color);
            }
        }*/

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
