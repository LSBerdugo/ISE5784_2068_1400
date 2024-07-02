package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;
/**
 * The {@code PointLight} class represents a point light source in a 3D scene.
 * It extends the {@code Light} class and implements the {@code LightSource} interface.
 */
public class PointLight extends Light implements LightSource {
    protected Point position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;

    /**
     * Constructs a {@code PointLight} with the specified color and position.
     *
     * @param color The color of the light.
     * @param position The position of the light.
     */
    public PointLight(Color color, Point position) {
        super(color);
        this.position = position;
    }

    /**
     * Gets the direction vector from the light to the specified point.
     *
     * @param p The point to which the direction vector is calculated.
     * @return The direction vector from the light to the specified point.
     */
    public Vector getL(Point p) {
        return p.subtract(position);
    }

    /**
     * Sets the constant attenuation factor.
     *
     * @param Kc The constant attenuation factor.
     * @return This {@code PointLight} object for method chaining.
     */
    public PointLight setKc(double Kc) {
        this.kC =Kc;
        return this;
    }

    /**
     * Sets the linear attenuation factor.
     *
     * @param Kl The linear attenuation factor.
     * @return This {@code PointLight} object for method chaining.
     */
    public PointLight setKl(double Kl) {
        this.kL =Kl;
        return this;
    }

    /**
     * Sets the quadratic attenuation factor.
     *
     * @param Kq The quadratic attenuation factor.
     * @return This {@code PointLight} object for method chaining.
     */
    public PointLight setKq(double Kq) {
        this.kQ = Kq;
        return this;
    }

    /**
     * Gets the intensity of the light at a given point.
     * The intensity is attenuated based on the distance to the point and the attenuation factors.
     *
     * @param p The point at which to calculate the light's intensity.
     * @return The intensity of the light at the specified point.
     */
    @Override
    public Color getIntensity(Point p) {
        Color I0 = super.getIntensity();
        double d = p.distance(position);
        double denominator = kC + kL * d + kQ * d * d;
        double scale = 1.0 / denominator;
        return I0.scale(scale);
    }
}
