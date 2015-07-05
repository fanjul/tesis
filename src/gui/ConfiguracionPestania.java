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
	
	private static final long serialVersionUID = 2238586550033832359L;
	private JTabbedPane pestanias;


	public ConfiguracionPestania(Integer posPestania) {

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
		add(lblTitulo);
		add(btClose);

		btClose.addActionListener(this);
		pestanias.setTabComponentAt(posPestania, this);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		int i = pestanias.indexOfTabComponent(this);
		if (i != -1) {
			pestanias.remove(i);
		}
	}
}
