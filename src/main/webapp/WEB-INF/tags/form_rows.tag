<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="label" fragment="true" %>
<%@attribute name="input" fragment="true" %>
<div class="form-group row">
	<label class="col-sm-2 col-form-label">
		<jsp:invoke fragment="label"/>
	</label>
	<div class="col-sm-10">
		<jsp:invoke fragment="input"/>
	</div>
</div>