package gui;

import javax.swing.JPanel;

public abstract class Pestania extends JPanel {

	protected String tooltip;
	protected String nombre;

	public Pestania() {
		super();
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	abstract void setEnableAbrirArchivo();

	abstract void setEnableAbrirBaseDeDatos();

}
