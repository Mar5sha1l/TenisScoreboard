<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Новый матч</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="centered-block">
    <h2 class="main-title">Создание нового матча</h2>

    <c:if test="${not empty errorMessage}">
        <div style="color: red; font-weight: bold;">
                ${errorMessage}
        </div>
    </c:if>

    <form method="POST" action="${pageContext.request.contextPath}/new-match" class="new-match-form">
        <div class="form-group">
            <label for="player1">Имя игрока 1:</label>
            <input type="text" id="player1" name="player1" required>
        </div>
        <div class="form-group">
            <label for="player2">Имя игрока 2:</label>
            <input type="text" id="player2" name="player2" required>
        </div>
        <button type="submit" class="submit-button">Начать матч</button>
    </form>
</div>
</body>
</html>
