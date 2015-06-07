package com.astoev.cave.survey.emulator.servlet;

import com.astoev.cave.survey.emulator.bluetooth.BluetoothServer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by astoev on 6/1/15.
 */

public class BluetoothServlet extends HttpServlet {

    private static BluetoothServer server = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        System.out.println("request to " + action  + " for " + request.getParameter("config"));

        if ("start".equals(action)) {
            server = new BluetoothServer();
            server.start(request.getParameter("config"));

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/server.jsp");
            dispatcher.forward(request, response);
        } else if ("stop".equals(action)) {
            server.stop();
        } else if ("log".equals(action)) {
           // ...
        }


    }
}
