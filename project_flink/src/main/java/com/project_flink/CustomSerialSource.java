package com.project_flink;

import com.fazecast.jSerialComm.SerialPort;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

public class CustomSerialSource extends RichSourceFunction<String> {

    private final String portName;
    private final int baudRate;
    private transient SerialPort serialPort;
    private volatile boolean isRunning = true;

    public CustomSerialSource(String portName, int baudRate) {
        this.portName = portName;
        this.baudRate = baudRate;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);

        serialPort = SerialPort.getCommPort(portName);
        serialPort.setBaudRate(baudRate);

        if (!serialPort.openPort()) {
            throw new RuntimeException("Não foi possível abrir a porta serial: " + portName);
        }
    }

    @Override
    public void run(SourceContext<String> ctx) {
        try {
            byte[] readBuffer = new byte[1024];
            StringBuilder messageBuffer = new StringBuilder();

            while (isRunning) {
                int numRead = serialPort.readBytes(readBuffer, readBuffer.length);
                if (numRead > 0) {

                    String data = new String(readBuffer, 0, numRead);
                    messageBuffer.append(data);


                    String bufferedData = messageBuffer.toString();
                    if (bufferedData.contains("{") && bufferedData.contains("}")) {

                        int start = bufferedData.indexOf("{");
                        int end = bufferedData.indexOf("}") + 1; // Inclui '}'

                        if (end > start) {
                            String json = bufferedData.substring(start, end);

                            synchronized (ctx.getCheckpointLock()) {
                                ctx.collect(json.trim());
                            }

                            messageBuffer = new StringBuilder(bufferedData.substring(end));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cancel() {
        isRunning = false;
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
        }
    }

    @Override
    public void close() throws Exception {
        super.close();
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
        }
    }
}
