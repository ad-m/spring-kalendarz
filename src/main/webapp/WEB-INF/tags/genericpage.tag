<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@tag description="Bootstrap Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="extra_css" fragment="true" %>
<%@attribute name="extra_js" fragment="true" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title><jsp:invoke fragment="title"/></title>

    <!-- Bootstrap core CSS -->
   <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
	<jsp:invoke fragment="extra_css"/>
  </head>

  <body>

    <nav class="navbar navbar-toggleable-md navbar-inverse bg-inverse">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <a class="navbar-brand" href="/kalendarz/">Kalendarz</a>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          	<c:choose>
			    <c:when test="${empty user}">
			    	<li class="nav-item"><a class="nav-link" href="/kalendarz/login/"><fmt:message key="navbar.login_link"/></a></li>
			        <li class="nav-item"><a class="nav-link" href="/kalendarz/register/"><fmt:message key="navbar.register_link"/></a></li>
			    </c:when>
	    		<c:otherwise>
			        <li class="nav-item"><a class="nav-link" href="/kalendarz/logout"><fmt:message key="navbar.logout_link"/></a></li>
			        <li class="nav-item"><a class="nav-link" href="/kalendarz/events/~create"><fmt:message key="navbar.event_add"/></a></li>
			        <li class="nav-item"><a class="nav-link" href="/kalendarz/events/"><fmt:message key="navbar.event_list"/></a></li>
			    </c:otherwise>
			</c:choose>
			
          <li class="nav-item dropdown">
          	<c:choose>
			    <c:when test="${empty user}">
		            <a class="nav-link disabled" href="#" data-toggle="dropdown" ><fmt:message key="navbar.category_link"/></a>
			    </c:when>
	    		<c:otherwise>
		            <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" ><fmt:message key="navbar.category_link"/></a>
		            <div class="dropdown-menu" aria-labelledby="dropdown01">
						<c:forEach items="${userCategories}" var="element"> 
						   <a class="dropdown-item" href="/kalendarz/categories/${element.id}"><c:out value="${element.name}"/></a>
						</c:forEach>
					  <div class="dropdown-divider"></div>
		              <a class="dropdown-item" href="/kalendarz/categories/"><fmt:message key="navbar.category_add"/></a>
		            </div>
			    </c:otherwise>
			</c:choose>

          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0" action="/kalendarz/event-search">
          <input class="form-control mr-sm-2" type="text" name="query" placeholder="<fmt:message key="navbar.search_placeholder"/>">
          <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="navbar.search_btn"/></button>
        </form>
      </div>
    </nav>

    <div class="container">
      <!-- Example row of columns -->
		<h1><jsp:invoke fragment="title"/></h1>
		<c:choose>
			    <c:when test="${empty message}">
			    </c:when>
	    		<c:otherwise>
					<div class="alert alert-success" role="alert">
						    <strong>Well done!</strong> ${message.message}
					</div>
			    </c:otherwise>
			</c:choose>
 	     <jsp:doBody/>
      <hr>

      <footer>
        <p id="copyright"><fmt:message key="footer"/></p>
      </footer>
    </div> <!-- /container -->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<jsp:invoke fragment="extra_js"/>
  </body>
</html>
