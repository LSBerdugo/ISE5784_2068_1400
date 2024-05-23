package primitives;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTests {
    /**
     * Executes all the add tests.
     */
    @org.junit.jupiter.api.Test
    void testAdd()
    {
        testAdd2();
        testAdd1();
        testAdd4();
        testAdd3();
    }

    // =============== Boundary Values Tests ==================

    @org.junit.jupiter.api.Test
    void testAdd4() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        assertThrows(IllegalArgumentException.class, () -> v1.add(v1Opposite), "ERROR: Vector + -itself does not throw an exception");
    }

    // ============ Equivalence Partitions Tests ==============

    @org.junit.jupiter.api.Test
    void testAdd1() {
        Vector v1 = new Vector(5, 5, 5);
        Vector v2 = new Vector(-1, -1, -1);
        assertEquals(new Vector(4, 4, 4), v1.add(v2), "ERROR: Vector does not work correctly");
    }

    @org.junit.jupiter.api.Test
    void testAdd2() {
        Vector v1         = new Vector(1, 2, 3);
        Vector v1Opposite = new Vector(-1, -2, -3);
        Vector v2         = new Vector(-2, -4, -6);
        assertEquals(v1Opposite, v1.add(v2), "ERROR: Vector + Vector does not work correctly");
    }
    /**
     * Test adding two vectors in the positive direction.
     * V1 = (5, 5, 5)
     * V2 = (1, 1, 1)
     * Expected: V1 + V2 = (6, 6, 6)
     */
    @org.junit.jupiter.api.Test
    void testAdd3() {
        Vector v1 = new Vector(5, 5, 5);
        Vector v2 = new Vector(1, 1, 1);
        assertEquals(new Vector(6, 6, 6), v1.add(v2), "ERROR: Vector does not work correctly");
    }


    @org.junit.jupiter.api.Test
    void testScale()
    {
        testScale1();
        testScale2();
        testScale3();
    }
    // =============== Boundary Values Tests ==================
    @org.junit.jupiter.api.Test
    void testScale1()
    {
        Vector v1         = new Vector(1, 2, 3);
        assertThrows(IllegalArgumentException.class, () -> v1.scale(0), "ERROR: Vector * scale does not throw an exception");
    }
    // ============ Equivalence Partitions Tests ==============
    @org.junit.jupiter.api.Test
    void testScale2()
    {
        Vector v1  = new Vector(1, 2, 3);
        assertEquals(new Vector(3,6,9), v1.scale(3), "ERROR: Vector * scale does not throw an exception");
    }
    @org.junit.jupiter.api.Test
    void testScale3()
    {
        Vector v1  = new Vector(1, 2, 3);
        assertEquals(new Vector(-3,-6,-9), v1.scale(-3), "ERROR: Vector * scale does not throw an exception");
    }

    @org.junit.jupiter.api.Test
    void testDotProduct()
    {
        testDotProduct1();
        testDotProduct2();
    }
    // =============== Boundary Values Tests ==================
    @org.junit.jupiter.api.Test
    void testDotProduct1()
    {
        Vector v1         = new Vector(1, 2, 3);
        Vector v3         = new Vector(0, 3, -2);
        assertEquals(0, v1.dotProduct(v3), "ERROR: dotProduct() for orthogonal vectors is not zero");
    }
    // ============ Equivalence Partitions Tests ==============
    @org.junit.jupiter.api.Test
    void testDotProduct2()
    {
        Vector v1         = new Vector(1, 2, 3);
        Vector v2         = new Vector(-2, -4, -6);
        assertEquals(0, v1.dotProduct(v2) + 28, "ERROR: dotProduct() wrong value");
    }

    @org.junit.jupiter.api.Test
    void testCrossProduct()
    {
    }
    // =============== Boundary Values Tests ==================
    @org.junit.jupiter.api.Test
    void testCrossProduct1()
    {
        Vector v1         = new Vector(1, 2, 3);
        Vector v2         = new Vector(-2, -4, -6);
        assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2),"ERROR: crossProduct() for parallel vectors does not throw an exception");
    }
    @org.junit.jupiter.api.Test
    void testCrossProduct2()
    {
        Vector v1         = new Vector(1, 2, 3);
        Vector v2        = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);
        assertEquals(true,isZero(vr.length()-v1.length()*v2.length()),"ERROR: crossProduct() wrong result length");
    }
    @org.junit.jupiter.api.Test
    void testCrossProduct3()
    {
        Vector v1         = new Vector(1, 2, 3);
        Vector v3         = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);
        assertEquals(true,isZero(vr.dotProduct(v1)) || isZero(vr.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
    }


    @org.junit.jupiter.api.Test
    void testLengthSquared()
    {
        testLengthSquared1();
    }
    // ============ Equivalence Partitions Tests ==============
    @org.junit.jupiter.api.Test
    void testLengthSquared1()
    {
        Vector v4         = new Vector(1, 2, 2);
        assertEquals(true,isZero(v4.lengthSquared() - 9),"ERROR: lengthSquared() wrong value");    }
    @org.junit.jupiter.api.Test
    void testLength() {
    }
    // ============ Equivalence Partitions Tests ==============
    @org.junit.jupiter.api.Test
    void testLength1()
    {
        Vector v4         = new Vector(1, 2, 2);
        assertEquals(true,isZero(v4.length() - 3),"ERROR: length() wrong value");
    }
    @org.junit.jupiter.api.Test
    void testNormalize()
    {
        testNormalize1();
        testNormalize2();
        testNormalize3();

    }
    // ============ Equivalence Partitions Tests ==============
    @org.junit.jupiter.api.Test
    void testNormalize1()
    {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(true,isZero(u.length() - 1),"ERROR: the normalized vector is not a unit vector");
    }
    @org.junit.jupiter.api.Test
    void testNormalize2()
    {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertEquals(true,v.dotProduct(u) > 0,"ERROR: the normalized vector is opposite to the original one");
    }
    @org.junit.jupiter.api.Test
    void testNormalize3()
    {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalize();
        assertThrows(IllegalArgumentException.class,()->v.crossProduct(u),"ERROR: the normalized vector is not parallel to the original one");
    }

}