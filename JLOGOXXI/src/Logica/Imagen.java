package Logica;

public class Imagen
{	
	private Pixel[][] pixeles;
	private int ancho;
	private int alto;
	private boolean [][] flags;
	
	public Imagen(int alto, int ancho)
	{
		this.alto = alto;
		this.ancho = ancho;
		this.pixeles = new Pixel[alto][ancho];
	}
	
	//public Imagen(){}
	
	public void fondoBlanco()
	{
		for (int i = 0; i < alto; i++)
		{
			for (int j = 0; j < ancho; j++)
			{
				pixeles[i][j] = new Pixel(255, 255, 255);
			}
		}
	}
	public void inicializarFlags()
	{
		this.flags = new boolean[this.alto][this.ancho];
		for (int i = 0; i < alto; i++)
		{
			for (int j = 0; j < ancho; j++)
			{
				this.flags[i][j] = false;
			}
		}
	}


	/********************************************************************************************/
	
	//setters && getters
	public Pixel getYX(int y, int x)
	{
		return pixeles[y][x];
	}
	
	public void setPixel(int fila, int columna, Pixel color)
	{
		this.pixeles[fila][columna] = new Pixel(color.getR(), color.getG(), color.getB());
	}
	
	public Pixel[][] getPixeles()
	{
		return pixeles;
	}

	public void setPixeles(Pixel[][] pixeles)
	{
		this.pixeles = pixeles;
	}

	public int getAncho() 
	{
		return ancho;
	}

	public void setAncho(int ancho) 
	{
		this.ancho = ancho;
	}

	public int getAlto()
	{
		return alto;
	}

	public void setAlto(int alto)
	{
		this.alto = alto;
	}
	
	public Pixel[][] getSalida()
	{
		return pixeles;
	}
	public void setSalida(Pixel[][] salida)
	{
		this.pixeles = salida;
	}
	public boolean getFlags(int fila, int columna)
	{
		return flags[fila][columna];
	}
	
	public void setFlags(int fila, int columna, boolean flags)
	{
		this.flags[fila][columna] = flags;
	}
}
