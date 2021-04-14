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
        sortVertices();
        edges[0] = new Line(vertices[0],vertices[1]);
        edges[1] = new Line(vertices[1],vertices[2]);
        edges[2] = new Line(vertices[2],vertices[0]);
    }

    public Line[] getEdges() {
        return edges;
    }

    // Find the ordering of vertices for Bresenhamstyle fill...
    public void sortVertices() {

    }
}
