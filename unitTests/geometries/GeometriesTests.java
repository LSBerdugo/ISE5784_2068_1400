package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Testing Geometries
 * This class contains tests for the `Geometries` class, specifically for the `findIntersections` method.
 */
class GeometriesTests {

    /**
     * Runs all tests for the `findIntersections` method.
     */
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
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     * Tests the case where the ray intersects multiple geometries.
     * <br>TC05: Ray intersects multiple geometries
     */
    @Test
    void testFindIntersections5()
    {
        Ray ray=new Ray(new Point(0,2,1.5),new Vector(1,-3,0.5));
        final var result =geometries.findIntersections(ray)
                .stream().toList();
        assertEquals(3,result.size(),"ERROR:Ray not intersect the geometries");
    }
    // ============================= Boundary Value Tests =================================
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     * Tests the case where the geometries set is empty.
     * <br>TC01: Empty geometries set
     */
    @Test
    void testFindIntersections1()
    {
        Geometries geometries=new Geometries();
        Ray ray=new Ray(new Point(1,0,2),new Vector(-2,0,-4));
        assertNull(geometries.findIntersections(ray),"ERROR:Ray not intersect the geometries");
    }
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     * Tests the case where the ray does not intersect any geometry.
     * <br>TC02: Ray does not intersect any geometry
     */
    @Test
    void testFindIntersections2()
    {

        Ray ray=new Ray(new Point(-3,0,0),new Vector(-7,0,-1));
        assertNull(geometries.findIntersections(ray),"ERROR:Ray not intersect the geometries");
    }
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     * Tests the case where the ray intersects one geometry.
     * <br>TC03: Ray intersects one geometry
     */
    @Test
    void testFindIntersections3()
    {
        Ray ray=new Ray(new Point(-4,0,-1),new Vector(1,0,2));
        final var result =geometries.findIntersections(ray)
                .stream().toList();
        assertEquals(1,result.size(),"ERROR:Ray  intersect the geometries in one point");
    }
    /**
     * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
     * Tests the case where the ray intersects multiple geometries.
     * <br>TC04: Ray intersects multiple geometries
     */
    @Test
    void testFindIntersections4()
    {
        Ray ray=new Ray(new Point(0,2,0),new Vector(1,-3,2));
        final var result =geometries.findIntersections(ray)
                .stream().toList();
        assertEquals(4,result.size(),"ERROR:Ray not intersect the geometries");
    }

}