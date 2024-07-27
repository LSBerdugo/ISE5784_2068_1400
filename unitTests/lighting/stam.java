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
                        setMaterial(new Material().setKd(0.9d).setShininess(100)),
                new Sphere(new Point(100, -50, -200), 20)
                        .setEmission(new Color(209, 77, 35)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                new Sphere(new Point(50, -20, -400), 25)
                        .setEmission(new Color(148, 85, 44)).
                        setMaterial(new Material().setKd(1d).setShininess(70)),
                new Sphere(new Point(5, -15, -600), 12)
                        .setEmission(new Color(158, 120, 57)).
                        setMaterial(new Material().setKd(1d).setKt(0.3d).setShininess(70)),

                new Tube(new Ray(new Point(450, -40, -300), new Vector(1, -0.2, 0)), 0.5).
                        setEmission(new Color(255, 255, 255)),

                new Sphere(new Point(-300, -200, -500), 330)
                        .setEmission(new Color(237, 50, 2)).
                        setMaterial(new Material().setKr(0.5d).setKd(1d).setShininess(70)),


                new Triangle(
                        new Point(20, -70, 0),      // Vertex 1 (top point)
                        new Point(15, -80, 0),      // Vertex 2 (left point)
                        new Point(25, -80, 0)        // Vertex 3 (right point)
                ).setEmission(new Color(0, 0, 50)) // Dark blue color
                        .setMaterial(new Material().setKd(1d).setShininess(70)),

// Bottom triangle
         new Triangle(
                new Point(20, -90, 0),     // Vertex 1 (bottom point)
                new Point(15, -80, 0),      // Vertex 2 (left point)
                new Point(25, -80, 0)        // Vertex 3 (right point)
        ).setEmission(new Color(0, 0, 50)) // Dark blue color
                .setMaterial(new Material().setKd(1d).setShininess(70)),

// Left triangle
         new Triangle(
                new Point(20, -80, 5),       // Vertex 1 (right point)
                new Point(15, -80, 0),      // Vertex 2 (left point)
                new Point(20, -70, 0)       // Vertex 3 (top point)
        ).setEmission(new Color(0, 0, 50)) // Dark blue color
                .setMaterial(new Material().setKd(1d).setShininess(70)),

// Right triangle
      new Triangle(
                new Point(20, -80, -5),      // Vertex 1 (left point)
                new Point(25, -80, 0),       // Vertex 2 (right point)
                new Point(20, -70, 0)       // Vertex 3 (top point)
        ).setEmission(new Color(0, 0, 50)) // Dark blue color
                .setMaterial(new Material().setKd(1d).setShininess(70))
        );

        //the PointLight of the blue sphere in the edge
        List<LightSource> light=new LinkedList<>();
        light.add(

                new PointLight(new Color(78,171,255),
                        new Point(67,-140,200))
                        .setKl(0.1)
                        .setKq(0.001)
                        .setKc(0.01)

        );
        //the SpotLight of the blue sphere between the edge and the middle(the light cycle)
        light.add(

                new SpotLight(new Color(0,97,255),
                        new Point(70,-180,200),new Vector(0.2,0,-0.2))
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
                        new Point(150, -80, 20),new Vector(0,-20,-120)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.000001)
                        .setKc(3)

        );
        light.add(

                new PointLight(new Color(177, 126, 100),  // White light
                        new Point(150, -50, 0)) // Position of the point light
                        .setKl(0.01)
                        .setKq(0.000001)
                        .setKc(0.1)

        );








        scene.setLights(light);


        scene.setAmbientLight(new AmbientLight(new Color(40, 0, 101), 0.2)).setBackground(new Color(16, 3, 27));


        Camera cam3 = cameraBuilder.setLocation(new Point(100, -100, 1500))
                .setVpDistance(1000)
                .setVpSize(150, 150)
                .setImageWriter(new ImageWriter("space_Picture", 600, 600))
                .setApertureNumberOfPoints(9).
                setAperture(15).
                setDepthOfField(400).
                setDofON(false)
                .build();
        cam3.renderImage();
        cam3.writeToImage();

    }
    }