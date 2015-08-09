package com.astoev.cave.survey.emulator;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    public static String executeCommand(String[] command) {
        try {
            System.out.println("command = " + Arrays.toString(command));
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
            String errors = IOUtils.toString(p.getErrorStream());
            System.out.println("errors = " + errors);
            String output = IOUtils.toString(p.getInputStream());
            System.out.println("output = " + output);
            if (StringUtils.isNotEmpty(errors) || p.exitValue() != 0) {
                return "Exit code " + p.exitValue() + " : " + errors;
            } else {
                return "Success " + output;
            }
        } catch (Exception e) {
            return  "Error : " + e.getMessage();
        }
    }
}
