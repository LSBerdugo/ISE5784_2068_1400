package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
}