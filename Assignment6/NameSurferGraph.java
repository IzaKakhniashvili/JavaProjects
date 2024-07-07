/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	
	private ArrayList<NameSurferEntry> EnteredNames = new ArrayList<NameSurferEntry>(); //displayed names
	private NameSurferEntry EnteredName;
	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		EnteredName = null;
		//	 You fill in the rest //
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		EnteredNames = new ArrayList<NameSurferEntry>();		//	 You fill this in //
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		EnteredName = entry;
		if(EnteredName != null) {
			EnteredNames.add(EnteredName);
		}
		
		// You fill this in //
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		addHorizontalLines();
		addVerticalLines();
		for(int i = 0; i < EnteredNames.size(); i++) {
			addRanking(EnteredNames.get(i + 1));
		}
		
		//	 You fill this in //
	}
	
	private void addHorizontalLines() {
		GLine topHorizont = new GLine(0, GRAPH_MARGIN_SIZE, APPLICATION_WIDTH, GRAPH_MARGIN_SIZE);
		add(topHorizont);
		GLine botHorizont = new GLine(0, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE, APPLICATION_WIDTH, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE);
		add(botHorizont);
	}
	
	private void addVerticalLines() {
		for(int i = 0; i <= NDECADES; i++) {
			double x = i * APPLICATION_WIDTH / NDECADES;
			double y = APPLICATION_HEIGHT;
			GLine line = new GLine(x, 0, x, y);
			add(line);
		}
		
	}
	private void addRanking(NameSurferEntry name) {
		for(int i =0; i < NDECADES; i++) {
			int RANK = name.getRank(i);
			if(RANK == 0 ) {
				GLine line = new GLine(APPLICATION_WIDTH / NDECADES * i, APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE, APPLICATION_WIDTH/NDECADES * (i + 1), APPLICATION_HEIGHT - GRAPH_MARGIN_SIZE );
				add(line);
			}
			if(RANK == 1) {
				GLine line = new GLine(APPLICATION_WIDTH / NDECADES * 1, GRAPH_MARGIN_SIZE, APPLICATION_WIDTH/NDECADES * (i +1), GRAPH_MARGIN_SIZE);
				add(line);
			}
			double x1 = APPLICATION_WIDTH * i / MAX_RANK;
			double x2 = APPLICATION_WIDTH * (i+1)/ MAX_RANK;
			double y1 = APPLICATION_HEIGHT * RANK / MAX_RANK;
			double y2 = APPLICATION_WIDTH * name.getRank(i + 1) / MAX_RANK;
			GLine line = new GLine(x1, y1, x2, y2);
			add(line);
			
		}
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
