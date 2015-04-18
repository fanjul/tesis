package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class PestaniaResultado extends JPanel {

	public PestaniaResultado(JTabbedPane pestanias) {
		JPanel pestaniaResultados = new JPanel();
		pestanias.addTab("Resultados   ", null, pestaniaResultados, null);
		pestanias.setSelectedIndex(pestanias.getTabCount() - 1);
		pestaniaResultados.setLayout(null);
		JButton btnCompararResultados = new JButton("Comparar Resultados");
		btnCompararResultados.setBounds(488, 287, 135, 23);
		pestaniaResultados.add(btnCompararResultados);

		JComboBox comboBoxNuevoGrafico = new JComboBox();
		comboBoxNuevoGrafico.setBounds(112, 246, 253, 20);
		pestaniaResultados.add(comboBoxNuevoGrafico);

		JLabel lblNuevoGarfico = new JLabel("Nuevo gr\u00E1fico");
		lblNuevoGarfico.setBounds(10, 249, 92, 14);
		pestaniaResultados.add(lblNuevoGarfico);

		JPanel panelGrafico = new JPanel();
		panelGrafico.setBounds(10, 11, 464, 225);
		pestaniaResultados.add(panelGrafico);
	}

}
