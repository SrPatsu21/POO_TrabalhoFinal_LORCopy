package table;

import cards.CardGenerator;
import dimension_controler.Button;
import dimension_controler.Vec2;

public class Hand
{
    public final int HAND_SIZE = 10;
    private Slot[] slot = new Slot [HAND_SIZE];
    private Button button;
    private int cards_on_hand = 0;
    private final CardGenerator CARD_GENERATOR = new CardGenerator();

    public Hand(int resolution_x, int resolution_y)
    {
        defineHandPos(resolution_x, resolution_y);
        defineButton();
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
    public Button getButton()
    {
        return button;
    }
    public int getCardsOnHand()
    {
        return cards_on_hand;
    }

    public void setSlot(Slot[] slot)
    {
        this.slot = slot;
    }
    public void setButton(Button hand_pos)
    {
        this.button = hand_pos;
    }
    public void setCardsOnHand(int cards_on_hand)
    {
        this.cards_on_hand = cards_on_hand;
    }
    private void defineHandPos(int resolution_x, int resolution_y)
    {
        this.button = new Button(new Vec2((int)(resolution_x-(resolution_x*0.1)), 0), new Vec2((int)(resolution_x-(resolution_x*0.9)), (int)(resolution_y-(resolution_y*0.2))));
    }
    private void defineButton()
    {
        int col_size = getButton().getStart().getX()*1/HAND_SIZE;
        for (int i = 0; i < HAND_SIZE; i++)
        {
            this.slot[i] = new Slot(
                    new Vec2(getButton().getStart().getX()+(i*col_size), getButton().getStart().getY()),
                    new Vec2(getButton().getStart().getX()+((i+1)*col_size), getButton().getEnd().getY())
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
