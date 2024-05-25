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


}