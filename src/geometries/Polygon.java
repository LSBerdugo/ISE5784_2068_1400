package geometries;

import java.util.List;

import static primitives.Util.isZero;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon implements Geometry {
    /**
     * List of polygon's vertices
     */
    protected final List<Point> vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected final Plane plane;
    /**
     * The size of the polygon - the amount of the vertices in the polygon
     */
    private final int size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by
     *                 edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this.vertices = List.of(vertices);
        size = vertices.length;

        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (size == 3) return; // no need for more tests for a Triangle

        Vector n = plane.getNormal();
        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with the normal. If all the rest consequent edges will generate the same sign
        // - the polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
    }




    /**
     * Returns the normal vector to the surface of the polygon at the given point.
     * This implementation delegates to the normal vector of the underlying plane.
     *
     * @param point the point on the surface of the polygon where the normal is to be calculated
     * @return the normal vector at the given point
     */
    @Override
    public Vector getNormal(Point point) {
        return plane.getNormal();
    }


    /**
     * Finds all intersection points between the given ray and this polygon.
     *
     * @param ray the ray to intersect with the object
     * @return a list of intersection points, or null if there are no intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray){
//        //find intersection for polygon
//        List<Point> planeIntersections = plane.findIntersections(ray);
//        if (planeIntersections == null) {
//            return null;
//        }
//        Point p0 = ray.getHead();
//        Vector v = ray.getDirection();
//        Vector n = plane.getNormal();
//        double t = planeIntersections.get(0).subtract(p0).dotProduct(n) / v.dotProduct(n);
//        if (t <= 0) {
//            return null;
//        }
//        Point p = p0.add(v.scale(t));
//        Vector v1 = vertices.get(0).subtract(p).crossProduct(vertices.get(1).subtract(p)).normalize();
//        Vector v2 = vertices.get(1).subtract(p).crossProduct(vertices.get(2).subtract(p)).normalize();
//        Vector v3 = vertices.get(2).subtract(p).crossProduct(vertices.get(0).subtract(p)).normalize();
//        if (v1.dotProduct(v2) > 0 && v2.dotProduct(v3) > 0 && v3.dotProduct(v1) > 0) {
//            return List.of(p);
//        }
//        return null;
        try {
            List<Point> planeIntersections = plane.findIntersections(ray);
            if (planeIntersections == null) {
                return null;
            }

            Point intersectionPoint = planeIntersections.get(0);

            // Check if the intersection point is inside the polygon using cross products
            boolean isInside = true;
            int numVertices = vertices.size();
            for (int i = 0; i < numVertices; i++) {
                Point currentVertex = vertices.get(i);
                Point nextVertex = vertices.get((i + 1) % numVertices);
                Vector edge = nextVertex.subtract(currentVertex);
                Vector toIntersection = intersectionPoint.subtract(currentVertex);
                Vector crossProduct = edge.crossProduct(toIntersection).normalize();

                if (crossProduct.dotProduct(plane.getNormal()) < 0) {
                    isInside = false;
                    break;
                }
            }

            if (isInside) {
                return List.of(intersectionPoint);
            }

            return null;
        }
        catch(IllegalArgumentException e){
            return null;
        }
    }



}
