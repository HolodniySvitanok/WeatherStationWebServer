package com.holodniysvitanok.weatherstationwebserver.services.chart;

import java.io.IOException;
import java.io.OutputStream;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class Chart {
    private int width = 900;
    private int height = 300;
    
    public Chart() {
    }

    public void setSize(int width, int height){
        this.height = height;
        this.width = width;
    }
    
    public void createImage(Pointers pointers, OutputStream ous) throws IOException {

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeries ax = new TimeSeries(pointers.getyName(),Hour.class);
        for (Point gp : pointers.getListPointers()) {
            ax.addOrUpdate(new Hour(gp.getDate()), gp.getY());
        }
        dataset.addSeries(ax);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(pointers.getGraphicName(),
                pointers.getxName(),
                pointers.getyName(),
                dataset,
                false, false, false);

        ChartUtilities.writeChartAsPNG(ous, chart, width, height);
    }

}
