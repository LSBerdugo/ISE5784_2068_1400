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
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class stam {
    private final Scene scene = new Scene("Test scene");
    /**
     * Camera builder for the tests with triangles
     */
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void nightCityReflectionScene() {
        scene.geometries.add(
                //sphere blue
                new Sphere(new Point(100, -180, 170), 25)
                        .setEmission(new Color(0, 114, 250)).
                        setMaterial(new Material().setKd(2d).setKs(1d).setShininess(1500)),
                //the sphere green
                new Sphere(new Point(140, -185, 100), 22)
                        .setEmission(new Color(0, 83, 75)).
                        setMaterial(new Material().setKd(1d).setKt(1d).setKs(0.5).setShininess(500)),
                //beige big sphere
                new Sphere(new Point(150, -100, -100), 60)
                        .setEmission(new Color(158, 140, 93)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                //yellow sphere
                new Sphere(new Point(180, -140, 0), 30)
                        .setEmission(new Color(221, 199, 113)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                //the orange sphere
                new Sphere(new Point(100, -50, -200), 20)
                        .setEmission(new Color(209, 77, 35)).
                        setMaterial(new Material().setKd(3d).setShininess(70)),
                //the marron sphere
                new Sphere(new Point(50, -20, -400), 25)
                        .setEmission(new Color(148, 85, 44)).
                        setMaterial(new Material().setKd(3d).setShininess(70)),
                //the little sphere
                new Sphere(new Point(3, -15, -600), 12)
                        .setEmission(new Color(158, 120, 57)).
                        setMaterial(new Material().setKd(2d).setKr(0.1d).setShininess(70)),

                new Tube(new Ray(new Point(450, -40, -300), new Vector(1, -0.2, 0)), 0.5).
                        setEmission(new Color(255, 255, 255)),
//the big sphere
                new Sphere(new Point(-300, -200, -500), 330)
                        .setEmission(new Color(237, 50, 2)).
                        setMaterial(new Material().setKr(0.5d).setKd(1d).setShininess(70)),
                // Diamond shape using two triangles
                new Triangle(
                        new Point(110, -140, 500),  // Vertex 1 (bottom point)
                        new Point(105, -130, 500),  // Vertex 2 (left point)
                        new Point(115, -130, 500)   // Vertex 3 (right point)
                ).setEmission(new Color(66, 72, 245)) // Dark blue color
                        .setMaterial(new Material().setKd(2d).setShininess(70)),
                new Triangle(
                        new Point(110, -120, 500),  // Vertex 1 (top point)
                        new Point(105, -130, 500),  // Vertex 2 (left point)
                        new Point(115, -130, 500)   // Vertex 3 (right point)
                ).setEmission(new Color(66, 72, 245)) // Dark blue color
                        .setMaterial(new Material().setKd(2d).setShininess(70))
//

        );

        //the PointLight of the blue sphere in the edge
        List<LightSource> light = new LinkedList<>();
        light.add(

                new PointLight(new Color(78, 171, 255),
                        new Point(67, -140, 200))
                        .setKl(0.1)
                        .setKq(0.001)
                        .setKc(0.01)

        );
        //the SpotLight of the blue sphere between the edge and the middle(the light cycle)
        light.add(

                new SpotLight(new Color(0, 97, 255),
                        new Point(70, -180, 200), new Vector(0.2, 0, -0.2))
                        .setKl(0.1)
                        .setKq(0.001)
                        .setKc(0.01)

        );

        //the PointLight  of the yellow sphere the point in the on.
        light.add(

                new PointLight(new Color(168, 80, 50),  // White light
                        new Point(180, -20, 95)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.0001)
                        .setKc(0.01)

        );
        //the PointLight  of the yellow sphere the point in the bottom.
        light.add(

                new PointLight(new Color(168, 115, 50),  // White light
                        new Point(30, -250, 10)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.000001)
                        .setKc(0.5)

        );
        //the PointLight  of the yellow sphere the point in the bottom.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(150, -80, 20), new Vector(0, -20, -120)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.000001)
                        .setKc(2)

        );
//the spotlight in the orange sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(100, 0, -100), new Vector(10, -50, -100)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );
        //the spotlight in the marron sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(0, 0, -200), new Vector(50, -30, -200)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );
        //the spotlight in the little sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(0, 0, -600), new Vector(3, -15, 0)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );
        //the spotlight in the little sphere.
        light.add(

                new SpotLight(new Color(177, 126, 100),  // White light
                        new Point(0, 0, -600), new Vector(3, -15, 0)) // Position of the point light
                        .setKl(0.00001)
                        .setKq(0.000001)
                        .setKc(0.1)

        );


        scene.setLights(light);


        scene.setAmbientLight(new AmbientLight(new Color(40, 0, 101), 0.2)).setBackground(new Color(16, 3, 27));


        Camera cam3 = cameraBuilder.setLocation(new Point(100, -100, 1500))
                .setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("space Picture", 600, 600))
                .setApertureNumberOfPoints(9).
                setAperture(15).
                setDepthOfField(400).
                setDofON(true)
                .build();

        // Create a sphere

//        // Create a disk using a polygon
//        List<Point> vertices = new ArrayList<>();
//        int numVertices = 100; // Number of vertices for the disk
//        double radius = 60;
//        double thickness = 10;
//        Point center = new Point(180, -140, -100);
//
//        for (int i = 0; i < numVertices; i++) {
//            double angle = 2 * Math.PI * i / numVertices;
//            double x = 180 + radius * Math.cos(angle);
//            double y = -140;
//            double z = -100 + thickness * 2 + radius * Math.sin(angle);
//            vertices.add(new Point(x, y, z));
//
//        }
//        Geometry disk = new Polygon(vertices.toArray(new Point[0]))
//                .setEmission(new Color(165, 143, 66)).setMaterial(new Material().setKt(0.5d)); // Example color for the disk
//
//        // Create a collection of geometries and add the objects
//        scene.geometries.add(disk);

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


    @Test
    public void DepthOfFieldTest() {
        // Create camera
        Scene scene = new Scene("Depth of Field Test Scene");
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));
        Camera camera = Camera.getBuilder()
                .setLocation(new Point(0, 0, 500))
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVpDistance(1000)
                .setVpSize(200, 200)
                .setRayTracer(new SimpleRayTracer(scene))
                .setImageWriter(new ImageWriter("depth_of_field_test", 500, 500))
                .setAperture(2) // Large aperture for noticeable DoF effect
                .setDepthOfField(500)
                .setDofON(false)
                .build();

        // Create scene


        // Add geometries to the scene
        scene.geometries.add(
                new Sphere(new Point(0, 0, -600), 10).setEmission(new Color(java.awt.Color.RED)), // At focal distance
                new Sphere(new Point(0, -20, 0), 10).setEmission(new Color(BLUE)), // Behind focal distance
                new Sphere(new Point(0, 50, -2000), 10).setEmission(new Color(java.awt.Color.RED)) // In front of focal distance
        );

        // Add a light source
        scene.lights.add(new SpotLight(new Color(java.awt.Color.WHITE), new Point(10, 10, -5), new Vector(-1, -1, -1)));

        // Render the image
        camera.renderImage();
        camera.writeToImage();
    }
}