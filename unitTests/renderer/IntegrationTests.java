package renderer;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IntegrationTests
{
    @Test
    void cameraRaySphereIntegration()
    {
        cameraRaySphereIntegration1();
        cameraRaySphereIntegration2();
        cameraRaySphereIntegration3();
        cameraRaySphereIntegration4();
        cameraRaySphereIntegration5();
    }
    Camera camera1 = Camera.getBuilder().
            setLocation(Point.ZERO).
            setVpSize(3,3).
            setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).
            setVpDistance(1d).build();
    Camera camera2 = Camera.getBuilder().
            setLocation(new Point(0,0,0.5)).
            setVpSize(3,3).
            setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).
            setVpDistance(1d).build();


    @Test
    void cameraRaySphereIntegration1()
    {
        Sphere sphere=new Sphere(new Point(0,0,-3),1);
        assertCountIntersections(camera1,sphere,2);
    }
    @Test
    void cameraRaySphereIntegration2()
    {
        Sphere sphere=new Sphere(new Point(0,0,-2.5),2.5d);
        assertCountIntersections(camera2,sphere,18);
    }
    @Test
    void cameraRaySphereIntegration3()
    {
        Sphere sphere=new Sphere(new Point(0,0,-2),2d);
        assertCountIntersections(camera2,sphere,10);
    }
    @Test
    void cameraRaySphereIntegration4()
    {
        Sphere sphere=new Sphere(new Point(0,0,-1),4d);
        assertCountIntersections(camera2,sphere,9);
    }
    @Test
    void cameraRaySphereIntegration5()
    {
        Sphere sphere=new Sphere(new Point(0,0,1),0.5d);
        assertCountIntersections(camera2,sphere,0);
    }

    @Test
    void cameraRayPlaneIntegration()
    {
        cameraRayPlaneIntegration1();
        cameraRayPlaneIntegration2();
        cameraRayPlaneIntegration3();
    }
    Camera camera3 = Camera.getBuilder().
            setLocation(Point.ZERO).
            setVpSize(3,3).
            setDirection(new Vector(0,0,-1),new Vector(0,-1,0)).
            setVpDistance(1d).build();
    @Test
    void cameraRayPlaneIntegration1()
    {
        Plane plane=new Plane(new Point(0,0,-5),new Vector(0,0,1));
        assertCountIntersections(camera3,plane,9);

    }
    @Test
    void cameraRayPlaneIntegration2()
    {
        Plane plane=new Plane(new Point(0,0,-5),new Vector(0,1,2));
        assertCountIntersections(camera3,plane,9);

    }
    @Test
    void cameraRayPlaneIntegration3()
    {
        Plane plane=new Plane(new Point(0,0,-5),new Vector(0,1,1));
        assertCountIntersections(camera3,plane,6);

    }

    @Test
    void cameraRayTriangleIntegration()
    {

    }
    @Test
    void cameraRayTriangleIntegration1()
    {
        Triangle triangle=new Triangle(new Point(0,1,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertCountIntersections(camera3,triangle,1);
    }
    @Test
    void cameraRayTriangleIntegration2()
    {
        Triangle triangle=new Triangle(new Point(0,20,-2),new Point(1,-1,-2),new Point(-1,-1,-2));
        assertCountIntersections(camera3,triangle,2);
    }

    private void assertCountIntersections(Camera cam, Intersectable geo, int expected)
    {
        int count = 0;

        List<Point> allpoints = null;

        int nX =3;
        int nY =3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRay(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<Point>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }

        }
        assertEquals(expected, count, "Wrong amount of intersections");
    }
}
