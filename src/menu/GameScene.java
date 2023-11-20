package menu;

import cards.Card;
import dimension_controler.TurnButton;
import dimension_controler.Vec2;
import edu.princeton.cs.algs4.Draw;
import table.Hand;
import table.Slot;
import table.Table;

import java.awt.*;

public class GameScene
{
    private Draw draw;
    private Table table;
	private Hand hand;
    private Table enemy_table;
	private Slot selected_card;
    public final int RESOLUTION_X;
	public final int RESOLUTION_Y;
	public final Dimension DIMENSION;
	public final Color BACKGROUND = Color.green;
	public final Color HANDOCOLOR = Color.RED;
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
				new Vec2((int)(resolution_x-(resolution_x*0.15)), (int)(resolution_y-(resolution_y*0.55))),
				new Vec2((int)(resolution_x-(resolution_x*0.05)), (int)(resolution_y-(resolution_y*0.45))),
				turn)
		);
		setDraw(draw);
		getDraw().clear(BACKGROUND);
		initTable();
		initHand();
		getTable().drawTable(BACKGROUND, Color.BLACK);
		getEnemyTable().drawTable(BACKGROUND, Color.BLACK);
		getHand().drawHand(HANDOCOLOR);
		getTurn().drawButton(getDraw());
    }

	//gettter setter
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

	public Slot getSelectedCard()
	{
		return selected_card;
	}

	public void setSelectedCard(Slot selected_card)
	{
		this.selected_card = selected_card;
	}

	//Table
    private void initTable() 
    {
        table = new Table(draw, RESOLUTION_X, (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.70)), (int)(RESOLUTION_Y - (RESOLUTION_Y * 0.45)));
        enemy_table = new Table(draw, RESOLUTION_X, (int) (RESOLUTION_Y -(RESOLUTION_Y * 0.10)), (int) (RESOLUTION_Y - (RESOLUTION_Y * 0.35)));
    }
	public void redrawTable()
	{
		getTable().clearTable(BACKGROUND);
		getTable().drawTable(BACKGROUND, Color.RED);
	}

	//Hand
	public void initHand()
	{
		this.hand = new Hand(getDraw(), RESOLUTION_X, RESOLUTION_Y);
	}
	public void redrawHand()
	{
		getHand().clearHand(BACKGROUND);
		getHand().drawHand(HANDOCOLOR);
	}

	//clear draw
	public void clearArea(Vec2 start, Vec2 end, Color color)
	{
		getDraw().setPenColor(color);
		getDraw().filledPolygon(new double[]{start.getX(), end.getX()}, new double[]{start.getY(), end.getY()});
	}

	//mouse events
    public void mousePressed(double x, double y)
    {
		//table button
		if (getTable().getButton().isInside((int)x, (int)y))
		{
			for(int i = 0; i < getTable().SLOTSN; i++)
			{
				if(getTable().getSlotPos()[i].getButton().isInside(x, y))
				{
					if(getSelectedCard() != null) {
						//clear
						clearArea(getSelectedCard().getButton().getStart(), getSelectedCard().getButton().getEnd(), BACKGROUND);
						//operation
//						getHand().removeCard(getSelectedCard().getCard());
//						getTable().getSlotPos()[i].setCard(getSelectedCard().getCard());
						Card aux = getSelectedCard().getCard();
						getSelectedCard().setCard(getTable().getSlotPos()[i].getCard());
						getTable().getSlotPos()[i].setCard(aux);
						setSelectedCard(null);
						//draw
						redrawTable();
						redrawHand();
					}else
					{
						setSelectedCard(getTable().getSlotPos()[i]);
					}
				}
			}
		//hand button
		}else if (getHand().getHand_pos().isInside((int)x, (int)y))
		{
			for(int i = 0; i < getHand().HAND_SIZE; i++)
			{
				if(getHand().getSlot()[i].getButton().isInside(x, y))
				{
						setSelectedCard(getHand().getSlot()[i]);
				}
			}
		}else if(getTurn().isInside((int)x, (int)y))
		{
			getHand().addCard(1);
			redrawHand();
			getTurn().passTurn();
		}
//remover tardiamente, muito util em testes
//		System.out.println(getHand().getButton().isInside(x, y) + " " + getHand().getButton().getStart().getX()+ "/" + getHand().getButton().getStart().getY() + " x " + x + "/" + y);
	}
}
