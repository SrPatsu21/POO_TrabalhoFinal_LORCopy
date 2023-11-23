package players;

import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class Player
{
    public final int RESOLUTION_X;
    public final int RESOLUTION_Y;
    public final Color BACKGROUND;
    public final Color HANDOCOLOR;
    public final int MAX_ENERGY = 10;
    protected Table table;
    protected Hand hand;
    protected Energy energy;
    protected Draw draw;
    protected boolean still_play;

    public Player(Draw draw, int resolution_x, int resolution_y, Color background, Color handcolor)
    {
        this.RESOLUTION_X = resolution_x;
        this.RESOLUTION_Y = resolution_y;
        this.BACKGROUND = background;
        this.HANDOCOLOR = handcolor;
        setDraw(draw);
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
    public Energy getEnergy()
    {
        return energy;
    }
    public void setEnergy(Energy energy)
    {
        this.energy = energy;
    }
    public boolean isStill_play()
    {
        return still_play;
    }
    public void setStill_play(boolean still_play) {
        this.still_play = still_play;
    }
    //init
    public void initPlayer()
    {
        initTable();
        initHand();
        initEnergy();
    }
    //Table
    public void initTable()
    {
        this.table = new Table(getDraw(), RESOLUTION_X, (int)(RESOLUTION_Y*0.3), (int)(RESOLUTION_Y*0.55));
    }
    public void redrawTable()
    {
        getTable().drawTable(BACKGROUND, Color.RED);
    }

    //Hand
    public void initHand()
    {
        this.hand = new Hand(getDraw(), RESOLUTION_X, (int)(RESOLUTION_Y*0.1), (int)(RESOLUTION_Y*0.25));
    }
    public void redrawHand()
    {
        getHand().drawHand(HANDOCOLOR, BACKGROUND);
    }
    //Energy
    public void initEnergy()
    {
        this.energy = new Energy(getDraw(), MAX_ENERGY, (int)(RESOLUTION_X*0.1), (int)(RESOLUTION_Y*0.3), (int)(RESOLUTION_X*0.2), (int)(RESOLUTION_Y*0.4));
    }
    public void redrawEnergy()
    {
        getEnergy().redrawEnergy(BACKGROUND, Color.YELLOW);
    }
    public void receiveEnergyBeforeRound(int round)
    {
        getEnergy().addEnergy(round);
        redrawEnergy();
    }
    //still can play
    public boolean verifyIfCanPlay()
    {
        boolean b = true;

        if(getEnergy().getEnergy() == 0)
        {
            b = false;
        }
        return b;
    }
    //client
    public void redraw()
    {
        redrawTable();
        redrawHand();
        redrawEnergy();
    }
}
