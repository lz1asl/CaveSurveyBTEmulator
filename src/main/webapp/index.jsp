<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CaveSurvey BT Emulator</title>
</head>
<body>
<h2>Emulate CaveSurvey supported device:</h2>
<p>

    <form action="btServlet">
        <select name="device">
        <!--%

            String path =  getServletContext().getRealPath("/WEB-INF/classes/definitions");
            for (String device : new java.io.File(path).list()) {
                out.println("<option value=\"" + device + "\">" +device + "</option>");
            }
        % -->

        </select>
        <input type="hidden" name="action" value="start" />
        <input type="submit" value="Start"/>
    </form>
</p>
</body>
</html>
