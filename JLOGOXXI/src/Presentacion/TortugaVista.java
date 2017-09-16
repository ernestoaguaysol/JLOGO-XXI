package Presentacion;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.JLabel;

public class TortugaVista extends JLabel
{   
    private double angulo = 0;
   
    public void setAngulo(double angulo)
    {
        this.angulo = angulo;
    }
   
    protected void paintComponent(Graphics g)
    {
    	Graphics2D g2 = (Graphics2D)g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	AffineTransform aT = g2.getTransform();
    	Shape oldshape = g2.getClip();
    	double x = getWidth()/2.0;
    	double y = getHeight()/2.0;
    	aT.rotate(Math.toRadians(90.0 - angulo), x, y);
    	g2.setTransform(aT);
    	g2.setClip(oldshape);
    	super.paintComponent(g);
    }
}

