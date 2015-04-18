package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class ConfiguracionPestania extends JPanel implements ActionListener {
	private JTabbedPane pestanias;
	
	public ConfiguracionPestania(JTabbedPane pestanias, String nombrePestania, Integer posPestania, String toolTip){
	
		this.pestanias = pestanias;		
	
		setOpaque(false);
	    add(new JLabel(	pestanias.getTitleAt(posPestania), pestanias.getIconAt(posPestania), JLabel.LEFT));
		
	    Icon closeIcon = new IconoCerrar();
	    JButton btClose = new JButton(closeIcon);
		
	    btClose.setPreferredSize(new Dimension(closeIcon.getIconWidth(), closeIcon.getIconHeight()));
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
	    }
	}
}
