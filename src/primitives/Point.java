package primitives;

public class Point {
    protected Double3 xyz;
    public Point(double x,double y,double z)
    {
        xyz=new Double3(x,y,z);
    }
    public Point(Double3 abc)
    {
        xyz=abc;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(!(obj instanceof Point point))return false;
        return xyz.equals(point.xyz);
    }

    @Override
    public String toString() {
        return "Point {" + xyz+"}";
    }

    public Vector substract(Point p)
    {

    }

}
