package players;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class Enemy extends Player
{
    public Enemy(Draw draw, int resolution_x, int resolution_y, Color background, Color handcolor)
    {
        super(draw, resolution_x, resolution_y, background, handcolor);
    }

    @Override
    public void initPlayerStatus()
    {
        this.playerStatus = new PlayerStatus(getDraw(), (int)(RESOLUTION_X*0.1), (int)(RESOLUTION_Y*0.9), (int)(RESOLUTION_X*0.2), (int)(RESOLUTION_Y*0.8));
    }
    @Override
    public void initTable()
    {
        super.table = new Table(getDraw(), RESOLUTION_X, (int)(RESOLUTION_Y*0.65), (int)(RESOLUTION_Y*0.90));
    }
    @Override
    public void initHand()
    {
        this.hand = new Hand(getDraw(), 0, 0, 0);
    }
    @Override
    public void redraw()
    {
        redrawTable();
        redrawEnergy();
    }

    public void autoPlay()
    {

    }
}
