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
    public final int MAX_ENERGY = 10;
    private Table table;
    private Hand hand;
    private Energy energy;
    private Draw draw;
    private boolean still_play;

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
        initEnergy();
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
    //Energy
    public void initEnergy()
    {
        this.energy = new Energy(getDraw(), RESOLUTION_X, RESOLUTION_Y, MAX_ENERGY);
    }
    public void redrawEnergy()
    {
        getEnergy().redrawEnergy(BACKGROUND, Color.YELLOW);
    }
    public void receiveEnergyBeforeRound(int round)
    {
        redrawEnergy();
        getEnergy().addEnergy();
        redrawEnergy();
    }
    //still can play
    public boolean verifyIfCanPlay()
    {


        if(getEnergy().getEnergy() == 0)
        {
            return false;
        }
    }
    //client
    public void redraw()
    {
        redrawTable();
        redrawHand();
        redrawEnergy();
    }
}
