package com.astoev.cave.survey.emulator.servlet;

import com.astoev.cave.survey.emulator.Util;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created by astoev on 6/7/15.
 */
public class DeviceServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String device = request.getParameter("device");
        System.out.println("device = " + device);

        Map<String, String> devices = new Util().getDevicesConfig();
        String deviceConfig = devices.get(device);
        System.out.println("deviceDef = " + deviceConfig);

        Map<String, Object> deviceDef = new Util().loadDeviceDefinition(deviceConfig);

        request.setAttribute("deviceName", deviceDef.get("displayName"));
        request.setAttribute("deviceBluetoothName", deviceDef.get("btName"));
        request.setAttribute("deviceConfig", deviceConfig);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/device.jsp");
        dispatcher.forward(request, response);
    }
}
