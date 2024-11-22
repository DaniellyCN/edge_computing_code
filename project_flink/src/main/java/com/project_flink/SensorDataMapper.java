package com.project_flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;


public class SensorDataMapper implements MapFunction<String, Tuple3<Double, Double, Integer>> {
    @Override
    public Tuple3<Double, Double, Integer> map(String line) {
        System.out.println(line);
        String[] parts = line.replace("{", "").replace("}", "").replace("\"", "").split(",");
        double temperature = Double.parseDouble(parts[0].split(":")[1]);
        System.out.println("temperatura obtida"+temperature);
        double humidity = Double.parseDouble(parts[1].split(":")[1]);
        System.out.println("umidade obtida"+humidity);

        return new Tuple3<>(temperature, humidity, 1);
    }
}