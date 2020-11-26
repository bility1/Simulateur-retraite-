
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Blabla</title>
</head>
<body>
<H1>Bonjour ${courant.login}</H1>
<p>Voici les derniers messages :</p>
<ul>
<c:forEach items="${messages}" var="message">
    <li>
        ${message.auteur.login} à ${message.dateMessage} : ${message.texteMessage}
    </li>
</c:forEach>
</ul>


<form method="post">
    <button type="submit" name="TODO" value="rediger">S'EXPRIMER !</button>
</form>

<form method="post">
    <button type="submit" name="TODO" value="amis">LA LISTE DE MES AMIS</button>
</form>


<form method="post">
    <button type="submit" name="TODO" value="logout">DECONNEXION</button>
</form>
</body>
</html>
