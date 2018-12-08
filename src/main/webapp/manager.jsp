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
    <link rel="stylesheet" href="manager.css">
</head>
<body>
<div>
    <%
        String message=(String) request.getAttribute("message");
        message=(message==null?"":message);
    %>
    <span id="message"><%=message%></span>
</div>

<form action="manageDB" method="POST">
    <input type="radio" name="action" value="start" onclick="showInput()">Start
    <input type="radio" name="action" value="stop" onclick="hideInput()">Stop
    <br>

    <div id="urlinput">
    <label for="url">Enter the starting URL:</label>
    <input type="URL" id="url" name="url" value="" required>
    </div>
    <input type="submit">
</form>

<hr>


<script>
    let inputdiv=document.getElementById("urlinput");
    let urlinput=document.getElementById("url");
    let messagediv=document.getElementById("message");

    function showInput(){
        inputdiv.style.display="block";
        urlinput.value="";
        urlinput.removeAttribute("disabled");
        messagediv.outerText="";

    }
    function hideInput(){
        inputdiv.style.display="none";
        urlinput.value="";
        urlinput.disabled='true';
        messagediv.outerText="";
    }
</script>
</body>
</html>
