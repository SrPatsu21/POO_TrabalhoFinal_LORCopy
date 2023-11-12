package cards;

public class Card 
{
	private final int id;
	private byte type;
	protected String description;
	protected byte full_life;
	protected String image;
	//status
	protected byte life;
	protected byte damage;
	//buffs
	protected byte damage_buff;
	protected byte life_buff;
	protected byte damage_buff_turns;
	protected byte life_buff_turns;
	
	public Card(int id, byte type)
	{
		this.id = id;
		this.type = type;
	}
	public Card(int id, byte type, byte full_life, byte life, byte damage, String description, String path)
	{
		this(id, type);
		setFullLife(full_life);
		setLife(full_life);
		setDamage(damage);
		setDescription(description);
		setImage(path);
	}
	public byte getFullLife() {
		return life;
	}
	public void setFullLife(byte life) {
		this.life = (byte)Math.max(0, life);
	}
	public byte getLife() {
		return life;
	}
	public void setLife(byte life) {
		this.life = (byte)Math.max(0, life);
	}
	public byte getDamage() {
		return damage;
	}
	public void setDamage(byte damage) 
	{
		this.damage = (byte)Math.max(0, damage);
	}
	public String getDescription() 
	{
		return description;
	}
	public void setDescription(String description) 
	{
		this.description = description;
	}
	public int getId() 
	{
		return id;
	}
	public byte getType() 
	{
		return type;
	}
	public void setType(byte type) 
	{
		this.type = type;
	}
	//buff
	public void buffDamage(byte turns, byte buff)
	{
		this.damage_buff += buff;
		this.damage_buff_turns += turns;
	}
	public void buffLife(byte turns, byte buff) 
	{
		this.life_buff += buff;
		this.life_buff_turns += turns;
	}
	public void dbuffDamage(byte turns, byte buff)
	{
		this.damage_buff -= buff*-1;
		this.damage_buff_turns -= turns;
	}
	public void dbuffLife(byte turns, byte buff) 
	{
		this.life_buff -= buff;
		this.life_buff_turns -= turns;
	}
	public void clearLifeBuff() 
	{
		this.life_buff = 0;
		this.life_buff_turns = 0;
	}
	public void clearDamageBuff() 
	{
		this.damage_buff = 0;
		this.damage_buff_turns = 0;
	}
	public void clearBuff() 
	{
		clearLifeBuff();
		clearDamageBuff();
	}
	public void passTurn() 
	{
		if(this.life_buff_turns != 0) 
		{
			if(this.life_buff_turns < 0) 
			{
				this.life_buff_turns++;
			}else
			{
				this.life_buff_turns--;
			}
			if(this.life_buff_turns == 0) 
			{
				clearLifeBuff();
			}
		}
		if(this.damage_buff_turns != 0) 
		{
			if(this.damage_buff_turns < 0) 
			{
				this.damage_buff_turns++;
			}else
			{
				this.damage_buff_turns--;
			}
			if(this.damage_buff_turns == 0) 
			{
				clearDamageBuff();
			}
		}
	}
	//atack and defense
	public byte getActualDamage() 
	{
		return (byte)(this.damage+this.damage_buff);
	}
	public byte getActualLife() 
	{
		return (byte)(this.life+this.life_buff);
	}
	//recive damage
	public byte reciveDamage(int damage)
	{
		if(this.life + this.life_buff < damage) 
		{
			this.kill();
			return 0;
		}else 
		{
			if(damage < this.life_buff) 
			{
				this.life_buff -= damage;
			}else
			{
				this.life -= damage - this.life_buff;
			}
			return 1;
		}
	}
	//load image
	 protected void setImage(String path) 
	 {
		 this.image = path;
	 }
	public String getImage() 
	{
		return this.image;
	}
	//kill
	public void kill() 
	{
	}
}
