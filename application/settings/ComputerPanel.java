package com.pulsebeat02.shoeraffleservice.application.settings;

import java.awt.AWTException;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.pulsebeat02.shoeraffleservice.application.settings.graph.DiskUsageGraph;
import com.pulsebeat02.shoeraffleservice.application.settings.graph.MemoryUsageGraph;
import com.pulsebeat02.shoeraffleservice.application.settings.graph.ProcessorUsageGraph;

public class ComputerPanel extends JPanel {

    public JTabbedPane tabs;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void main(String[] args) throws AWTException {

	JFrame myFrame = new JFrame("Test");

	ComputerPanel p = new ComputerPanel();

	myFrame.getContentPane().add(p);
	myFrame.pack();
	myFrame.setVisible(true);

    }

    public ComputerPanel() throws AWTException {

	ProcessorUsageGraph processor = new ProcessorUsageGraph();
	MemoryUsageGraph memory = new MemoryUsageGraph();
	DiskUsageGraph disk = new DiskUsageGraph();

	this.setSize(415, 385);
	this.setPreferredSize(new Dimension(415, 385));
	this.setLayout(null);

	this.tabs = new JTabbedPane(JTabbedPane.TOP);
	this.tabs.setBounds(10, 11, 395, 365);
	this.tabs.addTab("Processor", processor);
	this.tabs.addTab("Memory", memory);
	this.tabs.addTab("Disk", disk);
	this.tabs.addTab("Help", new HelpPanel());
	this.tabs.setSelectedIndex(3);

	this.tabs.getModel().addChangeListener(new ChangeListener() {

	    public void stateChanged(ChangeEvent e) {

//				switch (lastTabIndex) {
//
//				case 0:
//					processor.cont = false;
//					break;
//
//				case 1:
//					memory.cont = false;
//					break;
//
//				case 2:
//					disk.cont = false;
//					break;
//
//				}
//
//				int newIndex = tabs.getSelectedIndex();
//
//				switch (newIndex) {
//
//				case 0:
//					processor = new ProcessorUsageGraph();
//					break;
//
//				case 1:
//					memory = new MemoryUsageGraph();
//					break;
//
//				case 2:
//					disk = new DiskUsageGraph();
//					break;
//
//				}
//
//				lastTabIndex = newIndex;

	    }
	});
	add(tabs);

    }

}
