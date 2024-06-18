package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight {
    private final Color intensity;
    public static AmbientLight NONE=new AmbientLight(Color.BLACK,0);
    public AmbientLight(Color ia, Double3 ka) {
        this.intensity = ia.scale(ka);
    }

    public AmbientLight(Color ia, double ka) {
        this.intensity = ia.scale(ka);
    }

    public Color getIntensity() {
        return intensity;
    }





}
