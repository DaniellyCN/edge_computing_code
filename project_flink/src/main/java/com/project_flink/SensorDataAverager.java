package com.project_flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple2;

public class SensorDataAverager implements MapFunction<Tuple3<Double, Double, Integer>, Tuple2<Double, Double>> {
    @Override
    public Tuple2<Double, Double> map(Tuple3<Double, Double, Integer> data) throws Exception {
        System.out.println("Mapping data: " + data);
        double avgTemperature = data.f0 / (double) data.f2;
        double avgHumidity = data.f1 / (double) data.f2;
        System.out.println("Calculated averages: Temperature = " + avgTemperature + ", Humidity = " + avgHumidity);
        return new Tuple2<>(avgTemperature, avgHumidity);
    }

}