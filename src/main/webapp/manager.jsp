<%@ page import="java.util.List" %>
<%@ page import="java.util.LinkedList" %><%--
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
<div class="center">
    <h1><span id="tiny">Tiny</span> &nbsp;<span id="search">Search</span></h1>
</div>
<div class="auto">
<form>
    <fieldset class="fieldset-auto-width">
        <legend>Manage:</legend>
<div>
    <%
        String message=(String) request.getAttribute("message");
        message=(message==null?"":message);
    %>
    <span id="message"><%=message%></span>
</div>

<form class="auto" action="manageDB" method="POST">
    <input type="radio" name="action" value="start" onclick="showInput()">Start
    <input type="radio" name="action" value="stop" onclick="hideInput()">Stop
    <br>

    <div id="urlinput">
    <label for="url">Enter the starting URL:</label>
    <input type="URL" id="url" name="url" value="" required>
    </div>
    <input type="submit">
</form>
    </fieldset>
</form>
</div>
<hr>
<h5>Database Tables</h5>
<form action="database" method="POST">
    <input type="radio" name="database" value="page">Page
    <input type="hidden" name="page_startingindex" value="0">
    <input type="radio" name="database" value="word">Word
    <input type="radio" name="database" value="page_word">Page_Word
    <br>
    <button type="submit">Query</button>
</form>
<div id="result">
    <table>
        <tr>
            <%
                String table = (String) request.getAttribute("table");
                if(table==null) table="";
                String[][] tables={
                        {"Page ID","Page URL","Page Title","Last Modified"},
                        {"Word ID", "Word"},
                        {"Page ID","Word ID","Frequency","Description"}
                };
                if(table.equals("page")) {
                    int index=0;
                    while(index<tables[0].length) {
            %>
                <th>
                    <%=tables[0][index]%>
                </th>
            <%
                        ++index;
                    }
                }
                else if(table.equals("word")){
                    int index=0;
                    while(index<tables[1].length) {
            %>
                    <th>
                        <%=tables[1][index]%>
                    </th>
            <%
                        ++index;
                    }
                }
                else if(table.equals("page_word")){
                    int index=0;
                    while(index<tables[2].length) {
            %>
                    <th>
                        <%=tables[2][index]%>
                    </th>
            <%
                        ++index;
                    }
                }
            %>

        </tr>
        <%

            List<List<String>> result= (List<List<String>>) request.getAttribute("result");
            if(result==null) result=new LinkedList<>();
            for (List<String> row : result) {
        %>
            <tr>
                <%
                    for (String data : row) {
                %>
                <td>
                    <%=data%>
                </td>
                <%
                    }
                %>
            </tr>
        <%
            }
        %>
    </table>
</div>

<script>
    let inputdiv=document.getElementById("urlinput");
    let urlinput=document.getElementById("url");
    let messagediv=document.getElementById("message");
    let resultdiv=document.getElementById("result");

    function showInput(){
        inputdiv.style.display="block";
        urlinput.value="";
        urlinput.removeAttribute("disabled");
        messagediv.innerText="";
    }
    function hideInput(){
        inputdiv.style.display="none";
        urlinput.value="";
        urlinput.disabled='true';
        messagediv.innerText="";
    }
</script>
</body>
</html>
