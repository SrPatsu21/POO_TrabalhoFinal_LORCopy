package dimension_controler;

import table.Vec2;

public class button
{
    private Vec2 start;
    private Vec2 end;

    public button()
    {

    }
    public button(Vec2 start, Vec2 end)
    {
        setStart(start);
        setEnd(end);
    }
    public button(int x_start, int y_start, int x_end, int y_end)
    {
        this(new Vec2(x_start,y_start), new Vec2(x_end, y_end));
    }
    public Vec2 getStart()
    {
        return start;
    }
    public void setStart(Vec2 start)
    {
        this.start = start;
    }
    public Vec2 getEnd()
    {
        return end;
    }
    public void setEnd(Vec2 end)
    {
        this.end = end;
    }
    public boolean isInside(int x, int y)
    {
        if ((getStart().getX() < x && getEnd().getX() > x) && (getStart().getY() < y && getEnd().getY() > y))
        {
            return true;
        }else
        {
            return false;
        }
    }
    public Vec2 getCenter()
    {
        return new Vec2(getStart().getX()+(getEnd().getX()-getStart().getX())/2, getStart().getY()+(getEnd().getY()-getStart().getY())/2);
    }
}
