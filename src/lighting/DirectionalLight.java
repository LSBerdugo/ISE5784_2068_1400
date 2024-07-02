package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**
 * The {@code DirectionalLight} class represents a directional light source in a 3D scene.
 * It extends the {@code Light} class and implements the {@code LightSource} interface.
 */
public class DirectionalLight extends Light implements LightSource {
    private Vector direction;

    /**
     * Constructs a {@code DirectionalLight} with the specified color and direction.
     *
     * @param color The color of the light.
     * @param dir The direction of the light.
     */
    public DirectionalLight(Color color, Vector dir) {
        super(color);
        direction = dir;
    }

    /**
     * Gets the intensity of the light at a given point.
     * Since this is a directional light, the intensity is constant and does not depend on the point.
     *
     * @param p The point at which to calculate the light's intensity.
     * @return The intensity of the light, which is constant.
     */
    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    /**
     * Gets the direction vector of the light.
     * For a directional light, this is constant and does not depend on the point.
     *
     * @param p The point to which the direction vector is calculated.
     * @return The direction vector of the light.
     */
    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }
}
