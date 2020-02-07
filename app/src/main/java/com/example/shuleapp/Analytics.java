package com.example.shuleapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

public class Analytics extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analytics);
        final GraphView graphView = (GraphView) findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(
                new DataPoint[]{
                        new DataPoint(1,57),
                        new DataPoint(2,150),
                        new DataPoint(3,300),
                }

        );

        graphView.addSeries(series);
        series.setSpacing(10);
        series.setAnimated(true);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.WHITE);
        graphView.setTitle("SCHOOL APP ANALYTICS");
        graphView.setTitleColor(Color.WHITE);
        graphView.setCursorMode(true);
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4,(int) Math.abs(data.getY()*255/6),100);
            }
        });
    }
}
