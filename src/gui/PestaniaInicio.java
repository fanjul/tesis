//package gui;
//
//import java.awt.FlowLayout;
//import java.awt.GridBagConstraints; 
//import java.awt.GridBagLayout;
//import java.awt.GridLayout;
//import java.awt.Insets;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.util.concurrent.ExecutionException;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTabbedPane;
//import javax.swing.JTextField;
//import javax.swing.SwingWorker;
//import javax.swing.border.Border;
//import javax.swing.border.LineBorder;
//
//import org.joda.time.LocalDate;
//
//import properties.Parameters;
//import properties.PropertyManager;
//import baseDatos.DBAnalisis;
//import baseDatos.DBIndicador;
//import baseDatos.DBValorIndicador;
//import baseDatos.Item;
//import baseDatos.Periodo;
//
//
//
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
//
//
//
//@SuppressWarnings("serial")
//public class PestaniaInicio extends JPanel {
//
//	private JTextField txtMaxDate;
//	private JTextField txtMinDate;
//	private JLabel lblFechaInicioPeriodo;
//	private JLabel lblFechaFinPeriodo;
//	private JLabel lblDd;
//	private JLabel jLabel2;
//	private JLabel lblPeriodoMuestras;
//	private JComboBox comboBoxPeriodo;
//
//	private LocalDate minDate;
//	private LocalDate maxDate;
//
//	private JComboBox comboBoxSeleccionarIndicador;
//	private JButton btnCalcularResultados;
//	
//	@SuppressWarnings("rawtypes")
//	public PestaniaInicio(JTabbedPane pestanias) {
//
//		JPanel pestaniaInicio = new JPanel();
//		GridBagConstraints gbc = new GridBagConstraints();
//		pestanias.addTab("Inicio ", null, pestaniaInicio, "Ventana principal");
//		pestaniaInicio.setLayout(new GridBagLayout());
//		
//		
//		JLabel lblSeleccionarIndicador = new JLabel("Seleccionar indicador");
//		gbc.gridx = 0 ;
//		gbc.gridy = 0;
//		gbc.gridheight = 1 ;
//		gbc.gridwidth = 1 ;
//		gbc.anchor = GridBagConstraints.WEST;
//		gbc.insets = new Insets(75, 100, 20, 60);
//		gbc.weightx = 1.0;
//		gbc.ipadx = 100;
//		pestaniaInicio.add(lblSeleccionarIndicador,gbc);
//		gbc.weightx = 0.0;
//		gbc.ipadx = 0;
//
//
//		
//		comboBoxSeleccionarIndicador = new JComboBox();
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		gbc.gridwidth = 3 ;
//		gbc.gridheight = 1;
//		gbc.weightx = 1.5;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.anchor = GridBagConstraints.NORTH;
//		gbc.insets = new Insets(75, -80, 20, 300);
//		gbc.ipady = 2;
//		gbc.ipadx = 100;
//		pestaniaInicio.add(comboBoxSeleccionarIndicador,gbc);
//		gbc.weightx = 0.0;
//		gbc.ipady = 0;
//		gbc.ipadx = 0;
//
//
//		JLabel lblSeleccionarMetodoMatematico = new JLabel(
//				"Seleccionar m\u00E9todo matem\u00E1tico");
//		gbc.gridx = 0 ;
//		gbc.gridy = 1;	
//		gbc.gridheight = 1 ;
//		gbc.gridwidth = 1 ;
//		gbc.insets = new Insets(75, 100, 20, 60);
//		pestaniaInicio.add(lblSeleccionarMetodoMatematico,gbc);
//
//		JComboBox comboBoxSeleccionarMetodoMatematico = new JComboBox();
//		gbc.gridx = 1;
//		gbc.gridy = 1;		
//		gbc.gridwidth = 3;
//		gbc.gridheight = 1;
//		gbc.insets = new Insets(75, -80, 20, 300);
//		gbc.ipady = 2;
//		gbc.fill = GridBagConstraints.HORIZONTAL;
//		gbc.anchor = GridBagConstraints.NORTH;	
//		pestaniaInicio.add(comboBoxSeleccionarMetodoMatematico,gbc);
//
//
//		JButton btnNuevoMetodoMatematico = new JButton("Nuevo m\u00E9todo matem\u00E1tico");
//		btnNuevoMetodoMatematico.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new PestaniaNuevoMetodoMatematico(pestanias); 
//
//			}
//		}); 
//		
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		gbc.gridwidth = 2;
//		gbc.gridheight = 1;
//		gbc.anchor = GridBagConstraints.NORTH;
//		gbc.fill = GridBagConstraints.NORTH;
//		gbc.insets = new Insets(60, 700, 20, 20);
//		btnNuevoMetodoMatematico.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/nuevoMetodo.png")));
//		pestaniaInicio.add(btnNuevoMetodoMatematico,gbc);
//		
//		
//		JButton btnVerResultadosPrevios = new JButton("Ver resultados previos");
//		gbc.gridx = 0;
//		gbc.gridy = 3;
//		gbc.gridwidth = 1;
//		gbc.gridheight = 1;
//		gbc.anchor = GridBagConstraints.CENTER;
//		gbc.fill = GridBagConstraints.NONE;
//		gbc.weighty = 1.0;
//		gbc.insets = new Insets(200, 100, 50, 60);
//		btnVerResultadosPrevios.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/resultadosPrevios.png")));
//		pestaniaInicio.add(btnVerResultadosPrevios,gbc);
//		gbc.weighty = 0.0;
//
//		
//		btnCalcularResultados = new JButton("Calcular resultados");
//		btnCalcularResultados.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new PestaniaResultado(pestanias);
//
//			}
//		});
//
//		gbc.gridx = 2;
//		gbc.gridy = 3;
//		gbc.gridwidth = 2;
//		gbc.gridheight = 1;
//		gbc.weightx = 1.0;
//		gbc.anchor = GridBagConstraints.SOUTHEAST;
//		gbc.fill = GridBagConstraints.NONE;
//		gbc.insets = new Insets(200, 200, 50, 100);
//		
//		btnCalcularResultados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Imagenes/calcularResultados.png")));
//		
//		pestaniaInicio.add(btnCalcularResultados,gbc);
//		gbc.weightx = 0.0;
//
//		
//
//		
//		{
//			lblFechaInicioPeriodo = new JLabel();
//			lblFechaInicioPeriodo.setText("Fecha inicio periodo:");
//			
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 0;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(50, 50, 76, 0);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(lblFechaInicioPeriodo,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//			
//		}
//		
//		{
//			txtMinDate = new JTextField();
//			
//			gbc.gridx = 0;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(50, 180, 76, 12);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(txtMinDate,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//
//
//		{
//			lblFechaFinPeriodo = new JLabel();
//			lblFechaFinPeriodo.setText("Fecha fin periodo:");
//			
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(50, 380, 76, 12);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(lblFechaFinPeriodo,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//			
//		{
//			txtMaxDate = new JTextField();
//			
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(50, 500, 76, 12);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(txtMaxDate,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//		
//		{
//			lblPeriodoMuestras = new JLabel();
//			lblPeriodoMuestras.setText("Periodo:");
//			
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(50, 700, 76, 24);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(lblPeriodoMuestras,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//		
//		
//		{
//			comboBoxPeriodo = new JComboBox();
//			comboBoxPeriodo.setModel(new DefaultComboBoxModel(Periodo.values()));
//			comboBoxPeriodo.setEnabled(false);
//			comboBoxPeriodo.setEditable(true);
//			comboBoxPeriodo.setSelectedIndex(2);
//		
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(50, 770, 76, 24);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 1;
//			pestaniaInicio.add(comboBoxPeriodo,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//		
//
//		{
//			lblDd = new JLabel();
//			lblDd.setText("DD/MM/AAAA");
//		
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(0, 190, 76, 12);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(lblDd,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//		
//		{
//			jLabel2 = new JLabel();
//			pestaniaInicio.add(jLabel2);
//			jLabel2.setText("DD/MM/AAAA");
//			jLabel2.setBounds(362, 82, 79, 16);
//			
//			gbc.gridx = 0 ;
//			gbc.gridy = 3;
//			gbc.gridheight = 1 ;
//			gbc.gridwidth = 6 ;
//			gbc.anchor = GridBagConstraints.WEST;
//			gbc.insets = new Insets(0, 515, 76, 12);
//			gbc.weightx = 1.0;
//			gbc.ipadx = 100;
//			pestaniaInicio.add(jLabel2,gbc);
//			gbc.weightx = 0.0;
//			gbc.ipadx = 0;
//		}
//		
//	}
//	
//	////////////////////////////////////////
//
//
//		public void cargarDatos(File dbPath){
//			PropertyManager.instance().setProperty(Parameters.DB_NAME.toString(),
//					"jdbc:ucanaccess://" + dbPath.getAbsolutePath());
//
//			new SwingWorker<Object[], Void>() {
//
//				@Override
//				protected Object[] doInBackground() throws Exception {
//					DBValorIndicador vIndicador = new DBValorIndicador();
//					minDate = vIndicador.getMinDate();
//					maxDate = vIndicador.getMaxDate();
//					DBIndicador indicador = new DBIndicador();
//					return indicador.getIndicatorNames();
//				}
//
//				@SuppressWarnings({ "unchecked" })
//				@Override
//				public void done() {
//					try {
//						comboBoxSeleccionarIndicador.setModel(new DefaultComboBoxModel<>(get()));
//						AutoCompleteDecorator.decorate(comboBoxSeleccionarIndicador);
//					} catch (InterruptedException | ExecutionException e) {
//						e.printStackTrace();
//					}
//					
//					// Cargar el valor de periodo del indicador
//					DBIndicador indicador = new DBIndicador();
//					//switch (indicador.getPeriodo(indicador.getIdByName(comboBox.getSelectedItem().toString()))) {
//					switch (indicador.getPeriodo(indicador.getIdByName(((Item<String>)comboBoxPeriodo.getSelectedItem()).getDescription()))) {
//					case "diario":
//						comboBoxPeriodo.setSelectedIndex(0);
//						break;
//					case "mensual":
//						comboBoxPeriodo.setSelectedIndex(1);
//						break;
//					case "anual":
//						comboBoxPeriodo.setSelectedIndex(2);
//						break;
//					}
//									
//					// Cambiar la fecha inicio y fin segun el indicador
//					DBValorIndicador vIndicador = new DBValorIndicador();
//					minDate = vIndicador.getMinDate(indicador.getIdByName(((Item<String>)comboBoxSeleccionarIndicador.getSelectedItem()).getDescription()));
//					//maxDate = vIndicador.getMaxDate(indicador.getIdByName(comboBox.getSelectedItem().toString()));
//					maxDate = vIndicador.getMaxDate(indicador.getIdByName(((Item<String>)comboBoxSeleccionarIndicador.getSelectedItem()).getDescription()));
//					txtMinDate.setText(minDate.getDayOfMonth() + "/"
//							+ minDate.getMonthOfYear() + "/" + minDate.getYear());
//					txtMaxDate.setText(maxDate.getDayOfMonth() + "/"
//							+ maxDate.getMonthOfYear() + "/" + maxDate.getYear());
//					btnCalcularResultados.setEnabled(true);
//				}
//			}.execute();
//			;
//				
//				
//			}
//
//		public JTextField getTxtMaxDate() {
//			return txtMaxDate;
//		}
//
//
//
//		public JButton getBtnCalcularResultados() {
//			return btnCalcularResultados;
//		}
//
//
//		public JTextField getTxtMinDate() {
//			return txtMinDate;
//		}
//
//
//
//		public JComboBox getComboBoxPeriodo() {
//			return comboBoxPeriodo;
//		}
//
//
//		public LocalDate getMinDate() {
//			return minDate;
//		}
//
//	
//
//		public LocalDate getMaxDate() {
//			return maxDate;
//		}
//
//	
//
//		public JComboBox getComboBoxSeleccionarIndicador() {
//			return comboBoxSeleccionarIndicador;
//		}
//
//	
//			
//			
//		
//	
//	//////////////////////////////////////
//   
//}