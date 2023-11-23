package cards;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class CardGenerator 
{
	private JSONArray json_array;
	
	public CardGenerator() 
	{
		this("resources\\json\\cards.json");
	}
	private CardGenerator(String path)
	{
		try {
			this.json_array = (JSONArray) new JSONParser().parse(new FileReader(path));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
	public Card getCard(int id)
	{
		if(json_array != null) 
		{
			//	public Card(int id, byte type, byte full_life, byte life, byte damage, byte energy_cost, String description)
			JSONObject json = (JSONObject) json_array.get(id);
			return new Card(id, Byte.parseByte(json.get("type").toString()), Byte.parseByte(json.get("full_life").toString()),
					Byte.parseByte(json.get("life").toString()), Byte.parseByte(json.get("damage").toString()),
					Byte.parseByte(json.get("energy_cost").toString()), (String) json.get("description"));
		}else
		{
			return null;
		}
	}
}
