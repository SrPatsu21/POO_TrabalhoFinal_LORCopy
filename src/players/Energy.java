package players;

import dimension_controler.Button;
import dimension_controler.Vec2;
import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class Energy
{
    private Button button;
    private int energy;
    private Draw draw;
    private final int MAX_ENERGY;

    public Energy()
    {
        MAX_ENERGY = 10;
    }
    public Energy(Draw draw, int max_energy, int x0, int y0, int x1, int y1)
    {
        MAX_ENERGY = max_energy;
        setDraw(draw);
        setButton( new Button(new Vec2(x0, y0), new Vec2(x1, y1)));
    }
    public Button getButton()
    {
        return button;
    }
    public void setButton(Button button)
    {
        this.button = button;
    }
    public int getEnergy()
    {
        return energy;
    }
    public void setEnergy(int energy)
    {
        this.energy = energy;
    }
    public Draw getDraw()
    {
        return draw;
    }
    public void setDraw(Draw draw)
    {
        this.draw = draw;
    }
    //add round
    public void addEnergy(int round)
    {
        this.energy = Math.min(getEnergy()+round, MAX_ENERGY);
    }
    //client
    public void clearEnergy(Color background)
    {
        getDraw().setPenColor(background);
        getDraw().filledPolygon(new double[]{getButton().getStart().getX(), getButton().getStart().getX(), getButton().getEnd().getX(), getButton().getEnd().getX()}, new double[]{getButton().getStart().getY(), getButton().getEnd().getY(), getButton().getEnd().getY(), getButton().getStart().getY()});
        getDraw().show();
    }
    public void drawEnergy(Color energy_color)
    {
        getDraw().setPenColor(energy_color);
        getDraw().text(getButton().getCenter().getX(), getButton().getCenter().getY()*1.03, "Energy:");
        getDraw().text(getButton().getCenter().getX(), getButton().getCenter().getY()*0.97, ""+getEnergy());
        getDraw().line(getButton().getStart().getX(), getButton().getStart().getY(), getButton().getEnd().getX(), getButton().getStart().getY());
        getDraw().line(getButton().getStart().getX(), getButton().getEnd().getY(), getButton().getEnd().getX(), getButton().getEnd().getY());
        getDraw().line(getButton().getStart().getX(), getButton().getStart().getY(), getButton().getStart().getX(), getButton().getEnd().getY());
        getDraw().line(getButton().getEnd().getX(), getButton().getStart().getY(), getButton().getEnd().getX(), getButton().getEnd().getY());
        getDraw().show();
    }
    public void redrawEnergy(Color background, Color energy_color)
    {
        clearEnergy(background);
        drawEnergy(energy_color);
        getDraw().show();
    }
}
