package com.astoev.cave.survey.emulator.bluetooth;

import com.astoev.cave.survey.emulator.Util;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by astoev on 6/2/15.
 */
public class BluetoothServer {

    private Map<String, Object> deviceDef;
    private static StringBuilder log = new StringBuilder();
    private static ConnectThread thread;
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

        new Thread(new ConnectThread()).start();

        return info;
    }

    public void stop() {
        System.out.println("Stop server");
        log.append("Exiting\n");
        if (thread != null) {
            thread.stop();
        }
    }

    public String getLog() {
        return log.toString();
    }

    class ConnectThread implements Runnable {

        private boolean running = true;

        @Override
        public void run() {


            while (running) {
                try {
                    log.append("Waiting for connection...");
                    connection = notifier.acceptAndOpen();
                    log.append("Connected");

//                Thread processThread = new Thread(new ProcessConnectionThread(connection));
//                processThread.start();

                } catch (Exception e) {
                    log.append("Failure:\n").append(e).append("\n");
                    stop();
                    return;
                }
            }
        }

        public void stop() {
            running = false;
        }
    }


}



