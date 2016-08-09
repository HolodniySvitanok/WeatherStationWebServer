package com.holodniysvitanok.weatherstationwebserver.services;

import com.holodniysvitanok.weatherstationwebserver.entity.MeasurementPoint;
import com.holodniysvitanok.weatherstationwebserver.services.chart.Point;
import com.holodniysvitanok.weatherstationwebserver.services.chart.Pointers;
import java.text.SimpleDateFormat;
import java.util.List;

// преобразование набора точек измерения в точки для построения графика
public class PointsConverter {

    private String xName;
    private String yName;
    private String graphicName;

    public PointsConverter(String xName, String yName, String graphicName) {
        this.xName = xName;
        this.yName = yName;
        this.graphicName = graphicName;
    }

    public PointsConverter() {
        this.graphicName = "График";
        this.yName = "Y";
        this.xName = "Время";
    }

    // надпись в графике
    private void textInChart(List<MeasurementPoint> measurementPoints) {
        MeasurementPoint mPoint = measurementPoints.get(0);
        switch (mPoint.getTypeMeasurement()) {
            case Temperature: {
                yName = "Температура";
            }
            break;
            case Humidity: {
                yName = "Влажность";
            }
            break;
            case Pressure: {
                yName = "Давление";
            }
            break;
            default:
                yName = "Y";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        graphicName = "в период с " + sdf.format(measurementPoints.get(0).getDatePoint())
                + " до "
                + sdf.format(measurementPoints.get(measurementPoints.size() - 1).getDatePoint());
    }

    public Pointers pointMeasurementToPointChart(List<MeasurementPoint> measurementPoints, boolean autoDetectedChartName) {

        if (autoDetectedChartName) {
            textInChart(measurementPoints);
        }

        Pointers pointers = new Pointers(xName, yName, graphicName);

        for (MeasurementPoint measurementPoint : measurementPoints) {
            Point point = new Point();
            if (measurementPoint.getTypeMeasurement()
                    == MeasurementPoint.TypeMeasurement.Temperature) {
                float value = (float) measurementPoint.getValue() / 10;
                point.setY(value);
            } else {
                point.setY(measurementPoint.getValue());
            }
            point.setDate(measurementPoint.getDatePoint());
            pointers.addGraphicsPoint(point);
        }
        return pointers;
    }

    public Pointers pointMeasurementToPointChart(List<MeasurementPoint> measurementPoints) {
        return pointMeasurementToPointChart(measurementPoints, false);
    }

    public String getxName() {
        return xName;
    }

    public void setxName(String xName) {
        this.xName = xName;
    }

    public String getyName() {
        return yName;
    }

    public void setyName(String yName) {
        this.yName = yName;
    }

    public String getGraphicName() {
        return graphicName;
    }

    public void setGraphicName(String graphicName) {
        this.graphicName = graphicName;
    }
}
