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




       /* // waiting for connection
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
        }*/
    }
}