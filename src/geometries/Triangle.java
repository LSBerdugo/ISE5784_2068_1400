package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.List;

/**
 * Represents a triangle in three-dimensional space.
 * A triangle is a polygon with three edges and three vertices.
 * It extends the Polygon class.
 */
public class Triangle extends Polygon
{
    public Triangle(Point p1, Point p2, Point p3)
    {
      super(p1,p2,p3);
    }

   public List<Point> findIntersections(Ray ray){
        if(plane.findIntersections(ray)==null)
            return null;
        Point p1=vertices.get(0);
        Point p2=vertices.get(1);
        Point p3=vertices.get(2);
        Vector v1=p1.subtract(ray.getHead());
        Vector v2=p2.subtract(ray.getHead());
        Vector v3=p3.subtract(ray.getHead());
        Vector n1=v1.crossProduct(v2).normalize();
        Vector n2=v2.crossProduct(v3).normalize();
        Vector n3=v3.crossProduct(v1).normalize();
        if((ray.getDirection().dotProduct(n1)>0 && ray.getDirection().dotProduct(n2)>0 && ray.getDirection().dotProduct(n3)>0)
                ||( ray.getDirection().dotProduct(n1)<0 && ray.getDirection().dotProduct(n2)<0 && ray.getDirection().dotProduct(n3)<0)) {

           List<Point> l= plane.findIntersections(ray);
           Point p=l.get(0);
           Vector AP=p.subtract(p1);
           Vector BP=p.subtract(p2);
          Vector CP=p.subtract(p3);
          Vector AB=p2.subtract(p1);
            Vector BC=p3.subtract(p2);
            Vector CA=p1.subtract(p3);
            if(AP.crossProduct(AB).lengthSquared()==0 || BP.crossProduct(BC).lengthSquared()==0 || CP.crossProduct(CA).lengthSquared()==0)
                return null;
                return l;

        }
         return null;
    }
}

