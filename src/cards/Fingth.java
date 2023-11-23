package cards;

import players.Player;
import players.Slot;
import players.Table;

public class Fingth
{
    private Player player;
    private Player enemy;

    public Fingth()
    {

    }
    public Fingth(Player player, Player enemy)
    {
        setPlayer(player);
        setEnemy(enemy);
    }
    //getter setter
    public Player getPlayer()
    {
        return player;
    }
    public void setPlayer(Player player)
    {
        this.player = player;
    }
    public Player getEnemy()
    {
        return enemy;
    }
    public void setEnemy(Player enemy)
    {
        this.enemy = enemy;
    }
    public Card getCard(Player player,int i)
    {
        return player.getTable().getSlotPos()[i].getCard();
    }
    //receiving damage
    public void cardReceiveDamage(Card card, int damage)
    {
        int life = getPlayer().getPlayerStatus().getLife();
        if (card.getActualLife() < damage)
        {
            card.reciveDamage(damage);
        }else
        {
            card.kill();
        }
    }
    //combat
    public void fight()
    {
        for (int i = 0; i < getPlayer().getTable().SLOTSN; i++)
        {
            int player_d = 0;
            int enemy_d = 0;
            //set damage
            if (getCard(getPlayer(), i) != null)
            {
                player_d = getCard(getPlayer(), i).getActualDamage();
            }
            if (getCard(getEnemy(), i) != null)
            {
                enemy_d = getCard(getEnemy(), i).getActualDamage();
            }
            //receiving
            //player
            if (getCard(getPlayer(), i) != null)
            {
                cardReceiveDamage(getCard(getPlayer(), i), enemy_d);
            }else
            {
                getPlayer().getPlayerStatus().receiveDamage(enemy_d);
            }
            //enemy
            if (getCard(getEnemy(), i) != null)
            {
                cardReceiveDamage(getCard(getEnemy(), i), player_d);
            }else
            {
                getEnemy().getPlayerStatus().receiveDamage(player_d);
            }
        }
    }
}
