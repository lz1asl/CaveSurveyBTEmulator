package com.astoev.cave.survey.emulator.bluetooth;

import com.astoev.cave.survey.emulator.Util;

import java.util.Map;

/**
 * Created by astoev on 6/2/15.
 */
public class BluetoothServer {

    public static void start(String aConfig) {

        Map<String, Object> deviceDef = new Util().loadDeviceDefinition(aConfig);


        new Thread( new WaitThread()).start();

    }

    public static void stop() {
        System.out.println("Stop server");
    }

}


