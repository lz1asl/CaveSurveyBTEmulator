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
    private StringBuilder log = new StringBuilder();


    public Map start(String aConfig) {

        deviceDef = new Util().loadDeviceDefinition(aConfig);
        log = new StringBuilder();

        Map<String, Object> info = new HashMap<String, Object>();

        log.append("Starting\n");

//        new Thread( new WaitThread()).start();


        // retrieve the local Bluetooth device object
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

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
                System.out.println("bluecove:" + bluecoveVersion);
                System.out.println("stack:" + LocalDevice.getProperty("bluecove.stack"));
                System.out.println("stack version:" + LocalDevice.getProperty("bluecove.stack.version"));
                System.out.println("radio manufacturer:" + LocalDevice.getProperty("bluecove.radio.manufacturer"));
                System.out.println("radio version:" + LocalDevice.getProperty("bluecove.radio.version"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.append("Failure:\n").append(e).append("\n");
            info.put("Error", e.getMessage());
            stop();
        }

        return info;
    }

    public void stop() {
        System.out.println("Stop server TODO");
        log.append("Exiting\n");
        // TODO
    }

    public String getLog() {
        return log.toString();
    }

}


