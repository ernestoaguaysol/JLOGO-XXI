package Logica;

import java.util.ArrayList;

public class Historial
{
	private ArrayList<Comando> Comandos = new ArrayList<Comando>();

	public ArrayList<Comando> getComandos()
	{
		return Comandos;
	}
	public void setComandos(Comando e)
	{
		Comandos.add(e);
	}
	public void removeComandos()
	{
		Comandos.remove(Comandos);
	}	
}
