package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

import javax.swing.*;

import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class textstam{
    private final Scene scene1 = new Scene("Test scene");
    private Scene scene2 = new Scene("Test scene") //
            .setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
    private final Camera.Builder camera1 =Camera.getBuilder().setLocation(new Point(0, 0, 1000)).setDirection( new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVpSize(150, 150) //
            .setVpDistance(1000);
    private final Camera.Builder camera2 = Camera.getBuilder().setLocation(  new Point(0, 0, 1000)).setDirection( new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVpSize(200, 200) //
            .setVpDistance(1000);


    private Point[] p = { // The Triangles' vertices:
            new Point(-110, -110, -150), // the shared left-bottom
            new Point(95, 100, -150), // the shared right-top
            new Point(110, -110, -150), // the right-bottom
            new Point(-75, 78, 100) }; // the left-top
    private Point trPL = new Point(30, 10, -100); // Triangles test Position of Light
    private Point spPL = new Point(-50, -50, 25); // Sphere test Position of Light
    private Color trCL = new Color(800, 500, 250); // Triangles test Color of Light
    private Color spCL = new Color(800, 500, 0); // Sphere test Color of Light
    private Vector trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
    private Material material = new Material().setKd(0.5).setKs(0.5).setShininess(300);
    private Geometry triangle1 = new Triangle(p[0], p[1], p[2]).setMaterial(material);
    private Geometry triangle2 = new Triangle(p[0], p[1], p[3]).setMaterial(material);
//    private Geometry sphere = new Sphere(new Point(0, 0, -50), 50d) //
//            .setEmission(new Color(BLUE).reduce(2)) //
//            .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(300));

//    /**
//     * Produce a picture of a sphere lighted by a directional light
//     */
//    @Test
//    public void sphereDirectional() {
//
//        scene1.getGeometries().add(sphere);
//        scene1.lights.add(new DirectionLight(spCL, new Vector(1, 1, -0.5)));
//
//        ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
//        camera1.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene1)) //
//                .renderImage();
//        camera1.writeToImage();//
//
//    }
//
//    /**
//     * Produce a picture of a sphere lighted by a point light
//     */
//    @Test
//    public void spherePoint() {
//        scene1.getGeometries().add(sphere);
//        scene1.lights.add(new PointLight(spCL, spPL).setKL(0.001).setKQ(0.0002));
//
//        ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
//        camera1.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene1)) //
//                .renderImage();//
//        camera1.writeToImage();//
//    }
//
//    /**
//     * Produce a picture of a sphere lighted by a spot light
//     */
//    @Test
//    public void sphereSpot() {
//        scene1.getGeometries().add(sphere);
//        scene1.lights.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.0001));
//
//        ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
//        camera1.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene1)) //
//                .renderImage(); //
//        camera1.writeToImage();
//    }
//
//    /**
//     * Produce a picture of a two triangles lighted by a directional light
//     */
//    @Test
//    public void trianglesDirectional() {
//        scene2.getGeometries().add(triangle1, triangle2);
//        scene2.getLights().add(new DirectionLight(trCL, trDL));
//
//        ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
//        camera2.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene2)) //
//                .renderImage(); //
//        camera2.writeToImage(); //
//    }
//
//    /**
//     * Produce a picture of a two triangles lighted by a point light
//     */
//    @Test
//    public void trianglesPoint() {
//        scene2.getGeometries().add(triangle1, triangle2);
//        scene2.getLights().add(new PointLight(trCL, trPL).setKL(0.001).setKQ(0.0002));
//
//        ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
//        camera2.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene2)) //
//                .renderImage(); //
//        camera2.writeToImage(); //
//
//    }
//
//    /**
//     * Produce a picture of a two triangles lighted by a spot light
//     */
//    @Test
//    public void trianglesSpot() {
//        scene2.getGeometries().add(triangle1, triangle2);
//        scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKL(0.001).setKQ(0.0001));
//
//        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
//        camera2.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene2)) //
//                .renderImage(); //
//        camera2.writeToImage();
//    }

//    /**
//     * Produce a picture of a sphere lighted by a narrow spot light
//     */
//    @Test
//    public void sphereSpotSharp() {
//        scene1.getGeometries().add(sphere);
//        scene1.lights
//                .add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setKL(0.001).setKQ(0.00004));
//
//        ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
//        camera1.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene1)) //
//                .renderImage(); //
//        camera1.writeToImage();
//    }
//    /**
//     * Produce a picture of a two triangles lighted by a narrow spot light
//     */
//    @Test
//    public void trianglesSpotSharp() {
//        scene2.getGeometries().add(triangle1, triangle2);
//        scene2.getLights().add(new SpotLight(trCL, trPL, trDL).setKL(0.001).setKQ(0.00004));
//
//        ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
//        camera2.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene2)) //
//                .renderImage(); //
//        camera2.writeToImage(); //
//
//    }
//    @Test
//    public void sphereMultiLights() {
//        scene1.getGeometries().add(sphere);
//        scene1.lights.add(new DirectionLight(spCL, new Vector(-1, -0.8, -0.5)));
//        scene1.lights.add(new SpotLight(spCL, spPL, new Vector(0.6, 2.5, -0.5)).setKL(0.09).setKQ(0.01));
//        scene1.lights.add(new PointLight(spCL, spPL).setKL(0.1).setKQ(0.00002));
//
//        ImageWriter imageWriter = new ImageWriter("lightsMultiLightSphere", 500, 500);
//        camera1.setImageWriter(imageWriter) //
//                .setRayTracer(new RayTracerBasic(scene1)) //
//                .renderImage();
//        camera1.writeToImage();//
//    }
//    /**
//     * Produce a picture of a two triangles lighted by a multiple lights
//     */
    @Test
    public void TrianglesMultiLight() {
        scene2.geometries.add(triangle1, triangle2);
        scene2.lights.add(new SpotLight(trCL, trPL, trDL).setKl(0.0002).setKq(0.0000001));
        scene2.lights.add(new DirectionalLight(trCL, trDL));
        scene2.lights.add(new PointLight(trCL, trPL).setKl(0.0001).setKq(0.009));
        ImageWriter imageWriter = new ImageWriter("lightsMultiLightTriangle", 500, 500);
        Camera cam2=camera2.setImageWriter(imageWriter) //
                .setRayTracer(new SimpleRayTracer(scene2)).build(); //
               cam2 .renderImage(); //
        cam2.writeToImage();
    }

}