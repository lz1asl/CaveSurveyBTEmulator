<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>CaveSurvey BT Emulator</title>
    </head>
<body>
<h2><%=request.getAttribute("deviceName")%>:</h2>
<p>

    Server Info : <br/>
    <textarea rows="8" cols="80">
        <%=request.getAttribute("info")%>
    </textarea>

    <br/><br/>

    Server Log :  <br/>
    <textarea rows="20" cols="80" id="log">
        ...
    </textarea>

    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script>
        $(document).ready(
                function() {
                    setInterval(function() { // reload
                        $( "#log" ).load( "server?action=log" ); // from server
                        $('#log').scrollTop($('#log')[0].scrollHeight); // scroll
                    }, 2000);
                });

        function readMeasure() {
            $.get("server?action=simulate");
        }
    </script>

    <br/>
    <button type="button" onClick="readMeasure()">Read measure</button>
    <br/><br/>
    <form action="server">
        <input type="hidden" name="action" value="stop"/>
        <input type="submit" value="Stop"/>
    </form>
</p>
</body>
</html>
