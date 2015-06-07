package com.astoev.cave.survey.emulator.servlet;

import com.astoev.cave.survey.emulator.bluetooth.BluetoothServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by astoev on 6/1/15.
 */

public class BluetoothServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("request to " + request.getParameter("action") + " for " + request.getParameter("device"));

        if ("start".equals(request.getParameter("action"))) {
            BluetoothServer.start(request.getParameter("device"));
        } else {
            BluetoothServer.stop();
        }
    }
}
