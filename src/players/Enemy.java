package players;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class Enemy extends Player
{
    public Enemy(Draw draw, Dimension dimension, int resolution_x, int resolution_y, Color background, Color handcolor)
    {
        super(draw, dimension, resolution_x, resolution_y, background, handcolor);
    }

    @Override
    public void initEnergy() {
        super.initEnergy();
    }
//    protected void initTable()
//    {
//        super.table = new Table(getDraw(), RESOLUTION_X, (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.70)), (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.45)));
//    }
    @Override
    public void initHand()
    {
        this.hand = new Hand(getDraw(), RESOLUTION_X, RESOLUTION_Y);
    }

    @Override
    public void redraw()
    {
        redrawTable();
        redrawEnergy();
    }
}
