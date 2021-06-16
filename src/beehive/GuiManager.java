package beehive;

import java.awt.*;

import javax.swing.*;

/**
 * GuiManager manages the window display of the simulation for viewing purposes.
 * @author Serafin Prusik
 *
 */
public class GuiManager {
	//	Manages everything that happens on screen.
	
	/**
	 * Board to display.
	 */
	private Board mapa;
	/**
	 * Application's window.
	 */
	private JFrame frame;
	/**
	 * Label displaying number of plants present.
	 */
	JLabel plantLabel;
	/**
	 * Label displaying hive nectar storage.
	 */
	JLabel hiveLabel;
	/**
	 * Label displaying number of bees present.
	 */
	JLabel beeLabel;
	/**
	 * Label displaying current iteration number.
	 */
	JLabel iterationText;
	/**
	 * Label displaying status message of Simulation object.
	 */
	JLabel statusMessage;
	
	/**
	 * Creates GUI object.
	 * @param mapa Board to display.
	 */
	public GuiManager(Board mapa)
	{
		this.mapa = mapa;
	}
	
	/**
	 * Initializes GUI.
	 */
	public void Go()
	{
		frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 100, 10, 30);
		
		plantLabel = new JLabel("EMPTY", JLabel.LEFT);
		Dimension plantlabelSize = plantLabel.getPreferredSize();
		plantLabel.setForeground(Color.WHITE);
		plantLabel.setBounds(0, 20, 500, plantlabelSize.height);
		
		hiveLabel = new JLabel("EMPTY", JLabel.LEFT);
		Dimension hiveLabelSize = hiveLabel.getPreferredSize();
		hiveLabel.setForeground(Color.WHITE);
		hiveLabel.setBounds(0, 40, 500, hiveLabelSize.height);
		
		beeLabel = new JLabel("EMPTY", JLabel.LEFT);
		Dimension beeLabelSize = beeLabel.getPreferredSize();
		beeLabel.setForeground(Color.WHITE);
		beeLabel.setBounds(0, 60, 500, beeLabelSize.height);
		
		iterationText = new JLabel("EMPTY", JLabel.LEFT);
		Dimension itTextSize = iterationText.getPreferredSize();
		iterationText.setForeground(Color.WHITE);
		iterationText.setBounds(0, 0, 500, itTextSize.height);
		
		statusMessage = new JLabel("EMPTY", JLabel.LEFT);
		Dimension statusMsgSize = statusMessage.getPreferredSize();
		statusMessage.setForeground(Color.WHITE);
		statusMessage.setBounds(0, 80, 500, statusMsgSize.height);
		
		
		//Set JFrame size
		frame.setSize(new Dimension(515,538));
		
		MyDrawPanel drawPanel = new MyDrawPanel(mapa);
		drawPanel.setLayout(null);
		drawPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		drawPanel.add(plantLabel);
		drawPanel.add(hiveLabel);
		drawPanel.add(beeLabel);
		drawPanel.add(iterationText);
		drawPanel.add(statusMessage);
		
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		
		frame.add(drawPanel);
		frame.getContentPane().setBackground(Color.BLACK);
		
	

		
		
		frame.setResizable(false);
		frame.setTitle("Beehive Simulator");
		
		//Make JFrame visible 
		frame.setVisible(true);
	}
	
	/**
	 * Draws window content.
	 */
	public void Draw()
	{
		//	UpdateText();
		//jlabel.setText(text);
		frame.repaint();
	}
}
