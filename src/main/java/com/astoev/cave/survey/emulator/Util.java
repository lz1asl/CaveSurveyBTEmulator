package com.astoev.cave.survey.emulator;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by astoev on 6/7/15.
 */
public class Util {

    public Map<String, Object> loadDeviceDefinition(String fileName) {

        System.out.println("loading definition = " + fileName);
        InputStream in = getClass().getClassLoader().getResourceAsStream("/definitions/" + fileName);
        try {
            // load list of devices
            return new Gson().fromJson(new InputStreamReader(in), Map.class);
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
