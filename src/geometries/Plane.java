package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry
{
    private final Point q;
    private final Vector normal;
     public Plane(Point p1,Point p2,Point p3)
     {
         normal=null;
         q=p1;
     }
    public Plane(Point p,Vector v)
    {
        q=p;
        normal=v.normalize();
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    public Vector getNormal() {
        return normal;
    }
}
