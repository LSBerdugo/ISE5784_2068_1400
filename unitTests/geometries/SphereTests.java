package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Testing Spheres
 * This class contains tests for the `Sphere` class, specifically for the `getNormal` method.
 */
class SphereTests {
    /**
     * Runs all tests for the `getNormal` method.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
    }

    //============================Equivalence Partitions Tests================================

    /**
     * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
     * Tests the normal calculation for a point on the sphere's surface.
     * <br>TC01: Normal calculation for point on sphere's surface
     */
    @Test
    void testGetNormal1()
    {
        assertEquals(new Vector(1, 0, 0).normalize(),
                new Sphere(new Point(0,0,0), 1.0).getNormal(new Point(1,0,0)),
                "ERROR:The normal of the sphere is no correct");
    }

    Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);


    @Test
    void testFindIntersections() {
        testFindIntersections1();
        testFindIntersections2();
        testFindIntersections3();
        testFindIntersections4();
        testFindIntersections5();
        testFindIntersections6();
        testFindIntersections7();
        testFindIntersections8();
        testFindIntersections9();
        testFindIntersections10();
        testFindIntersections11();
        testFindIntersections12();
        testFindIntersections13();
        testFindIntersections14();
        testFindIntersections15();
        testFindIntersections16();
        testFindIntersections17();
    }
    // ============================ Equivalence Partitions Tests ================================

    //zero point
    @Test
    void testFindIntersections1()
    {
        Ray r=new Ray(new Point(-1,0,-1),new Vector(1,0,4));
        assertNull(sphere.findIntersections(r),"ERROR:Ray intersects the sphere ");
    }
    @Test
    void testFindIntersections2()
    {
        Ray r=new Ray(new Point(-1,0,-1),new Vector(-3,0,-3));
        assertNull(sphere.findIntersections(r),"ERROR:Ray intersects the sphere ");
    }

    //one point (change ray)
    @Test
    void testFindIntersections3()
    {
        Ray r=new Ray(new Point(0.5,0,0),new Vector(1.5,0,2));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(1,result.size(),"ERROR:Ray intersects the sphere in one point");
    }

    //two point (change ray)
    @Test
    void testFindIntersections4()
    {
        Ray r=new Ray(new Point(-1,0,-1),new Vector(3,0,3));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(2,result.size(),"ERROR:Ray intersects the sphere in two points");
    }

    // ============================= Boundary Value Tests =================================
    //zero point
    @Test
    void testFindIntersections5()
    {
        Ray r=new Ray(new Point(0,0,0),new Vector(-1,-1,-1));
        assertNull(  sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    //one point
    @Test
    void testFindIntersections6()
    {
        Ray r=new Ray(new Point(0,0,0),new Vector(2,0,2));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(1,  result.size(),"ERROR:Ray intersects the sphere in one point");
    }
    @Test
    void testFindIntersections7()
    {
        Ray r=new Ray(new Point(0,0,0),new Vector(0,0,2));
        assertNull( sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    @Test
    void testFindIntersections8()
    {
        Ray r=new Ray(new Point(0,0,-2),new Vector(0,0,2));
        assertNull( sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    @Test
    void testFindIntersections9()
    {
        Ray r=new Ray(new Point(0,0,1),new Vector(0,0,2));
        assertNull( sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    @Test
    void testFindIntersections10()
    {
        Ray r=new Ray(new Point(0,0,0),new Vector(-2,0,0));
        assertNull( sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    @Test
    void testFindIntersections11()
    {
        Ray r=new Ray(new Point(1,0,0),new Vector(-2,0,0));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(1,  result.size(),"ERROR:Ray intersects the sphere in one point");
    }
    @Test
    void testFindIntersections12()
    {
        Ray r=new Ray(new Point(2,0,0),new Vector(-2,0,0));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(1,  result.size(),"ERROR:Ray intersects the sphere in one point");
    }
    @Test
    void testFindIntersections13()
    {
        Ray r=new Ray(new Point(-1,0,0),new Vector(-2,0,0));
        assertNull( sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    @Test
    void testFindIntersections14() {
        Ray r = new Ray(new Point(3, 0, 0), new Vector(-2, 0, 0));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(2, result.size(), "ERROR:Ray intersects the sphere in two point");
    }
    @Test
    void testFindIntersections15(){
        Ray r = new Ray(new Point(0.5, 0, 0), new Vector(-2, 0, 0));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(1, result.size(), "ERROR:Ray intersects the sphere in one point");
    }
    @Test
    void testFindIntersections16()
    {
        Ray r = new Ray(new Point(0.5, 0, 0), new Vector(0, 0, 1));
        final var result = sphere.findIntersections(r).stream().toList();
        assertEquals(1, result.size(), "ERROR:Ray intersects the sphere in one point");
    }
    @Test
    void testFindIntersections17()
    {
        Ray r=new Ray(new Point(-1,0,0),new Vector(0,0,1));
        assertNull( sphere.findIntersections(r),"ERROR:Ray does not intersects the sphere");
    }
    @Test
    void testFindIntersections18()
    {
        final  Sphere sphere=new Sphere(new Point(0,1,1),1);
        Ray ray=new Ray(new Point(0,3,0),new Vector(0,-4,2));
        final var result =sphere.findIntersections(ray)
                .stream().toList();
        assertEquals( 2,result.size(),"ERROR:Ray does not intersects the sphere");
    }


}