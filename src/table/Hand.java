package table;

import cards.CardGenerator;
import dimension_controler.Button;
import dimension_controler.Vec2;
import edu.princeton.cs.algs4.Draw;

import java.awt.*;

public class Hand
{
    public final int HAND_SIZE = 10;
    private Slot[] slot = new Slot [HAND_SIZE];
    private Draw draw;
    private Button button;
    private int cards_on_hand = 0;
    public final int COLS_SIZE;
    public final int MARGE;
    public final int COLSN = 16;
    private final CardGenerator CARD_GENERATOR = new CardGenerator();

    public Hand(Draw draw,int resolution_x, int resolution_y)
    {
        setDraw(draw);
        defineHandPos(resolution_x, resolution_y);
        defineButton();
        COLS_SIZE = resolution_x/COLSN;
        MARGE = (COLS_SIZE*COLSN/2) - (HAND_SIZE*COLS_SIZE/2+1);
    }
    public Hand(Draw draw, int resolution_x, int resolution_y,Slot[] slot, int cards_on_hand)
    {
        this(draw, resolution_x, resolution_y);
        setSlot(slot);
        setCardsOnHand(cards_on_hand);
    }

    public Draw getDraw()
    {
        return draw;
    }
    public void setDraw(Draw draw)
    {
        this.draw = draw;
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
    //cliente
    public void clearTable(Color background)
    {
        getDraw().setPenColor(background);
        getDraw().filledPolygon(new double[]{getButton().getStart().getX(), getButton().getEnd().getX()}, new double[]{getButton().getStart().getY(), getButton().getStart().getY()});
        getDraw().show();
    }
    public void drawCards()
    {
        for (int i = 0; i < HAND_SIZE; i++)
        {
            if(getSlot()[i] != null)
            {
                if(getSlot()[i].getCard() != null)
                {
                    getSlot()[i].getCard().drawCard(getDraw(), getSlot()[i].getButton().getStart(), getSlot()[i].getButton().getEnd());
                }
            }
        }
        getDraw().show();
    }
    public void drawSlots(Color color)
    {
        this.draw.setPenColor(color);
        for(int pos = 0; pos < HAND_SIZE; pos++) {
            // x1, y1, x2, y2
            getDraw().line(MARGE+(pos*COLS_SIZE), getButton().getStart().getY(), MARGE+(pos*COLS_SIZE), getButton().getEnd().getY());
            getDraw().line(MARGE+((pos+1)*COLS_SIZE), getButton().getStart().getY(), MARGE+((pos+1)*COLS_SIZE), getButton().getEnd().getY());
            getDraw().line(MARGE+(pos*COLS_SIZE), getButton().getStart().getY(), MARGE+((pos+1)*COLS_SIZE), getButton().getStart().getY());
            getDraw().line(MARGE+(pos*COLS_SIZE), getButton().getEnd().getY(), MARGE+((pos+1)*COLS_SIZE), getButton().getEnd().getY());
        }
        getDraw().show();
    }

    public void drawHand(Color background)
    {
        clearTable(background);
        drawSlots(Color.RED);
        drawCards();
        getDraw().show();
    }
}
