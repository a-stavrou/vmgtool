<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ include file="../layout/taglib.jsp" %>
 
 <!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
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
</script>
<form:form commandName="study" cssClass="form-horizontal">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel">New Study</h4>
      </div>
      <div class="modal-body">

		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Name:</label>
			<div class="col-sm-10">
				<form:input path="studyName" cssClass="form-control" />
				
			</div>
		</div>
		<div class="form-group">
			<label for="name" class="col-sm-2 control-label">Description:</label>
			<div class="col-sm-10">
				<form:input path="studyDesc" cssClass="form-control" />
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
			<th>Study Name</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.studies}" var="study">
			<tr>
			  
			   <td>
			  
				<a href="<spring:url value="studies/${study.id}.html" />">
						
						${study.studyName}</a>
						</br>
	             ${study.studyDesc}	
				
				</td>
				<td>
				 <a href="<spring:url value="/study/remove/${study.id}.html" /> " class="btn btn-danger triggerRemove">remove study</a>
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
