package com.astoev.cave.survey.emulator.bluetooth;

import com.intel.bluetooth.BluetoothStack;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

/**
 * Created by astoev on 6/7/15.
 */
public class WaitThread implements Runnable{

    /** Constructor */
    public WaitThread() {
    }

    @Override
    public void run() {
        waitForConnection();
    }

    /** Waiting for connection from devices */
    private void waitForConnection() {
        // retrieve the local Bluetooth device object
        LocalDevice local = null;

        StreamConnectionNotifier notifier;
        StreamConnection connection = null;

        // setup the server to listen for connection
        try {
            local = LocalDevice.getLocalDevice();

            local.setDiscoverable(DiscoveryAgent.GIAC);

            UUID uuid = new UUID("d0c722b07e1511e1b0c40800200c9a66", false);
            System.out.println(uuid.toString());

            String url = "btspp://localhost:" + uuid.toString() + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);



            System.out.println("Local btaddr is " + local.getBluetoothAddress());
            System.out.println("Local name is " + local.getFriendlyName());

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
            return;
        }

        // waiting for connection
        while(true) {
            try {
                System.out.println("waiting for connection...");
                connection = notifier.acceptAndOpen();
                System.out.println("After AcceptAndOpen...");

//                Thread processThread = new Thread(new ProcessConnectionThread(connection));
//                processThread.start();

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
}