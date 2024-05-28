package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
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
    void testFindIntersections1() {
        throw null;
    }

    //one point (change ray)
    @Test
    void testFindIntersections2() {
        throw null;
    }

    //two point (change ray)
    @Test
    void testFindIntersections3() {
        throw null;
    }


    //zero point (change ray)- if the ray is the other side, would have intersections
    @Test
    void testFindIntersections4() {
        throw null;
    }

    // ============================= Boundary Value Tests =================================
    void testFindIntersections5(){
        throw null;
    }

    void testFindIntersections6(){
        throw null;
    }

    void testFindIntersections7(){
        throw null;
    }
    void testFindIntersections8(){
        throw null;
    }
    void testFindIntersections9(){
        throw null;
    }
    void testFindIntersections10(){
        throw null;
    }
    void testFindIntersections11(){
        throw null;
    }
    void testFindIntersections12(){
        throw null;
    }
    void testFindIntersections13(){
        throw null;
    }
    void testFindIntersections14(){
        throw null;
    }
    void testFindIntersections15(){
        throw null;
    }
    void testFindIntersections16(){
        throw null;
    }
    void testFindIntersections17(){
        throw null;
    }

}