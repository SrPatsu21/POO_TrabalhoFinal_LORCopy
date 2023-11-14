package table;

public class Hand
{
    public final int HAND_SIZE = 10;
    private Slot[] slot = new Slot [HAND_SIZE];
    private Slot hand_pos = new Slot();
    private int cards_on_hand = 0;

    public Hand(int resolution)
    {

    }
    public Hand(int resolution,Slot slot, int cards_on_hand)
    {
        this(resolution);
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
    public void defineHandPos(int resolution)
    {
        this.hand_pos = new Slot(new Vec2((int)(resolution-(resolution*0.1)), 0), new Vec2((int)(resolution-(resolution*0.9)), (int)(resolution-(resolution*0.2))));
    }
}
