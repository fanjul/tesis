package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PestaniaResultado extends JPanel implements Pestania {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PestaniaResultado() {
		super();
		VentanaPrincipal.getInstance().getPestanias().addTab(null, this);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{290, 250, 72};
		gridBagLayout.rowHeights = new int[]{161, 0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0};
		
		this.setLayout(gridBagLayout);
		
		JEditorPane editorPane = new JEditorPane();
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.insets = new Insets(10, 10, 10, 10);
		gbc_editorPane.anchor = GridBagConstraints.WEST;
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 0;
		this.add(editorPane, gbc_editorPane);
		
		JEditorPane editorPane_1 = new JEditorPane();
		GridBagConstraints gbc_editorPane_1 = new GridBagConstraints();
		gbc_editorPane_1.weightx = 2.0;
		gbc_editorPane_1.fill = GridBagConstraints.BOTH;
		gbc_editorPane_1.insets = new Insets(10, 10, 10, 10);
		gbc_editorPane_1.gridx = 1;
		gbc_editorPane_1.gridy = 0;
		this.add(editorPane_1, gbc_editorPane_1);
		
		JButton btnCompararResultados = new JButton("Comparar resultados");
		btnCompararResultados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/comparar.png")));
		btnCompararResultados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO
			}
		});
		GridBagConstraints gbc_btnCompararResultados = new GridBagConstraints();
		gbc_btnCompararResultados.insets = new Insets(30, 0, 40, 5);
		gbc_btnCompararResultados.gridx = 0;
		gbc_btnCompararResultados.gridy = 1;
		this.add(btnCompararResultados, gbc_btnCompararResultados);
		
		JLabel lblSeleccionarGrafico = new JLabel("Seleccionar Gr\u00E1fico");
		GridBagConstraints gbc_lblSeleccionarGrafico = new GridBagConstraints();
		gbc_lblSeleccionarGrafico.insets = new Insets(0, 20, 10, 5);
		gbc_lblSeleccionarGrafico.gridx = 1;
		gbc_lblSeleccionarGrafico.gridy = 1;
		this.add(lblSeleccionarGrafico, gbc_lblSeleccionarGrafico);
		
		JComboBox comboBoxSeleccionar = new JComboBox();
		GridBagConstraints gbc_comboBoxSeleccionar = new GridBagConstraints();
		gbc_comboBoxSeleccionar.insets = new Insets(0, 0, 10, 5);
		gbc_comboBoxSeleccionar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxSeleccionar.weightx = 1.0;
		gbc_comboBoxSeleccionar.gridx = 2;
		gbc_comboBoxSeleccionar.gridy = 1;
		this.add(comboBoxSeleccionar, gbc_comboBoxSeleccionar);


		VentanaPrincipal.getInstance().getPestanias().setSelectedIndex(VentanaPrincipal.getInstance().getPestanias().getTabCount() - 1);
		

		
		/*pestaniaResultados.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		JEditorPane editor = new JEditorPane();
		editor.setVisible(true);
		JPanel panelGrafico = new JPanel(new CardLayout());
		gbc.gridx = 0 ;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weighty = 0.5;
		gbc.weightx = 0.5;
		gbc.fill = GridBagConstraints.BOTH;
	//	gbc.insets = new Insets(10, 10, 10, 10);
		
		panelGrafico.add(editor);

		pestaniaResultados.add(panelGrafico,gbc);
		
		JPanel panelGrafico2 = new JPanel();
		gbc.gridx = 1 ;
		gbc.gridy = 0;
		gbc.gridheight = 1 ;
		gbc.gridwidth = 1;
	//	gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		pestaniaResultados.add(panelGrafico2,gbc);

		
		JButton btnCompararResultados = new JButton("Comparar Resultados");
		gbc.gridx = 0 ;
		gbc.gridy = 1;
		gbc.gridheight = 1 ;
		gbc.gridwidth = 1 ;
	//	gbc.insets = new Insets(0, 40, 40, 0);
		gbc.fill = GridBagConstraints.NONE;
		pestaniaResultados.add(btnCompararResultados,gbc);
		
		JLabel lblSeleccionarGrafico = new JLabel("Seleccionar gr\u00E1fico");
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
	//	gbc.insets = new Insets(0, 50, 40, 0);
		pestaniaResultados.add(lblSeleccionarGrafico,gbc);

		JComboBox comboBoxNuevoGrafico = new JComboBox();
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.gridwidth = 1 ;
		gbc.fill = GridBagConstraints.HORIZONTAL;
	//	gbc.insets = new Insets(0, 0, 40, 70);
		pestaniaResultados.add(comboBoxNuevoGrafico,gbc);

	
*/

		this.setEnableAbrirArchivo();
		this.setEnableAbrirBaseDeDatos();
		
		ConfiguracionPestania configuracionPestania = new ConfiguracionPestania( "Resultados", VentanaPrincipal.getInstance().getPestanias().getTabCount()-1, "Resultados obtenidos");
	}

	@Override
	public void setEnableAbrirArchivo() {
		VentanaPrincipal.getInstance().setEnableAbrirArchivo(false);
		
	}

	@Override
	public void setEnableAbrirBaseDeDatos() {
		VentanaPrincipal.getInstance().setEnableAbrirBaseDeDatos(false);
		
	}
}
