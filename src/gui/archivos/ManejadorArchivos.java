package gui.archivos;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.joda.time.LocalDate;

import properties.Parameters;
import properties.PropertyManager;
import baseDatos.DBIndicador;
import baseDatos.DBValorIndicador;
import baseDatos.Item;

public abstract class ManejadorArchivos extends JFrame {

	private static final long serialVersionUID = -5657938366292925505L;
	protected JComboBox comboBoxSeleccionarIndicador;
	protected JLabel lblSeleccionarMetodoMatematico;
	protected JComboBox comboBoxSeleccionarMetodoMatematico;
	protected JButton btnNuevoMetodoMatematico;
	protected JButton btnCalcularResultados;
	protected JButton btnVerResultadosPrevios;
	protected JLabel lblFechaInicioPeriodo;
	protected JTextField txtFechaInicio;
	protected JLabel lblFechaFinPeriodo;
	protected JTextField txtFechaFin;
	protected JLabel lblPeriodoMuestras;
	protected JComboBox comboBoxPeriodo;
	protected LocalDate fechaInicio;
	protected LocalDate fechaFin;
	protected String ultimaRuta;

	public abstract void abrirBaseDeDatos();
	public abstract void abrirArchivo() throws IOException;

}
