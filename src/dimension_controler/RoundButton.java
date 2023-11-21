package dimension_controler;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class RoundButton extends Button
{
    private static int turn;
    private Draw draw;
    private Color BACKGROWND;
    public RoundButton()
    {

    }
    public RoundButton(Draw draw, Vec2 start, Vec2 end, int turn, Color background)
    {
        super(start, end);
        setDraw(draw);
        this.BACKGROWND = background;
        setTurn(turn);
    }
    public RoundButton(Draw draw, int x_start, int y_start, int x_end, int y_end, int turn, Color background)
    {
        this(draw ,new Vec2(x_start,y_start), new Vec2(x_end, y_end), turn, background);
    }

    public Draw getDraw()
    {
        return draw;
    }
    public void setDraw(Draw draw)
    {
        this.draw = draw;
    }
    public int getTurn()
    {
        return turn;
    }
    public void setTurn(int turn_set)
    {
        turn = turn_set;
    }
    public void passTurn()
    {
        turn++;
    }
    public void drawRound(String text)
    {
        getDraw().setPenColor(Color.red);
        getDraw().text(getCenter().getX(), getCenter().getY(), text);
        getDraw().line(getStart().getX(), getStart().getY(), getEnd().getX(), getStart().getY());
        getDraw().line(getStart().getX(), getEnd().getY(), getEnd().getX(), getEnd().getY());
        getDraw().line(getStart().getX(), getStart().getY(), getStart().getX(), getEnd().getY());
        getDraw().line(getEnd().getX(), getStart().getY(), getEnd().getX(), getEnd().getY());
        getDraw().show();
    }
    public void clearController(Color background)
    {
        getDraw().setPenColor(background);
        getDraw().filledPolygon(new double[]{getStart().getX(), getStart().getX(), getEnd().getX(), getEnd().getX()}, new double[]{getStart().getY(), getEnd().getY(), getEnd().getY(), getStart().getY()});
        getDraw().show();
    }
    public void RoundController()
    {

    }
}
