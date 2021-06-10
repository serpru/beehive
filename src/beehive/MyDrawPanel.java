package beehive;

import javax.swing.*;
import java.awt.*;

public class MyDrawPanel extends JPanel{
	//	Draws board objects on screen
	
	private Board mapa;
	
	MyDrawPanel(Board mapa)
	{
		this.mapa = mapa;
		setSize(mapa.getMaxSize().getX(), mapa.getMaxSize().getY());
	}

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