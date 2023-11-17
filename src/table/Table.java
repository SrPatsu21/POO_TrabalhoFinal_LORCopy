package table;

import java.awt.Color;

import cards.CardGenerator;
import edu.princeton.cs.algs4.Draw;

public class Table 
{
    private Draw draw;
    private CardGenerator card_gen = new CardGenerator();
    public final int SLOTSN = 6;
    public final int COLSN = 16;
    public final int COLS_SIZE;
    public final int MARGE;
    private Slot [] slot_pos = new Slot[SLOTSN];

	public Slot[] getSlotPos() {
		return slot_pos;
	}

	public void setSlot_pos(Slot[] slot_pos) {
		this.slot_pos = slot_pos;
	}

	public Table(Draw draw, int resolution_x)
	{
		this.draw = draw;
		COLS_SIZE = resolution_x/COLSN;
		MARGE = (COLS_SIZE*COLSN/2) - (SLOTSN*COLS_SIZE/2+1);
	}
	
    public Draw getDraw() {
		return draw;
	}

	public void setDraw(Draw draw) {
		this.draw = draw;
	}

	public void drawSlot(int y0, int y1) 
    {
    	for(int pos = 0; pos < SLOTSN; pos++)
    	{
	        this.draw.setPenColor(Color.RED);
			// x1, y1, x2, y2
	        getDraw().line(MARGE+(pos*COLS_SIZE), y0, MARGE+(pos*COLS_SIZE), y1);
			getDraw().line(MARGE+((pos+1)*COLS_SIZE), y0, MARGE+((pos+1)*COLS_SIZE), y1);
			getDraw().line(MARGE+(pos*COLS_SIZE), y0, MARGE+((pos+1)*COLS_SIZE), y0);
			getDraw().line(MARGE+(pos*COLS_SIZE), y1, MARGE+((pos+1)*COLS_SIZE), y1);
    	}
    }
    public void defineSlotPos(int y0, int y1) 
    {
    	for(int pos = 0; pos < SLOTSN; pos++)
    	{
    		getSlotPos()[pos] = new Slot(MARGE+(pos*COLS_SIZE), y0, MARGE+((pos+1)*COLS_SIZE), y1);
    		setCardOnSlot(pos, 1);
    	}
    }
	 public void drawImage(Slot slot) 
	 {
		 if(slot != null) 
		 {
			 if(slot.getCard() != null) 
			 {
				 if(slot.getCard().getImage() != null) 
				 {
					 getDraw().picture(slot.getButton().getCenter().getX(), slot.getButton().getCenter().getY(), slot.getCard().getImage());
					 getDraw().show();
				 }
			 }
		 }
	 }
	 public void setCardOnSlot(int pos, int card_id) 
	 {
		 if(getSlotPos()[pos] != null) 
		 {
			 getSlotPos()[pos].setCard(card_gen.getCard(card_id));
		 }
	 }
}