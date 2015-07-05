package gui.archivos;

import java.io.File;
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
	public abstract void abrirArchivo();

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
					comboBoxSeleccionarIndicador
							.setModel(new DefaultComboBoxModel<>(get()));
					AutoCompleteDecorator
							.decorate(comboBoxSeleccionarIndicador);
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}

				// Cargar el valor de periodo del indicador
				DBIndicador indicador = new DBIndicador();
				// switch
				// (indicador.getPeriodo(indicador.getIdByName(comboBox.getSelectedItem().toString())))
				// {
				switch (indicador
						.getPeriodo(indicador
								.getIdByName(((Item<String>) comboBoxSeleccionarIndicador
										.getSelectedItem()).getDescription()))) {
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
				fechaInicio = vIndicador
						.getMinDate(indicador
								.getIdByName(((Item<String>) comboBoxSeleccionarIndicador
										.getSelectedItem()).getDescription()));
				// fechaFin =
				// vIndicador.getMaxDate(indicador.getIdByName(comboBoxSeleccionarIndicador.getSelectedItem().toString()));
				fechaFin = vIndicador
						.getMaxDate(indicador
								.getIdByName(((Item<String>) comboBoxSeleccionarIndicador
										.getSelectedItem()).getDescription()));
				txtFechaInicio.setText(fechaInicio.getDayOfMonth() + "/"
						+ fechaInicio.getMonthOfYear() + "/"
						+ fechaInicio.getYear());
				txtFechaFin.setText(fechaFin.getDayOfMonth() + "/"
						+ fechaFin.getMonthOfYear() + "/" + fechaFin.getYear());
				// generarButton.setEnabled(true);
			}
		}.execute();
		;

	}
}