package primitives;
/**
 * The {@code Material} class represents the material properties of a geometry in a 3D scene.
 * It defines how the surface interacts with light, including diffuse and specular reflection and shininess.
 */
public class Material {
    /**
     * The diffuse reflection coefficient. It determines how much light is scattered
     * diffusely by the surface. A higher value means the surface is more matte.
     */
    public Double3 kD = Double3.ZERO;
    /**
     * The specular reflection coefficient. It determines how much light is reflected
     * in a mirror-like manner by the surface. A higher value means the surface is more glossy.
     */
    public Double3 kS = Double3.ZERO;


    /**
     * The transparency coefficient. It determines how much light passes through the
     * surface. A higher value means the surface is more transparent.
     */
    public Double3 kT = Double3.ZERO;
    /**
     * The reflection coefficient. It determines how much light is reflected by the
     * surface. A higher value means the surface has a higher reflection.
     */
    public Double3 kR = Double3.ZERO;
    /**
     * The shininess exponent. It determines the size and sharpness of the specular
     * highlight. A higher value results in a smaller and sharper highlight.
     */
    public int nShininess = 0;

    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param kd The diffuse reflection coefficient as a {@code Double3}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKd(Double3 kd) {
        this.kD = kd;
        return this;
    }
    /**
     * Sets the transparency coefficient for this material.
     *
     * @param kt the new transparency coefficient
     * @return this Material object for method chaining
     */
    public Material setKt(Double3 kt) {
        this.kT = kt;
        return this;
    }
    /**
     * Sets the reflection coefficient for this material.
     *
     * @param kr the new reflection coefficient
     * @return this Material object for method chaining
     */
    public Material setKr(Double3 kr) {
        this.kR = kr;
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param Ks The specular reflection coefficient as a {@code Double3}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKs(Double3 Ks) {
        this.kS = Ks;
        return this;
    }
    /**
     * Sets the transparency coefficient for this material.
     *
     * @param Kt the new transparency coefficient as a double
     * @return this Material object for method chaining
     */
    public Material setKt(double Kt) {
        this.kT = new Double3(Kt);
        return this;
    }
    /**
     * Sets the reflection coefficient for this material.
     *
     * @param Kr the new reflection coefficient as a double
     * @return this Material object for method chaining
     */
    public Material setKr(double Kr) {
        this.kR = new Double3(Kr);
        return this;
    }


    /**
     * Sets the diffuse reflection coefficient.
     *
     * @param Kd The diffuse reflection coefficient as a {@code double}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKd(double Kd) {
        this.kD = new Double3(Kd);
        return this;
    }

    /**
     * Sets the specular reflection coefficient.
     *
     * @param Ks The specular reflection coefficient as a {@code double}.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setKs(double Ks) {
        this.kS = new Double3(Ks);
        return this;
    }

    /**
     * Sets the shininess exponent for specular reflection.
     *
     * @param nShininess The shininess exponent.
     * @return This {@code Material} object for chaining setters.
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}

