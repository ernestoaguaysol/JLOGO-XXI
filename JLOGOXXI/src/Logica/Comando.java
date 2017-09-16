package Logica;

import java.util.ArrayList;

public class Comando
{
	private String nombre;
	private ArrayList<String> parametros;
	
	public Comando()
	{
		this.parametros = new ArrayList<>();
	}
	
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public ArrayList<String> getParametros()
	{
		return parametros;
	}
	public void setParametros(String e)
	{
		parametros.add(e);
	}
}
