package primitives;

public class Ray
{
    private Point head;
    private Vector direction;

    public Ray(Point p,Vector v)
    {
        head=p;
        direction=v.normalize();
    }
    public boolean equal(Object obj)
    {
        if(this==obj)return true;
        if(!(obj instanceof Ray ray))return false;
        return head.equals(ray.head) && direction.equals(ray.direction);
    }
    public String toString()
    {
        return head.toString() +"\n"+ direction.toString();
    }
}
