<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Match Scoreboard</title>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="centered-block">
  <h2 class="main-title">Match Scoreboard</h2>
  <h1 id="score-display">${match.player1Score}-${match.player2Score}</h1>

  <table class="matches-table">
    <thead>
    <tr>
      <th>Player</th>
      <th>Game</th>
      <th>Sets</th>
      <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <tr>
      <td>${match.player1.name}</td>
      <td id="player1-sets">${match.player1Game}</td>
      <td id="player1-set-count">${match.player1Set}</td>
      <td>
        <form action="match-score" method="post">
          <input type="hidden" name="uuid" value="${match.matchId}">
          <input type="hidden" name="winner" value="1">
          <button type="submit" class="score-button">Add Point</button>
        </form>
      </td>
    </tr>
    <tr>
      <td>${match.player2.name}</td>
      <td id="player2-sets">${match.player2Game}</td>
      <td id="player2-set-count">${match.player2Set}</td>
      <td>
        <form action="match-score" method="post">
          <input type="hidden" name="uuid" value="${match.matchId}">
          <input type="hidden" name="winner" value="2">
          <button type="submit" class="score-button">Add Point</button>
        </form>
      </td>
    </tr>
    </tbody>
  </table>
</div>
</body>
</html>