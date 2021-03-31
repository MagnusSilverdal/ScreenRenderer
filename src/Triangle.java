/**
 * 2D Triangle in screen coordinates
 * Vertices must come in clockwise order
 * Created 2021-03-31
 *
 * @author Magnus Silverdal
 */
public class Triangle {
    private Point[] vertices = new Point[3];
    private Line[] edges = new Line[3];

    public Triangle(Point p0, Point p1, Point p2) {
        vertices[0] = p0;
        vertices[1] = p1;
        vertices[2] = p2;
        edges[0] = new Line(p0,p1);
        edges[1] = new Line(p1,p2);
        edges[2] = new Line(p2,p0);
    }

    public Line[] getEdges() {
        return edges;
    }
}
