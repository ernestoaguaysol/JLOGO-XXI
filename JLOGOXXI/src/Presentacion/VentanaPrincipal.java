package Presentacion;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import Logica.Imagen;
import Logica.Logo;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JToolBar;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VentanaPrincipal extends JFrame
{
	private static final long serialVersionUID = 1L;
	private Logo logo = new Logo();
	private JPanel contenedor;
	private JPanel container_lienzo;
	private JLabel imagen_resultado;
	private JPanel entrada;
	private JButton btnOk;
	private TortugaVista lblTortuga;
	private ImageIcon tortuga;
	private JScrollPane historial;
	private JTextArea textAreaHistorial;
	private JTextField entrada_comandos;
	private JPanel panel_toolBar1;
	private JPanel panel_toolBar2;
	private JToolBar toolBar1;
	private JButton btnNuevo;
	private JButton btnNuevoBMP;
	private JButton btnFondo;
	private JButton btnAyuda;
	private JToolBar toolBar2;
	private JButton btnAvanzar;
	private JButton btnGirar;
	private JButton btnBorrar;
	private JButton btnPintar;
	private JButton btnUbicar;
	private JButton btnPincel;
	private JButton btnColor;
	private JFileChooser file = new JFileChooser();
	private JLabel lblHistorial;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal()
	{	
		setTitle("LOGO XXI");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 730);

		contenedor = new JPanel();
		contenedor.setBackground(Color.GRAY);
		contenedor.setName("");
		contenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedor);
		contenedor.setLayout(null);
		
		historial = new JScrollPane();
		historial.setBackground(Color.GRAY);
		historial.setRequestFocusEnabled(false);
		historial.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		historial.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		historial.setBounds(77, 127, 191, 480);
		contenedor.add(historial);
		
		textAreaHistorial = new JTextArea();
		textAreaHistorial.setDisabledTextColor(Color.GRAY);
		textAreaHistorial.setFont(new Font("TradeGothic LT Extended", Font.PLAIN, 13));
		textAreaHistorial.setRequestFocusEnabled(false);
		historial.setViewportView(textAreaHistorial);
		textAreaHistorial.setEditable(false);
		textAreaHistorial.setDragEnabled(false);
		textAreaHistorial.setBackground(Color.LIGHT_GRAY);
		
		container_lienzo = new JPanel();
		container_lienzo.setRequestFocusEnabled(false);
		container_lienzo.setBackground(Color.WHITE);
		container_lienzo.setBounds(278, 95, 512, 512);
		container_lienzo.setLayout(null);
		contenedor.add(container_lienzo);
		
		lblTortuga = new TortugaVista();
		lblTortuga.setRequestFocusEnabled(false);
		URL url = getClass().getResource("\\imagenes\\tortuga.PNG");
		tortuga = new ImageIcon(url);
		lblTortuga.setIcon(tortuga);
		inicializarTortuga();
		container_lienzo.add(lblTortuga);
		
		imagen_resultado = new JLabel("");
		imagen_resultado.setRequestFocusEnabled(false);
		imagen_resultado.setBackground(Color.WHITE);
		imagen_resultado.setHorizontalAlignment(0);
		imagen_resultado.setVerticalAlignment(0);
		imagen_resultado.setBounds(0, 0, 512, 512);
		container_lienzo.add(imagen_resultado);
		
		entrada = new JPanel();
		entrada.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		entrada.setBackground(Color.DARK_GRAY);
		entrada.setBounds(77, 618, 713, 73);
		contenedor.add(entrada);
		entrada.setLayout(null);		
		
		btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ejecutar(entrada_comandos.getText());			
			}
		});

		entrada_comandos = new JTextField();
		entrada_comandos.requestFocus();
		entrada_comandos.requestFocusInWindow();
		entrada_comandos.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent arg0)
			{
				char tecla = arg0.getKeyChar();
				
				if(tecla == KeyEvent.VK_ENTER)
				{
					btnOk.doClick();
				}
			}
		});
		entrada_comandos.setToolTipText("Escriba \"Ayuda\", para ver una lista de comandos");
		entrada_comandos.setBounds(212, 27, 297, 20);
		entrada_comandos.setColumns(10);
		entrada.add(entrada_comandos);
		btnOk.setBounds(519, 26, 101, 23);
		entrada.add(btnOk);
		
		JLabel lblIngreseUnComando = new JLabel("Ingrese un comando");
		lblIngreseUnComando.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblIngreseUnComando.setForeground(Color.LIGHT_GRAY);
		lblIngreseUnComando.setBounds(60, 27, 142, 20);
		entrada.add(lblIngreseUnComando);
		
		panel_toolBar1 = new JPanel();
		panel_toolBar1.setBackground(Color.GRAY);
		panel_toolBar1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_toolBar1.setOpaque(false);
		panel_toolBar1.setBounds(77, 11, 250, 73);
		contenedor.add(panel_toolBar1);
		
		toolBar1 = new JToolBar();
		toolBar1.setOpaque(false);
		toolBar1.setFloatable(false);
		toolBar1.setBounds(10, 10, 210, 50);
		panel_toolBar1.add(toolBar1);
		
		btnNuevo = instanciarBoton("Nuevo", "\\imagenes\\Nuevo_ico.png");
		btnNuevo.setToolTipText("Nuevo");
		btnNuevoBMP = instanciarBoton("NuevoBMP", "\\imagenes\\NuevoBMP_ico.png");
		btnNuevoBMP.setToolTipText("NuevoBMP");
		btnAyuda = instanciarBoton("Ayuda", "\\imagenes\\Ayuda_ico.png");
		btnAyuda.setToolTipText("Ayuda");
		btnFondo = instanciarBoton("Fondo", "\\imagenes\\Fondo_ico.png");
		btnFondo.setToolTipText("Fondo");
		
		toolBar1.add(btnNuevo);
		toolBar1.add(btnNuevoBMP);
		toolBar1.add(btnFondo);
		toolBar1.add(btnAyuda);
		
		panel_toolBar2 = new JPanel();
		panel_toolBar2.setOpaque(false);
		panel_toolBar2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_toolBar2.setBackground(Color.GRAY);
		panel_toolBar2.setBounds(390, 11, 400, 73);
		contenedor.add(panel_toolBar2);
		
		toolBar2 = new JToolBar();
		toolBar2.setOpaque(false);
		toolBar2.setOrientation(SwingConstants.HORIZONTAL);
		toolBar2.setBounds(10, 10, 365, 50);
		toolBar2.setFloatable(false);
		panel_toolBar2.add(toolBar2);
		
		btnAvanzar = instanciarBoton("Avanzar", "\\imagenes\\Avanzar_ico.png");
		btnAvanzar.setToolTipText("Avanzar");
		btnGirar = instanciarBoton("Girar", "\\imagenes\\Girar_ico.png");
		btnGirar.setToolTipText("Girar");
		btnPintar = instanciarBoton("Pintar", "\\imagenes\\Pintar_ico.png");
		btnPintar.setToolTipText("Pintar");
		btnBorrar = instanciarBoton("Borrar", "\\imagenes\\Borrar_ico.png");
		btnBorrar.setToolTipText("Borrar");
		btnUbicar = instanciarBoton("Ubicar", "\\imagenes\\Ubicar_ico.png");
		btnUbicar.setToolTipText("Ubicar");
		btnPincel = instanciarBoton("Pincel", "\\imagenes\\Pincel_ico.png");
		btnPincel.setToolTipText("Pincel");
		btnColor = instanciarBoton("Color", "\\imagenes\\Color_ico.png");
		btnColor.setToolTipText("Color");
		
		toolBar2.add(btnAvanzar);
		toolBar2.add(btnGirar);
		toolBar2.add(btnBorrar);
		toolBar2.add(btnPintar);
		toolBar2.add(btnUbicar);
		toolBar2.add(btnPincel);
		toolBar2.add(btnColor);
		
		lblHistorial = new JLabel("Historial");
		lblHistorial.setBackground(Color.GRAY);
		lblHistorial.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHistorial.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		lblHistorial.setForeground(Color.WHITE);
		lblHistorial.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorial.setBounds(77, 95, 191, 30);
		contenedor.add(lblHistorial);
		
	}				
	
	private JButton instanciarBoton(final String name, String resource_path)
	{
		JButton button = new JButton(name);
		URL url = getClass().getResource(resource_path);
		if (url != null)
		{
			button.setIcon(new ImageIcon(url));
			button.setText("");
			button.setSize(40, 40);
		}
		else
		{
			System.out.println("No se pudo cargar la imagen, no se encontra el path a: " + resource_path);
		}
		button.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ejecutar(name);			
				agregarEntradaBoton(name);
			}
		});
		return button;
	}
	
	/*ActionListener mi_accion = new ActionListener()
	{	
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ejecutar();			
		}
	};*/

	/*************************************************************************************************/
	//mostrar reslutado
	  public BufferedImage showResult()
	  {
		  Imagen imagen = logo.getDibujo();
		  Imagen fondo = logo.getFondo();
		  BufferedImage buff_image = this.transformToBufferedImage(imagen, fondo);
		  ImageIcon image_icon = new ImageIcon(buff_image);
	    
		  this.imagen_resultado.setText("");
		  this.imagen_resultado.setIcon(image_icon);
		  return buff_image;
	  }
	  
	  private BufferedImage transformToBufferedImage(Imagen imagen, Imagen fondo)
	  {
	    BufferedImage image = new BufferedImage(imagen.getAncho(), imagen.getAlto(), 2);
	    for (int i = 0; i < imagen.getAlto(); i++) 
	    {
	    	for (int j = 0; j < imagen.getAncho(); j++)
	    	{
	    		if(logo.getDibujo().getFlags(i, j))
	    		{
	    			Color c = new Color(imagen.getYX(i, j).getR(), imagen.getYX(i, j).getG(), imagen.getYX(i, j).getB());
	    			image.setRGB(j, imagen.getAlto() - i - 1, c.getRGB());	    			
	    		}
	    		if(!logo.getDibujo().getFlags(i, j))
	    		{
	    			Color c = new Color(fondo.getYX(i, j).getR(), fondo.getYX(i, j).getG(), fondo.getYX(i, j).getB());
	    			image.setRGB(j, i, c.getRGB());	    			
	    		}
	    	}
	    }
	    return image;
	  }
	  
	  public void showErrorMessage(String message)
	  {
	    JOptionPane.showMessageDialog(this.contenedor, message, "¡Error!", 0);
	  }
	  public void showInformationMessage(String message)
	  {
	    JOptionPane.showMessageDialog(this.contenedor, message, "Información", 1);
	  }
	  /**
	 * @throws Exception *****************************************************************************************************************/
	  
	  private void parseComando(String comando) throws Exception
	  {
		  String[] arraycomando = comando.split(" ");
		  if(arraycomando[0].equalsIgnoreCase("Avanzar"))
		  {
			  try
			  {
				  if(arraycomando.length != 1)
				  {
					  int q = Integer.parseInt(arraycomando[1]);
					  logo.avanzar(q);					  
				  }
				  else
				  {
					  String coordenadas = JOptionPane.showInputDialog("Ingrese un numero entero: ", "");
					  logo.avanzar(Integer.parseInt(coordenadas));
				  }
			  }
			  catch(NumberFormatException nfe)
			  {
				  showErrorMessage("Solo numeros enteros");
			  }
		  }
		  if(arraycomando[0].equalsIgnoreCase("Color"))
		  {
			  try
			  {
				  Color color  =JColorChooser.showDialog(null, "Seleccione un color de la paleta", Color.gray);

				  int r = color.getRed();
				  int g = color.getGreen();
				  int b = color.getBlue();
				  logo.colorPincel(r, g, b);
			  }
			  catch(Exception e)
			  {
				  showErrorMessage("Parametros no validos");
			  }
		  }
		  if(arraycomando[0].equalsIgnoreCase("Girar"))
		  {
			  try
			  {
				  if(arraycomando.length != 1)
				  {
					  String alpha = arraycomando[1];
					  logo.girar(alpha);					  
				  }
				  else
				  {
					  String coordenadas = JOptionPane.showInputDialog("Ingrese cantidad: ", "");
					  logo.girar(coordenadas);
				  }
			  }
			  catch(Exception e)
			  {
				  showErrorMessage("Solo numeros enteros o r, para reset a 0");
			  }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Ubicar"))
		  {
			  try
			  {
				  String coordenadas = JOptionPane.showInputDialog("Ingrese nuevas coordenadas (x, y)", "(x, y)");
				  logo.ubicar(coordenadas);
			  }
			  catch(NumberFormatException nfe)
			  {
				  showErrorMessage("Valores no permitidos");
			  }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Borrar"))
		  {
			  try
			  {
				  if(arraycomando.length != 1)
				  {
					  int q = Integer.parseInt(arraycomando[1]);
					  logo.borrar(q);					  
				  }
				  else
				  {
					  String coordenadas = JOptionPane.showInputDialog("Ingrese un número entero: ", "");
					  logo.borrar(Integer.parseInt(coordenadas));
				  }
			  }
			  catch(NumberFormatException nfe)
			  {
				  showErrorMessage("Solo numeros enteros");
			  }
		  }
		  
		  if(arraycomando[0].equalsIgnoreCase("Pintar"))
		  {
			  try
			  {
				  logo.pintarIterativo();
			  }
			  catch(Exception e){ }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Pincel"))
		  {
			  try
			  {
				  if(arraycomando.length != 1)
				  {
					  int q = Integer.parseInt(arraycomando[1]);
					  logo.pincel(q);
				  }
				  else
				  {
					  String coordenadas = JOptionPane.showInputDialog("Ingrese un número entero: ", "");
					  logo.pincel(Integer.parseInt(coordenadas));
				  }
			  }
			  catch(Exception e){ }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Fondo"))
		  {
			  try
			  {
				  file = new JFileChooser();
				  file.setAcceptAllFileFilterUsed(false);
				  FileNameExtensionFilter filter = new FileNameExtensionFilter("BMP Images", new String[] { "bmp" });
				  file.setFileFilter(filter);

				  int value = file.showOpenDialog(VentanaPrincipal.this.contenedor);
				  if (value == 0)
				  {
					  File abrir = file.getSelectedFile();
					  String path = abrir.getPath();
					  VentanaPrincipal.this.logo.fondo(path);
				  }
				  else
				  {
					  showInformationMessage("Se canceló la apertura de archivo");
				  }
			  }
			  catch(Exception e)
			  {
				  showErrorMessage("No se puede cargar esta imagen de fondo");
			  }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Ayuda"))
		  {
			  try
			  {
				  showInformationMessage("Lista de Comandos: \n"
						  + "Anvanzar (cantidad): mueve la tortuga hacia adelante, tanto como se le pase por parámetro\n"
						  + "Girar (angulo): Gira la tortuga, en sentido antihorario\n"
						  + "Ubicar: Aparecerá una ventana de diálogo, donde pueda ingresar las coordenadas X e Y destino\n"
						  + "Borrar: Borra los trazos hechos por la tortuga\n"
						  + "Pincel: Permite ajustar el ancho del trazo\n"
						  + "Color: Cambia el color del trazo actual\n"
						  + "Pintar: Rellena un área con el color del pincel\n"
						  + "Nuevo: Comienza un nuevo Espacio de dibujo\n"
						  + "Guardar: Guarda el trabajo realizado\n"
						  + "Fondo: Permite seleccionar una imagen BMP como fondo");
			  } 
			  catch(Exception e)
			  {
				  showErrorMessage("Error de ejecucion");
			  }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Nuevo"))
		  {
			  try
			  {
				  logo.nuevo();
			  } 
			  catch(Exception e)
			  {
				  showErrorMessage("Error de ejecucion");
			  }
		  }

		  if(arraycomando[0].equalsIgnoreCase("nuevoBMP"))
		  {
			  try
			  {  
				  file = new JFileChooser();
				  file.setAcceptAllFileFilterUsed(false);
				  FileNameExtensionFilter filter = new FileNameExtensionFilter("BMP Images", new String[] { "bmp" });
				  file.setFileFilter(filter);

				  int value = file.showOpenDialog(VentanaPrincipal.this.contenedor);
				  if (value == 0)
				  {
					  File abrir = file.getSelectedFile();
					  String path = abrir.getPath();
					  VentanaPrincipal.this.logo.nuevo(path);
				  }
				  else
				  {
					  showInformationMessage("Se canceló la apertura de archivo");
				  }

			  }
			  catch(Exception e)
			  {
				  showErrorMessage("No se puede cargar esta imagen de fondo");
			  }
		  }

		  if(arraycomando[0].equalsIgnoreCase("Dibujar"))
		  {
			  try
			  {
				  file.showSaveDialog(this);
				  File guardar = file.getSelectedFile();
				  logo.dibujar(showResult(), guardar.getAbsolutePath());
			  } 
			  catch(Exception e){}
		  }		  
	  }
	  private void agregarEntradaBoton(String nombre)
	  {
		  textAreaHistorial.append(nombre);
		  //textAreaHistorial.append(System.getProperty("line.separator"));
	  }
	  private void agregarEntrada()
	  {
		  textAreaHistorial.append(entrada_comandos.getText());
		  textAreaHistorial.append(System.getProperty("line.separator"));
	  }
	  
	  private void ejecutar(String comando)
	  {
		  try
		  {
			  this.parseComando(comando);
			  inicializarTortuga();
			  agregarEntrada();
			  entrada_comandos.setText("");
			  entrada_comandos.requestFocus();
			  imagen_resultado.setIcon(new ImageIcon(showResult()));
		  }
		  catch(Exception e){}
	  }
	  
	  
	  private void inicializarTortuga()
	  {
		  lblTortuga.setBounds(logo.getTortuga().getPosicion().getX() - (tortuga.getIconWidth() /2),
				  Math.abs(logo.getTortuga().getPosicion().getY() - 512) - (tortuga.getIconHeight() /2), 
				  tortuga.getIconWidth(), tortuga.getIconHeight());
		  lblTortuga.setAngulo(logo.getTortuga().getAngulo());
		  //tooltip tortuga
		  lblTortuga.setToolTipText("X: " + logo.getTortuga().getPosicion().getX() + " \n"
		  + "Y: " + logo.getTortuga().getPosicion().getY() + "\n" + "Angulo: " + logo.getTortuga().getAngulo() + " \n"
		  + "Ancho Pincel: " + logo.getPincel().getAncho() + " \n"
		  + "Color: " + "R: " + logo.getPincel().getColor().getR() + ", G: " + logo.getPincel().getColor().getG()
		  + ", B: " + logo.getPincel().getColor().getB());
	  }
}
