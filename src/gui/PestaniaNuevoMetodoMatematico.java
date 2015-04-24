package gui;

import syntax.JEditTextArea;
import syntax.JavaTokenMarker;
import syntax.SyntaxStyle;
import syntax.SyntaxUtilities;
import syntax.Token;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.EditorKit;
import javax.swing.text.Element;
import javax.swing.text.Highlighter;

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

		//JEditorPane editorTexto = new JEditorPane();
		JTextArea areaNumeroLineas = new JTextArea();
		//JScrollPane scrollEditor = new JScrollPane(editorTexto);

	
		
		JEditTextArea textArea = new JEditTextArea();
		
		/*NumeroDeLineaEditor nroLinea = new NumeroDeLineaEditor(textArea,areaNumeroLineas);
		nroLinea.mostrarNumeroDeLinea();*/
	
        textArea.setTokenMarker(new JavaTokenMarker());
     //   textArea.setText("en 2 meses nos ponemos el traje");
        textArea.recalculateVisibleLines();
        textArea.setFirstLine(0);
        textArea.setElectricScroll(0);
        textArea.getPainter().setSelectionColor(
                UIManager.getColor("TextArea.selectionBackground"));

        SyntaxStyle[] styles = SyntaxUtilities.getDefaultSyntaxStyles();
        styles[Token.COMMENT1] = new SyntaxStyle(Color.GRAY,true,false);
        styles[Token.KEYWORD1] = new SyntaxStyle(new Color(0x000080),false,true);
        styles[Token.KEYWORD2] = new SyntaxStyle(new Color(0x000080),false,true);
        styles[Token.KEYWORD3] = new SyntaxStyle(new Color(0x000080),false,true);
        styles[Token.LITERAL1] = new SyntaxStyle(new Color(0x008000),false,true);
        styles[Token.LITERAL2] = new SyntaxStyle(new Color(0x000080),false,true);

        textArea.getPainter().setStyles(styles);
      //  pestaniaNuevoMetodoMatematico.add(textArea);

		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.weightx = 1.0;
		gbc_editorPane.anchor = GridBagConstraints.WEST;
		gbc_editorPane.insets = new Insets(0, 0, 50, 100);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 0;
		gbc_editorPane.gridy = 0;
		pestaniaNuevoMetodoMatematico.add(textArea, gbc_editorPane);

		JButton btnEjecutar = new JButton("Ejecutar");
		GridBagConstraints gbc_btnEjecutar = new GridBagConstraints();
		gbc_btnEjecutar.insets = new Insets(0, 0, 0, 142);
		gbc_btnEjecutar.gridx = 1;
		gbc_btnEjecutar.gridy = 0;
		pestaniaNuevoMetodoMatematico.add(btnEjecutar, gbc_btnEjecutar);

		pestanias.addTab(null, pestaniaNuevoMetodoMatematico);
		pestanias.setSelectedIndex(pestanias.getTabCount() - 1);

		new ConfiguracionPestania(pestanias, "Nuevo Método Matemático",	pestanias.getTabCount() - 1, "Agregue un muevo metodo matemático");
	}

}
