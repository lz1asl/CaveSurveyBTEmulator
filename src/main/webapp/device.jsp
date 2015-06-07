<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CaveSurvey BT Emulator</title>
</head>
<body>
<h2>Emulate <%=request.getAttribute("deviceName")%> :</h2>
    <ul>
        <li>Make sure bluetooth radio is enabled</li>
        <li>Make sure system bluetooth device name matches '<%=request.getAttribute("deviceBluetoothName")%>':

            <ul>
                <li>'<%=request.getAttribute("deviceBluetoothName")%>' to set</li>
                <li>'hciconfig -a' to test</li>
            </ul>
        </li>
        <li>
            <form action="server">
                <input type="hidden" name="config" value="<%=request.getAttribute("deviceConfig")%>"/>
                <input type="hidden" name="action" value="start"/>
                <input type="submit" value="Emulate device"/>
            </form>
        </li>
    </ul>
<p>
</p>
</body>
</html>
