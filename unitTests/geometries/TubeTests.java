package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Testing Tubes
 * This class contains tests for the `Tube` class, specifically for the `getNormal` method.
 */
class TubeTests {
    /**
     * Runs all tests for the `getNormal` method.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
        testGetNormal2();
    }

    //============================Equivalence Partitions Tests================================

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     * Tests the normal calculation for a point on the tube's surface.
     * <br>TC01: Normal calculation for point on tube's surface
     */
    @Test
    void testGetNormal1()
    {
        Ray r=new Ray(new Point(1,2,3),new Vector(0,0,5));
        Point p=new Point(5,4,2);
        assertEquals( new Vector(4,2,0).normalize(),
                        new Tube(r,5.0).getNormal(p),
                "ERROR:the get normal of the tube not correct");
    }


    //=============================Boundary Value Tests================================

    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     * Tests the normal calculation for a point outside the tube's bounds.
     * <br>TC02: Normal calculation for point outside tube's bounds
     */
    @Test
    void testGetNormal2() {
        Ray r=new Ray(new Point(2,-1,1),new Vector(1,1,1));
        Point p=new Point(1,0,1);
        assertThrows(IllegalArgumentException.class,()->
            new Tube(r,1.0).getNormal(p),
                "ERROR:the get normal of the tube not correct");
    }


    @Test
    void testFindIntersections() {
    }
    Tube tube=new Tube(new Ray(new Point(1,0,0),new Vector(0,0,1)),1);

    //============================Equivalence Partitions Tests================================

    @Test
    void testFindIntersections1() {
        Ray r=new Ray(new Point(-3,0,0),new Vector(0,5,5));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
    void testFindIntersections2() {
        Ray r=new Ray(new Point(-2,0,1),new Vector(5,0,1));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2,result.size(),"ERROR:must be 2 intersections");
    }

    @Test
    void testFindIntersections3() {
        Ray r=new Ray(new Point(1.5,0,1),new Vector(1.5,0,1));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1,result.size(),"ERROR:must be 1 intersections");
    }

    //=============================Boundary Value Tests================================

    // TC11: Ray is inside the tube (0 points)
    @Test
    void testFindIntersections4() {
        Ray r=new Ray(new Point(1,0,1),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC12: Ray is outside the tube
    @Test
    void testFindIntersections5() {
        Ray r=new Ray(new Point(3,0,1),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC13: Ray is at the tube surface
    @Test
    void testFindIntersections6() {
        Ray r=new Ray(new Point(2,0,1),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC14: Ray is inside the tube and starts against axis head
    @Test
    void testFindIntersections7() {
        Ray r=new Ray(new Point(1.5,0.5,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC15: Ray is outside the tube and starts against axis head
    @Test
    void testFindIntersections8() {
        Ray r=new Ray(new Point(1.5,3,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC16: Ray is at the tube surface and starts against axis head
    @Test
    void testFindIntersections9() {
        Ray r=new Ray(new Point(1.5,1,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC17: Ray is inside the tube and starts at axis head
    @Test
    void testFindIntersections10() {
        Ray r=new Ray(new Point(1,0,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }


    Vector v2=new Vector(0,1,0);
    @Test
    void testFindIntersections11() {
        Ray r=new Ray(new Point(3,0,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
    void testFindIntersections12() {
        Ray r=new Ray(new Point(2,-3,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
    void testFindIntersections13() {
        Ray r=new Ray(new Point(2,0,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
    void testFindIntersections14() {
        Ray r=new Ray(new Point(2,3,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
    void testFindIntersections15() {
        Ray r=new Ray(new Point(1.5,-3,0),v2);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }

 Vector v3=new Vector(-1,0,0);
    @Test
    void testFindIntersections16() {
     Ray r=new Ray(new Point(2,0,0),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );

    }

    @Test
    void testFindIntersections17() {
        Ray r=new Ray(new Point(1.5,0,0),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 2 intersections" );
    }

    Vector v4=new Vector(1,0,0);
    @Test
    void testFindIntersections18() {
        Ray r=new Ray(new Point(2,0,0),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }




















}