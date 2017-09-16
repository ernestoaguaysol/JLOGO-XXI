package Logica;

public class Tortuga 
{
	private Punto posicion;
	private int angulo;
	
	public Tortuga(Punto posicion, int angulo)
	{
		this.posicion = posicion;
		this.angulo = angulo;
	}

	//Setters & Getters
	public Punto getPosicion()
	{
		return posicion;
	}
	public void setPosicion(Punto posicion)
	{
		this.posicion = posicion;
	}
	public int getAngulo() 
	{
		return angulo;
	}
	public void setAngulo(int angulo) 
	{
		this.angulo = angulo;
	}

}
