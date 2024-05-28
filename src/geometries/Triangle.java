package geometries;

import primitives.Point;
import primitives.Ray;

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

    List<Point> findIntersections(Ray ray){return null;}
}

