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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" href="manager.css">
</head>
<body>
<div class="searchPart">
    <div class="searchName">
        <p><span id="tiny_">Tiny</span><span id="search_">Search</span></p>
    </div>
    <div class="searchBar">
        <form action="search">
            <input type="text" id="keywords" class="form-control" name="search">
            <button style="background-color: Transparent; background-repeat:no-repeat; border: none;
            cursor:pointer;" type="submit" id="keywords" name="search"><i class="fas fa-search"></i></button>
            <%--<button type="submit" class="btn">Search</button>--%>
        </form>
    </div>
</div>
<div class="center">
    <h1><span id="tiny">Tiny</span><span id="search">Search</span></h1>
</div>
<div class="auto">
<form action="manageDB" method="POST">
    <fieldset class="fieldset-auto-width">
        <legend>Manage Scraping:</legend>
            <div>
                <%
                    String message=(String) request.getAttribute("message");
                    Integer status= (Integer) request.getAttribute("status");
                    status=(status==null)?0:status;
                    message=(message==null?"":message);
                %>
                <input type="hidden" name="status" value="<%=status%>">
                <span class="auto" id="message" style="margin-bottom:5px; font-weight: bold; color:<%=
                      status==0?"green":status==1?"orange":"red"
                %>"><%=message%></span>
            </div>
            <div class="choice-box">
                <input type="radio" name="action" value="start" onclick="showInput()">Start
                <input class="rs" type="radio" name="action" value="stop" onclick="hideInput()">Stop
            </div>

                <div class="auto" id="urlinput">
                <label for="url">Enter the starting URL:</label>
                <input type="URL" id="url" name="url" value="" required>
                </div>
            <div>
                <input class="btn" type="submit">
            </div>
    </fieldset>
</form>
</div>
<form class="auto2" style="white-space: nowrap" action="database" method="POST">
    <fieldset>
        <legend>Database Tables:</legend>
        <input type="radio" name="database" value="page">Page
        <input type="hidden" name="page_startingindex" value="0">
        <input class="rs" type="radio" name="database" value="word">Word
        <input class="rs" type="radio" name="database" value="page_word">Page_Word
        <button class="btn2" type="submit">Query</button>

        <div id="result">
            <table id="database">
                <tr>
                    <%
                        String table = (String) request.getAttribute("table");
                        if(table==null) table="";
                        String[][] tables={
                                {"Page ID","Page URL","Page Title","Last Modified"},
                                {"Word ID", "Word"},
                                {"Page ID","Word ID","Frequency","Description"}
                        };
                        System.out.println(message+"**");
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
    </fieldset>
</form>


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
