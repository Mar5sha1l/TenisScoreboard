<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>All Matches</title>
  <link rel="stylesheet" href="style.css">
  <script src="scripts/script.js"></script>
</head>
<body>
<div class="centered-block">
  <h1 class="main-title">All Matches</h1>

  <form id="searchForm" method="GET" action="${pageContext.request.contextPath}/matches">
    <label for="searchInput"></label>
    <input
            type="text"
            id="searchInput"
            name="name"
            placeholder="Search matches...">
    <button type="submit" class="action-button">Search</button>
  </form>

  <c:if test="${not empty param.name}">
    <p class="highlighted-text">Поиск на букву: <strong>${param.name}</strong></p>
  </c:if>

  <table class="matches-table">
    <thead>
    <tr>
      <th>Match ID</th>
      <th>Player 1</th>
      <th>Player 2</th>
      <th>Winner</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="match" items="${matches}">
      <tr>
        <td>${match.id}</td>
        <td>${match.player1.name}</td>
        <td>${match.player2.name}</td>
        <td>${match.winner.name}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
  <div class="pagination">
    <div class="pages-wrapper">
      <c:forEach var="page" begin="${currentPage - 1}" end="${currentPage + 1}" step="1">
        <c:if test="${page > 0 && page <= totalPages}">
            <a href="matches?page=${page}&name=${param.name}" class="page" data-page="${page}">${page}</a>
        </c:if>
      </c:forEach>
    </div>
  </div>
</div>
</body>
</html>
