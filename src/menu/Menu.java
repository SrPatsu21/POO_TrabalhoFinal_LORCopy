package menu;
import edu.princeton.cs.algs4.Draw;
import edu.princeton.cs.algs4.DrawListener;

import java.awt.*;

import javax.swing.JOptionPane;

public class Menu implements DrawListener
{
    private Draw draw;
    private Dimension screen_size;
    private gameScene game;
    private int resolution;


    public Menu() 
    {
    	this(256);
    }
    public Menu (int resolution)
    {
        defineScreenSize();
        setResolution(resolution);
        setDraw(new Draw());
        getDraw().addListener(this);
        getDraw().setCanvasSize(getScreenSize().width, getScreenSize().height);
        getDraw().setXscale(0, getResolution());
        getDraw().setYscale(0, getResolution());
        getDraw().enableDoubleBuffering();
        getDraw().clear(Color.GREEN);
        getDraw().setPenColor(Color.RED);
        //font size????
        
        draw.text(resolution/2, resolution/2, "click on screen to start", 25);
        draw.show();
    }
    
    public Draw getDraw() {
		return draw;
	}
	public void setDraw(Draw draw) {
		this.draw = draw;
	}
	//resolution
    public int getResolution() {
		return resolution;
	}
	public void setResolution(int resolution) 
	{
		this.resolution = resolution;
	}
	//screen size
	public void setScreenSize(Dimension screen_size) 
	{
		this.screen_size = screen_size;
	}
	public Dimension getScreenSize() 
	{
		return this.screen_size;
	}
	public void defineScreenSize() 
    {
    	if(JOptionPane.showConfirmDialog(
                null,
                "Would you like to set the screen size manually?",
                null,
                JOptionPane.YES_NO_OPTION) == 0) 
    	{
    		int x = Integer.parseInt(JOptionPane.showInputDialog(null, "width: "));
    		int y = Integer.parseInt(JOptionPane.showInputDialog(null, "height: "));
    		this.screen_size = new Dimension(x, y);
    	}else 
    	{
    		this.screen_size = Toolkit.getDefaultToolkit().getScreenSize();
    	}
    }
	
    public void mousePressed(double x, double y) 
    {
    	if(game != null) 
    	{
    		game.mousePressed(x, y);
    	}else 
    	{
    		game = new gameScene(getDraw(), getResolution());
    	}
    }
    //main
	public static void main(String[] args) 
	{
		new Menu();
	}
}
