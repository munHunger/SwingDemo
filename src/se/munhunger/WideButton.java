package se.munhunger;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class WideButton extends JButton implements MouseListener
{
	private static final long serialVersionUID = 1L;
	BufferedImage icon;
	Color background = new Color(79, 140, 0);
	Color hover = new Color(120, 180, 0);
	Color current = background;
	Color foreground = new Color(0.9f, 0.9f, 0.9f);
	Dimension dim = new Dimension(200, 100);
	String bottomText;
	
	public WideButton(String bottomText, String rightText, String icon)
	{
		super.addActionListener((e) ->
		{
			System.out.println("click click MOFO");
		});
		super.addMouseListener(this);
		this.bottomText = bottomText;
		try
		{
			this.icon = ImageIO.read(new File(icon));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		super.setPreferredSize(dim);
		super.setMaximumSize(dim);
		super.setMinimumSize(dim);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.setBackground(current);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g);
		g.drawImage(icon, 100 - icon.getWidth() / 2, 50 - icon.getHeight() / 2, null);
		g.setColor(foreground);
		g.drawString(bottomText, 4, 94);
	}
	
	long entered = System.currentTimeMillis();
	long exited = System.currentTimeMillis();
	Runnable fadeIn = () ->
	{
		float progress = 0f;
		while (progress < 1f)
		{
			int r = (int) (background.getRed() + (hover.getRed() - background.getRed()) * progress);
			int g = (int) (background.getGreen() + (hover.getGreen() - background.getGreen()) * progress);
			int b = (int) (background.getBlue() + (hover.getBlue() - background.getBlue()) * progress);
			current = new Color(r, g, b);
			try
			{
				EventQueue.invokeAndWait(() ->
				{
					this.repaint();
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			progress = (System.currentTimeMillis() - entered) / 500f;
			try
			{
				Thread.sleep(10);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	};
	Thread fadeInThread;
	
	Runnable fadeOut = () ->
	{
		float progress = 0f;
		while (progress < 1f)
		{
			int r = (int) (background.getRed() + (hover.getRed() - background.getRed()) * (1f - progress));
			int g = (int) (background.getGreen() + (hover.getGreen() - background.getGreen()) * (1f - progress));
			int b = (int) (background.getBlue() + (hover.getBlue() - background.getBlue()) * (1f - progress));
			current = new Color(r, g, b);
			try
			{
				EventQueue.invokeAndWait(() ->
				{
					this.repaint();
				});
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			progress = (System.currentTimeMillis() - exited) / 1000f;
			try
			{
				Thread.sleep(10);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	};
	Thread fadeOutThread;
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e)
	{
		if (fadeInThread != null && fadeInThread.isAlive())
		{
			fadeInThread.stop();
		}
		if (fadeOutThread != null && fadeOutThread.isAlive())
		{
			fadeOutThread.stop();
		}
		entered = System.currentTimeMillis();
		if (fadeInThread == null || !fadeInThread.isAlive())
		{
			fadeInThread = new Thread(fadeIn);
			fadeInThread.start();
		}
	}
	
	@Override
	public void mouseExited(MouseEvent e)
	{
		if (fadeInThread != null && fadeInThread.isAlive())
		{
			fadeInThread.stop();
		}
		if (fadeOutThread != null && fadeOutThread.isAlive())
		{
			fadeOutThread.stop();
		}
		exited = System.currentTimeMillis();
		if (fadeOutThread == null || !fadeOutThread.isAlive())
		{
			fadeOutThread = new Thread(fadeOut);
			fadeOutThread.start();
		}
	}
}
