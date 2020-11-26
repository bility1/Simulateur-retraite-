
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Mes amis !</title>
</head>
<body>
<H1>Bonjour ${courant.login}</H1>
<p>Voici vos amis :</p>
<ul>
<c:forEach items="${courant.amis}" var="ami">
    <li>
        ${ami.login}
    </li>
</c:forEach>
</ul>

<p>De nouveaux amis ? </p>
<ul>
    <c:forEach items="${suggestions}" var="suggestion">
        <li>
            <form method="post">
                <input type="hidden" name="membre" value="${suggestion.login}">
                ${suggestion.login} <button type="submit" name="TODO" value="ami">Soyons amis!</button>
            </form>
        </li>
    </c:forEach>
</ul>


<form method="post">
    <button type="submit" name="TODO" value="mur">RETOUR AU MUR</button>
</form>
</body>
</html>
