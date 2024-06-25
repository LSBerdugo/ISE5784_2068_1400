package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase {

    /**
     * The scene to render
     */
    protected Scene scene;

    /**
     * Constructor for the RayTracerBase
     * @param scene the scene to render
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    /**
     * Trace a ray in the scene
     * @param ray the ray to trace
     * @return the color intensity in the point
     */
    public abstract Color traceRay(Ray ray);


 
}
