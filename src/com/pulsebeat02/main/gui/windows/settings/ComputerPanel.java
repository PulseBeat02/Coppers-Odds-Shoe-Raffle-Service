package com.pulsebeat02.main.gui.windows.settings;

import java.awt.AWTException;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.pulsebeat02.main.gui.windows.settings.graph.cpu.ProcessorUsageGraph;
import com.pulsebeat02.main.gui.windows.settings.graph.disk.DiskUsageGraph;
import com.pulsebeat02.main.gui.windows.settings.graph.memory.MemoryUsageGraph;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ComputerPanel extends JPanel {
	
	public static JTabbedPane tabs;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ProcessorUsageGraph processor = new ProcessorUsageGraph();
	MemoryUsageGraph memory = new MemoryUsageGraph();
	DiskUsageGraph disk = new DiskUsageGraph();

	public static void main(String[] args) throws AWTException {

		JFrame myFrame = new JFrame("Test");
		
		ComputerPanel p = new ComputerPanel();
		
		myFrame.getContentPane().add(p);
		myFrame.pack();
		myFrame.setVisible(true);

	}

	/**
	 * Create the panel.
	 * @throws AWTException 
	 * 
	 */
	public ComputerPanel() throws AWTException {

		setSize(415, 385);
		setPreferredSize(new Dimension(415, 385));
		setLayout(null);

		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 11, 395, 365);
		tabs.addTab("Processor", processor);
		tabs.addTab("Memory", memory);
		tabs.addTab("Disk", disk);
		tabs.addTab("Help", new HelpPanel());
		tabs.setSelectedIndex(3);
		
		tabs.getModel().addChangeListener(new ChangeListener() {

//			int lastTabIndex = 0;

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
