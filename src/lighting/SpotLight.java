package lighting;

import java.math.BigDecimal;
import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * The {@code SpotLight} class represents a spotlight in a 3D scene.
 * It extends the {@code PointLight} class and adds a direction to the light.
 */
public class SpotLight extends PointLight{
    private Vector direction;

    /**
     * Constructs a {@code SpotLight} with the specified color, position, and direction.
     *
     * @param color The color of the light.
     * @param position The position of the light.
     * @param dir The direction of the light, which is normalized.
     */
    public SpotLight(Color color,Point position,Vector dir)
    {
        super(color,position);
        direction=dir.normalize();
    }

    /**
     * Gets the intensity of the light at a given point.
     * The intensity is scaled by the dot product of the light's direction and the direction vector to the point.
     *
     * @param p The point at which to calculate the light's intensity.
     * @return The intensity of the light at the specified point.
     */
    @Override
    public Color getIntensity(Point p) {
        Vector l = super.getL(p);
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(l)));

    }


    /**
     * Sets the constant attenuation factor.
     *
     * @param Kc The constant attenuation factor.
     * @return This {@code SpotLight} object for method chaining.
     */
    @Override
    public PointLight setKc(double Kc) {
       return(SpotLight) super.setKc(Kc);

    }

    /**
     * Sets the linear attenuation factor.
     *
     * @param Kl The linear attenuation factor.
     * @return This {@code SpotLight} object for method chaining.
     */
    @Override
    public PointLight setKl(double Kl) {
        return(SpotLight) super.setKl(Kl);
    }

    /**
     * Sets the quadratic attenuation factor.
     *
     * @param Kq The quadratic attenuation factor.
     * @return This {@code SpotLight} object for method chaining.
     */
    @Override
    public PointLight setKq(double Kq) {
        return(SpotLight) super.setKq(Kq);
    }
}
