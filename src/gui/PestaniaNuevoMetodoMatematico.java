package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PestaniaNuevoMetodoMatematico extends JPanel {

	public PestaniaNuevoMetodoMatematico(JTabbedPane pestanias) {

		JPanel pestaniaNuevoMetodoMatematico = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		pestaniaNuevoMetodoMatematico.setLayout(gridBagLayout);

		JTextArea editorTexto = new JTextArea();
		JTextArea areaNumeroLineas = new JTextArea();
		JScrollPane scrollEditor = new JScrollPane(editorTexto);

		NumeroDeLineaEditor nroLinea = new NumeroDeLineaEditor(editorTexto,
				areaNumeroLineas, scrollEditor);
		nroLinea.createAndShowGUI();

		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.weightx = 1.0;
		gbc_editorPane.anchor = GridBagConstraints.WEST;
		gbc_editorPane.insets = new Insets(0, 0, 50, 100);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 0;
		pestaniaNuevoMetodoMatematico.add(scrollEditor, gbc_editorPane);

		JButton btnEjecutar = new JButton("Ejecutar");
		GridBagConstraints gbc_btnEjecutar = new GridBagConstraints();
		gbc_btnEjecutar.insets = new Insets(0, 0, 0, 142);
		gbc_btnEjecutar.gridx = 1;
		gbc_btnEjecutar.gridy = 0;
		pestaniaNuevoMetodoMatematico.add(btnEjecutar, gbc_btnEjecutar);

		pestanias.addTab(null, pestaniaNuevoMetodoMatematico);
		pestanias.setSelectedIndex(pestanias.getTabCount() - 1);

		new ConfiguracionPestania(pestanias, "Nuevo Método Matemático",
				pestanias.getTabCount() - 1, "Resultados obtenidos");
	}

}
