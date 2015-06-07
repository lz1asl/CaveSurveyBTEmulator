package com.astoev.cave.survey.emulator.servlet;

import com.astoev.cave.survey.emulator.Util;
import com.astoev.cave.survey.emulator.bluetooth.BluetoothServer;
import org.apache.commons.io.IOUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by astoev on 6/1/15.
 */

public class BluetoothServerServlet extends HttpServlet {

    private static BluetoothServer server = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String configFile = request.getParameter("config");

        System.out.println("request to " + action  + " for " + configFile);

        if ("start".equals(action)) {
            server = new BluetoothServer();

            Map<String, Object> deviceDef = new Util().loadDeviceDefinition(configFile);
            request.setAttribute("deviceName", deviceDef.get("displayName"));

            Map info = server.start(configFile);

            request.setAttribute("info", info);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/server.jsp");
            dispatcher.forward(request, response);
        } else if ("stop".equals(action)) {
            server.stop();

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else if ("log".equals(action)) {
            IOUtils.write(server.getLog(), response.getWriter());
            response.getWriter().flush();
        }
    }
}