package menu;

import edu.princeton.cs.algs4.Draw;
import table.Table;

import java.awt.*;

public class GameScene
{
    private Draw draw;
    private Table table;
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
    	drawTable();
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
    public void mousePressed(double x, double y) 
    {
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
