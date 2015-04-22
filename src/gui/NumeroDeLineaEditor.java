package gui;

import java.awt.Color;
 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
 
public class NumeroDeLineaEditor extends JFrame{
	private JTextArea editorTexto;
	private JTextArea areaNumeroLineas;
	private JScrollPane scrollEditor;
 
	public NumeroDeLineaEditor(JTextArea editorTexto, JTextArea lines, JScrollPane scrollEditor){
		this.editorTexto = editorTexto;
		this.scrollEditor = scrollEditor;
		this.areaNumeroLineas =lines;
	}
 
	public  void createAndShowGUI(){
	
		editorTexto = new JTextArea();
		areaNumeroLineas = new JTextArea("1");
 
		areaNumeroLineas.setBackground(Color.LIGHT_GRAY);
		areaNumeroLineas.setEditable(false);
 
		editorTexto.getDocument().addDocumentListener(new DocumentListener(){
			public String getText(){
				int caretPosition = editorTexto.getDocument().getLength();
				Element root = editorTexto.getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			@Override
			public void changedUpdate(DocumentEvent de) {
				areaNumeroLineas.setText(getText());
			}
 
			@Override
			public void insertUpdate(DocumentEvent de) {
				areaNumeroLineas.setText(getText());
			}
 
			@Override
			public void removeUpdate(DocumentEvent de) {
				areaNumeroLineas.setText(getText());
			}
 
		});
 
		scrollEditor.getViewport().add(editorTexto);
		scrollEditor.setRowHeaderView(areaNumeroLineas);
		scrollEditor.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
 
	
	}
 

}


 
