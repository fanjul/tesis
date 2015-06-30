package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.joda.time.LocalDate;

import properties.Parameters;
import properties.PropertyManager;
import baseDatos.DBIndicador;
import baseDatos.DBValorIndicador;
import baseDatos.Item;
import baseDatos.Periodo;

import java.awt.Toolkit;


//TODO hay que arreglar cuando se toca "Archivo" o "Edicion"

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {
	
	private static VentanaPrincipal instance = null;
	private static final String NOMBRE = "Herramienta Tesis";
	
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem mntmAbrirBaseDeDatos;
	private JMenuItem mntmAbrirArchivo;
	private JMenu mnArchivo;
	private JTabbedPane pestanias;
	private GridBagConstraints gbc;
	private JPanel pestaniaInicio; 
	
	//Componentes
	private JLabel lblSeleccionarIndicador;
	private JComboBox comboBoxSeleccionarIndicador;
	private JLabel lblSeleccionarMetodoMatematico;
	private JComboBox comboBoxSeleccionarMetodoMatematico; 
	private JButton btnNuevoMetodoMatematico;
	private JButton btnCalcularResultados;
	private JButton btnVerResultadosPrevios;
	private JLabel lblFechaInicioPeriodo;
	private JTextField txtFechaInicio;
	private JLabel lblFechaFinPeriodo;
	private JTextField txtFechaFin;
	private JLabel lblPeriodoMuestras;
	private JComboBox comboBoxPeriodo;
	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	
	public static VentanaPrincipal getInstance(){
		if(instance == null){
			instance = new VentanaPrincipal();
		}
		return instance;
	}

	private void inicializarVariables(){
		mntmAbrirBaseDeDatos = new JMenuItem("Abrir Base de Datos");
		mnArchivo = new JMenu("Archivo");
		menuBar = new JMenuBar();
		contentPane = new JPanel(new GridBagLayout());
		pestanias = new JTabbedPane(JTabbedPane.TOP);
		gbc = new GridBagConstraints();
		pestaniaInicio = new JPanel();
		
		lblSeleccionarIndicador = new JLabel("Seleccionar indicador");
		comboBoxSeleccionarIndicador = new JComboBox();
		lblSeleccionarMetodoMatematico = new JLabel("Seleccionar m\u00E9todo matem\u00E1tico");
		comboBoxSeleccionarMetodoMatematico = new JComboBox();
		btnNuevoMetodoMatematico = new JButton("Nuevo m\u00E9todo matem\u00E1tico");
		btnCalcularResultados = new JButton("Ver resultados previos");
		btnVerResultadosPrevios = new JButton("Ver resultados previos");
		lblFechaInicioPeriodo = new JLabel();
		txtFechaInicio = new JTextField();
		lblFechaFinPeriodo = new JLabel();
		txtFechaFin = new JTextField();
		lblPeriodoMuestras = new JLabel();
		comboBoxPeriodo = new JComboBox();
	}
	
	
	private void layoutComboBox(){
		{//Agrega label Seleccionar indicador
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 1 ;
			gbc.gridwidth = 2;
			gbc.ipadx = 100;
			gbc.insets = new Insets(75, 10, 20, 10);
			pestaniaInicio.add(lblSeleccionarIndicador,gbc);
			gbc.ipadx = 0;
		}
		
		{//Agrega ComboBoxSeleccionarIndicador
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			gbc.gridheight = 1;
			gbc.insets = new Insets(75, 0, 20, 10);
			gbc.ipadx = 100;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			pestaniaInicio.add(comboBoxSeleccionarIndicador,gbc);
			gbc.fill = GridBagConstraints.NONE;
			gbc.ipadx = 0;
		}
		
		{//Agrega Label Seleccionar Metodo Matematico
			gbc.gridx = 0 ;
			gbc.gridy = 1;	
			gbc.gridheight = 1 ;
			gbc.gridwidth = 2;
			gbc.ipadx = 100;
			gbc.insets = new Insets(75, 10, 20, 60);
			pestaniaInicio.add(lblSeleccionarMetodoMatematico,gbc);
			gbc.ipadx = 0;
		}
		
		{//Agrega comboBoxSeleccionarMetodoMatematico
			gbc.gridx = 1;
			gbc.gridy = 1;		
			gbc.gridwidth = 2;
			gbc.gridheight = 1;
			gbc.insets = new Insets(75, 0, 20, 10);
			gbc.ipady = 2;
			gbc.ipadx = 100;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			pestaniaInicio.add(comboBoxSeleccionarMetodoMatematico,gbc);
			gbc.ipady = 0;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.NONE;
		}
		
		{//Agrega Boton Nuevo Metodo Matematico
			btnNuevoMetodoMatematico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PestaniaNuevoMetodoMatematico(pestanias); 
				}
			}); 
			gbc.gridx = 5;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.gridheight = 1;
			gbc.insets = new Insets(60, 0, 20, 20);
			btnNuevoMetodoMatematico.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/nuevoMetodo.png")));
			pestaniaInicio.add(btnNuevoMetodoMatematico,gbc);
		}
		
		{//Agrega Label Fecha inicio
			lblFechaInicioPeriodo.setText("Fecha inicio periodo:");
			gbc.gridx = 0 ;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			gbc.insets = new Insets(50, 50, 76, 25);
			gbc.ipadx = 100;
			gbc.anchor = GridBagConstraints.CENTER;
			pestaniaInicio.add(lblFechaInicioPeriodo,gbc);
			gbc.ipadx = 0;
		}
		
		{//Agrega Area de Texto fecha inicio		
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.gridheight = 1 ;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, -160, 76, 12);
			//gbc.weightx = 1.0;
			gbc.ipadx = 100;
			pestaniaInicio.add(txtFechaInicio,gbc);
		//	gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}
		
		{//Agrega Label Fecha Fin
			lblFechaFinPeriodo.setText("Fecha fin periodo:");
			gbc.gridx = 2 ;
			gbc.gridy = 3;
			gbc.gridheight = 1 ;
			gbc.gridwidth = 1 ;
		//	gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(50, -35, 76, -90);
		//	gbc.weightx = 1.0;
			gbc.ipadx = 100;
			pestaniaInicio.add(lblFechaFinPeriodo,gbc);
			//gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}
		
		{//Agrega Area de Texto fecha fin	
			gbc.gridx = 3;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
		//	gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, -70, 76, 12);
		//	gbc.weightx = 1.0;
			gbc.ipadx = 100;
			pestaniaInicio.add(txtFechaFin, gbc);
		//	gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}
		
		{//Agrega Label Periodo
			lblPeriodoMuestras.setText("Periodo:");
			gbc.gridx = 4 ;
			gbc.gridy = 3;
			gbc.gridheight = 1 ;
			gbc.gridwidth = 1 ;
		//	gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, 10, 76, 24);
		//	gbc.weightx = 1.0;
			gbc.ipadx = 100;
			pestaniaInicio.add(lblPeriodoMuestras,gbc);
		//	gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}
		
		{//Agrega ComboBox Periodo
			comboBoxPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
			comboBoxPeriodo.setEnabled(false);
			comboBoxPeriodo.setEditable(true);
			comboBoxPeriodo.setSelectedIndex(2);
			gbc.gridx = 5 ;
			gbc.gridy = 3;
			gbc.gridheight = 1 ;
			gbc.gridwidth = 1 ;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, -110, 76, 24);
		//	gbc.weightx = 1.0;
			gbc.ipadx = 30;
			pestaniaInicio.add(comboBoxPeriodo,gbc);
		//	gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}
	
		{//Agrega Boton Calcular resultados
			btnCalcularResultados = new JButton("Calcular resultados");
			btnCalcularResultados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PestaniaResultado(pestanias);
				}
			});
			gbc.gridx = 5;
			gbc.gridy = 3;
			gbc.insets = new Insets(200, 0, 50, 0);		
			btnCalcularResultados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/calcularResultados.png")));	
			pestaniaInicio.add(btnCalcularResultados,gbc);
		}
		
		{//Agrega Boton Resultados previos
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.insets = new Insets(200, 100, 50, 60);
			btnVerResultadosPrevios.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/resultadosPrevios.png")));
			pestaniaInicio.add(btnVerResultadosPrevios,gbc);
		}
		
	}

	
	private VentanaPrincipal(){
		super(NOMBRE);
		
		inicializarVariables();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Imagenes/IconoHerramienta.jpg")));
		setAlwaysOnTop(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 768, 409);

		getContentPane().add(contentPane,BorderLayout.PAGE_START);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		getContentPane().add(pestanias,BorderLayout.PAGE_START);
		
		pestanias.add(pestaniaInicio);
		pestaniaInicio.setLayout(new GridBagLayout());
		
		this.layoutComboBox();
		
		{//Menu Archivo
			setJMenuBar(menuBar);
			menuBar.add(mnArchivo);
			
			//abrir BD
			mntmAbrirBaseDeDatos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/abrirBaseDeDatos2.png")));		
			mntmAbrirBaseDeDatos.addActionListener(new ActionListener() {
					//Abre el dialogo para abrir una BD
					public void actionPerformed(ActionEvent e) {
						ManejadorArchivos manejadorArchivos = new ManejadorArchivos(fechaInicio,fechaFin,comboBoxSeleccionarIndicador,txtFechaInicio,txtFechaFin,comboBoxPeriodo);
						manejadorArchivos.abrirBaseDeDatos();
					}	
			});
			mnArchivo.add(mntmAbrirBaseDeDatos);
	/*	
			//abrir archivo txt/java
			mntmAbrirArchivo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/abrirArchivo.png")));		
			mntmAbrirArchivo.addActionListener(new ActionListener() {
					//Abre el dialogo para abrir una BD
					public void actionPerformed(ActionEvent e) {
						ManejadorArchivos manejadorArchivos = new ManejadorArchivos();
						manejadorArchivos.abrirArchivo();
					}	
			});
			mnArchivo.add(mntmAbrirArchivo);	*/
			
	
		}


	
		
		
		new ConfiguracionPestania(pestanias, "Inicio",	pestanias.getTabCount() - 1, "Pantalla inicio",false);
	}
	
	

	
	
	/*
	public void cargarDatos(File dbPath) {
		PropertyManager.instance().setProperty(Parameters.DB_NAME.toString(),
				"jdbc:ucanaccess://" + dbPath.getAbsolutePath());

		new SwingWorker<Object[], Void>() {

			@Override
			protected Object[] doInBackground() throws Exception {
				DBValorIndicador vIndicador = new DBValorIndicador();
				fechaInicio = vIndicador.getMinDate();
				fechaFin = vIndicador.getMaxDate();
				DBIndicador indicador = new DBIndicador();
				return indicador.getIndicatorNames();
			}

			@SuppressWarnings({ "unchecked" })
			@Override
			public void done() {
				try {
					comboBoxSeleccionarIndicador.setModel(new DefaultComboBoxModel<>(get()));
					AutoCompleteDecorator.decorate(comboBoxSeleccionarIndicador);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				
				// Cargar el valor de periodo del indicador
				DBIndicador indicador = new DBIndicador();
				//switch (indicador.getPeriodo(indicador.getIdByName(comboBox.getSelectedItem().toString()))) {
				switch (indicador.getPeriodo(indicador.getIdByName(((Item<String>)comboBoxSeleccionarIndicador.getSelectedItem()).getDescription()))) {
				case "diario":
					comboBoxPeriodo.setSelectedIndex(0);
					break;
				case "mensual":
					comboBoxPeriodo.setSelectedIndex(1);
					break;
				case "anual":
					comboBoxPeriodo.setSelectedIndex(2);
					break;
				}
								
				// Cambiar la fecha inicio y fin segun el indicador
				DBValorIndicador vIndicador = new DBValorIndicador();
				fechaInicio = vIndicador.getMinDate(indicador.getIdByName(((Item<String>)comboBoxSeleccionarIndicador.getSelectedItem()).getDescription()));
			//	fechaFin = vIndicador.getMaxDate(indicador.getIdByName(comboBoxSeleccionarIndicador.getSelectedItem().toString()));
				fechaFin = vIndicador.getMaxDate(indicador.getIdByName(((Item<String>)comboBoxSeleccionarIndicador.getSelectedItem()).getDescription()));
				txtFechaInicio.setText(fechaInicio.getDayOfMonth() + "/"
						+ fechaInicio.getMonthOfYear() + "/" + fechaInicio.getYear());
				txtFechaFin.setText(fechaFin.getDayOfMonth() + "/"
						+ fechaFin.getMonthOfYear() + "/" + fechaFin.getYear());
				//generarButton.setEnabled(true);
			}
		}.execute();
		;
	
		
	}	*/
}
	