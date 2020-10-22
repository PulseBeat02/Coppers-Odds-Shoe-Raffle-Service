package com.pulsebeat02.shoeraffleservice.application.settings.graph;

import java.awt.Dimension;
import java.lang.management.ManagementFactory;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ProcessorUsageGraph extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create the panel.
     */
    public volatile boolean cont = true;

    public static String percent = "";

    public ProcessorUsageGraph() {

	XYSeries usage = new XYSeries("CPU Usage (%)");
	XYSeriesCollection data = new XYSeriesCollection(usage);

	Thread update = new Thread(new Runnable() {
	    @Override
	    public void run() {

		JFreeChart chart = ChartFactory.createXYLineChart("CPU Usage Graph", "Time (Seconds)", "Usage (%)",
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

		    } catch (Exception e1) {

			e1.printStackTrace();

		    }

		    chartPanel = new ChartPanel(ChartFactory.createXYLineChart("CPU Usage Graph", "Time (Seconds)",
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

    public static int getUsage() throws Exception {

	int sum = 0;

	for (int i = 0; i < 7; i++) {

	    MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
	    ObjectName name = ObjectName.getInstance("java.lang:type=OperatingSystem");
	    AttributeList list = mbs.getAttributes(name, new String[] { "ProcessCpuLoad" });

	    Attribute att = (Attribute) list.get(0);
	    Double value = (Double) att.getValue();

	    int percent = (int) (value * 100) * 5;

	    if (percent > 100) {

		percent = 100;

	    }

	    sum += percent;

	}

	return sum / 7;

    }

}
