<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
    <jsp:attribute name="title">
      <fmt:message key="categoryDelete.label"/>
    </jsp:attribute>
    <jsp:body>
		<p>Naciśnij, aby potwierdzić usunięcie "${category.name}" z bazy danych. Żadne wydarzenia nie zostaną naruszone.</p>
		<form method="post" action="?">
			<button type="submit" name="delete" class="btn btn-danger"><fmt:message key="categoryForm.delete"/></button>
		</form>
    </jsp:body>
</t:genericpage>
