package se.munhunger;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author munhunger
 * 		
 */
public class Main
{
	public static void main(String[] args)
	{
		new Main();
	}
	
	JFrame frame = new JFrame("Swing Demo");
	
	private Point initialClick;
	
	public Main()
	{
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setBackground(new Color(21, 21, 21));
		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		JPanel headerPanel = new JPanel(new BorderLayout());
		headerPanel.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mousePressed(MouseEvent e)
			{
				initialClick = e.getPoint();
			}
		});
		headerPanel.addMouseMotionListener(new MouseMotionAdapter()
		{
			
			@Override
			public void mouseDragged(MouseEvent e)
			{
				
				// get location of Window
				int thisX = frame.getLocation().x;
				int thisY = frame.getLocation().y;
				
				// Determine how much the mouse moved since the initial click
				int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
				int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);
				
				// Move window to this position
				int X = thisX + xMoved;
				int Y = thisY + yMoved;
				frame.setLocation(X, Y);
			}
		});
		headerPanel.add(getWindowControlls(), BorderLayout.EAST);
		headerPanel.setOpaque(false);
		mainPanel.add(headerPanel);
		
		mainPanel.add(Box.createRigidArea(new Dimension(0, 150)));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.LINE_AXIS));
		
		centerPanel.add(getPanel1());
		centerPanel.add(getPanel2());
		centerPanel.add(getPanel3());
		
		mainPanel.add(centerPanel);
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
	}
	
	private Component getWindowControlls()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.setOpaque(false);
		JButton min = getImageButton("res/minimize.png");
		min.addActionListener((e) ->
		{
			frame.setExtendedState(JFrame.ICONIFIED);
		});
		panel.add(min);
		panel.add(Box.createRigidArea(new Dimension(25, 0)));
		JButton max = getImageButton("res/maximize.png");
		max.addActionListener((e) ->
		{
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		});
		panel.add(max);
		panel.add(Box.createRigidArea(new Dimension(25, 0)));
		JButton exit = getImageButton("res/close.png");
		exit.addActionListener((e) ->
		{
			System.exit(0);
		});
		panel.add(exit);
		panel.add(Box.createRigidArea(new Dimension(5, 0)));
		return panel;
	}
	
	private JButton getImageButton(String image)
	{
		JButton button = new JButton(new ImageIcon(image));
		button.setBorderPainted(false);
		button.setBorder(null);
		button.setMargin(new Insets(0, 0, 0, 0));
		button.setContentAreaFilled(false);
		button.setIcon(new ImageIcon(image));
		button.setRolloverIcon(new ImageIcon(image));
		button.setPressedIcon(new ImageIcon(image));
		button.setDisabledIcon(new ImageIcon(image));
		return button;
	}
	
	private Component getPanel1()
	{
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setOpaque(false);
		JLabel label = new JLabel("Panel1");
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 24));
		label.setForeground(new Color(0.7f, 0.7f, 0.7f));
		panel1.add(label, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		panel1.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		
		buttonPanel.add(getButton2());
		buttonPanel.add(getButton2());
		buttonPanel.add(getButton2());
		buttonPanel.add(getButton2());
		buttonPanel.add(getButton2());
		panel1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 25));
		return panel1;
	}
	
	private Component getPanel2()
	{
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setOpaque(false);
		JLabel label = new JLabel("Panel2");
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 24));
		label.setForeground(new Color(0.7f, 0.7f, 0.7f));
		panel1.add(label, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
		panel1.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		
		buttonPanel.add(getButton1());
		buttonPanel.add(getButton1());
		
		panel1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 25));
		return panel1;
	}
	
	private Component getPanel3()
	{
		JPanel panel1 = new JPanel(new BorderLayout());
		panel1.setOpaque(false);
		JLabel label = new JLabel("Panel3");
		label.setFont(new Font(label.getFont().getName(), Font.BOLD, 24));
		label.setForeground(new Color(0.7f, 0.7f, 0.7f));
		panel1.add(label, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		panel1.add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.setOpaque(false);
		
		buttonPanel.add(getButton3());
		buttonPanel.add(getButton3());
		buttonPanel.add(getButton3());
		buttonPanel.add(getButton3());
		buttonPanel.add(getButton3());
		buttonPanel.add(getButton3());
		
		panel1.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
		return panel1;
	}
	
	private WideButton getButton1()
	{
		WideButton wb = new WideButton("My Documents", "Empty", "res/files.png");
		wb.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 3));
		wb.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("text1", new ImageIcon("res/files2.png"), SwingConstants.RIGHT);
		label.setForeground(new Color(0.75f, 0.75f, 0.75f, 0.75f));
		p.add(label);
		label = new JLabel("text2", new ImageIcon("res/folder.png"), SwingConstants.RIGHT);
		label.setForeground(new Color(0.75f, 0.75f, 0.75f, 0.75f));
		p.add(label);
		panel.add(p);
		wb.add(panel, BorderLayout.EAST);
		return wb;
	}
	
	private WideButton getButton2()
	{
		WideButton wb = new WideButton("My Documents", "Empty", "res/files.png");
		wb.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 3));
		wb.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("Empty");
		label.setForeground(new Color(0.75f, 0.75f, 0.75f, 0.75f));
		p.add(label);
		panel.add(p);
		wb.add(panel, BorderLayout.EAST);
		return wb;
	}
	
	private WideButton getButton3()
	{
		WideButton wb = new WideButton("My Documents", "Empty", "res/drive.png");
		wb.background = new Color(50, 50, 50);
		wb.hover = new Color(70, 70, 70);
		wb.current = wb.background;
		wb.setBorder(BorderFactory.createLineBorder(new Color(30, 30, 30), 3));
		wb.setLayout(new BorderLayout());
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setOpaque(false);
		JPanel p = new JPanel();
		p.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		p.setOpaque(false);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		JLabel label = new JLabel("Empty");
		label.setForeground(new Color(0.75f, 0.75f, 0.75f, 0.75f));
		p.add(label);
		panel.add(p);
		wb.add(panel, BorderLayout.EAST);
		return wb;
	}
}
