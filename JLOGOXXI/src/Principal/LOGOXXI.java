package Principal;

import java.awt.EventQueue;

import javax.swing.UIManager;

import Presentacion.VentanaPrincipal;

public class LOGOXXI
{
	public static void main(String[] args)
	{
		try 
	    {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.GraphiteLookAndFeel");
	        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } 
	    catch(Exception e){ 
	    }
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} 
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}
