package Logica;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Logo
{
	private Tortuga tortuga;
	private Pincel pincel;
	private Imagen fondo;
	private Imagen dibujo;
	private Historial historial;

	public Logo()
	{
		this.tortuga = new Tortuga(new Punto(256, 256), 90);
		this.pincel = new Pincel(1, new Pixel(255, 0, 0));
		this.dibujo = new Imagen(512, 512);
		this.fondo = new Imagen(512, 512);
		this.dibujo.inicializarFlags();
		this.dibujo.fondoBlanco();
		fondo.fondoBlanco();
		this.historial = new Historial();
	}
	
	public void avanzar(int q)
	{
		int x0 = this.tortuga.getPosicion().getX();
		int y0 = this.tortuga.getPosicion().getY();
		
		int x_dest = limitar((int) Math.round(x0 + q * Math.cos(Math.toRadians(this.tortuga.getAngulo()))));
		int y_dest = limitar((int) Math.round(y0 + q * Math.sin(Math.toRadians(this.tortuga.getAngulo()))));
		
		int Dx = x_dest - x0;
		int Dy = y_dest - y0;
		
		int y = y0;
		int x = x0;
		int p, incE, incNE, stepx, stepy;

		/* determinar que punto usar para empezar, cual para terminar */
		if (Dy < 0) 
		{ 
			Dy = -Dy; 
			stepy = -1; 
		} 
		else 
		{
			stepy = 1;
		}

		if (Dx < 0)
		{  
			Dx = -Dx;  
			stepx = -1; 
		} 
		else
		{
			stepx = 1;
		}
		plot(x, y);
		
		/* se cicla hasta llegar al extremo de la línea */
		if(Dx>Dy)
		{
			p = 2*Dy - Dx;
			incE = 2*Dy;
			incNE = 2*(Dy-Dx);
			while (x != x_dest)
			{
				x = x + stepx;
				if (p < 0)
				{
					p = p + incE;
				}
				else 
				{
					y = y + stepy;
					p = p + incNE;
				}
				plot(x, y);
			}
		}
		else
		{
			p = 2*Dx - Dy;
			incE = 2*Dx;
			incNE = 2*(Dx-Dy);
			while (y != y_dest)
			{
				y = y + stepy;
				if (p < 0)
				{
					p = p + incE;
				}
				else 
				{
					x = x + stepx;
					p = p + incNE;
				}
				plot(x, y);
			}
		}
		tortuga.setPosicion(new Punto(x, y));
		agregarComando("avanzar", q);
	}

	private void plot(int x, int y)
	{
		//tope sera el desplazamiento en x e y
		//si el ancho del pincel es mayor a 2, el tope sera la mitad del ancho del pincel
		int tope = (pincel.getAncho() > 2) ? pincel.getAncho() /2 : pincel.getAncho();		

		for (int i = (y - tope); i <= y + tope; i++)
		{
			for (int j = (x - tope); j <= x + tope; j++)
			{
				if(!fueraDeRango(i, j))
				{
					this.dibujo.setPixel(i, j, pincel.getColor());
					this.dibujo.setFlags(i, j, true);;						
				}
				else
				{
					return;
				}
			}
		}			
	}
	public void borrar(int q)
	{
		int x0 = this.tortuga.getPosicion().getX();
		int y0 = this.tortuga.getPosicion().getY();
		
		int x_dest = limitar((int) Math.round(x0 + q * Math.cos(Math.toRadians(this.tortuga.getAngulo()))));
		int y_dest = limitar((int) Math.round(y0 + q * Math.sin(Math.toRadians(this.tortuga.getAngulo()))));
		
		int Dx = x_dest - x0;
		int Dy = y_dest - y0;
		
		int y = y0;
		int x = x0;
		int p, incE, incNE, stepx, stepy;

		/* determinar que punto usar para empezar, cual para terminar */
		if (Dy < 0) 
		{ 
			Dy = -Dy; 
			stepy = -1; 
		} 
		else 
		{
			stepy = 1;
		}

		if (Dx < 0)
		{  
			Dx = -Dx;  
			stepx = -1; 
		} 
		else
		{
			stepx = 1;
		}
		erase(x, y);
		
		/* se cicla hasta llegar al extremo de la línea */
		if(Dx>Dy)
		{
			p = 2*Dy - Dx;
			incE = 2*Dy;
			incNE = 2*(Dy-Dx);
			while (x != x_dest)
			{
				x = x + stepx;
				if (p < 0)
				{
					p = p + incE;
				}
				else 
				{
					y = y + stepy;
					p = p + incNE;
				}
				erase(x, y);
			}
		}
		else
		{
			p = 2*Dx - Dy;
			incE = 2*Dx;
			incNE = 2*(Dx-Dy);
			while (y != y_dest)
			{
				y = y + stepy;
				if (p < 0)
				{
					p = p + incE;
				}
				else 
				{
					x = x + stepx;
					p = p + incNE;
				}
				erase(x, y);
			}
		}
		tortuga.setPosicion(new Punto(x, y));
		agregarComando("borrar", q);
	}
	
	private void erase(int x, int y)
	{
		//tope sera el desplazamiento en x e y
		//si el ancho del pincel es mayor a 2, el tope sera la mitad del ancho del pincel
		int tope = (pincel.getAncho() > 2) ? pincel.getAncho() /2 : pincel.getAncho();
		
		for (int i = limitar((y - tope)); i <= y + tope; i++)
		{
			for (int j = limitar((x - tope)); j <= x + tope; j++)
			{
				if(this.dibujo.getFlags(i, j))
				{
					this.dibujo.setFlags(i, j, false);;
					this.dibujo.setPixel(i, j, fondo.getYX(i, j));					
				}
			}
		}
	}
	private int limitar(int n)
	{
		n = (n < 0) ? 0 : n;
		n = (n > 511) ? 511 : n;
		return n;
	}
	
	public void girar(String alpha) throws Exception//puede saltar una Exception
	{
		if (alpha.equals("r") || alpha.equals("R"))
		{
			this.getTortuga().setAngulo(0);
		}
		else
		{
			int nuevoAngulo = Integer.parseInt(alpha);
			nuevoAngulo = nuevoAngulo + tortuga.getAngulo();
			while(nuevoAngulo >= 360)
				nuevoAngulo = nuevoAngulo - 360;
			this.tortuga.setAngulo(nuevoAngulo);
		}
	}
	
	public void ubicar(String coordenadas)
	{
			String[] valores = coordenadas.split("\\D");
			int pos = 0;
			int[]X_Y = new int[2];
			for (int i = 0; i < valores.length; i++)
			{
				try
				{
					X_Y[pos] = Integer.parseInt(valores[i]);
					pos++;
				}
				catch(NumberFormatException nfe){}
			}
			Comando comando = new Comando();
			comando.setNombre("ubicar");
			comando.setParametros(coordenadas);
			tortuga.setPosicion(new Punto(X_Y[0], X_Y[1]));
	}
	
	private void agregarComando(String c, int q)
	{
		//Historial historial = new Historial();
		Comando comando = new Comando();
		comando.setNombre(c);
		comando.setParametros(Integer.toString(q));
		historial.setComandos(comando);
	}
	
	public void pintarRecursivo()
	{
		int x = tortuga.getPosicion().getX();
		int y = tortuga.getPosicion().getY();
		Pixel color_orig = dibujo.getYX(x, y);
		Pixel color_pincel = pincel.getColor();
		
		if(!color_orig.esIgual(color_pincel))
		{
			pintarRecursivo(x, y, color_orig, color_pincel);			
		}

		Comando pintar = new Comando();
		pintar.setNombre("pintar");
		historial.setComandos(pintar);
	}
	private void pintarRecursivo(int x, int y, Pixel color_orig, Pixel color_pincel)
	{
		Pixel color_aca = dibujo.getYX(y, x);
		
		if(!color_aca.esIgual(color_orig) || color_aca.esIgual(color_pincel))
		{
			return;
		}
		
		this.dibujo.setPixel(y, x, color_pincel);
		dibujo.setFlags(y, x, true);

		if(!fueraDeRango(x + 1, y)) pintarRecursivo(x + 1, y, color_orig, color_pincel);//derecha				
		if(!fueraDeRango(x, y - 1)) pintarRecursivo(x, y - 1, color_orig, color_pincel);//arriba	
		if(!fueraDeRango(x - 1, y)) pintarRecursivo(x - 1, y, color_orig, color_pincel);//izquierda									
		if(!fueraDeRango(x, y + 1)) pintarRecursivo(x, y + 1, color_orig, color_pincel);//abajo	
	}
	
	public void pintarIterativo()
	{
		Pixel color_orig = dibujo.getYX(tortuga.getPosicion().getX(), tortuga.getPosicion().getY());
		Pixel color_pincel = pincel.getColor();
		
		if(color_orig.esIgual(color_pincel))
		{
			return;
		}
		
		Punto actual = tortuga.getPosicion();
		ArrayList<Punto> pila = new ArrayList<Punto>();
		pila.add(actual);

		while (!pila.isEmpty())
		{
			actual = pila.remove(0);
			int x = actual.getX();
			int y = actual.getY();
			dibujo.setPixel(y, x, color_pincel);
			dibujo.setFlags(y, x, true);

			//arriba
			if(!contiene(new Punto(x, limitar(y + 1)), pila))
			{
				Pixel color_arriba = dibujo.getYX(limitar(y + 1), x);
				if(color_arriba.esIgual(color_orig)) 
				{
					pila.add(new Punto(x, limitar(y + 1)));
				}
			}				

			//abajo
			if(!contiene(new Punto(x, limitar(y - 1)), pila))
			{
				Pixel color_abajo = dibujo.getYX(limitar(y - 1), x);
				if(color_abajo.esIgual(color_orig)) 
				{
					pila.add(new Punto(x, limitar(y - 1)));						
				}
			}

			//izquierda
			if(!contiene(new Punto(limitar(x - 1), y), pila))
			{
				Pixel color_izquierda = dibujo.getYX(y, limitar(x - 1));
				if(color_izquierda.esIgual(color_orig))
				{
					pila.add(new Punto(limitar(x - 1), y));						
				}
			}

			//derecha
			if(!contiene(new Punto(limitar(x + 1), y), pila))
			{
				Pixel color_derecha = dibujo.getYX(y, limitar(x + 1));
				if(color_derecha.esIgual(color_orig))
				{
					pila.add(new Punto(limitar(x + 1), y));						
				}
			}
		}
	}
	public boolean contiene(Punto punto, ArrayList<Punto> pila)
	{
		boolean encontrado = false;
		for (int i = 0; i < pila.size() && !encontrado; i++)
		{
			if(pila.get(i).esIgual(punto))
			{
				encontrado = true;
			}
		}
		return encontrado;
	}

	private boolean fueraDeRango(int x, int y)
	{
		boolean retorno =  false;
		if(x > 511 || y > 511 && !retorno)
		{
			retorno = true;
		}
		if(x < 0 || y < 0 && !retorno)
		{
			retorno = true;
		}
		
		return retorno;
	}
	
	public void pincel(int q)
	{
		this.pincel.setAncho(q);
		Comando comando = new Comando();
		comando.setNombre("pincel");
		comando.setParametros(Integer.toString(q));
		this.historial.setComandos(comando);
	}
	public void colorPincel(int r, int g, int b) 
	{
		Pixel nuevoColor = new Pixel(r, g, b);
		this.pincel.setColor(nuevoColor);
	}
	public void fondo(String direccion) throws Exception
	{
		FileInputStream fi = null;
		DataInputStream di = null;
		try
		{
			fi = new FileInputStream(direccion);
			di = new DataInputStream(fi);
		} 
		catch (FileNotFoundException NoEncontrado){}
		try
		{
			char b = (char) di.readByte();
			char m = (char) di.readByte();
			if (b != 'B' && m != 'M')
			{
				throw new Exception("");
			}
			else
			{
				// tamaño del archivo
				readInt(di);
				//reservado
				this.readShort(di);
				this.readShort(di);
				//offsetPixel
				readInt(di); 
				//tamaño de la cabezera del bitmap
				readInt(di);
				//ancho imagen
				int ancho = this.readInt(di); 
				//alto imagen
				int alto = this.readInt(di); 
				//plano de colores
				readShort(di); 
				//bytes por pixel
				readShort(di); 
				//compresion
				readInt(di); 
				//tamaño de la imagen
				readInt(di);
				//no hacer nada
				readInt(di);
				readInt(di);
				readInt(di);
				readInt(di);
				
				//cargar imagen al reves
				//if (ancho > fondo.getAncho()) ancho = fondo.getAncho();
				//if (alto > fondo.getAlto()) alto = fondo.getAlto();
			
				int padding = ancho % 4;
				
				for (int i = alto - 1; i >= 0; i--) 
				{
					for (int j = 0; j < ancho; j++) 
					{
						int B = readColor(di);
						int G = readColor(di);
						int R = readColor(di);
						fondo.setPixel(i, j, (new Pixel(R, G, B)));
					}
					if(padding != 0)
					{
						for (int j = 0; j < padding; j++)
						{
							di.readByte();
						}
					}
				}
			}
		}catch (Exception e)
		{
			throw new Exception("");
		}
	}
	private static int readColor(DataInputStream di) throws IOException
	{
		byte b = di.readByte();
		int color = b & 0xff;
		return color;
	}
	private int readInt(DataInputStream di) throws IOException
	{
		byte[] b = new byte[4];
		di.read(b);
		int e = ((b[3] & 0xff) << 24) | ((b[2] & 0xff) << 16) | ((b[1] & 0xff) << 8) | b[0] & 0xff;
		return e;
	}
	private short readShort(DataInputStream di) throws IOException
	{
		byte[] b = new byte[2];
		di.read(b);
		short s = (short) (((b[1] & 0xFF) << 8 ) | b[0] & 0xFF);
		return s;
	}//...

	public void nuevo()
	{
		fondo = new Imagen(512,512);
		dibujo = new Imagen(512,512);
		fondo.fondoBlanco();
		dibujo.fondoBlanco();
		dibujo.inicializarFlags();
		tortuga.setPosicion(new Punto(256, 256));
		tortuga.setAngulo(90);
	}

	public void nuevo(String direccion) throws Exception
	{
		dibujo = new Imagen(512,512);
		dibujo.inicializarFlags();
		FileInputStream fi = null;
		DataInputStream di = null;

		try
		{
			fi = new FileInputStream(direccion);
			di = new DataInputStream(fi);
		} 
		catch (FileNotFoundException NoEncontrado)
		{
			
		}
		try
		{
			char b = (char) di.readByte();
			char m = (char) di.readByte();
			if (b != 'B' && m != 'M')
			{
				throw new Exception("");
			}
			else
			{
				// tamaño del archivo
				readInt(di);
				//reservado
				readShort(di);
				readShort(di);
				//offsetPixel
				readInt(di); 
				//tamaño de la cabezera del bitmap
				readInt(di);
				//ancho imagen
				int ancho = this.readInt(di); 
				//alto imagen
				int alto = this.readInt(di); 
				//plano de colores
				readShort(di); 
				//bytes por pixel
				 readShort(di); 
				//compresion
				readInt(di); 
				//tamaño de la imagen
				readInt(di);
				//no hacer nada
				this.readInt(di);
				this.readInt(di);
				this.readInt(di);
				this.readInt(di);
				//cargar imagen al reves
				if (ancho > fondo.getAncho())
					ancho = fondo.getAncho();
				if (alto > fondo.getAlto())
					alto = fondo.getAlto();
				int padding = ancho%4;
				for (int i = alto - 1; i >= 0; i--) 
				{
					for (int j = 0; j < ancho; j++) 
					{
						int B = di.readByte() & 0xff;
						int G = di.readByte() & 0xff;
						int R = di.readByte() & 0xff;
						this.fondo.setPixel(i, j, (new Pixel(R, G, B)));
					}
					if (padding != 0)
					{
						for (int j = 0; j < padding; j++)
						{
							di.readByte();
						}
					}
				}
			}
		}catch (Exception e)
		{
			throw new Exception("");
		}
	}
	
	public void dibujar(BufferedImage imagen, String nombre) throws Exception
	{
		FileOutputStream fo = null;
		try 
		{
			fo = new FileOutputStream (nombre + ".bmp");
		}
		catch (FileNotFoundException e) {}
		try 
		{
		   //MapBitFileHeader
		   //caracteres del header
		   char b = 'B';
		   char m = 'M';
		   fo.write((byte)b);
		   fo.write((byte)m);
		   int tamanoArchivo = imagen.getHeight() * imagen.getWidth() * 3 + imagen.getHeight() * (imagen.getWidth() % 4) + 54;
		   fo.write(writeInt(tamanoArchivo));
		   short reservado = 0;
		   fo.write(writeShort(reservado));
		   fo.write(writeShort(reservado));
		   int offset = 54;
		   fo.write(writeInt(offset));
		   fo.write(writeInt(40));
		   fo.write(writeInt(imagen.getWidth()));
		   fo.write(writeInt(imagen.getHeight()));
		   short planoColores = 1;
		   fo.write(writeShort(planoColores));
		   short colorDepth = 24;
		   fo.write(writeShort(colorDepth));
		   fo.write(writeInt(0));
		   int tamanoImagen = imagen.getHeight() * imagen.getWidth() * 3 + imagen.getHeight() + (imagen.getWidth() % 4);
		   fo.write(writeInt(tamanoImagen));
		   fo.write(writeInt(0));
		   fo.write(writeInt(0));
		   fo.write(writeInt(0));
		   fo.write(writeInt(0));
		   
		   int basura = imagen.getWidth() % 4; //variable para el padding
		   for (int i = 0; i < imagen.getHeight(); i++) 
			{
				for (int j = 0; j < imagen.getWidth(); j++) 
				{
					fo.write((byte)(char) imagen.getColorModel().getBlue(imagen.getRGB(j, i)));
					fo.write((byte)(char) imagen.getColorModel().getGreen(imagen.getRGB(j, i)));
					fo.write((byte)(char) imagen.getColorModel().getRed(imagen.getRGB(j, i)));
				}
				if (basura != 0)
				{
					for (int j = 0; j < basura; j++)
					{
						fo.write((byte)(char)0);
					}
				}
			}
		   fo.close();//se cierra el archivo
		} 
		catch (Exception e)
		{
			
		}
	}
	private byte [] writeInt (int parValue) 
	{
		byte retValue [] = new byte [4];
		retValue [0] = (byte) (parValue & 0x00FF);
		retValue [1] = (byte) ((parValue >> 8) & 0x000000FF);
		retValue [2] = (byte) ((parValue >> 16) & 0x000000FF);
		retValue [3] = (byte) ((parValue >> 24) & 0x000000FF);
		return (retValue);
	}
	private byte [] writeShort (short parValue) 
	{
		byte retValue [] = new byte [2];
		retValue [0] = (byte) (parValue & 0x00FF);
		retValue [1] = (byte) ((parValue >> 8) & 0x00FF);
		return (retValue);		
	}
	
	// Setters & Getters
	public Tortuga getTortuga() 
	{
		return tortuga;
	}
	public void setTortuga(Tortuga tortuga)
	{
		this.tortuga = tortuga;
	}
	public Pincel getPincel() 
	{
		return pincel;
	}
	public void setPincel(Pincel pincel)
	{
		this.pincel = pincel;
	}
	public Imagen getFondo()
	{
		return fondo;
	}
	public void setFondo(Imagen fondo) 
	{
		this.fondo = fondo;
	}
	public Imagen getDibujo()
{
		return dibujo;
	}
	public void setDibujo(Imagen dibujo) 
	{
		this.dibujo = dibujo;
	}
}
