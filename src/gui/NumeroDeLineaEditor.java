package gui;

import java.awt.Color;
 


import java.awt.Font;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

import syntax.JEditTextArea;
 
//TODO tratar de hacerla SINGLETON
/*
public class NumeroDeLineaEditor extends JFrame{
	private JEditTextArea editorTexto;
	private JTextArea areaNumeroLineas;
	private JScrollPane scrollEditor;
 
	public NumeroDeLineaEditor(JEditTextArea editorTexto, JTextArea areaNumeroLineas){
		this.editorTexto = editorTexto;
		this.scrollEditor = scrollEditor;
		this.areaNumeroLineas = areaNumeroLineas;
	}
 
	public void mostrarNumeroDeLinea(){
	
		editorTexto = new JEditTextArea();
		areaNumeroLineas = new JTextArea("1");
 
		areaNumeroLineas.setBackground(Color.CYAN);
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

*/
 
