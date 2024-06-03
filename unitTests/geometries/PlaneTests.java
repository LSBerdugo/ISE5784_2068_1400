package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Comparator;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Planes
 * This class contains tests for the `Plane` class, specifically for the constructor and the `getNormal` method.
 */
public class PlaneTests {

    /**
     * Runs all tests for the constructor of the `Plane` class.
     */
    @Test
    void testConstructor() {
        testConstructor1();
        testConstructor2();
        testConstructor3();
        testConstructor4();
        testConstructor5();
        testConstructor6();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a valid plane with three non-collinear points.
     * <br>TC01: Valid plane with non-collinear points
     */
    @Test
    void testConstructor1() {
        assertDoesNotThrow(() -> new Plane(new Point(0, 0, 0), new Point(1, 1, 0), new Point(1, 0, 1)),
                "Failed constructing a correct Plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a valid plane with three non-collinear points.
     * <br>TC02: Valid plane with non-collinear points
     */
    @Test
    void testConstructor2() {
        assertDoesNotThrow(() -> new Plane(new Point(1, -1, 1), new Point(-1, 1, -1), new Point(1, 1, 1)),
                "Failed constructing a correct Plane");
    }

    // ============================= Boundary Value Tests =================================

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with collinear points.
     * <br>TC03: Collinear points
     */
    @Test
    void testConstructor3() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 0), new Point(1, 1, 1), new Point(2, 2, 2)),
                "ERROR: The points are collinear and can't form a plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with nearly collinear points.
     * <br>TC04: Points are nearly collinear
     */
    @Test
    void testConstructor4() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 0), new Point(1, 1, 1), new Point(2, 2.0000001, 2)),
                "ERROR: The points are nearly collinear and can't form a plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with two coinciding points.
     * <br>TC05: Two points coincide
     */
    @Test
    void testConstructor5() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(0, 0, 0), new Point(1, 1, 1), new Point(0, 0, 0)),
                "ERROR: Two points coincide and can't form a plane");
    }

    /**
     * Test method for {@link geometries.Plane#Plane(primitives.Point, primitives.Point, primitives.Point)}.
     * Tests the creation of a plane with all coinciding points.
     * <br>TC06: All points coincide
     */
    @Test
    void testConstructor6() {
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1, 1), new Point(1, 1, 1), new Point(1, 1, 1)),
                "ERROR: All the points coincide and can't form a plane");
    }

    /**
     * Runs all tests for the `getNormal` method of the `Plane` class.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     * Tests the normal of the plane at a given point.
     * <br>TC01: Normal vector calculation for points
     */
    @Test
    void testGetNormal1() {
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 1, 3);
        Point p3 = new Point(4, 0, 2);
        Vector expectedNormal = new Vector(2, 5, -1).normalize();
    }

    /**
     * Runs all tests for the {@code findIntersections} method of the {@link Plane} class.
     */

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
    }

    Plane plane = new Plane(new Point(1,1,2),new Vector(-1,1,1));
    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the case where the ray intersects the plane.
     * <br>TC01: Ray intersects the plane
     */
    @Test
    void testFindIntersections1()
    {
        Ray r=new Ray(new Point(2,-1,1),new Vector(0,5,-1));
        final var result = plane.findIntersections(r)
                .stream().toList();
        assertEquals(1,result.size(),"ERROR:Ray intersects the plane");

    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the case where the ray does not intersect the plane.
     * <br>TC02: Ray does not intersect the plane
     */
    @Test
    void testFindIntersections2()
    {
        Ray r=new Ray(new Point(2,-1,1),new Vector(1,3,-100));
        assertNull(plane.findIntersections(r),"ERROR:Ray does not intersects the plane");
    }



    // ============================= Boundary Value Tests =================================
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the case where the ray is parallel to the plane and included in the plane.
     * <br>TC03: Ray is parallel to the plane and included in the plane
     */
    @Test
    void testFindIntersections3(){
        Ray r=new Ray(new Point(0,2,0),new Vector(1,1,0));
        assertNull(plane.findIntersections(r),"ERROR:Ray is parallel to the plane and included in the plane");
    }
/**
 * Test method for {@link Plane#findIntersections(Ray)}.
 * Tests the case where the ray is parallel to the plane.
 * <br>TC04: Ray is parallel to the plane
*/
 @Test
    void testFindIntersections4()
    {
        Ray r=new Ray(new Point(-2,2,2),new Vector(1,1,0));
        assertNull(plane.findIntersections(r),"ERROR:Ray is parallel in the plane");
    }
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the case where the ray is orthogonal to the plane and not in the direction of the plane.
     * <br>TC05: Ray is orthogonal and not in the direction of the plane
     */
    @Test
    void testFindIntersections5(){
        Ray r=new Ray(new Point(-2,2,2),new Vector(-1,1,1));
        assertNull(plane.findIntersections(r),"ERROR:Ray is orthogonal and not in direction of the plane");
    }
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the case where the ray is orthogonal and starts in the plane.
     * <br>TC06: Ray is orthogonal and starts in the plane
     */
    @Test
    void testFindIntersections6(){
        Ray r=new Ray(new Point(2,4,0),new Vector(-1,1,1));
        assertNull(plane.findIntersections(r),"ERROR:Ray is orthogonal and start in the plane ");
    }
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the case where the ray is orthogonal and not in the direction of the plane.
     * <br>TC07: Ray is orthogonal and not in the direction of the plane
     */
    @Test
    void testFindIntersections7(){

            Ray r=new Ray(new Point(2,-1,1),new Vector(-1,1,1));
            assertNull(plane.findIntersections(r),"ERROR:Ray is orthogonal and not in direction of the plane");

    }
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the scenario where the ray is not orthogonal or parallel to the plane and begins at a reference point on the plane.
     * <br>TC08: Ray not orthogonal or parallel and begins in the reference point on the plane
     */
    @Test
    void testFindIntersections8()
    {
        Ray r=new Ray(new Point(1,1,2),new Vector(-4,-2,2));
        assertNull(plane.findIntersections(r),"ERROR:Ray not orthogonal or parallel and begins in the reference point on the plan");

    }
    /**
     * Test method for {@link Plane#findIntersections(Ray)}.
     * Tests the scenario where the ray is not orthogonal or parallel to the plane and begins on the plane itself.
     * <br>TC09: Ray not orthogonal or parallel and begins in the plane
     */
    @Test
    void testFindIntersections9()
    {
        Ray r=new Ray(new Point(2,4,0),new Vector(-4,-2,2));
        assertNull(plane.findIntersections(r),"ERROR:Ray not orthogonal or parallel and begins in the plane");

    }






}
