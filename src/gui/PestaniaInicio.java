package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import org.joda.time.LocalDate;

import baseDatos.Periodo;

public class PestaniaInicio extends Pestania {

	private static final long serialVersionUID = 1L;
	// Componentes
	private GridBagConstraints gbc;
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

	public PestaniaInicio() {

		super();
		this.inicializarVariables();
		this.setLayout(new GridBagLayout());
		this.setLayoutConstraints();
		
		VentanaPrincipal.getInstance().getMntmAbrirBaseDeDatos().addActionListener(new ActionListener() {
			// Abre el dialogo para abrir una BD
			public void actionPerformed(ActionEvent e) {
				ManejadorArchivos manejadorArchivos = new ManejadorArchivos(
						fechaInicio, fechaFin,
						comboBoxSeleccionarIndicador, txtFechaInicio,
						txtFechaFin, comboBoxPeriodo);
				manejadorArchivos.abrirBaseDeDatos();
			}
		});
		this.setEnableAbrirArchivo();
		this.setEnableAbrirBaseDeDatos();
	}

	@Override
	public void setEnableAbrirArchivo() {
		VentanaPrincipal.getInstance().setEnableAbrirArchivo(false);
	}

	@Override
	public void setEnableAbrirBaseDeDatos() {
		VentanaPrincipal.getInstance().setEnableAbrirBaseDeDatos(true);
	}

	private void inicializarVariables() {
		this.nombre = "Inicio";
		this.tooltip = "Ventana Principal";
		gbc = new GridBagConstraints();
		lblSeleccionarIndicador = new JLabel("Seleccionar indicador");
		comboBoxSeleccionarIndicador = new JComboBox();
		lblSeleccionarMetodoMatematico = new JLabel(
				"Seleccionar m\u00E9todo matem\u00E1tico");
		comboBoxSeleccionarMetodoMatematico = new JComboBox();
		btnNuevoMetodoMatematico = new JButton(
				"Nuevo m\u00E9todo matem\u00E1tico");
		btnCalcularResultados = new JButton("Ver resultados previos");
		btnVerResultadosPrevios = new JButton("Ver resultados previos");
		lblFechaInicioPeriodo = new JLabel();
		txtFechaInicio = new JTextField();
		lblFechaFinPeriodo = new JLabel();
		txtFechaFin = new JTextField();
		lblPeriodoMuestras = new JLabel();
		comboBoxPeriodo = new JComboBox();
	}

	private void setLayoutConstraints() {
		{// Agrega label Seleccionar indicador
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridheight = 1;
			gbc.gridwidth = 2;
			gbc.ipadx = 100;
			gbc.insets = new Insets(75, 10, 20, 10);
			this.add(lblSeleccionarIndicador, gbc);
			gbc.ipadx = 0;
		}

		{// Agrega ComboBoxSeleccionarIndicador
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.gridwidth = 2;
			gbc.gridheight = 1;
			gbc.insets = new Insets(75, 0, 20, 10);
			gbc.ipadx = 100;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			this.add(comboBoxSeleccionarIndicador, gbc);
			gbc.fill = GridBagConstraints.NONE;
			gbc.ipadx = 0;
		}

		{// Agrega Label Seleccionar Metodo Matematico
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridheight = 1;
			gbc.gridwidth = 2;
			gbc.ipadx = 100;
			gbc.insets = new Insets(75, 10, 20, 60);
			this.add(lblSeleccionarMetodoMatematico, gbc);
			gbc.ipadx = 0;
		}

		{// Agrega comboBoxSeleccionarMetodoMatematico
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.gridheight = 1;
			gbc.insets = new Insets(75, 0, 20, 10);
			gbc.ipady = 2;
			gbc.ipadx = 100;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			this.add(comboBoxSeleccionarMetodoMatematico, gbc);
			gbc.ipady = 0;
			gbc.ipadx = 0;
			gbc.fill = GridBagConstraints.NONE;
		}

		{// Agrega Boton Nuevo Metodo Matematico
			btnNuevoMetodoMatematico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PestaniaNuevoMetodoMatematico();
				}
			});
			gbc.gridx = 5;
			gbc.gridy = 1;
			gbc.gridwidth = 2;
			gbc.gridheight = 1;
			gbc.insets = new Insets(60, 0, 20, 20);
			btnNuevoMetodoMatematico.setIcon(new ImageIcon(
					VentanaPrincipal.class
							.getResource("/Imagenes/nuevoMetodo.png")));
			this.add(btnNuevoMetodoMatematico, gbc);
		}

		{// Agrega Label Fecha inicio
			lblFechaInicioPeriodo.setText("Fecha inicio periodo:");
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 2;
			gbc.insets = new Insets(50, 50, 76, 25);
			gbc.ipadx = 100;
			gbc.anchor = GridBagConstraints.CENTER;
			this.add(lblFechaInicioPeriodo, gbc);
			gbc.ipadx = 0;
		}

		{// Agrega Area de Texto fecha inicio
			gbc.gridx = 1;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, -160, 76, 12);
			// gbc.weightx = 1.0;
			gbc.ipadx = 100;
			this.add(txtFechaInicio, gbc);
			// gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}

		{// Agrega Label Fecha Fin
			lblFechaFinPeriodo.setText("Fecha fin periodo:");
			gbc.gridx = 2;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			// gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(50, -35, 76, -90);
			// gbc.weightx = 1.0;
			gbc.ipadx = 100;
			this.add(lblFechaFinPeriodo, gbc);
			// gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}

		{// Agrega Area de Texto fecha fin
			gbc.gridx = 3;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			// gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, -70, 76, 12);
			// gbc.weightx = 1.0;
			gbc.ipadx = 100;
			this.add(txtFechaFin, gbc);
			// gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}

		{// Agrega Label Periodo
			lblPeriodoMuestras.setText("Periodo:");
			gbc.gridx = 4;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			// gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, 10, 76, 24);
			// gbc.weightx = 1.0;
			gbc.ipadx = 100;
			this.add(lblPeriodoMuestras, gbc);
			// gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}

		{// Agrega ComboBox Periodo
			comboBoxPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
			comboBoxPeriodo.setEnabled(false);
			comboBoxPeriodo.setEditable(true);
			comboBoxPeriodo.setSelectedIndex(2);
			gbc.gridx = 5;
			gbc.gridy = 3;
			gbc.gridheight = 1;
			gbc.gridwidth = 1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(50, -110, 76, 24);
			// gbc.weightx = 1.0;
			gbc.ipadx = 30;
			this.add(comboBoxPeriodo, gbc);
			// gbc.weightx = 0.0;
			gbc.ipadx = 0;
		}

		{// Agrega Boton Calcular resultados
			btnCalcularResultados = new JButton("Calcular resultados");
			btnCalcularResultados.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new PestaniaResultado();
				}
			});
			gbc.gridx = 5;
			gbc.gridy = 3;
			gbc.insets = new Insets(200, 0, 50, 0);
			btnCalcularResultados.setIcon(new ImageIcon(VentanaPrincipal.class
					.getResource("/Imagenes/calcularResultados.png")));
			this.add(btnCalcularResultados, gbc);
		}

		{// Agrega Boton Resultados previos
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.gridheight = 1;
			gbc.insets = new Insets(200, 100, 50, 60);
			btnVerResultadosPrevios.setIcon(new ImageIcon(
					VentanaPrincipal.class
							.getResource("/Imagenes/resultadosPrevios.png")));
			this.add(btnVerResultadosPrevios, gbc);
		}

	}

}
