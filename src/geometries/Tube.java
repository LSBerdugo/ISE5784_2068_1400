package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Tube extends RadialGeometry
{
    protected final Ray axis;
    public Tube(Ray a, double r)
    {
        super(r);
        axis=a;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
