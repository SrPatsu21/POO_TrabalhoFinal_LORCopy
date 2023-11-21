package player;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class Player
{
    public final int RESOLUTION_X;
    public final int RESOLUTION_Y;
    public final Dimension DIMENSION;
    public final Color BACKGROUND;
    public final Color HANDOCOLOR;
    private Table table;
    private Hand hand;
    private Draw draw;

    public Player(Draw draw, Dimension dimension, int resolution_x, int resolution_y, Color background, Color handcolor)
    {
        this.DIMENSION = dimension;
        this.RESOLUTION_X = resolution_x;
        this.RESOLUTION_Y = resolution_y;
        this.BACKGROUND = background;
        this.HANDOCOLOR = handcolor;
        setDraw(draw);
        initTable();
        initHand();
    }

    public Draw getDraw()
    {
        return draw;
    }
    public void setDraw(Draw draw)
    {
        this.draw = draw;
    }
    public Hand getHand()
    {
        return hand;
    }
    public void setHand(Hand hand)
    {
        this.hand = hand;
    }
    public Table getTable()
    {
        return table;
    }
    public void setTable(Table table)
    {
        this.table = table;
    }

    //Table
    private void initTable()
    {
        this.table = new Table(getDraw(), RESOLUTION_X, (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.70)), (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.45)));
    }
    public void redrawTable()
    {
        getTable().drawTable(BACKGROUND, Color.RED);
    }

    //Hand
    public void initHand()
    {
        this.hand = new Hand(getDraw(), RESOLUTION_X, RESOLUTION_Y);
    }
    public void redrawHand()
    {
        getHand().drawHand(HANDOCOLOR, BACKGROUND);
    }
    //player redraw
    public void redraw()
    {
        redrawTable();
        redrawHand();
    }
}
