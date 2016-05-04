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
			<th>Question Title</th>
			<th>Question Description</th>
			<th>Answer</th>
			<th>Rule</th>
			
			
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${examanswers}" var="answer">
			<tr>
			  
			   <td>
						${answer.question.title}
						</br>
				
				</td>
			   <td>	             
						${answer.question.description}
			   </td>
				
				<td>
						${answer.selectedOptionValue}
				</td>
				<td>
						${answer.selectedOptionValue}
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