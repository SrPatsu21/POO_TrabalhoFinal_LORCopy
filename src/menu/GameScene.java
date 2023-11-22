package menu;

import cards.Card;
import dimension_controler.RoundButton;
import dimension_controler.Vec2;
import edu.princeton.cs.algs4.Draw;
import players.Player;
import players.Slot;
import players.Table;

import java.awt.*;

public class GameScene
{
	public final int RESOLUTION_X;
	public final int RESOLUTION_Y;
	public final Dimension DIMENSION;
	public final Color BACKGROUND = Color.green;
	public final Color HANDOCOLOR = Color.RED;
    private Draw draw;
    private Player player;
    private Table enemy_table;
	private Slot selected_card;
	public static RoundButton round_button;
	private int turn_cont = 0;

    public GameScene(Draw draw, Dimension dimension, int resolution_x, int resolution_y){
		this(draw, dimension, resolution_x, resolution_y, 0, 2);
	}
    public GameScene(Draw draw, Dimension dimension, int resolution_x, int resolution_y, int turnN, int turn_cont)
    {
		this.DIMENSION = dimension;
		this.RESOLUTION_X = resolution_x;
		this.RESOLUTION_Y = resolution_y;
		setDraw(draw);
		getDraw().clear(BACKGROUND);
		setRoundButton(new RoundButton(getDraw(),
				new Vec2((int)(resolution_x-(resolution_x*0.15)), (int)(resolution_y-(resolution_y*0.55))),
				new Vec2((int)(resolution_x-(resolution_x*0.05)), (int)(resolution_y-(resolution_y*0.45))),
				turnN, BACKGROUND)
		);
		setTurnCont(turn_cont);
		setPlayer(new Player(draw, dimension, resolution_x, resolution_y, BACKGROUND, HANDOCOLOR));
		getPlayer().initPlayer();
		roundController();

		//make
		enemy_table = new Table(draw, RESOLUTION_X, (int) (RESOLUTION_Y -(RESOLUTION_Y * 0.10)), (int) (RESOLUTION_Y - (RESOLUTION_Y * 0.35)));
		getEnemyTable().drawTable(BACKGROUND, Color.BLACK);
		//client
		getPlayer().redraw();
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
	public static RoundButton getRoundButton()
	{
		return round_button;
	}
	public static void setRoundButton(RoundButton round_button)
	{
		GameScene.round_button = round_button;
	}
	public Player getPlayer()
	{
		return player;
	}
	public void setPlayer(Player player)
	{
		this.player = player;
	}
	public Table getEnemyTable()
	{
		return enemy_table;
	}
	public void setEnemyTable(Table enemy_table)
	{
		this.enemy_table = enemy_table;
	}
	public Slot getSelectedCard()
	{
		return selected_card;
	}
	public void setSelectedCard(Slot selected_card)
	{
		this.selected_card = selected_card;
	}
	public int getTurnCont()
	{
		return turn_cont;
	}
	public void setTurnCont(int turn_cont)
	{
		this.turn_cont = turn_cont;
	}

	//round
	public void roundController() {
		if (getTurnCont() == 0)
		{
			setTurnCont(1);
			getRoundButton().redrawRound("pass");
		}else if(getTurnCont() == 1) {
			setTurnCont(2);
			getRoundButton().redrawRound("fight");
		} else if (getTurnCont() == 2)
		{
			setTurnCont(0);
			getPlayer().receiveEnergyBeforeRound(getRoundButton().getRound());
			//remake before
			getPlayer().getHand().addCard(1);
			getPlayer().getHand().addCard(1);
			//
			getRoundButton().passRound();
			getRoundButton().redrawRound("pass");
		}
	}

	//clear draw
	public void clearArea(Vec2 start, Vec2 end, Color color) {
		getDraw().setPenColor(color);
		getDraw().filledPolygon(new double[]{start.getX(), end.getX()}, new double[]{start.getY(), end.getY()});
	}

	//mouse events
    public void mousePressed(double x, double y)
    {
		//table button
		if (getPlayer().getTable().getButton().isInside((int)x, (int)y)) {
			for(int i = 0; i < getPlayer().getTable().SLOTSN; i++) {
				if(getPlayer().getTable().getSlotPos()[i].getButton().isInside(x, y)) {
					if(getSelectedCard() != null) {
						//clear
						clearArea(getSelectedCard().getButton().getStart(), getSelectedCard().getButton().getEnd(), BACKGROUND);
						//operation
						Card aux = getSelectedCard().getCard();
						getSelectedCard().setCard(getPlayer().getTable().getSlotPos()[i].getCard());
						getPlayer().getTable().getSlotPos()[i].setCard(aux);
						setSelectedCard(null);
						getPlayer().getHand().verifySlots();
						//draw
						getPlayer().redraw();
					}else {
						setSelectedCard(getPlayer().getTable().getSlotPos()[i]);
					}
				}
			}
			//hand button
		}else if (getPlayer().getHand().getHand_pos().isInside((int)x, (int)y)) {
			for(int i = 0; i < getPlayer().getHand().HAND_SIZE; i++) {
				if(getPlayer().getHand().getSlot()[i].getButton().isInside(x, y)) {
					setSelectedCard(getPlayer().getHand().getSlot()[i]);
				}
			}
			//round
		}else if(getRoundButton().isInside((int)x, (int)y)) {
			roundController();
			getPlayer().redraw();
		}
//remover tardiamente, muito util em testes
//		System.out.println(getHand().getButton().isInside(x, y) + " " + getHand().getButton().getStart().getX()+ "/" + getHand().getButton().getStart().getY() + " x " + x + "/" + y);
	}
}
