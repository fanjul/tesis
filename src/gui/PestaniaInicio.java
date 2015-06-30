package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class PestaniaInicio extends JPanel implements Pestania {

	
	private static final long serialVersionUID = 1L;

	public PestaniaInicio(JTabbedPane pestanias) {
		
		super();
		GridBagConstraints gbc = new GridBagConstraints();
		pestanias.addTab("Inicio ", null, this, "Ventana principal");
		this.setLayout(new GridBagLayout());
		
		
		JLabel lblSeleccionarIndicador = new JLabel("Seleccionar indicador");
		gbc.gridx = 0 ;
		gbc.gridy = 0;
		gbc.gridheight = 1 ;
		gbc.gridwidth = 1 ;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(75, 100, 20, 60);
		gbc.weightx = 1.0;
		gbc.ipadx = 100;
		this.add(lblSeleccionarIndicador,gbc);
		gbc.weightx = 0.0;
		gbc.ipadx = 0;


		
		JComboBox comboBoxSeleccionarIndicador = new JComboBox();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 2 ;
		gbc.gridheight = 1;
		gbc.weightx = 1.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(75, 50, 20, 0);
		gbc.ipady = 2;
		gbc.ipadx = 100;
		this.add(comboBoxSeleccionarIndicador,gbc);
		gbc.weightx = 0.0;
		gbc.ipady = 0;
		gbc.ipadx = 0;


		JLabel lblSeleccionarMetodoMatematico = new JLabel(
				"Seleccionar m\u00E9todo matem\u00E1tico");
		gbc.gridx = 0 ;
		gbc.gridy = 1;	
		gbc.gridheight = 1 ;
		gbc.gridwidth = 1 ;
		gbc.insets = new Insets(75, 100, 20, 60);
		this.add(lblSeleccionarMetodoMatematico,gbc);

		JComboBox comboBoxSeleccionarMetodoMatematico = new JComboBox();
		gbc.gridx = 1;
		gbc.gridy = 1;		
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.insets = new Insets(75, 50, 0, 0);
		gbc.ipady = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;	
		this.add(comboBoxSeleccionarMetodoMatematico,gbc);


		JButton btnNuevoMetodoMatematico = new JButton("Nuevo m\u00E9todo matem\u00E1tico");
		btnNuevoMetodoMatematico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PestaniaNuevoMetodoMatematico(pestanias); 

			}
		}); 
		
		gbc.gridx = 3;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.NORTH;
		gbc.insets = new Insets(0, 0, 20, 160);
		this.add(btnNuevoMetodoMatematico,gbc);

		
		JButton btnVerResultadosPrevios = new JButton("Ver resultados previos");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.NONE;
		gbc.weighty = 1.0;
		gbc.insets = new Insets(200, 100, 50, 60);
		this.add(btnVerResultadosPrevios,gbc);
		gbc.weighty = 0.0;

		
		JButton btnCalcularResultados = new JButton("Calcular resultados");
		btnCalcularResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PestaniaResultado(pestanias);
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 1.0;
		gbc.anchor = GridBagConstraints.SOUTHEAST;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(200, 300, 50, 0);
		this.add(btnCalcularResultados,gbc);
		gbc.weightx = 0.0;
		
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

	
}
