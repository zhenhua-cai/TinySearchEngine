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
    <title>Manager</title>
</head>
<body>
<form action="/manageDB" method="POST">
    <label for="start">Start</label>
    <input type="radio" name="changestatus" value="start" id="start">
    <br>
    <label for="start">Stop</label>
    <input type="radio" name="changestatus" value="stop" id="stop">
    <button type="submit">button</button>
</form>

</body>
</html>
