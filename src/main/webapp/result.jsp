<%@ page import="DBConnection.Page" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link rel="stylesheet" href="result.css">
</head>
<body>
<div class="searchPart">
    <div class="searchName">
        <p><span id="tiny">Tiny</span><span id="search">Search</span></p>
    </div>
    <div class="searchBar">
        <form action="search">
            <input type="text" id="keywords" class="form-control" name="search">
            <span typeof="summit"><i class="fas fa-search"></i></span>
            <%--<button type="submit" class="btn">Search</button>--%>
        </form>
    </div>
</div>
<div class="result">
    <br/>
    <ul style="list-style-type:none">
        <%
            List<Page> results=(List<Page>)request.getAttribute("results");
            String keywords=(String)request.getAttribute("keyword");
            for(Page p:results){
        %>
        <li>
            <a href="<%=p.getUrl()%>"><%=p.getTitle()%></a>
            <br>
            <small><a href="<%=p.getUrl()%>"><%=p.getUrl()%></a></small>
            <div>
                <%=p.getDescription()%>
            </div>
            <small class="form-text text-muted">Last Modified: &nbsp;<%=p.getLastModified()%>
            </small>
            <br/>
        </li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>
