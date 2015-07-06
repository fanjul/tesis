package gui;

import gui.archivos.ManejadorArchivos;

import javax.swing.JPanel;

public abstract class Pestania extends JPanel {

	private static final long serialVersionUID = 8192882882855514889L;
	protected String tooltip;
	protected String nombre;
	protected ManejadorArchivos manejadorArchivos;

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
