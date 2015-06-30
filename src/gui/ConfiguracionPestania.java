package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ConfiguracionPestania extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane pestanias;


	public ConfiguracionPestania(String nombrePestania, Integer posPestania, String toolTip) {

		this.pestanias = VentanaPrincipal.getInstance().getPestanias();

		setOpaque(false);
		add(new JLabel(pestanias.getTitleAt(posPestania),
				pestanias.getIconAt(posPestania), JLabel.LEFT));


		Icon closeIcon = new IconoCerrar();
		
		JButton btClose = new JButton(closeIcon);

		btClose.setPreferredSize(new Dimension(closeIcon.getIconWidth(),
				closeIcon.getIconHeight()));
		btClose.setToolTipText("Cerrar");

		JLabel lblTitulo = new JLabel();
		lblTitulo.setText(nombrePestania);
		add(lblTitulo);
		add(btClose);

		btClose.addActionListener(this);
		pestanias.setTabComponentAt(posPestania, this);
		pestanias.setToolTipTextAt(posPestania, toolTip);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i = pestanias.indexOfTabComponent(this);
		if (i != -1) {
			pestanias.remove(i);
			((Pestania) pestanias.getComponentAt(i-1)).setEnableAbrirArchivo();
			((Pestania) pestanias.getComponentAt(i-1)).setEnableAbrirBaseDeDatos();

		}
	}
}
