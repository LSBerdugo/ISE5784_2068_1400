package geometries;

import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry
{
    protected double radius;
     public RadialGeometry(double r)
     {
         radius=r;
     }
}
