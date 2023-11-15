package menu;

import edu.princeton.cs.algs4.Draw;
import table.Hand;
import table.Table;

import java.awt.*;

public class GameScene
{
    private Draw draw;
    private Table table;
	private Hand hand;
    private Table enemy_table;
    public final int RESOLUTION_X;
	public final int RESOLUTION_Y;
	public final Dimension DIMENSION;
    
    public GameScene(Draw draw, Dimension dimension, int resolution_x, int resolution_y)
    {
		this.DIMENSION = dimension;
		this.RESOLUTION_X = resolution_x;
		this.RESOLUTION_Y = resolution_y;
    	setDraw(draw);
    	getDraw().clear();
    	initTable();
		initHand();
    	drawTable();
//		drawHand(getDraw());
    }
    
    public Draw getDraw() 
    {
		return draw;
	}

	public void setDraw(Draw draw) 
	{
		this.draw = draw;
	}

	public Table getTable() 
	{
		return table;
	}

	public void setTable(Table table) 
	{
		this.table = table;
	}

	public Table getEnemyTable() 
	{
		return enemy_table;
	}

	public void setEnemyTable(Table enemy_table) 
	{
		this.enemy_table = enemy_table;
	}
	public Hand getHand()
	{
		return hand;
	}
	public void setHand(Hand hand)
	{
		this.hand = hand;
	}
	//Table
    private void initTable() 
    {
        table = new Table(draw, RESOLUTION_X);
        enemy_table = new Table(draw, RESOLUTION_X);
        table.defineSlotPos((int) (RESOLUTION_Y - (RESOLUTION_Y * 0.80)), (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.55)));
        enemy_table.defineSlotPos((int) (RESOLUTION_Y -(RESOLUTION_Y * 0.20)), (int) (RESOLUTION_Y - (RESOLUTION_Y * 0.45)));
    }
    public void drawTable() 
    {
        table.drawSlot((int) (RESOLUTION_Y - (RESOLUTION_Y * 0.80)), (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.55)));
        enemy_table.drawSlot((int) (RESOLUTION_Y -(RESOLUTION_Y * 0.20)), (int) (RESOLUTION_Y - (RESOLUTION_Y * 0.45)));
        getDraw().show();
    }
	//hand
	public void initHand()
	{
		this.hand = new Hand(RESOLUTION_X, RESOLUTION_Y);
	}
	public void drawHand(Draw draw)
	{
		for (int i = 0; i < getHand().HAND_SIZE; i++)
		{
			if (getHand().getSlot()[i].getCard().getImage() != null)
			{
				draw.picture((getHand().getSlot()[i].getEnd().getX()/2), (getHand().getSlot()[i].getEnd().getY()/2), getHand().getSlot()[i].getCard().getImage());
			}
		}
	}
    public void mousePressed(double x, double y) 
    {
		//table button
		for(int i = 0; i < getTable().SLOTSN; i++)
		{
			if(getTable().getSlotPos()[i].getStart().getX() < x && getTable().getSlotPos()[i].getStart().getY() < y)
			{
				if(getTable().getSlotPos()[i].getEnd().getX() > x && getTable().getSlotPos()[i].getEnd().getY() > y)
				{
					getTable().drawImage(getTable().getSlotPos()[i]);
				}
			}
		}
	}
}
