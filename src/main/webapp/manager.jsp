<%--
  Created by IntelliJ IDEA.
  User: zhenhua cai
  Date: 2018-12-01
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager|CS355</title>
</head>
<body>
<%--<form action="/manageDB" method="POST">--%>
    <%--&lt;%&ndash;<label for="start">Start</label>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<input type="radio" name="changestatus" value="start" id="start">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<label for="start">Stop</label>&ndash;%&gt;--%>
    <%--&lt;%&ndash;<input type="radio" name="changestatus" value="stop" id="stop">&ndash;%&gt;--%>
    <%--&lt;%&ndash;<button type="submit">button</button>&ndash;%&gt;--%>
<%--</form>--%>
<form action="/manageDB" method="POST">
    <input type="hidden" value="" id="action" name="action">
    <button type="submit" class="btn btn-success"  id="start" onclick="">Start</button>
    <button type="submit" class="btn btn-success" id="stop">Stop</button>
</form>
<script>
    function start(){
        var input=document.getElementById("action");
        input.setAttribute("value","start");
    }
    function stop(){
        var input=document.getElementById("action");
        input.setAttribute("value","stop");
    }
</script>
</body>
</html>
