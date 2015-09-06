<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>CaveSurvey BT Emulator</title>
    </head>
<body>
<h2>Choose CaveSurvey supported device:</h2>
<p>

    <form action="device">
        <select name="device">
        <%
            String[] devices = (String[]) request.getAttribute("devices");
            for (String device : devices) {
                out.println("<option value=\"" + device + "\">" +device + "</option>");
            }
        %>

        </select>
        <input type="submit" value="Next"/>
    </form>
</p>
</body>
</html>
