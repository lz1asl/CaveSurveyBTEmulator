package com.astoev.cave.survey.emulator.bluetooth;

import com.astoev.cave.survey.emulator.Util;
import org.apache.commons.io.IOUtils;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by astoev on 6/2/15.
 */
public class BluetoothServer {

    private Map<String, Object> deviceDef;
    private static StringBuilder log = new StringBuilder();
    private static ConnectThread connectThread;
    private static CommunicationThread communicationThread;
    StreamConnectionNotifier notifier;
    StreamConnection connection = null;


    public Map start(String aConfig) {

        deviceDef = new Util().loadDeviceDefinition(aConfig);
        log = new StringBuilder();

        Map<String, Object> info = new HashMap<String, Object>();

        log.append("Starting\n");

//        new Thread( new WaitThread()).start();


        // retrieve the local Bluetooth device object
        LocalDevice local = null;

        // setup the server to listen for connection
        try {
            local = LocalDevice.getLocalDevice();

            local.setDiscoverable(DiscoveryAgent.GIAC);
            log.append("Make ").append(local.getFriendlyName()).append(" discoverable\n");

            String uuidString = (String) deviceDef.get("UUID");
            UUID uuid = new UUID(uuidString, false);
            info.put("UUID", uuidString);

            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);

            info.put("Local btaddr", local.getBluetoothAddress());
            info.put("Local name ", local.getFriendlyName());

            String bluecoveVersion = LocalDevice.getProperty("bluecove");
            if (bluecoveVersion != null) {
                info.put("bluecove", bluecoveVersion);
                info.put("stack", LocalDevice.getProperty("bluecove.stack"));
                info.put("stack version", LocalDevice.getProperty("bluecove.stack.version"));
                info.put("radio manufacturer", LocalDevice.getProperty("bluecove.radio.manufacturer"));
                info.put("radio version", LocalDevice.getProperty("bluecove.radio.version"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.append("Failure:\n").append(e).append("\n");
            info.put("Error", e.getMessage());
            stop();
        }

        connectThread = new ConnectThread();
        connectThread.start();

        return info;
    }

    public void stop() {
        System.out.println("Stop server");
        log.append("Exiting\n");
        if (connectThread != null) {
            connectThread.stopListening();
        }
        if (communicationThread != null) {
            communicationThread.interrupt();
        }
    }

    public String getLog() {
        return log.toString();
    }

    public void simulateMeasurement() {
        if (communicationThread != null) {
            communicationThread.simulateMessage();
        }
    }

    class ConnectThread extends Thread {

        private boolean running = true;

        @Override
        public void run() {




            while (running) {
                try {
                    log.append("Waiting for connection...");

                    if (connection != null) {
                        connection.close();
                    }

                    if (communicationThread != null) {
                        communicationThread.interrupt();
                    }

                    connection = notifier.acceptAndOpen();
                    log.append("Connected");

                    communicationThread = new CommunicationThread(connection);
                    communicationThread.start();

                    sleep(100);

                } catch (Exception e) {
                    log.append("Failure:\n").append(e).append("\n");
                    stopListening();
                    return;
                }
            }
        }

        public void stopListening() {
            running = false;
        }
    }

    class CommunicationThread extends Thread {

        private StreamConnection connection = null;
        private InputStream inputStream = null;
        private OutputStream outputStream = null;

        public CommunicationThread(StreamConnection aConnection) {
            super();
            connection = aConnection;
        }

        @Override
        public void run() {
            try {

                inputStream = connection.openInputStream();
                outputStream = connection.openOutputStream();
                while (true) {


                    // read
                    String command = IOUtils.toString(inputStream);
                    System.out.println("command = " + command);
                    log.append("Got:\n").append(command).append("\n");

                    // respond
                    Map<String, String> messages = (Map<String, String>) deviceDef.get("messages");
                    if (messages.containsKey(command)) {
                        String response = messages.get(command);
                        System.out.println("response = " + response);
                        log.append("Respond:\n").append(response).append("\n");
                        IOUtils.write(response, outputStream);
                    } else {
                        // bad input
                        System.err.println("Unrecognized");
                        log.append("Failure:\n").append("not recognized").append("\n");
                    }


                    sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.append("Failure:\n").append(e).append("\n");
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly(outputStream);
                try {
                    connection.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        public void simulateMessage() {
            try {

                System.out.println("Simulating");
                String[] measurements = (String[]) deviceDef.get("measurements");
                String measurement = measurements[((int) (Math.random() * measurements.length))];

                log.append("Simulate:\n").append(measurement).append("\n");
                IOUtils.write(measurement, outputStream);

            } catch (IOException e) {
                e.printStackTrace();
                log.append("Failure:\n").append(e).append("\n");
            }
        }
    }


}



