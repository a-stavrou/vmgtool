<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%@ include file="../layout/taglib.jsp" %>
 
 <style>
body .modal-dialog {
   
    width: auto;
    
}
</style>

 <script type="text/javascript">
 function edit(imageLink){
		 $("#output").attr("src", "data:image/png;base64,"+document.getElementById(imageLink).value);
		 //$("#output").width(400); 
		 //$("#output").height(360);
		 	
	}
 </script>
 
 <div class="modal fade myModal" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-edit-close close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<div class="form-group" align="center">
					<img src="data:image/png;base64,${question.base64Image}" id="output" name="updateimage" class="output" alt="" class="img-rounded">
					</div>
				</div>
			</div>
		</div>
	</div>

</br> </br>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Question</th>
			<th>Image</th>
			<th>Participant Answer</th>
			<th>Actual Answer</th>
			<th>Correct(Y/N)</th>
			<th>Time Elapsed</th>


			

		</tr>
	</thead>
	<tbody>
		<c:forEach items="${answers}" var="answer">
			<tr>
			  
			   <td>${answer.question.title}</br></td>
			   <td> <a hfref="#" data-toggle="modal" 
					 onclick="edit('${answer.question.id}')" data-target="#myModal">Preview</a></td>
					 
			   <c:if test="${answer.question.type eq '1' }">
			   			   <td>${answer.selectedOptionValue}</td>
   			   			   <td>${answer.question.answer.selectedOptionValue}</td>
			   			   
			   </c:if>
			   
			   <c:if test="${answer.question.type eq '2' }">
			   			   <td>${answer.selectedOptionValue} - ${answer.optionsDescriptions}</td>  
   			   			   <td>${answer.question.answer.selectedOptionValue}</td>
			   			   
			   </c:if>
			   <c:if test="${answer.question.type eq '3' }">
			   			   <td>${answer.selectedOptionValue}</td>
   			   			   <td>${answer.question.answer.selectedOptionValue}</td>
			   			   
			   </c:if>
			   <c:if test="${answer.question.type eq '4' }">
			   			   <td>${answer.selectedOptionValue}</td>
   			   			   <td>${answer.question.answer.optionsDescriptions}</td>
			   			   
			   </c:if>
			   			   			  

			   <td>${answer.correct}</td>
			   <td>${answer.elapsedTime}</td>
			   <input type="hidden" id="${answer.question.id}" value="${answer.question.base64Image}"/>
			   

			</tr>
		</c:forEach>
	</tbody>
</table>

		<div class="modal-footer">

			<button type="button" class="btn btn-danger" onClick="history.go(-1);return true;" >Back</button>
				
		</div>