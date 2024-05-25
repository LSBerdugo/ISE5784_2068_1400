package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Cylinders
 * This class contains tests for the `Cylinder` class, specifically for the `getNormal` method.
 */
public class CylinderTests {

    Point p = new Point(0, 0, 2);
    Point p0 = new Point(0, 0, 0);
    Vector v = new Vector(0, 0, 1);
    Ray r = new Ray(p0, v);
    Cylinder c = new Cylinder(2, r, 1);

    /**
     * Runs all tests for the `getNormal` method.
     */
    @Test
    void testGetNormal() {
        testGetNormal1();
        testGetNormal2();
        testGetNormal3();
        testGetNormal4();
        testGetNormal5();
    }

    // ============================ Equivalence Partitions Tests ================================

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     * Tests the normal of a point on the curved surface of the cylinder.
     * <br>TC01: Point on the curved surface of the cylinder
     */
    @Test
    void testGetNormal1() {
        assertEquals(new Vector(0, 1, 0), c.getNormal(new Point(0, 1, 1)),
                "ERROR: Failed to compute normal of point on the curved surface of the cylinder.");
    }

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     * Tests the normal of a point on the bottom base of the cylinder.
     * <br>TC02: Point on the bottom base of the cylinder
     */
    @Test
    void testGetNormal2() {
        assertEquals(v, c.getNormal(new Point(0, 0.5, 0)),
                "ERROR: Failed to compute normal of point on the bottom base of the cylinder.");
    }

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     * Tests the normal of a point on the top base of the cylinder.
     * <br>TC03: Point on the top base of the cylinder
     */
    @Test
    void testGetNormal3() {
        assertEquals(v, c.getNormal(new Point(0, 0.5, 2)),
                "ERROR: Failed to compute normal of point on the top base of the cylinder.");
    }

    // ============================= Boundary Value Tests =================================

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     * Tests the normal of the center point of the bottom base of the cylinder.
     * <br>TC04: Center point of the bottom base of the cylinder
     */
    @Test
    void testGetNormal4() {
        assertEquals(v, c.getNormal(new Point(0, 0, 0)),
                "ERROR: Failed to compute normal of the center point of the bottom base of the cylinder.");
    }

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     * Tests the normal of the center point of the top base of the cylinder.
     * <br>TC05: Center point of the top base of the cylinder
     */
    @Test
    void testGetNormal5() {
        assertEquals(v, c.getNormal(new Point(0, 0, 2)),
                "ERROR: Failed to compute normal of the center point of the top base of the cylinder.");
    }
}
