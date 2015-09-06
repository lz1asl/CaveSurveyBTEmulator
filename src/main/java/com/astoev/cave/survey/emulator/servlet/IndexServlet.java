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
public class IndexServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String> devices = new Util().getDevicesConfig();
        System.out.println("devices found " + devices.size());
        request.setAttribute("devices", devices.keySet().toArray(new String[]{}));

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/devices.jsp");
        dispatcher.forward(request, response);
    }
}
