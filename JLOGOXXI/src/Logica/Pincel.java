package Logica;

public class Pincel
{
	private int ancho;
	private Pixel color;
	
	public Pincel(int ancho, Pixel color)
	{
		this.ancho = ancho;
		this.color = color;
	}

	//Setters & Getters
	public int getAncho() 
	{
		return ancho;
	}
	public void setAncho(int ancho) 
	{
		this.ancho = ancho;
	}
	public Pixel getColor()
	{
		return color;
	}
	public void setColor(Pixel color)
	{
		this.color = color;
	}
}
