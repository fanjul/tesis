package baseDatos;

public class TablaResultados {
	
	private String m[][];
	private int size;
	
	public TablaResultados(String[][] m, int size) {
		super();
		this.m = m;
		this.size = size;
	}

	public String[][] getResultados() {
		return m;
	}

	public void setResultados(String[][] m) {
		this.m = m;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

}
