package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class PestaniaInicio extends JPanel {

	public PestaniaInicio(JTabbedPane pestanias) {

		JPanel pestaniaInicio = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		pestanias.addTab("Inicio ", null, pestaniaInicio, "Ventana principal");
		pestaniaInicio.setLayout(new GridBagLayout());
		
		
		JLabel lblSeleccionarIndicador = new JLabel("Seleccionar indicador");
		gbc.gridx = 0 ;
		gbc.gridy = 0;
		gbc.gridheight = 1 ;
		gbc.gridwidth = 1 ;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(50, 50, 50, 60);
		pestaniaInicio.add(lblSeleccionarIndicador,gbc);


		
		JComboBox comboBoxSeleccionarIndicador = new JComboBox();
//		comboBoxSeleccionarIndicador.setBounds(263, 55, 320, 20);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.gridwidth = 4 ;
		gbc.gridheight = 1;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets = new Insets(50, 10, 50, 60);
		gbc.ipady = 3;
		pestaniaInicio.add(comboBoxSeleccionarIndicador,gbc);
		gbc.weightx = 0.0;
		gbc.ipady = 0;

		JLabel lblSeleccionarMetodoMatematico = new JLabel(
				"Seleccionar m\u00E9todo matem\u00E1tico");
		gbc.gridx = 0 ;
		gbc.gridy = 1;	
		gbc.gridheight = 1 ;
		gbc.gridwidth = 1 ;
		gbc.weightx = 1.0;
		pestaniaInicio.add(lblSeleccionarMetodoMatematico,gbc);
		gbc.weightx = 0.0;

		JComboBox comboBoxSeleccionarMetodoMatematico = new JComboBox();
		gbc.gridx = 1;
		gbc.gridy = 1;		
		gbc.gridwidth = 4 ;
		gbc.gridheight = 1;
		pestaniaInicio.add(comboBoxSeleccionarMetodoMatematico,gbc);

		JButton btnNuevoMetodoMatematico = new JButton(
				"Nuevo m\u00E9todo matem\u00E1tico");
		
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		pestaniaInicio.add(btnNuevoMetodoMatematico,gbc);
		
		
		JButton btnVerResultadosPrevios = new JButton("Ver resultados previos");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		pestaniaInicio.add(btnVerResultadosPrevios,gbc);

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
		pestaniaInicio.add(btnCalcularResultados,gbc);
		gbc.weightx = 0.0;

		
	}
}
