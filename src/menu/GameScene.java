package menu;

import cards.Card;
import cards.Fingth;
import dimension_controler.RoundButton;
import dimension_controler.Vec2;
import edu.princeton.cs.algs4.Draw;
import players.Enemy;
import players.Player;
import players.Slot;

import java.awt.*;

public class GameScene
{
	public final int RESOLUTION_X;
	public final int RESOLUTION_Y;
	public final Color BACKGROUND = Color.green;
	public final Color HANDOCOLOR = Color.RED;
    private Draw draw;
    private Player player;
	private Enemy enemy;
	private Slot selected_card;
	public static RoundButton round_button;
	private int turn_cont = 0;
	private Fingth fight;
	private boolean game_stop = false;

    public GameScene(Draw draw, int resolution_x, int resolution_y){
		this(draw, resolution_x, resolution_y, 0, 2);
	}
    public GameScene(Draw draw, int resolution_x, int resolution_y, int turnN, int turn_cont)
    {
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
		setPlayer(new Player(draw, resolution_x, resolution_y, BACKGROUND, HANDOCOLOR));
		getPlayer().initPlayer();
		setEnemy(new Enemy(draw, resolution_x, resolution_y, BACKGROUND, HANDOCOLOR));
		getEnemy().initPlayer();
		setFight( new Fingth());
		roundController();

		//client
		getPlayer().redraw();
		getEnemy().redraw();
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
	public Enemy getEnemy()
	{
		return enemy;
	}
	public void setEnemy(Enemy enemy)
	{
		this.enemy = enemy;
	}
	public Fingth getFight()
	{
		return fight;
	}
	public void setFight(Fingth fight)
	{
		this.fight = fight;
	}

	//round
	public void roundController() {
		if (getTurnCont() == 0)
		{
			setTurnCont(1);
			getEnemy().autoPlay();
			getRoundButton().redrawRound("pass");
		}else if(getTurnCont() == 1) {
			setTurnCont(2);
			getFight().setPlayer(getPlayer());
			getFight().setEnemy(getEnemy());
			getFight().fight();
			getRoundButton().redrawRound("fight");

		} else if (getTurnCont() == 2)
		{
			if (getPlayer().getPlayerStatus().getLife() == 0 || getEnemy().getPlayerStatus().getLife() == 0)
			{
				this.game_stop = true;
			}else {
				setTurnCont(0);
				getPlayer().receiveEnergyBeforeRound(getRoundButton().getRound() + 1);
				getEnemy().receiveEnergyBeforeRound(getRoundButton().getRound() + 1);
				//remake before
				getPlayer().getHand().addCard(1);
				getPlayer().getHand().addCard(1);
				getEnemy().getHand().addCard(1);
				getEnemy().getHand().addCard(1);
				//
				getRoundButton().passRound();
				getRoundButton().redrawRound("pass");
			}
		}
	}
	public void endGameMessage()
	{
		if (getPlayer().getPlayerStatus().getLife() == 0 || getEnemy().getPlayerStatus().getLife() == 0)
		{
			getDraw().setPenColor(Draw.BLUE);
			if(getPlayer().getPlayerStatus().getLife() == 0 && getEnemy().getPlayerStatus().getLife() == 0)
			{
				getDraw().text(RESOLUTION_X/2, RESOLUTION_Y/2, "I could say u lost anyway", 25);
			} else if (getEnemy().getPlayerStatus().getLife() == 0)
			{
				getDraw().text(RESOLUTION_X/2, RESOLUTION_Y/2, "U WIN", 25);
			}else if (getPlayer().getPlayerStatus().getLife() == 0)
			{
				getDraw().text(RESOLUTION_X/2, RESOLUTION_Y/2, "U LOST to a shito AI", 25);
			}
			getDraw().show();
		}
	}
	//clear draw
	public void clearArea(Vec2 start, Vec2 end, Color color)
	{
		getDraw().setPenColor(color);
		getDraw().filledPolygon(new double[]{start.getX(), end.getX()}, new double[]{start.getY(), end.getY()});
	}
	public void redraw(){
		getPlayer().redraw();
		getEnemy().redraw();
		if (this.game_stop)
		{
			endGameMessage();
		}
	}
	//mouse events
    public void mousePressed(double x, double y)
    {
		if (!game_stop)
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
				redraw();
			}
		}else
		{
		//call a new screen to a new game or load
		}
	}
}
