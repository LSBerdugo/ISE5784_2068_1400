/**
 *
 */
package lighting;

import static java.awt.Color.*;

import geometries.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 *
 * @author dzilb
 */
public class ReflectionRefractionTests {
    /**
     * Scene for the tests
     */
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        scene.geometries.add(
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add(
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                        .setKl(0.0004).setKq(0.0000006));

        Camera cam = cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
                .build();
        cam.renderImage();
        cam.writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        scene.geometries.add(
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20))
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000))
                        .setEmission(new Color(20, 20, 20))
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
                .setKl(0.00001).setKq(0.000005));

        Camera cam2 = cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
                .setVpSize(2500, 2500)
                .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
                .build();
        cam2.renderImage();
        cam2.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        scene.geometries.add(
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
        scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
        scene.lights.add(
                new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                        .setKl(4E-5).setKq(2E-7));

        Camera cam3 = cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
                .build();
        cam3.renderImage();
        cam3.writeToImage();
    }

    //PR07
    @Test
    public void nightCityReflectionScene() {
        scene.geometries.add(
                //sphere blue
                new Sphere(new Point(0, -85, 30), 25)
                        .setEmission(new Color(0, 114, 250)).
                        setMaterial(new Material().setKd(2d).setKs(1d).setShininess(1500)),
                //the sphere green
                new Sphere(new Point(40, -85, -20), 22)
                        .setEmission(new Color(0, 83, 75)).
                        setMaterial(new Material().setKd(1d).setKt(1d).setKs(0.5).setShininess(500)),
                new Sphere(new Point(50, 0, -300), 60)
                        .setEmission(new Color(158, 140, 93)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                new Sphere(new Point(80, -40, -100), 30)
                        .setEmission(new Color(221, 199, 113)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                new Sphere(new Point(0, 50, -400), 20)
                        .setEmission(new Color(209, 77, 35)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                new Sphere(new Point(-50, 80, -300), 25)
                        .setEmission(new Color(148, 85, 44)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                new Sphere(new Point(-105, 85, -500), 12)
                        .setEmission(new Color(158, 120, 57)).
                        setMaterial(new Material().setKd(1d).setKt(0.3d).setShininess(70)),

                new Tube(new Ray(new Point(350,60,-300),new Vector(1,-0.2,0)),0.5).
                        setEmission(new Color(255,255,255)),

                new Sphere(new Point(-400, -100, -500), 330)
                        .setEmission(new Color(237, 50, 2)).
                        setMaterial(new Material().setKr(0.5d).setKd(1d).setShininess(70)),
                new Triangle(new Point(10,0,-20),new Point(110,0,-20),new Point(50,0,-50)).
                        setEmission(new Color(0,0,0)),
                new Triangle(new Point(-30,-63,-30),new Point(60,-63,-30),new Point(10,-60,-100)).
                        setEmission(new Color(40,0,0)),
               new Triangle(new Point(0, 70, -250),new Point(100,70,-250),new Point(50,40,-250)).
                       setEmission(new Color (173,216,330))
        );

//scene.lights.add(
//                //spotlight in the sphere maron the SpotLight blue(on1).
//                new PointLight(new Color(219, 205, 124), new Point(50, 100, -300))
//                        .setKl(0.01)
//                        .setKq(0.0001));
//        scene.lights.add(
//                //spotlight in the sphere blue.
//                new SpotLight(new Color(0, 0, 66), new Point(0, -199, 30),new Vector(0,1,0))
//                        .setKl(0.00001)
//                        .setKq(0.000001));
//        scene.lights.add(
//                //spotlight in the sphere blue the SpotLight blue.
//                new SpotLight(new Color(0, 0, 29), new Point(10, -200, -40), new Vector(-10, 115, 20))
//                        .setKl(0.0001)
//                        .setKq(0.00001));

        scene.lights.add(
                //spotlight in the sphere blue the SpotLight blue.
                new SpotLight(new Color(208, 91, 32), new Point(200, -700, 50),new Vector(-1,1,0))
                        .setKl(0.1)
                        .setKq(0.0001));
        scene.lights.add(
                //spotlight in the sphere blue the SpotLight blue.
                new PointLight(new Color(232, 217, 176), new Point(50, -700, -300))
                        .setKl(0.0001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere blue the SpotLight blue.
                new SpotLight(new Color(45, 0, 255), new Point(0, -20, 20), new Vector(0, -65, 0))
                        .setKl(0.0001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere green the SpotLight blue.
                new SpotLight(new Color(69, 149, 252), new Point(0, -45, 0), new Vector(2, -100, -10))
                        .setKl(0.01)
                        .setKq(0.0001));


        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(on1).
                new PointLight(new Color(219, 205, 124), new Point(50, 100, -300))
                        .setKl(0.01)
                        .setKq(0.0001));


        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(on2).
                new PointLight(new Color(147, 119, 72), new Point(50, 300, -300))
                        .setKl(0.01)
                        .setKq(0.0001));
        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(on2).
                new PointLight(new Color(179, 146, 11), new Point(50, 400, -300))
                        .setKl(0.001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(on2).
                new PointLight(new Color(187, 97, 60), new Point(50, 500, -300))
                        .setKl(0.001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(on2).
                new PointLight(new Color(212, 58, 11), new Point(50, -500, -300))
                        .setKl(0.001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(on2).
                new PointLight(new Color(237, 227, 168), new Point(50, -700, -300))
                        .setKl(0.001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere maron the SpotLight blue(near in 8t8h7e left).
                new SpotLight(new Color(42, 20, 0), new Point(-100, 0, -200), new Vector(1, 0, 0))
                        .setKl(0.00001)
                        .setKq(0.000001));
        scene.lights.add(
//hhhhh#
new SpotLight(new Color(200, 173, 127), new Point(200, -200, -50), new Vector(-2, 0.25, -0.1))
                        .setKl(0.01)
                        .setKq(0.0001));
        scene.lights.add(
                //spotlight in the sphere yellow the SpotLight blue(on2).
                new PointLight(new Color(0, 20, 0), new Point(0, -40, -20))
                        .setKl(0.1)
                        .setKq(0.0001));
        scene.lights.add(
                //spotlight in the sphere yellow the SpotLight blue(on2).
                new PointLight(new Color(86, 70, 20), new Point(-20, -60, -20))
                        .setKl(1)
                        .setKq(0.01));
        scene.lights.add(
                //spotlight in the sphere yellow the SpotLight blue(on2).
                new PointLight(new Color(223, 207, 68), new Point(-200, -10, -500))
                        .setKl(0.001)
                        .setKq(0.00001));
        scene.lights.add(
                //spotlight in the sphere yellow the SpotLight blue(on2).
                new SpotLight(new Color(255, 255, 0), new Point(-200, -200, 0),new Vector(0,-1,0))
                        .setKl(0.001)
                        .setKq(0.00001));


        scene.setAmbientLight(new AmbientLight(new Color(40,0,101), 0.2)).setBackground(new Color(16, 3, 27));


        Camera cam3 = cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
                .setVpSize(200, 200)
                .setImageWriter(new ImageWriter("Image2", 600, 600))
                .build();

        // Create a sphere

        // Create a disk using a polygon
        List<Point> vertices = new ArrayList<>();
        int numVertices = 100; // Number of vertices for the disk
        double radius = 60;
        double thickness = 10;
        Point center = new Point(80, -40, -100);

        for (int i = 0; i < numVertices; i++) {
            double angle = 2 * Math.PI * i / numVertices;
            double x = 80+ radius * Math.cos(angle);
            double y = -40;
            double z = -100+thickness*2 + radius * Math.sin(angle);
            vertices.add(new Point(x, y, z));

        }
        Geometry disk = new Polygon(vertices.toArray(new Point[0]))
                .setEmission(new Color(165, 143, 66)).setMaterial(new Material().setKt(0.5d)); // Example color for the disk

        // Create a collection of geometries and add the objects
       scene. geometries.add(disk);

        // Render the scene (Assuming you have a render method in your Geometries class)

//        Random rand=new Random();
//        int sign1=1;
//        int sign2=1;
//        float starSize= 1;
//        for (int i = 0; i < 600; i++) {
//            float x = rand.nextFloat() * cam3.getWidth()*sign1;
//            float y = rand.nextFloat() * cam3.getHeight()*sign2;
//            float z = rand.nextFloat() * 20 *sign1-1300;
//            Triangle star = new Triangle(new Vector(x, y, z), new Vector(x + starSize, y, z), new Vector(x + starSize / 2, y + starSize, z));
//            star.setEmission(new Color(255,255,255));
//            scene.geometries.add(star);
//            if(i==300)
//                sign2*=-1;
//            sign1*=-1;
//        }
        cam3.renderImage();
        cam3.writeToImage();

}

    }
