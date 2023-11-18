package dimension_controler;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class TurnButton extends Button
{
    private int turn;
    public TurnButton()
    {

    }
    public TurnButton(Vec2 start, Vec2 end, int turn)
    {
        super(start, end);
        setTurn(turn);
    }
    public TurnButton(int x_start, int y_start, int x_end, int y_end, int turn)
    {
        super(new Vec2(x_start,y_start), new Vec2(x_end, y_end));
        setTurn(turn);
    }

    public int getTurn()
    {
        return turn;
    }

    public void setTurn(int turn)
    {
        this.turn = turn;
    }

    public void passTurn()
    {
        this.turn++;
    }
    public void drawButton(Draw draw)
    {
        draw.setPenColor(Color.red);
        draw.text(getCenter().getX(), getCenter().getY(), "next");
        draw.line(getStart().getX(), getStart().getY(), getEnd().getX(), getStart().getY());
        draw.line(getStart().getX(), getEnd().getY(), getEnd().getX(), getEnd().getY());
        draw.line(getStart().getX(), getStart().getY(), getStart().getX(), getEnd().getY());
        draw.line(getEnd().getX(), getStart().getY(), getEnd().getX(), getEnd().getY());
    }
}
