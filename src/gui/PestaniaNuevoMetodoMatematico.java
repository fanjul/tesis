package gui;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.TextArea;

public class PestaniaNuevoMetodoMatematico extends JPanel {

	/**
	 * Create the panel.
	 */
	public PestaniaNuevoMetodoMatematico(JTabbedPane pestanias) {
		JPanel pestaniaNuevoMetodoMatematico= new JPanel();
		pestanias.addTab(null, pestaniaNuevoMetodoMatematico);
		pestanias.setSelectedIndex(pestanias.getTabCount() - 1);
		

		JEditorPane areaTexto = new JEditorPane();
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; // El área de texto empieza en la columna cero.
		constraints.gridy = 0; // El área de texto empieza en la fila cero
		constraints.gridwidth = 1; // El área de texto ocupa dos columnas.
		constraints.gridheight = 1; // El área de texto ocupa 2 filas.
	
		constraints.fill = GridBagConstraints.CENTER;
		constraints.anchor = GridBagConstraints.BOTH;
		pestaniaNuevoMetodoMatematico.add (areaTexto, constraints);
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		
		JButton boton1 = new JButton ("Boton 1"); 
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		pestaniaNuevoMetodoMatematico.add (boton1, constraints);

		  
		/*JTextArea areaTextoNuevoMetodo = new JTextArea(25,80);
		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(areaTextoNuevoMetodo);
		// Para que el partido se haga respetando las palabras. Sólo se parte la
		// línea en los espacios entre palabras.
		areaTextoNuevoMetodo.setWrapStyleWord(true);
		areaTextoNuevoMetodo.setLineWrap(true);
		//add(scroll, BorderLayout.CENTER);pestanias.getContentPane().add(scroll, BorderLayout.CENTER);
		*/
		new ConfiguracionPestania(pestanias, "Nuevo Método Matemático", pestanias.getTabCount()-1, "Resultados obtenidos");
	}

}
