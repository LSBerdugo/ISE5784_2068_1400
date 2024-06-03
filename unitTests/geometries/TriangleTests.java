package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Triangles
 * This class contains tests for the `Triangle` class, specifically for the `getNormal` method.
 */
public class TriangleTests {

    /**
     * Runs all tests for the `getNormal` method.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     * Tests the normal calculation for a point in the triangle.
     * <br>TC01: Normal calculation for point in triangle
     */
    @Test
    void testGetNormal1() {
        Point p1 = new Point(1, 1, 1);
        Point p2 = new Point(2, 3, 1);
        Point p3 = new Point(4, 2, 2);
        Triangle t = new Triangle(p1, p2, p3);
        Point p = new Point(2, 3, 1);
        assertEquals(new Vector(2, -1, -5).normalize(), t.getNormal(p),
                "ERROR: Failed constructing a correct Triangle");
    }
    /**
     * Test method to run all intersection tests.
     * Invokes individual test methods for various intersection scenarios.
     */
    @Test
    void testFindIntersections() {
        testFindIntersections1();
        testFindIntersections2();
        testFindIntersections3();
        testFindIntersections4();
        testFindIntersections5();
        testFindIntersections6();
    }

    final Triangle triangle=new Triangle(new Point(0,0,3),new Point(0,-5,0),new Point(0,5,0));

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Tests the scenario where the ray intersects the triangle.
     * <br>TC01: Ray intersects the triangle
     */
    @Test
    void testFindIntersections1() {

        Ray r=new Ray(new Point(-4,0,2),new Vector(8,0,0));
        final var result = triangle.findIntersections(r).stream().toList();
        assertEquals(1,result.size(),"ERROR:Ray intersects the triangle");
    }

    /**
     * Tests the scenario where the ray does not intersect the triangle.
     * <br>TC02: Ray does not intersect the triangle
     */
    @Test
      void testFindIntersections2() {
        Ray r=new Ray(new Point(2,0,1),new Vector(-2,0,3));

        assertNull( triangle.findIntersections(r),"ERROR:Ray does not intersects the triangle");
    }
    /**
     * Tests another scenario where the ray does not intersect the triangle.
     * <br>TC03: Ray does not intersect the triangle
     */
    @Test
    void testFindIntersections3(){
        Ray r=new Ray(new Point(2,0,1),new Vector(-2,0,-2));
        assertNull( triangle.findIntersections(r),"ERROR:Ray does not intersects the triangle");
    }




    // ============================= Boundary Value Tests =================================
    /**
     * Tests a boundary scenario where the ray does not intersect the triangle.
     * <br>TC04: Ray does not intersect the triangle
     */
    @Test
    void testFindIntersections4(){
        Ray r=new Ray(new Point(3,-3,0),new Vector(-3,-2,6));
        assertNull( triangle.findIntersections(r),"ERROR:Ray does not intersects the triangle");
    }
    /**
     * Tests another boundary scenario where the ray does not intersect the triangle.
     * <br>TC05: Ray does not intersect the triangle
     */
    @Test
    void testFindIntersections5(){

        Ray r=new Ray(new Point(3,-3,0),new Vector(-3,8,0));
        assertNull( triangle.findIntersections(r),"ERROR:Ray does not intersects the triangle");

    }

    /**
     * Tests a boundary scenario where the ray does not intersect the triangle.
     * <br>TC06: Ray does not intersect the triangle
     */
    @Test
    void testFindIntersections6(){
        Ray r=new Ray(new Point(0,-1,0),new Vector(-3,2,0));
        assertNull( triangle.findIntersections(r),"ERROR:Ray does not intersects the triangle");
    }
}
