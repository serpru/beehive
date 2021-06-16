package beehive;

import javax.swing.*;
import java.awt.*;

/**
 * Draw panel to draw board on.
 * @author Serafin Prusik
 *
 */
public class MyDrawPanel extends JPanel{
	//	Draws board objects on screen
	
	/**
	 * Board to gather data from.
	 */
	private Board mapa;
	
	/**
	 * Creates MyDrawPanel object.
	 * @param mapa Board to gather data from.
	 */
	MyDrawPanel(Board mapa)
	{
		this.mapa = mapa;
		setSize(mapa.getMaxSize().getX(), mapa.getMaxSize().getY());
	}

	/**
	 * Paints objects the Board contains and places them on visual board.
	 */
	@Override
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);
		
		setBackground(Color.BLACK);
		
		for (int i = 0; i < mapa.getObjects().size(); i++)
		{
			int x = mapa.getObjects().get(i).getCoordinates().getX();
			int y = mapa.getObjects().get(i).getCoordinates().getY();
			int size = mapa.getObjects().get(i).getSize();
			g2d.setColor(mapa.getObjects().get(i).getColor());
			
			//	Draw objects as 3x3 rectangles
			g2d.drawRect(x,y,size,size);
			g2d.fillRect(x, y, size, size);
		}
	

	}
}