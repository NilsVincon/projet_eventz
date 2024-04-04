<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Page d'accueil</title>
</head>
<body>
<h1>Page d'accueil</h1>
<%-- Formulaire de connexion --%>
<form action="login" method="post">
    <label for="username">Nom d'utilisateur:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Mot de passe:</label>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="Se connecter">
</form>
</body>
</html>
