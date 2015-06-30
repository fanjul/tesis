package gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import syntax.JEditTextArea;
import syntax.JavaTokenMarker;
import syntax.SyntaxStyle;
import syntax.SyntaxUtilities;
import syntax.Token;

public class PestaniaNuevoMetodoMatematico extends JPanel implements Pestania {

	private static final long serialVersionUID = 1L;

	public PestaniaNuevoMetodoMatematico() {

		super();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		this.setLayout(gridBagLayout);

		JEditTextArea textArea = new JEditTextArea();

		textArea.setTokenMarker(new JavaTokenMarker());
		textArea.recalculateVisibleLines();
		textArea.setFirstLine(0);
		textArea.setElectricScroll(0);
		textArea.getPainter().setSelectionColor(
				UIManager.getColor("TextArea.selectionBackground"));

		SyntaxStyle[] styles = SyntaxUtilities.getDefaultSyntaxStyles();
		styles[Token.COMMENT1] = new SyntaxStyle(Color.GRAY, true, false);
		styles[Token.KEYWORD1] = new SyntaxStyle(new Color(0x000080), false,
				true);
		styles[Token.KEYWORD2] = new SyntaxStyle(new Color(0x000080), false,
				true);
		styles[Token.KEYWORD3] = new SyntaxStyle(new Color(0x000080), false,
				true);
		styles[Token.LITERAL1] = new SyntaxStyle(new Color(0x008000), false,
				true);
		styles[Token.LITERAL2] = new SyntaxStyle(new Color(0x000080), false,
				true);

		textArea.getPainter().setStyles(styles);
		// pestaniaNuevoMetodoMatematico.add(textArea);

		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.weightx = 1.0;
		gbc_editorPane.anchor = GridBagConstraints.WEST;
		gbc_editorPane.insets = new Insets(0, 0, 50, 100);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 0;
		this.add(textArea, gbc_editorPane);

		JButton btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setIcon(new ImageIcon(VentanaPrincipal.class
				.getResource("/Imagenes/ejecutar.png")));
		GridBagConstraints gbc_btnEjecutar = new GridBagConstraints();
		gbc_btnEjecutar.insets = new Insets(0, 0, 0, 0);
		gbc_btnEjecutar.gridx = 1;
		gbc_btnEjecutar.gridy = 0;
		this.add(btnEjecutar, gbc_btnEjecutar);

		VentanaPrincipal.getInstance().getPestanias().addTab(null, this);
		VentanaPrincipal.getInstance().getPestanias().setSelectedIndex(VentanaPrincipal.getInstance().getPestanias().getTabCount() - 1);

		this.setEnableAbrirArchivo();
		this.setEnableAbrirBaseDeDatos();

		new ConfiguracionPestania( "Nuevo Método Matemático",
				VentanaPrincipal.getInstance().getPestanias().getTabCount() - 1,
				"Agregue un muevo metodo matemático");
	}

	@Override
	public void setEnableAbrirArchivo() {
		VentanaPrincipal.getInstance().setEnableAbrirArchivo(true);
	}

	@Override
	public void setEnableAbrirBaseDeDatos() {
		VentanaPrincipal.getInstance().setEnableAbrirBaseDeDatos(false);

	}

}
