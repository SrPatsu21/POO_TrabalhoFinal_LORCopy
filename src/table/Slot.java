package table;

import cards.Card;

public class Slot {
	private Vec2 start;
	private Vec2 end;
	private Card card;

	public Slot()
	{
		
	}
	public Slot(int x0, int y0, int x1, int y1)
	{
		this(new Vec2(x0, y0), new Vec2(x1, y1));
	}
	public Slot(Vec2 start, Vec2 end)
	{
		this.start = start;
		this.end = end;
	}
	public Vec2 getStart() 
	{
		return start;
	}
	public void setStart(Vec2 start) 
	{
		this.start = start;
	}
	public void setStart(int x, int y) 
	{
		this.start = new Vec2(x, y);
	}
	public Vec2 getEnd() 
	{
		return end;
	}
	public void setEnd(Vec2 end) 
	{
		this.end = end;
	}
	public void setEnd(int x, int y) 
	{
		this.end = new Vec2(x, y);
	}
	public void setCard(Card card) 
	{
		this.card = card;
	}
	public Card getCard() 
	{
		return this.card;
	}
}
