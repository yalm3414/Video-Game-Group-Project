import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class TileMap
{
	//------------------------------------------------------------------------//
	
	String filename = "";
	
	String[] map = {""};
	
	Image[]  tile = null;
	String[] tile_name = null;
	
	Image    background = null;
	String   background_name = null;

	int scale = 32;
	int tileSize = 16;
	char active_tile = '.';
	
	static ArrayList<Rect> rects = new ArrayList<>();
	//------------------------------------------------------------------------//
	
	public TileMap()
	{
		
	}

	//------------------------------------------------------------------------//

	public TileMap(String filename, int scale, int tileSize)
	{
		this.filename = filename;
		
		loadMap(filename);
		
		loadAssets();		
		
		this.scale = scale;
		this.tileSize = tileSize;
		
		loadRects();
	}
	
	//------------------------------------------------------------------------//

	public void create(int rows, int cols)
	{
		map = new String[rows];
		
		StringBuilder empty_row = new StringBuilder(cols);
		
		for(int col = 0; col < cols; col++)
		{	
			empty_row.append('.');
		}
		
		for(int row = 0; row < rows; row++)
		{	
			map[row] = empty_row.toString();
		}
	}
	
	//------------------------------------------------------------------------//

	public void setMap(String[] map)
	{
		this.map = map;
	}
	
	//------------------------------------------------------------------------//

	public void setTileNames(String[] tile_name)
	{
		this.tile_name = tile_name;
	}
	
	//------------------------------------------------------------------------//

	public void setBackgroundNames(String background_name)
	{
		this.background_name = background_name;
	}
	
	//------------------------------------------------------------------------//
   // Load TileMap data from a text file                                     //
	//------------------------------------------------------------------------//
	
	public void loadMap(String filename)
	{
		this.filename = filename;
		
		File file = new File(filename);
		
		try
		{
		   BufferedReader input = new BufferedReader(new FileReader(file));
		   
		   map       = loadStringArray(input);    // Load Map Codes
		   tile_name = loadStringArray(input);    // Load Tile Filenames		   
		   background_name = input.readLine();	   // Load Background Filename
		   
		   input.close();
		}
		catch(IOException x) {};
		
	}
	
	public void loadRects()
	{
		
		for(int row = 0; row < map.length; row++)
		{	
			for(int col = 0; col < map[row].length(); col++)
			{
				char c = map[row].charAt(col);				
				
				if(c != '.')
				{	
					rects.add(new Rect (scale*col - Camera.x, scale*row - Camera.y,scale,scale));
				}
			}
		}	
		/*
		int c_row = Math.max(Camera.y / scale, 0);
		int c_col = Math.max(Camera.x / scale, 0);
		for(int row = c_row; row < Math.min(c_row+950/scale, map.length); row++)
		{	
			for(int col = c_col; col < Math.min(c_col+1500/scale, map[0].length()); col++)
			{
				char c = map[row].charAt(col);				
				
				if((c != '.') && ((c - 'A') < tile.length))
				{
				   rects.add(new Rect (scale*col - Camera.x, scale*row - Camera.y,scale,scale));
				   
				}
			}
		}
		*/
		System.out.println(rects.size());
	}

	//------------------------------------------------------------------------//
   // Load Images for Tiles and Background as indicated TileMap data files   // 
	//------------------------------------------------------------------------//
	
	public void loadAssets()
	{		
	   tile      = new Image[tile_name.length];

	   for(int i = 0; i < tile.length; i++)
		{
			tile[i] = getImage(tile_name[i]);
		}
		
		background = getImage(background_name);		
	}
	
	//------------------------------------------------------------------------//
   // Save TileMap data to a text file                                       //
	//------------------------------------------------------------------------//
	
	public void saveMap(String filename)
	{
		File file = new File(filename);
		
		try
		{
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			
			saveStringArray(map, output);        // Save Map Codes			
			saveStringArray(tile_name, output);  // Save Tile Filenames
			output.write(background_name);       // Save Background Filename
			
			output.close();
		}
		catch(IOException x) {}
	}
	
	//------------------------------------------------------------------------//
	
	public String[] loadStringArray(BufferedReader input) throws IOException
	{
	   int n = Integer.parseInt(input.readLine());  
	   
	   String[] s = new String[n];
	   
	   for(int i = 0; i < s.length; i++)
	   
	   	s[i] = input.readLine();
	   
	   return s;
	}
	
	//------------------------------------------------------------------------//

	public void saveStringArray(String[] s, BufferedWriter output) throws IOException
	{
		output.write(s.length + "\n");
		
		for(int i = 0; i < s.length; i++)
			
			output.write(s[i] + "\n");
	}
	
	//------------------------------------------------------------------------//
   // return the value at location (x, y) of the TileMap                     // 
	//------------------------------------------------------------------------//
	
	public char valueAt(int y, int x)
	{
		int row = y / scale;
		int col = x / scale;
		
		return map[row].charAt(col);
	}
	
	//------------------------------------------------------------------------//
   // Set the tile code that will be used to make changes to the TileMap     //
	//------------------------------------------------------------------------//

	public void setActiveTile(char code)
	{
		active_tile = code;
	}

	//------------------------------------------------------------------------//
   // Change the tile in the TileMap at location (x, y) to the active_tile   //
	//------------------------------------------------------------------------//
	
	public void changeAt(int x, int y)
	{
		int row = y / scale;
		int col = x / scale;
		
		map[row] = map[row].substring(0, col)   + 
				     
				     active_tile                  +  
				     
				     map[row].substring(col + 1);
	}
	
   //-------------------------------------------------------------------------//
   // Draw Clipped TileMap                                                       //
	//------------------------------------------------------------------------//

	public void draw(Graphics g)
	{
	
		g.drawImage(background, - Camera.x, - Camera.y, background.getWidth(null)*(scale/tileSize),background.getHeight(null)*(scale/tileSize), null);
	
		int c_row = Math.max(Camera.y / scale, 0);
		int c_col = Math.max(Camera.x / scale, 0);
		for(int row = c_row; row < Math.min(c_row+950/scale, map.length); row++)
		{	
			for(int col = c_col; col < Math.min(c_col+1500/scale, map[0].length()); col++)
			{
				char c = map[row].charAt(col);				
				
				if((c != '.') && ((c - 'A') < tile.length))
				{
				   g.drawImage(tile[c - 'A'], scale*col - Camera.x, scale*row - Camera.y, scale, scale, null); 
				}
			}
		}
	}
	
	public void drawNoClipping(Graphics g)
	{
		g.drawImage(background, - Camera.x, - Camera.y, background.getWidth(null)*(scale/tileSize),background.getHeight(null)*(scale/tileSize), null);
		
		for(int row = 0; row < map.length; row++)
		{	
			for(int col = 0; col < map[row].length(); col++)
			{
				char c = map[row].charAt(col);				
				
				if(c != '.')
				{	
			      g.drawImage(tile[c - 'A'], scale*col - Camera.x, scale*row - Camera.y, scale, scale, null);
			      rects.get(col+row).draw(g);
				}
			}
		}		
	}
	
	//------------------------------------------------------------------------//
   // Convenience method for loading images                                  //
	//------------------------------------------------------------------------//
	
	public Image getImage(String filename)
	{
		return Toolkit.getDefaultToolkit().getImage(filename);
	}

	//------------------------------------------------------------------------//
		
}