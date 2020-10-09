package com.pulsebeat02.main.gui.application.settings.graph.memory;

import java.awt.Dimension;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import com.sun.management.OperatingSystemMXBean;

public class MemoryUsageGraph extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public volatile boolean cont = true;

	public String percent = "";

	public MemoryUsageGraph() {

		XYSeries usage = new XYSeries("Memory Usage (%)");
		XYSeriesCollection data = new XYSeriesCollection(usage);

		Thread update = new Thread(new Runnable() {
			@Override
			public void run() {

				JFreeChart chart = ChartFactory.createXYLineChart("Memory Usage Graph", "Time (Seconds)", "Usage (%)",
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

					chartPanel = new ChartPanel(ChartFactory.createXYLineChart("Memory Usage Graph", "Time (Seconds)",
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

			OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
			
			long total = osBean.getTotalMemorySize();
			long free = osBean.getFreeMemorySize();
			long used = total - free;

			sum += (int) ((float) used / total * 100);

		}

		return sum / 7;

	}

}
