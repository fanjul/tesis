package gui;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.joda.time.LocalDate;

import properties.Parameters;
import properties.PropertyManager;
import baseDatos.DBIndicador;
import baseDatos.DBValorIndicador;
import baseDatos.Item;


public class ManejadorArchivos extends JFrame{

	private static final long serialVersionUID = 1L;
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

	public ManejadorArchivos( LocalDate fechaInicio, LocalDate fechaFin, JComboBox comboBoxSeleccionarIndicador, JTextField txtFechaInicio, JTextField txtFechaFin, JComboBox comboBoxPeriodo){
		
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.comboBoxSeleccionarIndicador = comboBoxSeleccionarIndicador;
		this.txtFechaInicio = txtFechaInicio;
		this.txtFechaFin = txtFechaFin;
		this.comboBoxPeriodo = comboBoxPeriodo;
	}
		//TODO separar en clases el abrir arch y el abrir bd? que extiendan de otra?
	@SuppressWarnings("unchecked")
	public void abrirBaseDeDatos(){
		JFileChooser seleccion = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.mdb , *.accdb", "mdb", "accdb");
		seleccion.setFileFilter(filter);
		seleccion.setCurrentDirectory(new File("./"));
		if (seleccion.showOpenDialog(VentanaPrincipal.getInstance()) == JFileChooser.APPROVE_OPTION) {}
		comboBoxSeleccionarIndicador.setModel(new DefaultComboBoxModel<>(
					new String[] { "Cargando datos..." }));
		cargarDatos(seleccion.getSelectedFile());

		
	}
	
	public void abrirArchivo(){
		
		JFileChooser seleccion = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos", "java", "txt");
		seleccion.setFileFilter(filter);
		seleccion.setCurrentDirectory(new File("./"));
		if (seleccion.showOpenDialog(VentanaPrincipal.getInstance()) == JFileChooser.APPROVE_OPTION) {}
		//	comboBox.setModel(new DefaultComboBoxModel<>(
		//			new String[] { "Cargando datos..." }));
		//	cargarDatos(seleccion.getSelectedFile());

		
	}

	
///////////////////////////////////////**********************************////////////////////////////////////////////	
	
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
	
		
	}
}
