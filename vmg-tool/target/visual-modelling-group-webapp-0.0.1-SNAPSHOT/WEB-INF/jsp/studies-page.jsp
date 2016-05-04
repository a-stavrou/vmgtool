<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ include file="../layout/taglib.jsp" %>
 
 <!-- Button trigger modal -->
<button class="add-button-modal btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
  New Study
</button>

</br> </br>
<script type="text/javascript">
$(document).ready(function() {
	$('.nav-tabs a:first').tab('show'); 
	$(".triggerRemove").click(function(e) {
		e.preventDefault();
		$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
		$("#modalRemove").modal();
	});
});

$('.add-button-modal').on('click', function() {
    document.getElementById("studyform").reset();
});

function edit(id,name,desc,timeout){
    document.getElementById("studyform").reset();
	 $('#studyform')
     .find('[name="studyName"]').val(name).end()
     .find('[name="studyDesc"]').val(desc).end()
     .find('[name="timeout"]').val(timeout).end()
     .find('[name="id"]').val(id).end();

}
</script>
<form:form commandName="study" id="studyform" cssClass="form-horizontal">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">New Study</h4>
      </div>
      <div class="modal-body">

		<form:hidden path="id" name="id" value="" cssClass="form-control" />

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="studyName" name="studyName" cssClass="form-control" required="true" />
			    <form:errors path="studyName"/>
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Description:</label>
			<div class="col-sm-10">
				<form:input path="studyDesc" name="studyDesc" cssClass="form-control" required="true"/>
			    <form:errors path="studyDesc"/>
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Timeout:</label>
			<div class="col-sm-10">
				<form:input path="timeout" name="timeout" cssClass="form-control" required="true"/>
			    
			</div>
		</div>
		
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Random:</label>
			<div class="col-sm-10">
				<form:checkbox path="random" name="random" cssClass="form-control" />
			</div>
		</div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <input type="submit" class="btn btn-primary" value="Save" />
      </div>
    </div>
  </div>
</div>
</form:form>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Name</th>
			<th>Description</th>
			<th>Actions</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.studies}" var="study">
			<tr>
			  
			   <td>
						
						${study.studyName}
						</br>
	             ${study.studyDesc}	
				
				</td>
				
			   <td>
	             ${study.studyDesc}	
				</td>
				
			   
				
				<td>
				<a href="<spring:url value="studies/${study.id}/exam" /> "  class="btn btn-primary">Run Study</a>
				
				<a href="<spring:url value="studies/${study.id}/" /> " class="btn btn-info custom-width">Manage Questions</a>
				<button data-toggle="modal" class="btn-edit btn btn-success custom-width"
					 onclick="edit('${study.id}','${study.studyName}','${study.studyDesc}','${study.timeout}')" data-target="#myModal">Edit Study</button>

				<a href="<spring:url value="studies/remove/${study.id}.html" /> " class="btn btn-danger triggerRemove">Remove</a>
				<a href="<spring:url value="studies/${study.id}/showexams" /> " class="btn btn-default">Tests Taken</a>
				
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">Remove Study</h4>
      </div>
      <div class="modal-body">
        Really remove?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>