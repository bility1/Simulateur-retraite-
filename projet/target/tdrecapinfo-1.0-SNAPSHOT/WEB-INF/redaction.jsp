
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Rédaction</title>
</head>
<body>

<h1>Que voulez-vous dire ?</h1>

<form method="post">
    Votre message :
    <input type="text" name="message">
<p>Publié dans un groupe ?
<select name="groupe">
    <option value="-1" selected>--</option>
    <c:forEach items="${groupes}" var="groupe">
        <option value="${groupe.idGroupe}">${groupe.nomGroupe}</option>
    </c:forEach>
</select>
</p>
    <button type="submit" name="TODO" value="publier">Publier</button>
</form>



</body>
</html>
