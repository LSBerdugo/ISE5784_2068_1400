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


    // TC01: Ray's line is outside the tube (0 points)
    @Test
    void testFindIntersections1() {
        Ray r=new Ray(new Point(-3,0,0),new Vector(0,5,5));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC02: Ray's crosses the tube (2 points)
    @Test
    void testFindIntersections2() {
        Ray r=new Ray(new Point(-2,0,1),new Vector(5,0,1));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2,result.size(),"ERROR:must be 2 intersections");
    }

    // TC03: Ray's starts within tube and crosses the tube (1 point)
    @Test
    void testFindIntersections3() {
        Ray r=new Ray(new Point(1.5,0,1),new Vector(1.5,0,1));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1,result.size(),"ERROR:must be 1 intersections");
    }

    //=============================Boundary Value Tests================================

    // TC04: Ray is inside the tube (0 points)
    @Test
    void testFindIntersections4() {
        Ray r=new Ray(new Point(1,0,1),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC05: Ray is outside the tube
    @Test
    void testFindIntersections5() {
        Ray r=new Ray(new Point(3,0,1),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC06: Ray is at the tube surface
    @Test
    void testFindIntersections6() {
        Ray r=new Ray(new Point(2,0,1),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC07: Ray is inside the tube and starts against axis head
    @Test
    void testFindIntersections7() {
        Ray r=new Ray(new Point(1.5,0.5,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC08: Ray is outside the tube and starts against axis head
    @Test
    void testFindIntersections8() {
        Ray r=new Ray(new Point(1.5,3,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC09: Ray is at the tube surface and starts against axis head
    @Test
    void testFindIntersections9() {
        Ray r=new Ray(new Point(1.5,1,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    // TC10: Ray is inside the tube and starts at axis head
    @Test
    void testFindIntersections10() {
        Ray r=new Ray(new Point(1,0,0),new Vector(0,0,1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    //----------Group: Ray is orthogonal to axis and not begins against the axis head-------
    Vector v2=new Vector(0,1,0);
    // TC11: Ray starts outside and the line is outside (0 points)
    @Test
    void testFindIntersections11() {
        Ray r=new Ray(new Point(3,0,1),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }

    @Test
        // TC12: The line is tangent and the ray starts before the tube (0 points)
    void testFindIntersections12() {
        Ray r=new Ray(new Point(2,-3,1),v2);
       assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");

    }
    // TC13: The line is tangent and the ray starts at the tube (0 points)
    @Test
    void testFindIntersections13() {
        Ray r=new Ray(new Point(2,0,1),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC14: The line is tangent and the ray starts after the tube (0 points)
    @Test
    void testFindIntersections14() {
        Ray r=new Ray(new Point(2,3,1),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC15: Ray starts before (2 points)6
    @Test
    void testFindIntersections15() {
        Ray r=new Ray(new Point(1.5,-3,1),v2);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }

 Vector v3=new Vector(-1,0,0);
    // TC16: Ray starts at the surface and goes inside (1 point)
    @Test
    void testFindIntersections16() {
     Ray r=new Ray(new Point(2,0,1),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );

    }
    // TC17: Ray starts inside (1 point)
    @Test
    void testFindIntersections17() {
        Ray r=new Ray(new Point(1.5,0,1),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );
    }

    Vector v4=new Vector(1,0,1);
    // TC18: Ray starts at the surface and goes outside (0 points)
    @Test
    void testFindIntersections18() {
        Ray r=new Ray(new Point(2,0,1),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC19: Ray starts after
    @Test
    void testFindIntersections19() {
        Ray r=new Ray(new Point(3,0,1),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC20: Ray starts before and crosses the axis (2 points)
    @Test
    void testFindIntersections20() {
        Ray r=new Ray(new Point(1,3,1),new Vector(0,-1,0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }
    // TC21: Ray starts at the surface and goes inside and crosses the axis
    @Test
    void testFindIntersections21() {
        Ray r = new Ray(new Point(1, 1, 1), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC22: Ray starts inside and the line crosses the axis (1 point)
    @Test
    void testFindIntersections22() {
        Ray r = new Ray(new Point(1, 0.5, 1), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC23: Ray start at the axis
    @Test
    void testFindIntersections23() {
        Ray r=new Ray(new Point(1,0,1),new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }






    // -------- Group: Ray is orthogonal to axis and begins against the axis head--------
    // TC24: Ray starts outside and the line is outside (
    @Test
    void testFindIntersections24() {
        Ray r=new Ray(new Point(3,0,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    @Test
        // TC25: The line is tangent and the ray starts before the tube (0 points)
    void testFindIntersections25() {
        Ray r=new Ray(new Point(2,-3,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");

    }
    // TC26: The line is tangent and the ray starts at the tube (0 points)
    @Test
    void testFindIntersections26() {
        Ray r=new Ray(new Point(2,0,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC27: The line is tangent and the ray starts after the tube (0 points)
    @Test
    void testFindIntersections27() {
        Ray r=new Ray(new Point(2,3,0),v2);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC28: Ray starts before (2 points)6
    @Test
    void testFindIntersections28() {
        Ray r=new Ray(new Point(1.5,-3,0),v2);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }

    // TC29: Ray starts at the surface and goes inside (1 point)
    @Test
    void testFindIntersections29() {
        Ray r=new Ray(new Point(2,0,0),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );

    }
    // TC30: Ray starts inside (1 point)
    @Test
    void testFindIntersections30() {
        Ray r=new Ray(new Point(1.5,0,0),v3);
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(),"ERROR:must be 1 intersections" );
    }

    // TC31: Ray starts at the surface and goes outside (0 points)
    @Test
    void testFindIntersections31() {
        Ray r=new Ray(new Point(2,0,0),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC32: Ray starts after
    @Test
    void testFindIntersections32() {
        Ray r=new Ray(new Point(3,0,0),v4);
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC33: Ray starts before and crosses the axis (2 points)
    @Test
    void testFindIntersections33() {
        Ray r=new Ray(new Point(1,3,0),new Vector(0,-1,0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(),"ERROR:must be 2 intersections" );
    }
    // TC34: Ray starts at the surface and goes inside and crosses the axis
    @Test
    void testFindIntersections34() {
        Ray r = new Ray(new Point(1, 1, 0), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC35: Ray starts inside and the line crosses the axis (1 point)
    @Test
    void testFindIntersections35() {
        Ray r = new Ray(new Point(1, 0.5, 0), new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC36: Ray start at the axis head
    @Test
    void testFindIntersections36() {
        Ray r=new Ray(new Point(1,0,0),new Vector(0, -1, 0));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }





    // -------------- Group: Ray's line is neither parallel nor orthogonal to the axis and begins against axis head------------
    // TC37: Ray's line is outside the tube
    Point p1=new Point(2,-2,0);
    @Test
    void testFindIntersections37() {
        Ray r=new Ray(p1,new Vector(1, 1, 1));
        assertNull(tube.findIntersections(r),"ERROR:Ray does not intersect the tube");
    }
    // TC38: Ray's line crosses the tube and begins before
    @Test
    void testFindIntersections38() {
        Ray r=new Ray(p1,new Vector(-1, 4, 4));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(2, result.size(), "ERROR:must be 2 intersections");
    }
    // TC39: Ray starts at the surface and goes inside and goes through the axis
    // head
    @Test
    void testFindIntersections39() {
        Ray r=new Ray(new Point(1,-1,0),new Vector(0 , 2, 1));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }
    // TC40: Ray start at the axis head
    @Test
    void testFindIntersections40() {
        Ray r=new Ray(new Point(1,0,0),new Vector(0 ,1,1 ));
        final var result = tube.findIntersections(r)
                .stream().toList();
        assertEquals(1, result.size(), "ERROR:must be 1 intersections");
    }









    @Test
    public void testFindGeoIntersectionsHelper() {
        testIntersectionsHelper1();
        testIntersectionsHelper2();
        testIntersectionsHelper3();
        testIntersectionsHelper4();
    }

    Point axisPoint = new Point(0, 0, 0);
    Vector axisDirection = new Vector(0, 1, 0);
    double radius = 1.0;
    Tube tube1=new Tube(new Ray(axisPoint, axisDirection), radius);

    @Test
    public void testIntersectionsHelper1()
{


        Point rayOrigin = new Point(1, 0, 2);
        Vector rayDirection = new Vector(0, 0, -1);
        Ray ray = new Ray(rayOrigin, rayDirection);
        double maxDistance = 10.0;

        List<Intersectable.GeoPoint> intersections = tube1.findGeoIntersectionsHelper(ray, maxDistance);
        assertNotNull(intersections, "Expected intersection points but found null");
        assertEquals(2, intersections.size(), "Expected 2 intersection points");

        Point expectedPoint1 = new Point(1, 0, 1);
        Point expectedPoint2 = new Point(1, 0, -1);

        assertEquals(expectedPoint1, intersections.get(0).point, "Intersection point 1 does not match");
        assertEquals(expectedPoint2, intersections.get(1).point, "Intersection point 2 does not match");
    }

    @Test
    public void testIntersectionsHelper2() {


        Point rayOrigin = new Point(1, 0, 2);
        Vector rayDirection = new Vector(0, 0, -1);
        Ray ray = new Ray(rayOrigin, rayDirection);
        double maxDistance = 1.0;

        List<Intersectable.GeoPoint> intersections = tube1.findGeoIntersectionsHelper(ray, maxDistance);
        assertNotNull(intersections, "Expected intersection points but found null");
        assertEquals(1, intersections.size(), "Expected 1 intersection point");

        Point expectedPoint = new Point(1, 0, 1);
        assertEquals(expectedPoint, intersections.get(0).point, "Intersection point does not match");
    }

    @Test
    public void testIntersectionsHelper3() {

        Point rayOrigin = new Point(3, 0, 0);
        Vector rayDirection = new Vector(1, 0, 0);
        Ray ray = new Ray(rayOrigin, rayDirection);
        double maxDistance = 10.0;

        List<Intersectable.GeoPoint> intersections = tube1.findGeoIntersectionsHelper(ray, maxDistance);
        assertNull(intersections, "Expected no intersection points but found some");
    }

    @Test
    public void testIntersectionsHelper4() {


        Point rayOrigin = new Point(1, 0, 1);
        Vector rayDirection = new Vector(0, 1, 0);
        Ray ray = new Ray(rayOrigin, rayDirection);
        double maxDistance = 10.0;

        List<Intersectable.GeoPoint> intersections = tube1.findGeoIntersectionsHelper(ray, maxDistance);
        assertNull(intersections, "Expected no intersection points but found some");
    }











}