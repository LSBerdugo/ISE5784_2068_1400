package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder extends Tube
{
    private final double height;
    public Cylinder(double h, Ray a,double r)
    {
        super(a,r);
        height=h;
    }

    @Override
    public Vector getNormal(Point p)
    {
        return null;
    }
}
