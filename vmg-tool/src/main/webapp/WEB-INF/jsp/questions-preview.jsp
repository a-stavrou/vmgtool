<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<script type="text/javascript">

	function selectOption(args){
		 $('#selectedOptionValue').val(args);
	}
	
	function selectPremise(args){
		 $('#selectedPremise').val(args);
	}

	
	function selectConclusion(args){
		 $('#selectedConclusion').val(args);
	}


	$(document)
	.ready(
			function() {
				var wrapper = $(".wrapper"); 
				$('.createfieldbutton').on('click', function() {
					$(wrapper).empty();
					var fieldCount = $(this).attr('data-id');
					for (var i = 0; i < fieldCount; i++) {
						var inputField = '<form:input path="answer.answerOptions[0].name"  cssClass="anwser-fields btn-block form-control"  name="mytext[]"/><br/>'

							$(wrapper) 
									.append(inputField.replace("answerOptions[0]","answerOptions["+i+"]"));
					}
					
					var continueButton = '<a data-id="${option.name}" class=" btn btn-default text-left btn-block"><span class="pull-left">&nbsp;&nbsp;&nbsp;continue</span>&nbsp;</a>'
						$(wrapper)
							.append(continueButton);
					
				
					$(".anwser-fields").val("");
	
				});
				
				$('.triggerOption1').click(function(e){
					$('.selectedOptionValue').val(this.value)
				});
				
				
				 //$("#output").width(640); 
				   // $("#output").height(480);
				    
				    
			});
								 	
</script>

<form:form commandName="answer" modelAttribute="answer" action="preview/update" method="POST" enctype="multipart/form-data" cssClass="form-horizontal">
	
		<div class="row">
		  <div class="col-xs-12 col-sm-6 col-md-8">
		  <img id="output" src="data:image/png;base64,${question.base64Image}" alt="" class="img-rounded">
		  
		  </div>
		  <div class="col-md-4" style="background-color:gray-light" >
		  	${question.title} <br/>
		  	

		  	<br/><br/><br/>
			<form:hidden path="selectedOptionValue" id="selectedOptionValue" name="optionValue" value="" cssClass="selectedOptionValue form-control" />
			<form:hidden path="selectedPremise" id="selectedPremise" name="selectedPremise" value="" cssClass="selectedPremise form-control" />
			<form:hidden path="selectedConclusion" id="selectedConclusion" name="selectedConclusion" value="" cssClass="selectedConclusion form-control" />
		  	
		 

		  	<c:if test="${question.type eq '3'}" >
		  		
			  	<c:forEach items="${question.questionOptions}" var="option">

					<div class="radio">
						<label> <form:radiobutton class="optionsRadios" onclick="selectOption('${option.name}')" 
						path="selectedRule"  name="optionsRadios" id="optionsRadios1" value="${option.id}" /> ${option.name}
						</label>
					</div>
				</c:forEach>			
		  	</c:if>
		  	
		  	<c:if test="${question.type eq '4'}" >
		  		
			  	<c:forEach items="${question.questionOptions}" var="option">

					<div class="radio">
						<label> <form:checkbox class="optionsRadios" onclick="selectOption('${option.name}')" 
						path="selectedRule"  name="optionsRadios" id="optionsRadios1" value="${option.id}" /> ${option.name}
						</label>
					</div>
				</c:forEach>			
		  	</c:if>
		  	
		  	

			<c:if test="${question.type eq '1'}" >
			  	<c:forEach items="${question.questionOptions}" var="option">
						<a data-id="${option.name}" onclick="selectOption('${option.name}')"  class="btn btn-default text-left btn-block">
							<span class="pull-left">&nbsp;&nbsp;&nbsp;${option.name}</span>&nbsp;</a>			
				</c:forEach>
				<br/>
			</c:if>
			
			<c:if test="${question.type eq '2'}" >
			  	<c:forEach items="${question.questionOptions}" var="option">
						<a data-id="${option.name}" onclick="selectOption('${option.name}')" class="triggerOption2 createfieldbutton btn btn-default text-left btn-block">
							<span class="pull-left">&nbsp;&nbsp;&nbsp;${option.name}</span>&nbsp;</a>			
				</c:forEach>
				<br/>
				<div class="wrapper"></div>
			</c:if>
		  </div>
		</div>

		<br /> <br />
		
		<div class="modal-footer">

			<button type="button" class="btn btn-danger" onClick="history.go(-1);return true;" >Back</button>
			
				
		</div>

</form:form>

