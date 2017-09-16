package Logica;

public class Punto 
{
	private int x, y;
	
	public Punto(int x, int y)
	{
		if(!valida(x) || !valida(y))
		{
			throw new RuntimeException("el valor de X e Y, deben estar comprendidos entre 0 y 511");
		}
		this.x = x;
		this.y = y;
	}

	//Setters & Getters
	public int getX() 
	{
		return x;
	}

	public void setX(int x)
	{
		if(!valida(x))
		{
			throw new RuntimeException("el valor de X e Y, deben estar comprendidos entre 0 y 511");
		}
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y)
	{
		if(!valida(y))
		{
			throw new RuntimeException("el valor  de X e Y, deben estar comprendidos entre 0 y 511");
		}
		this.y = y;
	}
	
	private boolean valida(int a)
	{
		if(a >= 0 && a < 512)
		{
			return true;
		}
		return false;
	}
	
	public boolean esIgual(Punto otro)
	{
		boolean retorno = false;
		if(this.x == otro.x && this.y == otro.y)
		{
			retorno = true;
		}
		return retorno;
	}
	
}
