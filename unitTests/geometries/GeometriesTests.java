package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTests {

    @Test
    void testAdd() {
    }
    @Test
    void testFindIntersections()
    {
        testFindIntersections1();
        testFindIntersections2();
        testFindIntersections3();
        testFindIntersections4();
        testFindIntersections5();

    }
    final Triangle triangle=new Triangle(new Point(4,0,0),new Point(-2,0,0),new Point(0,0,3));
    final  Sphere sphere=new Sphere(new Point(0,1,1),1);
    final Plane plane=new Plane(new Point(1,1,1),new Point(2,0,1),new Point(-1,-1,1));
    final Geometries geometries=new Geometries(plane,sphere,triangle);
    // ============================ Equivalence Partitions Tests ================================
    @Test
    void testFindIntersections5() {
    }
    // ============================= Boundary Value Tests =================================
    @Test
    void testFindIntersections1()
    {
        Geometries geometries=new Geometries();
        Ray ray=new Ray(new Point(1,0,2),new Vector(-2,0,-4));
        assertNull(geometries.findIntersections(ray),"ERROR:Ray not intersect the geometries");
    }
    @Test
    void testFindIntersections2()
    {

        Ray ray=new Ray(new Point(-3,0,0),new Vector(-7,0,-1));
        assertNull(geometries.findIntersections(ray),"ERROR:Ray not intersect the geometries");
    }
    @Test
    void testFindIntersections3()
    {
        Ray ray=new Ray(new Point(-4,0,-1),new Vector(1,0,2));
        final var result =geometries.findIntersections(ray)
                .stream().toList();
        assertEquals(1,result.size(),"ERROR:Ray  intersect the geometries in one point");
    }
    @Test
    void testFindIntersections4()
    {
        Ray ray=new Ray(new Point(0,3,0),new Vector(0,-4,2));
        final var result =geometries.findIntersections(ray)
                .stream().toList();
        assertEquals(4,result.size(),"ERROR:Ray not intersect the geometries");
    }

}