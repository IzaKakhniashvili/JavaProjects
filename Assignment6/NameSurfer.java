/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants{ 
		
		private NameSurferDataBase DATA;
		private NameSurferGraph GRAPHICS;
		private NameSurferEntry ENTRY;
/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
		GRAPHICS = new NameSurferGraph();
        add(GRAPHICS);
		JLabel name = new JLabel("Name");
		add(name, SOUTH);
		entry = new JTextField(10);
		add(entry, SOUTH);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		add(graph, SOUTH);
		add(clear, SOUTH);
		graph.addActionListener(this);
        clear.addActionListener(this);
        
        
        DATA = new NameSurferDataBase(NAMES_DATA_FILE);
        
	    // You fill this in, along with any helper methods //
	}
	


/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == graph) {
			String input = entry.getText(); 
			ENTRY = DATA.findEntry(input);
			GRAPHICS.addEntry(ENTRY);
			GRAPHICS.update();
		}else if(e.getSource() == clear){
			GRAPHICS.clear();
			GRAPHICS.update();
			
		}
	}
	private JButton graph;
	private JButton clear;
	private JTextField entry; 
	private NameSurferGraph  graphics;
}
