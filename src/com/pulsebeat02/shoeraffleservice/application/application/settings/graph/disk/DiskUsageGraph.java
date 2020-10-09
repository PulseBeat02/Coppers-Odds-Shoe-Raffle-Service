package com.pulsebeat02.main.gui.application.settings.graph.disk;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DiskUsageGraph extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public volatile boolean cont = true;

	public static String percent = "";

	public DiskUsageGraph() {

		XYSeries usage = new XYSeries("Disk Usage (%)");
		XYSeriesCollection data = new XYSeriesCollection(usage);

		Thread update = new Thread(new Runnable() {
			@Override
			public void run() {

				JFreeChart chart = ChartFactory.createXYLineChart("Disk Usage Graph", "Time (Seconds)", "Usage (%)",
						data, PlotOrientation.VERTICAL, true, true, false);

				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setPreferredSize(new Dimension(375, 325));

				XYPlot xyPlot = (XYPlot) chart.getPlot();
				NumberAxis range = (NumberAxis) xyPlot.getRangeAxis();
				range.setRange(0.0, 100.0);

				add(chartPanel);

				double time = 0;
				while (cont) {
					try {
						int usageNumber = getUsage();
						percent = usageNumber + "%";
						usage.add(time, usageNumber);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					chartPanel = new ChartPanel(ChartFactory.createXYLineChart("Disk Usage Graph", "Time (Seconds)",
							"Usage (%)", data, PlotOrientation.VERTICAL, true, true, false));
					repaint();

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					time += 0.5;

				}

			}
		});
		update.start();

	}

	public static int getUsage() throws IOException {

		int sum = 0;

		for (int i = 0; i < 7; i++) {

			long total = new File("/").getTotalSpace();
			long free = new File("/").getFreeSpace();

			sum += (int) ((float) (total - free) / total * 100);

		}

		return sum / 7;

	}

}
