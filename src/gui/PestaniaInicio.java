package gui;

import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

//import com.jgoodies.looks.common.RGBGrayFilter;

public class PestaniaInicio extends JPanel {

	public PestaniaInicio(JTabbedPane pestanias) {
		JPanel pestaniaInicio = new JPanel();
		pestaniaInicio.setLayout(null);
		pestaniaInicio.setOpaque(false);
		ImageIcon btnCerar = new ImageIcon(getClass().getClassLoader().getResource("C:\\Users\\Mariel\Desktop\Facultad\Tesis\HerramientaTesis\Imagenclose_icon.png"));
	 	ImageIcon btnCerarTamanio = new ImageIcon(btnCerar.getImage().getScaledInstance(15, 15, 1));
		pestanias.addTab("Incio",btnCerarTamanio, pestaniaInicio);
				
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
