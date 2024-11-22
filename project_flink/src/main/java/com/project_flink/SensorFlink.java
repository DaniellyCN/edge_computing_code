package com.project_flink;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.commons.compress.archivers.sevenz.CLI;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;

import java.net.HttpURLConnection;
import java.net.URL;


public class SensorFlink {
    public static void main(String[] args) throws Exception {


        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();



        // Configurar a porta serial
//        SerialPort serialPort = SerialPort.getCommPort("COM5"); // Substituir pela porta correta
//        serialPort.setComPortParameters(9600, 8, 1, 0);
//        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 0, 0);
//        if (!serialPort.openPort()) {
//            System.out.println("Erro ao abrir a porta serial.");
//            return;
//        }

        DataStream<String> sensorData = env.addSource(new CustomSerialSource("COM5", 9600));

        DataStream<Tuple3<Double, Double, Integer>> parsedData = sensorData
                .map(new SensorDataMapper())
                .assignTimestampsAndWatermarks(
                        WatermarkStrategy.<Tuple3<Double, Double, Integer>>forMonotonousTimestamps()
                                .withTimestampAssigner((event, timestamp) -> System.currentTimeMillis())
                );
        DataStream<Tuple3<Double, Double, Integer>> aggregatedData = parsedData
                .windowAll(TumblingEventTimeWindows.of(Time.minutes(10)))
                .reduce((t1, t2) -> {
                    System.out.println("Reducing: " + t1 + " and " + t2);
                    Tuple3<Double, Double, Integer> result = new Tuple3<>(
                            t1.f0 + t2.f0,
                            t1.f1 + t2.f1,
                            t1.f2 + t2.f2
                    );
                    System.out.println("Result of reduce: " + result);
                    return result;
                });

        DataStream<Tuple2<Double, Double>> averagedData = aggregatedData
                .map(new SensorDataAverager())
                .returns(Types.TUPLE(Types.DOUBLE, Types.DOUBLE));


        averagedData.map(new MapFunction<Tuple2<Double, Double>, String>() {
            @Override
            public String map(Tuple2<Double, Double> data) throws Exception {
                String apiKey = "PKWZBFCDPM8G7PCX";
                String url = "https://api.thingspeak.com/update?api_key=" + apiKey
                        + "&field1=" + data.f0
                        + "&field2=" + data.f1;
                System.out.println("url antes de retornar"+url);

                try {
                    System.out.println("url depois de retornar"+url);
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.setRequestMethod("GET");
                    conn.getResponseCode(); // Garantir a execução da requisição
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return url;
            }
        });




        env.execute();
    }
}