package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Testing Polygons
 * @author Dan
 */
public class PolygonTests {
   /**
    * Delta value for accuracy when comparing the numbers of type 'double' in
    * assertEquals
    */
   private final double DELTA = 0.000001;

   /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
   @Test
   public void testConstructor() {
      // ============ Equivalence Partitions Tests ==============

      // TC01: Correct concave quadrangular with vertices in correct order
      assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                                           new Point(1, 0, 0),
                                           new Point(0, 1, 0),
                                           new Point(-1, 1, 1)),
                         "Failed constructing a correct polygon");

      // TC02: Wrong vertices order
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                   "Constructed a polygon with wrong order of vertices");

      // TC03: Not in the same plane
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                   "Constructed a polygon with vertices that are not in the same plane");

      // TC04: Concave quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0.5, 0.25, 0.5)), //
                   "Constructed a concave polygon");

      // =============== Boundary Values Tests ==================

      // TC10: Vertex on a side of a quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0, 0.5, 0.5)),
                   "Constructed a polygon with vertix on a side");

      // TC11: Last point = first point
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                   "Constructed a polygon with vertice on a side");

      // TC12: Co-located points
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                   "Constructed a polygon with vertice on a side");

   }

   /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
   @Test
   public void testGetNormal() {
      // ============ Equivalence Partitions Tests ==============
      // TC01: There is a simple single test here - using a quad
      Point[] pts =
         { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
      Polygon pol = new Polygon(pts);
      // ensure there are no exceptions
      assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
      // generate the test result
      Vector result = pol.getNormal(new Point(0, 0, 1));
      // ensure |result| = 1
      assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
      // ensure the result is orthogonal to all the edges
      for (int i = 0; i < 3; ++i)
         assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                      "Polygon's normal is not orthogonal to one of the edges");
   }

   @Test
   void testFindIntersections(){
      testFindIntersections1();
        testFindIntersections2();
        testFindIntersections3();
        testFindIntersections4();
        testFindIntersections5();
        testFindIntersections6();
   }
   Polygon polygon =new Polygon(new Point(6,0,4),new Point(0,0,6),new Point(-6,0,4),new Point(-6,0,0),new Point(6,0,0));
   // ============================ Equivalence Partitions Tests ================================

   @Test
   void testFindIntersections1()
   {
      Ray r=new Ray(new Point(0,-3,2),new Vector(0,6,2));
      final var result = polygon.findIntersections(r)
              .stream().toList();
      assertEquals(1,result.size(),"ERROR:Ray intersects the plane");
   }

   @Test
   void testFindIntersections2()
   {
      Ray r=new Ray(new Point(0,3,4),new Vector(0,-3,-5));
     assertNull(polygon.findIntersections(r),"ERROR:Ray does not intersect the plane");
   }

    @Test
    void testFindIntersections3()
    {
       Ray r=new Ray(new Point(0,-5,0),new Vector(-7,5,5));
       assertNull(polygon.findIntersections(r),"ERROR:Ray does not intersect the plane");
    }

   // ============================= Boundary Value Tests =================================

   @Test
   void testFindIntersections4()
   {
      Ray r=new Ray(new Point(-6,-2,0),new Vector(0,2,2));
      assertNull(polygon.findIntersections(r),"ERROR:Ray does not intersect the plane");
   }

   @Test
   void testFindIntersections5()
   {
      Ray r=new Ray(new Point(-7,-2,5),new Vector(1,2,-5));
      assertNull(polygon.findIntersections(r),"ERROR:Ray does not intersect the plane");
   }

   @Test
   void testFindIntersections6()
   {
      Ray r=new Ray(new Point(-7,-2,5),new Vector(1,2,-7));
      assertNull(polygon.findIntersections(r),"ERROR:Ray does not intersect the plane");
   }


}
