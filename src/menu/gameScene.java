package menu;

import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.DrawListener;
import table.Table;

public class gameScene
{
    private Draw draw;
    private Table table;
    private Table enemy_table;
    private int resolution;
    
    public gameScene(Draw draw, int resolution) 
    {
    	setResolution(resolution);
    	setDraw(draw);
    	draw.clear();
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

	//resolution
    public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) 
	{
		this.resolution = resolution;
	}
	//Table
    private void initTable() 
    {
        table = new Table(draw, getResolution());
        enemy_table = new Table(draw, getResolution());
        table.defineSlotPos((int) (getResolution() - (getResolution() * 0.80)), (int)(getResolution() - (getResolution() * 0.55)));
        enemy_table.defineSlotPos((int) (getResolution() -(getResolution() * 0.20)), (int) (getResolution() - (getResolution() * 0.45)));
    }
    public void drawTable() 
    {
        table.drawSlot((int) (getResolution() - (getResolution() * 0.80)), (int)(getResolution() - (getResolution() * 0.55)));
        enemy_table.drawSlot((int) (getResolution() -(getResolution() * 0.20)), (int) (getResolution() - (getResolution() * 0.45)));
        draw.show();
    }
    public void mousePressed(double x, double y) 
    {
        for(int i = 0; i < getTable().SLOTSN; i++) 
        {
        	if(getTable().getSlotPos()[i].getStart().getX() < x && getTable().getSlotPos()[i].getStart().getY() < y) 
        	{
                System.out.println("start");
        		if(getTable().getSlotPos()[i].getEnd().getX() > x && getTable().getSlotPos()[i].getEnd().getY() > y) 
        		{
                    System.out.println("end");
        			getTable().drawImage(getTable().getSlotPos()[i]);
        		}
        	}
        }
        System.out.println("pressed");
    }
}
