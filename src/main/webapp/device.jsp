<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>CaveSurvey BT Emulator</title>
    </head>
<body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script>
    function applyName() {
        $(applyResult).load("server?action=updateName&btName=<%=request.getAttribute("deviceBluetoothName")%>");
     }

    function checkName() {
         $(checkResult).load("server?action=checkName");
      }
</script>

<h2>Emulate <%=request.getAttribute("deviceName")%> :</h2>
    <ul>
        <li>Make sure bluetooth radio is enabled</li>
        <li>Make sure running as root</li>
        <li>Make sure system bluetooth device name matches '<%=request.getAttribute("deviceBluetoothName")%>':

            <ul>
                <li>'hciconfig hci0 name <%=request.getAttribute("deviceBluetoothName")%>' ->
                    <input type="button" id="applyName" value="Apply" onClick="applyName()"/>
                    <label id="applyResult" />
                </li>
                <li>'hciconfig -a' to test ->
                   <input type="button" id="checkName" value="Check" onClick="checkName()"/>
                   <label id="checkResult" />
                </li>
                </li>
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
</body>
</html>
