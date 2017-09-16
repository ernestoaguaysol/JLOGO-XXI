package Logica;

public class Pixel 
{
	private int r, g, b;
	
	public Pixel(int r, int g, int b)
	  {
	    if ((!validate(r)) || (!validate(g)) || (!validate(b))) 
	    {
	      throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
	    }
	    this.r = r;
	    this.g = g;
	    this.b = b;
	  }
	  
	//Setters & Getters
	  public int getR()
	  {
	    return this.r;
	  }
	  
	  public void setR(int r)
	  {
	    if (!validate(r))
	    {
	      throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
	    }
	    this.r = r;
	  }
	  
	  public int getG()
	  {
	    return this.g;
	  }
	  
	  public void setG(int g)
	  {
	    if (!validate(g)) 
	    {
	      throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
	    }
	    this.g = g;
	  }
	  
	  public int getB()
	  {
	    return this.b;
	  }
	  
	  public void setB(int b)
	  {
	    if (!validate(b)) 
	    {
	      throw new RuntimeException("Los valores admitidos para cada canal de color es un entero positivo entre 0 y 255 inclusives");
	    }
	    this.b = b;
	  }
	  
	  private boolean validate(int v)
	  {
	    if ((v >= 0) && (v < 256))
	    {
	      return true;
	    }
	    return false;
	  }
	  
	  public boolean esIgual(Pixel otro)
	  {
		  boolean retorno = false;
		  if(this.r == otro.r && this.g == otro.g && this.b == otro.b)
		  {
			  retorno = true;
		  }
		  
		  return retorno;
	  }
}
