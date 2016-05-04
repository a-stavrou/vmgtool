<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ include file="../layout/taglib.jsp" %>
 

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
<form:form commandName="exam" id="examform" cssClass="form-horizontal">
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title" id="myModalLabel"></h4>
      </div>
      <div class="modal-body">

		<form:hidden path="id" name="id" value="" cssClass="form-control" />

	


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
			<th>Id</th>
			<th>Participant Reference</th>
			<th>University</th>
			<th>Gender</th>
			<th>Age</th>
			<th>Sigh</th>
			<th>Actions</th>
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${exams}" var="exam">
			<tr>
			  
			   <td>
						
						${exam.id}
						</br>
				
				</td>
			   <td>${exam.examUser.participantReference}</td>
			   <td>${exam.examUser.university}</td>
			   <td>${exam.examUser.gender}</td>
			   <td>${exam.examUser.age}</td>
			   <td>${exam.examUser.sighBasedDisability}</td>

				<td>
				<a href="<spring:url value="exam/${exam.id}/detail" /> "  class="btn btn-primary">View Results</a>
				<a href="<spring:url value="exam/${exam.id}/exportrow" /> "  class="btn btn-success">Export Individual Study</a>
				
				</td>
			</tr>
		</c:forEach>
					
	</tbody>
</table>
	<div class="modal-footer">
			<a href="<spring:url value="exam/${exam.id}/exportall" /> "  class="btn btn-success">Export All</a>
			<button type="button" class="btn btn-danger" onClick="history.go(-1);return true;" >Back</button>	
	</div>	
		
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