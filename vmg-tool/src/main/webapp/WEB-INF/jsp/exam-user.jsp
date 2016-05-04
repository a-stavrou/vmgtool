<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>

<form:form commandName="examuser" action="start" cssClass="form-horizontal">

	<div class="form-group">
		<div class="page-header col-sm-2 control-label">
		  <h3><small>REFERENCE</small></h3>
		</div>
	</div>
	
			
	<div class="form-group">
		<label for="experimentalReference" class="col-sm-2 control-label">Experimental Reference:</label>
		<div class="col-sm-1">
			<form:input path="experimentalReference" cssClass="form-control" required="true" />
		</div>
	</div>

	<div class="form-group">
		<label for="participantReference" class="col-sm-2 control-label">Participant Reference:</label>
		<div class="col-sm-4">
			<form:input path="participantReference" cssClass="form-control" required="true" />
		</div>
	</div>

	<div class="form-group">
		<div class="page-header col-sm-2 control-label">
		  <h3><small>INSTITUTION</small></h3>
		</div>
	</div>
			
	<div class="form-group">
		<label for="university" class="col-sm-2 control-label">University Reference:</label>
		<div class="col-sm-4">
			<form:input path="university" cssClass="form-control" required="true" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="degree" class="col-sm-2 control-label">Degree:</label>
		<div class="col-sm-4">
			<form:input path="degree" cssClass="form-control" required="true" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="yearOfStudy" class="col-sm-2 control-label">Year of Study:</label>
		<div class="col-sm-4">
			<form:input path="yearOfStudy" cssClass="form-control" required="true" />
		</div>
	</div>
	
	<div class="form-group">
		<div class="page-header col-sm-2 control-label">
		  <h3><small>PARTICIPANT</small></h3>
		</div>
	</div>
		
	<div class="form-group">
		<label for="gender" class="col-sm-2 control-label">Gender:</label>
		<div class="col-sm-4">
			<form:input path="gender" cssClass="form-control" required="true" />
		</div>
	</div>
	
	<div class="form-group">
		<label for="age" class="col-sm-2 control-label">Age:</label>
		<div class="col-sm-4">
			<form:input path="age" cssClass="form-control" required="true" />
		</div>
	</div>
	<div class="form-group">
		<label for="sighBasedDisability" class="col-sm-2 control-label">Sigh Based Disability:</label>
		<div class="col-sm-4">
			<form:input path="sighBasedDisability" cssClass="form-control" required="true" />
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-4">
			<input type="submit" value="Continue" class="btn btn-primary" />
		</div>
	</div>
</form:form>