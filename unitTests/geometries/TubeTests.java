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

    // **** Group: Ray is orthogonal to axis and not begins against the axis head
    // *****************
    Vector v2=new Vector(0,1,0);
    // TC21: Ray starts outside and the line is outside (0 points)
    @Test
    void testFindIntersections11() {
        Ray r=new Ray(new Point(3,0,1),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
        // TC22: The line is tangent and the ray starts before the tube (0 points)
    void testFindIntersections12() {
        Ray r=new Ray(new Point(2,-3,1),v2);
       assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");

    }
    // TC23: The line is tangent and the ray starts at the tube (0 points)
    @Test
    void testFindIntersections13() {
        Ray r=new Ray(new Point(2,0,1),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC24: The line is tangent and the ray starts after the tube (0 points)
    @Test
    void testFindIntersections14() {
        Ray r=new Ray(new Point(2,3,1),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC25: Ray starts before (2 points)6
    @Test
    void testFindIntersections15() {
        Ray r=new Ray(new Point(1.5,-3,1),v2);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }

 Vector v3=new Vector(-1,0,0);
    // TC26: Ray starts at the surface and goes inside (1 point)
    @Test
    void testFindIntersections16() {
     Ray r=new Ray(new Point(2,0,1),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );

    }
    // TC27: Ray starts inside (1 point)
    @Test
    void testFindIntersections17() {
        Ray r=new Ray(new Point(1.5,0,1),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );
    }

    Vector v4=new Vector(1,0,1);
    // TC28: Ray starts at the surface and goes outside (0 points)
    @Test
    void testFindIntersections18() {
        Ray r=new Ray(new Point(2,0,1),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC29: Ray starts after
    @Test
    void testFindIntersections19() {
        Ray r=new Ray(new Point(3,0,1),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC30: Ray starts before and crosses the axis (2 points)
    @Test
    void testFindIntersections20() {
        Ray r=new Ray(new Point(1,3,1),new Vector(0,-1,0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }
    // TC31: Ray starts at the surface and goes inside and crosses the axis
    @Test
    void testFindIntersections21() {
        Ray r = new Ray(new Point(1, 1, 1), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC32: Ray starts inside and the line crosses the axis (1 point)
    @Test
    void testFindIntersections22() {
        Ray r = new Ray(new Point(1, 0.5, 1), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC35: Ray start at the axis
    @Test
    void testFindIntersections23() {
        Ray r=new Ray(new Point(1,0,1),new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // **** Group: Ray is orthogonal to axis and begins against the axis head
    // *****************
    // TC41: Ray starts outside and the line is outside (
    @Test
    void testFindIntersections24() {
        Ray r=new Ray(new Point(3,0,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    @Test
        // TC22: The line is tangent and the ray starts before the tube (0 points)
    void testFindIntersections25() {
        Ray r=new Ray(new Point(2,-3,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");

    }
    // TC23: The line is tangent and the ray starts at the tube (0 points)
    @Test
    void testFindIntersections26() {
        Ray r=new Ray(new Point(2,0,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC24: The line is tangent and the ray starts after the tube (0 points)
    @Test
    void testFindIntersections27() {
        Ray r=new Ray(new Point(2,3,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC25: Ray starts before (2 points)6
    @Test
    void testFindIntersections28() {
        Ray r=new Ray(new Point(1.5,-3,0),v2);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }

    // TC26: Ray starts at the surface and goes inside (1 point)
    @Test
    void testFindIntersections29() {
        Ray r=new Ray(new Point(2,0,0),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );

    }
    // TC27: Ray starts inside (1 point)
    @Test
    void testFindIntersections30() {
        Ray r=new Ray(new Point(1.5,0,0),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );
    }

    // TC28: Ray starts at the surface and goes outside (0 points)
    @Test
    void testFindIntersections31() {
        Ray r=new Ray(new Point(2,0,0),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC29: Ray starts after
    @Test
    void testFindIntersections32() {
        Ray r=new Ray(new Point(3,0,0),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC30: Ray starts before and crosses the axis (2 points)
    @Test
    void testFindIntersections33() {
        Ray r=new Ray(new Point(1,3,0),new Vector(0,-1,0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }
    // TC31: Ray starts at the surface and goes inside and crosses the axis
    @Test
    void testFindIntersections34() {
        Ray r = new Ray(new Point(1, 1, 0), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC32: Ray starts inside and the line crosses the axis (1 point)
    @Test
    void testFindIntersections35() {
        Ray r = new Ray(new Point(1, 0.5, 0), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC35: Ray start at the axis head
    @Test
    void testFindIntersections36() {
        Ray r=new Ray(new Point(1,0,0),new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // **** Group: Ray's line is neither parallel nor orthogonal to the axis and
    // *****************
    // begins against axis head
    // TC61: Ray's line is outside the tube
    Point p1=new Point(2,-2,0);
    @Test
    void testFindIntersections37() {
        Ray r=new Ray(p1,new Vector(1, 1, 1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC62: Ray's line crosses the tube and begins before
    @Test
    void testFindIntersections38() {
        Ray r=new Ray(p1,new Vector(-1, 4, 4));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(), "ERROR:must be 2 intersections");
    }
    // TC51: Ray starts at the surface and goes inside and goes through the axis
    // head
    @Test
    void testFindIntersections39() {
        Ray r=new Ray(new Point(1,-1,0),new Vector(0 , 2, 1));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC55: Ray start at the axis head
    @Test
    void testFindIntersections40() {
        Ray r=new Ray(new Point(1,0,0),new Vector(0 ,1,1 ));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }





















}