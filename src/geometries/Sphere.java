package geometries;

import primitives.Point;
import primitives.Vector;

public class Sphere extends RadialGeometry {
    private final Point center;

    public Sphere(Point p, double r) {
        super(r);
        center = p;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
