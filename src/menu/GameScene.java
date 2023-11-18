package menu;

import dimension_controler.TurnButton;
import dimension_controler.Vec2;
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
	public static TurnButton turn;
    public GameScene(Draw draw, Dimension dimension, int resolution_x, int resolution_y){
		this(draw, dimension, resolution_x, resolution_y, 0);
	}
    public GameScene(Draw draw, Dimension dimension, int resolution_x, int resolution_y, int turn)
    {
		this.DIMENSION = dimension;
		this.RESOLUTION_X = resolution_x;
		this.RESOLUTION_Y = resolution_y;
		setTurn(new TurnButton(
				new Vec2((int)(resolution_x-(resolution_x*0.15)), (int)(resolution_y-(resolution_y*0.45))),
				new Vec2((int)(resolution_x-(resolution_x*0.05)), (int)(resolution_y-(resolution_y*0.55))),
				turn)
		);
    	setDraw(draw);
    	getDraw().clear();
    	initTable();
		initHand();
    	drawTable();
		drawHand(getDraw());
		getTurn().drawButton(getDraw());
    }
    
    public Draw getDraw() 
    {
		return draw;
	}
	public void setDraw(Draw draw) 
	{
		this.draw = draw;
	}
	public static TurnButton getTurn()
	{
		return turn;
	}
	public static void setTurn(TurnButton turn)
	{
		GameScene.turn = turn;
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
	//clear draw
	public void clearArea(Vec2 start, Vec2 end)
	{
		getDraw().filledPolygon(new double[]{start.getX(), end.getX()}, new double[]{start.getY(), end.getY()});
	}
	//Table
    private void initTable() 
    {
        table = new Table(draw, RESOLUTION_X, (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.80)), (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.55)));
        enemy_table = new Table(draw, RESOLUTION_X, (int) (RESOLUTION_Y -(RESOLUTION_Y * 0.20)), (int) (RESOLUTION_Y - (RESOLUTION_Y * 0.45)));
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
			if (getHand().getSlot()[i].getCard() != null)
			{
				draw.picture((getHand().getSlot()[i].getButton().getEnd().getX()/2), (getHand().getSlot()[i].getButton().getEnd().getY()/2), getHand().getSlot()[i].getCard().getImage());
			}
		}
	}
    public void mousePressed(double x, double y)
    {
		//table button
		if (getTable().getButton().isInside(x, y))
		{
			for(int i = 0; i < getTable().SLOTSN; i++)
			{
				if(getTable().getSlotPos()[i].getButton().isInside(x, y))
				{
					getTable().drawImage(getTable().getSlotPos()[i]);
				}
			}
		}

	}
}
