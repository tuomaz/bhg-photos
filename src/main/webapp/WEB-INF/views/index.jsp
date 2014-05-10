<!DOCTYPE html> 

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="sv" ng-app="fotonApp">
<head>
  <meta charset="utf-8">
  <title>BHG</title>
  <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.16/angular.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.2.16/angular-route.js"></script>
  <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

  <script src="resources/js/controllers.js"></script>
  <script src="resources/js/app.js"></script>
</head>
<body>
    <div ng-view></div>
</body>
</html>