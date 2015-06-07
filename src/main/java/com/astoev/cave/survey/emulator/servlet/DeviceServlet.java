package com.astoev.cave.survey.emulator.servlet;

import com.astoev.cave.survey.emulator.Util;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by astoev on 6/7/15.
 */
public class DeviceServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int device = Integer.parseInt(request.getParameter("device"));
        System.out.println("device = " + device);
        String deviceConfig;

        switch (device) {
            case 0:
                // CEM
                deviceConfig = "CEMiLDM150.json";
                break;

            default :
                throw new RuntimeException("Not implemented");
        }

        Map<String, Object> deviceDef = new Util().loadDeviceDefinition(deviceConfig);

        System.out.println("deviceDef = " + deviceDef);

        request.setAttribute("deviceName", deviceDef.get("displayName"));
        request.setAttribute("deviceBluetoothName", deviceDef.get("btName"));
        request.setAttribute("deviceConfig", deviceConfig);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/device.jsp");
        dispatcher.forward(request, response);
    }
}
