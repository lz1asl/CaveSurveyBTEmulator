package com.astoev.cave.survey.emulator.servlet;

import com.astoev.cave.survey.emulator.Util;
import com.astoev.cave.survey.emulator.bluetooth.BluetoothServer;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

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
        String device = request.getParameter("device");
        Map<String, String> devices = new Util().getDevicesConfig();
        String configFile = devices.get(device);

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
            if (server != null) {
                server.stop();
            }

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else if ("log".equals(action)) {
            if (server != null) {
                IOUtils.write(server.getLog(), response.getWriter());
                response.getWriter().flush();
            }
        } else if ("updateName".equals(action)) {
            String[] command = { "hciconfig", "hci0", "name",  request.getParameter("btName")};
            response.getWriter().write(Util.executeCommand(command));
        } else if ("checkName".equals(action)) {
            String[] command = {"hciconfig", "-a"};
            response.getWriter().write(Util.executeCommand(command));
        } else if ("simulate".equals(action)) {
            server.simulateMeasurement();
        }
    }
}
