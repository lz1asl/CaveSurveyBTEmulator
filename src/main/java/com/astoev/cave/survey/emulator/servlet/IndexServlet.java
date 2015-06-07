package com.astoev.cave.survey.emulator.servlet;

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

/**
 * Created by astoev on 6/7/15.
 */
public class IndexServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InputStream in = getClass().getClassLoader().getResourceAsStream("/definitions/devices.json");
        try {
            // load list of devices
            String [] devices = new Gson().fromJson(new InputStreamReader(in), String[].class);
            System.out.println("devices found " + devices.length);
            request.setAttribute("devices", devices);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/devices.jsp");
            dispatcher.forward(request, response);
        } finally {
            IOUtils.closeQuietly(in);
        }

    }
}
