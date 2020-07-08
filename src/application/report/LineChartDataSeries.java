package application.report;

import javafx.scene.chart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class LineChartDataSeries<T>{
    private List<XYChart.Series<String,Number>> list = new ArrayList<>();

    public boolean addSeries(XYChart.Series<String,Number> series){
        return list.add(series);
    }
    public boolean removeSerie(XYChart.Series<String,Number> series){
        return list.remove(series);
    }
    public List<XYChart.Series<String,Number>> getSeries(){
        return list;
    }
}
