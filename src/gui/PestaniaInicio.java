package gui;

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
		pestanias.addTab("Inicio ", null, pestaniaInicio, "Ventana principal");
		pestaniaInicio.setLayout(null);

		JComboBox comboBoxSeleccionarIndicador = new JComboBox();
		comboBoxSeleccionarIndicador.setBounds(263, 55, 320, 20);
		pestaniaInicio.add(comboBoxSeleccionarIndicador);

		JComboBox comboBoxSeleccionarMetodoMatematico = new JComboBox();
		comboBoxSeleccionarMetodoMatematico.setBounds(263, 111, 320, 20);
		pestaniaInicio.add(comboBoxSeleccionarMetodoMatematico);

		JButton btnVerResultadosPrevios = new JButton("Ver resultados previos");
		btnVerResultadosPrevios.setBounds(55, 265, 139, 23);
		pestaniaInicio.add(btnVerResultadosPrevios);

		JLabel lblSeleccionarIndicador = new JLabel("Seleccionar indicador");
		lblSeleccionarIndicador.setBounds(55, 58, 158, 17);
		pestaniaInicio.add(lblSeleccionarIndicador);

		JLabel lblSeleccionarMetodoMatematico = new JLabel(
				"Seleccionar m\u00E9todo matem\u00E1tico");
		lblSeleccionarMetodoMatematico.setBounds(55, 114, 158, 14);
		pestaniaInicio.add(lblSeleccionarMetodoMatematico);

		JButton btnCalcularResultados = new JButton("Calcular resultados");
		btnCalcularResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PestaniaResultado(pestanias);

			}
		});
		btnCalcularResultados.setBounds(484, 287, 139, 23);
		pestaniaInicio.add(btnCalcularResultados);

		JButton btnNuevoMetodoMatematico = new JButton(
				"Nuevo m\u00E9todo matem\u00E1tico");
		btnNuevoMetodoMatematico.setBounds(440, 154, 168, 23);
		pestaniaInicio.add(btnNuevoMetodoMatematico);
		
	}
}
