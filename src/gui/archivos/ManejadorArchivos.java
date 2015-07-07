package gui.archivos;

import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.joda.time.LocalDate;

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
