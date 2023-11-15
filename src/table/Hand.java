package table;

import cards.CardGenerator;

public class Hand
{
    public final int HAND_SIZE = 10;
    private Slot[] slot = new Slot [HAND_SIZE];
    private Slot hand_pos = new Slot();
    private int cards_on_hand = 0;
    private final CardGenerator CARD_GENERATOR = new CardGenerator();

    public Hand(int resolution_x, int resolution_y)
    {
        defineHandPos(resolution_x, resolution_y);
        defineSlotPos();
    }
    public Hand(int resolution_x, int resolution_y,Slot[] slot, int cards_on_hand)
    {
        this(resolution_x, resolution_y);
        setSlot(slot);
        setCardsOnHand(cards_on_hand);
    }

    public Slot[] getSlot()
    {
        return slot;
    }
    public Slot getHandPos()
    {
        return hand_pos;
    }
    public int getCardsOnHand()
    {
        return cards_on_hand;
    }

    public void setSlot(Slot[] slot)
    {
        this.slot = slot;
    }
    public void setHandPos(Slot hand_pos)
    {
        this.hand_pos = hand_pos;
    }
    public void setCardsOnHand(int cards_on_hand)
    {
        this.cards_on_hand = cards_on_hand;
    }
    private void defineHandPos(int resolution_x, int resolution_y)
    {
        this.hand_pos = new Slot(new Vec2((int)(resolution_x-(resolution_x*0.1)), 0), new Vec2((int)(resolution_x-(resolution_x*0.9)), (int)(resolution_y-(resolution_y*0.2))));
    }
    private void defineSlotPos()
    {
        int col_size = getHandPos().getStart().getX()*1/HAND_SIZE;
        for (int i = 0; i < HAND_SIZE; i++)
        {
            this.slot[i] = new Slot(
                    new Vec2(getHandPos().getStart().getX()+(i*col_size),getHandPos().getStart().getY()),
                    new Vec2(getHandPos().getStart().getX()+((i+1)*col_size), getHandPos().getEnd().getY())
            );
        }
    }
    public void addCard(int id)
    {
        if (cards_on_hand < HAND_SIZE)
        {
            for (int i = 0; i < HAND_SIZE; i++)
            {
                if (getSlot()[i] != null)
                {
                    this.slot[i].setCard(CARD_GENERATOR.getCard(id));
                    i = HAND_SIZE;
                }
            }
            this.cards_on_hand++;
        }
    }
}
